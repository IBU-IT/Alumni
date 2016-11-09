package Alumni;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class EditFaculty extends JFrame {

	private JPanel contentPane;
	private JTextField txtFacultyName;
	private static JTable tableFaculties;

	
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

	
	public EditFaculty() {
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

		//tableFaculties.setLocation(161, 34);
		//tableFaculties.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(75, 127, 117, 29);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(204, 127, 117, 29);
		contentPane.add(btnDelete);
	}

}
