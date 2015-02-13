package packageMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.sun.glass.ui.Window;

public class RegUser extends JFrame {
	private static final long serialVersionUID = 2363817494748215664L;
	private PicturePanel picturePanel;
	private JPanel panelLogin;
	private DataBase db;

	public RegUser() throws Exception {
		initComponents();
	}

	private void initComponents() throws Exception {

		picturePanel = new PicturePanel();
		panelLogin = new JPanel();
		setTitle("Login");
		setSize(400, 200);
		setResizable(false);
		setLocationRelativeTo(null);

		Font font = new Font("Game", Font.BOLD, 15);

		JLabel in = new JLabel("Input your Name: ");
		in.setBounds(60, 20, 160, 20);
		in.setForeground(Color.white);
		in.setFont(font);
		add(in);

		JTextField log = new JTextField();
		log.setBounds(60, 45, 280, 20);
		add(log);

		JLabel ip = new JLabel("Input your pass: ");
		ip.setBounds(60, 55, 160, 60);
		ip.setForeground(Color.white);
		ip.setFont(font);
		add(ip);

		JPasswordField pass = new JPasswordField();
		pass.setBounds(60, 100, 280, 20);
		add(pass);

		JButton reg = new JButton("Cheak In");
		reg.setBounds(230, 130, 100, 20);
		// reg.setOpaque(false);
		reg.setBackground(Color.white);
		add(reg);

		picturePanel.setLayout(new java.awt.BorderLayout());
		picturePanel
				.setImageFile(new java.io.File(
						"C:\\All files\\Java\\Проекты eclipse\\LoginBox\\textures\\left.jpg"));

		panelLogin.setLayout(new java.awt.GridLayout());
		panelLogin.setOpaque(false);

		picturePanel.add(panelLogin, java.awt.BorderLayout.NORTH);
		getContentPane().add(picturePanel, java.awt.BorderLayout.CENTER);
		
		
		
		reg.addActionListener(new ActionListener() { // Listeners
			public void actionPerformed(ActionEvent e) {
				boolean cheakName = Cheaker.name(log.getText());
				boolean cheakPass = Cheaker.pass(pass.getText());

				if (!cheakName && !cheakPass) {
					JOptionPane.showMessageDialog(null,
							"Ошибка логина или пароля!\n"
									+ "Проверте ровность рук!", "Error", 1);
				} else {
					if (!cheakName)
						JOptionPane.showMessageDialog(null,
								"Имя не корректно!", "Error", 1);
					if (!cheakPass)
						JOptionPane.showMessageDialog(null,
								"Ваш парольне корректен!", "Error", 1);
				}
				if (cheakName && cheakPass) {
					try {
						db = new DataBase("jdbc:mysql://localhost/",
								"loginbox", "root", "5813");
					} catch (SQLException e1) {
						System.out.println("Ошибка подключения к базе данных!");
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					try {
						
						db.update("USE loginbox");
						db.update("INSERT INTO loginTable(login,password) VALUES ('"
								+ log.getText()
								+ "','"
								+ pass.getText()
								+ "');");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Возникла проблема:\n" + ex);
					}
					JOptionPane.showMessageDialog(null,
							"Регистрация прошла успешно!");
					
					
					dispose();

				}
				try {
					db.showResultSet(db.query("SELECT * FROM loginTable;"));
					
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
/*
		*/
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}
