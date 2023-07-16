package MAnagement;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class loginPage extends JFrame{
	private JTextField idField;
	private JPasswordField passwordField;
	
	
	
	DataBaseCon conn ;
	
	public loginPage() {
		setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\iqbal\\OneDrive\\Pictures\\images.jpg"));
		setTitle("LOGIN PAGE");
		initcomponent();

	}
	
	
	
	public void initcomponent() {
		getContentPane().setBackground(new Color(153, 170, 170));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 650, 500);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 255, 204));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		
		JLabel lblLoginPanel = new JLabel("LOGIN PANEL FOR ADMIN ONLY");
		lblLoginPanel.setBounds(162, 10, 271, 133);
		lblLoginPanel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLoginPanel.setForeground(new Color(0, 0, 0));
		lblLoginPanel.setHorizontalAlignment(SwingConstants.CENTER);
		
		idField = new JTextField();
		idField.setBounds(162, 149, 271, 57);
		idField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ADMIN ID  :");
		lblNewLabel.setBounds(30, 154, 114, 42);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(30, 226, 114, 49);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(313, 319, 119, 41);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String password = passwordField.getText();
//				if (id.isEmpty()|| password.isEmpty()) {
//					JOptionPane.showMessageDialog(null,"ID or Password is empty", "Error",JOptionPane.ERROR_MESSAGE);
//				}
//				else {
//					
//				}
				Connection conn;
				final String DB_URL = "jdbc:mysql://localhost:3307/testdb";
			    final String USER = "root";
			    final String PASS = "";
				try {
		    		
		    		Class.forName("com.mysql.cj.jdbc.Driver");
		    		conn = DriverManager.getConnection(DB_URL,USER,PASS);

		    		String sql = "Select * from login where ID = '"+id+"' and Password = '"+password+"' ";
	    		
		    		Statement pst = conn.prepareStatement(sql);

		    		
		    		ResultSet rs = pst.executeQuery(sql);
		    		
		    		if (rs.next()) {
		    			dispose();
		    			
		    			HomePage hpage = new HomePage();
//		    			    idField.setText("");
//			 				passwordField.setText("");
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "ID OR Password is wrong");
		    			 idField.setText("");
		 				passwordField.setText("");
		    		}
		    		conn.close();
		    	}catch(Exception e1 ) {
		    		System.out.println(e);
		    		
		    		
		    	}
				
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(162, 319, 119, 41);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean m = true;
				if(m) {
				dispose();
				SignUp sign = new SignUp();
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 224, 271, 57);
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(lblPassword);
		panel.add(btnSignUp);
		panel.add(btnLogin);
		panel.add(passwordField);
		panel.add(idField);
		panel.add(lblLoginPanel);
		getContentPane().setLayout(groupLayout);
		setBackground(new Color(153, 170, 170));
		
		setVisible(true);
	}
}
