package com.webdev.spring.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
public class SecurityService {

    public ModelAndView createModelAndView(String viewName) {
        ModelAndView result = new ModelAndView(viewName);
        result.addObject("curUser", getUser());

        return result;
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object credential = auth.getPrincipal();
        User user;
        if (credential.equals("anonymousUser")) {
            user = null;
        } else {
            user = (User) credential;
        }

        return user;
    }

}
