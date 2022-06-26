package yandex.task.finalrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yandex.task.finalrest.data.Child;
import yandex.task.finalrest.data.ShopUnit;
import yandex.task.finalrest.data.ShopUnitMessage;
import yandex.task.finalrest.exceptions.NotFoundException;
import yandex.task.finalrest.repo.ChildRepo;
import yandex.task.finalrest.repo.ShopUnitRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NodesController {

    private final ShopUnitRepo shopUnitRepo;
    private final ChildRepo childRepo;

    public NodesController(ShopUnitRepo shopUnitRepo, ChildRepo childRepo) {
        this.shopUnitRepo = shopUnitRepo;
        this.childRepo = childRepo;
    }

    @GetMapping("/nodes/{id}")
    public ShopUnit get(@PathVariable String id){
        ShopUnitMessage shopUnitMessage = shopUnitRepo.findById(id).orElseThrow(NotFoundException::new);
        shopUnitMessage.setDate(shopUnitMessage.getDate());
        if(shopUnitMessage != null && shopUnitMessage.getType().equals("CATEGORY")){
            List<Child> children = childRepo.findAllByParentId(id);
            List<String> idList = children.stream().map(Child::getId).collect(Collectors.toList());
            List<ShopUnitMessage> childrenList = (ArrayList<ShopUnitMessage>) shopUnitRepo.findAllById(idList);
            if(childrenList.isEmpty()) childrenList = null;
            List<ShopUnitMessage> shopUnitsChildren = shopUnitRepo.findAllById(idList);
            for(ShopUnitMessage shopUnit: shopUnitsChildren){
                if (shopUnitMessage.getType().equals("CATEGORY")) get(shopUnit.getId());
                shopUnitMessage.setChildren((ArrayList<ShopUnitMessage>) childrenList);
            }
        }
        assert shopUnitMessage != null;
        return new ShopUnit(shopUnitMessage);
    }
}
