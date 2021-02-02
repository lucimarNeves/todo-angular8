package io.github.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.curso.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
