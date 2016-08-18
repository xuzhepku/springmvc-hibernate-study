package me.xuzhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XuZhe on 2016/8/11.
 */
@Controller
public class FirstController {
    @RequestMapping(path = {"/welcome", "/"})
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/hello")
    public String hello(String username, Model model) {
//        model.addAttribute("username", username);
        model.addAttribute(username);
        System.out.println("===inside hello");
        System.out.println("===username is" + username);
        return "hello";
    }
}
