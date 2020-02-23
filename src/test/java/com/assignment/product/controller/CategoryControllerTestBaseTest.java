package com.assignment.product.controller;

import com.assignment.product.ControllerBaseTest;
import com.assignment.product.domain.Category;
import com.assignment.product.service.CategoryService;
import com.assignment.product.testdata.CategoryTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles({"test"})
@WebMvcTest(CategoryController.class)
public class CategoryControllerTestBaseTest extends ControllerBaseTest {

    @MockBean
    private CategoryService categoryService;

    @BeforeClass
    public static void init() {
        baseAddress = "/categories";
    }

    @Test
    public void testCreateCategory_SendValidRequest_ReturnCreated() throws Exception {
        Category category = CategoryTestData.getCategory();
        when(categoryService.create(any(Category.class))).thenReturn(category);

        ResultActions perform = mockMvc.perform(post(baseAddress)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)));


        perform.andExpect(status().isCreated());
    }

    @Test
    public void testGetCategory_SendId_GetACategory() throws Exception {
        Category category = CategoryTestData.getCategory();
        when(categoryService.getById(category.getId())).thenReturn(category);

        ResultActions resultActions = mockMvc.perform(get(baseAddress + "/" + category.getId())
                .contentType(mediaType));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(category.getId())));

        verify(categoryService).getById(anyString());
    }


}
