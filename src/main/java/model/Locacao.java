package model;

import javax.persistence.*;
import java.io.Serializable;
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
    private double valorDoAluguel;
    @Column(name = "multa")
    private double multa;
    @Column(name = "data_vencimento")
    private LocalDate dataDoVencimento;
    @Column(name = "inicio_contrato")
    private LocalDate inicioDoContrato;
    @Column(name = "final_contrato")
    private LocalDate finalDoContrato;
    @Column(name = "observacoes")
    private String observacoes;

    public Locacao() { }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorDoAluguel() {
        return valorDoAluguel;
    }

    public void setValorDoAluguel(double valorDoAluguel) {
        this.valorDoAluguel = valorDoAluguel;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public LocalDate getDataDoVencimento() {
        return dataDoVencimento;
    }

    public void setDataDoVencimento(LocalDate dataDoVencimento) {
        this.dataDoVencimento = dataDoVencimento;
    }

    public LocalDate getInicioDoContrato() {
        return inicioDoContrato;
    }

    public void setInicioDoContrato(LocalDate inicioDoContrato) {
        this.inicioDoContrato = inicioDoContrato;
    }

    public LocalDate getFinalDoContrato() {
        return finalDoContrato;
    }

    public void setFinalDoContrato(LocalDate finalDoContrato) {
        this.finalDoContrato = finalDoContrato;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}