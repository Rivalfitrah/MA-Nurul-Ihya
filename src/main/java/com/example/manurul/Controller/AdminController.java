package com.example.manurul.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.manurul.Service.AdminService;
import com.example.manurul.entity.Admin;

@Controller
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String getloginpage(Model model){
        model.addAttribute("loginRequest", new Admin());
        return "login-admin";
    }


    @PostMapping("/login")
    public String login (@ModelAttribute Admin admin, Model model){
        System.out.println("login request" + admin);
        Admin authenticated = adminService.authenticate(admin.getUsername(), admin.getPassword());
        if (authenticated != null) {
            model.addAttribute("adminLogin", authenticated.getUsername());
            return "redirect:/index2";
        }else{
            return "redirect:/login";
        }
    }   
}