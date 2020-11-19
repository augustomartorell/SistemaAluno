package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.modelo.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public
interface AlunoRepository
        extends JpaRepository<Aluno, Long> {
    Page<Aluno> findByNomeAlunoContaining(String nomeAluno,
                                          Pageable paginacao);

    Page<Aluno> findByNomeUsuarioContaining(String nomeUsuario,
                                            Pageable paginacao);
}
