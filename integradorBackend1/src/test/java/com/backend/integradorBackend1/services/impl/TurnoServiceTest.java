package com.backend.integradorBackend1.services.impl;


import com.backend.integradorBackend1.dto.OdontologoDto;
import com.backend.integradorBackend1.dto.PacienteDto;
import com.backend.integradorBackend1.dto.TurnoDto;
import com.backend.integradorBackend1.entity.Domicilio;
import com.backend.integradorBackend1.entity.Odontologo;
import com.backend.integradorBackend1.entity.Paciente;
import com.backend.integradorBackend1.entity.Turno;
import com.backend.integradorBackend1.exceptions.BadRequestException;
import com.backend.integradorBackend1.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private  PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init(){
        paciente = new Paciente( "Rodriogo", "Ruiz", "4481611", LocalDate.of(2023, 06, 30),
                new Domicilio( "Laureles", 281, "Montevideo", "Montevide"));
        odontologo = new Odontologo( "AD-85698553", "Martin", "Lopez");

    }
    @Test
    @Order(1)
    void deberiaInsertarUnTurno() throws BadRequestException{
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2024, 10, 01),
                LocalTime.of(12,00))));

        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
        Assertions.assertEquals(turnoDto.getPaciente(), pacienteDto.getNombre()+" "+pacienteDto.getApellido());
        Assertions.assertEquals(turnoDto.getOdontologo(), odontologoDto.getNombre()+" "+odontologoDto.getApellido());


    }

   @Test
    @Order(2)
           void deberiaListarTodos() {

        List <TurnoDto> turnoDtoList = turnoService.listarTodos();
       Assertions.assertEquals(1,turnoDtoList.size());

   }


    @Test
    @Order(3)
    void deberiaBuscarTurnoPorId(){
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(1l);
        Assertions.assertNotNull(turnoDto);

    }


}

