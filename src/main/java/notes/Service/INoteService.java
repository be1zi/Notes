package notes.Service;

import notes.Helper.Enum.AddNoteEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;

import java.util.List;

public interface INoteService {

    ServiceResult<Note, AddNoteEnum> addNote(Note note, User user);
    ServiceResult<User, OperationEnum> deleteNote(Long noteId, User user);
}
