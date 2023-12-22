package com.pariksha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pariksha.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
