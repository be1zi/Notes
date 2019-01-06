package notes.Service;

import notes.DAO.CensureRepository;
import notes.DAO.NoteRepository;
import notes.DAO.UserRepository;
import notes.Helper.Censure.CensureMaker;
import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.EditEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("noteService")
@EnableTransactionManagement
public class NoteService implements INoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CensureRepository censureRepository;

    @Override
    @Transactional
    public ServiceResult<Note, AddEnum> addNote(Note note, User user) {

        ServiceResult<Note, AddEnum> serviceResult = new ServiceResult();

        if (note.getTitle() == null || note.getTitle().isEmpty()) {
            serviceResult.setEnumValue(AddEnum.EmptyField);
            return serviceResult;
        }

        Note dbNote = noteRepository.findByTitleAndUserIdAndDeletedFalse(note.getTitle(), user.getId());

        if (dbNote != null) {
            serviceResult.setEnumValue(AddEnum.Exist);
            return serviceResult;
        }

        try {
            note.setInsertDate(new Date());
            note.setUser(user);

            noteRepository.save(note);

            serviceResult.setData(note);
            serviceResult.setEnumValue(AddEnum.Success);
        } catch (Exception e) {
            serviceResult.setEnumValue(AddEnum.Failure);
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<User, OperationEnum> deleteNote(Long noteId, User user) {

        ServiceResult<User, OperationEnum> serviceResult = new ServiceResult<>();

        if (!(noteId > 0) || user == null || !(user.getId() > 0) ) {
            serviceResult.setEnumValue(OperationEnum.Failure);
            return serviceResult;
        }

        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if (optionalNote == null ||
                optionalNote.map(Note::getUser).orElse(null).getId() != user.getId()) {
            serviceResult.setEnumValue(OperationEnum.Failure);
            return serviceResult;
        }

        Note note = new Note();
        note.setId(optionalNote.map(Note::getId).orElse(null));
        note.setUser(optionalNote.map(Note::getUser).orElse(null));
        note.setInsertDate(optionalNote.map(Note::getInsertDate).orElse(null));
        note.setDesc(optionalNote.map(Note::getDesc).orElse(null));
        note.setTitle(optionalNote.map(Note::getTitle).orElse(null));
        note.setDeleted(true);

        try {
            noteRepository.save(note);
            serviceResult.setEnumValue(OperationEnum.Success);
        } catch (Exception e) {
            serviceResult.setEnumValue(OperationEnum.Failure);
        }

        user = userRepository.findByLogin(user.getLogin());
        serviceResult.setData(user);

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<List<Note>,OperationEnum> getNotesForUser(User user) {

        ServiceResult<List<Note>, OperationEnum> serviceResult = new ServiceResult<>();

        if (user == null || !(user.getId() > 0)) {
            serviceResult.setEnumValue(OperationEnum.Failure);
            return serviceResult;
        }

        try {
            List<Note> result = noteRepository.findByUserIdAndDeletedFalse(user.getId());
            serviceResult.setEnumValue(OperationEnum.Success);
            serviceResult.setData(result);
        } catch (Exception e) {
            serviceResult.setEnumValue(OperationEnum.Failure);
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<Note, EditEnum> editNote(Note note, User user) {

        ServiceResult<Note, EditEnum> serviceResult = new ServiceResult<>();

        if (note == null || !(note.getId() > 0) ||
                user == null || !(user.getId() > 0) ||
                note.getTitle() == null || note.getTitle().isEmpty()) {
            serviceResult.setEnumValue(EditEnum.Failure);
            return serviceResult;
        }

        Optional<Note> n = noteRepository.findById(note.getId());

        if (n == null) {
            serviceResult.setEnumValue(EditEnum.Invalid.Failure);
            return serviceResult;
        }

        Note existed = noteRepository.findByTitleAndUserIdAndDeletedFalse(note.getTitle(), user.getId());

        if (existed != null && existed.getId() != note.getId()) {
            serviceResult.setEnumValue(EditEnum.Exist);
            return serviceResult;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(note.getInsertDateString());

            Date before = format.parse("1980-1-01");
            Date after = format.parse("2042-01-01");

            if (date.before(before) || date.after(after)) {
                serviceResult.setEnumValue(EditEnum.WrongDate);
                return serviceResult;
            }

            note.setInsertDate(date);
            note.setUser(user);
            note.setDesc(CensureMaker.deleteCensures(note.getDesc(),
                    censureRepository.findAllByUserIdAndDeletedFalse(user.getId()) ));
        } catch (ParseException e) {
            serviceResult.setEnumValue(EditEnum.Failure);
            return serviceResult;
        }

        try {
            noteRepository.save(note);
            serviceResult.setEnumValue(EditEnum.Success);
            serviceResult.setData(note);
        } catch (Exception e) {
            serviceResult.setEnumValue(EditEnum.Failure);
        }

        return serviceResult;
    }
}
