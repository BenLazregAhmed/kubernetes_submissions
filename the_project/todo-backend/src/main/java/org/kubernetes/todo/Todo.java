package org.kubernetes.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor@NoArgsConstructor@Getter@Setter
@Builder
public class Todo {
    @Id@GeneratedValue
    private int id;
    private String todo;
}
