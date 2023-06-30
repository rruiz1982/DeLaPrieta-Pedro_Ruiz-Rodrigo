package com.backend.integradorBackend1.services;

import com.backend.integradorBackend1.dto.PacienteDto;
import com.backend.integradorBackend1.entity.Paciente;
import com.backend.integradorBackend1.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id);

    PacienteDto guardarPaciente(Paciente paciente);

    PacienteDto actualizarPaciente(Paciente paciente);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}
