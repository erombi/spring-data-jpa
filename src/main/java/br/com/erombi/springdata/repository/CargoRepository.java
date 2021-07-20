package br.com.erombi.springdata.repository;

import br.com.erombi.springdata.model.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
