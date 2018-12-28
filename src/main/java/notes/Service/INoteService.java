package notes.Service;

import notes.Helper.Enum.AddNoteEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;

public interface INoteService {

    ServiceResult<Note, AddNoteEnum> addNote(Note note, User user);
}
