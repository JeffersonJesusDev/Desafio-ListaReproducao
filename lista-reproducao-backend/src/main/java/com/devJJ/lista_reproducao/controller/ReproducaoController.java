package com.devJJ.lista_reproducao.controller;

import com.devJJ.lista_reproducao.model.Reproducao;
import com.devJJ.lista_reproducao.service.ReproducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ReproducaoController {

    @Autowired
    private ReproducaoService reproducaoService;

    @PostMapping
    public ResponseEntity<Reproducao> salvarReproducao(@RequestBody Reproducao reproducao) {
        reproducaoService.salvarReproducao(reproducao);
        return new ResponseEntity<>(reproducao, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reproducao>> listarReproducao() {
        reproducaoService.pegarReproducoes();
        return ResponseEntity.ok(reproducaoService.pegarReproducoes());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Reproducao> pegarReproducaoPeloNome(@PathVariable String nome) {
        Optional<Reproducao> optionalReproducao = reproducaoService.pegarReproducaoPeloNome(nome);

        if (optionalReproducao.isPresent()) {
            return ResponseEntity.ok(optionalReproducao.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Reproducao> removerReproducao(@PathVariable Reproducao reproducao) {
        reproducaoService.deletarReproducao(reproducao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> removerReproducaoPeloNome(@PathVariable String nome) {
        reproducaoService.deletarReproducaoPeloNome(nome);
        return ResponseEntity.noContent().build();
    }


}
