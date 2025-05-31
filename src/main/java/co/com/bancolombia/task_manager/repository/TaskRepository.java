package co.com.bancolombia.task_manager.repository;

import co.com.bancolombia.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {}
