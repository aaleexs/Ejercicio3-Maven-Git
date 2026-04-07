package es.gimbernat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public boolean insertDepartamento(Departamento d) {
    String sql = "INSERT INTO departamentos (nombre, localidad) VALUES (?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, d.getNombre());
        ps.setString(2, d.getLocalidad());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        showError(ex);
        return false;
    }
}

public boolean updateDepartamento(Departamento d) {
    String sql = "UPDATE departamentos SET nombre = ?, localidad = ? WHERE id = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, d.getNombre());
        ps.setString(2, d.getLocalidad());
        ps.setInt(3, d.getId());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        showError(ex);
        return false;
    }
}

public boolean deleteDepartamento(int id) {
    String sql = "DELETE FROM departamentos WHERE id = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        showError(ex);
        return false;
    }
}

public List<Departamento> getAllDepartamentos() {
    List<Departamento> lista = new ArrayList<>();
    String sql = "SELECT * FROM departamentos";

    try (Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            lista.add(new Departamento(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("localidad")
            ));
        }

    } catch (SQLException ex) {
        showError(ex);
    }

    return lista;
}
}
