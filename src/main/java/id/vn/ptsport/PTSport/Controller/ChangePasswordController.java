package id.vn.ptsport.PTSport.Controller;

import id.vn.ptsport.PTSport.Common.MessageResponse;
import id.vn.ptsport.PTSport.DTO.ChangePasswordDTO;
import id.vn.ptsport.PTSport.Service.ChangePassService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class ChangePasswordController {
    @Autowired
    private ChangePassService changePassService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/changepass")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO passwordChangeDTO) {
        this.changePassService.changePassword(passwordChangeDTO);
        return ResponseEntity.ok( new MessageResponse(200, "Mật khẩu đã được thay đổi", null));

    }

}
