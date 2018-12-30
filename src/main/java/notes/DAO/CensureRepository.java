package notes.DAO;

import notes.Model.Censure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CensureRepository extends CrudRepository<Censure, Long> {

    Censure findByTextAndUserIdAndDeletedFalse(String text, Long userId);
    List<Censure> findAllByUserIdAndDeletedFalse(Long userId);
}
