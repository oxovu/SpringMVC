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

  public boolean create(String description) {
    long id = counter.incrementAndGet();
    ToDo todo = new ToDo(id, description);
    toDos.add(todo);
    return true;
  }

  public List<ToDo> read() {
    comparator.setSortBy(onlyId);
    toDos.sort(comparator);
    return toDos;
  }

  public boolean update(long id, String description) {
    Iterator<ToDo> iterator = toDos.iterator();
    ToDo newTodo = new ToDo(id, description);
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
}


