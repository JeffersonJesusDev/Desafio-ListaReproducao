package com.devJJ.lista_reproducao.service;

import com.devJJ.lista_reproducao.exception.InvalidInputException;
import com.devJJ.lista_reproducao.model.Musica;
import com.devJJ.lista_reproducao.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public List<Musica> verTodasMusicas() {
        return musicaRepository.findAll();
    }

    public Musica salvarMusica(Musica musica) {
        if (musica.getTitulo() == null || musica.getTitulo().isEmpty()) {
            throw new InvalidInputException("O título da música não pode ser vazio.");
        }
        return musicaRepository.save(musica);
    }



}
