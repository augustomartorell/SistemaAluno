package br.com.compasso.sistemaaluno.repository;

import br.com.compasso.sistemaaluno.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface AlunoRepository
        extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeAlunoContaining(String nomeAluno,
                                          Pageable pageable);

    Page<Aluno> findByNomeUsuarioContaining(String nomeUsuario,
                                            Pageable pageable);


}
