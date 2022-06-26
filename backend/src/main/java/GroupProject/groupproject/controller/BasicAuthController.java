package GroupProject.groupproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GroupProject.groupproject.AuthenticationBean;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("basicauth")
public class BasicAuthController {

    @GetMapping()
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}