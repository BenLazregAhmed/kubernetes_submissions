package org.kubernetes.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepo extends JpaRepository<Todo,Integer> {

}
