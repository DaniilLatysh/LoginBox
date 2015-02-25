package packageMain;

import java.sql.SQLException;

public class LBMain {

	public static void main(String[] args) throws SQLException {
		InputUser IU = new InputUser();
		//BoxUser BU = new BoxUser();
		DataBase db = new DataBase("jdbc:mysql://localhost/", "", "root", "5813");
		//db.update("drop database loginbox;");
		db.update("CREATE DATABASE IF NOT EXISTS loginbox;");
		db.update("USE loginbox;");
		db.update("CREATE TABLE IF NOT EXISTS loginTable(id_login int(100) AUTO_INCREMENT, "
				+ "dataText varchar(1000), "
				+ "login varchar(50), "
				+ "password varchar(50), PRIMARY KEY(id_login));");
		db.showResultSet(db.query("SELECT * FROM loginTable"));

	}

}
