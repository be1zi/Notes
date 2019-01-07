package notes.DAO;

import notes.Model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    UserRepository userRepository;

    @Test
    public void addNewNotExistNote() {

        User user = new User();
        user.setLogin("test12345");
        user.setPassword("test12345");

        userRepository.save(user);

        Assert.assertNotNull(userRepository.findByLoginAndDeletedFalse("test12345"));

        userRepository.delete(user);
    }
}
