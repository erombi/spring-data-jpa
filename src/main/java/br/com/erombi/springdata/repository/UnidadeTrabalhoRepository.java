package br.com.erombi.springdata.repository;

import br.com.erombi.springdata.model.Funcionario;
import br.com.erombi.springdata.model.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Long> {
}
