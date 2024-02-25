package customer.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customer.info.CustomerAccountInfo;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class AccountDetailsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public AccountDetailsFrame(String tcId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Credit Cart No:");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 47, 132, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Debit Cart No:");
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 114, 132, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IBan:");
		lblNewLabel_2.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 175, 132, 13);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField(CustomerAccountInfo.getCrediCartDb(tcId));
		textField.setEditable(false);
		textField.setBounds(152, 45, 274, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(CustomerAccountInfo.getDebitCartDb(tcId));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(152, 112, 274, 19);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField(CustomerAccountInfo.getIbanDb(tcId));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(152, 173, 274, 19);
		contentPane.add(textField_2);
		
		
		
	}
	
//	public static void main(String[] argv) {
//		AccountDetailsFrame accountDetailsFrame = new AccountDetailsFrame("14141414141");
//		accountDetailsFrame.setVisible(true);
//	}
}
