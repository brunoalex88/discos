package com.obruno.discos.model;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Modelo que representa um item de venda de discos.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ITEM_VENDA")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Disco.class)
    private Disco disco;

    @ManyToOne
    @JsonIgnore
    private Venda venda;

    private Integer quantidade;

    private BigDecimal cashback;

}
