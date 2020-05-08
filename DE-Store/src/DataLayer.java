import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

public class DataLayer implements DataLayerInterface {
	Connection conn = SQLConnection.dbConnector();
	Connection conn2 = SQLConnection.dbConnector2();
	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";

	
	private String decryptPassword(String password) {
		try
	    {
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	         
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(password)));
	    } 
	    catch (Exception e) {
	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	
	public boolean loginStoreManager(String username, String password) {
		String query = "select * from storemanager where username=? ";
		PreparedStatement pst;
		boolean validation = false; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username );
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {	
				String encryptedPassword = rs.getString("Password");
				String decryptedPassword = decryptPassword(encryptedPassword);
				System.out.println("Decrypted password is " + decryptedPassword); 
				System.out.println("Password is " + password); 
				if(decryptedPassword.equals(password)) {
					validation = true; 
				}
				
			}
			System.out.println("Validation is " + Boolean.toString(validation)); 
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validation; 
	}
	public boolean loginNormalEmployee(String username, String password) {
		String query = "select * from employees where username=? ";
		PreparedStatement pst;
		boolean validation = false; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username );
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {	
				String encryptedPassword = rs.getString("Password");
				String decryptedPassword = decryptPassword(encryptedPassword);
				System.out.println("Decrypted password is " + decryptedPassword); 
				System.out.println("Password is " + password); 
				if(decryptedPassword.equals(password)) {
					validation = true; 
				}
				
			}
			System.out.println("Validation is " + Boolean.toString(validation)); 
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validation; 
	}
	
	public ArrayList<String> getProducts(String Sale) {
		ArrayList<String> products = new ArrayList<>(); 
		try {
			
			String query = "select * from products where Sale=? ";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, Sale );
			
			
			ResultSet rs = pst.executeQuery();
			int count = 0; 
			while(rs.next()) {
				count = count + 1; 
				String ProductID = rs.getString("ProductID");
				String productName = rs.getString("ProductName");
				String saleOffer = rs.getString("Sale");
				products.add("Product Id: " + ProductID + " Product Name: " + productName + " Sale Offer: " + saleOffer);
				
				
				
			}
			
			rs.close();
			pst.close(); 
			
		}
		
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		return products; 
	}
	
	
	
	
	
	public ArrayList<String> getAllProducts() {
		ArrayList<String> products = new ArrayList<>(); 
		try {
			
			String query = "select * from products";
			PreparedStatement pst = conn.prepareStatement(query);			
			
			ResultSet rs = pst.executeQuery();
			int count = 0; 
			while(rs.next()) {
				count = count + 1; 
				String ProductID = rs.getString("ProductID");
				String productName = rs.getString("ProductName");
				String saleOffer = rs.getString("Sale");
				String price = rs.getString("Price");

				products.add("Product Id: " + ProductID + " Product Name: " + productName + " Sale Offer: " + saleOffer + " Price: "+ price);
				
				
			}
			
			rs.close();
			pst.close(); 
			
		}
		
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		return products; 
	}
	
	
	
	public void updatePrice(String price, String ProductID) {
		try {
			
			String sql = "UPDATE products SET Price = ? WHERE ProductID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(price));
			ps.setInt(2, Integer.parseInt(ProductID));
		
		
			ps.executeUpdate();
			ps.close(); 
		}
		
		catch(Exception pe) {
			System.out.println(pe); 
		}
		
	}
	
	
	
	public void updateOffer(String offer, String ProductID) {
		try {
			
			String sql = "UPDATE products SET Sale = ? WHERE ProductID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, offer);
			ps.setInt(2, Integer.parseInt(ProductID));
		
		
			ps.executeUpdate();
			ps.close(); 
		}
		
		catch(Exception pe) {
			System.out.println(pe); 
		}
		
	}
	
	
	
	public ArrayList<String> getAllCustomers() {
		ArrayList<String> customers = new ArrayList<>(); 
		try {
			
			String query = "select * from customers";
			PreparedStatement pst = conn.prepareStatement(query);			
			
			ResultSet rs = pst.executeQuery();
			int count = 0; 
			while(rs.next()) {
				count = count + 1; 
				int CustomerID = rs.getInt("CustomerID");
				int numProducts = rs.getInt("NoOfProducts");
				int credit = rs.getInt("CreditRating");
				Boolean finance = rs.getBoolean("AcceptedFinance");
				Boolean card = rs.getBoolean("LoyaltyCard");


				customers.add("Customer Id: " + CustomerID + " Number of products: " + numProducts + " Credit Rating: " + credit + " Accepted Finance: "+ finance + " Accepted Loyalty Card: " + card);
				
				
			}
			
			rs.close();
			pst.close(); 
			
		}
		
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		return customers; 
	}
	
	
	
	
	public boolean addLoyaltyCard(Boolean loyaltyCard, int CustomerID) {
		boolean flag = false;
		try {
			String query = "SELECT NoOfProducts FROM customers WHERE CustomerID = ? ";
			PreparedStatement ps2 = conn.prepareStatement(query);
			ps2.setInt(1, CustomerID);
			
			ResultSet rset = ps2.executeQuery();
			int num = 0; 
			 
			if(rset.next()) {
				num = rset.getInt(1);
				
			}
			else {
				flag = false;
			}
			rset.close();
			ps2.close();
			if(num > 5) {
				String query2 = "UPDATE customers SET LoyaltyCard = ? WHERE CustomerID = ? ";
				PreparedStatement ps = conn.prepareStatement(query2);
				ps.setBoolean(1, loyaltyCard);
				ps.setInt(2, CustomerID);
				ps.executeUpdate();
				ps.close(); 
				flag = true;
			}
			else {
				flag = false;
			}
		}
		catch(Exception pe) {
			System.out.println(pe); 
		}
		return flag; 
		
	}
	
	public boolean giveFinancialApproval(Boolean financeApproval, int customerID) {
		
		boolean flag = false;
		try {
			String sql = "SELECT CreditRating FROM customers WHERE customerID = ? ";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, customerID);
			ResultSet rset = ps2.executeQuery();
			
			int CreditRating = 0;
			
			if(rset.next()) {
				CreditRating = Integer.parseInt(rset.getString(1));
			}
			else {
				flag = false;
			}
			
			rset.close();
			ps2.close();
			
			if(CreditRating>=70) {
				String query = "UPDATE customers SET AcceptedFinance = ? WHERE customerID = ? ";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setBoolean(1, financeApproval);
				ps.setInt(2, customerID);
				ps.executeUpdate();
				ps.close(); 
				flag = true;
			}
			else {
				flag = false;
			}
			 
			 
			
			
		}
		catch(Exception pe) {
			System.out.println(pe); 
		}
		return flag;
	}
	
	public void setPurchaseInformation(){
		
		
		try {
			
			String sql3 = "SELECT * FROM purchase_information";
			PreparedStatement ps3 = conn2.prepareStatement(sql3);
			ResultSet rs2 = ps3.executeQuery();
			while(rs2.next()) {
				int PurchaseID = rs2.getInt("PurchaseID");
				String sql2 = "DELETE from purchase_information WHERE PurchaseID = ?";
				PreparedStatement ps2 = conn2.prepareStatement(sql2);
				ps2.setInt(1, PurchaseID);
				ps2.execute();
				ps2.close();
			}
			ps3.close();
			rs2.close();
			 
			String sql = "SELECT * FROM products";
			PreparedStatement ps = conn.prepareStatement(sql);
			

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			
				String sql2 = "INSERT INTO purchase_information(CustomerID, ProductID, ProductName, Quantity) VALUES (?,?,?,?)";
				
				int CustomerID = rs.getInt("CustomerID");
				int ProductID = rs.getInt("ProductID");
				String productName = rs.getString("ProductName");
				int quantity = rs.getInt("quantity");
				PreparedStatement ps2 = conn2.prepareStatement(sql2);
				ps2.setInt(1,CustomerID);
				ps2.setInt(2, ProductID);
				ps2.setString(3, productName);
				ps2.setInt(4, quantity);
				ps2.execute();
				ps2.close(); 
				
				
				
				
			
				// Display the student details
				
				
				
			}
			
			rs.close();
			ps.close(); 
		}
		catch(Exception pe) {
			System.out.println(pe); 
		}
		
	}
	
	public ArrayList<String> getPurchaseInformation() {
		ArrayList<String> purchases = new ArrayList<String>(); 
		try {
			String sql = "SELECT * FROM purchases";
			PreparedStatement ps = conn2.prepareStatement(sql);
			

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			
				
				int purchaseId = rs.getInt("PurchaseID");
				
				int CustomerID = rs.getInt("CustomerID");
				
				String productName = rs.getString("ProductName");
				int quantity = rs.getInt("Quantity");
				int Paid = rs.getInt("Paid");
				purchases.add("Purchase Id: " + purchaseId + " Phone Number: " + CustomerID +  " Product Name " + productName + " Quantity: " + quantity + " Total Paid: " + Paid);
				
				
				
				
				
			
				// Display the student details
				
				
				
			}
			
			rs.close();
			ps.close(); 
			
		}
		catch(Exception pe) {
			System.out.println(pe); 
		}
		
		return purchases; 
	}
	
	public DefaultCategoryDataset getReport() {
		DefaultCategoryDataset reportDataset = new DefaultCategoryDataset(); 
		try {
			String sql = "SELECT * FROM purchases";
			PreparedStatement ps = conn2.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int Paid = rs.getInt("Paid");
				String item = rs.getString("ProductName");
				reportDataset.setValue(Paid, item, item);
				
			}
			rs.close();
			ps.close(); 
		}
		catch(Exception pe) {
			System.out.println(pe); 
		}
		
		return reportDataset; 
	}
	
	
	public ArrayList<String> monitorStock() {
		ArrayList<String> products = new ArrayList<>(); 
		try {
			String query = "select * from products";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int quantity = rs.getInt("quantity");
				if(quantity < 5) {
					String productName = rs.getString("ProductName");
					String productId = rs.getString("ProductID");
					
					products.add(productId);
					products.add(productName);
					products.add(Integer.toString(quantity));
				}
			}
			rs.close();
			pst.close();
			
		}
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		
		return products; 
	}
	
	public ArrayList<String> sendEmailAddresses() {
		ArrayList<String> emailAddresses = new ArrayList<String>(); 
		try {
			String query = "select * from storemanager";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				
				String Email = rs.getString("Email");
				emailAddresses.add(Email);
			}
			rs.close();
			pst.close();
			
		}
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		return emailAddresses;
	}
	
	public ArrayList<String> monitorStock2() {
		ArrayList<String> products = new ArrayList<>(); 
		try {
			String query = "select * from products";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int quantity = rs.getInt("quantity");
				
				String productName = rs.getString("ProductName");
				String productId = rs.getString("ProductID");
				
				
				
				products.add("Product: " + productName + "(" + productId + ") " + "Quantity: " + quantity);
				
			}
			rs.close();
			pst.close();
			
		}
		catch(Exception pe) {
			JOptionPane.showMessageDialog(null, pe); 
		}
		
		return products; 
	}
	
	private static String encryptPassword( String password) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");    
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	

	public void registerStoreManager(String password, String username, String Email){
		
		String query = " insert into storemanager(username, password, Email) " + "values(?,?,?) ";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			String encryptedPassword = encryptPassword(password); 
			pst.setString(1, username );
			pst.setString(2, encryptedPassword);
			pst.setString(3,  Email);
			pst.execute(); 
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean validateUsername(String username) {
		boolean notExist = false;  
		String query = "select * from storemanager where username=?  ";
		PreparedStatement pst;
		int count = 0; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username );
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1; 
			}
			if(count >= 1) {
				notExist = false;
			}
			else {
				notExist = true; 
			}
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notExist;
		
	}
	
	public boolean validateEmailAddress(String emailAddress) {
		boolean notExist = false;  
		String query = "select * from storemanager where Email=?  ";
		PreparedStatement pst;
		int count = 0; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, emailAddress );
			ResultSet rs = pst.executeQuery();	
			while(rs.next()) {
				count = count + 1; 
			}
			if(count >= 1) {
				notExist = false;
			}
			else {
				notExist = true;
			}
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notExist;
	}
	
	public void registerNormalEmployee(String password, String username, String Email){
		
		String query = " insert into employees(username, password, Email) " + "values(?,?,?) ";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			String encryptedPassword = encryptPassword(password); 
			pst.setString(1, username );
			pst.setString(2, encryptedPassword);
			pst.setString(3,  Email);
			pst.execute(); 
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean validateUsername2(String username) {
		boolean notExist = false;  
		String query = "select * from employees where username=?  ";
		PreparedStatement pst;
		int count = 0; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username );
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1; 
			}
			if(count >= 1) {
				notExist = false;
			}
			else {
				notExist = true; 
			}
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notExist;
		
	}
	
	public boolean validateEmailAddress2(String emailAddress) {
		boolean notExist = false;  
		String query = "select * from employees where Email=?  ";
		PreparedStatement pst;
		int count = 0; 
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, emailAddress );
			ResultSet rs = pst.executeQuery();	
			while(rs.next()) {
				count = count + 1; 
			}
			if(count >= 1) {
				notExist = false;
			}
			else {
				notExist = true;
			}
			rs.close();
			pst.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notExist;
	}
}
