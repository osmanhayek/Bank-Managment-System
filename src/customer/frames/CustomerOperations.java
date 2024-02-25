package customer.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import customer.info.Bills;
import customer.info.Customer;
import customer.info.CustomerAccountInfo;
import database.operations.DbConnection;
import employee.frames.AOperationFrame;

public class CustomerOperations extends AOperationFrame implements ICustomerOperation{

	private static final long serialVersionUID = 1L;
	private Customer customer;

	public CustomerOperations(Customer customer_1) {
		super();
		customer = customer_1;
		customer.setAccountInfo(CustomerAccountInfo.getAccountObjFromDb(this.customer.getAccountId()));
		this.customer.setBillInfo(Bills.getBillfromDb(customer.getTcId()));
		this.mainLabel.setText("Customer Page");
		this.topLeftButton.setText("    Deposit money    ");
		this.topRigthButton.setText("Withdraw money");
		this.leftMiddleButton.setText("Transfer money");
		this.middleRightButton.setText("Balance inquiry");
		this.leftBottomButton.setText("Pay bills");
		this.bottomRightButton.setText("<html>  Account<br>Information</html>");
		
		this.topLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (depositMoney())
					System.out.println("Yes be!\n");
				
			}
		});
		
		this.topRigthButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (withdrawMoney())
					System.out.println("Yes be!\n");
			}
		});
		
		this.leftMiddleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (transferMoney()) {
					System.out.println("Yes be!\n");
				}
			}
		});
		
		this.middleRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (balanceInquiry())
					System.out.println("Yes be!\n");
					
			}
		});
		
		this.goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerOperations.this.setVisible(false);
				CustomerOperations.this.preFrame.setVisible(true);
			}
		});
		
		this.leftBottomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BillsFrame billsFrame = new BillsFrame(customer);
				billsFrame.setPreFrame(CustomerOperations.this);
				setVisible(false);
				billsFrame.setVisible(true);
			}
		});
		
		this.bottomRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AccountDetailsFrame accountDetailsFrame = new AccountDetailsFrame(CustomerOperations.this.customer.getTcId());
				accountDetailsFrame.setVisible(true);
			}
		});
	}
	
	public void setPreFrame(JFrame preFrame) { this.preFrame = preFrame; }

	@Override
	public boolean depositMoney() {
		String depositAmountS = JOptionPane.showInputDialog(this, "Enter deposit amount");
		double preAmount;

		if (depositAmountS != null) {
			try {
				double depositAmount = Double.parseDouble(depositAmountS);
				if (depositAmount >= 6900.31 || depositAmount <= 0) {
					JOptionPane.showMessageDialog(this, "You cannot deposit this amount!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				preAmount = customer.getAccountInfo().getAmount();
				customer.getAccountInfo().setAmount(depositAmount + preAmount);
				try (Connection connection = DbConnection.getConnection()) {
					String updateQ = "UPDATE accounts_info SET AccountAmount = ? WHERE AccountId = ?";
					try (PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
						preparedStatement.setDouble(1, depositAmount + preAmount);
						preparedStatement.setString(2, customer.getAccountId());
						
						int rowAffected = preparedStatement.executeUpdate();
						
						if (rowAffected > 0) {
							JOptionPane.showMessageDialog(this, "Deposit transaction completed successfully");
							return true;
						}
						else
							return false;
					}
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean withdrawMoney() {
		String withdrawalAmountS = JOptionPane.showInputDialog(this, "Enter withdrawal amount");
		double preAmount;

		if (withdrawalAmountS != null) {
			try {
				double withrdawalAmount = Double.parseDouble(withdrawalAmountS);
				if (withrdawalAmount <= 0) {
					JOptionPane.showMessageDialog(this, "You cannot withdraw this amount!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				preAmount = customer.getAccountInfo().getAmount();
				if (preAmount < withrdawalAmount) {
					JOptionPane.showMessageDialog(this, "You Dont have enoph money in your account!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				customer.getAccountInfo().setAmount(preAmount - withrdawalAmount);
				try (Connection connection = DbConnection.getConnection()) {
					String updateQ = "UPDATE accounts_info SET AccountAmount = ? WHERE AccountId = ?";
					try (PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
						preparedStatement.setDouble(1, preAmount - withrdawalAmount);
						preparedStatement.setString(2, customer.getAccountId());
						
						int rowAffected = preparedStatement.executeUpdate();
						
						if (rowAffected > 0) {
							JOptionPane.showMessageDialog(this, "Withdrawal transaction completed successfully");
							return true;
						}
						else
							return false;
					}
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean transferMoney() {
		String iban = JOptionPane.showInputDialog(this, "Enter the recipient's IBAN:");
		if (iban == null) return false;
		String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to transfer:");
		if (amountStr == null) return false;
		double preAmount = customer.getAccountInfo().getAmount();

		if (iban != null && amountStr != null || (!iban.isEmpty() && !amountStr.isEmpty())) {
			try {
				double amount = Double.parseDouble(amountStr);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(this, "You cannot transfer this amount!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if (preAmount < amount) {
					JOptionPane.showMessageDialog(this, "You Dont have enoph money in your account!", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				String updateQ = "UPDATE accounts_info SET AccountAmount = AccountAmount - ? WHERE AccountId = ?";
				String updateRQ = "UPDATE accounts_info SET AccountAmount = AccountAmount + ? WHERE AccountId = ?";
				try (Connection connection = DbConnection.getConnection();
						PreparedStatement preparedStatement = connection.prepareStatement(updateQ);
						PreparedStatement preparedStatement2 = connection.prepareStatement(updateRQ)) {
					connection.setAutoCommit(false);
					
					preparedStatement.setDouble(1, amount);
					preparedStatement.setString(2, customer.getAccountId());
					preparedStatement.executeUpdate();
					
					preparedStatement2.setDouble(1, amount);
					preparedStatement2.setString(2, iban);
					int r = preparedStatement2.executeUpdate();
					if (r > 0) 
						JOptionPane.showMessageDialog(this, "transfer transaction completed successfully");
					else
						JOptionPane.showMessageDialog(this, "Couldn't find iban!");
					connection.commit();
					connection.setAutoCommit(true);
					
					customer.getAccountInfo().setAmount(customer.getAccountInfo().getAmount() - amount);
					return true;
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean balanceInquiry() {
		String selectQ = "SELECT AccountAmount FROM accounts_info WHERE AccountId = ?";
		double amount;

		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, customer.getAccountId());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					amount = resultSet.getDouble("AccountAmount");
					JOptionPane.showMessageDialog(this, "You have " + String.valueOf(amount) + "$ in your Account!");
					return true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean payBill() {
		BillsFrame billsFrame = new BillsFrame(customer);
		billsFrame.setPreFrame(this);
		setVisible(false);
		billsFrame.setVisible(true);
		return true;
	}

	@Override
	public boolean investmentTr() {
		// TODO Auto-generated method stub
		return false;
	}
}