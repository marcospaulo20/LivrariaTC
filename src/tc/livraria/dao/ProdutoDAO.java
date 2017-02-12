package tc.livraria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tc.livraria.model.Produto;
import tc.livraria.model.Produto.ProdutoBuilder;
import tc.livraria.util.ConexaoFabrica;
import tc.livraria.util.CriaTabela;

public class ProdutoDAO {

	private Connection conn = null;
	private Statement stmt = null;

	public ProdutoDAO() {
		this.conn = new ConexaoFabrica().getConexao();
		new CriaTabela().criarTabelaProduto();
	}

	public boolean novo(Produto produto) {
		boolean retorno = false;
		String query = "INSERT INTO produto (nome, ano, disponivel) VALUES (" 
				+ "'" + produto.getNome() + "', " + produto.getAno() + ", " + (produto.getDisponivel() ? 1 : 0) + ");";
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

			stmt.close();
			conn.commit();

			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public boolean atualizar(Produto produto) {
		String query = 
				"UPDATE produto SET "
					+ "nome = '" + produto.getNome() + "', " + "ano = " + produto.getAno() + ", "
					+ "disponivel = " + (produto.getDisponivel() ? 1 : 0) + " "
				+ "WHERE id = " + produto.getId();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(query);

			stmt.close();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Produto> lista() {
		List<Produto> produtos = new ArrayList<>();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM produto");

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nome = rs.getString("nome");
				int ano = rs.getInt("ano");
				boolean disponivel = rs.getBoolean("disponivel");

				Produto produto = new ProdutoBuilder()
						.id(id)
						.nome(nome)
						.ano(ano)
						.disponivel(disponivel)
						.builder();
				produtos.add(produto);
			}
			
			rs.close();
			stmt.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public void remover(Integer id) {
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM produto WHERE id = " + id + ";";
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}