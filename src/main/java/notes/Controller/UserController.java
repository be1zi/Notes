package notes.Controller;

import com.google.gson.Gson;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.LoginEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;
import notes.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

        ModelAndView modelAndView = new ModelAndView("homme");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public String check(@ModelAttribute("user") @Valid User user, HttpSession session) {

        ServiceResult<User, LoginEnum> serviceResult = userService.checkUser(user);

        session.setAttribute("userStatus", serviceResult.getEnumValue());
        session.setAttribute("user", serviceResult.getData());

        if (serviceResult.getEnumValue() == LoginEnum.Login) {
            return "dashboard";
        }

        return "homme";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ServiceResult<User, AddEnum> register(@RequestBody String json) {

        System.out.println("Register");

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        System.out.println(user.toString());

        ServiceResult<User, AddEnum> serviceResult = userService.registerUser(user);

        return serviceResult;
    }
}
