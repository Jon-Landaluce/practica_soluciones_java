package modelo;

public class Pelicula {

    private int id;
    private String titulo;
    private Director director;
    private int anyo;
    private String url_caratula;
    private Enum<?> generos;
    private int idGenero;
    private String es_animacion; 

    public Pelicula(int id, String titulo, Director director, int anyo, String url_caratula, Enum<?> generos, String es_animacion) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.url_caratula = url_caratula;
        this.generos = generos;
        this.es_animacion = es_animacion;
    }

    public Pelicula(int id, String titulo, Director director, int anyo, String url_caratula, int idGenero, String es_animacion) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anyo = anyo;
        this.url_caratula = url_caratula;
        this.idGenero = idGenero;
        this.es_animacion = es_animacion;
    }

    public Pelicula(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getUrl_caratula() {
        return url_caratula;
    }

    public void setUrl_caratula(String url_caratula) {
        this.url_caratula = url_caratula;
    }

    public Enum<?> getGeneros() {
        return generos;
    }

    public void setGeneros(Enum<?> generos) {
        this.generos = generos;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getEs_animacion() {
        return es_animacion;
    }

    public void setEs_animacion(String es_animacion) {
        this.es_animacion = es_animacion;
    }

}
