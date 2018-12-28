package notes.Service;

import notes.DAO.NoteRepository;
import notes.DAO.UserRepository;
import notes.Helper.Enum.AddNoteEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("noteService")
@EnableTransactionManagement
public class NoteService implements INoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public ServiceResult<Note, AddNoteEnum> addNote(Note note, User user) {

        ServiceResult<Note, AddNoteEnum> serviceResult = new ServiceResult();

        if (note.getTitle() == null || note.getDesc() == null) {
            serviceResult.setEnumValue(AddNoteEnum.EmptyTitle);
            return serviceResult;
        }

        Note dbNote = noteRepository.findByTitleAndUserId(note.getTitle(), user.getId());

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
}
