package customer.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import database.operations.DbConnection;

public class Customer {
    private String name;
    private String surName;
    private String fatherName;
    private Date dateOfBirth;
    private String mail;
    private String address;
    private String city;
    private String state;
    private boolean gender;
    private String tcId;
    private String securityQ;
    private CustomerAccountInfo accountInfo;
    private String accountId;
    private Bills bills;

    private Customer(Builder builder) {
        this.name = builder.name;
        this.surName = builder.surName;
        this.fatherName = builder.fatherName;
        this.dateOfBirth = builder.dateOfBirth;
        this.mail = builder.mail;
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.gender = builder.gender;
        this.tcId = builder.tcId;
        this.securityQ = builder.securityQ;
        this.accountInfo = builder.accountInfo;
        this.accountId = builder.accountId;
        this.bills = builder.bills;
    }

    public String getName() { return name; }
	public String getSurName() { return surName; }
	public String getFatherName() { return fatherName; }
	public Date getDateOfBirth() { return dateOfBirth; }
	public String getMail() {return mail; }
	public String getAddress() { return address; }
	public String getCity() { return city; }
	public String getState() { return state; }
	public boolean isGender() { return gender; }
	public String getTcId() { return tcId; }
	public String getSecurityQ() { return securityQ; }
	public CustomerAccountInfo getAccountInfo() { return accountInfo; }
	public String getAccountId() { return accountId; }
    public void setAccountInfo(CustomerAccountInfo accountInfo) { this.accountInfo = accountInfo; }
    public Bills getBillsInfo() { return bills; }
    public void setBillInfo (Bills bills) { this.bills = bills; }
	

	public static class Builder {
        private String name;
        private String surName;
        private String fatherName;
        private Date dateOfBirth;
        private String mail;
        private String address;
        private String city;
        private String state;
        private boolean gender;
        private String tcId;
        private String securityQ;
        private CustomerAccountInfo accountInfo;
        private String accountId;
        private Bills bills;

        public Builder(String name, String surName, String mail) {
            this.name = name;
            this.surName = surName;
            this.mail = mail;
        }

        public Builder fatherName(String fatherName) {
            this.fatherName = fatherName;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Builder city(String city) {
        	this.city = city;
        	return this;
        }
        
        public Builder state(String state) {
        	this.state = state;
        	return this;
        }
        
        public Builder gender(boolean gender) {
        	this.gender = gender;
        	return this;
        }
        
        public Builder tcId(String tcId) {
        	this.tcId = tcId;
        	return this;
        }
        
        public Builder securityQ(String securityQ) {
        	this.securityQ = securityQ;
        	return this;
        }
        
        public Builder accountInfo(CustomerAccountInfo customerAccountInfo) {
        	this.accountInfo = customerAccountInfo;
        	return this;
        }
        
        public Builder accountInfo() {
        	this.accountInfo = new CustomerAccountInfo();
        	return this;
        }
        
        public Builder accountInfo(String securityQ, String securityA) {
        	this.accountInfo = new CustomerAccountInfo(securityQ, securityA);
        	return this;
        }
        
        public Builder accountId(String accountId) {
        	this.accountId = accountId;
        	return this;
        }
        
        public Builder bills(Bills bills) {
        	this.bills = bills;
        	return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
	
	public void insertToDbTable() {
		try (Connection connection = DbConnection.getConnection()) {
			String inserQ = "INSERT INTO users (UsersTcID, "
					+ "UserName, "
					+ "UserSurNamel, "
					+ "UserFatherName, "
					+ "UserGender, "
					+ "UserDateOfBirth, "
					+ "UserMailAddress, "
					+ "UserAdress, "
					+ "UserCity, "
					+ "UserState, "
					+ "UserAccountNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(inserQ)) {
                preparedStatement.setString(1, getTcId());
                preparedStatement.setString(2, getName());
                preparedStatement.setString(3, getSurName());
                preparedStatement.setString(4, getFatherName());
                preparedStatement.setString(5, (isGender()) ? "1" : "0");
                preparedStatement.setString(6, String.valueOf(new java.sql.Date(getDateOfBirth().getTime())));
                preparedStatement.setString(7, getMail());
                preparedStatement.setString(8, getAddress());
                preparedStatement.setString(9, getCity());
                preparedStatement.setString(10, getState());
                preparedStatement.setString(11, accountInfo.getAccountId());
                
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                    this.accountInfo.insertAccountIntoDb(getTcId());
                    this.bills.toDbTable();
                } else {
                    System.out.println("Data insertion failed.");
                }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 public static boolean isIdExists(String tcId) {
	        boolean idExists = false;
	        try (Connection connection = DbConnection.getConnection()) {
	        String sql = "SELECT * FROM users WHERE UsersTcId = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	              preparedStatement.setString(1, tcId);
	              try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    idExists = resultSet.next();
	                }
	            }
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return idExists;
	 }
	 
	 public static boolean isEmailExists(String mail) {
		 boolean mailExists = false;
		 String selectQ = "SELECT * FROM users WHERE UserMailAddress = ?";
		 try (Connection connection = DbConnection.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			 preparedStatement.setString(1, mail);
			 try (ResultSet resultSet = preparedStatement.executeQuery()) {
				 mailExists = resultSet.next();
			 }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return mailExists;
 	 }
	 
	 public static String getTcId(String mail) {
		 String tcId = null;
		 String selectQ = "SELECT * FROM users WHERE UserMailAddress = ?";
		 
		 try (Connection connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, mail);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					tcId = resultSet.getString("UsersTcId");
			}
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return tcId;
	 }
	 
	 public static String getSecurityQ(String mail) {
		 String securityQ = null;
		 String tcId = getTcId(mail);
		 String selectQ = "SELECT * FROM accounts_info WHERE AccountTcId = ?";
		 
		 try (Connection connection = DbConnection.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			 preparedStatement.setString(1, tcId);
			 try (ResultSet resultSet = preparedStatement.executeQuery()) {
				 if (resultSet.next())
					 securityQ = resultSet.getString("AccountSecurityQuestion");
			 }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return securityQ;
	 }

//    public static void main(String []argv) {
//    	Customer customer = new Customer.Builder("John", "Doe", "john.doe@example.com")
//    		    .fatherName("John Doe Sr.")
//    		    .dateOfBirth(new Date())
//    		    .address("123 Main Street")
//    		    .city("Anytown")
//    		    .state("CA")
//    		    .gender(true)
//    		    .tcId(123456789)
//    		    .securityQ("Favorite color")
//    		    .build();
//
//    }
    // Other methods and fields of the Customer class...
}
