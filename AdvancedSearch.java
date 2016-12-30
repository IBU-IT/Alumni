import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;

import FunctionImplementations.DBConnection;
import Models.Search;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class AdvancedSearch extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private Connection DBConnection;
	static Connection conn = new DBConnection().connect();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvancedSearch frame = new AdvancedSearch();
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
	public AdvancedSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(6, 6, 61, 16);
		contentPane.add(lblSearch);

		JButton btnCounter = new JButton("Counter");
		btnCounter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				contentPane.setVisible(false);
				Counter c = new Counter();
				c.setVisible(true);
			}
		});
		btnCounter.setBounds(344, 1, 117, 29);
		contentPane.add(btnCounter);

		txtSearch = new JTextField();
		txtSearch.setBounds(78, 1, 130, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search();
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
				table = new JTable(model);
				table.setRowSelectionAllowed(false);
				table.setEnabled(false);
				table.setBounds(6, 49, 690, 223);
				try {
					contentPane.add(table);

					String sql = "SELECT * FROM Students sd LEFT JOIN Departments dp ON sd.Department = dp.Id "
							+ "INNER JOIN Faculties fc ON sd.Faculty = fc.Id " + "WHERE sd.Id LIKE '%"
							+ txtSearch.getText() + "%' OR sd.Name LIKE '%" + txtSearch.getText()
							+ "%' OR sd.Surname LIKE '%" + txtSearch.getText() + "%' OR dp.Name LIKE '%"
							+ txtSearch.getText() + "%' OR fc.Name LIKE '%" + txtSearch.getText()
							+ "%' OR sd.PlaceOfBirth LIKE '%" + txtSearch.getText() + "%'";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					model.addColumn("Id");
					model.addColumn("Name");
					model.addColumn("Birthday");
					model.addColumn("PlaceOfBirth");
					model.addColumn("Department");
					model.addColumn("Faculty");

					while (rs.next()) {
						s.setId(rs.getInt("Id"));
						s.setName(rs.getString("Name"));
						s.setSurname(rs.getString("Surname"));
						s.setBirthday(rs.getString("Birthday"));
						s.setPlaceOfBirth(rs.getString("PlaceOfBirth"));
						s.setDepartment(rs.getString("dp.Name"));
						s.setFaculty(rs.getString("fc.Name"));
						Date date = rs.getDate("Birthday");
						DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
						String birthday = df.format(date);
						model.addRow(new Object[] { s.getId(), s.getName() + " " + s.getSurname(), birthday,
								s.getPlaceOfBirth(), s.getDepartment(), s.getFaculty() });
					}
					table.setModel(model);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setSize(new Dimension(690, 223));
					TableColumnModel tcm = table.getColumnModel();
					tcm.getColumn(0).setPreferredWidth(150);
					tcm.getColumn(1).setPreferredWidth(250);
					tcm.getColumn(2).setPreferredWidth(200);
					tcm.getColumn(3).setPreferredWidth(130);
					tcm.getColumn(4).setPreferredWidth(370);
					tcm.getColumn(5).setPreferredWidth(290);

					scrollPane.setLocation(6, 49);
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					contentPane.add(scrollPane);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearch.setBounds(215, 1, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				contentPane.setVisible(false);
				Homepage ah = new Homepage();
				ah.setVisible(true);
			}
		});
		btnBack.setBounds(473, 1, 117, 29);
		contentPane.add(btnBack);
	}

	public static void clearTable(final DefaultTableModel table) {
		for (int i = 0; i < table.getRowCount(); i++)
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
	}
}