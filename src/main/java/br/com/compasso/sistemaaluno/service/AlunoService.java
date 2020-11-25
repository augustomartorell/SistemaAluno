package br.com.compasso.sistemaaluno.service;

import br.com.compasso.sistemaaluno.model.Aluno;
import br.com.compasso.sistemaaluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public abstract class AlunoService
        implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Page<Aluno> findByNomeAlunoContaining(String nomeAluno,
                                                 Pageable pageable) {
        return alunoRepository.findByNomeAlunoContaining(nomeAluno, pageable);
    }

    @Override
    public Page<Aluno> findByNomeUsuarioContaining(String nomeUsuario,
                                                   Pageable pageable) {
        return alunoRepository.findByNomeUsuarioContaining(nomeUsuario, pageable);
    }
}
