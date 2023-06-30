package com.backend.integradorBackend1.services.impl;

import com.backend.integradorBackend1.dto.OdontologoDto;
import com.backend.integradorBackend1.entity.Odontologo;
import com.backend.integradorBackend1.exceptions.ResourceNotFoundException;
import com.backend.integradorBackend1.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class OdontologoServiceTest {

    @MockBean
    private OdontologoRepository odontologoRepository;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    void buscarOdontologoPorId_Exists_ReturnsOdontologoDto() {
        // Arrange
        Long id = 1L;
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);
        OdontologoDto expectedDto = new OdontologoDto();
        expectedDto.setId(id);

        when(odontologoRepository.findById(id)).thenReturn(Optional.of(odontologo));

        // Act
        OdontologoDto result = odontologoService.buscarOdontologoPorId(id);

        // Assert
        assertEquals(expectedDto, result);
    }

    @Test
    void buscarOdontologoPorId_NotExists_ReturnsNull() {
        // Arrange
        Long id = 1L;

        when(odontologoRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        OdontologoDto result = odontologoService.buscarOdontologoPorId(id);

        // Assert
        assertEquals(null, result);
    }

    @Test
    void listarOdontologos_ReturnsListOfOdontologoDto() {
        // Arrange
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        List<Odontologo> odontologoList = Collections.singletonList(odontologo);
        OdontologoDto expectedDto = new OdontologoDto();
        expectedDto.setId(1L);
        List<OdontologoDto> expectedDtoList = Collections.singletonList(expectedDto);

        when(odontologoRepository.findAll()).thenReturn(odontologoList);
        ObjectMapper objectMapper = new ObjectMapper();
        OdontologoService odontologoService = new OdontologoService(objectMapper, odontologoRepository);

        // Act
        List<OdontologoDto> result = odontologoService.listarOdontologos();

        // Assert
        assertEquals(expectedDtoList.size(), result.size());
        assertEquals(expectedDtoList.get(0).getId(), result.get(0).getId());
    }


    @Test
    void eliminarOdontologo_Exists_DeletesOdontologo() throws ResourceNotFoundException {
        // Arrange
        Long id = 1L;
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);

        when(odontologoRepository.findById(id)).thenReturn(Optional.of(odontologo));

        // Act
        odontologoService.eliminarOdontologo(id);

        // Assert
        verify(odontologoRepository).deleteById(id);
    }
}


