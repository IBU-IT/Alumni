import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Component;

public class EditDepartment extends JFrame {

	private static JPanel contentPane;
	private JTextField txtDepartmentName;
	private static JTable tableDepartments;
	private static JButton btnDelete;

	
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

	public EditDepartment() { 
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
		btnEdit.setBounds(75, 127, 117, 29);
		contentPane.add(btnEdit);
		tableDepartments.setLocation(161, 34);

		btnDelete = new JButton("Delete");
}

