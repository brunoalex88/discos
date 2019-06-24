package com.obruno.discos.repository;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Repositório de controle de Taxa de cashback.
 */

import com.obruno.discos.model.Taxa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface TaxaRepository extends CrudRepository<Taxa, Long> {

    public Optional<Taxa> findByGeneroAndDiaDaSemana(String genero, DayOfWeek diaDaSemana);

    public Optional<Taxa> findFirstByOrderByIdAsc();

}
