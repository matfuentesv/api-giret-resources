package com.giret.apigiretresources.controller;

import com.giret.apigiretresources.model.Recurso;
import com.giret.apigiretresources.service.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResourceController.class)
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResourceService resourceService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ResourceService resourceService() {
            return Mockito.mock(ResourceService.class);
        }
    }

    private Recurso recurso;

    @BeforeEach
    void setUp() {
        recurso = new Recurso();
        recurso.setIdRecurso(1L);
        recurso.setModelo("Modelo X");
        recurso.setDescripcion("Descripción de prueba");
        recurso.setNumeroSerie("123456");
        recurso.setFechaCompra("2024-07-12");
        recurso.setFechaVencimientoGarantia("2025-07-12");
        recurso.setEmailUsuario("test@example.com");
        recurso.setEstado("Disponible");
        recurso.setCategoria("Electrónica");
    }



    @Test
    void shouldFindAllResource() throws Exception {
        when(resourceService.findAllResource()).thenReturn(List.of(recurso));

        mockMvc.perform(get("/api/findAllResource"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idRecurso").value(1L))
                .andExpect(jsonPath("$[0].modelo").value("Modelo X"))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }



    @Test
    void shouldCreateResource() throws Exception {
        when(resourceService.saveResource(any(Recurso.class))).thenReturn(recurso);

        String json = """
            {
                "modelo": "Modelo X",
                "descripcion": "Descripción de prueba",
                "numeroSerie": "123456",
                "fechaCompra": "2024-07-12",
                "fechaVencimientoGarantia": "2025-07-12",
                "emailUsuario": "test@example.com",
                "estado": "Disponible",
                "categoria": "Electrónica"
            }
        """;

        mockMvc.perform(post("/api/saveResource")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Modelo X"))
                .andExpect(jsonPath("$.estado").value("Disponible"));
    }

    @Test
    void shouldGetResourceById() throws Exception {
        when(resourceService.findResourceById(1L)).thenReturn(recurso);

        mockMvc.perform(get("/api/findResourceById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Modelo X"))
                .andExpect(jsonPath("$.estado").value("Disponible"));
    }

    @Test
    void shouldUpdateResource() throws Exception {
        when(resourceService.updateResource(Mockito.eq(1L), any(Recurso.class))).thenReturn(recurso);

        String json = """
            {
                "modelo": "Modelo X Updated",
                "descripcion": "Actualizado",
                "numeroSerie": "654321",
                "fechaCompra": "2024-07-12",
                "fechaVencimientoGarantia": "2025-07-12",
                "emailUsuario": "test@example.com",
                "estado": "En uso",
                "categoria": "Electrónica"
            }
        """;

        mockMvc.perform(put("/api/updateResource/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteResource() throws Exception {
        mockMvc.perform(delete("/api/deleteResource/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCountResources() throws Exception {
        when(resourceService.countResources()).thenReturn(10L);

        mockMvc.perform(get("/api/resource/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    void shouldCountByEstado() throws Exception {
        List<Object[]> mockResult = List.of(
                new Object[]{"Disponible", 5L},
                new Object[]{"En uso", 3L}
        );

        when(resourceService.countByState()).thenReturn(mockResult);

        mockMvc.perform(get("/api/resource/countByEstado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0][0]").value("Disponible"))
                .andExpect(jsonPath("$[0][1]").value(5))
                .andExpect(jsonPath("$[1][0]").value("En uso"))
                .andExpect(jsonPath("$[1][1]").value(3));
    }

    @Test
    void shouldFindByEstado() throws Exception {
        when(resourceService.findByState("Disponible")).thenReturn(List.of(recurso));

        mockMvc.perform(get("/api/resource/findByEstado/Disponible"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Disponible"))
                .andExpect(jsonPath("$[0].modelo").value("Modelo X"));
    }


    @Test
    void shouldSearchResource() throws Exception {
        when(resourceService.searchResource("Modelo")).thenReturn(List.of(recurso));

        mockMvc.perform(get("/api/resource/search")
                        .param("term", "Modelo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modelo").value("Modelo X"))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }

    @Test
    void shouldUpdateResourceState() throws Exception {
        when(resourceService.updateState(1L, "En uso")).thenReturn(recurso);

        mockMvc.perform(put("/api/updateResourceByState/1/En uso"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Disponible"));
    }






}
