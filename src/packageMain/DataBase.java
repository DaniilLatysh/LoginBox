package packageMain;

import java.sql.*;

import javax.swing.JOptionPane;

public class DataBase {
	private Connection con;
	private Statement st;
	private ResultSet res;
	public String show = "";

	public DataBase(String path, String nameDB, String login, String pass)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error database connection!\n"
					+ e);
			
		}
		con = DriverManager.getConnection(path + nameDB, login, pass);
		st = con.createStatement();
	}

	public void update(String sql) throws SQLException {
		st.executeUpdate(sql);
	}

	public ResultSet query(String sql) throws SQLException {
		res = st.executeQuery(sql);
		return res;
	}

	public void close() throws SQLException {
		st.close();
		con.close();
	}

	public void showDatabaseMetadata() {
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			System.out.println(dbmd.getDatabaseProductName());
			System.out.println(dbmd.getDatabaseProductVersion());
			System.out.println(dbmd.getDriverName());
			System.out.println(dbmd.getDriverVersion());
		} catch (SQLException ex) {
			System.out.println("Error in showDatabaseMetaData " + ex);
		}
	}

	public void showResultSet(ResultSet rs){
		
		try{
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				System.out.print(rsmd.getColumnName(i) + " ");
			}
			while (rs.next()) {
				System.out.println();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rs.getString(i) + " " +show);	
					
				}
			}
			System.out.println();
		}catch(SQLException ex){
			System.out.println("Error in showResultSet " + ex);
		}
	}
	
	public int getID(String login){
		int id = 0;
		try {
			ResultSet data = query("SELECT id_login FROM loginTable Where login='"
							+ login + "';");
			if (data.next()) {
				id = data.getInt("id_login");
			}
		}
			catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Exeption \n"+ex);
			}
		return id;
		
	}
	
	public String getText(String login){
		String dataText = "";
		try {
			ResultSet data = query("SELECT dataText FROM loginTable Where login='"
							+ login + "';");
			if (data.next()) {
				dataText = data.getString("dataText");
			}
		}
			catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Exeption \n"+ex);
			}
		return dataText;
		
	}
	
	
}
