package id.vn.ptsport.PTSport.AdminRegistration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AdminRegistrationController {
    private final AdminService adminService;

    public AdminRegistrationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/registration")
    public AdminRegistrationResponse registerAdmin(@RequestBody AdminRegistrationRequest adminRegistrationRequest){
        return  adminService.registerAdmin(adminRegistrationRequest);
    }
}
