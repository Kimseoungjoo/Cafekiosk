package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cafekiosk.domain.CafeDTO;
import cafekiosk.domain.OrderDTO;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;

import cafekiosk.domain.UserDTO;
import cafekiosk.persistence.CafeDAO;
import cafekiosk.persistence.CafeMenu;
import cafekiosk.persistence.OrderDAO;
import cafekiosk.persistence.PointDAO;
import lombok.Getter;
import lombok.Setter;

public class MemOrder extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JLabel name;
	UserDTO dto;
	CafeDAO dao;
	PointDAO pointDAO = new PointDAO();
	OrderDAO odao = new OrderDAO();
	private JTextField textField_2;
	private String point;
	private String tel;

	private int sum;

	public int getSum() {
		
		return this.sum;
	}

	public void setSum(int sum) {
		System.out.println("MemOrder :   "+sum);
		this.sum = sum;	
	}
	

	public MemOrder(int sum) {
		this();
		this.sum=sum;
		
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MemOrder frame = new MemOrder();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MemOrder() {
		setTitle("솔 카페"); // 창 제목
		setIconImage(Toolkit.getDefaultToolkit().getImage(CafeMain.class.getResource("/image/bubble-tea.png"))); // 창 아이콘
		Color bgColor = new Color(245, 245, 245); // 레이아웃 배경 색
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Color btnCkPoint = new Color(128, 65, 217); // 포인트 확인 버튼 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 16); // 버튼 폰트 종류, 크기
		Font lblFont = new Font("맑은 고딕", Font.BOLD, 14); // 라벨 폰트 종류, 크기
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
//		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setLayout(null);
		panel.setBackground(bgColor);

		name = new JLabel("손님. 반갑습니다.");
		name.setFont(lblFont);
		name.setBounds(145, 70, 480, 25);
		panel.add(name);

//		JLabel lblNewLabel_3 = new JLabel("반갑습니다. 당신의 포인트는 다음과 같습니다.");
//		lblNewLabel_3.setFont(lblFont);
//		lblNewLabel_3.setBounds(100, 80, 400, 30);
//		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3 = new JLabel("당신의 포인트는 다음과 같습니다.");
		lblNewLabel_3.setFont(lblFont);
		lblNewLabel_3.setBounds(70, 100, 250, 25);
		panel.add(lblNewLabel_3);

		textField_1 = new JTextField();
		textField_1.setBounds(300, 100, 50, 25);
		textField_1.addActionListener(this);
		panel.add(textField_1);
//		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("몇 포인트를 사용하시겠습니까?");
		lblNewLabel_1.setFont(lblFont);
		lblNewLabel_1.setBounds(50, 130, 300, 25);
		panel.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(260, 130, 120, 25);
		textField_2.addActionListener(this);
		panel.add(textField_2);
//		textField_2.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setBackground(bgColor);

		JButton btnNewButton = new JButton("포인트 사용 결제");
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(btnBgColor);
		btnNewButton.setFont(btnFont);
		btnNewButton.setForeground(Color.WHITE);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("포인트 미사용 결제");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBackground(btnBgColor);
		btnNewButton_1.setFont(btnFont);
		btnNewButton_1.setForeground(Color.WHITE);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("이전");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBackground(btnBgColor);
		btnNewButton_2.setFont(btnFont);
		btnNewButton_2.setForeground(Color.WHITE);
		panel_1.add(btnNewButton_2);

//		JPanel panel_2 = new JPanel();
//		contentPane.add(panel_2, BorderLayout.NORTH);
//		panel_2.setBackground(bgColor);

		JLabel lblNewLabel = new JLabel("휴대폰번호");
		lblNewLabel.setFont(lblFont);
		lblNewLabel.setBounds(60, 40, 80, 25);
//		panel_2.add(lblNewLabel);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(140, 40, 100, 25);
//		panel_2.add(textField);
		panel.add(textField);
		textField.addActionListener(this);
//		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("포인트확인");
		btnNewButton_3.setBackground(btnCkPoint);
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBounds(250, 40, 100, 25);
		btnNewButton_3.addActionListener(this);
//		panel_2.add(btnNewButton_3);
		panel.add(btnNewButton_3);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				odao.deleteOrderTBL();
				pointDAO.deletePointTBL();
				System.exit(0);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("포인트확인")) {

			textField.getText();

			dto = new UserDTO();
			dto = dao.getPoint(textField.getText());

			if (dto != null) {

				name.setText(dto.getName()+"님. 반갑습니다.");
				textField_1.setText(dto.getPoint() + "");
				setPoint(dto.getPoint() + "");

			} else {
				JOptionPane.showMessageDialog(getParent(), "등록되지 않았습니다. 회원가입해주세요");

			}

		}

		if (e.getActionCommand().equals("포인트 사용 결제")) {

			if (!textField_2.getText().equals("")
					&& Integer.parseInt(textField_1.getText()) >= Integer.parseInt(textField_2.getText())
					&& getSum() >= Integer.parseInt(textField_2.getText())) {

				try {


					tel = textField.getText();
					point = textField_2.getText();

					pointDAO = new PointDAO();

					pointDAO.insertPoint(tel, Integer.parseInt(point));

					MemPayment mp = new MemPayment();

//					mp.setTel(tel);
//					mp.setUsePoint(point);


					mp.setVisible(true);
					this.setVisible(false);

				} 


				catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				}

			else if (textField.getText().equals("")) {
				JOptionPane.showMessageDialog(getParent(), "휴대폰 번호를 입력해주세요.");
			} else if (textField_2.getText().equals("")) {
				JOptionPane.showMessageDialog(getParent(), "포인트를 입력해주세요.");
			} else if (Integer.parseInt(textField_1.getText()) < Integer.parseInt(textField_2.getText())) {
				JOptionPane.showMessageDialog(getParent(), "사용포인트가 보유포인트보다 큽니다. 사용포인트를 다시 입력해주세요.");
			} else if (getSum() < Integer.parseInt(textField_2.getText())) {
				JOptionPane.showMessageDialog(getParent(), "사용포인트가 결제금액보다 큽니다." + getSum()+ "이하로 사용포인트를 설정해주세요.");
			}
		}


		if (e.getActionCommand().equals("포인트 미사용 결제"))

		{

			

			if (!textField.getText().equals("")) {

				tel = textField.getText();

				

				pointDAO.insertPoint(tel, 0);

				MemPaymentNotPoint mpnp = new MemPaymentNotPoint();
				mpnp.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(getParent(), "휴대폰 번호를 입력해주세요");
			}

		}

		if (e.getActionCommand().equals("이전")) {
			
			CafeMenu cm = new CafeMenu();
			cm.setVisible(true);
			this.setVisible(false);

			cm.showOrder();
			odao.deleteOrderTBL();
			

		}

	

	}
	}
