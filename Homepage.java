import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Homepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
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
	public Homepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AdvancedSearch as = new AdvancedSearch();
				as.setVisible(true);
			}
		});
		btnSearch.setBounds(245, 89, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnAdminLogin = new JButton("");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AdminLogin al = new AdminLogin();
				al.setVisible(true);
			}
		});
		btnAdminLogin.setBounds(6, 6, 20, 15);
		contentPane.add(btnAdminLogin);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Alumni Center.");
		lblNewLabel.setFont(new Font("Luminari", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(16, 33, 402, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLetsSearchingAlumnis = new JLabel("Let's searching alumnis!");
		lblLetsSearchingAlumnis.setForeground(new Color(102, 0, 255));
		lblLetsSearchingAlumnis.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblLetsSearchingAlumnis.setBounds(100, 61, 318, 16);
		contentPane.add(lblLetsSearchingAlumnis);
	}
}

