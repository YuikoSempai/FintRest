package yandex.task.finalrest.data;

import java.io.Serializable;
import java.util.Objects;

public class ChildId implements Serializable {
    private String id;

    private String parentId;

    public ChildId(){

    }

    public ChildId(String id, String parentId){
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId = (ChildId) o;
        return Objects.equals(id, childId.id) && Objects.equals(parentId, childId.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId);
    }
}
