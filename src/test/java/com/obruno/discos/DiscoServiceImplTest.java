package com.obruno.discos;

import com.obruno.discos.model.Disco;
import com.obruno.discos.service.DiscoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class DiscoServiceImplTest {

    @Mock
    private DiscoServiceImpl discoService;

    @Test
    public void testarPesquisaPorGenero() {
        Page<Disco> discos = new PageImpl<>(Arrays.asList(new Disco()));

        Mockito.when(discoService.buscarPorGenero(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(discos);
        Assert.assertEquals(discos, discoService.buscarPorGenero("rock", 1, 1));
    }

}