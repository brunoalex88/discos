package com.obruno.discos.repository;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Repositório de controle de Vendas de disco.
 */

import com.obruno.discos.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VendaRepository extends PagingAndSortingRepository<Venda, Long> {

    public Page<Venda> findByDataVendaBetween(Date dataInicial, Date dataFinal, Pageable pageable);

}
