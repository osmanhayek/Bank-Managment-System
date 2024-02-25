package customer.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customer.info.Bills;
import customer.info.Customer;
import database.operations.DbConnection;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BillsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame preFrame;
	private Customer customer;

	public BillsFrame(Customer customer_1) {
		this.customer = customer_1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Go back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				preFrame.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(10, 10, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Electric Bill");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 63, 114, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Water Bill");
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(50, 124, 114, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gas Bill");
		lblNewLabel_2.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(50, 184, 114, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel electricBill = new JLabel("31.69$");
		electricBill.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		electricBill.setBounds(198, 63, 55, 13);
		contentPane.add(electricBill);
		
		JCheckBox electricPaid = new JCheckBox("Paid");
		electricPaid.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		electricPaid.setEnabled(false);
		electricPaid.setBounds(280, 59, 93, 21);
		contentPane.add(electricPaid);
		
		JButton electricButton = new JButton("Pay");
		electricButton.setBounds(411, 60, 85, 21);
		contentPane.add(electricButton);
		
		JLabel waterBill = new JLabel("5.31$");
		waterBill.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		waterBill.setBounds(198, 124, 55, 13);
		contentPane.add(waterBill);
		
		JCheckBox waterPaid = new JCheckBox("Paid");
		waterPaid.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		waterPaid.setEnabled(false);
		waterPaid.setBounds(280, 120, 93, 21);
		contentPane.add(waterPaid);
		
		JButton waterButton = new JButton("Pay");
		waterButton.setBounds(411, 121, 85, 21);
		contentPane.add(waterButton);
		
		JLabel gasBill = new JLabel("15.69$");
		gasBill.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		gasBill.setBounds(198, 184, 55, 13);
		contentPane.add(gasBill);
		
		JCheckBox gasPaid = new JCheckBox("Paid");
		gasPaid.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		gasPaid.setEnabled(false);
		gasPaid.setBounds(280, 180, 93, 21);
		contentPane.add(gasPaid);
		
		JButton gasButton = new JButton("Pay");
		gasButton.setBounds(411, 181, 85, 21);
		contentPane.add(gasButton);
		
		Bills bill = this.customer.getBillsInfo();
		
		if (bill.isElectricBill())
			electricPaid.setSelected(true);
		if (bill.isWaterBill())
			waterPaid.setSelected(true);
		if (bill.isGasBill())
			gasPaid.setSelected(true);
		
		electricButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bill.isElectricBill())
					return ;
				int isCanceled = JOptionPane.showConfirmDialog(BillsFrame.this, "This operation gonna cost you 31.69$, Are you sure?");
				if (isCanceled == JOptionPane.CANCEL_OPTION || isCanceled == JOptionPane.NO_OPTION)
					return ;
				String updateQ = "UPDATE accounts_info SET AccountAmount = AccountAmount - ? WHERE AccountId = ?";
				double toPay = 31.69;
				double preAmount = customer.getAccountInfo().getAmount();
				
				if (preAmount < toPay) {
					JOptionPane.showMessageDialog(BillsFrame.this, "You don't have enoph money in your account!", "Error", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				try (Connection connection = DbConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
					preparedStatement.setDouble(1, toPay);
					preparedStatement.setString(2, customer.getAccountId());
					
					int rowAff = preparedStatement.executeUpdate();
					if (rowAff > 0) {
						if (Bills.updateElectricBill(customer.getTcId(), true)) {
							customer.getBillsInfo().setElectricBill(true);
							JOptionPane.showMessageDialog(BillsFrame.this, "The invoice has been paid successfully");
							electricPaid.setSelected(true);
						}
					}
					else
						return ;
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		gasButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (bill.isGasBill())
					return ;
				int isCanceled = JOptionPane.showConfirmDialog(BillsFrame.this, "This operation gonna cost you 15.69$, Are you sure?");
				if (isCanceled == JOptionPane.CANCEL_OPTION || isCanceled == JOptionPane.NO_OPTION)
					return ;
				String updateQ = "UPDATE accounts_info SET AccountAmount = AccountAmount - ? WHERE AccountId = ?";
				double toPay = 15.69;
				double preAmount = customer.getAccountInfo().getAmount();
				
				if (preAmount < toPay) {
					JOptionPane.showMessageDialog(BillsFrame.this, "You don't have enoph money in your account!", "Error", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				try (Connection connection = DbConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
					preparedStatement.setDouble(1, toPay);
					preparedStatement.setString(2, customer.getAccountId());
					
					int rowAff = preparedStatement.executeUpdate();
					if (rowAff > 0) {
						if (Bills.updateGasBill(customer.getTcId(), true)) {
							customer.getBillsInfo().setGasBill(true);
							JOptionPane.showMessageDialog(BillsFrame.this, "The invoice has been paid successfully");
							gasPaid.setSelected(true);
						}
					}
					else
						return ;
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		waterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bill.isWaterBill())
					return ;
				int isCanceled = JOptionPane.showConfirmDialog(BillsFrame.this, "This operation gonna cost you 5.31$, Are you sure?");
				if (isCanceled == JOptionPane.CANCEL_OPTION || isCanceled == JOptionPane.NO_OPTION)
					return ;
				String updateQ = "UPDATE accounts_info SET AccountAmount = AccountAmount - ? WHERE AccountId = ?";
				double toPay = 5.31;
				double preAmount = customer.getAccountInfo().getAmount();
				
				if (preAmount < toPay) {
					JOptionPane.showMessageDialog(BillsFrame.this, "You don't have enoph money in your account!", "Error", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				try (Connection connection = DbConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
					preparedStatement.setDouble(1, toPay);
					preparedStatement.setString(2, customer.getAccountId());
					
					int rowAff = preparedStatement.executeUpdate();
					if (rowAff > 0) {
						if (Bills.updateWaterBill(customer.getTcId(), true)) {
							customer.getBillsInfo().setWaterBill(true);
							JOptionPane.showMessageDialog(BillsFrame.this, "The invoice has been paid successfully");
							waterPaid.setSelected(true);
						}
					}
					else
						return ;
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void setPreFrame(JFrame preFrame) { this.preFrame = preFrame; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	
}
