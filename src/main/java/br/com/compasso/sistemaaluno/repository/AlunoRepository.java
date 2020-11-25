package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface AlunoRepository
        extends JpaRepository<Aluno, Long> {
}
