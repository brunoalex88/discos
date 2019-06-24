package com.obruno.discos.service;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Serviço responsável pelas regras na venda de discos.
 */

import com.obruno.discos.dto.ItemVendaDto;
import com.obruno.discos.exception.*;
import com.obruno.discos.model.Disco;
import com.obruno.discos.model.ItemVenda;
import com.obruno.discos.model.Taxa;
import com.obruno.discos.model.Venda;
import com.obruno.discos.repository.DiscoRepository;
import com.obruno.discos.repository.TaxaRepository;
import com.obruno.discos.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private TaxaRepository taxaRepository;

    @Override
    public Venda vender(List<ItemVendaDto> itemVendaDto) {

        if (itemVendaDto.isEmpty() || itemVendaDto.get(0).getId().equals(0L)) {
            throw new VendaEmptyException();
        }

        List<ItemVenda> itensVenda = new ArrayList<>();
        Venda venda = new Venda();

        itemVendaDto.forEach(x -> {
            Disco disco = discoRepository.findById(x.getId()).orElseThrow(() -> new DiscoNotFoundException(x.getId()));
            DayOfWeek diaDaSemana = LocalDate.now().getDayOfWeek();
            Taxa taxa = recuperarTaxa(disco.getGenero(), diaDaSemana);
            ItemVenda itemVenda = ItemVenda.builder()
                    .disco(disco)
                    .cashback(BigDecimal.valueOf(taxa
                            .getValorTaxa())
                            .multiply(disco.getValorDisco()))
                    .quantidade(x.getQuantidade())
                    .build();
            itensVenda.add(itemVenda);
        });

        venda.setItensVenda(itensVenda);
        venda.setTotal(calcularValorTotal(itensVenda));
        venda.setCashback(calcularCashbackTotal(itensVenda));
        return vendaRepository.save(venda);
    }

    @Override
    public Page<Venda> buscarPorDatas(Date dataInicial, Date dataFinal, int pagina, int registrosPorPagina) {
        if (dataFinal.before(dataInicial)) {
            throw new IntervalDateException();
        }
        Pageable pageable = PageRequest.of(pagina, registrosPorPagina, Sort.by("dataHoraVenda").descending());
        return vendaRepository.findByDataVendaBetween(dataInicial, dataFinal, pageable);
    }

    private Taxa recuperarTaxa(String genero, DayOfWeek diaDaSemana) {
        Optional<Taxa> taxa = taxaRepository.findByGeneroAndDiaDaSemana(genero, diaDaSemana);
        if (!taxa.isPresent()) {
            throw new TaxaNotFoundException(genero, diaDaSemana);
        }
        return taxa.get();
    }

    private BigDecimal calcularValorTotal(List<ItemVenda> itensDaVenda) {
        return itensDaVenda.stream()
                .map(itemVenda -> itemVenda.getDisco().getValorDisco().multiply(new BigDecimal(itemVenda.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularCashbackTotal(List<ItemVenda> itensDaVenda) {
        return itensDaVenda.stream()
                .map(itemDaVenda -> itemDaVenda.getCashback())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}