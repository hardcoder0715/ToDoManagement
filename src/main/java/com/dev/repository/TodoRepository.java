package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
