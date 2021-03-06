package br.com.compasso.sistemaaluno.controller;

import br.com.compasso.sistemaaluno.controller.dto.AlunoDto;
import br.com.compasso.sistemaaluno.controller.dto.DetalhesAlunoDto;
import br.com.compasso.sistemaaluno.controller.form.AlunoForm;
import br.com.compasso.sistemaaluno.controller.form.AtualizaAlunoForm;
import br.com.compasso.sistemaaluno.model.Aluno;
import br.com.compasso.sistemaaluno.model.Sexo;
import br.com.compasso.sistemaaluno.service.IAlunoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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
    private IAlunoService alunoService;

    @GetMapping
    @Cacheable(value = "ListaDeAlunos")
    @ApiOperation(value = "Retorna lista de alunos",
                  notes = "Pode retorna lista de alunos selecionados pelos parâmetros nomeAluno ou nomeUsuario",
                  response = AlunoDto.class,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 500,
                         message = "Server error"),
            @ApiResponse(code = 404,
                         message = "Não há registros localizados."),
            @ApiResponse(code = 200,
                         message = "Ok")
    })
    public Page<AlunoDto> lista(
            @RequestParam(required = false)
                    String nomeAluno,
            @RequestParam(required = false)
                    String nomeUsuario,
            @PageableDefault(sort = "id")
                    Pageable pageable) {

        Page<Aluno> alunos;
        if (nomeAluno != null) alunos = alunoService.findByNomeAlunoContaining(nomeAluno,
                                                                               pageable);
        else if (nomeUsuario != null)
            alunos = alunoService.findByNomeUsuarioContaining(nomeUsuario,
                                                              pageable);
        else alunos = alunoService.findAll(pageable);
        return AlunoDto.converter(alunos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Detalha aluno",
                  notes = "Traz detalhes do aluno",
                  response = DetalhesAlunoDto.class)
    public ResponseEntity<DetalhesAlunoDto> detalhar(
            @PathVariable
                    Long id) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        return alunoOptional.map(aluno -> ResponseEntity.ok(new DetalhesAlunoDto(aluno)))
                            .orElseGet(() -> ResponseEntity.notFound()
                                                           .build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "ListaDeAlunos",
                allEntries = true)
    @ApiOperation(value = "Cadastra Aluno",
                  notes = "Cadastra novo aluno a base de dados",
                  response = AlunoDto.class,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 500,
                         message = "Server error"),
            @ApiResponse(code = 403,
                         message = "Acesso não autorizado."),
            @ApiResponse(code = 200,
                         message = "Aluno cadastrado")
    })
    public ResponseEntity<AlunoDto> cadastrar(
            @RequestBody
            @Valid AlunoForm form,
            UriComponentsBuilder uriBuilder) {
        Aluno aluno = form.converter();
        alunoService.save(aluno);

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
    @ApiOperation(value = "Atualiza aluno",
                  notes = "Atualiza dados do aluno pelo id selecionado",
                  response = AlunoDto.class,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 500,
                         message = "Server error"),
            @ApiResponse(code = 403,
                         message = "Acesso não autorizado."),
            @ApiResponse(code = 404,
                         message = "Não há registros localizados."),
            @ApiResponse(code = 200,
                         message = "Ok")
    })
    public ResponseEntity<AlunoDto> atualizar(
            @PathVariable
                    Long id,
            @RequestBody
                    AtualizaAlunoForm form) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);

        if (alunoOptional.isPresent()) {
            Aluno aluno = form.atualizar(id,
                                         alunoService);
            alunoService.save(aluno);
            return ResponseEntity.ok()
                                 .body(new AlunoDto(aluno));
        }
        return ResponseEntity.notFound()
                             .build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "ListaDeAlunos",
                allEntries = true)
    @ApiOperation(value = "Remove aluno",
                  notes = "Remove aluno da base dados pelo id selecionado.",
                  response = String.class,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 500,
                         message = "Server error"),
            @ApiResponse(code = 403,
                         message = "Acesso não autorizado."),
            @ApiResponse(code = 404,
                         message = "Não há registros localizados."),
            @ApiResponse(code = 200,
                         message = "Aluno removido com sucesso.")
    })
    public ResponseEntity<String> remover(
            @PathVariable
                    Long id) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        String msgRetorno;
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            if (aluno.getSexo() == Sexo.FEMININO)
                msgRetorno = "Aluna " + aluno.getNomeAluno() + " removida.";
            else msgRetorno = "Aluno " + aluno.getNomeAluno() + " removido.";
            alunoService.deleteById(id);
            return ResponseEntity.ok(msgRetorno);
        }

        return ResponseEntity.notFound()
                             .build();
    }
}
