package com.koszalka.crud.unit;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.koszalka.crud.bo.CityBO;
import com.koszalka.crud.persistence.dto.CityDTO;
import com.koszalka.crud.persistence.entities.CityEntity;
import com.koszalka.crud.rest.controllers.CityController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CityBO cityBo;

    @InjectMocks
    private CityController cityController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    private void shortenerControllerGetGone() throws Exception {
        Mockito
            .when(cityBo.getCityByName("Blumenau"))
            .thenReturn(new CityEntity());

        mockMvc
            .perform(MockMvcRequestBuilders.get("/v1/city/city-name/Blumenau"))
            .andExpect(status().isOk());
    }

    @Test
    private void cityControllerPost() throws Exception {

        CityDTO data = new CityDTO();
        data.setState("Santa Catarina");
        data.setName("Blumenau");

        this.mockMvc
            .perform(post("/v1/city/new")
             .contentType(MediaType.APPLICATION_JSON).content(data.toString()))
            .andExpect(status().isCreated());
    }
}
