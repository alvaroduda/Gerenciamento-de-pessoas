package com.atividade.backend.Controller;

import com.atividade.backend.DTO.DtoPessoa;
import com.atividade.backend.Model.ModelPessoa;
import com.atividade.backend.Repository.RepositoryPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class ControllerPessoa {

    @Autowired
    private RepositoryPessoa repositoryPessoa;

    @PostMapping
    public ResponseEntity<DtoPessoa> criarPessoa(@RequestBody ModelPessoa pessoa) {
        ModelPessoa pessoaSalva = repositoryPessoa.save(pessoa);
        DtoPessoa dtoPessoa = new DtoPessoa(pessoa.getId(), pessoa.getNome(), pessoa.getCidade(), pessoa.getIdade(), pessoa.getCpf(), pessoa.getAltura(), pessoa.getWhatsapp());
        return ResponseEntity.ok(dtoPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoPessoa> obterPessoa(@PathVariable Long id) {
        ModelPessoa pessoa = repositoryPessoa.findById(Math.toIntExact(id)).orElse(null);

        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }

        DtoPessoa dtoPessoa = new DtoPessoa(pessoa.getId(), pessoa.getNome(), pessoa.getCidade(), pessoa.getIdade(), pessoa.getCpf(), pessoa.getAltura(), pessoa.getWhatsapp());
        return ResponseEntity.ok(dtoPessoa);
    }
}