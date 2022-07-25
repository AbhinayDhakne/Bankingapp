package com.am.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.am.model.Account;
import com.am.service.AccountService;

@Controller
public class AccountController {
	
	 @Autowired
	    private AccountService service;

	    @GetMapping("/acc")
	    public String viewHomePage(Model model) {
	        List<Account> listacc = service.listAll();
	        model.addAttribute("listacc", listacc);
	        return "showacc";
	    }

	    @GetMapping("/acc/new")
	    public String add(Model model) {
	        model.addAttribute("account", new Account());
	        return "newacc";
	    }

	    @RequestMapping(value = "/acc/save", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute("account") Account acc) {
	        service.save(acc);
	        return "redirect:/acc";
	    }

	    @RequestMapping("/acc/edit/{id}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("newacc");
	        Account std = service.get(id);
	        mav.addObject("account", std);
	        return mav;
	        
	    }
	    @RequestMapping("/acc/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/acc";
	    }
}

