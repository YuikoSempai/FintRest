package yandex.task.finalrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import yandex.task.finalrest.data.ShopUnitMessage;

public interface ShopUnitRepo extends JpaRepository<ShopUnitMessage, String> {
}
