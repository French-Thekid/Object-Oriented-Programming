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
import java.sql.*;
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

import services.DBConnection;
import services.FileProcess;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domain.StudentRecords;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentList extends StudentRecords{

	private JFrame studentList;
	private JButton SeeAll;
	private JTable table;
	private JTextField find;
	private String name;
	Connection conn=null;
	
	public void load() {
		try {
			StudentList window = new StudentList();
			window.studentList.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StudentList() {
		initialize();
		conn=DBConnection.dbConnector();
	}
	
	public void clock(){
		Thread clock=new Thread()
		{
			public void run()
			{
				try{
					for(;;){
				/*	Calendar now = new GregorianCalendar();
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
	
	private void initialize() {
		studentList = new JFrame();
		studentList.setResizable(false);
		studentList.setTitle("STAFF ");
		studentList.getContentPane().setBackground(new Color(0, 102, 255));
		studentList.setBounds(100, 100, 1023, 577);
		studentList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentList.setLocationRelativeTo(null);
		studentList.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 31, 212, 461);
		studentList.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(39, 0, 130, 44);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("REGISTER STUDENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentList.dispose();
				StaffMenu sm = new StaffMenu();
				sm.load();
			}
		});
		btnNewButton.setBounds(12, 172, 188, 25);
		panel.add(btnNewButton);
		
		JButton btnCreateProgramme = new JButton("CREATE PROGRAMME");
		btnCreateProgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentList.dispose();
				CreateProgramme rs = new CreateProgramme();
				rs.load();
			}
		});
		btnCreateProgramme.setBounds(12, 224, 188, 25);
		panel.add(btnCreateProgramme);
		
		JButton btnModifyPrgramme = new JButton("MODIFY PRGRAMME");
		btnModifyPrgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentList.dispose();
				ModifyProgramme pm = new ModifyProgramme();
				pm.load();
			}
		});
		btnModifyPrgramme.setBounds(12, 278, 188, 25);
		panel.add(btnModifyPrgramme);
		
		JButton btnGenerateStudentList = new JButton("STUDENT LIST");
		btnGenerateStudentList.setEnabled(false);
		btnGenerateStudentList.setBounds(12, 330, 188, 25);
		panel.add(btnGenerateStudentList);
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				studentList.dispose();	
				log.run();
			}
		});
		btnNewButton_1.setBounds(39, 424, 117, 25);
		panel.add(btnNewButton_1);
		
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
			//System.out.println(e);
		}
		StaffName.setText(name);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("person.png"));
		lblNewLabel_2.setBounds(44, 33, 146, 97);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(236, 12, 773, 504);
		studentList.getContentPane().add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 120, 749, 372);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		find = new JTextField();
		find.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{	
					String Query= "SELECT * FROM studentinfo WHERE ProgrammeCode=?";
					PreparedStatement pst= conn.prepareStatement(Query);
					pst.setString(1, find.getText());
					ResultSet rs= pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				  }
				  catch(SQLException ex){
					  JOptionPane.showMessageDialog(null, ex.getMessage());
				  }
			}
		});
		find.setBounds(227, 75, 222, 19);
		panel_1.add(find);
		find.setColumns(10);
		
		JLabel lblProgrammeCode = new JLabel("Programme Code:");
		lblProgrammeCode.setFont(new Font("Dialog", Font.BOLD, 18));
		lblProgrammeCode.setForeground(new Color(255, 255, 255));
		lblProgrammeCode.setBounds(42, 74, 182, 20);
		panel_1.add(lblProgrammeCode);
		
		SeeAll = new JButton("VIEW ALL STUDENTS");
		SeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String q = "SELECT * FROM studentinfo";
					PreparedStatement pst= conn.prepareStatement(q);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		SeeAll.setBounds(472, 72, 235, 25);
		panel_1.add(SeeAll);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 53, 749, 10);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 109, 749, 10);
		panel_1.add(separator_1);
		
		JLabel lblGenerateStudentList = new JLabel("GENERATE STUDENT LIST");
		lblGenerateStudentList.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		lblGenerateStudentList.setForeground(new Color(255, 255, 255));
		lblGenerateStudentList.setBounds(186, 12, 377, 29);
		panel_1.add(lblGenerateStudentList);
		JMenuBar menuBar = new JMenuBar();
		studentList.setJMenuBar(menuBar);
		
		JMenu mnAccount = new JMenu("Account Settings");
		menuBar.add(mnAccount);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword cp = new ChangePassword();
				cp.start();
			}
		});
		mnAccount.add(mntmChangePassword);
		
		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmProject = new JMenuItem("Project");
		mntmProject.addActionListener(new ActionListener() {
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
		mnNewMenu.add(mntmProject);
	}
}
