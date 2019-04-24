/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import javax.swing.JTextField;

import services.DBConnection;
import services.FileProcess;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import domain.CourseRecords;
import domain.ProgrammeRecords;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Window.Type;

public class CreateProgramme extends ProgrammeRecords{

	private JFrame frmCreateProgramme;
	private JTextField progCode;
	private JTextField progName;
	private JCheckBox dip;
	private JCheckBox cer;
	private JCheckBox deg;
	private JCheckBox no;
	private JCheckBox yes;
	private String name;
	private JComboBox numofCourses;
	Connection conn=null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProgramme window = new CreateProgramme();
					window.frmCreateProgramme.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void load() {
		try {
			CreateProgramme window = new CreateProgramme();
			window.frmCreateProgramme.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateProgramme() {
		initialize();
		conn=DBConnection.dbConnector();
	}
	
	public CreateProgramme(String code, String name, int maxCourse, String award, String accreditation, CourseRecords course) {
		super(code, name, maxCourse, award, accreditation, course);
	}
	
	public void clear(){
		progCode.setText("");
		progName.setText("");
		cer.setSelected(false);
		dip.setSelected(false);
		deg.setSelected(false);
		cer.setEnabled(true);
		deg.setEnabled(true);
		dip.setEnabled(true);
		no.setSelected(false);
		yes.setSelected(false);
		numofCourses.setSelectedIndex(0);
	}
	
	public void clock(){
		Thread clock=new Thread()
		{
			public void run()
			{
				try{
					for(;;){
					/*Calendar now = new GregorianCalendar();
					int day = now.get(Calendar.DAY_OF_MONTH);
					int month=now.get(Calendar.MONTH);
					int year =now.get(Calendar.YEAR);
					int seconds = now.get(Calendar.SECOND);
					int minutes=now.get(Calendar.MINUTE);
					int hour=now.get(Calendar.HOUR);*/
					
					//lblNewLabel_1.setText(hour+":"+minutes+":"+seconds);
					sleep(1000);
					}
				}catch(Exception e){
					
				}
			}
		};
		clock.start();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmCreateProgramme = new JFrame();
		frmCreateProgramme.setTitle("STAFF MENU");
		frmCreateProgramme.setResizable(false);
		frmCreateProgramme.getContentPane().setBackground(new Color(0, 102, 255));
		frmCreateProgramme.setBounds(100, 100, 757, 569);
		frmCreateProgramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateProgramme.setLocationRelativeTo(null);
		frmCreateProgramme.getContentPane().setLayout(null);
			
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 31, 212, 461);
		frmCreateProgramme.getContentPane().add(panel);
		panel.setLayout(null);
			
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(39, 0, 130, 44);
		panel.add(lblNewLabel);
	
		JLabel StaffName = new JLabel("New label");
		StaffName.setFont(new Font("Bitstream Charter", Font.BOLD, 18));
		StaffName.setForeground(Color.WHITE);
		StaffName.setBounds(50, 135, 112, 24);
		panel.add(StaffName);
		Login in =new Login();
		FileProcess fp = new FileProcess();
		String getid = in.getid();
		String[]info = new String[3];
		info=fp.getUserinfo(getid);
		try{
			name=info[0];
		}catch(Exception e){
		}
		StaffName.setText(name);
			
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("person.png"));
		lblNewLabel_2.setBounds(44, 33, 146, 97);
		panel.add(lblNewLabel_2);
			
		JButton btnNewButton = new JButton("REGISTER STUDENT");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				frmCreateProgramme.dispose();
				StaffMenu sm = new StaffMenu();
				sm.load();
			}
		});
		btnNewButton.setBounds(12, 162, 188, 25);
		panel.add(btnNewButton);
			
		JButton btnCreateProgramme = new JButton("CREATE PROGRAMME");
		btnCreateProgramme.setEnabled(false);
		btnCreateProgramme.setBounds(12, 214, 188, 25);
		panel.add(btnCreateProgramme);
			
		JButton btnModifyPrgramme = new JButton("MODIFY PRGRAMME");
		btnModifyPrgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCreateProgramme.dispose();
				ModifyProgramme pm = new ModifyProgramme();
				pm.load();
			}
		});
		btnModifyPrgramme.setBounds(12, 268, 188, 25);
		panel.add(btnModifyPrgramme);
			
		JButton btnGenerateStudentList = new JButton("STUDENT LIST");
		btnGenerateStudentList.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				frmCreateProgramme.dispose();
				StudentList sl =new StudentList();
				sl.load();
			}
		});
		btnGenerateStudentList.setBounds(12, 320, 188, 25);
		panel.add(btnGenerateStudentList);
			
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				frmCreateProgramme.dispose();	
				log.run();
			}
		});
		btnNewButton_1.setBounds(39, 424, 117, 25);
		panel.add(btnNewButton_1);
			
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 51, 255));
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(236, 12, 507, 496);
		frmCreateProgramme.getContentPane().add(panel_1);
		panel_1.setLayout(null);
			
		JLabel lblNewLabel_1 = new JLabel("Programme Code:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(23, 79, 191, 28);
		panel_1.add(lblNewLabel_1);
			
		progCode = new JTextField();
		progCode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
					String q2 = "SELECT * FROM programmeinfo WHERE ProgrammeCode=?";
					PreparedStatement check = conn.prepareStatement(q2);
					check.setString(1,progCode.getText());
					ResultSet rs1 = check.executeQuery();
					if(rs1.next()){
						JOptionPane.showMessageDialog(null, "The Programme Code: "+progCode.getText()+" Already Exist!");
						progCode.setText("");
						check.close();
					}else{
						check.close();
					}
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		progCode.setBounds(216, 85, 242, 19);
		panel_1.add(progCode);
		progCode.setColumns(10);
			
		JLabel lblProgrammeName = new JLabel("Programme Name:");
		lblProgrammeName.setForeground(Color.WHITE);
		lblProgrammeName.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProgrammeName.setBounds(23, 133, 191, 28);
		panel_1.add(lblProgrammeName);
			
		progName = new JTextField();
		progName.setColumns(10);
		progName.setBounds(216, 139, 242, 19);
		panel_1.add(progName);
			
		JLabel lblNumberOfCourses = new JLabel("Max Number of Courses:");
		lblNumberOfCourses.setForeground(Color.WHITE);
		lblNumberOfCourses.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNumberOfCourses.setBounds(23, 382, 257, 28);
		panel_1.add(lblNumberOfCourses);
						
		JButton btnNewButton_2 = new JButton("CREATE PROGRAMME");
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try{
				String Accreditation;
				String Award;
				if(progCode.getText().length()==0 || progName.getText().length()==0 || cer.isSelected()==false && dip.isSelected()==false && deg.isSelected()==false){
						JOptionPane.showMessageDialog(null, "Form incompleted");
					}else{
						CourseRecords course = new CourseRecords();
						if(yes.isSelected()){
							Accreditation = yes.getText();
						}else{
							Accreditation = no.getText();
						}
						if(cer.isSelected()){
							Award=cer.getText();
						}else{
							if(dip.isSelected()){
								Award=dip.getText();
							}else{
									Award=deg.getText();
							}
						}
						ProgrammeRecords programme = new CreateProgramme(progCode.getText(), progName.getText(), Integer.parseInt((String) numofCourses.getSelectedItem()), Award, Accreditation,course);
						
						try {
							String query = "INSERT INTO programmeinfo(ProgrammeCode, ProgrammeName, NumberofCourses, Award, Accreditation)VALUES(?,?,?,?,?)";
							PreparedStatement pst;
							pst = conn.prepareStatement(query);
							pst.setString(1,programme.getCode());
							pst.setString(2, programme.getName());
							pst.setString(3, String.valueOf(programme.getMaxCourse()));
							pst.setString(4, programme.getAward());
							pst.setString(5, programme.getAccreditation());
							pst.execute();
							pst.close();	
							clear();
							programme.display();
							JOptionPane.showMessageDialog(null, "Programme Created!");
							int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Register Courses for this Programme now?","Register Courses",JOptionPane.YES_NO_OPTION);
							if(dialogResult == JOptionPane.YES_OPTION){
								ModifyProgramme mc = new ModifyProgramme();
								frmCreateProgramme.dispose();
								mc.load();
							}else{
								
							}
						} catch (Exception err) {
							err.printStackTrace();
						}
						
					}
				
			}catch(Exception err){
				System.out.println(err);
			}
		}
		});
		btnNewButton_2.setBounds(72, 443, 183, 34);
		panel_1.add(btnNewButton_2);
			
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 173, 483, 2);
		panel_1.add(separator);
			
		JLabel lblProgrammeType = new JLabel("PROGRAMME TYPE");
		lblProgrammeType.setBackground(new Color(0, 153, 255));
		lblProgrammeType.setFont(new Font("Bitstream Charter", Font.BOLD, 25));
		lblProgrammeType.setForeground(new Color(255, 255, 255));
		lblProgrammeType.setBounds(143, 187, 247, 40);
		panel_1.add(lblProgrammeType);
			
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 225, 483, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 429, 483, 2);
		panel_1.add(separator_2);
			
		JLabel lblCreateProgramme = new JLabel("CREATE PROGRAMME");
		lblCreateProgramme.setForeground(Color.WHITE);
		lblCreateProgramme.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		lblCreateProgramme.setBounds(84, 12, 340, 40);
		panel_1.add(lblCreateProgramme);
			
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 52, 483, 2);
		panel_1.add(separator_3);
	
		cer = new JCheckBox("Certificate");
		cer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try{
					if(cer.isSelected()){
					yes.setSelected(true);
					numofCourses.setSelectedIndex(1);
					dip.setEnabled(false);
					deg.setEnabled(false);
				}else{
					yes.setSelected(false);
					numofCourses.setSelectedIndex(0);
					dip.setEnabled(true);
					deg.setEnabled(true);
				}
			}catch(Exception err){
				System.out.println(err);
					}
			}
		});
		cer.setForeground(new Color(255, 255, 255));
		cer.setFont(new Font("Dialog", Font.BOLD, 18));
		cer.setBackground(new Color(0, 153, 255));
		cer.setBounds(33, 235, 207, 23);
		panel_1.add(cer);
			
		dip = new JCheckBox("Diploma");
		dip.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try{
				if(dip.isSelected()){
					no.setSelected(true);
					numofCourses.setSelectedIndex(2);
					cer.setEnabled(false);
					deg.setEnabled(false);
					}else{
						no.setSelected(false);
						numofCourses.setSelectedIndex(0);
						cer.setEnabled(true);
						deg.setEnabled(true);	
					}
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		dip.setForeground(Color.WHITE);
		dip.setFont(new Font("Dialog", Font.BOLD, 18));
		dip.setBackground(new Color(0, 153, 255));
		dip.setBounds(33, 285, 207, 23);
		panel_1.add(dip);
			
		deg = new JCheckBox("Associate Degree");
		deg.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try{
					if(deg.isSelected()){
					yes.setSelected(true);
					numofCourses.setSelectedIndex(3);
					dip.setEnabled(false);
					cer.setEnabled(false);
				}else{
					yes.setSelected(false);
					numofCourses.setSelectedIndex(0);
					dip.setEnabled(true);
					cer.setEnabled(true);
				}
			}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		deg.setForeground(Color.WHITE);
		deg.setFont(new Font("Dialog", Font.BOLD, 18));
		deg.setBackground(new Color(0, 153, 255));
		deg.setBounds(33, 328, 207, 23);
		panel_1.add(deg);
	
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(261, 239, 19, 117);
		panel_1.add(separator_4);
			
		yes = new JCheckBox("ACCREDITED");
		yes.setForeground(Color.WHITE);
		yes.setFont(new Font("Dialog", Font.BOLD, 18));
		yes.setEnabled(false);
		yes.setBackground(new Color(255, 255, 255));
		yes.setBounds(288, 255, 207, 23);
		panel_1.add(yes);
			
		no = new JCheckBox("UNACCREDITED");
		no.setForeground(Color.WHITE);
		no.setFont(new Font("Dialog", Font.BOLD, 18));
		no.setEnabled(false);
		no.setBackground(new Color(255, 255, 255));
		no.setBounds(288, 311, 207, 23);
		panel_1.add(no);
			
		numofCourses = new JComboBox();
		numofCourses.setFont(new Font("Dialog", Font.BOLD, 30));
		numofCourses.setEnabled(false);
		numofCourses.setEditable(true);
		numofCourses.setForeground(new Color(0, 51, 204));
		numofCourses.setModel(new DefaultComboBoxModel(new String[] {"", "4", "6", "8"}));
		numofCourses.setSelectedIndex(0);
		numofCourses.setBounds(288, 382, 207, 35);
		panel_1.add(numofCourses);
			
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(24, 368, 483, 2);
		panel_1.add(separator_5);
			
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(267, 443, 183, 34);
		panel_1.add(btnClear);
	
		JMenuBar menuBar = new JMenuBar();
		frmCreateProgramme.setJMenuBar(menuBar);
			
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Project");
		mntmNewMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				try{
					Runtime.getRuntime().exec("CIT2004 - SEM1 - Programming Project 2016-2017.pdf");
				}catch(Exception err){
					try{
						Desktop d = java.awt.Desktop.getDesktop ();
							d.open (new java.io.File ("CIT2004 - SEM1 - Programming Project 2016-2017.pdf"));
					}catch(Exception err2){
						System.out.println(err2);
					}
				}
			}
		});
		mnAbout.add(mntmNewMenuItem);
	}
}
