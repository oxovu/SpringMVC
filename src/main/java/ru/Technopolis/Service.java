package ru.Technopolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.Technopolis.model.ToDoDAO;
import ru.Technopolis.model.entities.ToDo;
import ru.Technopolis.model.repos.TodoRepository;
import ru.Technopolis.model.repos.TodoUserRepository;

@Controller
public class Service {

  @Autowired
  private TodoRepository todoRepository;
  @Autowired
  private TodoUserRepository todoUserRepository;

  private ToDoDAO dao;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("toDos", dao.getToDos());
    model.addAttribute("counter", dao.leftIteams());
    return "index";
  }

  @Autowired //Dependency Injection
  public Service(ToDoDAO dao) {
    this.dao = dao;
  }

  @RequestMapping(value = "/create")
  public @ResponseBody
  ToDo create(@RequestParam String description) {
    return dao.create(description, false);
  }

  @RequestMapping(value = "/update")
  public @ResponseBody
  void update(@RequestParam long id, @RequestParam boolean checked) {
    dao.update(id, checked);
  }

  @RequestMapping(value = "/delete")
  public @ResponseBody
  void delete(@RequestParam long id) {
    dao.delete(id);
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }
}
