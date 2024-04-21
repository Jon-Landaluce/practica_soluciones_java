-- PELICULAS
CREATE TABLE peliculas (
    id INTEGER,
    titulo TEXT NOT NULL,
    id_director INTEGER,
    anyo INTEGER,
    url_caratula TEXT,
    id_genero INTEGER,
    es_animacion CHAR(1),
	PRIMARY KEY ("id" AUTOINCREMENT),
    FOREIGN KEY (id_director) REFERENCES directores(id),
    FOREIGN KEY (id_genero) REFERENCES generos(id)	
);

-- ARTISTAS
CREATE TABLE artistas (
    id INTEGER,
    nombre TEXT NOT NULL,
    url_foto TEXT,
    url_web TEXT,
	PRIMARY KEY ("id")
);

-- DIRECTORES
CREATE TABLE directores (
    id INTEGER,
    nombre TEXT NOT NULL,
    url_foto TEXT,
    url_web TEXT,
	PRIMARY KEY ("id" AUTOINCREMENT)
);

-- GENEROS
CREATE TABLE generos (
    id INTEGER,
    descripcion TEXT NOT NULL,
	PRIMARY KEY ("id")
);

-- REPARTOS
CREATE TABLE repartos (
    id_pelicula INTEGER,
    id_artista INTEGER,
	PRIMARY KEY (id_pelicula),
    FOREIGN KEY (id_pelicula) REFERENCES peliculas(id),
    FOREIGN KEY (id_artista) REFERENCES artistas(id)
);

-- INSERT GENEROS

INSERT INTO generos(id, descripcion) values (1, "accion");
INSERT INTO generos(id, descripcion) values (2, "aventura");
INSERT INTO generos(id, descripcion) values (3, "comedia");
INSERT INTO generos(id, descripcion) values (4, "drama");
INSERT INTO generos(id, descripcion) values (5, "fantasia");
INSERT INTO generos(id, descripcion) values (6, "terror");
INSERT INTO generos(id, descripcion) values (7, "cienciaficcion");
INSERT INTO generos(id, descripcion) values (8, "musical");
INSERT INTO generos(id, descripcion) values (9, "suspense");
INSERT INTO generos(id, descripcion) values (10, "western");
INSERT INTO generos(id, descripcion) values (11, "docuemental");
INSERT INTO generos(id, descripcion) values (12, "biografia");
INSERT INTO generos(id, descripcion) values (13, "romance");

-- INSERT DIRECTORES

INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Christopher Nolan", "https://example.com/nolan.jpg", "https://nolanfilms.com");
INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Steven Spielberg", "https://example.com/spielberg.jpg", "https://spielbergmovies.com");
INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Quentin Tarantino", "https://example.com/tarantino.jpg", "https://tarantinofilms.net");
INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Hayao Miyazaki", "https://example.com/miyazaki.jpg", "https://miyazakianimation.com");
INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Greta Gerwig", "https://example.com/gerwig.jpg", "https://gerwigdirects.com");
INSERT INTO directores (nombre, url_foto, url_web) VALUES ("Denis Villeneuve", "https://example.com/villeneuve.jpg", "https://villeneuvemovies.com");


-- INSERT ARTISTAS

INSERT INTO artistas(id, nombre, url_foto, url_web) values (1, "Di Caprio", "www.Leodicaprio.com", "Leodicaprio.jpg");
INSERT INTO artistas(id, nombre, url_foto, url_web) values (2, "Will Smith", "www.WillSmith.com", "WillSmith.jpg");
INSERT INTO artistas(id, nombre, url_foto, url_web) values (3, "Brad Pitt", "www.BradPitt", "BradPitt.jpg");
INSERT INTO artistas(id, nombre, url_foto, url_web) values (4, "Mario Casas", "www.MarioCasas.com", "MarioCasas.jpg");
INSERT INTO artistas(id, nombre, url_foto, url_web) values (5, "Harrison Ford", "www.HarrisonFord.com", "HarrisonFord.jpg");
INSERT INTO artistas(id, nombre, url_foto, url_web) values (6, "Jim Carrey", "www.JimCarrey.com", "JimCarrey.jpg");

-- INSERT PELICULAS

INSERT INTO peliculas (titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES ("Inception", 1, 2010, "https://example.com/inception.jpg", 1, "FALSE");
INSERT INTO peliculas (titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES ("Interstellar", 1, 2014, "https://example.com/interstellar.jpg", 1, "FALSE");
INSERT INTO peliculas (titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES ("Toy Story", 2, 1995, "https://example.com/toy_story.jpg", 2, "TRUE");
INSERT INTO peliculas (titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES ("Finding Nemo", 2, 2003, "https://example.com/finding_nemo.jpg", 2, "TRUE");
INSERT INTO peliculas (titulo, id_director, anyo, url_caratula, id_genero, es_animacion) VALUES ("Up", 2, 2009, "https://example.com/up.jpg", 2, "TRUE");

-- INSERT REPARTOS

INSERT INTO repartos(id_pelicula, id_artista) values (1,1);
INSERT INTO repartos(id_pelicula, id_artista) values (2,2);
INSERT INTO repartos(id_pelicula, id_artista) values (3,3);
INSERT INTO repartos(id_pelicula, id_artista) values (4,4);
INSERT INTO repartos(id_pelicula, id_artista) values (5,5);

