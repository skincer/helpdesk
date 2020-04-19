package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    private final TestEntityManager entityManager;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(TestEntityManager entityManager, CategoryRepository categoryRepository) {
        this.entityManager = entityManager;
        this.categoryRepository = categoryRepository;
    }

    @Test
    public void whenFindFirst10ByNameIgnoreCase_thenReturnCategory() {

        String name = "TestCategory";

        CategoryEntity category = new CategoryEntity(name);
        entityManager.persist(category);
        entityManager.flush();

        List<CategoryEntity> result = categoryRepository.findFirst10ByNameContainingIgnoreCaseOrderByIdDesc("testCategory");

        assertThat(result.size()).isGreaterThan(0);

        assertThat(result.get(0).getName()).isEqualToIgnoringCase(name);

    }

}


