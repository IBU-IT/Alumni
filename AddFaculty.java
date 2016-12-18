import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;
import Models.Faculty;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddFaculty extends JFrame {

	private Connection DBConnection;
	static Connection conn = new DBConnection().connect();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	private static JPanel contentPane;
	private JTextField txtFacultyName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFaculty frame = new AddFaculty();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AddFaculty() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFacultyName = new JLabel("Faculty Name");
		lblFacultyName.setBounds(6, 6, 114, 16);
		contentPane.add(lblFacultyName);

		txtFacultyName = new JTextField();
		txtFacultyName.setColumns(10);
		txtFacultyName.setBounds(6, 28, 130, 26);
		contentPane.add(txtFacultyName);
		
		JLabel lblCurrentFaculties = new JLabel("Current Faculties");
		lblCurrentFaculties.setBounds(179, 6, 139, 16);
		contentPane.add(lblCurrentFaculties);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Faculty faculty = new Faculty();
				faculty.setName(txtFacultyName.getText());
				String sql = "INSERT INTO Faculties VALUES (?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, null);
					pstmt.setString(2, faculty.getName());
					int i = pstmt.executeUpdate();
				}
			}
		});
		btnAdd.setBounds(6, 64, 69, 29);
		contentPane.add(btnAdd);
	}
}
