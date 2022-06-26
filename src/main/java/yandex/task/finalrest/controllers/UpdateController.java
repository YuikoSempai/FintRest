package yandex.task.finalrest.controllers;

import yandex.task.finalrest.data.Child;
import yandex.task.finalrest.data.ShopUnitMessage;
import yandex.task.finalrest.repo.ChildRepo;
import yandex.task.finalrest.repo.ShopUnitRepo;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UpdateController {

    protected ShopUnitRepo shopUnitRepo;
    protected ChildRepo childRepo;

    public UpdateController(ShopUnitRepo shopUnitRepo, ChildRepo childRepo) {
        this.shopUnitRepo = shopUnitRepo;
        this.childRepo = childRepo;
    }

    protected void updateImports(String parentId, ZonedDateTime date) {
        Optional<ShopUnitMessage> optionalMessage = shopUnitRepo.findById(parentId);
        if (optionalMessage.isPresent()) {
            ShopUnitMessage shopUnitMessage = optionalMessage.get();
            if (shopUnitMessage.getType().equals("OFFER")) throw new RuntimeException("Parent is a offer");
            shopUnitMessage.setPrice(updatePrice(parentId));
            shopUnitMessage.setDate(date);
            shopUnitRepo.save(shopUnitMessage);
            if (shopUnitMessage.getParentId() != null) {
                updateImports(shopUnitMessage.getParentId(),shopUnitMessage.getDate());
            }

        }
    }

    private Long updatePrice(String parentId) {
        List<Child> children = childRepo.findAllByParentId(parentId);
        List<String> idList = children.stream().map(Child::getId).collect(Collectors.toList());
        List<ShopUnitMessage> shopUnitsChildren = shopUnitRepo.findAllById(idList);
        Long price = getFullPrice(parentId);
        Integer count = findCountOfChildren(parentId);
        return price / count;
    }

    private Integer findCountOfChildren(String parentId) {
        Integer count = 0;
        List<Child> children = childRepo.findAllByParentId(parentId);
        List<String> idList = children.stream().map(Child::getId).collect(Collectors.toList());
        List<ShopUnitMessage> shopUnitsChildren = shopUnitRepo.findAllById(idList);
        for (ShopUnitMessage shopUnit : shopUnitsChildren) {
            if (shopUnit.getType().equals("CATEGORY")) {
                count += findCountOfChildren(shopUnit.getId());
            } else {
                count++;
            }
        }
        return count;
    }

    private Long getFullPrice(String parentId) {
        Long price = 0L;
        List<Child> children = childRepo.findAllByParentId(parentId);
        List<String> idList = children.stream().map(Child::getId).collect(Collectors.toList());
        List<ShopUnitMessage> shopUnitsChildren = shopUnitRepo.findAllById(idList);
        for (ShopUnitMessage shopUnit : shopUnitsChildren) {
            if (shopUnit.getType().equals("CATEGORY")) {
                price += getFullPrice(shopUnit.getId());
            } else {
                price += shopUnit.getPrice();
            }
        }
        return price;
    }

}
