package modelo;

public class Director {

    private int id_director;
    private String nombre;
    private String url_foto;
    private String url_web;

    public Director(int id_director, String nombre, String url_foto, String url_web) {
        this.id_director = id_director;
        this.nombre = nombre;
        this.url_foto = url_foto;
        this.url_web = url_web;
    }

   /*  public Director(String nombre) {
        this.nombre = nombre;
    } */

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getUrl_web() {
        return url_web;
    }
    
    public void setUrl_web(String url_web) {
        this.url_web = url_web;
    }
    
}