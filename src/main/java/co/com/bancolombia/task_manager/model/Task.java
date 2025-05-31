package co.com.bancolombia.task_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(name = "completed")
    private Boolean isCompleted;
}
