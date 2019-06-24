package com.obruno.discos.service;

import com.obruno.discos.dto.ItemVendaDto;
import com.obruno.discos.model.Venda;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface VendaService {

    public Venda vender(List<ItemVendaDto> itemVendaDto);
    public Page<Venda> buscarPorDatas(Date dataInicial, Date dataFinal, int pagina, int registrosPorPagina);

}
