import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminHomepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public AdminHomepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddStudent = new JButton("Add  Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AddStudent as = new AddStudent();
				as.setVisible(true);
			}
		});
		btnAddStudent.setBounds(6, 26, 117, 29);
		contentPane.add(btnAddStudent);

		JButton btnEditStudent = new JButton("Edit Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				EditStudent es = new EditStudent();
				es.setVisible(true);
			}
		});
		btnEditStudent.setBounds(6, 67, 117, 29);
		contentPane.add(btnEditStudent);

		JButton btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AddDepartment ad;
				try {
					ad = new AddDepartment();
					ad.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddDepartment.setBounds(162, 26, 135, 29);
		contentPane.add(btnAddDepartment);

		JButton btnEditDepartment = new JButton("Edit Department");
		btnEditDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				EditDepartment ed;
				try {
					ed = new EditDepartment();
					ed.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnEditDepartment.setBounds(162, 67, 135, 29);
		contentPane.add(btnEditDepartment);

		JButton btnAddFaculty = new JButton("Add Faculty");
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AddFaculty af;
				try {
					af = new AddFaculty();
					af.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnAddFaculty.setBounds(327, 26, 117, 29);
		contentPane.add(btnAddFaculty);

		JButton btnEditFaculty = new JButton("Edit Faculty");
		btnEditFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				EditFaculty ef;
				try {
					ef = new EditFaculty();
					ef.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnEditFaculty.setBounds(327, 67, 117, 29);
		contentPane.add(btnEditFaculty);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AdvancedSearch search = new AdvancedSearch();
				search.setVisible(true);
			}
		});
		btnSearch.setBounds(177, 129, 117, 29);
		contentPane.add(btnSearch);
	}

}
