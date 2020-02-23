package com.assignment.product.service;

import com.assignment.product.ServiceBaseTest;
import com.assignment.product.domain.Category;
import com.assignment.product.exception.CategoryNotFoundException;
import com.assignment.product.repository.CategoryRepository;
import com.assignment.product.service.impl.CategoryServiceImpl;
import com.assignment.product.testdata.CategoryTestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceTest extends ServiceBaseTest {
    
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void testCreate_SendValidCategory_ReturnCategory() {
        Category category = CategoryTestData.getCategory();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category created = categoryService.create(category);

        verify(categoryRepository).save(any(Category.class));
        Assert.assertEquals(category.getId(), created.getId());
        Assert.assertEquals(category.getTitle(), created.getTitle());
        Assert.assertEquals(category.getParentId(), created.getParentId());
    }

    @Test
    public void testGetById_RecordNotFound_ThrowException() {
        String id = "PRODUCT-001";
        when(categoryRepository.findById(anyString())).thenReturn(Optional.empty());
        thrown.expect(CategoryNotFoundException.class);
        thrown.expectMessage(id);

        categoryService.getById(id);

        verify(categoryRepository).findById(anyString());
    }

    @Test
    public void testGetBy_SendExistingId_ReturnCategory() {
        Category category = CategoryTestData.getCategory();
        when(categoryRepository.findById(anyString())).thenReturn(Optional.of(category));

        Category found = categoryService.getById(category.getId());

        verify(categoryRepository).findById(anyString());
        Assert.assertEquals(category.getId(), found.getId());
        Assert.assertEquals(category.getTitle(), found.getTitle());
        Assert.assertEquals(category.getParentId(), found.getParentId());
    }


}
