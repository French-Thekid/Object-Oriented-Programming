/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package gui;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.StaffRecords;
import services.FileProcess;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class CreateStaff extends StaffRecords{

	private JFrame frmStaffAccount;
	private JTextField firstName;
	private JTextField username;
	private JComboBox faculty;
	private JComboBox department;
	private JDateChooser DateC;
	private JPasswordField passwordField;
	private JPasswordField confirmpasswordField;
	private JTextField lastName;
	public String usr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					CreateStaff window = new CreateStaff();
					window.frmStaffAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void run() {
		try {
			CreateStaff window = new CreateStaff();
			window.frmStaffAccount.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateStaff() {
		initialize();
	}
	
	public CreateStaff(String id, String firstName, String lastName,String faculty, String department, String employmentDate) {
		super(id, firstName, lastName, faculty, department, employmentDate);
		super.setLastName(lastName);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmStaffAccount = new JFrame();
		frmStaffAccount.setTitle("Staff Account");
		frmStaffAccount.getContentPane().setBackground(new Color(0, 102, 255));
		frmStaffAccount.setResizable(false);
		frmStaffAccount.setBounds(100, 100, 614, 524);
		frmStaffAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStaffAccount.setLocationRelativeTo(null);
		frmStaffAccount.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 12, 588, 472);
		frmStaffAccount.getContentPane().add(panel);
		panel.setLayout(null);
		
		firstName = new JTextField();
		firstName.setBounds(118, 76, 166, 19);
		panel.add(firstName);
		firstName.setColumns(10);
		
		JLabel fname = new JLabel("First Name:");
		fname.setForeground(new Color(255, 255, 255));
		fname.setBounds(22, 78, 99, 15);
		panel.add(fname);
		
		JLabel lname = new JLabel("Last Name:");
		lname.setForeground(Color.WHITE);
		lname.setBounds(302, 78, 99, 15);
		panel.add(lname);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(265, 163, 198, 19);
		panel.add(username);
		
		JLabel usrname = new JLabel("ID Number:");
		usrname.setForeground(Color.WHITE);
		usrname.setBounds(123, 165, 99, 15);
		panel.add(usrname);
		
		JLabel pass = new JLabel("Password:");
		pass.setForeground(Color.WHITE);
		pass.setBounds(123, 214, 75, 15);
		panel.add(pass);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(265, 212, 198, 19);
		panel.add(passwordField);
		
		confirmpasswordField = new JPasswordField();
		confirmpasswordField.setBounds(265, 256, 198, 19);
		panel.add(confirmpasswordField);
		
		JLabel confirmpasss = new JLabel("Confirm Password:");
		confirmpasss.setForeground(Color.WHITE);
		confirmpasss.setBounds(123, 258, 133, 15);
		panel.add(confirmpasss);
		
		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setForeground(Color.WHITE);
		lblDomain.setBounds(123, 346, 99, 15);
		panel.add(lblDomain);
		
		JComboBox domain = new JComboBox();
		domain.setEnabled(false);
		domain.setEditable(true);
		domain.setBounds(265, 341, 198, 24);
		domain.setModel(new DefaultComboBoxModel(new String[]{"STAFF"}));
		panel.add(domain);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(400, 76, 159, 19);
		panel.add(lastName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 150, 564, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 377, 564, 2);
		panel.add(separator_1);
		
		JButton btnNewButton = new JButton("ADD STAFF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String date = df.format(DateC.getDate());
				StaffRecords sr = new CreateStaff(username.getText(), firstName.getText(), lastName.getText(), String.valueOf(department.getSelectedItem()), String.valueOf(faculty.getSelectedItem()), date);
				FileProcess fp = new FileProcess();
				String name = new String(sr.getFirstName()+" "+sr.getLastName());
				String passwrd = new String(passwordField.getPassword());
				String confirmPasswrd = new String(confirmpasswordField.getPassword());
				String type = String.valueOf(domain.getSelectedItem());
				
				fp.validate(name, sr.getIdNumber(), passwrd, confirmPasswrd, type,sr.getDepartment(), sr.getFaculty(), date);
			}
		});
		btnNewButton.setBounds(192, 391, 221, 32);
		panel.add(btnNewButton);
		
		JLabel lblAddStaffAccount = new JLabel("Staff Account");
		lblAddStaffAccount.setFont(new Font("Courier 10 Pitch", Font.BOLD, 47));
		lblAddStaffAccount.setForeground(new Color(255, 255, 255));
		lblAddStaffAccount.setBounds(129, 12, 377, 52);
		panel.add(lblAddStaffAccount);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 62, 564, 2);
		panel.add(separator_2);
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				frmStaffAccount.dispose();	
				log.run();
			}
		});
		btnNewButton_1.setBounds(252, 435, 99, 25);
		panel.add(btnNewButton_1);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setBounds(302, 123, 99, 15);
		panel.add(lblDepartment);
		
		JLabel lblFaculty = new JLabel("Faculty:");
		lblFaculty.setForeground(Color.WHITE);
		lblFaculty.setBounds(22, 123, 99, 15);
		panel.add(lblFaculty);
		
		JLabel lblDateEmployed = new JLabel("Date Employed:");
		lblDateEmployed.setForeground(Color.WHITE);
		lblDateEmployed.setBounds(129, 303, 127, 15);
		panel.add(lblDateEmployed);
		
		DefaultComboBoxModel  COHSdepartments = new DefaultComboBoxModel(new String[]{"SON","SAHN","SOP"});
		DefaultComboBoxModel  COBAMdepartments= new DefaultComboBoxModel(new String[]{"SBA","SHTM","SEEL","SAM"});
		DefaultComboBoxModel  FELSdepartments = new DefaultComboBoxModel(new String[]{"STVE","SHSS"});
		DefaultComboBoxModel  FOBEdepartments = new DefaultComboBoxModel(new String[]{"SBLM","CSA"});
		DefaultComboBoxModel  FENCdepartments = new DefaultComboBoxModel(new String[]{"SE","SCIT"});
		DefaultComboBoxModel  FOLdepartments  = new DefaultComboBoxModel(new String[]{"LAW"});
		DefaultComboBoxModel  FOSSdepartments = new DefaultComboBoxModel(new String[]{"SONAS","SOMAS","CSOSS"});
		DefaultComboBoxModel  COMOVdepartments= new DefaultComboBoxModel(new String[]{"Medicine","PHHT","OHS","VS"});
		
		faculty = new JComboBox();
		faculty.setModel(new DefaultComboBoxModel(new String[] {"COHS", "COBAM", "FELS", "FOBE", "FENC", "FOL", "FOSS", "COMOV"}));
		faculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (faculty.getSelectedIndex()==0){
					department.setModel(COHSdepartments);
				}				
				else if (faculty.getSelectedIndex()==1){
					department.setModel(COBAMdepartments);
				}
				else if (faculty.getSelectedIndex()==2){
					department.setModel(FELSdepartments);
				}
				else if (faculty.getSelectedIndex()==3){
					department.setModel(FOBEdepartments);
				}
				else if (faculty.getSelectedIndex()==4){
					department.setModel(FENCdepartments);
				}
				else if (faculty.getSelectedIndex()==5){
					department.setModel(FOLdepartments);
				}
				else if (faculty.getSelectedIndex()==6){
					department.setModel(FOSSdepartments);
				}
				else if (faculty.getSelectedIndex()==7){
					department.setModel(COMOVdepartments);
				}
			}
		});
		
		faculty.setBounds(118, 118, 166, 24);
		panel.add(faculty);
		
		department = new JComboBox();
		department.setBounds(400, 118, 166, 24);
		panel.add(department);
		
		DateC = new JDateChooser();
		DateC.setDateFormatString("MMM d, yyyy");
		DateC.setBounds(265, 298, 198, 20);
		panel.add(DateC);
	}
}
