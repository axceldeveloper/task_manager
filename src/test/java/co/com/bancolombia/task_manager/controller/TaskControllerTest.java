package co.com.bancolombia.task_manager.controller;

import co.com.bancolombia.task_manager.dto.TaskRequestDTO;
import co.com.bancolombia.task_manager.dto.TaskResponseDTO;
import co.com.bancolombia.task_manager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@Import(TaskControllerTest.MockConfig.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public TaskService taskService() {
            return Mockito.mock(TaskService.class);
        }
    }

    @Test
    void createTask_shouldReturnCreatedTask() throws Exception {
        TaskRequestDTO request = TaskRequestDTO.builder()
                .name("Tarea de prueba")
                .description("Descripción de prueba")
                .build();

        TaskResponseDTO response = TaskResponseDTO.builder()
                .id(1L)
                .name("Tarea de prueba")
                .description("Descripción de prueba")
                .build();

        Mockito.when(taskService.saveTask(any(TaskRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Tarea de prueba"))
                .andExpect(jsonPath("$.description").value("Descripción de prueba"));
    }
}