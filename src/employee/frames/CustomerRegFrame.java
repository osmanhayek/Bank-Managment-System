package employee.frames;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import customer.info.Bills;
import customer.info.Customer;
import validators.EmailValidatorStrict;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class CustomerRegFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame preFrame;
	private JPanel contentPane;
	private JPanel page1;
	private JPanel securityAnswerField;
	private CardLayout mainL;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField nameField;
	private JTextField fatherField;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> securityQCombo;
    private JTextField mailField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JButton page1GoBackButton;
    private JButton nextButton;
    private JLabel lblNewLabel_10;
    private JTextField tcIdField;
    private JLabel lblNewLabel_11;
    private JLabel lblNewLabel_12;
    private JRadioButton femaleButton;
    private JRadioButton maleButton;
    private JTextField securityAnswerField_1;

	public CustomerRegFrame(JFrame preFrame) {
		this.preFrame = preFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		mainL = new CardLayout(0, 0);
		setContentPane(contentPane);
		contentPane.setLayout(mainL);
		
		page1 = new JPanel(null);
		securityAnswerField = new JPanel(null);
		
		contentPane.add(page1);
		page1.setLayout(null);
		
		lblNewLabel = new JLabel("APPLICATION FORM");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblNewLabel.setBounds(152, 10, 259, 27);
		lblNewLabel.setHorizontalAlignment(0);
		
		JLabel lblNewLabePage2 = new JLabel("APPLICATION FORM");
		lblNewLabePage2.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblNewLabePage2.setBounds(152, 10, 259, 27);
		lblNewLabePage2.setHorizontalAlignment(0);

		page1.add(lblNewLabel);
		securityAnswerField.add(lblNewLabePage2);
		
		lblNewLabel_1 = new JLabel("Personal Details");
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(182, 47, 195, 27);
		lblNewLabel_1.setHorizontalAlignment(0);
		
		JLabel clonePersonalLabel = new JLabel("Additional Details");
		clonePersonalLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 15));
		clonePersonalLabel.setBounds(182, 47, 195, 27);
		clonePersonalLabel.setHorizontalAlignment(0);
		
		page1.add(lblNewLabel_1);
		securityAnswerField.add(clonePersonalLabel);
		
		lblNewLabel_2 = new JLabel("Name Surname:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(65, 116, 105, 13);
		page1.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Father's Name:");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(65, 154, 105, 13);
		page1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Gender:");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(65, 196, 105, 13);
		page1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Date Of Birth:");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(65, 231, 105, 13);
		page1.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Email Adress:");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(65, 267, 105, 13);
		page1.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Adress:");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(65, 310, 105, 13);
		page1.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("City:");
		lblNewLabel_8.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(65, 349, 105, 13);
		page1.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("State:");
		lblNewLabel_9.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(65, 395, 105, 13);
		page1.add(lblNewLabel_9);
		
		nameField = new JTextField();
		nameField.setBounds(209, 114, 259, 19);
		page1.add(nameField);
		nameField.setColumns(10);
		
		fatherField = new JTextField();
		fatherField.setBounds(209, 152, 259, 19);
		page1.add(fatherField);
		fatherField.setColumns(10);
		
		femaleButton = new JRadioButton("Female");
		femaleButton.setBounds(209, 193, 103, 21);
		page1.add(femaleButton);
		
		maleButton = new JRadioButton("Male");
		maleButton.setBounds(365, 193, 103, 21);
		page1.add(maleButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleButton);
		buttonGroup.add(femaleButton);
		
        dayComboBox = new JComboBox<>();
        dayComboBox.setSize(51, 19);
        dayComboBox.setLocation(209, 229);
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"});
        monthComboBox.setLocation(270, 229);
        monthComboBox.setSize(95, 19);
        yearComboBox = new JComboBox<>();
        yearComboBox.setLocation(375, 229);
        yearComboBox.setSize(100, 19);

        // Add items to day and year JComboBoxes
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 100; i <= currentYear; i++) {
            yearComboBox.addItem(i);
        }
		page1.add(dayComboBox);
		page1.add(monthComboBox);
		page1.add(yearComboBox);
		
		mailField = new JTextField();
		mailField.setBounds(209, 265, 259, 19);
		page1.add(mailField);
		mailField.setColumns(10);
		
		addressField = new JTextField();
		addressField.setBounds(209, 308, 259, 19);
		page1.add(addressField);
		addressField.setColumns(10);
		
		cityField = new JTextField();
		cityField.setBounds(209, 347, 259, 19);
		page1.add(cityField);
		cityField.setColumns(10);
		
		stateField = new JTextField();
		stateField.setBounds(209, 393, 259, 19);
		page1.add(stateField);
		stateField.setColumns(10);
		
		page1GoBackButton = new JButton("Go Back");
		page1GoBackButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		page1GoBackButton.setBounds(175, 445, 85, 27);
		
		page1GoBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				CustomerRegFrame.this.preFrame.setVisible(true);
			}
		});
		
		page1.add(page1GoBackButton);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(326, 445, 85, 27);
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkPage1())
					mainL.next(contentPane);
			}
		});
		page1.add(nextButton);
		
		contentPane.add(securityAnswerField);
		
		lblNewLabel_10 = new JLabel("TC ID:");
		lblNewLabel_10.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(65, 116, 105, 13);
		securityAnswerField.add(lblNewLabel_10);
		
		tcIdField = new JTextField();
		tcIdField.setBounds(209, 114, 259, 19);
		securityAnswerField.add(tcIdField);
		tcIdField.setColumns(10);
		
		((AbstractDocument) tcIdField.getDocument()).setDocumentFilter(new DocumentFilter() {
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
		
		lblNewLabel_11 = new JLabel("Security Question:");
		lblNewLabel_11.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(66, 228, 122, 13);
		securityAnswerField.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Answer:");
		lblNewLabel_12.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(65, 348, 105, 13);
		securityAnswerField.add(lblNewLabel_12);
		
		securityQCombo = new JComboBox<String>(new String[] {"What's Your favorite Food", "What's your elementry school name", "You'r favorite coding language"});
		securityQCombo.setBounds(209, 225, 259, 21);
		securityAnswerField.add(securityQCombo);
		
		JButton page2GoBackButton = new JButton("Go Back");
		page2GoBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainL.previous(contentPane);
			}
		});
		page2GoBackButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		page2GoBackButton.setBounds(209, 413, 85, 33);
		
