import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;


public class AddFaculty extends JFrame {


	private JList listDepartments = null;
	private JPanel contentPane;
	private JTextField txtFacultyName;

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

	public AddFaculty() {
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

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(6, 64, 117, 29);
		contentPane.add(btnAdd);

		JLabel lblCurrentFaculties = new JLabel("Current Faculties");
		lblCurrentFaculties.setBounds(179, 6, 139, 16);
		contentPane.add(lblCurrentFaculties);
	}
}

