package yandex.task.finalrest.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yandex.task.finalrest.data.*;
import yandex.task.finalrest.data.Error;
import yandex.task.finalrest.repo.ChildRepo;
import yandex.task.finalrest.repo.ShopUnitRepo;

import java.util.Comparator;
import java.util.Optional;

@RestController
public class ImportsController extends UpdateController{

    public ImportsController(ShopUnitRepo shopUnitRepo, ChildRepo childRepo) {
        super(shopUnitRepo, childRepo);
    }

    @PostMapping("/imports")
    public Error add(@RequestBody ShopUnitImportRequest importRequest){
        if (importRequest == null || importRequest.getItems() == null) throw new RuntimeException("Wrong request body");
        importRequest.getItems().sort(Comparator.comparing(Unit::getType));
        if(!validateRequest(importRequest)) throw new RuntimeException();
        for (ShopUnitImport shopUnitImport: importRequest.getItems()){
            Optional<ShopUnitMessage> optionalUnitMessage = shopUnitRepo.findById(shopUnitImport.getId());
            ShopUnitMessage unitMessage;
            if(optionalUnitMessage.isPresent()){
                unitMessage = optionalUnitMessage.get();
                unitMessage.setName(shopUnitImport.getName());
                unitMessage.setParentId(shopUnitImport.getParentId());
                if(shopUnitImport.getType().equals("OFFER")){
                    unitMessage.setPrice(shopUnitImport.getPrice());
                }
            }else{
                unitMessage = new ShopUnitMessage(shopUnitImport);
            }
            unitMessage.setDate(importRequest.getUpdateDate());
            if(unitMessage.getParentId() != null) childRepo.save(new Child(unitMessage.getId(),unitMessage.getParentId()));
            shopUnitRepo.save(unitMessage);
            if(unitMessage.getParentId() != null){
//                childrenRepo.save(new Children(shopUnitImport.getId(), shopUnitImport.getParentId()));
                if(unitMessage.getType().equals("OFFER")){
                    updateImports(unitMessage.getParentId(),unitMessage.getDate());
                }
            }
        }
        return new Error(200);
    }

    private boolean validateRequest(ShopUnitImportRequest shopUnitImportRequest){
        if(shopUnitImportRequest.getUpdateDate() == null) return false;
        for(ShopUnitImport shopUnitImport: shopUnitImportRequest.getItems()){
            if(!validateShopUnitImport(shopUnitImport)) return false;
            Optional<ShopUnitMessage> optionalMessage = shopUnitRepo.findById(shopUnitImport.getId());
            if(optionalMessage.isPresent()){
                if(shopUnitImport.getParentId() != null){
                    ShopUnitMessage parent = shopUnitRepo.findById(shopUnitImport.getParentId()).orElseThrow(RuntimeException::new);
                    if (parent.getType().equals("OFFER")) throw  new RuntimeException();
                }
            }
        }
        return true;
    }

    public boolean validateShopUnitImport(ShopUnitImport shopUnitImport){
        if(shopUnitImport.getId() == null || shopUnitImport.getName() == null || shopUnitImport.getType() == null) return false;
        if(shopUnitImport.getType().equals("CATEGORY") && shopUnitImport.getPrice() != null) return false;
        if(shopUnitImport.getType().equals("OFFER") &&
                (shopUnitImport.getPrice() == null || shopUnitImport.getPrice()<0)) return false;
        return true;
    }
}
