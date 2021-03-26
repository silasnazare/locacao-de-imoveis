package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
public class Aluguel implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "valor_pago")
    private BigDecimal valorPago;
    @Column(name = "data_pagamento")
    private LocalDate dataDePagamento;
    @Column(name = "observacoes")
    private String observacoes;

    @Override
    public Integer getId() {
        return id;
    }
}
