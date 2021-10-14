package cafekiosk.persistence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class CafeMenuCoffee extends JFrame {


	private JPanel contentPane;
	private JButton btnCoffee,btnAde,btnTea,btnDessert,btnEtc;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JButton btnNewButton_8;
	private JButton btnNewButton_9;
	private JButton btnNewButton_10;
	private JButton btnNewButton_11;
	private JButton btnNewButton_12;
	private JButton btnNewButton_13;
	private JButton btnNewButton_14;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeMenu frame = new CafeMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CafeMenuCoffee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
		contentPane.add(panel, BorderLayout.NORTH);
		
		 btnCoffee = new JButton("\uCEE4\uD53C");
		panel.add(btnCoffee);
		
		btnAde = new JButton("\uC5D0\uC774\uB4DC");
		panel.add(btnAde);
		
		 btnTea = new JButton("\uD2F0");
		panel.add(btnTea);
		
		 btnDessert = new JButton("\uB514\uC800\uD2B8");
		panel.add(btnDessert);
		
		 btnEtc = new JButton("\uAE30\uD0C0");
		panel.add(btnEtc);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBounds(0, 0, 200, 350);
		panel_1.setLayout(null);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(0, 0, 158, 113);
//		btnNewButton.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapeade.png")));
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(158, 0, 158, 113);
//		btnNewButton_1.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapefruitade.png")));
		panel_1.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(316, 0, 158, 113);
//		btnNewButton_2.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/lemon.png")));
		panel_1.add(btnNewButton_2);
		

		setSize(500,300);
		
	}
}
