package com.sxt.controller;

import com.sxt.pojo.User;
import com.sxt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaxueting on 2017/6/5.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String getUserById(Model model){
        String  id="1";
        User user=userService.getUserById(id);
        ArrayList<Object> list = new ArrayList<>();
        list.add(user);
        model.addAttribute("list",list);

        //String userStr= JSON.toJSONString(user);
        return "list3";
    }
    @RequestMapping("/userList")
    public String selectByExample(Model model , HttpServletRequest request, HttpServletResponse response){

      List list=userService.selectByExample();

        model.addAttribute("list",list);

        //String userStr= JSON.toJSONString(user);
        request.getSession().setAttribute("username","hfdhfjd");
        Object username = request.getSession().getAttribute("username");

        System.out.println(username);

        return "list3";
    }
}
