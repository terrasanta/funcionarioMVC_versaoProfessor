package opet.funcionario.model;

import java.util.List;

import opet.funcionario.vo.Funcionario;

public class FuncionarioModel implements IFuncionarioDAO {

	private FuncionarioDAO funcionarioDAO;

	public FuncionarioModel() {
		funcionarioDAO = new FuncionarioDAO();
	}

	@Override
	public boolean incluir(Funcionario funcionario) {
		return funcionarioDAO.incluir(funcionario);
	}

	@Override
	public List<Funcionario> listarAll() {
		return funcionarioDAO.listarAll();
	}

}
