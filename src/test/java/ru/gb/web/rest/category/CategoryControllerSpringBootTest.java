package ru.gb.web.rest.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.web.dto.CategoryDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerSpringBootTest {

    final private static String BASE_URL = "/api/v1/category";
    final private static Long FIND_CATEGORY_ID = 2L;

    final private static String CATEGORY_TITLE_LAPTOP = "Laptop";
    final private static String CATEGORY_TITLE_CAR = "Car";
    final private static String CATEGORY_TITLE_BIKE = "Bike";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    void saveCategoryLaptopTest() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(CategoryDto.builder()
                                .title(CATEGORY_TITLE_LAPTOP)
                                .build()
                        )
                ))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void saveCategoryCarTest() throws Exception {
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(CategoryDto.builder()
                                        .title(CATEGORY_TITLE_CAR)
                                        .build()
                                )
                        ))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    void findAllTest() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value(CATEGORY_TITLE_LAPTOP))
                .andExpect(jsonPath("$.[1].title").value(CATEGORY_TITLE_CAR))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    @Order(4)
    void findByIdTest() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + FIND_CATEGORY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(CATEGORY_TITLE_CAR));
    }

    @Test
    @Order(5)
    void updateCategoryByIdTest () throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + FIND_CATEGORY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(CategoryDto.builder()
                                        .title(CATEGORY_TITLE_BIKE)
                                        .build()
                                )
                        ))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(6)
    void findByIdAfterUpdateTest() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + FIND_CATEGORY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(CATEGORY_TITLE_BIKE));
    }

    @Test
    @Order(7)
    void deleteCategoryByIdTest() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + FIND_CATEGORY_ID))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(8)
    void findAllAfterDeleteCategoryTest() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }
}
