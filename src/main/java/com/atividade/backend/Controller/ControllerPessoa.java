package com.atividade.backend.Controller;

import com.atividade.backend.DTO.DtoPessoa;
import com.atividade.backend.Model.ModelPessoa;
import com.atividade.backend.Service.ServicePessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class ControllerPessoa {

    @Autowired
    private ServicePessoa servicePessoa;

    @PostMapping
    public ResponseEntity<DtoPessoa> criarPessoa(@RequestBody ModelPessoa pessoa) {
        ModelPessoa pessoaSalva = servicePessoa.criarPessoa(pessoa);
        DtoPessoa dtoPessoa = new DtoPessoa(pessoaSalva.getId(), pessoaSalva.getNome(), pessoaSalva.getCidade(), pessoaSalva.getIdade(), pessoaSalva.getCpf(), pessoaSalva.getAltura(), pessoaSalva.getWhatsapp());
        return ResponseEntity.ok(dtoPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoPessoa> obterPessoa(@PathVariable Integer id) {
        ModelPessoa pessoa = servicePessoa.obterPessoa(id);

        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }

        DtoPessoa dtoPessoa = new DtoPessoa(pessoa.getId(), pessoa.getNome(), pessoa.getCidade(), pessoa.getIdade(), pessoa.getCpf(), pessoa.getAltura(), pessoa.getWhatsapp());
        return ResponseEntity.ok(dtoPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
        servicePessoa.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DtoPessoa> atualizarPessoa(@RequestBody ModelPessoa pessoa) {
        ModelPessoa pessoaAtualizada = servicePessoa.atualizarPessoa(pessoa);
        DtoPessoa dtoPessoa = new DtoPessoa(pessoaAtualizada.getId(), pessoaAtualizada.getNome(), pessoaAtualizada.getCidade(), pessoaAtualizada.getIdade(), pessoaAtualizada.getCpf(), pessoaAtualizada.getAltura(), pessoaAtualizada.getWhatsapp());
        return ResponseEntity.ok(dtoPessoa);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DtoPessoa>> buscarPessoas(@RequestParam String nome, @RequestParam int idade) {
        List<ModelPessoa> pessoas = servicePessoa.buscarPessoasPorNomeEIdade(nome, idade);
        List<DtoPessoa> dtos = pessoas.stream()
                .map(p -> new DtoPessoa(p.getId(), p.getNome(), p.getCidade(), p.getIdade(), p.getCpf(), p.getAltura(), p.getWhatsapp()))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
