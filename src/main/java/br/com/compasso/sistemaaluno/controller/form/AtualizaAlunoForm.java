package br.com.compasso.sistemaaluno.controller.form;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.modelo.Sexo;
import br.com.compasso.sistemaaluno.repository.AlunoRepository;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class AtualizaAlunoForm {

    @NotEmpty
    @NotNull
    @Size(min = 5)
    private String nomeAluno;

    @NotEmpty
    @NotNull
    private Sexo sexo;

    @NotEmpty
    @NotNull
    @Size(min = 11,
          max = 11)
    private String cpf;

    @NotEmpty
    @NotNull
    @Size(min = 10,
          max = 254)
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8)
    private String telefone;

    @NotEmpty
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNascimento;

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Aluno atualizar(Long id,
                           @NotNull AlunoRepository alunoRepository) {
        Aluno aluno = alunoRepository.getOne(id);

        aluno.setNomeAluno(this.nomeAluno);
        aluno.setSexo(this.sexo);
        aluno.setCpf(this.cpf);
        aluno.setEmail(this.email);
        aluno.setTelefone(this.telefone);
        aluno.setDtNascimento(this.dtNascimento);

        return aluno;
    }
}
