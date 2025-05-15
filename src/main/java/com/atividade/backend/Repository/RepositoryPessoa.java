package com.atividade.backend.Repository;

import com.atividade.backend.Model.ModelPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPessoa extends JpaRepository<ModelPessoa, Integer> {
    List<ModelPessoa> findByNomeAndIdadeGreaterThan(String nome, int idade);
}
