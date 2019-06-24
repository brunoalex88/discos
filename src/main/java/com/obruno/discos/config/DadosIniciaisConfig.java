package com.obruno.discos.config;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Valida se os registro de Taxas de Cashback e Discos estão cadastradas.
 * Essas validações são executadas após o refresh do contexto do Spring ao iniciar
 */

import com.obruno.discos.repository.DiscoRepository;
import com.obruno.discos.repository.TaxaRepository;
import com.obruno.discos.service.SpotifyWebApiService;
import com.obruno.discos.service.TaxaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DadosIniciaisConfig {

    @Autowired
    private TaxaRepository taxaRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private TaxaService taxaService;

    @Autowired
    private SpotifyWebApiService spotifyWebApiService;

    @Value("${spotify.token}")
    private String spotifyToken;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRecords()) {
            taxaService.criarTaxas();
        }

        if (!hasDiscos()) {
            spotifyWebApiService.criarDiscosSpotify(spotifyToken);
        }
    }

    /**
     * Verifica se já existem Taxas de Cashback cadastradas.
     * @return boolean
     */
    private boolean hasRecords() {
        return taxaRepository.findFirstByOrderByIdAsc().isPresent();
    }

    /**
     * Verifica se já existem discos cadastrados.
     * @return boolean
     */
    private boolean hasDiscos() {
        return discoRepository.findFirstByOrderByIdAsc().isPresent();
    }

}
