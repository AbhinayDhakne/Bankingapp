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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.am.model.User;
import com.am.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<User> listuser = service.listAll();
		model.addAttribute("listuser", listuser);
		return "index";
	}

	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("user") User std) {
		
		//Validation of Mobile no.
		String a=std.getMobile();
		if(a.length()==10) {
			boolean flag = true;
			for(char c : a.toCharArray()) {
				if(!Character.isDigit(c)) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				return "redirect:/validate";
			}
		}
		else{
			return "redirect:/validate";
		}
		
		service.save(std);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/validate")
	@ResponseBody()
	public String getvalidate() {
		String w = "<html><head><script>alert(\"Please validate your mobile number!\");window.location.href = \"/new\";</script></head></html>";
		return w;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("new");
		User std = service.get(id);
		mav.addObject("user", std);
		return mav;

	}
	@RequestMapping("/delete/{id}")
	public String deletestudent(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}
	
}
