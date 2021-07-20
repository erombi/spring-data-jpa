package br.com.erombi.springdata;

import br.com.erombi.springdata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private boolean execute = true;

	private final CRUDCargoService cargoService;
	private final CRUDUnidadeTrabalhoService unidadeTrabalhoService;
	private final CRUDFuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataApplication(CRUDCargoService cargoService, CRUDUnidadeTrabalhoService unidadeTrabalhoService, CRUDFuncionarioService funcionarioService,
								 RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.funcionarioService = funcionarioService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (execute) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorio dinamico");

			int action = scanner.nextInt();

			switch (action) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					execute = false;
					break;
			}
		}
	}
}
