package br.csi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuario {

    private int id_user;
    private String nome;
    private String email;
    private LocalDate dt_nascimento;
    private String cpf;
    private String senha;

    public Usuario() {
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int idUser) {
        this.id_user = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDtNascimentoValue() {
        return this.dt_nascimento;
    }

    public String getDtNascimento() {
        if (dt_nascimento != null) {
            return dt_nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dt_nascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
