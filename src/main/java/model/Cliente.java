package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "telefone", unique = true)
    private String telefone;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "data_nascimento")
    private LocalDate dataDeNascimento;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
