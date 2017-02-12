package tc.livraria.util;

import java.sql.SQLException;
import java.sql.Statement;

public class CriaTabela {

	public void criarTabelaProduto() {
		Statement st = null;

		try {
			st = new ConexaoFabrica().getConexao().createStatement();
			String query = "CREATE TABLE IF NOT EXISTS produto ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, nome CHAR(100), ano INT, disponivel INT)";

			st.execute(query);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
