package com.obruno.discos.model;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Modelo que representa uma venda de discos.
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private BigDecimal total;

    @PositiveOrZero
    private BigDecimal cashback;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraVenda;

    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (dataVenda == null) {
            this.dataVenda = new Date();
        }
        if (dataHoraVenda == null) {
            this.dataHoraVenda = new Date();
        }
    }

}