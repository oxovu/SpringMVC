package ru.Technopolis.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todo_user")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TodoUser {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "todo_user_connect",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id"))
    private Set<ToDo> todos;

    public TodoUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.todos = new HashSet<>();
    }

    public void addToDo(ToDo toDo) {
        todos.add(toDo);
    }

    public void removeToDo(ToDo toDo) {
        todos.remove(toDo);
    }
}
