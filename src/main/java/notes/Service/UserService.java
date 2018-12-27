package notes.Service;

import notes.DAO.UserRepository;
import notes.Helper.Enum.LoginEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service("userService")
@EnableTransactionManagement
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    public ServiceResult<User, LoginEnum> checkUser(User u) {

        ServiceResult<User, LoginEnum> serviceResult = new ServiceResult<>();

        if (u.getLogin() == null || u.getLogin().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.LoginNotFound);
            return serviceResult;
        }

        User user = userRepository.findByLoginAndPassword(u.getLogin(), u.getPassword());
        serviceResult.setData(user);

        if (user != null) {
            serviceResult.setEnumValue(LoginEnum.Login);
        } else {
            user = userRepository.findByLogin(u.getLogin());

            if (user != null) {
                serviceResult.setEnumValue(LoginEnum.WrongPassword);
            } else {
                serviceResult.setEnumValue(LoginEnum.LoginNotFound);
            }
        }

        return serviceResult;
    }
}
