package com.dreamteam.chaosgame.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetWebPage {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("name", "World!");
        return "123"; // Вернет шаблон greeting.html
    }


}
