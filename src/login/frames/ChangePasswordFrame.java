package login.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ChangePasswordFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField mailField;
	public JTextField answerLabel;
	public JPasswordField newPasswordField;
	public JPasswordField confirmPasswordField;
	public JButton changePasswordButton;
	public JButton page3GoBackButton;
	public JButton page2GoBackButton;
	public JButton page2NextButton;
	public JLabel securityQuestionLabel;
	public JButton page1NextButton;
	public CardLayout mainL;
	public JLabel confirmPasswordLabel;
	public JLabel newPasswordLabel;

	public ChangePasswordFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		mainL = new CardLayout(0, 0);
		contentPane.setLayout(mainL);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "page1");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Password Section");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(0);
		lblNewLabel.setBounds(10, 10, 356, 34);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setHorizontalAlignment(0);
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 118, 97, 13);
		panel.add(lblNewLabel_1);
		
		mailField = new JTextField();
		mailField.setBounds(145, 116, 203, 19);
		panel.add(mailField);
		mailField.setColumns(10);
		
		page1NextButton = new JButton("Next");
//		page1NextButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				mainL.next(contentPane);
//			}
//		});
		page1NextButton.setBounds(145, 193, 85, 21);
		panel.add(page1NextButton);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "page2");
		panel_1.setLayout(null);
		
		JLabel lblNewLabelPage2 = new JLabel("Change Password Section");
		lblNewLabelPage2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabelPage2.setHorizontalAlignment(0);
		lblNewLabelPage2.setBounds(10, 10, 356, 34);
		panel_1.add(lblNewLabelPage2);
		
		securityQuestionLabel = new JLabel("");
		securityQuestionLabel.setHorizontalAlignment(0);
		securityQuestionLabel.setBounds(10, 54, 356, 50);
		panel_1.add(securityQuestionLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Please anwer your security question");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(0);
		lblNewLabel_3.setBounds(10, 125, 356, 13);
		panel_1.add(lblNewLabel_3);
		
		answerLabel = new JTextField();
		answerLabel.setBounds(20, 161, 346, 37);
		panel_1.add(answerLabel);
		answerLabel.setColumns(10);
		
		page2NextButton = new JButton("Next");
		page2NextButton.setBounds(216, 208, 85, 35);
		panel_1.add(page2NextButton);
		
		page2GoBackButton = new JButton("Go Back");
		page2GoBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainL.previous(contentPane);
			}
		});
		page2GoBackButton.setBounds(91, 208, 85, 35);
		panel_1.add(page2GoBackButton);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "page3");
		panel_2.setLayout(null);
		
		JLabel lblNewLabelPage3 = new JLabel("Change Password Section");
		lblNewLabelPage3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabelPage3.setHorizontalAlignment(0);
		lblNewLabelPage3.setBounds(78, 5, 220, 22);
		panel_2.add(lblNewLabelPage3);
		
		newPasswordLabel = new JLabel("New Password:");
		newPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		newPasswordLabel.setBounds(37, 121, 100, 13);
		panel_2.add(newPasswordLabel);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(173, 118, 178, 19);
		panel_2.add(newPasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(173, 147, 178, 19);
		panel_2.add(confirmPasswordField);
		
		confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		confirmPasswordLabel.setBounds(37, 150, 100, 13);
		panel_2.add(confirmPasswordLabel);
		
		page3GoBackButton = new JButton("Go Back");
		page3GoBackButton.setBounds(50, 207, 125, 36);
		panel_2.add(page3GoBackButton);
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.setBounds(213, 207, 138, 36);
		panel_2.add(changePasswordButton);
	}
	
//	public static void main(String[] argv) {
//		ChangePasswordFrame a = new ChangePasswordFrame();
//		a.setVisible(true);
//	}
}
