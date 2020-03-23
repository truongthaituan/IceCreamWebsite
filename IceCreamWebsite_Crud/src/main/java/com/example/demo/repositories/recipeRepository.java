package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.models.recipe;
@Repository
public interface recipeRepository extends JpaRepository<recipe, Long> {
}
