package ru.Technopolis.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "todo")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ToDo {

  @Id
  @Getter
  @Setter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Getter
  @Setter
  @Column(name = "description")
  private String description;

  @Getter
  @Setter
  @Column(name = "checked")
  private boolean checked;

  @Getter
  @Setter
  @JsonBackReference
  @ManyToMany
  @JoinTable(name = "todo_user_connect",
          joinColumns = @JoinColumn(name = "todo_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<TodoUser> todoUsers;

  public ToDo(String description, boolean checked) {
    this.description = description;
    this.checked = checked;
    this.todoUsers = new HashSet<>();
  }

  public void addUser(TodoUser user) {
    todoUsers.add(user);
  }

  public void removeUser(TodoUser user) {
    todoUsers.remove(user);
  }
}
