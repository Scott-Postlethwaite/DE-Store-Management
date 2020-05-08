import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

public interface DataLayerInterface {
	public boolean loginStoreManager(String username, String password);
	public boolean loginNormalEmployee(String username, String password);
	public ArrayList<String> getProducts(String sale_offer);
	public ArrayList<String> getAllProducts();
	public ArrayList<String> getAllCustomers();
	public void updatePrice(String price, String product_id);
	public void updateOffer(String offer, String product_id);
	public boolean addLoyaltyCard(Boolean loyaltyCard, int CustomerID);
	public boolean giveFinancialApproval(Boolean financeApproval, int customerID);
	public void setPurchaseInformation();
	public ArrayList<String> getPurchaseInformation();
	public ArrayList<String> monitorStock();
	public ArrayList<String> sendEmailAddresses();
	public ArrayList<String> monitorStock2();
	public void registerStoreManager(String password, String username, String Email);
	public boolean validateUsername(String username);
	public boolean validateEmailAddress(String emailAddress);
	
	public void registerNormalEmployee(String password, String username, String Email);
	public boolean validateUsername2(String username);
	public boolean validateEmailAddress2(String emailAddress);
	public DefaultCategoryDataset getReport();
	
}
