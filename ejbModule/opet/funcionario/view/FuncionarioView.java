package opet.funcionario.view;

import java.util.List;

import opet.funcionario.controller.FuncionarioController;
import opet.funcionario.util.Leitor;
import opet.funcionario.vo.Diretor;
import opet.funcionario.vo.Funcionario;
import opet.funcionario.vo.Professor;
import opet.funcionario.vo.Secretario;

public class FuncionarioView {

	private FuncionarioController funcionarioController;

	public FuncionarioView() {
		funcionarioController = new FuncionarioController();
	}

	public void menu() {

		int op = 9;
		do {
			System.out.println(" --- MENU ---");
			System.out.println("1 - Incluir Funcionario");
			System.out.println("2 - Listar Funcionarios");
			System.out.println("9 - Sair");

			op = Leitor.readInt("Entre com a opcao desejada: ");
			switch (op) {
			case 1:
				incluir();
				break;
			case 2:
				listar();
				break;
			case 9:
				break;
			default:
				System.out.println("Opcaoo invalida");
			}
		} while (op != 9);
	}

	public void incluir() {

		String nome = Leitor.readString("Entre com a nome: ");
		double salario = Leitor.readDouble("Entre com a salario: ");
		int tipo = Leitor.readInt("Entre com o tipo: ");

		// fazer as validacoes de tela desses dados antes de criar o Funcionario

		Funcionario novoFuncionnario;

		if (tipo == 1) {
			novoFuncionnario = new Diretor(nome, salario);

		} else if (tipo == 2) {
			novoFuncionnario = new Secretario(nome, salario);
		} else {
			novoFuncionnario = new Professor(nome, salario);
		}

		if (funcionarioController.incluir(novoFuncionnario)) {
			System.out.println("Funcionario incluido");
		} else {
			System.out.println("Funcionario nao incluido");
		}

	}

	public void listar() {
		List<Funcionario> listaFuncionarios = funcionarioController.listarAll();

		if (listaFuncionarios == null) {
			System.out.println("erro");
		} else if (listaFuncionarios.isEmpty()) {
			System.out.println("lista vazia");
		} else {

			for (Funcionario funcionario : listaFuncionarios) {
		
				int tipo;
				if (funcionario instanceof Diretor) {
					tipo = 1;
				} else if (funcionario instanceof Secretario) {
					tipo = 2;
				} else {
					tipo = 3;
				}
				
				System.out.println("Nome: "+ funcionario.getNome() +
						" Salario: " + funcionario.getSalario() +
						" Tipo: " + tipo
						);
				
				System.out.println("-------");
			}
		}

	}

}
