package br.com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.projeto.entities.Linguagem;

@Repository
public interface LinguagemRepository extends JpaRepository<Linguagem, Long>{

}
