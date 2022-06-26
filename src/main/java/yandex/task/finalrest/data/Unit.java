package yandex.task.finalrest.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Unit {

    protected String type;
    protected String name;
    protected String id;
    protected Long price;
    protected String parentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    protected ZonedDateTime date;

    public Unit(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getParentId() {
        return parentId;
    }

    public Long getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(ZonedDateTime date){
        this.date = date.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", parentId='" + parentId + '\'' +
                ", price=" + price +
                '}';
    }
}
