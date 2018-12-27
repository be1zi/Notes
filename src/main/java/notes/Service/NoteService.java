package notes.Service;

import notes.DAO.NoteRepository;
import notes.Helper.Enum.AddNote;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service("noteService")
@EnableTransactionManagement
public class NoteService implements INoteService {

    @Autowired
    NoteRepository noteRepository;

    public ServiceResult<Note, AddNote> addNote(Note note) {

        return null;
    }
}
