package com.devJJ.lista_reproducao.service;

import com.devJJ.lista_reproducao.exception.ResourceNotFoundException;
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

    public Optional<Reproducao> pegarReproducaoPeloNome(String nome) {
        return Optional.ofNullable(reproducaoRepository.findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Reprodução com o nome '" + nome + "' não encontrada.")));
    }

    public void deletarReproducao(Reproducao reproducao){
        reproducaoRepository.delete(reproducao);
    }

    public void deletarReproducaoPeloNome(String nome) {
        Reproducao reproducao = pegarReproducaoPeloNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Reprodução com o nome '" + nome + "' não encontrada."));
        reproducaoRepository.delete(reproducao);
    }

    public Reproducao salvarReproducao(Reproducao reproducao){
        return reproducaoRepository.save(reproducao);
    }

}
