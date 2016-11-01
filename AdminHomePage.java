import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class AdminHomepage extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomepage frame = new AdminHomepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminHomepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add  Student");

		btnAddStudent.setBounds(6, 26, 117, 29);
		contentPane.add(btnAddStudent);
		
		JButton btnEditStudent = new JButton("Edit Student");

		btnEditStudent.setBounds(6, 67, 117, 29);
		contentPane.add(btnEditStudent);
		
		JButton btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.setBounds(162, 26, 135, 29);
		contentPane.add(btnAddDepartment);
		JButton btnEditDepartment = new JButton("Edit Department");
		btnEditDepartment.setBounds(162, 67, 135, 29);
		contentPane.add(btnEditDepartment);
		
		JButton btnAddFaculty = new JButton("Add Faculty");
		btnAddFaculty.setBounds(327, 26, 117, 29);
		contentPane.add(btnAddFaculty);
		
		JButton btnEditFaculty = new JButton("Edit Faculty");
		btnEditFaculty.setBounds(327, 67, 117, 29);
		contentPane.add(btnEditFaculty);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(177, 129, 117, 29);
		contentPane.add(btnSearch);
	}

}

