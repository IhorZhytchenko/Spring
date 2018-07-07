package com.webdev.spring.controller;


import com.webdev.spring.domain.Role;
import com.webdev.spring.domain.User;
import com.webdev.spring.service.RoleService;
import com.webdev.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UserController extends BaseSecurityController{
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user/all")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = createModelAndView("user/all");
        modelAndView.addObject("users", this.service.findAll());
        return modelAndView;
    }

    @GetMapping("/user/delete")
    public String deleteProduct(@RequestParam long id) {
        if (this.service.exists(id)) {
            User user = this.service.findById(id);
            this.service.delete(user);
        }
        return "redirect:/user/all";
    }

    @GetMapping("/user/edit")
    public ModelAndView editProduct(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("/user/all");
        }
        ModelAndView modelAndView = this.createModelAndView("/user/edit");
        modelAndView.addObject("user", this.service.findById(id));
        modelAndView.addObject("roless", this.roleService.findAll());
        return modelAndView;
    }
    @PostMapping("/user/edit")
    public String handleEditProduct(@ModelAttribute User user,
                                    @RequestParam String pass) {

        if (pass != null && pass.trim().length() > 0) {
            user.setPassword(this.service.encode(pass));
        }
        if (!this.service.exists(user.getId())) {
            return "redirect:/user/all";
        }
        this.service.update(user);
        return "redirect:/user/all";
    }

    @GetMapping("/user/add")
    public ModelAndView addProduct() {
        ModelAndView modelAndView = this.createModelAndView("/user/add");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roless", this.roleService.findAll());

        return modelAndView;
    }
    @PostMapping("/user/add")
    public String handleAddProduct(@ModelAttribute User user) {
        this.service.add(user);
        return "redirect:/user/all";
    }


}
