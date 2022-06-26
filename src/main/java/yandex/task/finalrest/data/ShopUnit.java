package yandex.task.finalrest.data;

import java.util.ArrayList;

public class ShopUnit extends Unit{

    private ArrayList<ShopUnit> children;

    public ShopUnit(){}

    public ShopUnit(ShopUnitMessage shopUnitMessage){
        this.id = shopUnitMessage.getId();
        this.date = shopUnitMessage.getDate();
        this.parentId = shopUnitMessage.getParentId();
        this.name = shopUnitMessage.getName();
        this.type = shopUnitMessage.getType();
        this.price = shopUnitMessage.getPrice();
        this.children = getChildrenList(shopUnitMessage.getChildren());
    }

    public ArrayList<ShopUnit> getChildrenList(ArrayList<ShopUnitMessage> childrenList) {
        if (childrenList == null) return null;
        ArrayList<ShopUnit> result = new ArrayList<>();
        for(ShopUnitMessage shopUnitMessage: childrenList){
            result.add(new ShopUnit(shopUnitMessage));
        }
        return result;
    }

    public ArrayList<ShopUnit> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "ShopUnit{" +
                "children=" + children +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", parentId='" + parentId + '\'' +
                ", price=" + price +
                '}';
    }
}
