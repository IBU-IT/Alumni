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

public class Counter extends JFrame {

	private JPanel contentPane; //create panel
	private JRadioButton rbtnFaculty; //create a radio button for faculties
	private JRadioButton rbtnDepartment; //create a radio button for departments 
	private Connection DBConnection; //create an object for Connection class
	private static Connection conn = new DBConnection().connect(); //create an object for connect() function
	private static PreparedStatement pstmt = null;//create an object that represents a compiled sql statement
	private static ResultSet rs = null;//create an object that shows database result related to query
	private JTextField txtSearch; //create an input text field for search

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //Eventqueue is a platform-independent class that queues events, from classes and forms
			public void run() { //run function gets triggered when the page is opened
				try {
					Counter frame = new Counter(); //create an object for the page
					frame.setVisible(true); //set page frame visible true
				} catch (Exception e) {
					e.printStackTrace(); //shows an error if occurs while frame gets opened
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Counter() { //create page elements.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the application when click X on the window
		setBounds(100, 100, 450, 300); //set location and size. (X,Y,width,height)
		contentPane = new JPanel(); //create a panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); //set content panel of the frame
		contentPane.setLayout(null); //set layout of the panel

		txtSearch = new JTextField(); //create an input text field for search
		txtSearch.setBounds(314, 5, 130, 26); //set location and size (X,Y,width,height)
		contentPane.add(txtSearch); //adds it to panel
		txtSearch.setColumns(10); //set column of the input field.

		rbtnDepartment = new JRadioButton("Count students related to their departments"); //create an radio button with displayed text
		rbtnDepartment.addActionListener(new ActionListener() {//create action listener to listen when it gets triggered
			public void actionPerformed(ActionEvent e) { //create an action event to fire up the code.
				if (txtSearch.getText().isEmpty()) { //check if input is empty
					JOptionPane.showMessageDialog(null, "You need to enter a department name!", "ERROR",
							JOptionPane.ERROR_MESSAGE); //show an error message
					rbtnDepartment.setSelected(false); //set the radio button unclicked
					return; //finish compiling the program
				}
				// if not, continue
				rbtnFaculty.setSelected(false); //set radio button of faculty false
				DefaultTableModel model = new DefaultTableModel(); //create a model for table
				JTable table = new JTable(model); //create a table
				table.setRowSelectionAllowed(false); //set row selection false. Rows are unclickable.
				table.setEnabled(false); //set table enable false. Rows cannot be edited
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.setLayout(new FlowLayout()); //set layout
				table.setBounds(6, 76, 309, 100); //set location and size (X,Y,width,height)
				try { //create a try-catch so you can catch the error if psmt or rs throws an error.
					contentPane.add(table); //adds table to the panel.
					String sql = "SELECT * FROM Students st INNER JOIN Departments dp "
							+ "ON st.Department = dp.Id WHERE dp.Name = ?";
					//create a query string. Select everything from student and connect id of department column in students table table to the id of department table
					// where entered input is equal to name column of department table.
					pstmt = conn.prepareStatement(sql); //create a prepared statement for query
					pstmt.setString(1, txtSearch.getText()); //set ? in query to search input text field
					rs = pstmt.executeQuery(); //execute the query
					model.addColumn("Name"); //add first column in table
					model.addColumn("Count"); //add second column in table
					if (rs.first() == false) { //if resultset is null which means if it cannot find anything in database
						table.setVisible(false); //set table invisible
						JOptionPane.showMessageDialog(null, txtSearch.getText() + " student does not exist in db.", "Warning",
								JOptionPane.WARNING_MESSAGE); //show an error that entered value does not exist
						rbtnDepartment.setSelected(false); //set radio button false
						return; //finish compiling the program
					}

					int counter = 0; //create a variable for counter
					if (rs.last()) { //rs.last() moves the cursor to the last row.
						counter = rs.getRow(); // assign total row number to the counter variable.
					}

					String departmentName = rs.getString("dp.Name"); //assign name column of the department table to a string variable
					model.addRow(new Object[] { departmentName, counter }); //add rows into table. First row department name, second one is total number
					//* addRow function only accepts Object inside of it. Thats why we created a new object in order to fill rows.

					table.setModel(model); //set table a model
					JScrollPane scrollPane = new JScrollPane(table); //create a scrollable panel for table
					scrollPane.setSize(new Dimension(309, 100)); //set size. setSize func. only accepts Dimension class.(width,height)
					TableColumnModel tcm = table.getColumnModel(); //create an object to fetch column models
					tcm.getColumn(0).setPreferredWidth(250); //set width of the first column
					tcm.getColumn(1).setPreferredWidth(50); //set width of the second column

					scrollPane.setLocation(6, 76); // set location for scrollable panel (x,y)
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //assign vertical scrollbar if needed
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //assign horizontal scrollbar if needed
					contentPane.add(scrollPane); //add scrollable panel to the frame
					rs.close(); //close resultset statement
					pstmt.close(); //close preparedstatement 
					rbtnDepartment.setSelected(false); //set department radio button false
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2, "ERROR", JOptionPane.ERROR_MESSAGE); //show if an error occurs
					rbtnDepartment.setSelected(false); //set department radio false
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
				table.setBounds(6, 76, 309, 100);
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

					scrollPane.setLocation(6, 76);
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
	}
}
