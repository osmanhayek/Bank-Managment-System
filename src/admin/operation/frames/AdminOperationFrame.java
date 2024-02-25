package admin.operation.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.operations.DbConnection;
import employee.frames.AOperationFrame;
import employee.frames.ViewCustomerInfo;

public class AdminOperationFrame extends AOperationFrame implements IAdminOperations {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public AdminOperationFrame(JFrame preFrame) {
		super();
		this.preFrame = preFrame;
		
		this.topLeftButton.setText("Employee ragistration");
		this.topRigthButton.setText("Delete customer");
		this.leftMiddleButton.setText("Delete employee");
		this.middleRightButton.setText("view Employee info");
		this.leftBottomButton.setText("view customer info");
		this.bottomRightButton.setVisible(false);
		
		this.topRigthButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (deleteCustomer())
					JOptionPane.showMessageDialog(AdminOperationFrame.this, "Done succesfuly");
			}
		});
		
		this.leftMiddleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (deleteEmployee())
					JOptionPane.showMessageDialog(AdminOperationFrame.this, "Done succesfuly");
			}
		});
		
		this.middleRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewEmployee();
			}
		});
		
		this.leftBottomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewCustomer();
			}
		});
		
		this.topLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				employeeReg();
			}
		});
	}

	@Override
	public boolean employeeReg() {
		EmployeeRegisterForm employeeRegisterForm = new EmployeeRegisterForm(this);
		employeeRegisterForm.setVisible(true);
		
		return true;
	}

	@Override
	public boolean deleteCustomer() {
		String tcId = JOptionPane.showInputDialog(this, "Please write the customer's tcId");
		if (tcId == null)
			return false;
		if (tcId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid input");
			return false;
		}
		
		String deleteQ = "DELETE FROM accounts_info WHERE AccountTcId = ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteQ)) {
			preparedStatement.setString(1, tcId);
			int rowA = preparedStatement.executeUpdate();
			
			if (rowA > 0)
				return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee() {
		String tcId = JOptionPane.showInputDialog(this, "Please write the customer's tcId");
		if (tcId == null)
			return false;
		if (tcId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Invalid input");
			return false;
		}
		
		String deleteQ = "DELETE FROM employee_info WHERE EmployeeTcId = ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteQ)) {
			preparedStatement.setString(1, tcId);
			int rowA = preparedStatement.executeUpdate();
			
			if (rowA > 0)
				return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean viewCustomer() {
		ViewCustomerInfo viewCustomerInfo = new ViewCustomerInfo("users");
		viewCustomerInfo.setVisible(true);
		viewCustomerInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return true;
	}

	@Override
	public boolean viewEmployee() {
		ViewCustomerInfo viewCustomerInfo = new ViewCustomerInfo("employee_info");
		viewCustomerInfo.setVisible(true);
		return true;
	}

}
