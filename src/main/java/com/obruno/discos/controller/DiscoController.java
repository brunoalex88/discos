package com.obruno.discos.controller;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Controller principal da API
 */

import com.obruno.discos.dto.ItemVendaDto;
import com.obruno.discos.exception.DiscoNotFoundException;
import com.obruno.discos.exception.VendaNotFoundException;
import com.obruno.discos.model.Disco;
import com.obruno.discos.model.Venda;
import com.obruno.discos.repository.DiscoRepository;
import com.obruno.discos.repository.VendaRepository;
import com.obruno.discos.service.DiscoService;
import com.obruno.discos.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class DiscoController {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private DiscoService discoService;

    @Autowired
    private VendaService vendaService;

    @GetMapping("/disco")
    public Page<Disco> buscaDiscosPorGenero(@RequestParam String genero, @RequestParam int pagina, @RequestParam int registrosPorPagina) {
        Page<Disco> discos = discoService.buscarPorGenero(genero, pagina, registrosPorPagina);
        if (discos.isEmpty()) {
            throw new DiscoNotFoundException();
        }
        return discos;
    }

    @GetMapping("/disco/{id}")
    public Disco buscaDiscosPorId(@PathVariable Long id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (!disco.isPresent()) {
            throw new DiscoNotFoundException(id);
        }
        return disco.get();
    }

    @GetMapping("/venda")
    public Page<Venda> buscaVendasPorDatas(@RequestParam Date dataInicial, @RequestParam Date dataFinal, @RequestParam int pagina, @RequestParam int registrosPorPagina) {
        Page<Venda> vendas = vendaService.buscarPorDatas(dataInicial, dataFinal, pagina, registrosPorPagina);
        if (vendas.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            throw new VendaNotFoundException(simpleDateFormat.format(dataInicial), simpleDateFormat.format(dataFinal));
        }
        return vendas;
    }

    @GetMapping("/venda/{id}")
    public Venda buscaVendaPorId(@PathVariable Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        if (!venda.isPresent()) {
            throw  new VendaNotFoundException(id);
        }
        return venda.get();
    }

    @PostMapping("/venda")
    public Venda registrarVenda(@RequestBody @Valid List<ItemVendaDto> itemVendaDto) {
        return vendaService.vender(itemVendaDto);
    }

}