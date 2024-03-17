package com.SpringBoot.GharBhada.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SpringBoot.GharBhada.Entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
