package login.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MainFrame currentInstance;

	public MainFrame() {
		currentInstance = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bank App");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 0, 215, 26);
		panel.add(lblNewLabel_1);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\osman\\eclipse-workspace\\BankManagmentSystme\\src\\images\\lacasa.png");
		
		JLabel lblNewLabel_2 = new JLabel(icon);
		lblNewLabel_2.setBounds(0, 25, 235, 340);
		panel.add(lblNewLabel_2);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("aşağıdan konumunuzu seçin");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.ITALIC, 15));
		panel_1.add(lblNewLabel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Personel");
		btnNewButton.setBackground(new Color(128, 128, 128));
		panel_1.add(btnNewButton, BorderLayout.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ALoginFrame employeeFrame = new EmployeeFrame();
				employeeFrame.setPreFrame(currentInstance);
				employeeFrame.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Yönetici");
		btnNewButton_1.setBackground(new Color(128, 128, 128));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ALoginFrame adminFrame = new AdminLogin();
				adminFrame.setPreFrame(currentInstance);
				adminFrame.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_1, BorderLayout.WEST);
		
		JButton btnNewButton_2 = new JButton("Müşteri");
		btnNewButton_2.setBackground(new Color(128, 128, 128));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ALoginFrame customerFrame = new CustomerFrame();
				customerFrame.setPreFrame(currentInstance);
				customerFrame.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_2, BorderLayout.EAST);
		contentPane.setLayout(gl_contentPane);;
	}
}
