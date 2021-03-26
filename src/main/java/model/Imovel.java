package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "imovel")
public class Imovel implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "metragem")
    private BigDecimal metragem;
    @Column(name = "dormitorios")
    private Integer dormitorios;
    @Column(name = "banheiros")
    private Integer banheiros;
    @Column(name = "suites")
    private Integer suites;
    @Column(name = "vagas_garagem")
    private Integer vagasDeGaragem;
    @Column(name = "valor_sugerido")
    private BigDecimal valorSugerido;
    @Column(name = "observacoes")
    private String observacoes;

    @Override
    public Integer getId() {
        return id;
    }
}
