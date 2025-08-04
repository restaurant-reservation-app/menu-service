package com.restaurantapp.menuservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.restaurantapp.menuservice.model.dto.DishDto;
import com.restaurantapp.menuservice.serivce.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {
    @MockitoBean
    MenuService menuService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addDish_CorrectData_DtoReturned() throws Exception {
        DishDto dishDto = getDish("name");
        String dishJson = getObjectAsString(dishDto);
        when(menuService.addDish(dishDto)).thenReturn(dishDto);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/menu/dish/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dishJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals(dishJson, result.getResponse().getContentAsString());
    }

    @Test
    void getMenu_CorrectData_WithoutParams_DtoListReturned() throws Exception {
        List<DishDto> dishList = List.of(
                getDish("name1"),
                getDish("name2")
        );

        String dishJson = getObjectAsString(dishList);
        when(menuService.getMenu(null, null)).thenReturn(dishList);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/menu"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(dishJson, result.getResponse().getContentAsString());
    }

    @Test
    void getMenu_CorrectData_WithParams_DtoListReturned() throws Exception {
        List<DishDto> dishList = List.of(
                getDish("name1"),
                getDish("name2")
        );

        String dishJson = getObjectAsString(dishList);
        when(menuService.getMenu("category1", "name")).thenReturn(dishList);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/menu")
                        .param("category", "category1")
                        .param("dishName","name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(dishJson, result.getResponse().getContentAsString());
    }

    @Test
    void updateDish_CorrectData_DtoReturned() throws Exception {
        String name = "name";
        DishDto dishDto = getDish(name);
        String dishJson = getObjectAsString(dishDto);
        when(menuService.updateDish(dishDto)).thenReturn(dishDto);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.patch("/menu/dish/update/{dishName}", name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dishJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(dishJson, result.getResponse().getContentAsString());
    }

    @Test
    void deleteDish_CorrectData_NoContentReturned() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/menu/dish/delete/{dishName}", "name"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    DishDto getDish(String name) {
        return DishDto.builder()
                .name(name)
                .description("description")
                .category("category")
                .price(new BigDecimal("10.5"))
                .build();
    }

    String getObjectAsString(Object object) {
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String expectedResult;
        try {
            expectedResult = removeEmptyLines(ow.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return expectedResult;
    }

    String removeEmptyLines(String string) {
        return string.replaceAll("\r\n", "")
                .replaceAll(" ", "");
    }
}
