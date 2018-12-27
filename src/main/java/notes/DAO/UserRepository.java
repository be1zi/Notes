package notes.DAO;

import notes.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
    User findByLogin(String login);
}
