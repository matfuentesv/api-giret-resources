package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Recurso;
import com.giret.apigiretresources.repository.ResourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResourceServiceImplTest {

    @InjectMocks
    private ResourceServiceImpl resourceService;

    @Mock
    private ResourceRepository resourceRepository;

    private Recurso recurso;

    @BeforeEach
    void setUp() {
        recurso = new Recurso();
        recurso.setIdRecurso(1L);
        recurso.setModelo("Modelo X");
        recurso.setEstado("Disponible");
        recurso.setNumeroSerie("123456");
    }

    @Test
    void shouldFindAllResources() {
        when(resourceRepository.findAll()).thenReturn(List.of(recurso));

        List<Recurso> result = resourceService.findAllResource();

        assertEquals(1, result.size());
        assertEquals("Modelo X", result.get(0).getModelo());
    }

    @Test
    void shouldFindResourceByIdPresent() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(recurso));

        Recurso result = resourceService.findResourceById(1L);

        assertNotNull(result);
        assertEquals("Modelo X", result.getModelo());
    }

    @Test
    void shouldFindResourceByIdNotPresent() {
        when(resourceRepository.findById(1L)).thenReturn(Optional.empty());

        Recurso result = resourceService.findResourceById(1L);

        assertNotNull(result);
        assertNull(result.getIdRecurso());
    }

    @Test
    void shouldSaveResource() {
        when(resourceRepository.save(any(Recurso.class))).thenReturn(recurso);

        Recurso saved = resourceService.saveResource(recurso);

        assertNotNull(saved);
        assertEquals("Modelo X", saved.getModelo());
    }

    @Test
    void shouldUpdateResource() {
        when(resourceRepository.save(any(Recurso.class))).thenReturn(recurso);

        Recurso updated = resourceService.updateResource(1L, recurso);

        assertNotNull(updated);
        assertEquals(1L, updated.getIdRecurso());
    }

    @Test
    void shouldDeleteResource() {
        doNothing().when(resourceRepository).deleteById(1L);

        Boolean result = resourceService.deleteResource(1L);

        assertTrue(result);
        verify(resourceRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldCountResources() {
        when(resourceRepository.count()).thenReturn(5L);

        long count = resourceService.countResources();

        assertEquals(5L, count);
    }



    @Test
    void shouldFindByState() {
        when(resourceRepository.findByEstado("Disponible")).thenReturn(List.of(recurso));

        List<Recurso> result = resourceService.findByState("Disponible");

        assertEquals(1, result.size());
        assertEquals("Disponible", result.get(0).getEstado());
    }

    @Test
    void shouldSearchResource() {
        when(resourceRepository.findByModeloContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase("Modelo", "Modelo"))
                .thenReturn(List.of(recurso));

        List<Recurso> result = resourceService.searchResource("Modelo");

        assertEquals(1, result.size());
        assertEquals("Modelo X", result.get(0).getModelo());
    }

    @Test
    void shouldUpdateStateSuccess() {
        when(resourceRepository.updateState(1L, "En uso")).thenReturn(1);
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(recurso));

        Recurso updated = resourceService.updateState(1L, "En uso");

        assertNotNull(updated);
    }

    @Test
    void shouldUpdateStateNoRowsUpdated() {
        when(resourceRepository.updateState(1L, "En uso")).thenReturn(0);

        assertThrows(RuntimeException.class, () -> {
            resourceService.updateState(1L, "En uso");
        });
    }

    @Test
    void shouldUpdateStateNoFound() {
        when(resourceRepository.updateState(1L, "En uso")).thenReturn(1);
        when(resourceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            resourceService.updateState(1L, "En uso");
        });
    }

    @Test
    void shouldCountByState() {
        List<Object[]> mockResult = Collections.singletonList(new Object[] { "Disponible", 3L });
        when(resourceRepository.countGroupByState()).thenReturn(mockResult);

        List<Object[]> result = resourceService.countByState();

        assertEquals(1, result.size());
        assertEquals("Disponible", result.get(0)[0]);
        assertEquals(3L, result.get(0)[1]);
    }

}