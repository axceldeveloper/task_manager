package co.com.bancolombia.task_manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TaskResponseDTO {
    private Long id;
    private String name;
    private String description;
}
