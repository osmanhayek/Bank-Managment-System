package customer.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.operations.DbConnection;
import generators.Generator;

public class CustomerAccountInfo {
	private String accountId;
	private double amount;
	private String debitCartNo;
	private String creditCartNo;
	private String securityQ;
	private String securityA;
	private boolean creditIsConfermed;
	private char[] pin;
	private boolean isExpired;
	
	public CustomerAccountInfo() {
		this.accountId = Generator.generateRandomId();
		this.amount = 0;
		this.debitCartNo = Generator.generateCreditCardNo();
		this.creditCartNo = Generator.generateCreditCardNo();
		this.creditIsConfermed = false;
		this.pin = Generator.generatePin().toCharArray();
		this.isExpired = false;
	}
	
	public CustomerAccountInfo(String securityQ, String securityA) {
		this();
		this.securityA = securityA;
		this.securityQ = securityQ;
	}
	
	public CustomerAccountInfo(String accountId, double amount, 
							   String debitCartNo, String creditCartNo, 
							   boolean creditIsConfermed, char[] pin, 
							   boolean isExpired) {
		this.accountId = accountId;
		this.amount = amount;
		this.debitCartNo = debitCartNo;
		this.creditCartNo = creditCartNo;
		this.creditIsConfermed = creditIsConfermed;
		this.pin = pin;
		this.isExpired = isExpired;
	}
	

	public String getAccountId() { return accountId; }
	public double getAmount() { return amount; }
	public String getDebitCartNo() { return debitCartNo; }
	public String getCreditCartNo() { return creditCartNo; }
	public boolean isCreditIsConfermed() { return creditIsConfermed; }
	public char[] getPin() { return pin; }
	public boolean isExpired() { return isExpired; }
	public String getSecurityQ() { return securityQ; }
	public String getSecurityA() { return securityA; }
	
	public void setAmount(double amount) { this.amount = amount; }
	
	public void insertAccountIntoDb(String tcId) {
		try (Connection connection = DbConnection.getConnection()) {
			String inserQ = "INSERT INTO accounts_info (AccountId, "
					+ "AccountTcId, "
					+ "AccountAmount, "
					+ "AccountDebitCartNo, "
					+ "AccountCreditCartNo, "
					+ "AccountPin, "
					+ "AccountCreditCartCon, "
					+ "AccountIsExpired, "
					+ "AccountSecurityQuestion, "
					+ "AccountQuestionAnswer) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(inserQ)) {
                preparedStatement.setString(1, getAccountId());
                preparedStatement.setString(2, tcId);
                preparedStatement.setDouble(3, getAmount());
                preparedStatement.setString(4, getDebitCartNo());
                preparedStatement.setString(5, getCreditCartNo());
                preparedStatement.setString(6, String.valueOf(getPin()));
                preparedStatement.setBoolean(7, isCreditIsConfermed());
                preparedStatement.setBoolean(8, isExpired);
                preparedStatement.setString(9, getSecurityQ());
                preparedStatement.setString(10, getSecurityA());
                
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                } else {
                    System.out.println("Data insertion failed.");
                }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static CustomerAccountInfo getAccountObjFromDb(String id) {
		CustomerAccountInfo customerAccountInfo;

		try (Connection connection = DbConnection.getConnection()) {
			String selectQ = "SELECT * FROM accounts_info WHERE AccountId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
				preparedStatement.setString(1, id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						customerAccountInfo = new CustomerAccountInfo(id, resultSet.getDouble("AccountAmount"), resultSet.getString("AccountDebitCartNo"), 
								resultSet.getString("AccountCreditCartNo"), resultSet.getBoolean("AccountCreditCartCon"), resultSet.getString("AccountPin").toCharArray(), 
								resultSet.getBoolean("AccountIsExpired"));
						return customerAccountInfo;
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean checkAnswer(String tcId, String answer) {
		boolean isThere = false;
		
		String selectQ = "SELECT * FROM accounts_info WHERE AccountTcId = ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, tcId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String toCompare = resultSet.getString("AccountQuestionAnswer");
					isThere = toCompare.equals(answer);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return isThere;
	}
	
	public static String getCrediCartDb(String tcId) {
		String toReturn = null;
		String selectQ = "SELECT * FROM accounts_info WHERE AccountTcId = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, tcId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					toReturn = resultSet.getString("AccountCreditCartNo");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public static String getDebitCartDb(String tcId) {
		String toReturn = null;
		String selectQ = "SELECT * FROM accounts_info WHERE AccountTcId = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, tcId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					toReturn = resultSet.getString("AccountDebitCartNo");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public static String getIbanDb(String tcId) {
		String toReturn = null;
		String selectQ = "SELECT * FROM accounts_info WHERE AccountTcId = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, tcId);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					toReturn = resultSet.getString("AccountId");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public static boolean changePassword(String tcId, String newPassword) {
		String updateQ = "UPDATE accounts_info SET AccountPin = ? WHERE AccountTcId = ?";
		int rowAffected = -1;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, tcId);
			
			rowAffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected > 0;
	}
	
}
