package notes.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import notes.Helper.Censure.CensureMaker;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.EditEnum;
import notes.Helper.Enum.LoginEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Censure;
import notes.Model.Note;
import notes.Model.User;
import notes.Service.ICensureService;
import notes.Service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/note")
public class NoteController {

    @Autowired
    @Qualifier(value = "noteService")
    INoteService iNoteService;

    @Autowired
    @Qualifier(value = "censureService")
    ICensureService iCensureService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView notesList(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        User user = (User)session.getAttribute("user");
        ServiceResult<List<Note>, OperationEnum> serviceResult = iNoteService.getNotesForUser(user);

        ModelAndView modelAndView = new ModelAndView("notes");
        modelAndView.addObject("notes", serviceResult.getData());
        modelAndView.addObject("noteAlert", serviceResult.getEnumValue());

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNote(HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        ModelAndView modelAndView = new ModelAndView("addNote");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("noteAlert", AddEnum.Default);

        return modelAndView;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("note") Note note, HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        User user = (User)session.getAttribute("user");

        ServiceResult<Note, AddEnum> serviceResult = iNoteService.addNote(note, user);
        session.setAttribute("noteAlert", serviceResult.getEnumValue());

        ModelAndView modelAndView = new ModelAndView("addNote");
        modelAndView.addObject("note", new Note());

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id", required = true) Long id, HttpSession session) {

        if (!validSession(session)) {
            return reautenticate();
        }

        User user = (User)session.getAttribute("user");
        ServiceResult<User, OperationEnum> serviceResult = iNoteService.deleteNote(id, user);
        ServiceResult<List<Note>, OperationEnum> listSR = iNoteService.getNotesForUser(user);

        session.setAttribute("user", serviceResult.getData());

        ModelAndView modelAndView = new ModelAndView("notes");
        modelAndView.addObject("notes", listSR.getData());
        modelAndView.addObject("noteAlert", serviceResult.getEnumValue());

        return modelAndView;

    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public @ResponseBody Note getItem(@RequestParam(value = "id") Long id, HttpSession session) {

        if (!validSession(session)) {
            return null;
        }

        User user = (User)session.getAttribute("user");
        ServiceResult<List<Note>, OperationEnum> listSR = iNoteService.getNotesForUser(user);
        ServiceResult<List<Censure>, OperationEnum> listCensures = iCensureService.getCensuresForUser(user);

        for (Note n : listSR.getData()) {
            if (n.isDeleted() == false && n.getId() == id) {
                n.setUser(null);
                n.setDesc(CensureMaker.createCensuredText(n.getDesc(), listCensures.getData()));
                return n;
            }
        }

        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ServiceResult<Note, EditEnum> editItem(@RequestBody String json, HttpSession session) {

        Gson gson = new Gson();
        Note note = gson.fromJson(json, Note.class);

        System.out.println(json);

        ServiceResult<Note, EditEnum> serviceResult = new ServiceResult<>();

        if (!validSession(session)) {
            serviceResult.setEnumValue(EditEnum.Invalid);
            return serviceResult;
        }

        User user = (User) session.getAttribute("user");
        serviceResult = iNoteService.editNote(note, user);

        return serviceResult;
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
