package notes.Service;

import notes.Helper.Enum.AddEnum;
import notes.Helper.Enum.EditEnum;
import notes.Helper.Enum.OperationEnum;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;
import notes.Model.User;

import java.util.List;

public interface INoteService {

    ServiceResult<Note, AddEnum> addNote(Note note, User user);
    ServiceResult<User, OperationEnum> deleteNote(Long noteId, User user);
    ServiceResult<List<Note>, OperationEnum> getNotesForUser(User user);
    ServiceResult<Note, EditEnum> editNote(Note note, User user);
}
