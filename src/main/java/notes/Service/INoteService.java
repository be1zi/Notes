package notes.Service;

import notes.Helper.Enum.AddNote;
import notes.Helper.Service.ServiceResult;
import notes.Model.Note;

public interface INoteService {

    ServiceResult<Note, AddNote> addNote(Note note);
}
