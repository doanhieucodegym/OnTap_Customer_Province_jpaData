package com.DoanHieu.controller;

import com.DoanHieu.model.Customer;
import com.DoanHieu.model.Province;
import com.DoanHieu.service.CustomerService;
import com.DoanHieu.service.ProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //tao doi tuong cho province
    @Autowired
    private ProvinceService provinceService;
    // boo sung danh sach cho nguoi dung  lua chon tinh.@ModelAttribute laf cach de gan danh sach tat ca model cua view

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    //hien thi khach  hang
    @GetMapping("/customers")
    public ModelAndView listCustomers(){
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    // tao  moi  khach hang
    //show man hinh
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView =new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    //tra ve save
    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }
    // edit customer
    //lay  customer ra
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if(customer!=null){
            ModelAndView modelAndView =new ModelAndView("/customer/edit");
            //link vao  view
            modelAndView.addObject("customer",customer);
            //tao dooi tuong truyen vao
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    // sua customer
    @PostMapping("/edit-customer")
    public  ModelAndView updateCustomer(@ModelAttribute("customer")Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView =new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Customer update successfully!");
        return modelAndView;

    }
    //xoa customer
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer =customerService.findById(id);
        if(customer!=null){
          ModelAndView modelAndView = new ModelAndView("/customer/delete");
          modelAndView.addObject("customer" ,customer);
          return modelAndView;
        }else {
            ModelAndView modelAndView =new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    //xoa
    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer")Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customers";
    }

}
