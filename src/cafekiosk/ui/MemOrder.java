package cafekiosk.ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cafekiosk.domain.CafeDTO;
import cafekiosk.domain.OrderDTO;

import java.awt.FlowLayout;
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
	PointDAO pointDAO;
	OrderDAO odao = new OrderDAO();
	private JTextField textField_2;
	private String point;
	private String tel;
	CafeMenu cm = new CafeMenu();
	private int sum;

	public int getSum() {
		return this.sum;
	}

	public void setSum(int sum) {
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemOrder frame = new MemOrder();
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
	public MemOrder() {
		setTitle("회원주문");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		name = new JLabel("손");
		panel.add(name);

		JLabel lblNewLabel_3 = new JLabel("님. 반갑습니다. 당신의 포인트는 다음과 같습니다.");
		panel.add(lblNewLabel_3);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.addActionListener(this);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("몇 포인트를 사용하시겠습니까?");
		panel.add(lblNewLabel_1);

		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.addActionListener(this);
		textField_2.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("포인트 사용 결제");
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("포인트 미사용 결제");
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("이전");
		btnNewButton_2.addActionListener(this);
		panel_1.add(btnNewButton_2);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("휴대폰번호");
		panel_2.add(lblNewLabel);

		textField = new JTextField();
		panel_2.add(textField);
		textField.addActionListener(this);
		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("포인트확인");
		btnNewButton_3.addActionListener(this);
		panel_2.add(btnNewButton_3);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				odao.deleteOrderTBL();
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

				name.setText(dto.getName());
				textField_1.setText(dto.getPoint() + "");
				setPoint(dto.getPoint() + "");

			} else {
				JOptionPane.showMessageDialog(getParent(), "등록되지 않았습니다. 회원가입해주세요");

			}

		}

		if (e.getActionCommand().equals("포인트 사용 결제")) {


			if (!textField_2.getText().equals("")
					&& Integer.parseInt(textField_1.getText()) > Integer.parseInt(textField_2.getText())) {


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
				JOptionPane.showMessageDialog(getParent(), "사용포인트가 결제금액보다 큽니다." + getSum() + "이하로 사용포인트를 설정해주세요.");
			}
		}


		if (e.getActionCommand().equals("포인트 미사용 결제"))

		{

			

			if (!textField.getText().equals("")) {

				tel = textField.getText();

				pointDAO = new PointDAO();

				pointDAO.insertPoint(tel, 0);

				MemPaymentNotPoint mpnp = new MemPaymentNotPoint();
				mpnp.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(getParent(), "휴대폰 번호를 입력해주세요");
			}

		}

		if (e.getActionCommand().equals("이전")) {

			cm.setVisible(true);
			this.setVisible(false);

			cm.showOrder();


		}

	

	}
	}
