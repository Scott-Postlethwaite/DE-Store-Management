import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Login {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Connection conn = null; 
	private JTextField txtUsername;
	private JPasswordField passwordField;
	static ApplicationLayer appLayer;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		DataLayer dataLayer = new DataLayer();
		appLayer = new ApplicationLayer(dataLayer);
		//conn = SQLConnection.dbConnector(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblLogin = new JLabel("StoreManagement");
		lblLogin.setBounds(170, 13, 146, 16);
		frame.add(lblLogin);

		
		txtUsername = new JTextField();
		txtUsername.setBounds(235, 60, 185, 22);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(42, 62, 83, 19);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPasssword = new JLabel("Passsword");
		lblPasssword.setBounds(42, 103, 69, 16);
		frame.getContentPane().add(lblPasssword);
		
		
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(149, 162, 97, 25);
		frame.getContentPane().add(btnSignIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register(); 
				register.setVisible(true);
			}
		});
		btnRegister.setBounds(149, 200, 97, 25);
		frame.getContentPane().add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(235, 100, 185, 22);
		frame.getContentPane().add(passwordField);
		
		JRadioButton rdbtnStoreManager = new JRadioButton("Store Manager");
		rdbtnStoreManager.setBounds(60, 120, 130, 25);
		rdbtnStoreManager.setSelected(true);
		frame.getContentPane().add(rdbtnStoreManager);
		
		JRadioButton rdbtnNormalEmployee = new JRadioButton("Normal Employee");
		rdbtnNormalEmployee.setBounds(200, 120, 170, 25);
		
		frame.getContentPane().add(rdbtnNormalEmployee);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnStoreManager);
		group.add(rdbtnNormalEmployee);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rdbtnStoreManager.isSelected()) {
						boolean value = appLayer.loginStoreManager(txtUsername.getText(), passwordField.getText());
						if(value == true) {
							
							StoreManagerGUI sm = new StoreManagerGUI(); 
							sm.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Unable to verify");
						}
					}
					else if(rdbtnNormalEmployee.isSelected()) {
						boolean value = appLayer.loginNormalEmployee(txtUsername.getText(), passwordField.getText());
						if(value == true) {
							
							NormalEmployee employee = new NormalEmployee(); 
							employee.setVisible(true);
						}
						
						else {
							JOptionPane.showMessageDialog(null, "Unable to verify");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Please select your role"); 
					}
			
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e); 
				}
				
			}
		});
		
		
	}
}
