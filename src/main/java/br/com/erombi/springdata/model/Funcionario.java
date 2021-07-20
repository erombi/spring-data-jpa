package br.com.erombi.springdata.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Double salario;
    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "funcionarios_unidades",
            joinColumns = @JoinColumn(name = "fk_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "fk_unidade")
    )
    private Set<UnidadeTrabalho> unidadesTrabalho = new HashSet<>();

    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String cpf, Double salario, LocalDate dataContratacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Set<UnidadeTrabalho> getUnidadesTrabalho() {
        return unidadesTrabalho;
    }

    public void setUnidadesTrabalho(Set<UnidadeTrabalho> unidadesTrabalho) {
        this.unidadesTrabalho = unidadesTrabalho;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", cargo=" + cargo +
                ", unidadesTrabalho=" + unidadesTrabalho +
                '}';
    }
}
