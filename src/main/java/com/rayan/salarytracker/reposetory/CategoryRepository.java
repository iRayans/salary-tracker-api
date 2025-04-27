package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

    public List<Category> findCategories(Long userId){
        return find("user.id", userId).list();
    }
    public Optional<Category> findUserCategory(Long categoryId, Long userId){
        return find("id = ?1 AND user.id = ?2", categoryId,userId).firstResultOptional();
    }
}
