package com.devJJ.lista_reproducao.controller;

import com.devJJ.lista_reproducao.model.Musica;
import com.devJJ.lista_reproducao.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Musica>> pegarTodasMusicas() {
        return ResponseEntity.ok(musicaService.verTodasMusicas());
    }

    @PostMapping
    public ResponseEntity<Musica> salvarMusica(@RequestBody Musica musica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicaService.salvarMusica(musica));
    }

}
