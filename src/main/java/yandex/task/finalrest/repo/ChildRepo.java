package yandex.task.finalrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import yandex.task.finalrest.data.Child;

import java.util.List;

public interface ChildRepo extends JpaRepository<Child, String> {
    List<Child> findAllByParentId(String parentId);
    Child findByParentId(String parentId);
}
