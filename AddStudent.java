import javax.swing.JTextField;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.omg.PortableInterceptor.TRANSPORT_RETRY;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import FunctionImplementations.DBConnection;
import FunctionImplementations.DateLabelFormatter;
import Models.Department;
import Models.Faculty;
import Models.Student;
import Models.LoadFaculties;
import Models.BindCombo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField textField;

	private Connection conn = new DBConnection().connect();
	private Connection DBConnection;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private JTextField txtPlaceOfBirth;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
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
	public AddStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(5, 72, 61, 16);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(88, 67, 130, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(6, 106, 61, 16);
		contentPane.add(lblSurname);

		txtSurname = new JTextField();
		txtSurname.setBounds(88, 105, 130, 26);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);

		JLabel lblNewLabel = new JLabel("Birthday");
		lblNewLabel.setBounds(5, 138, 61, 16);
		contentPane.add(lblNewLabel);

		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(241, 6, 61, 16);
		contentPane.add(lblFaculty);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(238, 66, 74, 16);
		contentPane.add(lblDepartment);

		JLabel lblPlaceOfBirth = new JLabel("Place of Birth");
		lblPlaceOfBirth.setBounds(5, 184, 91, 16);
		contentPane.add(lblPlaceOfBirth);

		txtPlaceOfBirth = new JTextField();
		txtPlaceOfBirth.setBounds(88, 179, 130, 26);
		contentPane.add(txtPlaceOfBirth);
		txtPlaceOfBirth.setColumns(10);


		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String birthday = df.format(selectedDate);


				Student as = new Student();
				as.setId(txtId.getText());
				as.setName(txtName.getText());
				as.setSurname(txtSurname.getText());
				as.setBirthday(birthday);
				as.setPlaceOfBirth(txtPlaceOfBirth.getText());

				if (as.getId().length() != 9) {
					JOptionPane.showMessageDialog(null, "Student ID must be 9 digits", "WARNING",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				String sql = "INSERT INTO Students VALUES (?,?,?,?,?,?,?)";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(as.getId()));
					pstmt.setString(2, as.getName());
					pstmt.setString(3, as.getSurname());
					pstmt.setString(4, as.getBirthday());
					pstmt.setString(5, as.getPlaceOfBirth());
					pstmt.setInt(6, as.getDepartment());
					pstmt.setInt(7, as.getFaculty());

					int i = pstmt.executeUpdate();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnAdd.setBounds(6, 243, 117, 29);
		contentPane.add(btnAdd);

		txtId = new JTextField();
		txtId.setBounds(88, 29, 130, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(5, 34, 61, 16);
		contentPane.add(lblId);

	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("close() exception: " + e);
		}
	}
}
