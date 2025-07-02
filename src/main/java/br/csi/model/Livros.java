package br.csi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livros {
    private int id_livro;
    private String nome;
    private String autor;
    private LocalDate dt_inicio;
    private LocalDate dt_final;
    private int num_paginas;
    private String genero;
    private int id_user; // Associa com Usuario

    public Livros() {
    }

    public int getIdLivro() {
        return id_livro;
    }

    public void setIdLivro(int idLivro) {
        this.id_livro = idLivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDtInicioValue() {
        return this.dt_inicio;
    }

    public String getDtInicio() {
        if (dt_inicio != null) {
            return dt_inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dt_inicio = dtInicio;
    }

    public LocalDate getDtFinalValue() {
        return this.dt_final;
    }

    public String getDtFinal() {
        if (dt_final != null) {
            return dt_final.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setDtFinal(LocalDate dtFinal) {
        this.dt_final = dtFinal;
    }

    public int getNumPaginas() {
        return num_paginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.num_paginas = numPaginas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int idUser) {
        this.id_user = idUser;
    }
}
