package MAnagement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class HomePage extends JFrame {
	private JTextField stName;
	private JTextField stId;
	private JTextField stSection;
	private JTextField stDept;
	private JTextField sTField;
	private JTable table;
	public HomePage() {
		setTitle("DATA SUBMIT PORTAL");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\iqbal\\OneDrive\\Pictures\\images.jpg"));
		getContentPane().setBackground(new Color(0, 204, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 793, 630);
		
		JPanel stInputPanel = new JPanel();
		stInputPanel.setBackground(new Color(51, 255, 204));
		stInputPanel.setBounds(10, 42, 764, 199);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 255, 204));
		panel.setBounds(10, 275, 764, 308);
		
		JLabel lblNewLabel = new JLabel("Created by ASADUZZAMAN SHUVO and his Team(Mahedi hasan, Mahi hoque)");
		lblNewLabel.setBounds(10, 244, 769, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Student Data Submit Panel");
		lblNewLabel_1.setBounds(10, 11, 755, 25);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton ShowBtn = new JButton("Show");
		ShowBtn.setBounds(579, 20, 132, 45);
		ShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				final String DB_URL = "jdbc:mysql://localhost:3307/testdb";
			    final String USER = "root";
			    final String PASS = "";
			    
			    try {
			    	
				Class.forName("com.mysql.cj.jdbc.Driver");
	    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
	    		String sql = "Select * from student_info";  		
	    		Statement pst = conn.prepareStatement(sql);	    		
	    		ResultSet rs = pst.executeQuery(sql);
	    		
	    		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	    		DefaultTableModel model = (DefaultTableModel)table.getModel();
	    		model.setRowCount(0);
	    		
	    		while (rs.next()) {	  
	    			
	    			String name = rs.getString("Name");
	    			
	    			String id = String.valueOf(rs.getInt("ID"));
	    			
	    			String sec = rs.getString("Section");
	    			
	    			String dep = rs.getString("Department");
	    			
	    			String tbData[] = {name,id,sec,dep};
	    			model.addRow(tbData);
	    				
	    		}   			
    			
	    		
	    		pst.close();
    			conn.close();
    			
			    }
			    catch(Exception e1) {
			    	System.out.println(e1);
			    }
				
				
			}
		});
		ShowBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JButton btnResetForTarea = new JButton("Reset");
		btnResetForTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
	    		model.setRowCount(0);
				
			}
		});
		btnResetForTarea.setBounds(579, 168, 132, 44);
		btnResetForTarea.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//DELETE FROM `student_info` WHERE 0
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
					
					String q = "DELETE FROM student_info WHERE ID = ?";
			    	
					PreparedStatement pstm = con.prepareStatement(q);
					//ResultSet rs = pstm.executeQuery();
						//JOptionPane.showMessageDialog(null, "Data Not Found");
					
			        pstm.setString(1, sTField.getText());
			        
			        JOptionPane.showMessageDialog(null, "Delete SuccessFull","Delete",JOptionPane.CANCEL_OPTION);
					
					pstm.executeUpdate();
  				    con.close();
			
				//JOptionPane.showMessageDialog(null, "Delete SuccessFull","Delete",JOptionPane.CANCEL_OPTION);
				
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnDelete.setBounds(579, 91, 132, 47);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		sTField = new JTextField();
		sTField.setBounds(72, 10, 277, 41);
		sTField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
					
					String q = "select * from student_info where ID = ?";
			    	
					PreparedStatement pstm = con.prepareStatement(q);
			       
			        pstm.setString(1, sTField.getText());
			        
			        ResultSet rs = pstm.executeQuery();
			        
			        DefaultTableModel model = (DefaultTableModel)table.getModel();
			        
			        model.setRowCount(0);
			        String x = sTField.getText();
			        
