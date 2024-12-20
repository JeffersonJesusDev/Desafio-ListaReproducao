package com.devJJ.lista_reproducao.controller;

import com.devJJ.lista_reproducao.model.Musica;
import com.devJJ.lista_reproducao.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;


    @GetMapping
    public ResponseEntity<List<Musica>> getAllMusicas() {
        try {
            List<Musica> musicas = musicaService.verTodasMusicas();
            return ResponseEntity.ok(musicas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping
    public ResponseEntity<Musica> saveMusica(@RequestBody Musica musica) {
        try {
            Musica novaMusica = musicaService.salvarMusica(musica);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaMusica);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
