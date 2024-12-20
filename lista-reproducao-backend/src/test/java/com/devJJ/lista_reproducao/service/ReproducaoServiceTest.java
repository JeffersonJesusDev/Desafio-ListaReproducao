package com.devJJ.lista_reproducao.service;

import com.devJJ.lista_reproducao.model.Reproducao;
import com.devJJ.lista_reproducao.repository.ReproducaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReproducaoServiceTest {

    @InjectMocks
    private ReproducaoService reproducaoService;

    @Mock
    private ReproducaoRepository reproducaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pegarReproducoes_deveRetornarListaDeReproducoes() {
        List<Reproducao> reproducoes = new ArrayList<>();
        reproducoes.add(new Reproducao(1L, "Lista 1", "Descrição 1"));
        reproducoes.add(new Reproducao(2L, "Lista 2", "Descrição 2"));

        when(reproducaoRepository.findAll()).thenReturn(reproducoes);

        List<Reproducao> resultado = reproducaoService.pegarReproducoes();

        assertEquals(2, resultado.size());
        verify(reproducaoRepository, times(1)).findAll();
    }

    @Test
    void pegarReproducaoPeloNome_deveRetornarReproducao() {
        String nome = "Lista Favorita";
        Reproducao reproducao = new Reproducao(1L, nome, "Descrição favorita");

        when(reproducaoRepository.findByNome(nome)).thenReturn(Optional.of(reproducao));

        Optional<Reproducao> resultado = reproducaoService.pegarReproducaoPeloNome(nome);

        assertTrue(resultado.isPresent());
        assertEquals(nome, resultado.get().getNome());
        verify(reproducaoRepository, times(1)).findByNome(nome);
    }

    @Test
    void deletarReproducao_deveChamarMetodoDelete() {
        Reproducao reproducao = new Reproducao(1L, "Lista Favorita", "Descrição");

        doNothing().when(reproducaoRepository).delete(reproducao);

        reproducaoService.deletarReproducao(reproducao);

        verify(reproducaoRepository, times(1)).delete(reproducao);
    }

    @Test
    void salvarReproducao_deveSalvarEDevolverReproducao() {
        Reproducao reproducao = new Reproducao(1L, "Nova Lista", "Descrição Nova");

        when(reproducaoRepository.save(reproducao)).thenReturn(reproducao);

        Reproducao resultado = reproducaoService.salvarReproducao(reproducao);

        assertNotNull(resultado);
        assertEquals("Nova Lista", resultado.getNome());
        verify(reproducaoRepository, times(1)).save(reproducao);
    }
}
