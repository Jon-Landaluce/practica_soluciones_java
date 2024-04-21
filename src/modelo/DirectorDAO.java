package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirectorDAO {

    private String path;

    public DirectorDAO (String path) {
        this.path = path;
    }

    public void insertarDirector (Director dir) throws SQLException {

        String sql = "INSERT INTO directores (id, nombre, url_foto, url_web) VALUES (?, ?, ?, ?)";

        Connection conn = new Utils().getConnection(path);

        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setInt(1, dir.getId_director());
        sentenciaSQL.setString(2, dir.getNombre());
        sentenciaSQL.setString(3, dir.getUrl_foto());
        sentenciaSQL.setString(4, dir.getUrl_web());

        sentenciaSQL.executeUpdate();
        conn.close();

    }

    public ArrayList<Director> traeTodosDirectores () throws SQLException {

        // Ordenar por nombre

        String sql = "SELECT id, nombre, url_foto, url_web FROM directores ORDER BY id";

        Connection conn = new Utils().getConnection(path);
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        ArrayList<Director> listaDir = new ArrayList<>();

        while (resultado.next()) {

            Director dir;

            dir = new Director (resultado.getInt("id"), 
                                resultado.getString("nombre"),
                                resultado.getString("url_foto"),
                                resultado.getString("url_web"));        

            listaDir.add(dir);
            //conn.close();
            
        }
        return listaDir;
    }

    public Director buscarPorId (int id) throws SQLException{

        // Devuelve una instancia de director o un null si no lo encuentra

        String sql = "SELECT * FROM directores WHERE id = ?";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setInt(1, id);

        //System.out.println(sentenciaSQL);

        ResultSet resultado = sentenciaSQL.executeQuery();
        
        Director dir;

        if (resultado.next()) {
            dir = new Director(resultado.getInt("id"),
                                resultado.getString("nombre"),
                                resultado.getString("url_foto"), 
                                resultado.getString("url_web"));
                                conn.close();

            return dir;

        } else {
            return null;
        }
        
        
    }

    public Director buscarPorNombre (String nombre) throws SQLException {

        // Devuelve una instancia de director o un null si no lo encuentra

        String sql = "SELECT * FROM directores WHERE nombre = ?";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setString(1, nombre);

        //System.out.println(sentenciaSQL);

        ResultSet resultado = sentenciaSQL.executeQuery();

        Director dir;

        //conn.close();

        if (resultado.next()) {
            dir = new Director(resultado.getInt("id"),
                                resultado.getString("nombre"),
                                resultado.getString("url_foto"), 
                                resultado.getString("url_web"));
                                conn.close();
            return dir;

        } else {
            return null;
        }

    }

    public Director borrarPorId (int id) throws SQLException {

        // Si existe borra el director

        Director dir = buscarPorId(id);

        if (dir != null) {

            String sql = "DELETE FROM directores WHERE id = ?";

            Connection conn = new Utils().getConnection(path);
            PreparedStatement sententenciaSQL = conn.prepareStatement(sql);
            sententenciaSQL.setInt(1,id);

            sententenciaSQL.executeUpdate();
            conn.close();

        }
        return dir;
    }
    

    public Director modificarDirector (Director director, String nuevoNombre, String nuevaUrlFoto, String nuevaUrlWeb) throws SQLException {

        // Director tiene un atributo Id, eso nos permitira usarlo en la clausula where de la sentencia SQL
        //el resto de valores tenedremos que modificarlos desde la base de datos

        String sql = "UPDATE directores SET nombre = ?, url_foto = ?, url_web = ? WHERE id=?";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setString(1, nuevoNombre);
        sentenciaSQL.setString(2, nuevaUrlFoto);
        sentenciaSQL.setString(3, nuevaUrlWeb);
        sentenciaSQL.setInt(4, director.getId_director());

        sentenciaSQL.executeUpdate();

        director.setNombre(nuevoNombre);
        director.setUrl_foto(nuevaUrlFoto);
        director.setUrl_web(nuevaUrlWeb);

        return director;
    }


    
}

