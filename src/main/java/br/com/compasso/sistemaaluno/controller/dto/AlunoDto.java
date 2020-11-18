package br.com.compasso.sistemaaluno.controller.dto;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.modelo.Sexo;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoDto {

    private Long id;
    private String nomeAluno;
    private Sexo sexo;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dtNascimento;

    public AlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nomeAluno = aluno.getNomeAluno();
        this.sexo = aluno.getSexo();
        this.cpf = aluno.getCpf();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.dtNascimento = aluno.getDtNascimento();
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

    public static Page<AlunoDto> converter(Page<Aluno> alunos) {
        return alunos.map(AlunoDto::new);
    }
}
