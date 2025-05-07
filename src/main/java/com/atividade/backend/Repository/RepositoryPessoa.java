package com.atividade.backend.Repository;

import com.atividade.backend.Model.ModelPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPessoa extends JpaRepository <ModelPessoa, Integer> {
}
