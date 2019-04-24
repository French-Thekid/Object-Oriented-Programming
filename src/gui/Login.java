/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import services.FileProcess;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Login{

	private JFrame frmLogin;
	private JTextField usrname;
	private JPasswordField pass;
	private static String usr;
	private static String pswrd;
	

	public void run() {
		try {
			Login window = new Login();
			window.frmLogin.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Login() {
		initialize();
	}
	
	public String getid(){
		return usr;
	}

	public String getpswrd() {
		return pswrd;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(new Color(0, 102, 255));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 614, 473);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 255));
		panel.setBounds(227, 102, 10, 222);
		frmLogin.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 255));
		panel_1.setBounds(0, 0, 230, 416);
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("Login-icon.png"));
		logo.setBounds(51, 124, 150, 138);
		panel_1.add(logo);
		
		JLabel lblNewLabel_1 = new JLabel("H&AI");
		lblNewLabel_1.setFont(new Font("Century Schoolbook L", Font.BOLD, 30));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(69, 236, 93, 46);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 153, 255));
		panel_2.setBounds(237, -15, 375, 431);
		frmLogin.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUsername = new JLabel("ID Number:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Droid Sans Fallback", Font.BOLD, 16));
		lblUsername.setBounds(12, 146, 121, 26);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Droid Sans Fallback", Font.BOLD, 16));
		lblPassword.setBounds(12, 184, 121, 26);
		panel_2.add(lblPassword);
		
		JLabel lblDomain = new JLabel("Domain:");
		lblDomain.setForeground(new Color(255, 255, 255));
		lblDomain.setFont(new Font("Droid Sans Fallback", Font.BOLD, 16));
		lblDomain.setBounds(12, 222, 121, 26);
		panel_2.add(lblDomain);
		
		usrname = new JTextField();
		usrname.setBounds(110, 151, 190, 19);
		panel_2.add(usrname);
		usrname.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(110, 189, 190, 19);
		panel_2.add(pass);
		
		JComboBox domain = new JComboBox();
		domain.setBounds(110, 224, 190, 24);
		domain.setModel(new DefaultComboBoxModel(new String[]{"Administrator","STAFF","STUDENT"}));
		panel_2.add(domain);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateStaff cs = new CreateStaff();
				StudentMenu stm = new StudentMenu();
				StaffMenu sm = new StaffMenu();
				pswrd = new String(pass.getPassword());
				usr =usrname.getText();
				String type = String.valueOf(domain.getSelectedItem());
				
				int check;
				FileProcess look = new FileProcess();
				if(pswrd.length()==0 ||usr.length()==0 ){
					JOptionPane.showMessageDialog(null,"Can't leave Username or Password field blank!");
				}
				else
				{
					check=look.fileLookUP(usr, pswrd,type);
					if(check==0){
						frmLogin.dispose();
						if(type.equals("Administrator"))
						{
							cs.run();
						}else{
							if(type.equals("STAFF")){
								sm.load();
							
							}else{
								if(type.equals("STUDENT")){
									stm.live();
								}
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(130, 276, 132, 26);
		panel_2.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 153, 255));
		panel_3.setBounds(227, -15, 10, 431);
		frmLogin.getContentPane().add(panel_3);
		
		JLabel lblNewLabel = new JLabel("The Home & Away Institute");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(201, 424, 298, 15);
		frmLogin.getContentPane().add(lblNewLabel);
		
		}
}
