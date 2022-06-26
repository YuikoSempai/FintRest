package yandex.task.finalrest.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import yandex.task.finalrest.data.Child;
import yandex.task.finalrest.data.Error;
import yandex.task.finalrest.data.ShopUnitMessage;
import yandex.task.finalrest.exceptions.NotFoundException;
import yandex.task.finalrest.repo.ChildRepo;
import yandex.task.finalrest.repo.ShopUnitRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DeleteController extends UpdateController {

    public DeleteController(ShopUnitRepo shopUnitRepo, ChildRepo childRepo) {
        super(shopUnitRepo, childRepo);
    }

    @DeleteMapping("/delete/{id}")
    public Error delete(@PathVariable String id) {
        Optional<ShopUnitMessage> optionalMessage = shopUnitRepo.findById(id);
        if(optionalMessage.isPresent()) {
            System.out.println("test");
            ShopUnitMessage shopUnitMessage = optionalMessage.get();
            shopUnitRepo.deleteById(id);
            List<Child> children = childRepo.findAllByParentId(id);
            List<String> idList = children.stream().map(Child::getId).collect(Collectors.toList());
            childRepo.deleteAllById(idList);
            if (shopUnitMessage.getParentId() != null) {
                updateImports(shopUnitMessage.getParentId(),shopUnitMessage.getDate());
            }
            return new Error(200);
        }else{
            throw new NotFoundException();
        }
    }
}
