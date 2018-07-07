package com.webdev.spring.controller;


import com.webdev.spring.domain.Product;
import com.webdev.spring.service.ProducerService;
import com.webdev.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController extends BaseSecurityController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProducerService producerService;

    @GetMapping("/")
    public ModelAndView index() {
        return createModelAndView("index");
    }

    @GetMapping("/products")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = createModelAndView("products");
        modelAndView.addObject("products", this.service.findAll());
        return modelAndView;
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@RequestParam long id) {
        if (this.service.exists(id)) {
            Product product = this.service.findById(id);
            this.service.delete(product);
        }
        return "redirect:/products";
    }

    @GetMapping("/product/edit")
    public ModelAndView editProduct(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("products");
        }
        ModelAndView modelAndView = this.createModelAndView("/product/edit");
        modelAndView.addObject("product", this.service.findById(id));
        modelAndView.addObject("producers", this.producerService.findAll());
        return modelAndView;
    }
    @PostMapping("/product/edit")
    public String handleEditProduct(@ModelAttribute Product product) {

        if (!this.service.exists(product.getId())) {
            return "redirect:/products";
        }
        this.service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/product/add")
    public ModelAndView addProduct() {
        ModelAndView modelAndView = this.createModelAndView("/product/add");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("producers", this.producerService.findAll());

        return modelAndView;
    }
    @PostMapping("/product/add")
    public String handleAddProduct(@ModelAttribute Product product) {
        this.service.save(product);
        return "redirect:/products";
    }

}
