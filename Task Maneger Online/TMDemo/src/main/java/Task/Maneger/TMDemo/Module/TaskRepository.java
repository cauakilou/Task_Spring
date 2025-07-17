package Task.Maneger.TMDemo.Module;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    public Iterable<Task> findByStatus(Task.Alfa status);
}
