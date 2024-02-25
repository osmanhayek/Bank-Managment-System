package login.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import admin.operation.frames.AdminOperationFrame;
import database.operations.DbConnection;

public class AdminLogin extends ALoginFrame {

	private static final long serialVersionUID = 1L;

	public AdminLogin() {
		super();
		
		this.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkLogin()) {
					JOptionPane.showMessageDialog(AdminLogin.this, "Logged successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
					AdminOperationFrame adminOperationFrame = new AdminOperationFrame(AdminLogin.this);
					setVisible(false);
					adminOperationFrame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(AdminLogin.this, "Wrong eMail or password try again!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
	public boolean checkLogin() {
		String id = this.idField.getText();
		String password = String.valueOf(this.passwordField.getPassword());
		String selectQ = "SELECT * FROM admin WHERE adminId = ? AND adminPassword = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
