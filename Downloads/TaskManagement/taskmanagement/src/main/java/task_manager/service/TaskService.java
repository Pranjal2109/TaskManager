package task_manager.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task_manager.model.Task;
import task_manager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create a new Task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Update an existing task
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCompleted(taskDetails.isCompleted());
            taskRepository.save(existingTask); // Save the updated task
            return Optional.of(existingTask);
        }
        return Optional.empty();
    }

    // Delete a task by ID
    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

