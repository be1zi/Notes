package notes.DAO;

import notes.Model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    Note findByTitleAndUserIdAndDeletedFalse(String title, Long userId);
    List<Note> findByUserIdAndDeletedFalse(Long userId);
}
