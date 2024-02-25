package admin.operation.frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import database.operations.DbConnection;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EmployeeRegisterForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField mailField;
	private JTextField tcField;
	private JTextField passwordField;
	private JComboBox<String> securityQCombo;
	private JTextField answerFiled;
	private JButton goBackButton;
	private JButton registerButton;
	private JFrame preFrame;

	public EmployeeRegisterForm(JFrame preFrame_1) {
		this.preFrame = preFrame_1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("APPLICATION FORM");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblNewLabel.setBounds(136, 10, 214, 27);
		lblNewLabel.setHorizontalAlignment(0);
		contentPane.add(lblNewLabel);

		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("employee mail:");
		lblNewLabel_1.setBounds(77, 98, 101, 13);
		contentPane.add(lblNewLabel_1);
		
		mailField = new JTextField();
		mailField.setBounds(188, 95, 245, 19);
		contentPane.add(mailField);
		mailField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("employee tcId");
		lblNewLabel_2.setBounds(77, 147, 101, 13);
		contentPane.add(lblNewLabel_2);
		
		tcField = new JTextField();
		tcField.setBounds(188, 144, 245, 19);
		contentPane.add(tcField);
		tcField.setColumns(10);
		
		((AbstractDocument) tcField.getDocument()).setDocumentFilter(new DocumentFilter() {
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
		
		JLabel lblNewLabel_3 = new JLabel("Account password:");
		lblNewLabel_3.setBounds(77, 200, 101, 13);
		contentPane.add(lblNewLabel_3);
		
		passwordField = new JTextField();
		passwordField.setBounds(188, 197, 245, 19);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		securityQCombo = new JComboBox<String>(new String[] {"What's Your favorite Food", "What's your elementry school name", "You'r favorite coding language"});
		securityQCombo.setBounds(124, 242, 245, 21);
		contentPane.add(securityQCombo);
		
		answerFiled = new JTextField();
		answerFiled.setBounds(123, 289, 246, 19);
		contentPane.add(answerFiled);
		answerFiled.setColumns(10);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setBounds(10, 17, 85, 21);
		contentPane.add(goBackButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(188, 332, 85, 21);
		contentPane.add(registerButton);
		
		goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				preFrame.setVisible(true);
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				registerEmployee();
			}
		});
	}
	
	public boolean registerEmployee() {
		String tcId = tcField.getText();
		String mail = mailField.getText();
		String password = passwordField.getText();
		String securtiyQ = String.valueOf(securityQCombo.getSelectedItem());
		String answer = answerFiled.getText();

		if (tcId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "tc id cannot be empty!");
			return false;
		}
		
		if (mail.isEmpty()) {
			JOptionPane.showMessageDialog(this, "mail cannot be empty!");
			return false;
		}
		
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "password cannot be empty!");
			return false;
		}
		
		if (answer.isEmpty()) {
			JOptionPane.showMessageDialog(this, "password cannot be empty!");
			return false;
		}
		
		String insertQ = "INSERT INTO employee_info (EmployeeTcId, EmployeeMail, EmployeePassword, EmployeeSecurityQ, EmployeeSecurityA) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertQ)) {
			preparedStatement.setString(1, tcId);
			preparedStatement.setString(2, mail);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, securtiyQ);
			preparedStatement.setString(5, answer);
			
			int rowE = preparedStatement.executeUpdate();
			if (rowE > 0) {
				JOptionPane.showMessageDialog(this, "Done sucssefuly!");
				dispose();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
