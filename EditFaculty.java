import java.awt.BorderLayout;
import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;
import Models.Faculty;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class EditFaculty extends JFrame {

	private static JPanel contentPane;
	static Connection conn = new DBConnection().connect();
	private Connection DBConnection;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	private JTextField txtFacultyName;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFaculty frame = new EditFaculty();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditFaculty() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFacultyName = new JLabel("Faculty Name");
		lblFacultyName.setBounds(6, 11, 114, 16);
		contentPane.add(lblFacultyName);

		txtFacultyName = new JTextField();
		txtFacultyName.setColumns(10);
		txtFacultyName.setBounds(129, 6, 130, 26);
		contentPane.add(txtFacultyName);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Faculty faculty = new Faculty();
				faculty.setName(txtFacultyName.getText());
				String sql = "UPDATE Faculties set Name = ? WHERE Name = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, faculty.getName());
					pstmt.setString(2, faculty.getName());
					int i = pstmt.executeUpdate();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Entered faculty name already exists!");
				}
			}
		});
		btnEdit.setBounds(75, 127, 117, 29);
		contentPane.add(btnEdit);
	}
}
