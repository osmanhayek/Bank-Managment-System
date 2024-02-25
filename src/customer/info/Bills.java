package customer.info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.operations.DbConnection;

public class Bills {
	private String tcId;
	private boolean electricBill;
	private boolean waterBill;
	private boolean gasBill;
	
	public Bills(String tcId) {
		this.tcId = tcId;
		setElectricBill(false);
		setWaterBill(false);
		setGasBill(false);
	}
	
	public Bills(String tcId, boolean electricBill, boolean waterBill, boolean gasBill) {
		this.tcId = tcId;
		this.electricBill = electricBill;
		this.waterBill = waterBill;
		this.gasBill = gasBill;
	}

	public boolean isElectricBill() {
		return electricBill;
	}

	public void setElectricBill(boolean electricBill) {
		this.electricBill = electricBill;
	}

	public boolean isWaterBill() {
		return waterBill;
	}

	public void setWaterBill(boolean waterBill) {
		this.waterBill = waterBill;
	}

	public boolean isGasBill() {
		return gasBill;
	}

	public void setGasBill(boolean gasBill) {
		this.gasBill = gasBill;
	}
	
	public boolean toDbTable() {
		String insertQ = "INSERT INTO user_bill (UserTcId, ElectricBill, WaterBill, NaturalGasBill) VALUES (?, ?, ?, ?)";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertQ)) {
			preparedStatement.setString(1, tcId);
			preparedStatement.setBoolean(2, electricBill);
			preparedStatement.setBoolean(3, waterBill);
			preparedStatement.setBoolean(4, gasBill);
			int r = preparedStatement.executeUpdate();
			if (r > 0)
				return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static Bills getBillfromDb(String tcId) {
		Bills bill;
		
		try (Connection connection = DbConnection.getConnection()) {
			String selectQ = "SELECT * FROM user_bill WHERE UserTcId = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
				preparedStatement.setString(1, tcId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						bill = new Bills(tcId, resultSet.getBoolean("ElectricBill"), resultSet.getBoolean("WaterBill"), resultSet.getBoolean("NaturalGasBill"));
						return bill;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean updateElectricBill(String tcId, boolean electricBill) {
		String updateQ = "UPDATE user_bill SET ElectricBill = ? WHERE UserTcId = ?";
		int rowEffected = -1;
		try (Connection connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
			preparedStatement.setBoolean(1, electricBill);
			preparedStatement.setString(2, tcId);
			
			rowEffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowEffected > 0;
	}
	
	public static boolean updateGasBill(String tcId, boolean gasBill) {
		String updateQ = "UPDATE user_bill SET NaturalGasBill = ? WHERE UserTcId = ?";
		int rowEffected = -1;
		try (Connection connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
			preparedStatement.setBoolean(1, gasBill);
			preparedStatement.setString(2, tcId);
			
			rowEffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowEffected > 0;
	}
	
	public static boolean updateWaterBill(String tcId, boolean waterBill) {
		String updateQ = "UPDATE user_bill SET WaterBill = ? WHERE UserTcId = ?";
		int rowEffected = -1;
		try (Connection connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
			preparedStatement.setBoolean(1, waterBill);
			preparedStatement.setString(2, tcId);
			
			rowEffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowEffected > 0;
	}
	
	public static Bills billFromFb(String tcId) {
		return null;
	}
}
