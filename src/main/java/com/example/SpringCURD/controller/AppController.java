package com.example.SpringCURD.controller;

import com.example.SpringCURD.entity.User;
import com.example.SpringCURD.entityImpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    private UserImpl userImpl;

    @Autowired(required = true)
    public void setUserImpl(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    @GetMapping("operations")
    public String operation(){
        return "operations";
    }

    @GetMapping("create")
    public String create(Model model){

        User user = new User(0,"","","");
        model.addAttribute("user",user);
        return "create";

    }

    @GetMapping("read")
    public String read(Model model){
        model.addAttribute("user",new User());
        return "read";
    }

    @GetMapping("update")
    public String update(){
        return "update";
    }

    @GetMapping("delete")
    public String delete(){
        return "delete";
    }

    @GetMapping("updateUser")
    public String updateUser(Model model){
        return "updateUser";
    }

    @PostMapping("create")
    public String create(@RequestParam("id")int id,@RequestParam("name")String name, Model model,
                         @RequestParam("email")String email, @RequestParam("password")String password){

        User user = new User(id,name,email,password);
        if(id==0){
            userImpl.setNewUser(user);
            model.addAttribute("userAdded","User added successfully");
        }else{
            userImpl.updateUser(user);
            model.addAttribute("updateMessage","user Updated");
        }
        System.out.println(id);
        try{
        }catch (Exception e){
            System.out.println(e);
        }
        return "redirect:operations";
    }

    @PostMapping("read")
    public String read(@RequestParam("email")String email, Model model){

        User user = userImpl.getUser(email);
        if(user==null){
            model.addAttribute("user",new User());
            model.addAttribute("readError","User not found");
        }else {
            System.out.println(user);
            model.addAttribute("user", user);
        }
        return "read";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("email")String email,Model model){
        if(userImpl.deleteUser(email)){
            model.addAttribute("deleteMessage","User Deleted :)");
        }else{
            model.addAttribute("deleteMessage","User not found :(");
        }
        return "redirect:delete";
    }

    @PostMapping("update")
    public String update(@RequestParam("email")String email, Model model){

        User user = userImpl.getUser(email);
        if(user!=null){
            model.addAttribute("user", user);
            return "create";
        }else {
            model.addAttribute("user",new User());
            model.addAttribute("updateMessage","User not found");
            return "update";
        }
    }
}
