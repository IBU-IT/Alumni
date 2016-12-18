import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import FunctionImplementations.DBConnection;
import FunctionImplementations.DateLabelFormatter;
import Models.BindCombo;
import Models.LoadFaculties;
import Models.Search;
import Models.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class EditStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtPlaceOfBirth;
	
	private Connection DBConnection;
	static Connection conn = new DBConnection().connect();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	private static String oldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudent frame = new EditStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(6, 6, 61, 16);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(109, 0, 130, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 44, 61, 16);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(109, 38, 130, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		txtSurname.setBounds(109, 76, 130, 26);
		contentPane.add(txtSurname);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(6, 82, 61, 16);
		contentPane.add(lblSurname);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(6, 120, 61, 16);
		contentPane.add(lblBirthday);

		JLabel lblPlaceOfBirth = new JLabel("Place of Birth");
		lblPlaceOfBirth.setBounds(6, 157, 82, 16);
		contentPane.add(lblPlaceOfBirth);

		txtPlaceOfBirth = new JTextField();
		txtPlaceOfBirth.setBounds(109, 152, 130, 26);
		contentPane.add(txtPlaceOfBirth);
		txtPlaceOfBirth.setColumns(10);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(279, 6, 61, 16);
		contentPane.add(lblFaculty);


		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(279, 76, 74, 16);
		contentPane.add(lblDepartment);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setBounds(16, 190, 61, 16);
		contentPane.add(lblStudents);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Student s = new Student();
				s.setId(txtId.getText());
				s.setName(txtName.getText());
				s.setSurname(txtSurname.getText());
				s.setPlaceOfBirth(txtPlaceOfBirth.getText());
				s.setDepartment(Integer.parseInt(sd.valueMember.toString()));
				if (s.getId().length() != 9) {
					JOptionPane.showMessageDialog(null, "Student ID must be 9 digits", "WARNING",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String sql = "UPDATE Students SET Id = ?, Name = ?, Surname = ?, Birthday = ?, PlaceOfBirth = ?, "
						+ "Department = ? WHERE Id = ?";
System.out.println(s.getDepartment());
System.out.println("fac " + s.getFaculty());
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(s.getId()));
					pstmt.setString(2, s.getName());
					pstmt.setString(3, s.getSurname());
					pstmt.setString(4, s.getBirthday());
					pstmt.setString(5, s.getPlaceOfBirth());
					pstmt.setInt(6, s.getDepartment());
					pstmt.setInt(8, Integer.parseInt(oldId));
					int i = pstmt.executeUpdate();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
					System.out.println(e2);
				}

			}
		});
		btnEdit.setBounds(6, 483, 117, 29);
		contentPane.add(btnEdit);
	}
}