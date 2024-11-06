package task_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import task_manager.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

     // Custom query to find tasks by completion status
     List<Task> findByCompleted(boolean completed);

     // Custom query to find tasks by title (case-insensitive partial match)
     List<Task> findByTitleContainingIgnoreCase(String title);
 
     // Custom query to find tasks by description (case-insensitive partial match)
     List<Task> findByDescriptionContainingIgnoreCase(String description);
 
     // Custom query to find a task by its title (assuming titles are unique)
     Optional<Task> findByTitle(String title);
    
}