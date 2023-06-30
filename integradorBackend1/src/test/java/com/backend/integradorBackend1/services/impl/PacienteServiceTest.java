package com.backend.integradorBackend1.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.backend.integradorBackend1.dto.DomicilioDto;
import com.backend.integradorBackend1.dto.PacienteDto;
import com.backend.integradorBackend1.entity.Domicilio;
import com.backend.integradorBackend1.entity.Paciente;
import com.backend.integradorBackend1.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PacienteServiceTest {

    @Test
    void listarPacientes_ReturnsListOfPacienteDto() {
        // Arrange
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle 123");
        domicilio.setLocalidad("Ciudad Ejemplo");
        paciente.setDomicilio(domicilio);
        List<Paciente> pacienteList = Collections.singletonList(paciente);
        PacienteDto expectedDto = new PacienteDto();
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle 123");
        domicilioDto.setLocalidad("Ciudad Ejemplo");
        expectedDto.setDomicilio(domicilioDto);
        List<PacienteDto> expectedDtoList = Collections.singletonList(expectedDto);

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        when(pacienteRepository.findAll()).thenReturn(pacienteList);
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteService pacienteService = new PacienteService(pacienteRepository, objectMapper);

        // Act
        List<PacienteDto> result = pacienteService.listarPacientes();

        // Assert
        assertEquals(expectedDtoList, result);
    }


    @Test
    void buscarPacientePorId_Exists_ReturnsPacienteDto() {
        // Arrange
        Long id = 1L;
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle 123");
        domicilio.setLocalidad("Ciudad Ejemplo");
        paciente.setDomicilio(domicilio);
        PacienteDto expectedDto = new PacienteDto();
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle 123");
        domicilioDto.setLocalidad("Ciudad Ejemplo");
        expectedDto.setDomicilio(domicilioDto);

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteService pacienteService = new PacienteService(pacienteRepository, objectMapper);

        // Act
        PacienteDto result = pacienteService.buscarPacientePorId(id);

        // Assert
        assertEquals(expectedDto, result);
    }

    @Test
    void guardarPaciente_ReturnsPacienteDto() {
        // Arrange
        Paciente paciente = new Paciente();
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle 123");
        domicilio.setLocalidad("Ciudad Ejemplo");
        paciente.setDomicilio(domicilio);
        PacienteDto expectedDto = new PacienteDto();
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setCalle("Calle 123");
        domicilioDto.setLocalidad("Ciudad Ejemplo");
        expectedDto.setDomicilio(domicilioDto);

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteService pacienteService = new PacienteService(pacienteRepository, objectMapper);

        // Act
        PacienteDto result = pacienteService.guardarPaciente(paciente);

        // Assert
        assertEquals(expectedDto, result);
    }
}
