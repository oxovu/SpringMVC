package ru.Technopolis.model;

import ru.Technopolis.model.entities.ToDo;

import java.util.Comparator;

import static ru.Technopolis.model.FlexibleComparator.Mode.full;

public class FlexibleComparator implements Comparator<ToDo> {
  public enum Mode {onlyId, full}

  private Mode sortBy = full;

  @Override
  public int compare(ToDo o1, ToDo o2) {
    switch (sortBy){
      case full: return Long.compare(o1.getId(), o2.getId())*o1.getDescription().compareTo(o2.getDescription());
      case onlyId: return Long.compare(o1.getId(), o2.getId());
    }
    return 0;
  }

  public void setSortBy(Mode mode){
    this.sortBy = mode;
  }
}
