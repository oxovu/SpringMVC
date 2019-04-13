package ru.Technopolis.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static ru.Technopolis.model.FlexibleComparator.Mode.onlyId;

@Component /*Кладем в контейнер */
public class ToDoDAO {
  private static AtomicLong counter = new AtomicLong();
  private static List<ToDo> toDos = new ArrayList<>();
  private FlexibleComparator comparator = new FlexibleComparator();

  public ToDoDAO(){
    create("download matlab", true);
    create("delete matlab", false);
  }

  public ToDo create(String description, boolean checked) {
    long id = counter.incrementAndGet();
    ToDo todo = new ToDo(id, description, checked);
    toDos.add(todo);
    return todo;
  }

  public List<ToDo> read() {
    comparator.setSortBy(onlyId);
    toDos.sort(comparator);
    return toDos;
  }

  public boolean update(long id, String description, boolean checked) {
    Iterator<ToDo> iterator = toDos.iterator();
    if (description == null) description = toDos.get((int) id).getDescription();
    ToDo newTodo = new ToDo(id, description, checked);
    while (iterator.hasNext()) {
      if (iterator.next().getId() == id) {
        iterator.remove();
        toDos.add(newTodo);
        return true;
      }
    }
    return false;
  }

  public boolean delete(long id) {
    Iterator<ToDo> iterator = toDos.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getId() == id) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

  public int leftIteams() {
    int count = toDos.size();
    Iterator<ToDo> iterator = toDos.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().isChecked()) {
        count--;
      }
    }
    return count;
  }
}


