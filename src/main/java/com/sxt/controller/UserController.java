package com.sxt.controller;

import com.sxt.cfg.WebConfig;
import com.sxt.pojo.User;
import com.sxt.service.UserService;
import com.sxt.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @Autowired
    WebConfig webConfig;
    @Resource(name="mongoTemplate")
    private MongoTemplate mongoTemplate;
    @RequestMapping("/user")

    public String getUserById(Model model) {
        String id = "1";
        User user = userService.getUserById(id);
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(user);
        model.addAttribute("list", list);
        mongoTemplate.save(user);
        mongoTemplate.save(user,"shashasha");
        //String userStr= JSON.toJSONString(user);
        ResponseData<Object> objectResponseData = new ResponseData<Object>();
        objectResponseData.setMsg("list3");
        return "list3";
    }

    @RequestMapping("/userList")
    public String selectByExample(Model model, HttpServletRequest request, HttpServletResponse response) {

        List list = userService.selectByExample();
        model.addAttribute("list", list);
        //String userStr= JSON.toJSONString(user);
        request.getSession().setAttribute("username", "hfdhfjd");
        Object username = request.getSession().getAttribute("username");
        System.out.println(username);
        return "list3";
    }


}
