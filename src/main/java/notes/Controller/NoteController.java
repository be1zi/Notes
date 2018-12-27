package notes.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/note")
public class NoteController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView notesList(HttpSession session) {

        if (!validSession(session)) {
            return new ModelAndView("homme");
        }

        return new ModelAndView("notes");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNote(HttpSession session) {

        if (!validSession(session)) {
            return new ModelAndView("homme");
        }

        return new ModelAndView("addNote");
    }

    private boolean validSession(HttpSession session) {

        if (session == null ||
                session.getAttribute("user") == null ||
                session.getAttribute("user").toString().isEmpty()) {

            return false;
        }

        return true;
    }
}
