package com.devJJ.lista_reproducao.repository;

import com.devJJ.lista_reproducao.model.Reproducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReproducaoRepository extends JpaRepository<Reproducao, Long> {
    Optional<Reproducao> findByNome(String nome);
}
