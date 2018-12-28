package notes.Service;

import notes.DAO.NoteRepository;
import notes.DAO.UserRepository;
import notes.Helper.Enum.AddNoteEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public ServiceResult<Note, AddNoteEnum> addNote(Note note, User user) {

        ServiceResult<Note, AddNoteEnum> serviceResult = new ServiceResult();

        if (note.getTitle() == null || note.getTitle().isEmpty()) {
            serviceResult.setEnumValue(AddNoteEnum.EmptyTitle);
            return serviceResult;
        }

        Note dbNote = noteRepository.findByTitleAndUserIdAndDeletedFalse(note.getTitle(), user.getId());

        if (dbNote != null) {
            serviceResult.setEnumValue(AddNoteEnum.TitleExist);
            return serviceResult;
        }

        try {
            note.setInsertDate(new Date());
            user.addNote(note);
            note.setUser(user);

            noteRepository.save(note);
            userRepository.save(user);

            serviceResult.setData(note);
            serviceResult.setEnumValue(AddNoteEnum.Success);
        } catch (Exception e) {
            serviceResult.setEnumValue(AddNoteEnum.Failure);
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<User, OperationEnum> deleteNote(Long noteId, User user) {

        ServiceResult<User, OperationEnum> serviceResult = new ServiceResult<>();

        if (user.getNotes() == null || !(user.getNotes().size() > 0)) {
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
}
