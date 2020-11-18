package br.com.compasso.sistemaaluno.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.modelo.Sexo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AlunoForm {

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String nomeAluno;

    @NotNull
    private Sexo sexo;

    @NotNull
    @NotEmpty
    @Size(min = 11,
          max = 11)
    private String cpf;

    @NotNull
    @NotEmpty
    @Size(min = 10,
          max = 254)
    private String email;


    @Size(min = 8)
    private String telefone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNascimento;

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }


    public Aluno converter() {

        return new Aluno(nomeAluno,
                         sexo,
                         cpf,
                         email,
                         telefone,
                         dtNascimento);
    }

    @Override
    public String toString() {
        return new String("nome Aluno " + this.nomeAluno);
    }
}
