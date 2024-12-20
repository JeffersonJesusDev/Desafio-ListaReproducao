package com.devJJ.lista_reproducao.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_listas_reproducao")
public class Reproducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "reproducao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Musica> musicas = new ArrayList<>();


    public Reproducao() {
    }


    public Reproducao(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }


    public void addMusica(Musica musica) {
        musicas.add(musica);
        musica.setReproducao(this);
    }


    public void removeMusica(Musica musica) {
        musicas.remove(musica);
        musica.setReproducao(null);
    }
}
