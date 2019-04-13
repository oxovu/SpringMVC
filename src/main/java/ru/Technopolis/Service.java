package ru.Technopolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.Technopolis.model.ToDo;
import ru.Technopolis.model.ToDoDAO;

import java.util.List;

@Controller
public class Service {

  private ToDoDAO dao;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("toDos", dao.read());
    model.addAttribute("counter", dao.leftIteams());
    return "index";
  }

  @Autowired //Dependency Injection
  public Service(ToDoDAO dao) {
    this.dao = dao;
  }

  @RequestMapping(value = "/create")
  public @ResponseBody /*Превращает в JSON*/
  ToDo create(@RequestParam String description) {
    return dao.create(description, false);
  }

  @RequestMapping(value = "/read")
  public @ResponseBody /*Превращает в JSON*/
  List<ToDo> read() {
    return dao.read();
  }

  @RequestMapping(value = "/update")
  public @ResponseBody /*Превращает в JSON*/
  boolean update(@RequestParam long id, @RequestParam String description, @RequestParam boolean checked) {
    return dao.update(id, description, checked);
  }

  @RequestMapping(value = "/delete")
  public @ResponseBody /*Превращает в JSON*/
  boolean delete(@RequestParam long id) {
    return dao.delete(id);
  }
}
