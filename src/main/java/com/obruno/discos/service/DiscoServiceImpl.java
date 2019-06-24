package com.obruno.discos.service;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Serviço com as regras de negócio para os discos.
 */

import com.obruno.discos.model.Disco;
import com.obruno.discos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoServiceImpl implements DiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private SpotifyWebApiService spotifyWebApiService;

    @Override
    public Page<Disco> buscarPorGenero(String genero, int pagina, int registrosPorPagina) {
        Pageable pageable = PageRequest.of(pagina, registrosPorPagina, Sort.by("album").ascending());
        return discoRepository.findAllByGenero(genero.toLowerCase(), pageable);
    }

    @Override
    public void salvarDiscos(List<Disco> discos) {
        discoRepository.saveAll(discos);
    }
}
