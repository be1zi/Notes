package notes.Controller;

import notes.Helper.Enum.LoginEnum;
import notes.Model.Note;
import notes.Model.User;
import notes.Service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/note")
public class NoteController {

    @Autowired
    @Qualifier(value = "noteService")
    INoteService iNoteService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView notesList(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        return new ModelAndView("notes");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNote(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        ModelAndView modelAndView = new ModelAndView("addNote");
        modelAndView.addObject("note", new Note());

        return modelAndView;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("note") Note note, HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }


        ModelAndView modelAndView = new ModelAndView("homme");
        modelAndView.addObject("user", new User());
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
