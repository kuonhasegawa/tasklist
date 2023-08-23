package jp.gihyo.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class HomeRestController {

    private final TaskListDao dao;

    @Autowired
    HomeRestController(TaskListDao dao){
        this.dao = dao;
    }

    @PostMapping("/rest_add")
    List<HomeController.TaskItem> addItem(@RequestParam("task") String task,
                                          @RequestParam("deadline") String deadLine){
        String id = UUID.randomUUID().toString().substring(0, 8);
        HomeController.TaskItem item = new HomeController.TaskItem(id, task, deadLine, "",false);
        this.dao.add(item);
        return this.dao.findAll();
    }
}
