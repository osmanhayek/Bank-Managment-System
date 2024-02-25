package employee.frames;

public interface IEmployeeOperations {
	public boolean viewCustomerInfo();
	public boolean registerCustomer();
	public boolean confirmCredit(String tcId);
	public void tableToCsvFile();
	public void writeRapors(String eMail);
}
