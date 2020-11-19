package br.com.compasso.sistemaaluno.controller.dto;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.modelo.Sexo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class AlunoDto {

    @ApiModelProperty(notes = "Identificador único para o aluno, dois alunos não podem ter o mesmo valor.",
                      example = "1",
                      required = true)
    private Long id;
    @ApiModelProperty(notes = "Nome completo do aluno",
                      example = "John Doe",
                      required = true)
    private String nomeAluno;
    @ApiModelProperty(notes = "Gênero do aluno",
                      required = true,
                      example = "MASCULINO")
    private Sexo sexo;
    @ApiModelProperty(notes = "CPF do aluno",
                      example = "01234567890",
                      required = true)
    private String cpf;
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
    private String nomeUsuario;

    public AlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nomeAluno = aluno.getNomeAluno();
        this.sexo = aluno.getSexo();
        this.cpf = aluno.getCpf();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.dtNascimento = aluno.getDtNascimento();
        this.nomeUsuario = aluno.getNomeUsuario();
    }

    public static Page<AlunoDto> converter(Page<Aluno> alunos) {
        return alunos.map(AlunoDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
