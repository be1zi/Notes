package notes.Controller;

import notes.Helper.Enum.LoginEnum;
import notes.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/censure")
public class CensureController {

    @RequestMapping(value = "/")
    public ModelAndView mainView(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        ModelAndView modelAndView = new ModelAndView("censure");

        return modelAndView;
    }

    private boolean validSession(HttpSession session) {

        if (session == null ||
                session.getAttribute("user") == null ||
                session.getAttribute("user").toString().isEmpty() ||
                session.getAttribute("userStatus") != LoginEnum.Login) {

            return false;
        }

        return true;
    }

    private ModelAndView reautenticate() {
        ModelAndView modelAndView = new ModelAndView("homme");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
}
