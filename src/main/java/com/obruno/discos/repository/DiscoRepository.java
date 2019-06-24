package com.obruno.discos.repository;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Repositório de controle de Disco
 */

import com.obruno.discos.model.Disco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscoRepository extends PagingAndSortingRepository<Disco, Long> {

    public Page<Disco> findAllByGenero(String genero, Pageable pageable);

    public Optional<Disco> findFirstByOrderByIdAsc();

}