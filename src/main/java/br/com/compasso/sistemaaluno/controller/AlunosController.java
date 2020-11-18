package br.com.compasso.sistemaaluno.controller;

import br.com.compasso.sistemaaluno.controller.dto.AlunoDto;
import br.com.compasso.sistemaaluno.controller.dto.DetalhesAlunoDto;
import br.com.compasso.sistemaaluno.controller.form.AlunoForm;
import br.com.compasso.sistemaaluno.controller.form.AtualizaAlunoForm;
import br.com.compasso.sistemaaluno.modelo.Aluno;
import br.com.compasso.sistemaaluno.repository.AlunoRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    @Cacheable(value = "ListaDeAlunos")
    @ApiOperation(value = "Consulta todos os alunos ou consulta os com nome similar ao enviado por parametro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "")
    })
    public Page<AlunoDto> lista(
            @RequestParam(required = false)
                    String nomeAluno,
            @RequestParam(required = false)
                    String nomeUsuario,
            @PageableDefault(sort = "nomeAluno")
                    Pageable paginacao) {

        Page<Aluno> alunos;
        if (nomeAluno == null) {
            alunos = alunoRepository.findAll(paginacao);
        } else {
            alunos = alunoRepository.findByNomeAlunoContaining(nomeAluno,
                                                               paginacao);
        }
        return AlunoDto.converter(alunos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Traz aluno selecionado pelo seu ID",
                  response = Aluno.class)
    public ResponseEntity<DetalhesAlunoDto> detalhar(
            @PathVariable
                    Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        return alunoOptional.map(aluno -> ResponseEntity.ok(new DetalhesAlunoDto(aluno)))
                            .orElseGet(() -> ResponseEntity.notFound()
                                                           .build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "ListaDeAlunos",
                allEntries = true)
    public ResponseEntity<AlunoDto> cadastrar(
            @RequestBody
            @Valid AlunoForm form,
            UriComponentsBuilder uriBuilder) {
        Aluno aluno = form.converter();
        alunoRepository.save(aluno);

        URI uri = uriBuilder.path("/alunos/{id}")
                            .buildAndExpand(aluno.getId())
                            .toUri();
        return ResponseEntity.created(uri)
                             .body(new AlunoDto(aluno));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ListaDeAlunos",
                allEntries = true)
    public ResponseEntity<AlunoDto> atualizar(
            @PathVariable
                    Long id,
            @RequestBody
                    AtualizaAlunoForm form) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = form.atualizar(id,
                                         alunoRepository);
            alunoRepository.save(aluno);
            return ResponseEntity.ok(new AlunoDto(aluno));
        }

        return ResponseEntity.notFound()
                             .build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ListaDeAlunos",
                allEntries = true)
    public ResponseEntity<?> remover(
            @PathVariable
                    Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.ok()
                                 .build();
        }

        return ResponseEntity.notFound()
                             .build();
    }
}
