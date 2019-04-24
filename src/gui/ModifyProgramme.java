/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
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

import domain.CourseRecords;
import domain.ProgrammeRecords;
import domain.StudentRecords;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModifyProgramme extends ProgrammeRecords{

	private JFrame modifyprog;
	private JButton SeeAll;
	private JTable table;
	private JButton modCourses;
	private JTextField find;
	private String name;
	private JTextField progName;
	private JTextField progCode;
	private JTextField maxCourses;
	private JTextField award;
	private JTextField accreditation;
	private String code;
	private static String programmeCode;
	private static String programmeName;
	private static String programmeAward;
	private static String programmeAccreditation;
	private static String programmemaxCourses;
	Connection conn=null;
	private JTextField cost;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyProgramme window = new ModifyProgramme();
					window.modifyprog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void load() {
		try {
			ModifyProgramme window = new ModifyProgramme();
			window.modifyprog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModifyProgramme() {
		initialize();
		conn=DBConnection.dbConnector();
	}
	
	public ModifyProgramme(String code, String name, int maxCourse, String award, String accreditation,CourseRecords course) {
		super(code, name, maxCourse, award, accreditation, course);
	}
	
	public void setEditable(boolean arg){
		progName.setEditable(arg);
		progCode.setEditable(arg);
		accreditation.setEditable(arg);
		cost.setEditable(arg);
	}
	public void clear(){
		award.setText("");
		progName.setText("");
		progCode.setText("");
		maxCourses.setText("");
		accreditation.setText("");
	}
	
	public String getProgrammeaward(){
		return programmeAward;
	}
	
	public String getProgrammecode(){
		return programmeCode;
	}
	
	public String getProgrammename(){
		return programmeName;
	}
	
	public String getProgrammemaxcourses(){
		return programmemaxCourses;
	}
	
	public String getProgrammeaccreditation(){
		return programmeAccreditation;
	}
	
	public void TableDisplay(String code) {
		try{
				String Query= "SELECT * FROM programmeinfo WHERE ProgrammeCode=?"; 
				PreparedStatement pst= conn.prepareStatement(Query);
				pst.setString(1, code);
				ResultSet rs= pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
		        }
		        catch(SQLException ex){
		        	ex.getMessage();
		        }
	   }
	
	public void search(String arg){
		 try{	
				String Query= "SELECT * FROM programmeinfo WHERE ProgrammeCode=?"; 
				PreparedStatement pst= conn.prepareStatement(Query);
				pst.setString(1, find.getText());
				ResultSet rs= pst.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				if(arg.length()==0){
					setEditable(false);
					clear();
				}
				pst.close();
			  }
			  catch(SQLException ex){
				  JOptionPane.showMessageDialog(null, ex.getMessage());
			  }
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
		modifyprog = new JFrame();
		modifyprog.setResizable(false);
		modifyprog.setTitle("STAFF ");
		modifyprog.getContentPane().setBackground(new Color(0, 102, 255));
		modifyprog.setBounds(100, 100, 984, 631);
		modifyprog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifyprog.setLocationRelativeTo(null);
		modifyprog.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 255));
		panel.setBounds(12, 31, 212, 506);
		modifyprog.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(39, 0, 130, 44);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("REGISTER STUDENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyprog.dispose();
				StaffMenu sm = new StaffMenu();
				sm.load();
			}
		});
		btnNewButton.setBounds(12, 172, 188, 25);
		panel.add(btnNewButton);
		
		JButton btnCreateProgramme = new JButton("CREATE PROGRAMME");
		btnCreateProgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyprog.dispose();
				CreateProgramme rs = new CreateProgramme();
				rs.load();
			}
		});
		btnCreateProgramme.setBounds(12, 224, 188, 25);
		panel.add(btnCreateProgramme);
		
		JButton btnModifyPrgramme = new JButton("MODIFY PRGRAMME ");
		btnModifyPrgramme.setEnabled(false);
		btnModifyPrgramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifyPrgramme.setBounds(12, 278, 188, 25);
		panel.add(btnModifyPrgramme);
		
		JButton btnGenerateStudentList = new JButton("STUDENT LIST");
		btnGenerateStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentList sl = new StudentList();
				modifyprog.dispose();
				sl.load();
			}
		});
		btnGenerateStudentList.setBounds(12, 330, 188, 25);
		panel.add(btnGenerateStudentList);
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				modifyprog.dispose();	
				log.run();
			}
		});
		btnNewButton_1.setBounds(45, 469, 117, 25);
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
		}
		catch(Exception e){
			System.out.println(e);
		}
		StaffName.setText(name);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("person.png"));
		lblNewLabel_2.setBounds(44, 33, 146, 97);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(236, 12, 738, 557);
		modifyprog.getContentPane().add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(12, 121, 706, 236);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					award.setText(model.getValueAt(selectedRowIndex, 3).toString());
					progName.setText(model.getValueAt(selectedRowIndex, 1).toString());
					progCode.setText(model.getValueAt(selectedRowIndex, 0).toString());
					maxCourses.setText(model.getValueAt(selectedRowIndex, 2).toString());
					accreditation.setText(model.getValueAt(selectedRowIndex, 4).toString());
					cost.setText(model.getValueAt(selectedRowIndex, 5).toString());
					code = model.getValueAt(selectedRowIndex, 0).toString();
					setEditable(true);
					modCourses.setEnabled(true);
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		find = new JTextField();
		find.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search(find.getText());
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
		
		SeeAll = new JButton("VIEW ALL PROGRAMMES");
		SeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String q = "SELECT * FROM programmeinfo";
					PreparedStatement pst= conn.prepareStatement(q);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		SeeAll.setBounds(465, 72, 253, 25);
		panel_1.add(SeeAll);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 53, 706, 10);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 106, 706, 13);
		panel_1.add(separator_1);
		
		JLabel lblGenerateStudentList = new JLabel("PROGRAMME MODIFICATION");
		lblGenerateStudentList.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
		lblGenerateStudentList.setForeground(new Color(255, 255, 255));
		lblGenerateStudentList.setBounds(150, 12, 436, 29);
		panel_1.add(lblGenerateStudentList);
		
		JLabel lblName = new JLabel("Programme Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(20, 400, 165, 20);
		panel_1.add(lblName);
		
		progName = new JTextField();
		progName.setEditable(false);
		progName.setColumns(10);
		progName.setBounds(202, 401, 222, 19);
		panel_1.add(progName);
		
		JLabel lblProgrammeCode_1 = new JLabel("Programme Code:");
		lblProgrammeCode_1.setForeground(Color.WHITE);
		lblProgrammeCode_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProgrammeCode_1.setBounds(20, 432, 187, 20);
		panel_1.add(lblProgrammeCode_1);
		
		progCode = new JTextField();
		progCode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
					String numOfRecords = null;
					String q="SELECT COUNT(ProgrammeCode) FROM programmeinfo WHERE ProgrammeCode=?";
					PreparedStatement get = conn.prepareStatement(q);
					get.setString(1,progCode.getText());
					ResultSet rs = get.executeQuery();
					if(rs.next()){
						numOfRecords=rs.getString(1);
						System.out.println(numOfRecords);
					}
					if(Integer.parseInt(numOfRecords)==1&& find.getText().equals(progCode.getText())==false){
						get.close();
						JOptionPane.showMessageDialog(null, progCode.getText()+" Already Exists");
						progCode.setText(find.getText());
					}else{
						get.close();
					}
					get.close();
				}catch(Exception err){
					System.out.println(err);
				}
			
			}
		});
		progCode.setEditable(false);
		progCode.setColumns(10);
		progCode.setBounds(202, 430, 222, 19);
		panel_1.add(progCode);
		
		JLabel lblMaxNoCourses = new JLabel("Max No. Courses:");
		lblMaxNoCourses.setForeground(Color.WHITE);
		lblMaxNoCourses.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMaxNoCourses.setBounds(20, 465, 187, 20);
		panel_1.add(lblMaxNoCourses);
		
		maxCourses = new JTextField();
		maxCourses.setEditable(false);
		maxCourses.setColumns(10);
		maxCourses.setBounds(202, 462, 222, 19);
		panel_1.add(maxCourses);
		
		JLabel lblA = new JLabel("Award:");
		lblA.setForeground(Color.WHITE);
		lblA.setFont(new Font("Dialog", Font.BOLD, 16));
		lblA.setBounds(65, 369, 71, 20);
		panel_1.add(lblA);
		
		award = new JTextField();
		award.setEditable(false);
		award.setColumns(10);
		award.setBounds(202, 368, 222, 19);
		panel_1.add(award);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(436, 369, 2, 175);
		panel_1.add(separator_2);
		
		JLabel lblAccreditation = new JLabel("Accreditation:");
		lblAccreditation.setForeground(Color.WHITE);
		lblAccreditation.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAccreditation.setBounds(42, 524, 124, 20);
		panel_1.add(lblAccreditation);
		
		accreditation = new JTextField();
		accreditation.setEditable(false);
		accreditation.setColumns(10);
		accreditation.setBounds(202, 525, 222, 19);
		panel_1.add(accreditation);
		
		JButton btnDelete = new JButton("UPDATE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(progName.getText().length()!=0 && progCode.getText().length()!=0  && accreditation.getText().length()!=0  && cost.getText().length()!=0)
					{
						CourseRecords course = new CourseRecords();
						ProgrammeRecords pr = new ModifyProgramme(progCode.getText(),progName.getText(),Integer.parseInt(maxCourses.getText()),award.getText(),accreditation.getText(),course);
						pr.display();
						
						String query="UPDATE programmeinfo SET ProgrammeName='"+pr.getName()+"',ProgrammeCode='"+progCode.getText()+"','Accreditation'='"+pr.getAccreditation()+"',Award='"+pr.getAward()+"','NumberofCourses'='"+pr.getMaxCourse()+"','CostPerCredit'='"+cost.getText()+"' WHERE ProgrammeCode='"+code+"' ";
						PreparedStatement pst= conn.prepareStatement(query);
					    pst.executeUpdate();
					    pst.close();
					   
						JOptionPane.showMessageDialog(null, "Programme Sucessfully Update");
					    find.setText(pr.getCode());
						search(pr.getCode());
					}else{
						JOptionPane.showMessageDialog(null, "Please ensure all fields are filled!");
					}
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		btnDelete.setBounds(476, 432, 198, 36);
		panel_1.add(btnDelete);
		
		JButton button = new JButton("DELETE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(progName.getText().length()!=0 && award.getText().length()!=0  && progCode.getText().length()!=0  && accreditation.getText().length()!=0  && maxCourses.getText().length()!=0 && find.getText().equals(progCode.getText())==true)
					{
						CourseRecords course = new CourseRecords();
						ProgrammeRecords pr = new ModifyProgramme(progCode.getText(),progName.getText(),Integer.parseInt(maxCourses.getText()),award.getText(),accreditation.getText(),course);
						pr.display();
						
						String query="DELETE FROM programmeinfo WHERE ProgrammeCode='"+progCode.getText()+"'";
						
						PreparedStatement pst= conn.prepareStatement(query);
					    pst.executeUpdate();
					    pst.close();
					    
					    JOptionPane.showMessageDialog(null, "Programme Sucessfully Update");
					    find.setText("");
						search("");
					}else{
						JOptionPane.showMessageDialog(null, "Please ensure all fields are filled! \nAnd you have entered the correct \nProgramme Code to delete!");
					}
					
				}catch(Exception err){
					System.out.println(err);
				}
			}
		});
		button.setBounds(476, 495, 198, 36);
		panel_1.add(button);
		
		modCourses = new JButton("MODIFY COURSES");
		modCourses.setEnabled(false);
		modCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyprog.dispose();
				programmeAward=award.getText();
				programmeCode=progCode.getText();
				programmeName=progName.getText();
				programmeAccreditation=accreditation.getText();
				programmemaxCourses=maxCourses.getText();
				ModifyCourse mc = new ModifyCourse();
				mc.load();
			}
		});
		modCourses.setBounds(476, 369, 198, 36);
		panel_1.add(modCourses);
		
		JLabel lblCostPerCredit = new JLabel("Cost Per Credit($):");
		lblCostPerCredit.setForeground(Color.WHITE);
		lblCostPerCredit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCostPerCredit.setBounds(20, 495, 175, 20);
		panel_1.add(lblCostPerCredit);
		
		cost = new JTextField();
		cost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					Integer.parseInt(cost.getText());
				}catch(Exception err){
					cost.setText("");
				}
			}
		});
		cost.setEditable(false);
		cost.setColumns(10);
		cost.setBounds(202, 497, 222, 19);
		panel_1.add(cost);
		JMenuBar menuBar = new JMenuBar();
		modifyprog.setJMenuBar(menuBar);
		
		JMenu mnAccount = new JMenu("Account Settings");
		menuBar.add(mnAccount);
		
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
