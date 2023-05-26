package com.backend.parcial.service;

import com.backend.parcial.dao.impl.OdontologoDaoH2;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private OdontologoDaoH2 odontologoDaoH2;


    public OdontologoService() {
        this.odontologoDaoH2 = new OdontologoDaoH2();
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){

        if (odontologo.getNombre().equals("")|| odontologo.getApellido().equals("") || odontologo.getNumeroMatricula() == 0){
            LOGGER.warn("El usuario trató de registrarse con datos faltantes");
            throw new RuntimeException("Faltan datos del odontólogo");
        }
        else {

            odontologoDaoH2.registrar(odontologo);
            LOGGER.info("Se ha registrado el Odontologo: " + odontologo.getNumeroMatricula() + ", " + odontologo.getNombre()+ ", " + odontologo.getApellido());
            return odontologo;
        }

    }

    public List<Odontologo> listarOdontologos(){
        List<Odontologo> listaOdontologos = odontologoDaoH2.listar();

        return listaOdontologos;
    }
}
