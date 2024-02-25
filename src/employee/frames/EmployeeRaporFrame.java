package employee.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.operations.DbConnection;

import javax.swing.JEditorPane;
import javax.swing.JButton;

public class EmployeeRaporFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public long startTime;
	public long endTime;
	public JEditorPane editorPane;


	public EmployeeRaporFrame(String tcId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setEnabled(false);
		editorPane.setBounds(10, 10, 426, 199);
		contentPane.add(editorPane);
		
		JButton startButton = new JButton("Start");
		startButton.setBounds(177, 232, 85, 21);
		contentPane.add(startButton);
		
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	editorPane.setEnabled(true);
            	startTime = System.currentTimeMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
                startButton.setEnabled(false);
            }
        });
        
	}
	
	public boolean kayitEkle(String passedTime, String raporText, String tcId) {
		String insertQ = "INSERT INTO employee_rapors (RaporsContext, RaporsPassedTime, EmployeeTcId) VALUES (?, ?, ?)";
		int isEffected = -1;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertQ)) {
			preparedStatement.setString(1, raporText);
			preparedStatement.setString(2, passedTime);
			preparedStatement.setString(3, tcId);
			
			isEffected = preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return isEffected > 0;
	}
	
//	public static void main(String[] argv) {
//		EmployeeRaporFrame employeeRaporFrame = new EmployeeRaporFrame("14141414141");
//		employeeRaporFrame.setVisible(true);
//	}
}
