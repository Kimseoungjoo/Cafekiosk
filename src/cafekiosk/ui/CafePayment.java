package cafekiosk.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class CafePayment extends JFrame implements ActionListener {

	private JPanel contentPane;
	private int sum;
	
	

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

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
		setTitle("솔 카페"); // 창 제목
		setIconImage(Toolkit.getDefaultToolkit().getImage(CafeMain.class.getResource("/image/bubble-tea.png"))); // 창 아이콘
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Color bgColor = new Color(245, 245, 245); // 레이아웃 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 24); // 버튼 폰트 종류, 크기
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(bgColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 5, 20));

		JButton btnNewButton_1 = new JButton("회원주문");
		btnNewButton_1.setBackground(btnBgColor);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(btnFont);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("비회원주문");
		btnNewButton.setBackground(btnBgColor);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(btnFont);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("회원주문")) {
			
			MemOrder mo = new MemOrder();
			mo.setSum(sum);
			mo.setVisible(true);
			this.setVisible(false);
		} else if (cmd.equals("비회원주문")) {
			
			UnmemPayment up = new UnmemPayment();
			up.setVisible(true);
			this.setVisible(false);
			
		}

	}

}
