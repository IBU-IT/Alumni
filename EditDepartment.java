import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;
import Models.Department;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditDepartment extends JFrame {

	private static JPanel contentPane;
	private JTextField txtDepartmentName;
	private Connection DBConnection;
	static Connection conn = new DBConnection().connect();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDepartment frame = new EditDepartment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public EditDepartment() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Department Name");
		label.setBounds(6, 11, 114, 16);
		contentPane.add(label);

		txtDepartmentName = new JTextField();
		txtDepartmentName.setColumns(10);
		txtDepartmentName.setBounds(129, 6, 130, 26);
		contentPane.add(txtDepartmentName);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department department = new Department();
				department.setName(txtDepartmentName.getText());
				if (department.getName().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Input cannot be empty!");
					return;
				}
				String sql = "UPDATE Departments set Name = ? WHERE Name = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, department.getName());
					pstmt.setString(2, department.getName());
					int i = pstmt.executeUpdate();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Entered department name already exists!");
				}
			}
		});
		btnEdit.setBounds(75, 127, 117, 29);
		contentPane.add(btnEdit);
	}
}
