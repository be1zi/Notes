package notes.Service;

import notes.Helper.Enum.LoginEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.User;

public interface IUserService {

    ServiceResult<User, LoginEnum> checkUser(User user);
}
