package org.kubernetes.pingpong;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor@NoArgsConstructor@Getter@Setter
@Builder
public class Counter {
    @Id
    private int id;
    private int value;
}
