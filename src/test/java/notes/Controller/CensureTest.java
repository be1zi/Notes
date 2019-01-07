package notes.Controller;

import notes.Model.Censure;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CensureTest {

    @Autowired
    private CensureController censureController;

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
    public void getListOfCensureForExistUser() throws Exception {

        login();

        mockMvc = standaloneSetup(censureController).build();

        MvcResult mvcResult = mockMvc.perform(get("/censure/").
                contentType(MediaType.APPLICATION_JSON).
                session(session)).
                andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        assert session.isInvalid() == false;
        assert modelAndView.getViewName().equals("censure");
        assert modelAndView.getModel().get("censure") != null;
        assert modelAndView.getModel().get("censures") != null;

        List<Censure> list = (List)modelAndView.getModel().get("censures");

        assert list.size() > 0;
    }

    public MvcResult login() throws Exception {

        User user = new User();
        user.setId(Long.parseLong("1"));
        user.setLogin("test");
        user.setPassword("test");

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
