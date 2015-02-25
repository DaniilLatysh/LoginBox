package packageMain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javafx.scene.layout.Border;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.awt.Component;

public class BoxUser extends JFrame {

	private static final long serialVersionUID = 8200297926255487874L;
	private JPanel box;
	private PicturePanel picturePanel;
	private JButton save;
	private DataBase db;
	public String i = "";
	private JButton wordButtonBig;
	//public int word = 11;
	
	public BoxUser() {
		initComponents();
	}

	private void initComponents() {
		

		
		box = new JPanel();
		setTitle("LoginBox");
		setSize(500, 280);
		setResizable(false);
		setLocationRelativeTo(null);
		Font font = new Font("font", Font.HANGING_BASELINE, 13);
		Font font2 = new Font("font2", Font.ITALIC, 15);
		
		
		
		
		
        JTextArea pane = new JTextArea(10, 3);
        pane.setBackground(new Color(255, 255, 255));
        pane.setText(i);
        pane.setCaretPosition(0);
        pane.setEditable(true);
 		pane.setFont(font);
        final JScrollPane scrollPane = new JScrollPane(pane);
 		scrollPane.setPreferredSize(new Dimension(18, 190));
 		
        box.add(scrollPane, BorderLayout.EAST);
/*  
        wordButtonBig = new JButton("A");
        wordButtonBig.setFont(font2);
        wordButtonBig.setBounds(110, 200, 45, 20);
        wordButtonBig.setBackground(Color.white);
        add(wordButtonBig);
*/
		save = new JButton("Save");
		save.setFont(font2);
		save.setBounds(10, 200, 100, 40);
		save.setBackground(Color.white);
		add(save);
		
		try {
			db = new DataBase("jdbc:mysql://localhost/",
					"loginbox", "root", "5813");
			db.update("USE loginbox");
			String login = InputUser.getLogin();
			String t = db.getText(login);
			pane.setText(t);
		} catch (SQLException e1) {
			System.out.println("Ошибка подключения к базе данных!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = InputUser.getLogin();
				i = pane.getText();
				if (i != null) {
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
						db.update("UPDATE loginTable SET dataText ='"
								+ i + "' where id_login ='"+db.getID(login)+"';");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Ahtung!\n" + ex);
					}
					try {
						db.showResultSet(db.query("SELECT * FROM loginTable;"));
						
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Область пуста!");
				}
			}
		});
/*
 *                                                                          Кнопка увеличения размера шрифта. Не работает из-за отсутствия обновления.
		wordButtonBig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					word = word++;
				}catch(Exception word){
					JOptionPane.showMessageDialog(null, "Ошибка!");
				}
			}
		});
*/
		picturePanel = new PicturePanel();
		picturePanel.setLayout(new java.awt.BorderLayout());
		picturePanel
				.setImageFile(new java.io.File(
						"C:\\All files\\Java\\Проекты eclipse\\LoginBox\\textures\\left.jpg"));

		box.setLayout(new java.awt.GridLayout());
		box.setOpaque(false);

		picturePanel.add(box, java.awt.BorderLayout.NORTH);
		getContentPane().add(picturePanel, java.awt.BorderLayout.CENTER);

		
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	

}
