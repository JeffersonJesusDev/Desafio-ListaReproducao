package com.devJJ.lista_reproducao.repository;

import com.devJJ.lista_reproducao.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
