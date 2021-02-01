package io.fulchr3356.SpringLogin.controllers;

import io.fulchr3356.SpringLogin.entities.User;
import io.fulchr3356.SpringLogin.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    public UserController (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("/login", "user", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("user")User user,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        log.info("Attempting to register user: " + user.toString());
        userRepository.save(user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("id", user.getId());
        return "login";
    }
    @PostMapping("/login")
    public String login( @Valid @ModelAttribute("user")User user,
                        BindingResult result, ModelMap model){
        log.info("Attempting to log in user: " + user.toString());
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());
        if(findUser.isPresent())
            if (findUser.get().getPassword().equals(user.getPassword()))
                return "welcome";
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("/register", "user", new User());
    }

}
