package br.com.erombi.springdata.model;

import javax.persistence.*;

@Entity
@Table(name = "unidades_trabalho")
public class UnidadeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String endereco;

    public UnidadeTrabalho() {
    }

    public UnidadeTrabalho(Long id, String descricao, String endereco) {
        this.id = id;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "UnidadeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
