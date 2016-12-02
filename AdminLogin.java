import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import FunctionImplementations.DBConnection;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AdminLogin extends JFrame {
	private JPanel contentPane; 
	private JTextField txtId;
	private JPasswordField txtPassword; 
	Connection conn = new DBConnection().connect();
	private JButton btnHomepage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try {
					AdminLogin frame = new AdminLogin(); 
					frame.setVisible(true); 
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e); 
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() { //create page elements
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 218, 194);
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null); 
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				String sql = "SELECT * FROM Admins WHERE id=" + txtId.getText() + " AND password= + " + txtPassword.getText() + ";";
				
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery(); 
					if (rs.next()) { 
						AdminHomepage ah = new AdminHomepage(); 
						ah.setVisible(true);

					}
				} catch (Exception e2) { 
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
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
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage hp = new Homepage();
				hp.setVisible(true);
			}
		});
		btnHomepage.setBounds(53, 137, 117, 29);
		contentPane.add(btnHomepage);
	}
}
