package notes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home () {

        return new ModelAndView("home");
    }
}
