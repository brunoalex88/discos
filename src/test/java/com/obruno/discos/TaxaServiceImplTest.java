package com.obruno.discos;

import com.obruno.discos.model.Taxa;
import com.obruno.discos.repository.TaxaRepository;
import com.obruno.discos.service.TaxaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class TaxaServiceImplTest {

    @Mock
    private TaxaRepository taxaRepository;

    @InjectMocks
    private TaxaServiceImpl taxaService;

    @Test
    public void testarCriacaoTaxas() {
        taxaService.criarTaxas();
    }

}
