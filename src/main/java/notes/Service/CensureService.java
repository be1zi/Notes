package notes.Service;

import notes.DAO.CensureRepository;
import notes.DAO.UserRepository;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Censure;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("censureService")
@EnableTransactionManagement
public class CensureService implements ICensureService {

    @Autowired
    CensureRepository censureRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public ServiceResult<Censure, AddEnum> addCensure(Censure censure, User user) {

        ServiceResult<Censure, AddEnum> serviceResult = new ServiceResult<>();

        if (censure.getText() == null ||
                censure.getText().isEmpty() ||
                censure.getPattern() == null ||
                censure.getPattern().isEmpty()) {
            serviceResult.setEnumValue(AddEnum.EmptyField);
            return serviceResult;
        }

        Censure dbCensure = censureRepository.findByTextAndUserIdAndDeletedFalse(censure.getText(), user.getId());

        if (dbCensure != null) {
            serviceResult.setEnumValue(AddEnum.Exist);
            return serviceResult;
        }

        try {
            censure.setUser(user);
            censureRepository.save(censure);

            serviceResult.setEnumValue(AddEnum.Success);
            serviceResult.setData(censure);

        } catch (Exception e) {
            serviceResult.setEnumValue(AddEnum.Failure);
            e.printStackTrace();
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<List<Censure>, OperationEnum> getCensuresForUser(User user) {

        ServiceResult<List<Censure>, OperationEnum> serviceResult = new ServiceResult<>();

        if (user == null || !(user.getId() > 0)) {
            serviceResult.setEnumValue(OperationEnum.Failure);
            return serviceResult;
        }

        try {
            List<Censure> list = censureRepository.findAllByUserIdAndDeletedFalse(user.getId());
            serviceResult.setEnumValue(OperationEnum.Success);
            serviceResult.setData(list);
        } catch (Exception e) {
            serviceResult.setEnumValue(OperationEnum.Failure);
        }

        return serviceResult;
    }
}
