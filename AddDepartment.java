import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;
import Models.Department;
import Models.LoadFaculties;
import Models.BindCombo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class AddDepartment extends JFrame {

	private static JPanel contentPane; 
	private JTextField txtDepartmentName; 
	private Connection DBConnection; 
	private static Connection conn = new DBConnection().connect(); 
																	
																	
																	
																	
	private static PreparedStatement pstmt = null;
													
													
	private static ResultSet rs = null;
										
	private static JTable tableDepartments; 
	private static JScrollPane scrollPane; 
	private static JButton btnBack; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
												
												
												
			public void run() { 
								
				try {
					AddDepartment frame = new AddDepartment(); 
																
																
					frame.setVisible(true); 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AddDepartment() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
														
														
		setBounds(100, 100, 392, 348); 
										
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);

		JLabel lblDepartmentName = new JLabel("Department Name"); 
																	
																	
																	
																	
																	
		lblDepartmentName.setBounds(139, 6, 114, 16); 
													
		contentPane.add(lblDepartmentName); 

		txtDepartmentName = new JTextField();
		txtDepartmentName.setBounds(131, 34, 130, 26);
		contentPane.add(txtDepartmentName);
		txtDepartmentName.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() { 
														
														
														
			public void actionPerformed(ActionEvent e) { 
															
															
				Department department = new Department(); 
															

				department.setName(txtDepartmentName.getText());
				String sql = "INSERT INTO Departments VALUES (?,?)";
				
				try {
					pstmt = conn.prepareStatement(sql); 
														
					pstmt.setInt(1, department.getId()); 
												
					pstmt.setString(2, department.getName()); 
																
					pstmt.executeUpdate(); 
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		btnAdd.setBounds(119, 108, 69, 29);
		contentPane.add(btnAdd);

		JLabel lblExistedDepartments = new JLabel("Current Departments");
		lblExistedDepartments.setBounds(16, 149, 139, 16);
		contentPane.add(lblExistedDepartments);

		tableDepartments = new JTable(); 
												

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHomepage ah = new AdminHomepage();
				ah.setVisible(true);
			}
		});
		btnBack.setBounds(197, 108, 80, 29);
		contentPane.add(btnBack);


			scrollPane = new JScrollPane(tableDepartments); 
															
			scrollPane.setSize(new Dimension(380, 105)); 
															
																		
																		
			scrollPane.setLocation(6, 176); 
												
																										// vertical
																										// scrollbar
																										// if
																										// needed
			contentPane.add(scrollPane); 



	}
}
