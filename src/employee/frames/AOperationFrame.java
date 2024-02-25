package employee.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.AcceptPendingException;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public abstract class AOperationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JFrame preFrame;
	protected JButton goBackButton;
	protected JButton topLeftButton;
	protected JLabel mainLabel;
	protected JButton leftMiddleButton;
	protected JButton leftBottomButton;
	protected JButton topRigthButton;
	protected JButton middleRightButton;
	protected JButton bottomRightButton;
	

	public AOperationFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		goBackButton = new JButton("Go Back");
		goBackButton.setBackground(new Color(255, 255, 0));
		
		topLeftButton = new JButton("customer registration");
		topLeftButton.setBackground(new Color(255, 128, 0));
		topLeftButton.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		mainLabel = new JLabel("Employee Page");
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftMiddleButton = new JButton("CreditCard confermation");
		leftMiddleButton.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		leftMiddleButton.setBackground(new Color(255, 128, 0));
		
		leftBottomButton = new JButton("customer registration");
		leftBottomButton.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		leftBottomButton.setBackground(new Color(255, 128, 0));
		
		topRigthButton = new JButton("customer registration");
		topRigthButton.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		topRigthButton.setBackground(new Color(255, 128, 0));
		
		middleRightButton = new JButton("customer registration");
		middleRightButton.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		middleRightButton.setBackground(new Color(255, 128, 0));
		
		bottomRightButton = new JButton("customer registration");
		bottomRightButton.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		bottomRightButton.setBackground(new Color(255, 128, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(topLeftButton)
					.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
					.addComponent(topRigthButton, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(leftMiddleButton, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
					.addComponent(middleRightButton, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(leftBottomButton, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
					.addComponent(bottomRightButton, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(goBackButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(127)
					.addComponent(mainLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(210, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(goBackButton)
						.addComponent(mainLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(topLeftButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(topRigthButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(leftMiddleButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(middleRightButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(bottomRightButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(leftBottomButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		goBackButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				preFrame.setVisible(true);
			}
		});
	}
	
	public void setPreFrame(JFrame preFrame) { this.preFrame = preFrame; }
	
//	public static void main(String[] argv) {
//		AOperationFrame aOperationFrame = new AOperationFrame();
//		aOperationFrame.setVisible(true);
//	}
}
