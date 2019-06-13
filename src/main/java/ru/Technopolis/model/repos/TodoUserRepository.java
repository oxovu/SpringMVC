package ru.Technopolis.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Technopolis.model.entities.TodoUser;

public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {

    TodoUser findTodoUserByName(String username);
}
