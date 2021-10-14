package cafekiosk.ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import cafekiosk.domain.CafeDTO;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import cafekiosk.domain.UserDTO;
import cafekiosk.persistence.CafeDAO;

public class MemOrder extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JLabel name; 
	UserDTO dto;
	CafeDAO dao;

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("포인트확인")) {
			
			
			textField.getText();

			
			dto = new UserDTO();
			dto = dao.getPoint(textField.getText());
		
			if(dto != null) {
				
				name.setText(dto.getName());
				textField_1.setText(dto.getPoint()+"");
				
			} else {
				JOptionPane.showMessageDialog(getParent(), "등록되지 않았습니다. 회원가입해주세요");
				
			}
			
		
		

		}

	}

}
