package co.com.bancolombia.task_manager.service;

import co.com.bancolombia.task_manager.dto.TaskRequestDTO;
import co.com.bancolombia.task_manager.dto.TaskResponseDTO;
import co.com.bancolombia.task_manager.model.Task;
import co.com.bancolombia.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponseDTO saveTask(TaskRequestDTO requestDTO) {

        var task = Task.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .isCompleted(false)
                .build();

        var taskSaved = taskRepository.save(task);

        return TaskResponseDTO.builder()
                .id(taskSaved.getId())
                .name(taskSaved.getName())
                .description(taskSaved.getDescription())
                .build();
    }
}
