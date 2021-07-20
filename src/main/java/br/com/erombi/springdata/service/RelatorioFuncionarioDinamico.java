package br.com.erombi.springdata.service;

import br.com.erombi.springdata.model.Funcionario;
import br.com.erombi.springdata.repository.FuncionarioRepository;
import br.com.erombi.springdata.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite um nome");
        String nome = scanner.next();

        if (nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite um cpf");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite um salario");
        Double salario = scanner.nextDouble();

        if (salario == 0) {
            salario = null;
        }

        System.out.println("Digite a data de contratacao");
        String data = scanner.next();
        LocalDate localDate;
        if (data.equalsIgnoreCase("NULL")) {
            localDate = null;
        } else {
            localDate = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
                        .where(
                                SpecificationFuncionario.nome(nome))
                                .or(SpecificationFuncionario.cpf(cpf))
                                .or(SpecificationFuncionario.dataContratacao(localDate))
                                .or(SpecificationFuncionario.salario(salario))
                        );

        funcionarios.forEach(System.out::println);
    }
}
