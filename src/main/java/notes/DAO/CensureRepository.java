package notes.DAO;

import notes.Model.Censure;
import org.springframework.data.repository.CrudRepository;

public interface CensureRepository extends CrudRepository<Censure, Long> {

    Censure findByTextAndUserIdAndDeletedFalse(String text, Long userId);
}
