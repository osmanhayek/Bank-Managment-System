package employee.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import customer.info.Customer;
import database.operations.DbConnection;

public class EmployeeOperationFrame extends AOperationFrame implements IEmployeeOperations {

	private static final long serialVersionUID = 1L;

	public EmployeeOperationFrame(JFrame preFrame, String eMail) {
		super();
		this.preFrame = preFrame;
		this.topLeftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registerCustomer();
			}
		});
		
		this.leftMiddleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tcId = JOptionPane.showInputDialog(EmployeeOperationFrame.this, "Please enter the customer's tc id");
				if (tcId == null)
						return ;
				if (tcId.isEmpty()) {
					JOptionPane.showMessageDialog(EmployeeOperationFrame.this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if (!Customer.isIdExists(tcId)) {
					JOptionPane.showMessageDialog(EmployeeOperationFrame.this, "No such tcId like this in the db!", "Error", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				if (confirmCredit(tcId))
					JOptionPane.showMessageDialog(EmployeeOperationFrame.this, "Done succsesfully!");
				else
					JOptionPane.showMessageDialog(EmployeeOperationFrame.this, "Error!!");
			}
		});
		
		this.topRigthButton.setText("<html>  View Customer<br>info</html>");
		this.topRigthButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				viewCustomerInfo();
			}
		});
		
		this.middleRightButton.setText("<html>  Customer Info<br>&nbsp;to csv</html>");
		this.middleRightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableToCsvFile();
			}
		});
		
		this.leftBottomButton.setText("Write Reports");
		this.leftBottomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				writeRapors(eMail);
			}
		});
		
		
		this.bottomRightButton.setVisible(false);

	}

	@Override
	public boolean registerCustomer() {
		CustomerRegFrame customerRegFrame = new CustomerRegFrame(EmployeeOperationFrame.this);
		customerRegFrame.setVisible(true);
		setVisible(false);
		return true;
	}

	@Override
	public boolean confirmCredit(String tcId) {
		int rowEffected = -1;
		String updateQ = "UPDATE accounts_info SET AccountCreditCartCon = ? WHERE AccountTcId = ?";
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateQ)) {
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, tcId);
			
			rowEffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowEffected > 0;
	}

	@Override
	public boolean viewCustomerInfo() {
		ViewCustomerInfo viewCustomerInfo = new ViewCustomerInfo("users");
		viewCustomerInfo.setVisible(true);
		return true;
	}

	@Override
	public void tableToCsvFile() {
        try {
            Connection connection = DbConnection.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            // Create a FileWriter to write data to a CSV file
            File directory = new File("csvFiles");
            
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created successfully: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                    return;
                }
            }
            
            String filePath = "csvFiles" + File.separator + "SQLTableData.csv";
            try (FileWriter csvWriter = new FileWriter(filePath)) {

                // Write header row
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getMetaData().getColumnName(i));
                    if (i < columnCount) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");

                // Write data rows
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        csvWriter.append(resultSet.getString(i));
                        if (i < columnCount) {
                            csvWriter.append(",");
                        }
                    }
                    csvWriter.append("\n");
                }

                JOptionPane.showMessageDialog(this, "CSV file created successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void writeRapors(String eMail) {
		EmployeeRaporFrame employeeRaporFrame = new EmployeeRaporFrame(getTcIdFromEmployeeTable(eMail));
		employeeRaporFrame.setVisible(true);
		employeeRaporFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

       employeeRaporFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	employeeRaporFrame.endTime = System.currentTimeMillis();
                long passingTime = employeeRaporFrame.endTime - employeeRaporFrame.startTime;

                SimpleDateFormat sureFormat = new SimpleDateFormat("HH:mm:ss");
                sureFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                String formattedSure = sureFormat.format(new Date(passingTime));
                
                if (employeeRaporFrame.kayitEkle(formattedSure, employeeRaporFrame.editorPane.getText(), getTcIdFromEmployeeTable(eMail)))
                	JOptionPane.showMessageDialog(employeeRaporFrame, "Done succusfuly!");
                employeeRaporFrame.dispose();
            }
        });
	}
	
	public static String getTcIdFromEmployeeTable(String eMail) {
		String selectQ = "SELECT * FROM employee_info WHERE EmployeeMail = ?";
		String toReturn = "";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectQ)) {
			preparedStatement.setString(1, eMail);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					toReturn = resultSet.getString("EmployeeTcId");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
//	public static void main(String[] argv) {
//		EmployeeOperationFrame employeeOperationFrame = new EmployeeOperationFrame(null);
//		employeeOperationFrame.setVisible(true);
//	}
}
