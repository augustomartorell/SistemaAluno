package br.com.compasso.sistemaaluno.controller.form;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.repository.AlunoRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel(description = "Dados Atualizáveis do aluno")
public class AtualizaAlunoForm {

    @NotEmpty
    @NotNull
    @Size(min = 5)
    @ApiModelProperty(notes = "Nome completo do aluno",
                      example = "John Doe",
                      required = true)
    private String nomeAluno;

    @NotEmpty
    @NotNull
    @Size(min = 10,
          max = 254)
    @ApiModelProperty(notes = "email de contato do aluno",
                      example = "johndoe@email.com",
                      required = true)
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8)
    @ApiModelProperty(notes = "telefone de contato, adicionar DDD ao número",
                      example = "48 2222-2222")
    private String telefone;

    @NotEmpty
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "data de nascimento no formato ISO yyyy-MM-dd",
                      example = "2000-01-01")
    private LocalDate dtNascimento;

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
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
        aluno.setEmail(this.email);
        aluno.setTelefone(this.telefone);
        aluno.setDtNascimento(this.dtNascimento);

        return aluno;
    }
}
