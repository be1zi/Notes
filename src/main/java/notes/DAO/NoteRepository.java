package notes.DAO;

import notes.Model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {

    Note findByTitleAndUserId(String title, Long userId);
}