//		page2GoBackButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				mainL.previous(contentPane);
//			}
//		});
		
		securityAnswerField.add(page2GoBackButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkPage2()) {
					if (registerUser()) {
						JOptionPane.showMessageDialog(CustomerRegFrame.this, "Registered successfully");
						dispose();
						preFrame.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(CustomerRegFrame.this, "The user is already registered!", "Warning", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				
			}
		});
		registerButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		registerButton.setBounds(326, 413, 85, 33);
		securityAnswerField.add(registerButton);
		
		securityAnswerField_1 = new JTextField();
		securityAnswerField_1.setBounds(209, 346, 259, 19);
		securityAnswerField.add(securityAnswerField_1);
		securityAnswerField_1.setColumns(10);
	}
	
	private boolean checkPage1() {
		String nameSurname;
		String fatherName;
		String eMail;
		String address;
		String city;
		String state;
		
		nameSurname = this.nameField.getText();
		
		if (nameSurname.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Name field can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		fatherName = this.fatherField.getText();
		
		if (fatherName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Father Name field can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (!this.maleButton.isSelected() && !this.femaleButton.isSelected()) {
			JOptionPane.showMessageDialog(this, "Please select your gender", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		eMail = this.mailField.getText();
		
		if (!EmailValidatorStrict.isValid(eMail)) {
			JOptionPane.showMessageDialog(this, EmailValidatorStrict.getErrorMessage(eMail), "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		address = this.addressField.getText();
		
		if (address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Address field can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		city = this.cityField.getText();
		
		if (city.isEmpty()) {
			JOptionPane.showMessageDialog(this, "city field can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		state = this.stateField.getText();
		
		if (state.isEmpty()) {
			JOptionPane.showMessageDialog(this, "state field can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean checkPage2() {
		String tcId;
		String securityA;
		
		tcId = this.tcIdField.getText();
		securityA = this.securityAnswerField_1.getText();
		
		if (tcId.isEmpty()) {
			JOptionPane.showMessageDialog(CustomerRegFrame.this, "Tc id cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (securityA.isEmpty()) {
			JOptionPane.showMessageDialog(CustomerRegFrame.this, "Security Asnwer cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean registerUser() {
    	Customer customer = new Customer.Builder(nameField.getText(), nameField.getText(), mailField.getText())
    		    .fatherName(fatherField.getText())
    		    .dateOfBirth(new Date((Integer) yearComboBox.getSelectedItem(), monthComboBox.getSelectedIndex(), (Integer)dayComboBox.getSelectedItem()))
    		    .address(addressField.getText())
    		    .city(cityField.getText())
    		    .state(stateField.getText())
    		    .gender((maleButton.isSelected()) ? true : false)
    		    .tcId(tcIdField.getText())
    		    .accountInfo((String)securityQCombo.getSelectedItem(), securityAnswerField_1.getText())
    		    .bills(new Bills(tcIdField.getText()))
    		    .build();
    	if (Customer.isIdExists(customer.getTcId()))
    		return false;
    	customer.insertToDbTable();
    	return true;
	}
	
//	public static void main(String[] arg )
//	{
//		CustomerRegFrame n = new CustomerRegFrame();
//		n.setVisible(true);
//	}
}
