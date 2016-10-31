import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class AddDepartment extends JFrame {

	private static JPanel contentPane;
	private JTextField txtDepartmentName;
	private static JTable tableDepartments;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDepartment frame = new AddDepartment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDepartmentName = new JLabel("Department Name");
		lblDepartmentName.setBounds(6, 6, 114, 16);
		contentPane.add(lblDepartmentName);

		txtDepartmentName = new JTextField();
		txtDepartmentName.setBounds(6, 34, 130, 26);
		contentPane.add(txtDepartmentName);
		txtDepartmentName.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(6, 72, 117, 29);
		contentPane.add(btnAdd);

		JLabel lblExistedDepartments = new JLabel("Current Departments");
		lblExistedDepartments.setBounds(189, 6, 139, 16);
		contentPane.add(lblExistedDepartments);

	}
}
