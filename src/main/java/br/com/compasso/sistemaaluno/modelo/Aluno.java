package br.com.compasso.sistemaaluno.modelo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ApiModel(description = "Classe que define objeto Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador único para o aluno, dois alunos não podem ter o mesmo valor.",
                      example = "1",
                      required = true)
    private Long id;
    @ApiModelProperty(notes = "Nome completo do aluno",
                      example = "John Doe",
                      required = true)
    private String nomeAluno;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(notes = "Gênero do aluno",
                      required = true,
                      example = "MASCULINO")
    private Sexo sexo;

    @Column(unique = true)
    @ApiModelProperty(notes = "CPF do aluno",
                      example = "01234567890",
                      required = true)
    private String cpf;
    @Column(unique = true)
    @ApiModelProperty(notes = "email para contato",
                      example = "john.doe@email.com",
                      required = true)
    private String email;

    @ApiModelProperty(notes = "telefone de contato, adicionar DDD ao número",
                      example = "48 2222-2222")
    private String telefone;

    @ApiModelProperty(notes = "data de nascimento no formato ISO yyyy-MM-dd",
                      example = "2000-01-01")
    private LocalDate dtNascimento;

//    @OneToOne
//    @ApiModelProperty(notes = "registro de usuário para o aluno, cada aluno ter no máximo 1 registro",
//                      required = false,
//                      example = "john.doe")
//    private Usuario usuario;

    @ApiModelProperty(notes = "nome de usuario para aluno poder usar o sistema",
                      example = "john.doe")
    private String nomeUsuario;

    public Aluno() {
    }

    public Aluno(String nomeAuno,
                 Sexo sexo,
                 String cpf,
                 String email,
                 String telefone,
                 LocalDate dtNascimento) {
        this.nomeAluno = nomeAuno;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
    }

    public Aluno(String nomeAuno,
                 Sexo sexo,
                 String cpf,
                 String email,
                 String telefone,
                 LocalDate dtNascimento,
//                 Usuario usuario,
                 String nomeUsuario) {
        this.nomeAluno = nomeAuno;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
//        this.usuario = usuario;
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ?
                                   0 :
                                   id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
