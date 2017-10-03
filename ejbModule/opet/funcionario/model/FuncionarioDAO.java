package opet.funcionario.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.funcionario.vo.Diretor;
import opet.funcionario.vo.Funcionario;
import opet.funcionario.vo.Professor;
import opet.funcionario.vo.Secretario;

public class FuncionarioDAO implements IFuncionarioDAO {

	public FuncionarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean incluir(Funcionario funcionario) {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "aluno", "aluno");

			/*
			 * PreparedStatement sttm = connection.prepareStatement(
			 * "select * from funcionarios"); ResultSet rs =
			 * sttm.executeQuery();
			 * 
			 * while(rs.next()) { System.out.println(rs.getString("nome") +
			 * " - " + rs.getDouble("salario") + " - " + rs.getInt("tipo")); }
			 */

			PreparedStatement sttm = connection.prepareStatement("insert into funcionarios values(?,?,?)");
			sttm.setString(1, funcionario.getNome());
			sttm.setDouble(2, funcionario.getSalario());

			if (funcionario instanceof Diretor) {
				sttm.setInt(3, 1);
			} else if (funcionario instanceof Secretario) {
				sttm.setInt(3, 2);
			} else {
				sttm.setInt(3, 3);
			}

			sttm.executeUpdate();
			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException Except) {
			Except.printStackTrace();
			return false;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Funcionario> listarAll() {
		Connection connection = null;
		List <Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "aluno", "aluno");

			PreparedStatement sttm = connection.prepareStatement("select * from funcionarios");
			ResultSet rs = sttm.executeQuery();

			while (rs.next()) {
				Funcionario f;
				
				int tipo = rs.getInt("TIPO");
				String nome = rs.getString("NOME");
				double salario = rs.getDouble("SALARIO");
				if (tipo == 1) {
					f = new Diretor(nome, salario);
				}else if (tipo == 2) {
					f = new Secretario(nome, salario);
				}else {
					f = new Professor(nome, salario);
				}
		
				listaFuncionario.add(f);
			}
			
			return listaFuncionario;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException Except) {
			Except.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
