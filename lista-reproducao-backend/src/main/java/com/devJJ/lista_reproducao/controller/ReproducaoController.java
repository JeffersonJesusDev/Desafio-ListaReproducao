package com.devJJ.lista_reproducao.controller;

import com.devJJ.lista_reproducao.model.Reproducao;
import com.devJJ.lista_reproducao.service.ReproducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/list")
public class ReproducaoController {

    @Autowired
    private ReproducaoService reproducaoService;

    @GetMapping
    public ResponseEntity<List<Reproducao>> listarReproducao() {
        reproducaoService.pegarReproducoes();
        return ResponseEntity.ok(reproducaoService.pegarReproducoes());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Reproducao> getPlaylistByName(@PathVariable String nome) {
        Optional<Reproducao> optionalReproducao = reproducaoService.pegarReproducaoPeloNome(nome);

        if (optionalReproducao.isPresent()) {
            return ResponseEntity.ok(optionalReproducao.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
