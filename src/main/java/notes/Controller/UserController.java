package notes.Controller;

import notes.Helper.Enum.LoginEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.User;
import notes.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    IUserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) {


        HttpSession session = request.getSession(true);
        request.getSession().setAttribute("userStatus", LoginEnum.Logout);

        ModelAndView modelAndView = new ModelAndView("home");

        User user = new User();
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@ModelAttribute("user") @Valid User user, HttpSession session) {

        ServiceResult<User, LoginEnum> serviceResult = userService.checkUser(user);

        session.setAttribute("userStatus", serviceResult.getEnumValue());
        session.setAttribute("user", serviceResult.getData());

        return "home";
    }
}
