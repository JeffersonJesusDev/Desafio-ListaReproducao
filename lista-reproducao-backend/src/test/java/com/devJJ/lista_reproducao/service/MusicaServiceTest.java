package com.devJJ.lista_reproducao.service;

import com.devJJ.lista_reproducao.model.Musica;
import com.devJJ.lista_reproducao.repository.MusicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MusicaServiceTest {

    @InjectMocks
    private MusicaService musicaService;

    @Mock
    private MusicaRepository musicaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verTodasMusicas_deveRetornarListaDeMusicas() {
        List<Musica> musicas = new ArrayList<>();
        musicas.add(new Musica(1L, "Imagine", "John Lennon", "Imagine", "1971", "Rock"));
        musicas.add(new Musica(2L, "Bohemian Rhapsody", "Queen", "A Night at the Opera", "1975", "Rock"));

        when(musicaRepository.findAll()).thenReturn(musicas);

        List<Musica> resultado = musicaService.verTodasMusicas();

        assertEquals(2, resultado.size());
        verify(musicaRepository, times(1)).findAll();
    }

    @Test
    void salvarMusica_deveSalvarEDevolverMusica() {
        Musica musica = new Musica(1L, "Imagine", "John Lennon", "Imagine", "1971", "Rock");

        when(musicaRepository.save(musica)).thenReturn(musica);

        Musica resultado = musicaService.salvarMusica(musica);

        assertNotNull(resultado);
        assertEquals("Imagine", resultado.getTitulo());
        verify(musicaRepository, times(1)).save(musica);
    }

    @Test
    void salvarMusica_comTituloVazioDeveLancarExcecao() {
        Musica musica = new Musica(1L, "", "Artista", "Album", "2022", "Pop");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            musicaService.salvarMusica(musica);
        });

        assertEquals("O título a música não pode ser vázio.", exception.getMessage());
        verify(musicaRepository, times(0)).save(musica);
    }
}