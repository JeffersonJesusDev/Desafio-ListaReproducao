package com.devJJ.lista_reproducao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_muisicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String artista;
    private String Album;
    private String ano;
    private String Genero;

    @ManyToOne
    @JoinColumn(name = "reproducao_id")
    @JsonBackReference
    private Reproducao reproducao;

    public Musica() {
    }

    public Musica(Long id, String titulo, String artista, String album, String ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        Album = album;
        this.ano = ano;
        Genero = genero;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }
}
