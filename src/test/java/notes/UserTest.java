package notes;

import notes.Controller.UserController;
import notes.Helper.Enum.LoginEnum;
import notes.Model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session;

    @Before
    public void setValues() {
        this.session = new MockHttpSession();
    }

    @Test
    public void loginWithCorrectData() throws Exception {

        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setLogin("test");
        user.setPassword("test");

        MvcResult result = tryLogin(user);
        String content =  result.getResponse().getForwardedUrl();

        assert content.equals("dashboard");
        assert session.isInvalid() == false;
        assert session.getAttribute("userStatus") == LoginEnum.Login;

        if (!session.isInvalid()) {
            assert session.getAttribute("user") != null;

            User user1 = (User)session.getAttribute("user");

            assert user1.getLogin().equals(user.getLogin());
            assert user1.getPassword().equals(user.getPassword());
        }
    }

    @Test
    public void loginWithIncorrectLogin() throws Exception {

        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setLogin("Badtest");
        user.setPassword("test");

        MvcResult result = tryLogin(user);
        String content =  result.getResponse().getForwardedUrl();

        assert content.equals("homme");
        assert session.getAttribute("userStatus") == LoginEnum.LoginNotFound;
    }

    @Test
    public void loginWithIncorrectPassword() throws Exception {

        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setLogin("test");
        user.setPassword("Badtest");

        MvcResult result = tryLogin(user);
        String content = result.getResponse().getForwardedUrl();

        assert content.equals("homme");
        assert session.getAttribute("userStatus") == LoginEnum.WrongPassword;
    }

    @Test
    public void logout() throws Exception {

        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setLogin("test");
        user.setPassword("test");

        tryLogin(user);
        mockMvc.perform(get("/user/logout").session(session)).andReturn();

        assert session.isInvalid();
    }

    public MvcResult tryLogin(User user) throws Exception {

        if(session == null) {
            session = new MockHttpSession();
        }

        mockMvc = standaloneSetup(userController).build();

        return mockMvc.perform(post("/user/check").
                contentType(MediaType.APPLICATION_JSON).
                flashAttr("user", user).
                session(session)).
                andReturn();

    }
}
