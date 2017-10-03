package opet.funcionario.model;

import java.util.List;

import opet.funcionario.vo.Funcionario;

public interface IFuncionarioDAO {

	public boolean incluir(Funcionario funcionario);

	public List<Funcionario> listarAll();
}
