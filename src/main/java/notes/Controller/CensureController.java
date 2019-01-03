package notes.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.LoginEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Censure;
import notes.Model.User;
import notes.Service.ICensureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/censure")
public class CensureController {

    @Autowired
    @Qualifier(value = "censureService")
    ICensureService iCensureService;

    @RequestMapping(value = "/")
    public ModelAndView mainView(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        User user = (User)session.getAttribute("user");

        ServiceResult<List<Censure>, OperationEnum> serviceResult = iCensureService.getCensuresForUser(user);

        ModelAndView modelAndView = new ModelAndView("censure");
        modelAndView.addObject("censure", new Censure());
        modelAndView.addObject("censures", serviceResult.getData());

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("censure") Censure censure, HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        User user = (User)session.getAttribute("user");

        ServiceResult<Censure, AddEnum> serviceResult = iCensureService.addCensure(censure, user);
        ServiceResult<List<Censure>, OperationEnum> listServiceResult = iCensureService.getCensuresForUser(user);

        session.setAttribute("alert", serviceResult.getEnumValue());

        ModelAndView modelAndView = new ModelAndView("censure");
        modelAndView.addObject("censure", censure);
        modelAndView.addObject("censures", listServiceResult.getData());

        return modelAndView;

    }

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public ModelAndView back(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        return new ModelAndView("dashboard");
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
