package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//import modelo.Artista;
import modelo.Director;
import modelo.Generos;

public class TestModelo {

    @Test

    public void testCrearGeneros () {
        
        Generos accion = Generos.ACCION;
        Generos aventura = Generos.AVENTURA;
        Generos comedia = Generos.COMEDIA;
        Generos drama = Generos.DRAMA;
        Generos fantasia = Generos.FANTASIA;
        Generos terror = Generos.TERROR;
        Generos cienciaficcion = Generos.CIENCIAFICCION;
        Generos musical = Generos.MUSICAL;
        Generos suspense = Generos.SUSPENSE;
        Generos western = Generos.WESTERN;
        Generos documental = Generos.DOCUMENTAL;
        Generos biografia = Generos.BIOGRAFIA;
        Generos romance = Generos.ROMANCE;
        assertEquals(Generos.ACCION, accion);
        assertEquals(Generos.AVENTURA, aventura);
        assertEquals(Generos.COMEDIA, comedia);
        assertEquals(Generos.DRAMA, drama);
        assertEquals(Generos.FANTASIA, fantasia);
        assertEquals(Generos.TERROR, terror);
        assertEquals(Generos.CIENCIAFICCION, cienciaficcion);
        assertEquals(Generos.MUSICAL, musical);
        assertEquals(Generos.SUSPENSE, suspense);
        assertEquals(Generos.WESTERN, western);
        assertEquals(Generos.DOCUMENTAL, documental);
        assertEquals(Generos.BIOGRAFIA, biografia);
        assertEquals(Generos.ROMANCE, romance);
    }

    @Test

    public void testCrearGenerosConIds () {

        Generos accion = Generos.ACCION;
        Generos aventura = Generos.AVENTURA;
        Generos comedia = Generos.COMEDIA;
        Generos drama = Generos.DRAMA;
        Generos fantasia = Generos.FANTASIA;
        Generos terror = Generos.TERROR;
        Generos cienciaficcion = Generos.CIENCIAFICCION;
        Generos musical = Generos.MUSICAL;
        Generos suspense = Generos.SUSPENSE;
        Generos western = Generos.WESTERN;
        Generos documental = Generos.DOCUMENTAL;
        Generos biografia = Generos.BIOGRAFIA;
        Generos romance = Generos.ROMANCE;
        assertEquals(accion.getId(), 1);
        assertEquals(aventura.getId(), 2);
        assertEquals(comedia.getId(), 3);
        assertEquals(drama.getId(), 4);
        assertEquals(fantasia.getId(), 5);
        assertEquals(terror.getId(), 6);
        assertEquals(cienciaficcion.getId(), 7);
        assertEquals(musical.getId(), 8);
        assertEquals(suspense.getId(), 9);
        assertEquals(western.getId(), 10);
        assertEquals(documental.getId(), 11);
        assertEquals(biografia.getId(), 12);
        assertEquals(romance.getId(), 13);
    }

    
    @Test

    public void testCrearDirector () {

        Director dir = new Director (001, "Mikel", "www.foto.com", "www.web.com");

        assertEquals(001, dir.getId_director());
        assertEquals("Mikel", dir.getNombre());
        assertEquals("www.foto.com", dir.getUrl_foto());
        assertEquals("www.web.com", dir.getUrl_web());

    }

    
}
