package com.maminozolotce.spring_test.controllers;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class GlobalExceptionController implements ErrorController {

    @RequestMapping("/error")
    public String getErrorPage(){
        return "error-page";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable throwable){
        return "redirect:/error";

    }
}
