package notes.DAO;

import notes.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPasswordAndDeletedFalse(String login, String password);
    User findByLoginAndDeletedFalse(String login);
}
