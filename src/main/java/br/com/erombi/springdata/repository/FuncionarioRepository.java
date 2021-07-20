package br.com.erombi.springdata.repository;

import br.com.erombi.springdata.model.Cargo;
import br.com.erombi.springdata.model.Funcionario;
import br.com.erombi.springdata.model.FuncionarioProjecao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {

    Page<Funcionario> findByNome(String nome, Pageable pageable);

    @Query(
            "SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data"
    )
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

    @Query(value = "SELECT * FROM FUNCIONARIOS F WHERE F.DATA_CONTRATACAO >= :data",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id id, f.nome nome, f.salario salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();


}
