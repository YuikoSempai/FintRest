package yandex.task.finalrest.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "imports")
public class ShopUnitMessage {

    @Transient
    protected ArrayList<ShopUnitMessage> children;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "parentId")
    private String parentId;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private Long price;
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime date;

    public ShopUnitMessage(){}

    public ShopUnitMessage(ShopUnitImport shopUnitImport){
        this.id = shopUnitImport.getId();
        this.parentId = shopUnitImport.getParentId();
        this.type = shopUnitImport.getType();
        this.name = shopUnitImport.getName();
        this.price = shopUnitImport.getPrice();
    }

    @Override
    public String toString() {
        return "ShopUnitMessage{" +
                "children=" + children +
                ", id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

    public ArrayList<ShopUnitMessage> getChildren() {
        return children;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getPrice() {
        return price;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setChildren(ArrayList<ShopUnitMessage> children) {
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDate(ZonedDateTime date){
        this.date = date.withZoneSameInstant(ZoneId.of("UTC"));
    }
}
