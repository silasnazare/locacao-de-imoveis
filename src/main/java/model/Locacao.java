package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "valor_aluguel")
    private BigDecimal valorDoAluguel;
    @Column(name = "multa")
    private BigDecimal multa;
    @Column(name = "dia_vencimento")
    private Integer diaDoVencimento;
    @Column(name = "inicio_contrato")
    private LocalDate inicioDoContrato;
    @Column(name = "final_contrato")
    private LocalDate finalDoContrato;
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name = "observacoes")
    private String observacoes;

    @Override
    public Integer getId() {
        return id;
    }
}