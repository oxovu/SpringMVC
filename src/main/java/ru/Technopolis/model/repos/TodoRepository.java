package ru.Technopolis.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Technopolis.model.entities.ToDo;
import ru.Technopolis.model.entities.TodoUser;

import java.util.List;

public interface TodoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByTodoUsers(TodoUser user);
    List<ToDo> findAllByCheckedIsTrue();
    List<ToDo> findAllByTodoUsersAndCheckedIsTrue(TodoUser user);
}
