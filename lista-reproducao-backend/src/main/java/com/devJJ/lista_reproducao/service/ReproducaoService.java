package com.devJJ.lista_reproducao.service;

import com.devJJ.lista_reproducao.model.Reproducao;
import com.devJJ.lista_reproducao.repository.ReproducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReproducaoService {

    @Autowired
    private ReproducaoRepository reproducaoRepository;



    public List<Reproducao> pegarReproducoes(){
        return reproducaoRepository.findAll();
    }

    public Optional<Reproducao> pegarReproducaoPeloNome(String nome){
        return reproducaoRepository.findByNome(nome);

    }

}
