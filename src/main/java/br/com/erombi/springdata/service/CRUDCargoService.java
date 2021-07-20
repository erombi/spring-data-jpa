package br.com.erombi.springdata.service;

import br.com.erombi.springdata.model.Cargo;
import br.com.erombi.springdata.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CRUDCargoService {

    private boolean execute = true;

    private final CargoRepository cargoRepository;

    public CRUDCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
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
                    visualizar();
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
        System.out.println("ID do Cargo");
        Long id = scanner.nextLong();
        cargoRepository.deleteById(id);
    }

    private void atualizar(Scanner scanner) {
        System.out.println("ID do cargo");

        Long id = scanner.nextLong();

        System.out.println("Nova Descrição do cargo");

        String descricao = scanner.next();

        System.out.println("DESCRICAO = "+ descricao);

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);

    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do cargo");

        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
    }

    private void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }
}
