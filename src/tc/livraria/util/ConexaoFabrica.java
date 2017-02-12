package tc.livraria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import totalcross.sys.Convert;
import totalcross.sys.Settings;

public class ConexaoFabrica {
	// Nome do banco de dados
	private static final String DB_NOME = "db_livraria.db";

	public Connection getConexao() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, DB_NOME));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
