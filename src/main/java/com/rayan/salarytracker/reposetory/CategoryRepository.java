package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

    public List<Category> findCategories(Long userId){
        return find("user.id", userId).list();
    }
    public Category findUserCategory(Long categoryId, Long userId){
        return find("id = ?1 and user.id =?2", categoryId, userId).firstResult();
    }
}
