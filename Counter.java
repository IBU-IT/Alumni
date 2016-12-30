import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class Counter extends JFrame {

	private JPanel contentPane; 
	private JRadioButton rbtnFaculty; 
	private JRadioButton rbtnDepartment;  
	private Connection DBConnection; 
	private static Connection conn = new DBConnection().connect(); 
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private JTextField txtSearch; 
	private JButton btnBack;
	private JButton btnHomepage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() { 
				try {
					Counter frame = new Counter(); 
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
	public Counter() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 322, 300);
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);

		txtSearch = new JTextField(); 
		txtSearch.setBounds(16, 76, 130, 26); 
		contentPane.add(txtSearch); 
		txtSearch.setColumns(10); 

		rbtnDepartment = new JRadioButton("Count students related to their departments"); 
		rbtnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (txtSearch.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null, "You need to enter a department name!", "ERROR",
							JOptionPane.ERROR_MESSAGE); 
					rbtnDepartment.setSelected(false); 
					return; 
				}
				
				rbtnFaculty.setSelected(false); 
				DefaultTableModel model = new DefaultTableModel(); 
				JTable table = new JTable(model); 
				table.setRowSelectionAllowed(false); 
				table.setEnabled(false); 
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.setLayout(new FlowLayout()); 
				table.setBounds(6, 146, 309, 100); 
				try {
					contentPane.add(table); 
					String sql = "SELECT * FROM Students st INNER JOIN Departments dp "
							+ "ON st.Department = dp.Id WHERE dp.Name = ?";
					
					pstmt = conn.prepareStatement(sql); 
					pstmt.setString(1, txtSearch.getText()); 
					rs = pstmt.executeQuery(); 
					model.addColumn("Name"); 
					model.addColumn("Count"); 
					if (rs.first() == false) { 
						table.setVisible(false); 
						JOptionPane.showMessageDialog(null, txtSearch.getText() + " student does not exist in db.", "Warning",
								JOptionPane.WARNING_MESSAGE); 
						rbtnDepartment.setSelected(false); 
						return;
					}

					int counter = 0; 
					if (rs.last()) { 
						counter = rs.getRow(); 
					}

					String departmentName = rs.getString("dp.Name"); 
					model.addRow(new Object[] { departmentName, counter }); 
					

					table.setModel(model); 
					JScrollPane scrollPane = new JScrollPane(table); 
					scrollPane.setSize(new Dimension(309, 100)); 
					TableColumnModel tcm = table.getColumnModel(); 
					tcm.getColumn(0).setPreferredWidth(250); 
					tcm.getColumn(1).setPreferredWidth(50); 

					scrollPane.setLocation(6, 146); 
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); 
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
					contentPane.add(scrollPane); 
					rs.close(); 
					pstmt.close();  
					rbtnDepartment.setSelected(false); 
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2, "ERROR", JOptionPane.ERROR_MESSAGE); 
					rbtnDepartment.setSelected(false); 
				}
			}
		});
		rbtnDepartment.setBounds(6, 6, 309, 23);
		contentPane.add(rbtnDepartment);

		rbtnFaculty = new JRadioButton("Count students related to their faculties");
		rbtnFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearch.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to enter a faculty name!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					rbtnDepartment.setSelected(false);
					return;
				}
				rbtnFaculty.setSelected(false);
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
				table.setRowSelectionAllowed(false);
				table.setEnabled(false);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setLayout(new FlowLayout());
				table.setBounds(6, 146, 309, 100);
				try {
					contentPane.add(table);
					String sql = "SELECT * FROM Students st INNER JOIN Faculties fc "
							+ "ON st.Faculty = fc.Id WHERE fc.Name = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtSearch.getText());
					rs = pstmt.executeQuery();
					model.addColumn("Name");
					model.addColumn("Count");
					if (rs.first() == false) {
						table.setVisible(false);
						JOptionPane.showMessageDialog(null, txtSearch.getText() + " student does not exist in db.", "Warning",
								JOptionPane.WARNING_MESSAGE);
						rbtnDepartment.setSelected(false);
						return;
					}

					int counter = 0;
					if (rs.last()) {
						counter = rs.getRow();
					}

					String departmentName = rs.getString("fc.Name");
					model.addRow(new Object[] { departmentName, counter });

					table.setModel(model);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setSize(new Dimension(309, 100));
					TableColumnModel tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(250);
					tcm.getColumn(1).setPreferredWidth(50);

					scrollPane.setLocation(6, 146);
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					contentPane.add(scrollPane);
					rs.close();
					pstmt.close();
					rbtnFaculty.setSelected(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2, "ERROR", JOptionPane.ERROR_MESSAGE);
					rbtnFaculty.setSelected(false);
				}
			}
		});
		rbtnFaculty.setBounds(6, 41, 284, 23);
		contentPane.add(rbtnFaculty);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				AdvancedSearch ah = new AdvancedSearch();
				ah.setVisible(true);
			}
		});
		btnBack.setBounds(198, 76, 117, 29);
		contentPane.add(btnBack);
		
		btnHomepage = new JButton("Homepage");
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				Homepage ah = new Homepage();
				ah.setVisible(true);
			}
		});
		btnHomepage.setBounds(198, 117, 117, 29);
		contentPane.add(btnHomepage);
	}
}
