package com.backend.integradorBackend1.services;


import com.backend.integradorBackend1.dto.TurnoDto;
import com.backend.integradorBackend1.entity.Turno;
import com.backend.integradorBackend1.exceptions.BadRequestException;
import com.backend.integradorBackend1.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id) throws ResourceNotFoundException;


}
