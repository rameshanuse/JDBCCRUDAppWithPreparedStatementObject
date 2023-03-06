package in.ineuron.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {}
	
	static {
		//Step1. Loading and register Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws IOException, SQLException {
		HikariConfig config = new HikariConfig("src\\in\\ineuron\\properties\\db.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}

}
