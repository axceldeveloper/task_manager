package co.com.bancolombia.task_manager.controller;

import co.com.bancolombia.task_manager.dto.TaskRequestDTO;
import co.com.bancolombia.task_manager.dto.TaskResponseDTO;
import co.com.bancolombia.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping()
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO request) {
        var task = taskService.saveTask(request);
        return ResponseEntity
                .status(201)
                .body(task);
    }
}
