package yandex.task.finalrest.data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ShopUnitImportRequest {

    private final ArrayList<ShopUnitImport> items;
    private final ZonedDateTime updateDate;


    public ShopUnitImportRequest(ArrayList<ShopUnitImport> items, ZonedDateTime updateDate) {
        this.items = items;
        this.updateDate = updateDate;
    }

    public ArrayList<ShopUnitImport> getItems() {
        return items;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "ShopUnitImportRequest{" +
                "items=" + items +
                ", updateDate=" + updateDate +
                '}';
    }

}
