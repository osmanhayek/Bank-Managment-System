package employee.frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import database.operations.DbConnection;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewCustomerInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;

    public ViewCustomerInfo(String tableName) {
        setTitle("MySQL to JTable Example");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        try {
            Connection connection = DbConnection.getConnection();

            // Execute query to retrieve data
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            // Create JTable and DefaultTableModel
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table = new JTable(model);

            // Get metadata for column names
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            // Populate data into the model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Adjust column widths dynamically
            for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
                TableColumn column = table.getColumnModel().getColumn(columnIndex);
                int maxWidth = 0;
                for (int row = 0; row < table.getRowCount(); row++) {
                    TableCellRenderer cellRenderer = table.getCellRenderer(row, columnIndex);
                    Component cellComponent = table.prepareRenderer(cellRenderer, row, columnIndex);
                    maxWidth = Math.max(maxWidth, cellComponent.getPreferredSize().width);
                }
                column.setPreferredWidth(maxWidth + 10); // Add some padding
            }

            // Add JTable to JFrame
            JScrollPane scrollPane = new JScrollPane(table);
            getContentPane().add(scrollPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
