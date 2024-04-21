package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.Director;
import modelo.DirectorDAO;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import modelo.Utils;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDAO {

    @Test

    public void testJDBCConnecta() {

        boolean laClaseJDBCExiste = false;

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Todo ha ido bien");
            laClaseJDBCExiste = true;
        } catch (ClassNotFoundException error) {
            System.out.println("SQLite JDBC no encontrado");
            error.printStackTrace();
        }
        assertTrue(laClaseJDBCExiste);

    }

    @Test

    public void testCrearConexion() throws SQLException {

        Utils getconn = new Utils();

        Connection conn = getconn.getConnection("./data/videoteca_test.sqlite");

        assertNotNull(conn);
        conn.close();

        conn = getconn.getConnection("./testDevuelveNull/videoteca_test.sqlite");
        assertNull(conn);

    }

    // TEST DIRECTOR

    @Test

    public void testInsertarDirector() throws SQLException {

        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director dir = new Director(7, "Martin Scorsese", "https://example.com/scorsese.jpg",
                "https://scorsesefilms.com");

        dao.insertarDirector(dir);

        int id_director = 0;
        String nombre = "";
        String url_foto = "";
        String url_web = "";

        String sql = "SELECT id, nombre, url_foto, url_web FROM directores ORDER BY id desc";
        Connection conn = new Utils().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            id_director = resultado.getInt("id");
            nombre = resultado.getString("nombre");
            url_foto = resultado.getString("url_foto");
            url_web = resultado.getString("url_web");
        }
        conn.close();

        assertEquals(7, id_director);
        assertEquals("Martin Scorsese", nombre);
        assertEquals("https://example.com/scorsese.jpg", url_foto);
        assertEquals("https://scorsesefilms.com", url_web);
    }

    @Test

    public void testTraeTodosDirectores() throws SQLException {

        String sql = "DELETE FROM directores";

        Connection conn = new Utils().getConnection("./data/videoteca_test.sqlite");

        Statement sentenciaSQL = conn.createStatement();

        sentenciaSQL.executeUpdate(sql);

        conn.close();

        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director dir_1 = new Director(1, "Christopher Nolan", "https://example.com/nolan.jpg",
                "https://nolanfilms.com");
        dao.insertarDirector(dir_1);
        Director dir_2 = new Director(2, "Steven Spielberg", "https://example.com/spielberg.jpg",
                "https://spielbergmovies.com");
        dao.insertarDirector(dir_2);
        Director dir_3 = new Director(3, "Quentin Tarantino", "https://example.com/tarantino.jpg",
                "https://tarantinofilms.net");
        dao.insertarDirector(dir_3);
        Director dir_4 = new Director(4, "Hayao Miyazaki", "https://example.com/miyazaki.jpg",
                "https://miyazakianimation.com");
        dao.insertarDirector(dir_4);
        Director dir_5 = new Director(5, "Greta Gerwig", "https://example.com/gerwig.jpg", "https://gerwigdirects.com");
        dao.insertarDirector(dir_5);
        Director dir_6 = new Director(6, "Denis Villeneuve", "https://example.com/villeneuve.jpg",
                "https://villeneuvemovies.com");
        dao.insertarDirector(dir_6);

        ArrayList<Director> listaDirectores = dao.traeTodosDirectores();

        assertEquals(6, listaDirectores.size());

        assertEquals(1, listaDirectores.get(0).getId_director());
        assertEquals(2, listaDirectores.get(1).getId_director());
        assertEquals(3, listaDirectores.get(2).getId_director());
        assertEquals(4, listaDirectores.get(3).getId_director());
        assertEquals(5, listaDirectores.get(4).getId_director());
        assertEquals(6, listaDirectores.get(5).getId_director());

        assertEquals("Christopher Nolan", listaDirectores.get(0).getNombre());
        assertEquals("Steven Spielberg", listaDirectores.get(1).getNombre());
        assertEquals("Quentin Tarantino", listaDirectores.get(2).getNombre());
        assertEquals("Hayao Miyazaki", listaDirectores.get(3).getNombre());
        assertEquals("Greta Gerwig", listaDirectores.get(4).getNombre());
        assertEquals("Denis Villeneuve", listaDirectores.get(5).getNombre());

        assertEquals("https://example.com/nolan.jpg", listaDirectores.get(0).getUrl_foto());
        assertEquals("https://example.com/spielberg.jpg", listaDirectores.get(1).getUrl_foto());
        assertEquals("https://example.com/tarantino.jpg", listaDirectores.get(2).getUrl_foto());
        assertEquals("https://example.com/miyazaki.jpg", listaDirectores.get(3).getUrl_foto());
        assertEquals("https://example.com/gerwig.jpg", listaDirectores.get(4).getUrl_foto());
        assertEquals("https://example.com/villeneuve.jpg", listaDirectores.get(5).getUrl_foto());

        assertEquals("https://nolanfilms.com", listaDirectores.get(0).getUrl_web());
        assertEquals("https://spielbergmovies.com", listaDirectores.get(1).getUrl_web());
        assertEquals("https://tarantinofilms.net", listaDirectores.get(2).getUrl_web());
        assertEquals("https://miyazakianimation.com", listaDirectores.get(3).getUrl_web());
        assertEquals("https://gerwigdirects.com", listaDirectores.get(4).getUrl_web());
        assertEquals("https://villeneuvemovies.com", listaDirectores.get(5).getUrl_web());

        conn.close();

    }

    @Test

    public void testbuscarDirectorPorId() throws SQLException {

        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director dir_1 = dao.buscarPorId(2);
        Director dir_2 = dao.buscarPorId(67);

        assertEquals("Steven Spielberg", dir_1.getNombre());
        assertNull(dir_2);

    }

    @Test

    public void testBuscarDirectorPorNombre() throws SQLException {

        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director dir_1 = dao.buscarPorNombre("Mikel");
        Director dir_2 = dao.buscarPorNombre("Anastasio");

        assertEquals("Hayao Miyazaki", dir_1.getNombre());
        assertEquals(4, dir_1.getId_director());
        assertNull(dir_2);

    }

    @Test

    public void testBorrarDirectorPorId() throws SQLException {

        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director dir_1 = dao.borrarPorId(6);

        if (dir_1 != null) {
            assertEquals("Denis Villeneuve", dir_1.getNombre());
            assertNotNull(dir_1);
        } else {
            assertNull(dir_1);
        }
        Director dir_2 = dao.borrarPorId(12);
        assertNull(dir_2);

    }

    @Test

    public void testModificarDirector() throws SQLException {


        DirectorDAO dao = new DirectorDAO("./data/videoteca_test.sqlite");

        Director directorExistente = dao.buscarPorId(5);

        String nuevoNombre = "Greta Gerwigg";
        String nuevaUrlFoto = "Modificación Greta Gerwigg.jpg";
        String nuevaUrlWeb = "http://Web actualizada Greta Gerwigg.com";

        Director directorModificado = dao.modificarDirector(directorExistente, nuevoNombre, nuevaUrlFoto, nuevaUrlWeb);

        assertEquals(nuevoNombre, directorModificado.getNombre());
        assertEquals(nuevaUrlFoto, directorModificado.getUrl_foto());
        assertEquals(nuevaUrlWeb, directorModificado.getUrl_web());

    }

    // TEST PELICULA

    @Test

    public void testInsertarPelicula() throws SQLException {

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");
        DirectorDAO dDAO = new DirectorDAO("./data/videoteca_test.sqlite");

        Director directorExistente = dDAO.buscarPorNombre("Christopher Nolan");

        Pelicula peli = new Pelicula(6, "Inception", directorExistente, 2010, "www.webInception.com", 9, "FALSE");
        pDAO.insertarPelicula(peli);

        Pelicula peliculaInsertada = pDAO.buscarPeliculaPorId(6);

        assertEquals(6, peliculaInsertada.getId());
        assertEquals("Inception", peliculaInsertada.getTitulo());
        assertEquals("Christopher Nolan", peliculaInsertada.getDirector().getNombre());
        assertEquals(2010, peliculaInsertada.getAnyo());
        assertEquals("www.webInception.com", peliculaInsertada.getUrl_caratula());
        assertEquals("FALSE", peliculaInsertada.getEs_animacion());
    }

    @Test

    public void testTraeTodasPeliculas() throws SQLException {

        String sql = "DELETE FROM peliculas";
        Connection conn = new Utils().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        sentenciaSQL.executeUpdate(sql);
        conn.close();

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");
        DirectorDAO dDAO = new DirectorDAO("./data/videoteca_test.sqlite");

        Director directorExistente_1 = dDAO.buscarPorNombre("Christopher Nolan");
        Pelicula peli_1 = new Pelicula(1, "Inception", directorExistente_1, 2010, "https://example.com/inception.jpg",
                1, "FALSE");
        pDAO.insertarPelicula(peli_1);

        Director directorExistente_2 = dDAO.buscarPorNombre("Christopher Nolan");
        Pelicula peli_2 = new Pelicula(2, "Interstellar", directorExistente_2, 2014,
                "https://example.com/interstellar.jpg", 1, "FALSE");
        pDAO.insertarPelicula(peli_2);

        Director directorExistente_3 = dDAO.buscarPorNombre("Steven Spielberg");
        Pelicula peli_3 = new Pelicula(3, "Toy Story", directorExistente_3, 1995, "https://example.com/toy_story.jpg",
                2, "TRUE");
        pDAO.insertarPelicula(peli_3);

        ArrayList<Pelicula> listaPeliculas = pDAO.traeTodasPeliculas();

        assertEquals(3, listaPeliculas.size());

        assertEquals(1, peli_1.getId());
        assertEquals(2, peli_2.getId());
        assertEquals(3, peli_3.getId());

        assertEquals("Inception", peli_1.getTitulo());
        assertEquals("Interstellar", peli_2.getTitulo());
        assertEquals("Toy Story", peli_3.getTitulo());

        assertEquals("Christopher Nolan", peli_1.getDirector().getNombre());
        assertEquals("Christopher Nolan", peli_2.getDirector().getNombre());
        assertEquals("Steven Spielberg", peli_3.getDirector().getNombre());

        assertEquals(2010, peli_1.getAnyo());
        assertEquals(2014, peli_2.getAnyo());
        assertEquals(1995, peli_3.getAnyo());

        assertEquals("https://example.com/inception.jpg", peli_1.getUrl_caratula());
        assertEquals("https://example.com/interstellar.jpg", peli_2.getUrl_caratula());
        assertEquals("https://example.com/toy_story.jpg", peli_3.getUrl_caratula());

        assertEquals("FALSE", peli_1.getEs_animacion());
        assertEquals("FALSE", peli_2.getEs_animacion());
        assertEquals("TRUE", peli_3.getEs_animacion());

        conn.close();

    }

    @Test

    public void testbuscarPeliculaPorId() throws SQLException {

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");

        Pelicula peli_1 = pDAO.buscarPeliculaPorId(3);
        Pelicula peli_2 = pDAO.buscarPeliculaPorId(67);

        assertEquals(3, peli_1.getId());
        assertEquals("Toy Story", peli_1.getTitulo());
        assertNull(peli_2);

    }

    @Test

    public void testBuscarPeliculaPorTitulo() throws SQLException {

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");

        Pelicula peli_1 = pDAO.buscarPeliculaPorTitulo("Inception");
        Pelicula peli_2 = pDAO.buscarPeliculaPorTitulo("Anastasio");

        assertEquals("Inception", peli_1.getTitulo());
        assertEquals(1, peli_1.getId());
        assertNull(peli_2);

    }

    @Test

    public void testBorrarpeliculaPorId() throws SQLException {

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");

        Pelicula peli_1 = pDAO.borrarPeliculaPorId(2);

        if (peli_1 != null) {
            assertEquals("Interstellar", peli_1.getTitulo());
            assertNotNull(peli_1);
        } else {
            assertNull(peli_1);
        }
        Pelicula peli_2 = pDAO.borrarPeliculaPorId(12);
        assertNull(peli_2);

    }

    @Test

    public void testModificarPelicula() throws SQLException {

        PeliculaDAO pDAO = new PeliculaDAO("./data/videoteca_test.sqlite");

        Pelicula peliculaExistente = pDAO.buscarPeliculaPorId(3);

        String nuevoTitulo = "La bestia";
        String nuevaUrlFoto = "nueva_foto_tarantino.jpg";

        Pelicula peliculaModificada = pDAO.modificarPelicula(peliculaExistente, nuevoTitulo, nuevaUrlFoto);

        assertEquals(nuevoTitulo, peliculaModificada.getTitulo());
        assertEquals(nuevaUrlFoto, peliculaModificada.getUrl_caratula());

    }

}
