package notes.Service;

import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Censure;
import notes.Model.User;

import java.util.List;

public interface ICensureService {

    ServiceResult<Censure, AddEnum> addCensure(Censure censure, User user);
    ServiceResult<List<Censure>, OperationEnum> getCensuresForUser(User user);
}
