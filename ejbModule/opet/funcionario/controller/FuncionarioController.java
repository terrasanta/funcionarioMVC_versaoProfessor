package opet.funcionario.controller;

import java.util.List;

import opet.funcionario.model.FuncionarioModel;
import opet.funcionario.vo.Funcionario;

public class FuncionarioController {

	private FuncionarioModel funcionarioModel;
	
	public FuncionarioController() {
		funcionarioModel = new FuncionarioModel();
	}

	public boolean incluir(Funcionario funcionario) {
		return funcionarioModel.incluir(funcionario);
	}
	
	public List<Funcionario> listarAll() {
		return funcionarioModel.listarAll();
	}
	
}
