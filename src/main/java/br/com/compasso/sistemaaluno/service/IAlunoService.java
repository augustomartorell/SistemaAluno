package br.com.compasso.sistemaaluno.service;

import br.com.compasso.sistemaaluno.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAlunoService
        extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeAlunoContaining(String nomeAluno,
                                          Pageable pageable);

    Page<Aluno> findByNomeUsuarioContaining(String nomeUsuario,
                                            Pageable pageable);

}
