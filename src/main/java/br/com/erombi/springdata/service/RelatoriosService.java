package br.com.erombi.springdata.service;

import br.com.erombi.springdata.model.Funcionario;
import br.com.erombi.springdata.model.FuncionarioProjecao;
import br.com.erombi.springdata.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private boolean execute = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Pageable pageable = Pageable.ofSize(10);

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while (execute) {
            System.out.println("O que deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario por nome");
            System.out.println("2 - Busca funcionario por nome, Salario e data");
            System.out.println("3 - Busca funcionario por Data Contratacao");
            System.out.println("4 - Busca funcionario Salario");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacaoMaior(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    execute = false;
                    break;
            }
        }

    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar ?");
        String nome = scanner.next();

        Page<Funcionario> funcionarios = funcionarioRepository.findByNome(nome, pageable);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {

        System.out.println("Qual nome deseja pesquisar ?");
        String nome = scanner.next();

        System.out.println("Qual data contratacao deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salario deseja pesquisar ?");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);

        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacaoMaior(Scanner scanner) {
        System.out.println("Qual data contratacao deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> funcionarioSalario = funcionarioRepository.findFuncionarioSalario();
        funcionarioSalario.forEach(f -> System.out.println("Funcionario: id= " + f.getId()
                                                            + " nome= " + f.getNome()
                                                            + " salario= " + f.getSalario()));
    }
}
