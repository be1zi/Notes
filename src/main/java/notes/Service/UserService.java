package notes.Service;

import notes.DAO.UserRepository;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.LoginEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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

        User user = userRepository.findByLoginAndPasswordAndDeletedFalse(u.getLogin(), u.getPassword());
        serviceResult.setData(user);

        if (user != null) {
            serviceResult.setEnumValue(LoginEnum.Login);
        } else {
            user = userRepository.findByLoginAndDeletedFalse(u.getLogin());

            if (user != null) {
                serviceResult.setEnumValue(LoginEnum.WrongPassword);
            } else {
                serviceResult.setEnumValue(LoginEnum.LoginNotFound);
            }
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<User, AddEnum> registerUser(User user) {

        ServiceResult<User, AddEnum> serviceResult = new ServiceResult<>();

        if (user == null ||
                user.getLogin() == null ||
                user.getLogin().isEmpty() ||
                user.getPassword() == null ||
                user.getPassword().isEmpty()) {

            serviceResult.setEnumValue(AddEnum.EmptyField);
            return serviceResult;
        }

        User existUser = userRepository.findByLoginAndDeletedFalse(user.getLogin());

        if (existUser != null) {
            serviceResult.setEnumValue(AddEnum.Exist);
            return serviceResult;
        }

        try {
            userRepository.save(user);
            serviceResult.setEnumValue(AddEnum.Success);
        } catch (Exception e) {
            serviceResult.setEnumValue(AddEnum.Failure);
        }

        return serviceResult;
    }
}
