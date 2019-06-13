package ru.Technopolis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.Technopolis.model.entities.ToDo;
import ru.Technopolis.model.repos.TodoRepository;
import ru.Technopolis.model.repos.TodoUserRepository;

import java.util.List;

@Component
public class ToDoDAO {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoUserRepository todoUserRepository;
    List<ToDo> toDos;

    public ToDo create(String description, boolean checked) {
        ToDo todo = new ToDo(description, checked);
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        todo.addUser(
                todoUserRepository.findTodoUserByName(userDetails.getUsername()));
        todoRepository.saveAndFlush(todo);
        readToDos();
        return todo;
    }

    public void update(long id, boolean checked) {
        ToDo todo = todoRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);
        todo.setChecked(checked);
        todoRepository.save(todo);
        readToDos();
    }

    public void delete(long id) {
        todoRepository.deleteById(id);
        readToDos();
    }

    public int leftIteams() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return todoRepository.findAllByTodoUsersAndCheckedIsTrue(
                todoUserRepository.findTodoUserByName(userDetails.getUsername())
        ).size();
    }

    public List<ToDo> getToDos() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return toDos = todoRepository.findAllByTodoUsers(
                todoUserRepository.findTodoUserByName(userDetails.getUsername())
        );
    }

    public void readToDos() {
        toDos = getToDos();
    }
}


