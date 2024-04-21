package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaDAO {

    private String path;

    public PeliculaDAO(String path) {
        this.path = path;
    }

    public void insertarPelicula(Pelicula peli) throws SQLException {

        String sql = "INSERT into peliculas(id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion) values(?, ?, ?, ?, ?, ?, ?)";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setInt(1, peli.getId());
        sentenciaSQL.setString(2, peli.getTitulo());
        sentenciaSQL.setInt(3, peli.getDirector().getId_director());
        sentenciaSQL.setInt(4, peli.getAnyo());
        sentenciaSQL.setString(5, peli.getUrl_caratula());
        sentenciaSQL.setInt(6, peli.getIdGenero());
        sentenciaSQL.setString(7, peli.getEs_animacion());

        sentenciaSQL.executeUpdate();
        conn.close();

    }

    
    public ArrayList<Pelicula> traeTodasPeliculas () throws SQLException {
     
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        
        String sql = "SELECT * FROM peliculas";
        
        Connection conn = new Utils().getConnection(path);
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        //PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");
        
        while (resultado.next()) {

            int id_pelicula = resultado.getInt("id");
            Pelicula peli = buscarPeliculaPorId(id_pelicula); 
            listaPeliculas.add(peli);
            //conn.close();
        
        }
        conn.close();
        return listaPeliculas;
    }
     

    public Pelicula buscarPeliculaPorId(int id) throws SQLException {

        // Devuelve una instancia de Pelicula o un null si no lo encuentra

        String sql = "SELECT * FROM peliculas WHERE id = ?";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setInt(1, id);

        // System.out.println(sentenciaSQL);

        ResultSet resultado = sentenciaSQL.executeQuery();

        Pelicula peli;

        if (resultado.next()) {

            DirectorDAO dDAO = new DirectorDAO("./data/videoteca_test.sqlite");

            int id_pelicula = resultado.getInt("id");
            String titulo = resultado.getString("titulo");
            int id_director = resultado.getInt("id_director");
            int anyo = resultado.getInt("anyo");   
            String url_caratula = resultado.getString("url_caratula");
            int id_genero = resultado.getInt("id_genero");
            String animacion = resultado.getString("es_animacion");

            Director director_peli = dDAO.buscarPorId(id_director);
            peli = new Pelicula (id_pelicula, titulo, director_peli, anyo, url_caratula, id_genero, animacion);
            
            conn.close();

            return peli;

        } else {
            return null;
        }

    }

    public Pelicula buscarPeliculaPorTitulo(String busca_titulo) throws SQLException {


        String sql = "SELECT * FROM peliculas WHERE titulo = ?";

        Connection conn = new Utils().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setString(1, busca_titulo);

        // System.out.println(sentenciaSQL);

        ResultSet resultado = sentenciaSQL.executeQuery();

        Pelicula peli;

        if (resultado.next()) {

            DirectorDAO dDAO = new DirectorDAO("./data/videoteca_test.sqlite");

            int id_pelicula = resultado.getInt("id");
            String titulo = resultado.getString("titulo");
            int id_director = resultado.getInt("id_director");
            int anyo = resultado.getInt("anyo");   
            String url_caratula = resultado.getString("url_caratula");
            int id_genero = resultado.getInt("id_genero");
            String animacion = resultado.getString("es_animacion");

            Director director_peli = dDAO.buscarPorId(id_director);
            peli = new Pelicula (id_pelicula, titulo, director_peli, anyo, url_caratula, id_genero, animacion);
            
            conn.close();

            return peli;

        } else {
            return null;
        }

    }


     
     public Pelicula borrarPeliculaPorId (int id) throws SQLException {
     
    Pelicula peli = buscarPeliculaPorId(id);
    
    if (peli != null) {

    String sql = "DELETE FROM peliculas WHERE id = ?";
     
    Connection conn = new Utils().getConnection(path);
    PreparedStatement sententenciaSQL = conn.prepareStatement(sql);
    sententenciaSQL.setInt(1,id);
    
    sententenciaSQL.executeUpdate();
    conn.close();
    
    }
    return peli;
    }
     
    
    public Pelicula modificarPelicula (Pelicula pelicula, String nuevoTitulo, String nuevaUrlFoto) throws SQLException{

        String sql = "UPDATE peliculas SET id = ?, titulo=?, id_director = ?, anyo = ?, url_caratula = ?, id_genero = ?, es_animacion = ? WHERE id=?";
        //Pelicula peli = new Pelicula(6, "Inception", directorExistente, 2010, "www.webInception.com", 9, "FALSE");

        Connection conn = new Utils().getConnection("./data/videoteca_test.sqlite");
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setInt(1, pelicula.getId());
        sentenciaSQL.setString(2, nuevoTitulo);
        sentenciaSQL.setInt(3, pelicula.getDirector().getId_director());
        sentenciaSQL.setInt(4, pelicula.getAnyo());
        sentenciaSQL.setString(5, nuevaUrlFoto);
        sentenciaSQL.setInt(6, pelicula.getIdGenero());
        sentenciaSQL.setString(7, pelicula.getEs_animacion());
       
        sentenciaSQL.executeUpdate();

        pelicula.setTitulo(nuevoTitulo);
        pelicula.setUrl_caratula(nuevaUrlFoto);

      
        conn.close();
        return pelicula;
    }
     
}