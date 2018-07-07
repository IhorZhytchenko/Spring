package com.webdev.spring.controller;

import com.webdev.spring.domain.Producer;
import com.webdev.spring.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProducerController extends BaseSecurityController{
    @Autowired
    private ProducerService service;

    @GetMapping("/producers")
    public ModelAndView getProducers() {
        ModelAndView modelAndView = createModelAndView("producers");
        modelAndView.addObject("producers", this.service.findAll());
        return modelAndView;
    }

    @GetMapping("/producer/delete")
    public String deleteProducer(@RequestParam long id) {
        if (this.service.exists(id)) {
            Producer producer = this.service.findById(id);
            this.service.delete(producer);
        }
        return "redirect:/producers";
    }

    @GetMapping("/producer/edit")
    public ModelAndView editProducer(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("producers");
        }
        ModelAndView modelAndView = this.createModelAndView("/producer/edit");
        modelAndView.addObject("producer", this.service.findById(id));
        return modelAndView;
    }
    @PostMapping("/producer/edit")
    public String handleEditProducer(@ModelAttribute Producer producer) {

        if (!this.service.exists(producer.getId())) {
            return "redirect:/producers";
        }
        this.service.save(producer);
        return "redirect:/producers";
    }

    @GetMapping("/producer/add")
    public ModelAndView addProducer() {
        return this.createModelAndView("/producer/add");
    }
    @PostMapping("/producer/add")
    public String handleAddProducer(@ModelAttribute Producer producer) {
        this.service.save(producer);
        return "redirect:/producers";
    }
}
