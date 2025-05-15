package com.atividade.backend.Service;

import com.atividade.backend.Model.ModelPessoa;
import com.atividade.backend.Repository.RepositoryPessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePessoa {

    private static final Logger logger = LoggerFactory.getLogger(ServicePessoa.class);

    @Autowired
    private RepositoryPessoa repositoryPessoa;

    public ModelPessoa criarPessoa(ModelPessoa pessoa) {
        logger.info("Criando pessoa: {}", pessoa.getNome());
        return repositoryPessoa.save(pessoa);
    }

    public ModelPessoa obterPessoa(Integer id) {
        logger.info("Buscando pessoa com id: {}", id);
        return repositoryPessoa.findById(id).orElseThrow(() -> {
            logger.error("Pessoa não encontrada com id: {}", id);
            return new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        });
    }

    public void deletarPessoa(Integer id) {
        logger.info("Deletando pessoa com id: {}", id);
        if (!repositoryPessoa.existsById(id)) {
            logger.error("Pessoa para deletar não encontrada com id: {}", id);
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + id);
        }
        repositoryPessoa.deleteById(id);
        logger.info("Pessoa deletada com sucesso, id: {}", id);
    }

    public ModelPessoa atualizarPessoa(ModelPessoa pessoa) {
        logger.info("Atualizando pessoa com id: {}", pessoa.getId());
        if (pessoa.getId() == null || !repositoryPessoa.existsById(pessoa.getId())) {
            logger.error("Pessoa para atualizar não encontrada ou id é null");
            throw new PessoaNotFoundException("Pessoa não encontrada com id: " + pessoa.getId());
        }
        return repositoryPessoa.save(pessoa);
    }

    public List<ModelPessoa> buscarPessoasPorNomeEIdade(String nome, int idade) {
        logger.info("Buscando pessoas com nome '{}' e idade maior que {}", nome, idade);
        return repositoryPessoa.findByNomeAndIdadeGreaterThan(nome, idade);
    }
}
