package id.vn.ptsport.PTSport.Controller;

import id.vn.ptsport.PTSport.DTO.Request.UserRegistrationRequest;
import id.vn.ptsport.PTSport.DTO.Response.UserRegistrationResponse;
import id.vn.ptsport.PTSport.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserRegistrationResponse registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }
}

