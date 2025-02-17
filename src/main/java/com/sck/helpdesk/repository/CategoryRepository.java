package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findFirst10ByNameContainingIgnoreCaseOrderByIdDesc(String name);
}
