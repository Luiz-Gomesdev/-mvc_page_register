package br.com.gft.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.projeto.entities.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}
