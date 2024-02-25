package login.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import customer.frames.CustomerOperations;
import customer.info.Bills;
import customer.info.Customer;
import customer.info.CustomerAccountInfo;
import database.operations.DbConnection;
import employee.frames.AOperationFrame;
import validators.EmailValidatorStrict;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;


public class CustomerFrame extends ALoginFrame {

	private static final long serialVersionUID = 1L;
	public CustomerFrame()
	{
		super();
		this.lblNewLabel_1.setText("TC id:");
		this.passwordLabel.setText("PIN: ");
		
		((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new DocumentFilter() {
		    @Override
		    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
		                             AttributeSet attr) throws BadLocationException {
		        if (string.matches("\\d") && (fb.getDocument().getLength() + string.length() <= 4)) {
		            super.insertString(fb, offset, string, attr);
		        }
		    }

		    @Override
		    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
		                        AttributeSet attrs) throws BadLocationException {
		        if (text.matches("\\d") && (fb.getDocument().getLength() - length + text.length() <= 4)) {
		            super.replace(fb, offset, length, text, attrs);
		        }
		    }
		});
		
		((AbstractDocument) idField.getDocument()).setDocumentFilter(new DocumentFilter() {
		    @Override
		    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
		                             AttributeSet attr) throws BadLocationException {
		        if (string.matches("\\d") && (fb.getDocument().getLength() + string.length() <= 11)) {
		            super.insertString(fb, offset, string, attr);
		        }
		    }

		    @Override
		    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
		                        AttributeSet attrs) throws BadLocationException {
		        if (text.matches("\\d") && (fb.getDocument().getLength() - length + text.length() <= 11)) {
		            super.replace(fb, offset, length, text, attrs);
		        }
		    }
		});
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(CustomerFrame.this, "Tc Id or pin cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				if (checkLogin()) {
					JOptionPane.showMessageDialog(CustomerFrame.this, "logged successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
					AOperationFrame customerOpFrame = new CustomerOperations(getCustomerObjFromDb(idField.getText()));
					customerOpFrame.setPreFrame(CustomerFrame.this);
					CustomerFrame.this.setVisible(false);
					customerOpFrame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(CustomerFrame.this, "Wrong tc id or pin try again!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		changePasswordLogic();
	}
	
	private boolean checkLogin() {
		String tcId = idField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String SQL_ACCOUNT_CHECK = "SELECT * FROM accounts_info WHERE AccountTcId = ? AND AccountPin = ?";
		
		 try (Connection connection = DbConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ACCOUNT_CHECK)) {
			
			preparedStatement.setString(1, tcId);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Customer getCustomerObjFromDb(String tcId) {
		Customer customer;

		try (Connection connection = DbConnection.getConnection()) {
			String selectQuery = "SELECT * FROM users WHERE UsersTcId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
				preparedStatement.setString(1, tcId);
				
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
				    	customer = new Customer.Builder(resultSet.getString("UserName"), resultSet.getString("UserName"), resultSet.getString("UserMailAddress"))
				    		    .fatherName(resultSet.getString("UserFatherName"))
				    		    .dateOfBirth(resultSet.getDate("UserDateOfBirth"))
				    		    .address(resultSet.getString("UserAdress"))
				    		    .city(resultSet.getString("UserCity"))
				    		    .state(resultSet.getString("UserState"))
				    		    .gender(resultSet.getBoolean("UserGender"))
				    		    .tcId(resultSet.getString("UsersTcId"))
				    		    .accountId(resultSet.getString("UserAccountNumber"))
				    		    .build();
				    	return customer;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void changePasswordLogic() {
	    // Declare the email variable outside the mouseClicked method
	    final String userEmail[] = new String[1];

	    changePasswordLabel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame();

	            changePasswordFrame.page1NextButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    userEmail[0] = changePasswordFrame.mailField.getText();
	                    if (!Customer.isEmailExists(userEmail[0])) {
	                        JOptionPane.showMessageDialog(changePasswordFrame, "Couldn't find this mail in the database, please try another one", "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                    changePasswordFrame.mainL.next(changePasswordFrame.contentPane);
	                    changePasswordFrame.securityQuestionLabel.setText(Customer.getSecurityQ(userEmail[0]));
	                    changePasswordFrame.page2NextButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!CustomerAccountInfo.checkAnswer(Customer.getTcId(userEmail[0]), changePasswordFrame.answerLabel.getText())) {
									JOptionPane.showMessageDialog(changePasswordFrame, "You got it wrong please try again", "Error", JOptionPane.ERROR_MESSAGE);
									return ;
								}
								changePasswordFrame.mainL.next(changePasswordFrame.contentPane);
								
								((AbstractDocument) changePasswordFrame.newPasswordField.getDocument()).setDocumentFilter(new DocumentFilter() {
								    @Override
								    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
								                             AttributeSet attr) throws BadLocationException {
								        if (string.matches("\\d") && (fb.getDocument().getLength() + string.length() <= 4)) {
								            super.insertString(fb, offset, string, attr);
								        }
								    }

								    @Override
								    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
								                        AttributeSet attrs) throws BadLocationException {
								        if (text.matches("\\d") && (fb.getDocument().getLength() - length + text.length() <= 4)) {
								            super.replace(fb, offset, length, text, attrs);
								        }
								    }
								});
								
								((AbstractDocument) changePasswordFrame.confirmPasswordField.getDocument()).setDocumentFilter(new DocumentFilter() {
								    @Override
								    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
								                             AttributeSet attr) throws BadLocationException {
								        if (string.matches("\\d") && (fb.getDocument().getLength() + string.length() <= 4)) {
								            super.insertString(fb, offset, string, attr);
								        }
								    }

								    @Override
								    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
								                        AttributeSet attrs) throws BadLocationException {
								        if (text.matches("\\d") && (fb.getDocument().getLength() - length + text.length() <= 4)) {
								            super.replace(fb, offset, length, text, attrs);
								        }
								    }
								});
								
								changePasswordFrame.newPasswordLabel.setText("New PIN:");
								changePasswordFrame.confirmPasswordLabel.setText("Confirm PIN:");
								
								
								
								changePasswordFrame.changePasswordButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										if (String.valueOf(changePasswordFrame.newPasswordField.getPassword()).isEmpty() ||
												String.valueOf(changePasswordFrame.confirmPasswordField.getPassword()).isEmpty()) {
									JOptionPane.showMessageDialog(changePasswordFrame, "Password field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
										return;
										}
										if (!String.valueOf(changePasswordFrame.newPasswordField.getPassword()).equals(String.valueOf(changePasswordFrame.confirmPasswordField.getPassword()))) {
											JOptionPane.showMessageDialog(changePasswordFrame, "Passwords doesnt match, Try again", "Warning", JOptionPane.WARNING_MESSAGE);
											return ;
										}
										if (CustomerAccountInfo.changePassword(Customer.getTcId(userEmail[0]), String.valueOf(changePasswordFrame.newPasswordField.getPassword()))) {
											JOptionPane.showMessageDialog(changePasswordFrame, "Password changed succsufuly!");
											changePasswordFrame.dispose();
										}
									}
								});
							}
						});
	                }
	            });
	            
	            changePasswordFrame.setVisible(true);
	        }
	    });
	}

}
