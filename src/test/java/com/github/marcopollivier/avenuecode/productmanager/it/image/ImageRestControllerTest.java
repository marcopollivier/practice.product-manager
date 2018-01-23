package com.github.marcopollivier.avenuecode.productmanager.it.image;

import com.github.marcopollivier.avenuecode.productmanager.app.controller.rest.ProductRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRestControllerTest {

    private MockMvc mockProductMvc;

    private MockMvc mockImageMvc;

    @Autowired
    private ProductRestController.ImageRest imageRest;

    @Autowired
    private ProductRestController.ProductRest productRest;

    @Before
    public void setUp() throws Exception {
        mockImageMvc = MockMvcBuilders.standaloneSetup(this.imageRest).build();

        mockProductMvc = MockMvcBuilders.standaloneSetup(this.productRest).build();

        String productJson = "{\"name\": \"Nome do produto\",\t\"description\": \"Detalhes do produto\", \"images\": [{\"type\": \"Imagem principal\"}, {\"type\": \"Imagem secundaria\"}]}";
        mockProductMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void testProductImages() throws Exception {
        mockImageMvc.perform(get("/image/product/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].type", containsInAnyOrder("Imagem principal", "Imagem secundaria")))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)));
    }

    @Test
    public void testPut() throws Exception {
        mockImageMvc.perform(put("/image/{imageId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\": \"Imagem alterada\"}"))
                .andExpect(status().isOk());

        mockImageMvc.perform(put("/image/{imageId}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\": \"Imagem secundaria outra\"}"))
                .andExpect(status().isOk());

        mockImageMvc.perform(get("/image/product/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].type", containsInAnyOrder("Imagem alterada", "Imagem secundaria outra")));
    }

    @Test
    public void testDelete() throws Exception {
        mockImageMvc.perform(delete("/image/{imageId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        mockImageMvc.perform(get("/image/product/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(2)));
    }


}
