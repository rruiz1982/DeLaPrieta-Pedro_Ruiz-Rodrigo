package com.backend.parcial.dao.impl;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAO {

    private Connection connection;

    public OdontologoDAO() {
        // Establecer conexión con la base de datos H2
        try {
            connection = DriverManager.getConnection("jdbc:h2:~/odontologos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarOdontologo(Odontologo odontologo) {
        String query = "INSERT INTO odontologos (matricula, nombre, apellido) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, odontologo.getMatricula());
            statement.setString(2, odontologo.getNombre());
            statement.setString(3, odontologo.getApellido());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Odontologo> listarOdontologos() {
        List<Odontologo> odontologos = new ArrayList<>();
        String query = "SELECT * FROM odontologos";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int matricula = resultSet.getInt("matricula");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Odontologo odontologo = new Odontologo(matricula, nombre, apellido);
                odontologos.add(odontologo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odontologos;
    }
}