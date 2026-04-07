package es.gimbernat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BBDD {
    private Connection conn;


    public boolean init() {
        try {
            Properties p = loadPropertiesFile();
            if (p==null) return false;
 
            String strConn = (String) p.get("db.string_connection");
            conn = DriverManager.getConnection(strConn);
            return true;
        } catch (SQLException e) {
            showError(e);
            unLoad();
            return false;
        }
    }

    public void showError(SQLException e) {
        System.out.println("Mensaje de error: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }

    public void unLoad()
    {
        try {
            if (conn!= null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Properties loadPropertiesFile()
    {
        Properties p = new Properties();
        try {
            InputStream resourceAsStream =
            getClass().getClassLoader().getResourceAsStream("config.properties");
            p.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    return p;
    }

    public boolean insertEmpleado(Empleado e) {
        String sql = "INSERT INTO empleados (nombre, salario) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getSalario());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            showError(ex);
            return false;
        }
    }

    public boolean updateEmpleado(Empleado e) {
        String sql = "UPDATE empleados SET nombre = ?, salario = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getSalario());
            ps.setInt(3, e.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            showError(ex);
            return false;
        }
    }

    public boolean deleteEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            showError(ex);
            return false;
        }
    }

    public List<Empleado> getAllEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("salario")
                ));
            }

        } catch (SQLException ex) {
            showError(ex);
        }

        return lista;
    }
}
