import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;


public class AdminLogin extends JFrame {
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPassword;
	private JButton btnHomepage;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 218, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(53, 109, 117, 29);
		contentPane.add(btnLogin);

		txtId = new JTextField();
		txtId.setBounds(43, 28, 130, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(43, 80, 130, 26);
		contentPane.add(txtPassword);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(50, 6, 61, 16);
		contentPane.add(lblId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 66, 61, 16);
		contentPane.add(lblPassword);

		btnHomepage = new JButton("Homepage");
		btnHomepage.setBounds(53, 137, 117, 29);
		contentPane.add(btnHomepage);
	}
}
