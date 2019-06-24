package com.obruno.discos.service;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Serviço para controle das Taxas de cashback.
 */

import com.obruno.discos.model.Taxa;
import com.obruno.discos.repository.TaxaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class TaxaServiceImpl implements TaxaService {
    private static final String POP = "pop";
    private static final String MPB = "mpb";
    private static final String CLASSIC = "classic";
    private static final String ROCK = "rock";

    @Autowired
    private TaxaRepository rateRepository;

    @Override
    public void criarTaxas() {
        log.info("Creating rates..");
        Taxa taxaPopDomingo = Taxa.builder().diaDaSemana(DayOfWeek.SUNDAY).valorTaxa(0.25D).genero(POP).build();
        Taxa taxaPopSegunda = Taxa.builder().diaDaSemana(DayOfWeek.MONDAY).valorTaxa(0.07D).genero(POP).build();
        Taxa taxaPopTerca = Taxa.builder().diaDaSemana(DayOfWeek.TUESDAY).valorTaxa(0.06D).genero(POP).build();
        Taxa taxaPopQuarta = Taxa.builder().diaDaSemana(DayOfWeek.WEDNESDAY).valorTaxa(0.02D).genero(POP).build();
        Taxa taxaPopQuinta = Taxa.builder().diaDaSemana(DayOfWeek.THURSDAY).valorTaxa(0.10D).genero(POP).build();
        Taxa taxaPopSexta = Taxa.builder().diaDaSemana(DayOfWeek.FRIDAY).valorTaxa(0.15D).genero(POP).build();
        Taxa taxaPopSabado = Taxa.builder().diaDaSemana(DayOfWeek.SATURDAY).valorTaxa(0.20D).genero(POP).build();

        Taxa taxaMpbDomingo = Taxa.builder().diaDaSemana(DayOfWeek.SUNDAY).valorTaxa(0.30D).genero(MPB).build();
        Taxa taxaMpbSegunda = Taxa.builder().diaDaSemana(DayOfWeek.MONDAY).valorTaxa(0.05D).genero(MPB).build();
        Taxa taxaMpbTerca = Taxa.builder().diaDaSemana(DayOfWeek.TUESDAY).valorTaxa(0.10D).genero(MPB).build();
        Taxa taxaMpbQuarta = Taxa.builder().diaDaSemana(DayOfWeek.WEDNESDAY).valorTaxa(0.15D).genero(MPB).build();
        Taxa taxaMpbQuinta = Taxa.builder().diaDaSemana(DayOfWeek.THURSDAY).valorTaxa(0.20D).genero(MPB).build();
        Taxa taxaMpbSexta = Taxa.builder().diaDaSemana(DayOfWeek.FRIDAY).valorTaxa(0.25D).genero(MPB).build();
        Taxa taxaMpbSabado = Taxa.builder().diaDaSemana(DayOfWeek.SATURDAY).valorTaxa(0.30D).genero(MPB).build();

        Taxa taxaClassicDomingo = Taxa.builder().diaDaSemana(DayOfWeek.SUNDAY).valorTaxa(0.35D).genero(CLASSIC).build();
        Taxa taxaClassicSegunda = Taxa.builder().diaDaSemana(DayOfWeek.MONDAY).valorTaxa(0.03D).genero(CLASSIC).build();
        Taxa taxaClassicTerca = Taxa.builder().diaDaSemana(DayOfWeek.TUESDAY).valorTaxa(0.05D).genero(CLASSIC).build();
        Taxa taxaClassicQuarta = Taxa.builder().diaDaSemana(DayOfWeek.WEDNESDAY).valorTaxa(0.08D).genero(CLASSIC).build();
        Taxa taxaClassicQuinta = Taxa.builder().diaDaSemana(DayOfWeek.THURSDAY).valorTaxa(0.13D).genero(CLASSIC).build();
        Taxa taxaClassicSexta = Taxa.builder().diaDaSemana(DayOfWeek.FRIDAY).valorTaxa(0.18D).genero(CLASSIC).build();
        Taxa taxaClassicSabado = Taxa.builder().diaDaSemana(DayOfWeek.SATURDAY).valorTaxa(0.25D).genero(CLASSIC).build();

        Taxa taxaRockDomingo = Taxa.builder().diaDaSemana(DayOfWeek.SUNDAY).valorTaxa(0.40D).genero(ROCK).build();
        Taxa taxaRockSegunda = Taxa.builder().diaDaSemana(DayOfWeek.MONDAY).valorTaxa(0.10D).genero(ROCK).build();
        Taxa taxaRockTerca = Taxa.builder().diaDaSemana(DayOfWeek.TUESDAY).valorTaxa(0.15D).genero(ROCK).build();
        Taxa taxaRockQuarta = Taxa.builder().diaDaSemana(DayOfWeek.WEDNESDAY).valorTaxa(0.15D).genero(ROCK).build();
        Taxa taxaRockQuinta = Taxa.builder().diaDaSemana(DayOfWeek.THURSDAY).valorTaxa(0.15D).genero(ROCK).build();
        Taxa taxaRockSexta = Taxa.builder().diaDaSemana(DayOfWeek.FRIDAY).valorTaxa(0.20D).genero(ROCK).build();
        Taxa taxaRockSabado = Taxa.builder().diaDaSemana(DayOfWeek.SATURDAY).valorTaxa(0.40D).genero(ROCK).build();

        List<Taxa> taxas = Arrays.asList(taxaPopDomingo, taxaPopSegunda, taxaPopTerca, taxaPopQuarta, taxaPopQuinta, taxaPopSexta, taxaPopSabado,
                taxaMpbDomingo, taxaMpbSegunda, taxaMpbTerca, taxaMpbQuarta, taxaMpbQuinta, taxaMpbSexta, taxaMpbSabado,
                taxaClassicDomingo, taxaClassicSegunda, taxaClassicTerca, taxaClassicQuarta, taxaClassicQuinta, taxaClassicSexta, taxaClassicSabado,
                taxaRockDomingo, taxaRockSegunda, taxaRockTerca, taxaRockQuarta, taxaRockQuinta, taxaRockSexta, taxaRockSabado);

        rateRepository.saveAll(taxas);
        log.info("Rates created");
    }
}
