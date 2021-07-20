package br.com.erombi.springdata.service;

import br.com.erombi.springdata.model.Cargo;
import br.com.erombi.springdata.model.Funcionario;
import br.com.erombi.springdata.model.UnidadeTrabalho;
import br.com.erombi.springdata.repository.CargoRepository;
import br.com.erombi.springdata.repository.FuncionarioRepository;
import br.com.erombi.springdata.repository.UnidadeTrabalhoRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Service
public class CRUDFuncionarioService {

    private boolean execute = true;

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public CRUDFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while (execute) {
            System.out.println("O que deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Novo");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    execute = false;
                    break;
            }
        }

    }

    private void deletar(Scanner scanner) {
        System.out.println("ID do Funcionario");
        Long id = scanner.nextLong();
        funcionarioRepository.deleteById(id);
    }

    private void atualizar(Scanner scanner) {
        System.out.println("ID do Funcionario");

        Long id = scanner.nextLong();

        System.out.println("Nova Nome do Funcionario");

        String nome = scanner.next();

        System.out.println("Nova CPF do Funcionario");

        String cpf = scanner.next();

        System.out.println("Nova salario do Funcionario");

        Double salario = scanner.nextDouble();


        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);

        funcionarioRepository.save(funcionario);
    }

    private void salvar(Scanner scanner) {
        boolean executando = true;
        int acao;

        System.out.println("Nome do Funcionario");

        String nome = scanner.next();

        System.out.println("CPF do Funcionario");

        String cpf = scanner.next();

        System.out.println("salario do Funcionario");

        Double salario = scanner.nextDouble();

        System.out.println("data do Funcionario");

        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Cargo id");

        Long idCargo = scanner.nextLong();

        Cargo cargo = new Cargo();
        cargo.setId(idCargo);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setUnidadesTrabalho(unidades(scanner));
        funcionario.setDataContratacao(localDate);
        funcionario.setCargo(cargo);

        funcionarioRepository.save(funcionario);
    }

    private Set<UnidadeTrabalho> unidades(Scanner scanner) {
        boolean executando = true;
        int acao;
        Set<UnidadeTrabalho> unidades = new HashSet<>();

        while (executando) {
            System.out.println("Escolha a ação");
            System.out.println("0 - Sair");
            System.out.println("1 - Adicionar");

            acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    System.out.println("Digite o ID da Unidade de trabalho");
                    Long idUnidade = scanner.nextLong();
                    Optional<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findById(idUnidade);
                    unidadeTrabalho.ifPresent(unidades::add);
                    break;
                default:
                    executando = false;
                    break;
            }

        }
        return unidades;
    }

    private void visualizar(Scanner scanner) {
        boolean lendo = true;
        int page = 0;
        int size = 10;
        String order = "id";

        System.out.println("Quantos registros por página quer ver ?");
        size = scanner.nextInt();

        System.out.println("Como você quer ordenar ?");
        order = scanner.next();

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, order);

        while (lendo) {

            Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
            funcionarios.forEach(System.out::println);

            System.out.println("Qual pagina quer ver ? (" + funcionarios.getPageable().getPageNumber() + "/" + funcionarios.getTotalPages() + ")");
            page = scanner.nextInt();

            pageable = PageRequest.of(page, size);
        }
    }
}
