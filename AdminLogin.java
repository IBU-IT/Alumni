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
	private JPanel contentPane; //create a panel
	private JTextField txtId; //create an input text field for id
	private JPasswordField txtPassword; //create an input text field for password
	Connection conn = new DBConnection().connect(); //create an object for connect() function 
	private JButton btnHomepage; //create a button

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //Eventqueue is a platform-independent class that queues events, from classes and forms
			public void run() { //run function gets triggered when the page is opened
				try {
					AdminLogin frame = new AdminLogin(); //create an object for the page
					frame.setVisible(true); //display the page
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e); //show error if occurs.
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminLogin() { //create page elements
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the application when click X on the window
		setBounds(100, 100, 218, 194); //sets location and size. (X,Y,width,height)
		contentPane = new JPanel(); //creates a panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); //sets content panel of the frame
		contentPane.setLayout(null); //sets layout of the panel

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() { //creates action listener to listen when it gets triggered
			public void actionPerformed(ActionEvent e) { //creates an action event to fire up the code.
				String sql = "SELECT * FROM Admins WHERE id=" + txtId.getText() + " AND password= + " + txtPassword.getText() + ";";
				//create a sql query. Select everything from admin where id and password are entered values
				try {
					PreparedStatement ps = conn.prepareStatement(sql); //prepare a statement for query
					ResultSet rs = ps.executeQuery(); // execute the query
					if (rs.next()) { //if execution returns something, 
						AdminHomepage ah = new AdminHomepage(); //create an object for the admin page
						ah.setVisible(true); //set it visible

					}
				} catch (Exception e2) { //show error if sql connection has problem
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnLogin.setBounds(53, 109, 117, 29); //set location and size for login button (X,Y,width,height)
		contentPane.add(btnLogin); //add it to the page

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
