package model;

import javax.persistence.*;
import java.io.Serializable;

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
    private double metragem;
    @Column(name = "dormitorios")
    private Integer dormitorios;
    @Column(name = "banheiros")
    private Integer banheiros;
    @Column(name = "suites")
    private Integer suites;
    @Column(name = "vagas_garagem")
    private Integer vagasDeGaragem;
    @Column(name = "valor_sugerido")
    private double valorSugerido;
    @Column(name = "ativo")
    private boolean ativo;
    @Column(name = "observacoes")
    private String observacoes;

    public Imovel() { }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getMetragem() {
        return metragem;
    }

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public Integer getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(Integer dormitorios) {
        this.dormitorios = dormitorios;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getSuites() {
        return suites;
    }

    public void setSuites(Integer suites) {
        this.suites = suites;
    }

    public Integer getVagasDeGaragem() {
        return vagasDeGaragem;
    }

    public void setVagasDeGaragem(Integer vagasDeGaragem) {
        this.vagasDeGaragem = vagasDeGaragem;
    }

    public double getValorSugerido() {
        return valorSugerido;
    }

    public void setValorSugerido(double valorSugerido) {
        this.valorSugerido = valorSugerido;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
