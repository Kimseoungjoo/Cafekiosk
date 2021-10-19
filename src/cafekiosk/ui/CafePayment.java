package cafekiosk.ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class CafePayment extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	static CafePayment frame = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CafePayment();
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
	public CafePayment() {
		setTitle("음료주문");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnNewButton_1 = new JButton("회원주문");
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 24));
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("비회원주문");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 24));
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("회원주문")) {
			
			MemOrder mo = new MemOrder();
			mo.setVisible(true);
			frame.setVisible(false);
		} else if (cmd.equals("비회원주문")) {
			UnmemPayment up = new UnmemPayment();
			up.setVisible(true);
			frame.setVisible(false);
			
		}

	}

}
