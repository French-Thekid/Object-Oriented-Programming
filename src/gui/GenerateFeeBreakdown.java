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

public class GenerateFeeBreakdown extends StudentRecords{

	private JTextField progCode;
	private JTextField stuId;
	private JTextField stuName;
	private JTextField progName;
	private JTextField award;
	private JTextField accreditation;
	private JSeparator separator_4;
	private JTextField maxCourses;
	private JFrame student;
	private String name;
	private String getid; 
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
	private JLabel lblCodes;
	private JLabel code1;
	private JLabel code2;
	private JLabel code3;
	private JLabel code4;
	private JLabel code5;
	private JLabel code6;
	private JLabel code7;
	private JLabel code8;
	private JLabel totalcred;
	private JLabel cost1;
	private JLabel cost2;
	private JLabel cost3;
	private JLabel cost4;
	private JLabel cost5;
	private JLabel cost6;
	private JLabel cost7;
	private JLabel cost8;
	private JLabel lblCost;
	private JLabel lblCostPerCredit;
	private JTextField costpercred;
	private JSeparator separator_2;
	private JLabel lblTotal;
	private JLabel totalcost4;
	Connection conn=null;
	
	public void live(){
		try {
			GenerateFeeBreakdown window = new GenerateFeeBreakdown();
			window.student.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public GenerateFeeBreakdown(){
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
		int totalCredits=0;
		int totalCost=0;
		if(courseInfo[2].equals(courseInfo2[1])){code1.setText(courseInfo[11]);c1.setText(courseInfo[2]);cred1.setText(courseInfo[15]);totalCredits=totalCredits+Integer.parseInt(cred1.getText());cost1.setText(String.valueOf(Integer.parseInt(cred1.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost1.getText());}else{code1.setText("----");c1.setText("----");cred1.setText("---");cost1.setText("---");}
		if(courseInfo[3].equals(courseInfo2[2])){code2.setText(courseInfo[12]);c2.setText(courseInfo[3]);cred2.setText(courseInfo[16]);totalCredits=totalCredits+Integer.parseInt(cred2.getText());cost2.setText(String.valueOf(Integer.parseInt(cred2.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost2.getText());}else{code2.setText("----");c2.setText("----");cred2.setText("---");cost2.setText("---");}
		if(courseInfo[4].equals(courseInfo2[3])){code3.setText(courseInfo[13]);c3.setText(courseInfo[4]);cred3.setText(courseInfo[17]);totalCredits=totalCredits+Integer.parseInt(cred3.getText());cost3.setText(String.valueOf(Integer.parseInt(cred3.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost3.getText());}else{code3.setText("----");c3.setText("----");cred3.setText("---");cost3.setText("---");}
		if(courseInfo[5].equals(courseInfo2[4])){code4.setText(courseInfo[14]);c4.setText(courseInfo[5]);cred4.setText(courseInfo[18]);totalCredits=totalCredits+Integer.parseInt(cred4.getText());cost4.setText(String.valueOf(Integer.parseInt(cred4.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost4.getText());}else{code4.setText("----");c4.setText("----");cred4.setText("---");cost4.setText("---");}
	
		totalcred.setText(String.valueOf(totalCredits));
		totalcost4.setText("$"+String.valueOf(totalCost));
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
		
		int totalCredits=0;
		int totalCost=0;
		if(courseInfo[2].equals(courseInfo2[1])){code1.setText(courseInfo[21]);c1.setText(courseInfo[2]);cred1.setText(courseInfo[15]);totalCredits=totalCredits+Integer.parseInt(cred1.getText());cost1.setText(String.valueOf(Integer.parseInt(cred1.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost1.getText());}else{code1.setText("----");c1.setText("----");cred1.setText("---");cost1.setText("---");}
		if(courseInfo[3].equals(courseInfo2[2])){code2.setText(courseInfo[22]);c2.setText(courseInfo[3]);cred2.setText(courseInfo[16]);totalCredits=totalCredits+Integer.parseInt(cred2.getText());cost2.setText(String.valueOf(Integer.parseInt(cred2.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost2.getText());}else{code2.setText("----");c2.setText("----");cred2.setText("---");cost2.setText("---");}
		if(courseInfo[4].equals(courseInfo2[3])){code3.setText(courseInfo[23]);c3.setText(courseInfo[4]);cred3.setText(courseInfo[17]);totalCredits=totalCredits+Integer.parseInt(cred3.getText());cost3.setText(String.valueOf(Integer.parseInt(cred3.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost3.getText());}else{code3.setText("----");c3.setText("----");cred3.setText("---");cost3.setText("---");}
		if(courseInfo[5].equals(courseInfo2[4])){code4.setText(courseInfo[24]);c4.setText(courseInfo[5]);cred4.setText(courseInfo[18]);totalCredits=totalCredits+Integer.parseInt(cred4.getText());cost4.setText(String.valueOf(Integer.parseInt(cred4.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost4.getText());}else{code4.setText("----");c4.setText("----");cred4.setText("---");cost4.setText("---");}
		if(courseInfo[6].equals(courseInfo2[5])){code5.setText(courseInfo[25]);c5.setText(courseInfo[6]);cred5.setText(courseInfo[19]);totalCredits=totalCredits+Integer.parseInt(cred5.getText());cost5.setText(String.valueOf(Integer.parseInt(cred5.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost5.getText());}else{code5.setText("----");c5.setText("----");cred5.setText("---");cost5.setText("---");}
		if(courseInfo[7].equals(courseInfo2[6])){code6.setText(courseInfo[26]);c6.setText(courseInfo[7]);cred6.setText(courseInfo[20]);totalCredits=totalCredits+Integer.parseInt(cred6.getText());cost6.setText(String.valueOf(Integer.parseInt(cred6.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost6.getText());}else{code6.setText("----");c6.setText("----");cred6.setText("---");cost6.setText("---");}
		
		totalcred.setText(String.valueOf(totalCredits));
		totalcost4.setText("$"+String.valueOf(totalCost));
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
		int totalCredits=0;
		int totalCost=0;
		if(courseInfo[2].equals(courseInfo2[1])){code1.setText(courseInfo[21]);c1.setText(courseInfo[2]);cred1.setText(courseInfo[15]);totalCredits=totalCredits+Integer.parseInt(cred1.getText());cost1.setText(String.valueOf(Integer.parseInt(cred1.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost1.getText());}else{code1.setText("----");c1.setText("----");cred1.setText("---");cost1.setText("---");}
		if(courseInfo[3].equals(courseInfo2[2])){code2.setText(courseInfo[22]);c2.setText(courseInfo[3]);cred2.setText(courseInfo[16]);totalCredits=totalCredits+Integer.parseInt(cred2.getText());cost2.setText(String.valueOf(Integer.parseInt(cred2.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost2.getText());}else{code2.setText("----");c2.setText("----");cred2.setText("---");cost2.setText("---");}
		if(courseInfo[4].equals(courseInfo2[3])){code3.setText(courseInfo[23]);c3.setText(courseInfo[4]);cred3.setText(courseInfo[17]);totalCredits=totalCredits+Integer.parseInt(cred3.getText());cost3.setText(String.valueOf(Integer.parseInt(cred3.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost3.getText());}else{code3.setText("----");c3.setText("----");cred3.setText("---");cost3.setText("---");}
		if(courseInfo[5].equals(courseInfo2[4])){code4.setText(courseInfo[24]);c4.setText(courseInfo[5]);cred4.setText(courseInfo[18]);totalCredits=totalCredits+Integer.parseInt(cred4.getText());cost4.setText(String.valueOf(Integer.parseInt(cred4.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost4.getText());}else{code4.setText("----");c4.setText("----");cred4.setText("---");cost4.setText("---");}
		if(courseInfo[6].equals(courseInfo2[5])){code5.setText(courseInfo[25]);c5.setText(courseInfo[6]);cred5.setText(courseInfo[19]);totalCredits=totalCredits+Integer.parseInt(cred5.getText());cost5.setText(String.valueOf(Integer.parseInt(cred5.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost5.getText());}else{code5.setText("----");c5.setText("----");cred5.setText("---");cost5.setText("---");}
		if(courseInfo[7].equals(courseInfo2[6])){code6.setText(courseInfo[26]);c6.setText(courseInfo[7]);cred6.setText(courseInfo[20]);totalCredits=totalCredits+Integer.parseInt(cred6.getText());cost6.setText(String.valueOf(Integer.parseInt(cred6.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost6.getText());}else{code6.setText("----");c6.setText("----");cred6.setText("---");cost6.setText("---");}
		if(courseInfo[27].equals(courseInfo2[7])){code7.setText(courseInfo[33]);c7.setText(courseInfo[27]);cred7.setText(courseInfo[31]);totalCredits=totalCredits+Integer.parseInt(cred7.getText());cost7.setText(String.valueOf(Integer.parseInt(cred7.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost7.getText());}else{code7.setText("----");c7.setText("----");cred7.setText("---");cost7.setText("---");}
		if(courseInfo[28].equals(courseInfo2[8])){code8.setText(courseInfo[34]);c8.setText(courseInfo[28]);cred8.setText(courseInfo[32]);totalCredits=totalCredits+Integer.parseInt(cred8.getText());cost8.setText(String.valueOf(Integer.parseInt(cred8.getText())*Integer.parseInt(costpercred.getText())));totalCost=totalCost+Integer.parseInt(cost8.getText());}else{code8.setText("----");c8.setText("----");cred8.setText("---");cost8.setText("---");}
		
		totalcred.setText(String.valueOf(totalCredits));
		totalcost4.setText("$"+String.valueOf(totalCost));
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
					pst.close();
					stuId.setText(loginfo[1]);
					stuName.setText(loginfo[2]+" "+loginfo[3]);
					progCode.setText(loginfo[6]);
				
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
					costpercred.setText(proginfo[6]);
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
					if(totalcost4.getText().equals("$0")){
					}else{
						String query="UPDATE studentinfo SET EnrolStat=1 WHERE IDNumber=?";
						PreparedStatement pst3= conn.prepareStatement(query);
					    pst3.setString(1,stuId.getText());
						pst3.executeUpdate();
					    pst3.close();
					}
				}catch(Exception err){
					//System.out.println(err);	
				}
			}
		});
		student.setTitle("STUDENT");
		student.setResizable(false);
		student.getContentPane().setBackground(new Color(0, 102, 255));
		student.setBounds(100, 100, 1010, 645);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		student.setLocationRelativeTo(null);
		student.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 31, 212, 528);
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
		btnNewButton.setBounds(44, 491, 117, 25);
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
		btnFeeBreakdown.setEnabled(false);
		btnFeeBreakdown.setBounds(12, 332, 188, 25);
		panel.add(btnFeeBreakdown);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(236, 12, 762, 576);
		student.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEnrolForSemester = new JLabel("ENROLLMENT DETAILS");
		lblEnrolForSemester.setFont(new Font("Bitstream Charter", Font.BOLD, 24));
		lblEnrolForSemester.setForeground(Color.WHITE);
		lblEnrolForSemester.setBounds(214, 12, 536, 28);
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
		
		JLabel lblCoursesAvailable = new JLabel("ENROLLEMENT FEE BREAKDOWN");
		lblCoursesAvailable.setForeground(Color.WHITE);
		lblCoursesAvailable.setFont(new Font("Bitstream Charter", Font.BOLD, 20));
		lblCoursesAvailable.setBounds(201, 245, 361, 28);
		panel_1.add(lblCoursesAvailable);
		
		JLabel label_2 = new JLabel("PROGRAMME NAME:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setBounds(12, 157, 188, 15);
		panel_1.add(label_2);
		
		progName = new JTextField();
		progName.setEditable(false);
		progName.setColumns(10);
		progName.setBounds(197, 156, 212, 19);
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
		
		c1 = new JLabel("");
		c1.setForeground(Color.WHITE);
		c1.setFont(new Font("Dialog", Font.BOLD, 16));
		c1.setBounds(210, 312, 221, 15);
		panel_1.add(c1);
		
		c2 = new JLabel("");
		c2.setForeground(Color.WHITE);
		c2.setFont(new Font("Dialog", Font.BOLD, 16));
		c2.setBounds(210, 339, 221, 15);
		panel_1.add(c2);
		
		c3 = new JLabel("");
		c3.setForeground(Color.WHITE);
		c3.setFont(new Font("Dialog", Font.BOLD, 16));
		c3.setBounds(210, 366, 221, 15);
		panel_1.add(c3);
		
		c4 = new JLabel("");
		c4.setForeground(Color.WHITE);
		c4.setFont(new Font("Dialog", Font.BOLD, 16));
		c4.setBounds(210, 393, 221, 15);
		panel_1.add(c4);
		
		c5 = new JLabel("");
		c5.setForeground(Color.WHITE);
		c5.setFont(new Font("Dialog", Font.BOLD, 16));
		c5.setBounds(210, 420, 221, 15);
		panel_1.add(c5);
		
		c6 = new JLabel("");
		c6.setForeground(Color.WHITE);
		c6.setFont(new Font("Dialog", Font.BOLD, 16));
		c6.setBounds(210, 451, 221, 15);
		panel_1.add(c6);
		
		c7 = new JLabel("");
		c7.setForeground(Color.WHITE);
		c7.setFont(new Font("Dialog", Font.BOLD, 16));
		c7.setBounds(210, 478, 221, 15);
		panel_1.add(c7);
		
		c8 = new JLabel("");
		c8.setForeground(Color.WHITE);
		c8.setFont(new Font("Dialog", Font.BOLD, 16));
		c8.setBounds(210, 505, 221, 15);
		panel_1.add(c8);
		
		cred1 = new JLabel("");
		cred1.setForeground(Color.WHITE);
		cred1.setFont(new Font("Dialog", Font.BOLD, 16));
		cred1.setBounds(433, 312, 81, 15);
		panel_1.add(cred1);
		
		cred2 = new JLabel("");
		cred2.setForeground(Color.WHITE);
		cred2.setFont(new Font("Dialog", Font.BOLD, 16));
		cred2.setBounds(433, 339, 81, 15);
		panel_1.add(cred2);
		
		cred3 = new JLabel("");
		cred3.setForeground(Color.WHITE);
		cred3.setFont(new Font("Dialog", Font.BOLD, 16));
		cred3.setBounds(433, 366, 81, 15);
		panel_1.add(cred3);
		
		cred4 = new JLabel("");
		cred4.setForeground(Color.WHITE);
		cred4.setFont(new Font("Dialog", Font.BOLD, 16));
		cred4.setBounds(433, 393, 81, 15);
		panel_1.add(cred4);
		
		cred5 = new JLabel("");
		cred5.setForeground(Color.WHITE);
		cred5.setFont(new Font("Dialog", Font.BOLD, 16));
		cred5.setBounds(433, 420, 81, 15);
		panel_1.add(cred5);
		
		cred6 = new JLabel("");
		cred6.setForeground(Color.WHITE);
		cred6.setFont(new Font("Dialog", Font.BOLD, 16));
		cred6.setBounds(433, 449, 81, 15);
		panel_1.add(cred6);
		
		cred7 = new JLabel("");
		cred7.setForeground(Color.WHITE);
		cred7.setFont(new Font("Dialog", Font.BOLD, 16));
		cred7.setBounds(433, 478, 81, 15);
		panel_1.add(cred7);
		
		cred8 = new JLabel("");
		cred8.setForeground(Color.WHITE);
		cred8.setFont(new Font("Dialog", Font.BOLD, 16));
		cred8.setBounds(433, 505, 81, 15);
		panel_1.add(cred8);
		
		JLabel lblCourses = new JLabel("COURSES");
		lblCourses.setForeground(Color.WHITE);
		lblCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourses.setBounds(239, 285, 99, 15);
		panel_1.add(lblCourses);
		
		JLabel lblCredits_8 = new JLabel("CREDITS");
		lblCredits_8.setForeground(Color.WHITE);
		lblCredits_8.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCredits_8.setBounds(430, 285, 78, 15);
		panel_1.add(lblCredits_8);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(12, 276, 741, 2);
		panel_1.add(separator_4);
		
		JLabel label_3 = new JLabel("MAX NO. OF COURSES:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 16));
		label_3.setBounds(426, 114, 216, 15);
		panel_1.add(label_3);
		
		maxCourses = new JTextField();
		maxCourses.setEditable(false);
		maxCourses.setColumns(10);
		maxCourses.setBounds(644, 113, 109, 19);
		panel_1.add(maxCourses);
		
		lblCodes = new JLabel("CODES");
		lblCodes.setForeground(Color.WHITE);
		lblCodes.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCodes.setBounds(75, 285, 78, 15);
		panel_1.add(lblCodes);
		
		code1 = new JLabel("");
		code1.setForeground(Color.WHITE);
		code1.setFont(new Font("Dialog", Font.BOLD, 16));
		code1.setBounds(87, 312, 114, 15);
		panel_1.add(code1);
		
		code2 = new JLabel("");
		code2.setForeground(Color.WHITE);
		code2.setFont(new Font("Dialog", Font.BOLD, 16));
		code2.setBounds(87, 339, 114, 15);
		panel_1.add(code2);
		
		code3 = new JLabel("");
		code3.setForeground(Color.WHITE);
		code3.setFont(new Font("Dialog", Font.BOLD, 16));
		code3.setBounds(87, 366, 114, 15);
		panel_1.add(code3);
		
		code4 = new JLabel("");
		code4.setForeground(Color.WHITE);
		code4.setFont(new Font("Dialog", Font.BOLD, 16));
		code4.setBounds(87, 393, 114, 15);
		panel_1.add(code4);
		
		code5 = new JLabel("");
		code5.setForeground(Color.WHITE);
		code5.setFont(new Font("Dialog", Font.BOLD, 16));
		code5.setBounds(87, 420, 114, 15);
		panel_1.add(code5);
		
		code6 = new JLabel("");
		code6.setForeground(Color.WHITE);
		code6.setFont(new Font("Dialog", Font.BOLD, 16));
		code6.setBounds(87, 449, 114, 15);
		panel_1.add(code6);
		
		code7 = new JLabel("");
		code7.setForeground(Color.WHITE);
		code7.setFont(new Font("Dialog", Font.BOLD, 16));
		code7.setBounds(87, 478, 114, 15);
		panel_1.add(code7);
		
		code8 = new JLabel("");
		code8.setForeground(Color.WHITE);
		code8.setFont(new Font("Dialog", Font.BOLD, 16));
		code8.setBounds(87, 505, 114, 15);
		panel_1.add(code8);
		
		totalcred = new JLabel("credits");
		totalcred.setForeground(Color.WHITE);
		totalcred.setFont(new Font("Dialog", Font.BOLD, 16));
		totalcred.setBounds(430, 548, 132, 15);
		panel_1.add(totalcred);
		
		cost1 = new JLabel("");
		cost1.setForeground(Color.WHITE);
		cost1.setFont(new Font("Dialog", Font.BOLD, 16));
		cost1.setBounds(561, 312, 81, 15);
		panel_1.add(cost1);
		
		cost2 = new JLabel("");
		cost2.setForeground(Color.WHITE);
		cost2.setFont(new Font("Dialog", Font.BOLD, 16));
		cost2.setBounds(561, 339, 81, 15);
		panel_1.add(cost2);
		
		cost3 = new JLabel("");
		cost3.setForeground(Color.WHITE);
		cost3.setFont(new Font("Dialog", Font.BOLD, 16));
		cost3.setBounds(561, 366, 81, 15);
		panel_1.add(cost3);
		
		cost4 = new JLabel("");
		cost4.setForeground(Color.WHITE);
		cost4.setFont(new Font("Dialog", Font.BOLD, 16));
		cost4.setBounds(561, 393, 81, 15);
		panel_1.add(cost4);
		
		cost5 = new JLabel("");
		cost5.setForeground(Color.WHITE);
		cost5.setFont(new Font("Dialog", Font.BOLD, 16));
		cost5.setBounds(561, 420, 81, 15);
		panel_1.add(cost5);
		
		cost6 = new JLabel("");
		cost6.setForeground(Color.WHITE);
		cost6.setFont(new Font("Dialog", Font.BOLD, 16));
		cost6.setBounds(561, 449, 81, 15);
		panel_1.add(cost6);
		
		cost7 = new JLabel("");
		cost7.setForeground(Color.WHITE);
		cost7.setFont(new Font("Dialog", Font.BOLD, 16));
		cost7.setBounds(561, 478, 81, 15);
		panel_1.add(cost7);
		
		cost8 = new JLabel("");
		cost8.setForeground(Color.WHITE);
		cost8.setFont(new Font("Dialog", Font.BOLD, 16));
		cost8.setBounds(561, 505, 81, 15);
		panel_1.add(cost8);
		
		lblCost = new JLabel("COST($)");
		lblCost.setForeground(Color.WHITE);
		lblCost.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCost.setBounds(551, 284, 78, 15);
		panel_1.add(lblCost);
		
		lblCostPerCredit = new JLabel("COST PER CREDIT($):");
		lblCostPerCredit.setForeground(Color.WHITE);
		lblCostPerCredit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCostPerCredit.setBounds(427, 158, 195, 15);
		panel_1.add(lblCostPerCredit);
		
		costpercred = new JTextField();
		costpercred.setEditable(false);
		costpercred.setColumns(10);
		costpercred.setBounds(621, 156, 132, 19);
		panel_1.add(costpercred);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(12, 533, 741, 2);
		panel_1.add(separator_2);
		
		lblTotal = new JLabel("TOTAL:");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTotal.setBounds(353, 548, 78, 15);
		panel_1.add(lblTotal);
		
		totalcost4 = new JLabel("cost");
		totalcost4.setForeground(Color.WHITE);
		totalcost4.setFont(new Font("Dialog", Font.BOLD, 16));
		totalcost4.setBounds(561, 549, 189, 15);
		panel_1.add(totalcost4);
		
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
