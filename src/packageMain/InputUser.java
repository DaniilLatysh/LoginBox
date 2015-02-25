package packageMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class InputUser extends JFrame {
	private static final long serialVersionUID = 6120724773571206839L;
	JPanel box;
	private PicturePanel picturePanel2;
	private DataBase db;
	private static String logData = "";
	private static String login;

	public InputUser() {
		initComponents();
	}

	

	private void initComponents() {

		picturePanel2 = new PicturePanel();
		box = new JPanel();
		setTitle("LoginBox");
		setSize(400, 230);
		setResizable(false);
		setLocationRelativeTo(null);
		Font font = new Font("Game", Font.BOLD, 11);
		Font font2 = new Font("font2", Font.ITALIC, 15);

		picturePanel2.setLayout(new java.awt.BorderLayout());
		picturePanel2
				.setImageFile(new java.io.File(
						"C:\\All files\\Java\\Проекты eclipse\\LoginBox\\textures\\left.jpg"));

		box.setLayout(new java.awt.GridLayout());
		box.setOpaque(false);

		picturePanel2.add(box, java.awt.BorderLayout.NORTH);
		getContentPane().add(picturePanel2, java.awt.BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);

		JMenuItem add = new JMenuItem("Add new user");
		add.setFont(font);
		fileMenu.add(add);

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegUser regUser = new RegUser();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setFont(font);
		fileMenu.add(exitItem);

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileMenu.addSeparator();
		menuBar.add(fileMenu);

		JLabel in = new JLabel("Input your Name: ");
		in.setBounds(60, 20, 160, 20);
		in.setForeground(Color.white);
		in.setFont(font2);
		add(in);

		JTextField log = new JTextField();
		log.setBounds(60, 45, 280, 20);
		add(log);

		JLabel ip = new JLabel("Input your pass: ");
		ip.setBounds(60, 55, 160, 60);
		ip.setForeground(Color.white);
		ip.setFont(font2);
		add(ip);

		JPasswordField pass = new JPasswordField();
		pass.setBounds(60, 100, 280, 20);
		add(pass);

		JButton ok = new JButton("Ok");
		ok.setBounds(155, 130, 100, 20);
		// reg.setOpaque(false);
		ok.setBackground(Color.white);
		add(ok);

		ok.addActionListener(new ActionListener() { // Button ok
			public void actionPerformed(ActionEvent e) {
				try {
					db = new DataBase("jdbc:mysql://localhost/", "loginbox",
							"root", "5813");
					db.update("USE loginbox");
					ResultSet data = db
							.query("SELECT * FROM loginTable Where login='"
									+ log.getText() + "' AND password='"
									+ pass.getText() + "';");
					if (data.next()) {
						logData = log.getText();
						dispose();
						BoxUser BU = new BoxUser();

					} else {
						JOptionPane.showMessageDialog(null,
								"Неверный логин или пароль!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: \n" + ex);
				}
				try {
					db.showResultSet(db.query("SELECT * FROM loginTable;"));
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		add(menuBar);
		add(box);
		add(picturePanel2);
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static String getLogin() {
		login = logData;

		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
