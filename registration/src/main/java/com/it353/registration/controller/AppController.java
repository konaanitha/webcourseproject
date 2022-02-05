package com.it353.registration.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.it353.registration.entity.Users;
import com.it353.registration.exception.UserAlreadyExistException;
import com.it353.registration.repository.UserRepository;
import com.it353.registration.service.UserService;
import com.it353.registration.util.FileUploadUtil;


@Controller
public class AppController {

		@GetMapping("")
		public String viewHomePage() {
		return "index";
		}

		@GetMapping("/register")
		public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Users());

		return "signup_form";
		}
		
		
		
		@Autowired
		private UserRepository userRepo;
		
		@Autowired
		private UserService userService;


		
		@GetMapping("/users")
		public String listUsers(Model model, HttpServletRequest request) {
		List<Users> listUsers = userRepo.findAll();//it is going to find all users
		model.addAttribute("listUsers", listUsers);
		
		Principal principal = request.getUserPrincipal();//get authenticated user
		Users user = userRepo.findByEmail(principal.getName()); //find the User object of the authenticated user

		model.addAttribute("user", user);


		return "users";
		}
		
	/*	@PostMapping("/process_register")
		public String  processRegister(Users user, Model model) {
		
			
		
		/*Users u = userRepo.findByEmail(user.getEmail());

		if (u !=null) {
		//sent an error message 
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Email already existed existed");
		model.addAllAttributes(map);
		return "signup_form";
		} else {
		userRepo.save(user);
		return "register_success";
		}

		userRepo.save(user);

		return "register_success";
		}
} 


      try {
    	  userService.saveUser(user);
    	  return "redirect:/login";
      }
      catch(UserAlreadyExistException e ) {
    	  model.addAttribute("message", "An account already exists for this email");
    	  return "signup_form";
      }
      
      }*/
		
		@PostMapping("/process_register")
		public String processRegister(Users user, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		        user.setPhoto(fileName);
		        try {
		            userService.saveUser(user);
		            String uploadDir = "H:/images/" + user.getId();
		            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		            
		            return "redirect:/login";
		        }catch (UserAlreadyExistException e) {
		          model.addAttribute("message", "An account already exists for this email");
		        return "signup_form";
		        }
		}


		}


