package login.frames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public abstract class ALoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPanel;
	protected JTextField idField;
	protected JPasswordField passwordField;
	protected JLabel lblNewLabel_1;
	protected JLabel passwordLabel;
	protected JPanel leftPanel;
	protected JPanel rightPanel;
	protected GroupLayout gl_contentPanel;
	protected JFrame preFrame;
	protected JButton goBackButton;
	protected JButton loginButton;
	protected ImageIcon icon;
	protected JLabel changePasswordLabel;
	private JLabel imageLabel;

	public ALoginFrame() {
		icon = new ImageIcon("C:\\Users\\osman\\eclipse-workspace\\BankManagmentSystme\\src\\images\\bankImage.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPanel);
		
		this.leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 128, 0));

		this.rightPanel = new JPanel();
		this.gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(rightPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
						.addComponent(leftPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
					.addContainerGap())
		);
		leftPanel.setLayout(null);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setBackground(new Color(255, 255, 0));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				preFrame.setVisible(true);
			}
		});
		goBackButton.setBounds(0, 0, 85, 38);
		leftPanel.add(goBackButton);
		
		imageLabel = new JLabel(icon);
		imageLabel.setBounds(0, 53, 187, 293);
		leftPanel.add(imageLabel);
		rightPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LoginPage");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(61, 10, 138, 34);
		rightPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Id:");
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 75, 55, 13);
		rightPanel.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(20, 93, 233, 34);
		rightPanel.add(idField);
		idField.setColumns(10);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 15));
		passwordLabel.setBounds(10, 156, 84, 13);
		rightPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 179, 233, 34);
		rightPanel.add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loginButton.setBounds(90, 243, 85, 46);
		rightPanel.add(loginButton);
	
		changePasswordLabel = new JLabel("Forgot or Change your password?");
		changePasswordLabel.setForeground(new Color(255, 0, 0));
		changePasswordLabel.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		changePasswordLabel.setBounds(52, 299, 176, 13);
		changePasswordLabel.addMouseMotionListener(new MouseAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		        final int x = e.getX();
		        final int y = e.getY();

		        final Rectangle cellBounds = changePasswordLabel.getBounds();
		        if (cellBounds != null && cellBounds.contains(x, y)) {
		            changePasswordLabel.setCursor(Cursor.getDefaultCursor());
		        } else {
		            changePasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        }
		    }
		});
        changePasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               System.out.println("Hello World");
            }
        });
		rightPanel.add(changePasswordLabel);
		
		JCheckBox showPasswordBox = new JCheckBox("show password");
		showPasswordBox.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
		showPasswordBox.setBounds(147, 219, 110, 21);
		showPasswordBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				passwordField.setEchoChar(showPasswordBox.isSelected() ? 0 : '•');
				
			}
		});
		rightPanel.add(showPasswordBox);
		contentPanel.setLayout(gl_contentPanel);
		
	}
	
	public void setPreFrame(JFrame preFrame) { this.preFrame = preFrame; }
	
}