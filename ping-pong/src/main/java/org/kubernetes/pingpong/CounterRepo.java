package org.kubernetes.pingpong;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepo extends JpaRepository<Counter, Integer> {
}
