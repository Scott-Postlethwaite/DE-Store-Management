import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class StoreManagerGUI extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public StoreManagerGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStoreManager = new JLabel("Store Manager Menu");
		lblStoreManager.setBounds(150, 13,150, 30);
		contentPane.add(lblStoreManager);
		
		JButton btnProducts = new JButton("Edit Products");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StoreManagerGUI sm = new StoreManagerGUI(); 
				sm.setVisible(false); 
				Products products = new Products();
				products.setVisible(true);
			}
		});
		btnProducts.setBounds(130, 82,180, 30);
		contentPane.add(btnProducts);
		
		JButton btnMessages = new JButton("Check Stock");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreManagerGUI sm = new StoreManagerGUI(); 
				sm.setVisible(false); 
				Messages messages = new Messages();
				messages.setVisible(true);
			}
		});
		btnMessages.setBounds(130, 124,180, 30);
		contentPane.add(btnMessages);
		
		JButton btnCustomers = new JButton("Manage Customers");
		btnCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreManagerGUI sm = new StoreManagerGUI(); 
				sm.setVisible(false); 
				Customers customers = new Customers();
				customers.setVisible(true);
			}
		});
		btnCustomers.setBounds(130, 165, 180, 30);
		contentPane.add(btnCustomers);
		
		JButton btnPurchaseInformation = new JButton("Purchase Information");
		btnPurchaseInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PurchaseInformation pi = new PurchaseInformation();
				pi.setVisible(true);
			}
		});
		btnPurchaseInformation.setBounds(130, 42, 180, 30);
		contentPane.add(btnPurchaseInformation);
		
	
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
