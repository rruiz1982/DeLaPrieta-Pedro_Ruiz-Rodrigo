package com.backend.parcial.dao.impl;

import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements Dao<Odontologo>{

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    public static final String DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:~/test;PASSWORD=;USER=sa;INIT=RUNSCRIPT FROM 'createDb.sql'";

    private static void getDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        getDriver();

        Connection connection = null;
        LOGGER.debug("La variable connection es: " + connection);

        try {

            connection = DriverManager.getConnection(DB_URL);
            LOGGER.debug("La variable connection es: " + connection);

            String insert = "INSERT INTO ODONTOLOGO (NROMATRICULA,NOMBRE, APELLIDO) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            LOGGER.info("Se insertaron con éxito los datos: " + odontologo.getNumeroMatricula()+  ", " + odontologo.getNombre() +  ", " + odontologo.getApellido() + " en la tabla ODONTOLOGO");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        List<Odontologo> listaOdontologos = new ArrayList<>();

        getDriver();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL);

            String query = "SELECT * FROM ODONTOLOGO";
            Statement stmt= connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int nroMatricula = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                Odontologo odontologo = new Odontologo(nroMatricula,nombre, apellido);
                listaOdontologos.add(odontologo);
            }



        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }
        }

        for (Odontologo odontologo : listaOdontologos) {

            LOGGER.info("Lista de odontologos: " + odontologo.getNumeroMatricula()+  ", " + odontologo.getNombre() +  ", " + odontologo.getApellido() );

        }
        return listaOdontologos;
    }


}
