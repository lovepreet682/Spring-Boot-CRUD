package com.theymeleaf.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.theymeleaf.Entity.StudentEntity;
import com.theymeleaf.Repositary.UserRepository;

@Controller
public class UserController {
    
	@Autowired
	UserRepository userRepository;
	
    @GetMapping("/signup")
    public String showSignUpForm(StudentEntity user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Validated StudentEntity user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        StudentEntity user = userRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        return "update-user";
    }

}