package com.backend.integradorBackend1.services;


import com.backend.integradorBackend1.dto.OdontologoDto;
import com.backend.integradorBackend1.entity.Odontologo;
import com.backend.integradorBackend1.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id);

    List<OdontologoDto> listarOdontologos();

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
