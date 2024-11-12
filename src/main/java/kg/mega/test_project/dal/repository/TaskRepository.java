package kg.mega.test_project.dal.repository;
import kg.mega.test_project.dal.entity.Task;
import kg.mega.test_project.dto.response.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("select new kg.mega.test_project.dto.response.TaskResponse(t.id,t.description,t.done) from Task t")
    List<TaskResponse> getAllTasks();

    @Query("select new kg.mega.test_project.dto.response.TaskResponse(t.id,t.description,t.done) from Task t where t.id = :id")
    Optional<TaskResponse> getTaskById(Long id);
}
