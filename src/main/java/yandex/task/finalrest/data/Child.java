package yandex.task.finalrest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "children")
public class Child implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "parentId")
    private String parentId;

    public Child(){}

    public Child(String id, String parentId){
        this.id = id;
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(id, child.id) && Objects.equals(parentId, child.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId);
    }
}
