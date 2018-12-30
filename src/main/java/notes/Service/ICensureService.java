package notes.Service;

import notes.Helper.Enum.AddEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Censure;
import notes.Model.User;

public interface ICensureService {

    ServiceResult<Censure, AddEnum> addCensure(Censure censure, User user);
}
