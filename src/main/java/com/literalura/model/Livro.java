package com.literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Double downloads;

    @ManyToOne
    private Autor autor;

    public Livro(){}

    public Livro(String titulo, String idioma, Double downloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Double getDownloads() {
        return downloads;
    }

    public Autor getAutor() {
        return autor;
    }

}