package com.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer nascimento;

    private Integer falecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Autor(){}

    public Autor(String nome, Integer nascimento, Integer falecimento) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.falecimento = falecimento;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNascimento() {
        return nascimento;
    }

    public Integer getFalecimento() {
        return falecimento;
    }

}