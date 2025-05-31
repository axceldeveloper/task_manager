package co.com.bancolombia.task_manager.service;

import co.com.bancolombia.task_manager.dto.TaskRequestDTO;
import co.com.bancolombia.task_manager.dto.TaskResponseDTO;
import co.com.bancolombia.task_manager.model.Task;
import co.com.bancolombia.task_manager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void saveTask_shouldSaveAndReturnTaskResponseDTO() {

        TaskRequestDTO request = TaskRequestDTO.builder()
                .name("Tarea test")
                .description("Descripción test")
                .build();

        Task savedTask = Task.builder()
                .id(1L)
                .name("Tarea test")
                .description("Descripción test")
                .isCompleted(false)
                .build();

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponseDTO response = taskService.saveTask(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Tarea test", response.getName());
        assertEquals("Descripción test", response.getDescription());

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(captor.capture());
        Task taskToSave = captor.getValue();
        assertEquals("Tarea test", taskToSave.getName());
        assertEquals("Descripción test", taskToSave.getDescription());
        assertFalse(taskToSave.getIsCompleted());
    }
}