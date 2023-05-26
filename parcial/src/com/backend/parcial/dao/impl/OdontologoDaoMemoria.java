package com.backend.parcial.dao.impl;


import com.backend.parcial.dao.impl.Dao;
import com.backend.parcial.model.Odontologo;
import java.util.List;
import org.apache.log4j.Logger;


public class OdontologoDaoMemoria implements Dao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologoRepositorio;

    public OdontologoDaoMemoria() {
    }

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepositorio) {
        this.odontologoRepositorio = odontologoRepositorio;
    }

    public Odontologo registrar(Odontologo odontologo) {
        this.odontologoRepositorio.add(odontologo);
        LOGGER.info("Odontologo guardado: " + String.valueOf(odontologo));
        return odontologo;
    }

    public List<Odontologo> listar() {
        LOGGER.info("Listado de todos los odontologos: \n" + String.valueOf(this.odontologoRepositorio));
        return this.odontologoRepositorio;
    }
}
