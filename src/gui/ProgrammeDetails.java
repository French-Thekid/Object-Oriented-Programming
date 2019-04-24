/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package gui;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;


import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import services.DBConnection;
import services.FileProcess;

import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.sql.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import domain.StudentRecords;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ProgrammeDetails extends StudentRecords{

	private JTextField progCode;
	private JTextField stuId;
	private JTextField stuName;
	private JTextField progName;
	private JTextField award;
	private JTextField accreditation;
	private JSeparator separator_4;
	private JTextField maxCourses;
	private JTextArea descritption;
	private JLabel en1;
	private JLabel en2;
	private JLabel en3;
	private JLabel en4;
	private JLabel en5;
	private JLabel en6;
	private JLabel en7;
	private JLabel en8;
	private JLabel c1;
	private JLabel c2;
	private JLabel c3;
	private JLabel c4;
	private JLabel c5;
	private JLabel c6;
	private JLabel c7;
	private JLabel c8;
	private JLabel cred1;
	private JLabel cred2;
	private JLabel cred3;
	private JLabel cred4;
	private JLabel cred5;
	private JLabel cred6;
	private JLabel cred7;
	private JLabel cred8;
	private JLabel pre1;
	private JLabel pre2;
	private JLabel pre3;
	private JLabel pre4;
	private JLabel pre5;
	private JLabel pre6;
	private JLabel pre7;
	private JLabel pre8;
	private String getid; 
	private JFrame student;
	private String name;
	private JLabel lblDescription;
	private JLabel lblCodes;
	private JLabel code1;
	private JLabel code2;
	private JLabel code3;
	private JLabel code4;
	private JLabel code5;
	private JLabel code6;
	private JLabel code7;
	private JLabel code8;
	Connection conn=null;
	
	public void live(){
		try {
			ProgrammeDetails window = new ProgrammeDetails();
			window.student.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ProgrammeDetails(){
		initialize();
		clock();
		conn=DBConnection.dbConnector();
	}
	
	public void clock(){
		Thread clock=new Thread()
		{
			public void run()
			{
				try{
					for(;;){
					//Calendar now = new GregorianCalendar();
					/*int seconds = now.get(Calendar.SECOND);
					int minutes=now.get(Calendar.MINUTE);
					int hour=now.get(Calendar.HOUR);*/
					//date.setText(month+"/"+day+"/"+year);
					sleep(1000);
					}
				}catch(Exception e){
					
				}
			}
		};
		clock.start();
	}
	
	public void loadCertificatecourses(String progCode, String studId){
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd2;
		ResultSet rs;
		ResultSet rs2;
		String q = "SELECT * FROM certificate WHERE ProgrammeCode=?";
		String q2 = "SELECT * FROM certificateenrollinfo WHERE studentId=?";
		PreparedStatement pst;
		PreparedStatement pst2;
		String[] courseInfo=null;
		String[] courseInfo2=null;
		try {
			pst = conn.prepareStatement(q);
			pst.setString(1,progCode);
			rs = pst.executeQuery();
			rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			courseInfo = new String[columnsNumber+1];
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					courseInfo[i]=rs.getString(i);
					if(courseInfo[i].equals("")||courseInfo[i].equals("-")){
						courseInfo[i]="NO LONGER OFFERED";
					}
			    }
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pst2 = conn.prepareStatement(q2);
			pst2.setString(1,studId);
			rs2 = pst2.executeQuery();
			rsmd2 = rs2.getMetaData();
			int columnsNumber2 = rsmd2.getColumnCount();
			courseInfo2 = new String[columnsNumber2+1];
			while (rs2.next()) {
				for (int i = 1; i <= columnsNumber2; i++) {
					courseInfo2[i]=rs2.getString(i);
			    }
			}
			pst2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		descritption.setText(courseInfo[6]);
		code1.setText(courseInfo[15]);
		code2.setText(courseInfo[16]);
		code3.setText(courseInfo[17]);
		code4.setText(courseInfo[18]);
		c1.setText(courseInfo[2]);
		c2.setText(courseInfo[3]);
		c3.setText(courseInfo[4]);
		c4.setText(courseInfo[5]);
		cred1.setText(courseInfo[11]);
		cred2.setText(courseInfo[12]);
		cred3.setText(courseInfo[13]);
		cred4.setText(courseInfo[14]);
		pre1.setText(courseInfo[7]);
		pre2.setText(courseInfo[8]);
		pre3.setText(courseInfo[9]);
		pre4.setText(courseInfo[10]);
		if(c1.getText().equals("NO LONGER OFFERED")){code1.setEnabled(false);c1.setEnabled(false); pre1.setEnabled(false);en1.setEnabled(false);}
		if(c2.getText().equals("NO LONGER OFFERED")){code2.setEnabled(false);c2.setEnabled(false); pre2.setEnabled(false);en2.setEnabled(false);}
		if(c3.getText().equals("NO LONGER OFFERED")){code3.setEnabled(false);c3.setEnabled(false); pre3.setEnabled(false);en3.setEnabled(false);}
		if(c4.getText().equals("NO LONGER OFFERED")){code4.setEnabled(false);c4.setEnabled(false); pre4.setEnabled(false);en4.setEnabled(false);}
		if(courseInfo[2].equals(courseInfo2[1])){en1.setText("YES");}else{en1.setText("NO");}
		if(courseInfo[3].equals(courseInfo2[2])){en2.setText("YES");}else{en2.setText("NO");}
		if(courseInfo[4].equals(courseInfo2[3])){en3.setText("YES");}else{en3.setText("NO");}
		if(courseInfo[5].equals(courseInfo2[4])){en4.setText("YES");}else{en4.setText("NO");}
		c5.setText("UNAVAILABLE");
		c6.setText("UNAVAILABLE");
		c7.setText("UNAVAILABLE");
		c8.setText("UNAVAILABLE");
		cred5.setText("0");
		cred6.setText("0");
		cred7.setText("0");
		cred8.setText("0");
		pre5.setText("UNAVAILABLE");
		pre6.setText("UNAVAILABLE");
		pre7.setText("UNAVAILABLE");
		pre8.setText("UNAVAILABLE");
		en5.setText("NO");
		en6.setText("NO");
		en7.setText("NO");
		en8.setText("NO");
		code5.setText("----");
		code6.setText("----");
		code7.setText("----");
		code8.setText("----");
		c5.setEnabled(false);
		c6.setEnabled(false);
		c7.setEnabled(false);
		c8.setEnabled(false);
		cred5.setEnabled(false);
		cred6.setEnabled(false);
		cred7.setEnabled(false);
		cred8.setEnabled(false);
		pre5.setEnabled(false);
		pre6.setEnabled(false);
		pre7.setEnabled(false);
		pre8.setEnabled(false);
		en5.setEnabled(false);
		en6.setEnabled(false);
		en7.setEnabled(false);
		en8.setEnabled(false);
		code5.setEnabled(false);
		code6.setEnabled(false);
		code7.setEnabled(false);
		code8.setEnabled(false);
	}
	
	public void loadDiplomacourses(String progCode, String studId){
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd2;
		ResultSet rs;
		ResultSet rs2;
		String q = "SELECT * FROM diploma WHERE ProgrammeCode=?";
		String q2 = "SELECT * FROM diplomaenrollinfo WHERE studentId=?";
		PreparedStatement pst;
		PreparedStatement pst2;
		String[] courseInfo=null;
		String[] courseInfo2=null;
		try {
			pst = conn.prepareStatement(q);
			pst.setString(1,progCode);
			rs = pst.executeQuery();
			rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			courseInfo = new String[columnsNumber+1];
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					courseInfo[i]=rs.getString(i);
					if(courseInfo[i].equals("")||courseInfo[i].equals("-")){
						courseInfo[i]="NO LONGER OFFERED";
					}
			    }
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pst2 = conn.prepareStatement(q2);
			pst2.setString(1,studId);
			rs2 = pst2.executeQuery();
			rsmd2 = rs2.getMetaData();
			int columnsNumber2 = rsmd2.getColumnCount();
			courseInfo2 = new String[columnsNumber2+1];
			while (rs2.next()) {
				for (int i = 1; i <= columnsNumber2; i++) {
					courseInfo2[i]=rs2.getString(i);
			    }
			}
			pst2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		descritption.setText(courseInfo[8]);
		code1.setText(courseInfo[21]);
		code2.setText(courseInfo[22]);
		code3.setText(courseInfo[23]);
		code4.setText(courseInfo[24]);
		code5.setText(courseInfo[25]);
		code6.setText(courseInfo[26]);
		c1.setText(courseInfo[2]);
		c2.setText(courseInfo[3]);
		c3.setText(courseInfo[4]);
		c4.setText(courseInfo[5]);
		c5.setText(courseInfo[6]);
		c6.setText(courseInfo[7]);
		cred1.setText(courseInfo[15]);
		cred2.setText(courseInfo[16]);
		cred3.setText(courseInfo[17]);
		cred4.setText(courseInfo[18]);
		cred5.setText(courseInfo[19]);
		cred6.setText(courseInfo[20]);
		pre1.setText(courseInfo[9]);
		pre2.setText(courseInfo[10]);
		pre3.setText(courseInfo[11]);
		pre4.setText(courseInfo[12]);
		pre5.setText(courseInfo[13]);
		pre6.setText(courseInfo[14]);
		if(c1.getText().equals("NO LONGER OFFERED")){code1.setEnabled(false);c1.setEnabled(false); pre1.setEnabled(false);en1.setEnabled(false);}
		if(c2.getText().equals("NO LONGER OFFERED")){code2.setEnabled(false);c2.setEnabled(false); pre2.setEnabled(false);en2.setEnabled(false);}
		if(c3.getText().equals("NO LONGER OFFERED")){code3.setEnabled(false);c3.setEnabled(false); pre3.setEnabled(false);en3.setEnabled(false);}
		if(c4.getText().equals("NO LONGER OFFERED")){code4.setEnabled(false);c4.setEnabled(false); pre4.setEnabled(false);en4.setEnabled(false);}
		if(c5.getText().equals("NO LONGER OFFERED")){code5.setEnabled(false);c5.setEnabled(false); pre5.setEnabled(false);en5.setEnabled(false);}
		if(c6.getText().equals("NO LONGER OFFERED")){code6.setEnabled(false);c6.setEnabled(false); pre6.setEnabled(false);en6.setEnabled(false);}
		if(courseInfo[2].equals(courseInfo2[1])){en1.setText("YES");}else{en1.setText("NO");}
		if(courseInfo[3].equals(courseInfo2[2])){en2.setText("YES");}else{en2.setText("NO");}
		if(courseInfo[4].equals(courseInfo2[3])){en3.setText("YES");}else{en3.setText("NO");}
		if(courseInfo[5].equals(courseInfo2[4])){en4.setText("YES");}else{en4.setText("NO");}
		if(courseInfo[6].equals(courseInfo2[5])){en5.setText("YES");}else{en5.setText("NO");}
		if(courseInfo[7].equals(courseInfo2[6])){en6.setText("YES");}else{en6.setText("NO");}
		c7.setText("UNAVAILABLE");
		c8.setText("UNAVAILABLE");
		cred7.setText("0");
		cred8.setText("0");
		pre7.setText("UNAVAILABLE");
		pre8.setText("UNAVAILABLE");
		en7.setText("NO");
		en8.setText("NO");
		code7.setText("----");
		code8.setText("----");
		c7.setEnabled(false);
		c8.setEnabled(false);
		cred7.setEnabled(false);
		cred8.setEnabled(false);
		pre7.setEnabled(false);
		pre8.setEnabled(false);
		en7.setEnabled(false);
		en8.setEnabled(false);
		code7.setEnabled(false);
		code8.setEnabled(false);
	}
	
	public void loadAssociatecourses(String progCode, String studId){
		ResultSetMetaData rsmd;
		ResultSetMetaData rsmd2;
		ResultSet rs;
		ResultSet rs2;
		String q = "SELECT * FROM associatedegree WHERE ProgrammeCode=?";
		String q2 = "SELECT * FROM degreeenrollinfo WHERE studentId=?";
		PreparedStatement pst;
		PreparedStatement pst2;
		String[] courseInfo=null;
		String[] courseInfo2=null;
		try {
			pst = conn.prepareStatement(q);
			pst.setString(1,progCode);
			rs = pst.executeQuery();
			rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			courseInfo = new String[columnsNumber+1];
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					courseInfo[i]=rs.getString(i);
					if(courseInfo[i].equals("")||courseInfo[i].equals("-")){
						courseInfo[i]="NO LONGER OFFERED";
					}
			    }
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pst2 = conn.prepareStatement(q2);
			pst2.setString(1,studId);
			rs2 = pst2.executeQuery();
			rsmd2 = rs2.getMetaData();
			int columnsNumber2 = rsmd2.getColumnCount();
			courseInfo2 = new String[columnsNumber2+1];
			while (rs2.next()) {
				for (int i = 1; i <= columnsNumber2; i++) {
					courseInfo2[i]=rs2.getString(i);
			    }
			}
			pst2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		descritption.setText(courseInfo[8]);
		code1.setText(courseInfo[21]);
		code2.setText(courseInfo[22]);
		code3.setText(courseInfo[23]);
		code4.setText(courseInfo[24]);
		code5.setText(courseInfo[25]);
		code6.setText(courseInfo[26]);
		code7.setText(courseInfo[33]);
		code8.setText(courseInfo[34]);
		c1.setText(courseInfo[2]);
		c2.setText(courseInfo[3]);
		c3.setText(courseInfo[4]);
		c4.setText(courseInfo[5]);
		c5.setText(courseInfo[6]);
		c6.setText(courseInfo[7]);
		c7.setText(courseInfo[27]);
		c8.setText(courseInfo[28]);
		cred1.setText(courseInfo[15]);
		cred2.setText(courseInfo[16]);
		cred3.setText(courseInfo[17]);
		cred4.setText(courseInfo[18]);
		cred5.setText(courseInfo[19]);
		cred6.setText(courseInfo[20]);
		cred7.setText(courseInfo[31]);
		cred8.setText(courseInfo[32]);
		pre1.setText(courseInfo[9]);
		pre2.setText(courseInfo[10]);
		pre3.setText(courseInfo[11]);
		pre4.setText(courseInfo[12]);
		pre5.setText(courseInfo[13]);
		pre6.setText(courseInfo[14]);
		pre7.setText(courseInfo[29]);
		pre8.setText(courseInfo[30]);
		if(c1.getText().equals("NO LONGER OFFERED")){code1.setEnabled(false);c1.setEnabled(false); pre1.setEnabled(false);en1.setEnabled(false);}
		if(c2.getText().equals("NO LONGER OFFERED")){code2.setEnabled(false);c2.setEnabled(false); pre2.setEnabled(false);en2.setEnabled(false);}
		if(c3.getText().equals("NO LONGER OFFERED")){code3.setEnabled(false);c3.setEnabled(false); pre3.setEnabled(false);en3.setEnabled(false);}
		if(c4.getText().equals("NO LONGER OFFERED")){code4.setEnabled(false);c4.setEnabled(false); pre4.setEnabled(false);en4.setEnabled(false);}
		if(c5.getText().equals("NO LONGER OFFERED")){code5.setEnabled(false);c5.setEnabled(false); pre5.setEnabled(false);en5.setEnabled(false);}
		if(c6.getText().equals("NO LONGER OFFERED")){code6.setEnabled(false);c6.setEnabled(false); pre6.setEnabled(false);en6.setEnabled(false);}
		if(c7.getText().equals("NO LONGER OFFERED")){code7.setEnabled(false);c7.setEnabled(false); pre7.setEnabled(false);en7.setEnabled(false);}
		if(c8.getText().equals("NO LONGER OFFERED")){code8.setEnabled(false);c8.setEnabled(false); pre8.setEnabled(false);en8.setEnabled(false);}
		if(courseInfo[2].equals(courseInfo2[1])){en1.setText("YES");}else{en1.setText("NO");}
		if(courseInfo[3].equals(courseInfo2[2])){en2.setText("YES");}else{en2.setText("NO");}
		if(courseInfo[4].equals(courseInfo2[3])){en3.setText("YES");}else{en3.setText("NO");}
		if(courseInfo[5].equals(courseInfo2[4])){en4.setText("YES");}else{en4.setText("NO");}
		if(courseInfo[6].equals(courseInfo2[5])){en5.setText("YES");}else{en5.setText("NO");}
		if(courseInfo[7].equals(courseInfo2[6])){en6.setText("YES");}else{en6.setText("NO");}
		if(courseInfo[27].equals(courseInfo2[7])){en7.setText("YES");}else{en7.setText("NO");}
		if(courseInfo[28].equals(courseInfo2[8])){en8.setText("YES");}else{en8.setText("NO");}
	}
	
	private void initialize() {
		student = new JFrame();
		student.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try{
					ResultSet rs;
					ResultSet rs2;
					ResultSetMetaData rsmd;
					ResultSetMetaData rsmd2;
					String q = "SELECT * FROM studentinfo WHERE IDNumber=?";
					String q2 = "SELECT * FROM programmeinfo WHERE ProgrammeCode=?";
					PreparedStatement pst= conn.prepareStatement(q);
					PreparedStatement pst2= conn.prepareStatement(q2);
					
					pst.setString(1,getid);
					rs = pst.executeQuery();
					rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();
					String[] loginfo = new String[columnsNumber+1];
					while (rs.next()) {
						for (int i = 1; i <= columnsNumber; i++) {
					         loginfo[i]=rs.getString(i);
					    }
					}
					stuId.setText(loginfo[1]);
					stuName.setText(loginfo[2]+" "+loginfo[3]);
					progCode.setText(loginfo[6]);
					pst.close();
					
					pst2.setString(1,progCode.getText());
					rs2 = pst2.executeQuery();
					rsmd2 = rs2.getMetaData();
					int columnsNumber1 = rsmd2.getColumnCount();
					String[] proginfo=new String[columnsNumber1+1];
					while (rs2.next()) {
						for (int i = 1; i <= columnsNumber1; i++) {
							proginfo[i]=rs2.getString(i);
					    }
					}
					pst2.close();
					
					progName.setText(proginfo[2]);
					maxCourses.setText(proginfo[3]);
					award.setText(proginfo[4]);
					accreditation.setText(proginfo[5]);
					if(proginfo[4].equals("Associate Degree")){
						loadAssociatecourses(loginfo[6],loginfo[1]);
					}else{
						if(proginfo[4].equals("Diploma")){
							loadDiplomacourses(loginfo[6],loginfo[1]);
						}else{
							if(proginfo[4].equals("Certificate")){
								loadCertificatecourses(loginfo[6],loginfo[1]);
							}
						}
					}
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		student.setTitle("STUDENT");
		student.setResizable(false);
		student.getContentPane().setBackground(new Color(0, 102, 255));
		student.setBounds(100, 100, 1010, 684);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		student.setLocationRelativeTo(null);
		student.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 31, 212, 561);
		student.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				student.dispose();	
				log.run();
			}
		});
		btnNewButton.setBounds(44, 525, 117, 25);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("Welcome");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setBounds(44, 12, 130, 44);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("person.png"));
		label_1.setBounds(44, 45, 146, 97);
		panel.add(label_1);
		
		JLabel studentName = new JLabel("<dynamic>");
		studentName.setForeground(Color.WHITE);
		studentName.setFont(new Font("Bitstream Charter", Font.BOLD, 18));
		studentName.setBounds(50, 147, 112, 24);
		panel.add(studentName);
		Login in =new Login();
		FileProcess fp = new FileProcess();
		getid = in.getid();
		String[]info = new String[3];
		info=fp.getUserinfo(getid);
		try{
			name=info[0];
		}
		catch(Exception e){
			
		}
		studentName.setText(name);
		
		JButton btnViewProgrammeDetails = new JButton("PROGRAMME DETAILS");
		btnViewProgrammeDetails.setEnabled(false);
		btnViewProgrammeDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgrammeDetails pd = new ProgrammeDetails();
				pd.live();
				student.dispose();
			}
		});
		btnViewProgrammeDetails.setBounds(12, 255, 188, 25);
		panel.add(btnViewProgrammeDetails);
		
		JButton btnAddCourse = new JButton("ADD COURSE");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMenu sm = new StudentMenu();
				sm.live();
				student.dispose();
			}
		});
		btnAddCourse.setBounds(12, 183, 188, 25);
		panel.add(btnAddCourse);
		
		JButton btnFeeBreakdown = new JButton("FEE BREAKDOWN");
		btnFeeBreakdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateFeeBreakdown gf = new GenerateFeeBreakdown();
				student.dispose();	
				gf.live();
			}
		});
		btnFeeBreakdown.setBounds(12, 332, 188, 25);
		panel.add(btnFeeBreakdown);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(236, 12, 762, 612);
		student.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEnrolForSemester = new JLabel("PROGRAMME DETAILS & PROGRESS REPORT");
		lblEnrolForSemester.setFont(new Font("Bitstream Charter", Font.BOLD, 24));
		lblEnrolForSemester.setForeground(Color.WHITE);
		lblEnrolForSemester.setBounds(124, 12, 536, 28);
		panel_1.add(lblEnrolForSemester);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 52, 741, 2);
		panel_1.add(separator);
		
		JLabel lblNewLabel = new JLabel("PROGRAMME CODE:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 114, 188, 15);
		panel_1.add(lblNewLabel);
		
		progCode = new JTextField();
		progCode.setEditable(false);
		progCode.setBounds(197, 113, 212, 19);
		panel_1.add(progCode);
		progCode.setColumns(10);
		
		JLabel lblName = new JLabel("ID NUMBER:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(12, 68, 114, 15);
		panel_1.add(lblName);
		
		stuId = new JTextField();
		stuId.setEditable(false);
		stuId.setColumns(10);
		stuId.setBounds(124, 67, 213, 19);
		panel_1.add(stuId);
		
		JLabel lblFullName = new JLabel("FULL NAME:");
		lblFullName.setForeground(Color.WHITE);
		lblFullName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFullName.setBounds(355, 68, 132, 15);
		panel_1.add(lblFullName);
		
		stuName = new JTextField();
		stuName.setEditable(false);
		stuName.setColumns(10);
		stuName.setBounds(468, 67, 285, 19);
		panel_1.add(stuName);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 231, 741, 2);
		panel_1.add(separator_1);
		
		JLabel lblCoursesAvailable = new JLabel("ENROLLEMENT INFORMATION");
		lblCoursesAvailable.setForeground(Color.WHITE);
		lblCoursesAvailable.setFont(new Font("Bitstream Charter", Font.BOLD, 20));
		lblCoursesAvailable.setBounds(249, 245, 293, 28);
		panel_1.add(lblCoursesAvailable);
		
		JLabel label_2 = new JLabel("PROGRAMME NAME:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(12, 157, 188, 15);
		panel_1.add(label_2);
		
		progName = new JTextField();
		progName.setEditable(false);
		progName.setColumns(10);
		progName.setBounds(197, 156, 556, 19);
		panel_1.add(progName);
		
		JLabel lblaward = new JLabel("AWARD:");
		lblaward.setForeground(Color.WHITE);
		lblaward.setFont(new Font("Dialog", Font.BOLD, 16));
		lblaward.setBounds(427, 195, 99, 15);
		panel_1.add(lblaward);
		
		award = new JTextField();
		award.setEditable(false);
		award.setColumns(10);
		award.setBounds(503, 194, 250, 19);
		panel_1.add(award);
		
		JLabel lblAccreditation = new JLabel("ACCREDITATION:");
		lblAccreditation.setForeground(Color.WHITE);
		lblAccreditation.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAccreditation.setBounds(12, 198, 162, 15);
		panel_1.add(lblAccreditation);
		
		accreditation = new JTextField();
		accreditation.setEditable(false);
		accreditation.setColumns(10);
		accreditation.setBounds(167, 194, 242, 19);
		panel_1.add(accreditation);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 95, 741, 11);
		panel_1.add(separator_3);
		
		c1 = new JLabel("courseName");
		c1.setForeground(Color.WHITE);
		c1.setFont(new Font("Dialog", Font.BOLD, 16));
		c1.setBounds(135, 393, 221, 15);
		panel_1.add(c1);
		
		c2 = new JLabel("courseName");
		c2.setForeground(Color.WHITE);
		c2.setFont(new Font("Dialog", Font.BOLD, 16));
		c2.setBounds(135, 420, 221, 15);
		panel_1.add(c2);
		
		c3 = new JLabel("courseName");
		c3.setForeground(Color.WHITE);
		c3.setFont(new Font("Dialog", Font.BOLD, 16));
		c3.setBounds(135, 447, 221, 15);
		panel_1.add(c3);
		
		c4 = new JLabel("courseName");
		c4.setForeground(Color.WHITE);
		c4.setFont(new Font("Dialog", Font.BOLD, 16));
		c4.setBounds(135, 474, 221, 15);
		panel_1.add(c4);
		
		c5 = new JLabel("courseName");
		c5.setForeground(Color.WHITE);
		c5.setFont(new Font("Dialog", Font.BOLD, 16));
		c5.setBounds(135, 501, 221, 15);
		panel_1.add(c5);
		
		c6 = new JLabel("courseName");
		c6.setForeground(Color.WHITE);
		c6.setFont(new Font("Dialog", Font.BOLD, 16));
		c6.setBounds(135, 532, 221, 15);
		panel_1.add(c6);
		
		c7 = new JLabel("courseName");
		c7.setForeground(Color.WHITE);
		c7.setFont(new Font("Dialog", Font.BOLD, 16));
		c7.setBounds(135, 559, 221, 15);
		panel_1.add(c7);
		
		c8 = new JLabel("courseName");
		c8.setForeground(Color.WHITE);
		c8.setFont(new Font("Dialog", Font.BOLD, 16));
		c8.setBounds(135, 586, 221, 15);
		panel_1.add(c8);
		
		cred1 = new JLabel("credits");
		cred1.setForeground(Color.WHITE);
		cred1.setFont(new Font("Dialog", Font.BOLD, 16));
		cred1.setBounds(358, 393, 62, 15);
		panel_1.add(cred1);
		
		cred2 = new JLabel("credits");
		cred2.setForeground(Color.WHITE);
		cred2.setFont(new Font("Dialog", Font.BOLD, 16));
		cred2.setBounds(358, 420, 78, 15);
		panel_1.add(cred2);
		
		cred3 = new JLabel("credits");
		cred3.setForeground(Color.WHITE);
		cred3.setFont(new Font("Dialog", Font.BOLD, 16));
		cred3.setBounds(358, 447, 78, 15);
		panel_1.add(cred3);
		
		cred4 = new JLabel("credits");
		cred4.setForeground(Color.WHITE);
		cred4.setFont(new Font("Dialog", Font.BOLD, 16));
		cred4.setBounds(358, 474, 78, 15);
		panel_1.add(cred4);
		
		cred5 = new JLabel("credits");
		cred5.setForeground(Color.WHITE);
		cred5.setFont(new Font("Dialog", Font.BOLD, 16));
		cred5.setBounds(358, 501, 78, 15);
		panel_1.add(cred5);
		
		cred6 = new JLabel("credits");
		cred6.setForeground(Color.WHITE);
		cred6.setFont(new Font("Dialog", Font.BOLD, 16));
		cred6.setBounds(358, 530, 78, 15);
		panel_1.add(cred6);
		
		cred7 = new JLabel("credits");
		cred7.setForeground(Color.WHITE);
		cred7.setFont(new Font("Dialog", Font.BOLD, 16));
		cred7.setBounds(358, 559, 78, 15);
		panel_1.add(cred7);
		
		cred8 = new JLabel("credits");
		cred8.setForeground(Color.WHITE);
		cred8.setFont(new Font("Dialog", Font.BOLD, 16));
		cred8.setBounds(358, 586, 78, 15);
		panel_1.add(cred8);
		
		pre1 = new JLabel("courseName");
		pre1.setForeground(Color.WHITE);
		pre1.setFont(new Font("Dialog", Font.BOLD, 16));
		pre1.setBounds(454, 395, 221, 15);
		panel_1.add(pre1);
		
		pre2 = new JLabel("courseName");
		pre2.setForeground(Color.WHITE);
		pre2.setFont(new Font("Dialog", Font.BOLD, 16));
		pre2.setBounds(454, 421, 221, 15);
		panel_1.add(pre2);
		
		pre3 = new JLabel("courseName");
		pre3.setForeground(Color.WHITE);
		pre3.setFont(new Font("Dialog", Font.BOLD, 16));
		pre3.setBounds(454, 448, 221, 15);
		panel_1.add(pre3);
		
		pre4 = new JLabel("courseName");
		pre4.setForeground(Color.WHITE);
		pre4.setFont(new Font("Dialog", Font.BOLD, 16));
		pre4.setBounds(454, 475, 221, 15);
		panel_1.add(pre4);
		
		pre5 = new JLabel("courseName");
		pre5.setForeground(Color.WHITE);
		pre5.setFont(new Font("Dialog", Font.BOLD, 16));
		pre5.setBounds(454, 502, 221, 15);
		panel_1.add(pre5);
		
		pre6 = new JLabel("courseName");
		pre6.setForeground(Color.WHITE);
		pre6.setFont(new Font("Dialog", Font.BOLD, 16));
		pre6.setBounds(454, 531, 221, 15);
		panel_1.add(pre6);
		
		pre7 = new JLabel("courseName");
		pre7.setForeground(Color.WHITE);
		pre7.setFont(new Font("Dialog", Font.BOLD, 16));
		pre7.setBounds(454, 560, 221, 15);
		panel_1.add(pre7);
		
		pre8 = new JLabel("courseName");
		pre8.setForeground(Color.WHITE);
		pre8.setFont(new Font("Dialog", Font.BOLD, 16));
		pre8.setBounds(454, 587, 221, 15);
		panel_1.add(pre8);
		
		JLabel lblCourses = new JLabel("COURSES");
		lblCourses.setForeground(Color.WHITE);
		lblCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourses.setBounds(174, 365, 99, 15);
		panel_1.add(lblCourses);
		
		JLabel lblCredits_8 = new JLabel("CREDITS");
		lblCredits_8.setForeground(Color.WHITE);
		lblCredits_8.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCredits_8.setBounds(336, 366, 78, 15);
		panel_1.add(lblCredits_8);
		
		JLabel lblPrereq = new JLabel("PRE-REQUISITES");
		lblPrereq.setForeground(Color.WHITE);
		lblPrereq.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrereq.setBounds(462, 365, 162, 15);
		panel_1.add(lblPrereq);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(12, 276, 741, 2);
		panel_1.add(separator_4);
		
		JLabel lblEnrolled = new JLabel("ENROLLED");
		lblEnrolled.setForeground(Color.WHITE);
		lblEnrolled.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnrolled.setBounds(644, 368, 92, 15);
		panel_1.add(lblEnrolled);
		
		en1 = new JLabel("Enrolled");
		en1.setForeground(Color.WHITE);
		en1.setFont(new Font("Dialog", Font.BOLD, 16));
		en1.setBounds(675, 393, 78, 15);
		panel_1.add(en1);
		
		en2 = new JLabel("Enrolled");
		en2.setForeground(Color.WHITE);
		en2.setFont(new Font("Dialog", Font.BOLD, 16));
		en2.setBounds(675, 420, 78, 15);
		panel_1.add(en2);
		
		en3 = new JLabel("Enrolled");
		en3.setForeground(Color.WHITE);
		en3.setFont(new Font("Dialog", Font.BOLD, 16));
		en3.setBounds(675, 447, 78, 15);
		panel_1.add(en3);
		
		en4 = new JLabel("Enrolled");
		en4.setForeground(Color.WHITE);
		en4.setFont(new Font("Dialog", Font.BOLD, 16));
		en4.setBounds(675, 474, 78, 15);
		panel_1.add(en4);
		
		en5 = new JLabel("Enrolled");
		en5.setForeground(Color.WHITE);
		en5.setFont(new Font("Dialog", Font.BOLD, 16));
		en5.setBounds(675, 501, 78, 15);
		panel_1.add(en5);
		
		en6 = new JLabel("Enrolled");
		en6.setForeground(Color.WHITE);
		en6.setFont(new Font("Dialog", Font.BOLD, 16));
		en6.setBounds(675, 532, 78, 15);
		panel_1.add(en6);
		
		en7 = new JLabel("Enrolled");
		en7.setForeground(Color.WHITE);
		en7.setFont(new Font("Dialog", Font.BOLD, 16));
		en7.setBounds(675, 559, 78, 15);
		panel_1.add(en7);
		
		en8 = new JLabel("Enrolled");
		en8.setForeground(Color.WHITE);
		en8.setFont(new Font("Dialog", Font.BOLD, 16));
		en8.setBounds(675, 586, 78, 15);
		panel_1.add(en8);
		
		JLabel label_3 = new JLabel("MAX NO. OF COURSES:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));
		label_3.setBounds(427, 114, 216, 15);
		panel_1.add(label_3);
		
		maxCourses = new JTextField();
		maxCourses.setEditable(false);
		maxCourses.setColumns(10);
		maxCourses.setBounds(644, 114, 109, 19);
		panel_1.add(maxCourses);
		
		lblDescription = new JLabel("DESCRIPTION:");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setBounds(12, 301, 132, 15);
		panel_1.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(138, 285, 491, 52);
		panel_1.add(scrollPane);
		
		descritption = new JTextArea();
		scrollPane.setRowHeaderView(descritption);
		descritption.setEditable(false);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 351, 741, 2);
		panel_1.add(separator_2);
		
		lblCodes = new JLabel("CODES");
		lblCodes.setForeground(Color.WHITE);
		lblCodes.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCodes.setBounds(22, 365, 78, 15);
		panel_1.add(lblCodes);
		
		code1 = new JLabel("credits");
		code1.setForeground(Color.WHITE);
		code1.setFont(new Font("Dialog", Font.BOLD, 16));
		code1.setBounds(12, 393, 114, 15);
		panel_1.add(code1);
		
		code2 = new JLabel("credits");
		code2.setForeground(Color.WHITE);
		code2.setFont(new Font("Dialog", Font.BOLD, 16));
		code2.setBounds(12, 420, 114, 15);
		panel_1.add(code2);
		
		code3 = new JLabel("credits");
		code3.setForeground(Color.WHITE);
		code3.setFont(new Font("Dialog", Font.BOLD, 16));
		code3.setBounds(12, 447, 114, 15);
		panel_1.add(code3);
		
		code4 = new JLabel("credits");
		code4.setForeground(Color.WHITE);
		code4.setFont(new Font("Dialog", Font.BOLD, 16));
		code4.setBounds(12, 474, 114, 15);
		panel_1.add(code4);
		
		code5 = new JLabel("credits");
		code5.setForeground(Color.WHITE);
		code5.setFont(new Font("Dialog", Font.BOLD, 16));
		code5.setBounds(12, 501, 114, 15);
		panel_1.add(code5);
		
		code6 = new JLabel("credits");
		code6.setForeground(Color.WHITE);
		code6.setFont(new Font("Dialog", Font.BOLD, 16));
		code6.setBounds(12, 530, 114, 15);
		panel_1.add(code6);
		
		code7 = new JLabel("credits");
		code7.setForeground(Color.WHITE);
		code7.setFont(new Font("Dialog", Font.BOLD, 16));
		code7.setBounds(12, 559, 114, 15);
		panel_1.add(code7);
		
		code8 = new JLabel("credits");
		code8.setForeground(Color.WHITE);
		code8.setFont(new Font("Dialog", Font.BOLD, 16));
		code8.setBounds(12, 586, 114, 15);
		panel_1.add(code8);
		
		JMenuBar menuBar = new JMenuBar();
		student.setJMenuBar(menuBar);
		
		JMenu mnAccount = new JMenu("Account Settings");
		menuBar.add(mnAccount);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmSystem = new JMenuItem("Project");
		mntmSystem.addActionListener(new ActionListener() {
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
		mnAbout.add(mntmSystem);
		}
}