//			        int x1 = Integer.parseInt(x);
//			        int x2 = rs.getInt("ID");
//			        x1=x1+0;
			        if(x.isEmpty()) {
			        	JOptionPane.showMessageDialog(null, "Value Empty");
			        }
			        else {
			      
			        while(rs.next()) {
			        	String name = rs.getString("Name");
		    			
		    			String id = String.valueOf(rs.getInt("ID"));
		    			
		    			String sec = rs.getString("Section");
		    			
		    			String dep = rs.getString("Department");
		    			
		    			String tbData[] = {name,id,sec,dep};
		    			
		    			model.addRow(tbData);
			        
			        	 
			        }
			        }
					
					//pstm.executeUpdate();
  				    con.close();
			
				
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnSearch.setBounds(359, 12, 110, 33);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panel.setLayout(null);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnExit.setBounds(579, 243, 132, 44);
		btnExit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panel.add(btnExit);
		panel.add(sTField);
		panel.add(btnSearch);
		panel.add(btnResetForTarea);
		panel.add(btnDelete);
		panel.add(ShowBtn);
		
		stName = new JTextField();
		stName.setBounds(270, 5, 238, 39);
		stName.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Student Name:");
		lblNewLabel_4.setBounds(122, 6, 116, 33);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		stId = new JTextField();
		stId.setBounds(270, 54, 238, 39);
		stId.setColumns(10);
		
		stSection = new JTextField();
		stSection.setBounds(270, 108, 238, 39);
		stSection.setColumns(10);
		
		stDept = new JTextField();
		stDept.setBounds(270, 157, 238, 39);
		stDept.setColumns(10);
		
		JButton SubmitBtn = new JButton("Submit");
		SubmitBtn.setBounds(580, 6, 132, 60);
		SubmitBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		SubmitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                    String name = stName.getText();
                    String Id= stId.getText();
                    String sec = stSection.getText();
                    String dept = stDept.getText();
                    
                    
                  
				if(name.isEmpty()|| Id.isEmpty() || sec.isEmpty() || dept.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill The Box", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					  int idNo = Integer.parseInt(Id);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root","");
					
					String q = "insert into student_info(Name,ID,Section,Department) values (?,?,?,?)";
			    	
					PreparedStatement pstm = con.prepareStatement(q);
					
					pstm.setString(1, name);
					pstm.setInt(2, idNo);
					pstm.setString(3, sec);
					pstm.setString(4, dept);
					
					pstm.executeUpdate();
    				con.close();
			
				
				
				}catch(Exception e1) {
					System.out.println(e1);
				}
				JOptionPane.showMessageDialog(null, "Update successfull");
				}
				//JOptionPane.showMessageDialog(null, "Update successfull");
				stName.setText("");
                stId.setText("");
                stSection.setText("");
                stDept.setText("");
				
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setBounds(580, 95, 132, 60);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stName.setText("");
                stId.setText("");
                stSection.setText("");
                stDept.setText("");
                
                //table.setModel(new DefaultTableModel());
			}
		});
		
		JLabel lblNewLabel_4_1 = new JLabel("Student ID:");
		lblNewLabel_4_1.setBounds(147, 55, 85, 33);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblNewLabel_4_2 = new JLabel("Student Section:");
		lblNewLabel_4_2.setBounds(114, 109, 124, 33);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblNewLabel_4_3 = new JLabel("Student DEPT.:");
		lblNewLabel_4_3.setBounds(122, 152, 116, 33);
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		stInputPanel.setLayout(null);
		stInputPanel.add(lblNewLabel_4);
		stInputPanel.add(lblNewLabel_4_1);
		stInputPanel.add(lblNewLabel_4_3);
		stInputPanel.add(lblNewLabel_4_2);
		stInputPanel.add(stSection);
		stInputPanel.add(stId);
		stInputPanel.add(stName);
		stInputPanel.add(stDept);
		stInputPanel.add(SubmitBtn);
		stInputPanel.add(btnNewButton_1);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 521, 233);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "ID", "Section", "Dept"
			}
		));
		
		JLabel lblNewLabel_2 = new JLabel("Name:                                  ID:                            Section:                                   Department:");
		lblNewLabel_2.setBounds(10, 56, 521, 13);
		panel.add(lblNewLabel_2);
		getContentPane().add(stInputPanel);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(lblNewLabel);
		setVisible(true);
	}
}
