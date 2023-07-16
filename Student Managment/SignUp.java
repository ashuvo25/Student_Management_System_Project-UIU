package MAnagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class SignUp extends JFrame {
	private JTextField idFieldsg;
	private JPasswordField passwordFieldsg;
	
	
	
	DataBaseCon conn ;
	private JTextField nameField;
	
	public SignUp() {
		setTitle("SIGN UP PAGE");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\iqbal\\OneDrive\\Pictures\\images.jpg"));
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
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		JLabel lblLoginPanel = new JLabel("SIGN UP PANEL");
		lblLoginPanel.setBounds(198, 0, 211, 78);
		lblLoginPanel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLoginPanel.setForeground(new Color(0, 0, 0));
		lblLoginPanel.setHorizontalAlignment(SwingConstants.CENTER);
		
		idFieldsg = new JTextField();
		idFieldsg.setBounds(198, 84, 244, 57);
		idFieldsg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Admin ID  :");
		lblNewLabel.setBounds(85, 89, 95, 42);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(78, 245, 114, 49);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JButton btnLoginsg = new JButton("<-Back");
		btnLoginsg.setBounds(198, 338, 119, 41);
		btnLoginsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean m = true;
				if(m) {
				dispose();
				loginPage lg = new loginPage();
				}
				
			}
		});
		btnLoginsg.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnSignUpsg = new JButton("Done");
		btnSignUpsg.setBounds(323, 338, 119, 41);
		btnSignUpsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name  = nameField.getText()	;
	    		String id  = idFieldsg.getText();
	    	    String pass =  passwordFieldsg.getText();
	    	    
	         	

				if(name.isEmpty()||id.isEmpty()||pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Something is empty", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
				try {
					int idNo = Integer.parseInt(id);
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
					
					String q = "insert into login(name,ID,Password) values (?,?,?)";
			    	
					PreparedStatement pstm = con.prepareStatement(q);
					
					pstm.setString(1, name);
					pstm.setInt(2, idNo);
					pstm.setString(3, pass);
					
					pstm.executeUpdate();
					
					con.close();

				
				}catch(Exception e1) {
					System.out.println(e1);
				}
				
				
				
				 nameField.setText("")	;
		    	 idFieldsg.setText("");
		    	 passwordFieldsg.setText("");
		    	 JOptionPane.showMessageDialog(null, "Sing up successfull");
		    	 
		    	loginPage lg = new loginPage();
		    	
		    	dispose();
		    	 
		    	 
				}
				
			}
		});
		btnSignUpsg.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		passwordFieldsg = new JPasswordField();
		passwordFieldsg.setBounds(198, 243, 244, 57);
		
		nameField = new JTextField();
		nameField.setBounds(198, 159, 244, 57);
		nameField.setColumns(10);
		
		JLabel lblName = new JLabel("Admin Name :");
		lblName.setBounds(61, 161, 131, 49);
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.setLayout(null);
		panel.add(btnLoginsg);
		panel.add(btnSignUpsg);
		panel.add(lblNewLabel);
		panel.add(lblPassword);
		panel.add(lblName);
		panel.add(passwordFieldsg);
		panel.add(lblLoginPanel);
		panel.add(idFieldsg);
		panel.add(nameField);
		getContentPane().setLayout(groupLayout);
		setBackground(new Color(153, 170, 170));
		
		setVisible(true);
	}
}
