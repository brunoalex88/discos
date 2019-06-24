package com.obruno.discos.dto;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Dto para inclusão de itens para a venda de discos
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVendaDto {

    @NotNull
    private Long id;

    @NonNull
    private Integer quantidade;

}
