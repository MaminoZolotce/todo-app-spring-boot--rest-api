package com.maminozolotce.spring_test.controllers;

import com.maminozolotce.spring_test.entity.DTO.TaskContainerDto;
import com.maminozolotce.spring_test.entity.TaskStatus;
import com.maminozolotce.spring_test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommonController {
    private final TaskService taskService;

    @Autowired
    public CommonController(TaskService taskService) {
        this.taskService = taskService;
    }



    @RequestMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getMainPage(Model model, @RequestParam(name="filter", required = false) String filterMode){
        TaskContainerDto container = taskService.findAllRecords(filterMode);

        model.addAttribute("tasks", container.getTasks());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        return "main-page";
    }

    /*@RequestMapping("/home")
    public ModelAndView getMainPage(Model model , @RequestParam(name="filter", required = false) String filterMode){
        ModelAndView modelAndView = new ModelAndView();
        RecordContainerDto records = recordService.findAllRecords(filterMode);
        modelAndView.setViewName("main-page");
        modelAndView.getModelMap().addAttribute("records", records.getRecords());
        modelAndView.getModelMap().addAttribute("numberOfActiveRecords", records.getNumberOfActiveRecords());
        modelAndView.getModelMap().addAttribute("numberOfDoneRecords", records.getNumberOfDoneRecords());
        return modelAndView;
    }*/


    @PostMapping( "/add-task")
    public String addRecord(@RequestParam("title") String title ){
        taskService.saveRecord(title);
        return "redirect:/home";
    }

    @PostMapping("/remove-task")
    public String removeRecord(@RequestParam("id") int id,
                               @RequestParam(name = "filterMode", required = false) String filterMode){
        taskService.deleteRecord(id);
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter="+filterMode : "");
    }

    @PostMapping( "/make-task-done")
    public String makeRecordDone(@RequestParam("id") int id,
                                 @RequestParam(name="filter", required = false) String filterMode,
                                 @RequestParam("status") TaskStatus status){
        TaskStatus newStatus = (status == TaskStatus.ACTIVE) ? TaskStatus.DONE : TaskStatus.ACTIVE;
        taskService.makeRecordDone(id, newStatus);
        return "redirect:/home" + (filterMode != null && !filterMode.isBlank() ? "?filter="+filterMode : "");
    }


}
