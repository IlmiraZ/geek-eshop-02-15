package ru.ilmira.controller;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ilmira.persist.model.Brand;
import ru.ilmira.persist.model.Category;
import ru.ilmira.persist.model.Product;
import ru.ilmira.persist.repository.BrandRepository;
import ru.ilmira.persist.repository.CategoryRepository;
import ru.ilmira.persist.repository.ProductRepository;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private SimpMessagingTemplate template;

    @Test
    public void testProductDetails() throws Exception {
        Category category = new Category();
        category.setName("Category");
        category = categoryRepository.save(category);

        Brand brand = new Brand();
        brand.setName("Brand");
        brand = brandRepository.save(brand);

        Product product = new Product();
        product.setName("Product");
        product.setDescription("Description");
        product.setPrice(new BigDecimal(1234));
        product.setCategory(category);
        product.setBrand(brand);

        product = productRepository.save(product);

        mvc.perform(MockMvcRequestBuilders
                        .get("/v1/product/all")
                        .param("name", category.getId().toString())
                        .param("name", brand.getId().toString())
                        .param("page", "1")
                        .param("size", "5")
                        .param("sortField", "id")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                // https://github.com/json-path/JsonPath
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is(product.getName())));
    }
}
