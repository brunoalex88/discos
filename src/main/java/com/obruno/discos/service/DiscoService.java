package com.obruno.discos.service;

import com.obruno.discos.model.Disco;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiscoService {

    Page<Disco> buscarPorGenero(String genero, int page, int recordsByPage);
    void salvarDiscos(List<Disco> discos);

}
