package id.vn.ptsport.PTSport.Service;

import id.vn.ptsport.PTSport.DTO.ChangePasswordDTO;
import id.vn.ptsport.PTSport.Entity.User;
import id.vn.ptsport.PTSport.Exception.InternalServerErrorException;
import id.vn.ptsport.PTSport.Exception.InvalidOldPasswordException;
import id.vn.ptsport.PTSport.Repository.ChangePassRepository;
import id.vn.ptsport.PTSport.Utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ChangePassService {


    @Autowired
    private ChangePassRepository changePassRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Validation validation;
    @Autowired
    private MessageSource messageSource;

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
//		Lấy username từ JWT sau khi đã giải token vá set vào SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		Lấy user từ username từ db
        User user = this.changePassRepository.findByUsername(username).orElseThrow(
                () -> new InternalServerErrorException(this.messageSource.getMessage("error.userAuthen", null, null)));
        String newPassword = changePasswordDTO.getNewPassword();
//		Kiểm tra password
        if (checkValidOldPassword(user.getPassword(), changePasswordDTO.getOldPassword())) {
            if (!this.validation.passwordValid(newPassword))
                throw new InternalServerErrorException(messageSource.getMessage("error.passwordRegex", null, null));
//			Lưu mât khẩu mới
            user.setPassword(this.passwordEncoder.encode(newPassword));
//			Lưu thông tin thay đổi xuống db theo user
            this.changePassRepository.save(user);
        } else {
            throw new InvalidOldPasswordException(this.messageSource.getMessage("error.passwordIncorrect", null, null));
        }
    }

    public boolean checkValidOldPassword(String oldPass, String confirmPass) {
        return passwordEncoder.matches(confirmPass, oldPass);
    }

}