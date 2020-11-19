package br.com.compasso.sistemaaluno.controller.dto;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.modelo.Sexo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DetalhesAlunoDto {

    private Long id;
    private String nomeAluno;
    private Sexo sexo;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dtNascimento;
    private int idade;
    private String nomeUsuario;

    public DetalhesAlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nomeAluno = aluno.getNomeAluno();
        this.sexo = aluno.getSexo();
        this.cpf = aluno.getCpf();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.dtNascimento = aluno.getDtNascimento();
        this.idade = (int) ChronoUnit.YEARS.between(this.dtNascimento,
                                                    LocalDate.now(ZoneId.of("America/Sao_Paulo")));
        this.nomeUsuario = aluno.getNomeUsuario();

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

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade() {
        return idade;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
