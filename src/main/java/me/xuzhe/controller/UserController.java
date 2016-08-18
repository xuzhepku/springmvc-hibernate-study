package me.xuzhe.controller;

import me.xuzhe.exception.UserException;
import me.xuzhe.model.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuZhe on 2016/8/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Map<String, User> users = new HashMap<String, User>();

    public UserController() {
        users.put("aaa", new User("aaa", "a", "password", "a@email.com"));
        users.put("bbb", new User("bbb", "b", "password", "b@email.com"));
        users.put("ccc", new User("ccc", "c", "password", "c@email.com"));
        users.put("ddd", new User("ddd", "d", "password", "d@email.com"));
        users.put("eee", new User("eee", "e", "password", "e@email.com"));
    }

    // 看起来value是path的别名，而path可以省略
    // 下面这个写法，requestmapping中有两个参数的时候，默认写value，否则可以不用写。
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        // jsp 拿到的key是第一个参数"users"
        // 这个model是怎么注入和返回的？很神奇。。。
        model.addAttribute("users", users);
        // 相对路径？我去
        return "/user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user) {
//        model.addAttribute("user", new User());
        return "/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated User user, BindingResult br, @RequestParam("attachs")MultipartFile[] attachs, HttpServletRequest req) {
        if (br.hasErrors()) {
            return "/user/add";
        } else {
            for (MultipartFile attach : attachs) {
                if (attach.isEmpty()) continue;
                System.out.println(attach.getName() + "," + attach.getOriginalFilename() + "," + attach.getContentType());
                String realPath = req.getServletContext().getRealPath("/resources/upload");
                System.out.println(realPath);
                File f = new File(realPath + "/" + attach.getOriginalFilename());
                try {
                    FileUtils.copyInputStreamToFile(attach.getInputStream(), f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            users.put(user.getUsername(), user);
            return "redirect:/user/list";
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String show(@PathVariable String username, Model model) {
        model.addAttribute(users.get(username));
        System.out.println("===" + users.get(username));
        return "/user/show";
    }

    /**
     * 返回JSON
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, params = "json")
    @ResponseBody
    public User show(@PathVariable String username) {
        return users.get(username);
    }

    /**
     * 跳转到user update页面
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
    public String update(@PathVariable String username, Model model) {
        model.addAttribute(users.get(username));
        return "/user/update";
    }

    /**
     * 更新一个user（update）
     *
     * @param username
     * @param user
     * @return
     */
    @RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
    public String update(@PathVariable String username, @Validated User user, BindingResult br) {
        if (br.hasErrors()) {
            return "/user/update";
        } else {
            users.put(username, user);
            //前端跳转
            return "redirect:/user/list";
        }
    }

    @RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable String username) {
        System.out.println("===inside usercontroller delete, username is: " + username);
        users.remove(username);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        System.out.println("===inside usercontroller login, username is: " + username);
        if (!users.containsKey(username)) {
            throw new UserException("用户名不存在异常");
        }
        User u = users.get(username);
        if (!u.getPassword().equals(password)) {
            throw new UserException("用户密码不正确异常");
        }
        session.setAttribute("loginUser", u);
        return "redirect:/user/list";
    }

//    /**
//     * @ExceptionHandler中能处理昂昂前controller中的异常
//     * req其实是说注入进来的，在这里没有用到，好像用到什么就可以注入什么似得
//     * @param ue
//     * @param req
//     * @return
//     */
//    @ExceptionHandler(value = {UserException.class})
//    public String UserExceptionHandler(UserException ue, HttpServletRequest req){
//        req.setAttribute("e", ue);
//        return "error";
//    }


}
