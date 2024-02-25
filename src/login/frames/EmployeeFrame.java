package login.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import customer.info.Customer;
import customer.info.CustomerAccountInfo;
import database.operations.DbConnection;
import employee.frames.AOperationFrame;
import employee.frames.EmployeeOperationFrame;
import validators.EmailValidatorStrict;

public class EmployeeFrame extends ALoginFrame {

	private static final long serialVersionUID = 1L;
	private JFrame currentFrame;
	
	public EmployeeFrame() {
		super();
		this.currentFrame = this;
		this.lblNewLabel_1.setText("Email");
//		this.loginButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String email = idField.getText();
//
//				if (!EmailValidatorStrict.isValid(email))
//					JOptionPane.showMessageDialog(currentFrame, EmailValidatorStrict.getErrorMessage(email), "Warning", JOptionPane.WARNING_MESSAGE);
//			}
//		});
		
		this.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkLogin()) {
					JOptionPane.showMessageDialog(EmployeeFrame.this, "Logged successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
					EmployeeOperationFrame employeeOperationFrame = new EmployeeOperationFrame(EmployeeFrame.this, idField.getText());
					setVisible(false);
					employeeOperationFrame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(EmployeeFrame.this, "Wrong eMail or password try again!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		changePasswordLogic();
	}
	
	public boolean checkLogin() {
		String mail = this.idField.getText();
		String password = String.valueOf(this.passwordField.getPassword());
		String selectQ = "SELECT * FROM employee_info WHERE EmployeeMail = ? AND EmployeePassword = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	 public static boolean isEmailExists(String mail) {
		 boolean mailExists = false;
		 String selectQ = "SELECT * FROM employee_info WHERE EmployeeMail = ?";
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
	 
	public static boolean checkAnswer(String tcId, String answer) {
			boolean isThere = false;
			
			String selectQ = "SELECT * FROM employee_info WHERE EmployeeMail = ?";
			try (Connection connection = DbConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
				preparedStatement.setString(1, tcId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						String toCompare = resultSet.getString("EmployeeSecurityA");
						isThere = toCompare.equals(answer);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return isThere;
		}
		
	public static boolean changePassword(String tcId, String newPassword) {
			String updateQ = "UPDATE employee_info SET EmployeePassword = ? WHERE EmployeeMail = ?";
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
	
	 public static String getSecurityQ(String mail) {
		 String securityQ = null;
		 String selectQ = "SELECT * FROM employee_info WHERE EmployeeMail = ?";
		 
		 try (Connection connection = DbConnection.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			 preparedStatement.setString(1, mail);
			 try (ResultSet resultSet = preparedStatement.executeQuery()) {
				 if (resultSet.next())
					 securityQ = resultSet.getString("EmployeeSecurityQ");
			 }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return securityQ;
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
	                    if (!EmployeeFrame.isEmailExists(userEmail[0])) {
	                        JOptionPane.showMessageDialog(changePasswordFrame, "Couldn't find this mail in the database, please try another one", "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                    changePasswordFrame.mainL.next(changePasswordFrame.contentPane);
	                    changePasswordFrame.securityQuestionLabel.setText(EmployeeFrame.getSecurityQ(userEmail[0]));
	                    changePasswordFrame.page2NextButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!EmployeeFrame.checkAnswer(userEmail[0], changePasswordFrame.answerLabel.getText())) {
									JOptionPane.showMessageDialog(changePasswordFrame, "You got it wrong please try again", "Error", JOptionPane.ERROR_MESSAGE);
									return ;
								}
								changePasswordFrame.mainL.next(changePasswordFrame.contentPane);
								
								changePasswordFrame.newPasswordLabel.setText("New Password:");
								changePasswordFrame.confirmPasswordLabel.setText("Confirm Password:");
								
								
								
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
										if (EmployeeFrame.changePassword(userEmail[0], String.valueOf(changePasswordFrame.newPasswordField.getPassword()))) {
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
