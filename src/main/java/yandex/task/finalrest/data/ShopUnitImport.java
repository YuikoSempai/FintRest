package yandex.task.finalrest.data;

public class ShopUnitImport extends Unit{

    public ShopUnitImport(){}

    public ShopUnitImport(String type, String name, String id, String parentId){
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }
}
