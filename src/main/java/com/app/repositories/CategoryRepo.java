package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByName(String name);
	
	@Query("Select c from Category c Where c.name=:name And c.parentCategory.name=:parentCategoryName")
	Category findByNameAndParent(@Param("parentCategoryName") String parentCategoryName,@Param("name") String name);
	
	
}
