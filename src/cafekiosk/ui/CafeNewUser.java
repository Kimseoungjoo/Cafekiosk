package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cafekiosk.domain.UserDTO;
import cafekiosk.persistence.UserDAO;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class CafeNewUser extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField name;
	private JTextField nickname;
	private JTextField tel;	
	private JButton btnTelCerti;
	private JButton btnJoin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeNewUser frame = new CafeNewUser();
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
	public CafeNewUser() {
		setTitle("솔 카페"); // 창 제목
		setIconImage(Toolkit.getDefaultToolkit().getImage(CafeMain.class.getResource("/image/bubble-tea.png"))); // 창 아이콘
		
		Color bgColor = new Color(245, 245, 245); // 레이아웃 배경 색
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Color btnTelCertiColor = new Color(128, 65, 217); // 전화번호 인증 버튼 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 18); // 버튼 폰트 종류, 크기
		Font lblFont = new Font("맑은 고딕", Font.BOLD, 20); // 라벨 폰트 종류, 크기
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 회원가입, 취소 버튼
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new GridLayout(0, 2, 0, 0));

		btnJoin = new JButton("회원가입");
		// 버튼 모양
		btnJoin.setBackground(btnBgColor);
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setFont(btnFont);
		btnJoin.setEnabled(false); // 버튼 비활성화
		btnJoin.addActionListener(this);
		panelBottom.add(btnJoin);

		JButton btnCancel = new JButton("취소");
		btnCancel.setBackground(btnBgColor);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(btnFont);
		btnCancel.addActionListener(this);
		panelBottom.add(btnCancel);

		// 회원가입 정보 입력
		JPanel panelMain = new JPanel();
		panelMain.setBackground(bgColor);
		contentPane.add(panelMain, BorderLayout.CENTER);
//		panelMain.setLayout(new GridLayout(0, 2, 0, 0));
		panelMain.setLayout(null);

		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(lblFont);
		lblName.setBounds(110, 100, 100, 30); // lblName 위치 및 크기
		panelMain.add(lblName);

		name = new JTextField();
		name.setBounds(190, 100, 150, 30); // name 위치 및 크기
		panelMain.add(name);

		JLabel lblNickname = new JLabel("닉네임");
		lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
		lblNickname.setFont(lblFont);
		lblNickname.setBounds(100, 150, 100, 30); // lblNickname 위치 및 크기
		panelMain.add(lblNickname);

		nickname = new JTextField();
		nickname.setBounds(190, 150, 150, 30); // nickname 위치 및 크기
		panelMain.add(nickname);

		JLabel lblTel = new JLabel("전화번호");
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTel.setFont(lblFont);
		lblTel.setBounds(90, 200, 100, 30); // lblTel 위치 및 크기
		panelMain.add(lblTel);

		tel = new JTextField();
		tel.setBounds(190, 200, 150, 30); // tel 위치 및 크기
		panelMain.add(tel);
		
		btnTelCerti = new JButton("전화번호 인증");
		// 버튼 모양
		btnTelCerti.setHorizontalAlignment(SwingConstants.LEFT);
		btnTelCerti.setBackground(btnTelCertiColor);
		btnTelCerti.setForeground(Color.WHITE);
		btnTelCerti.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnTelCerti.addActionListener(this);
		btnTelCerti.setBounds(210, 240, 115, 25); // btnTelCerti 위치 및 크기
		panelMain.add(btnTelCerti);
//		panelTel.add(btnTelCerti);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); // 버튼 클릭시 버튼 이름 가져오기

		if (cmd.equals("회원가입")) { // 회원가입 버튼 클릭
			UserDTO dto = new UserDTO();
			UserDAO dao = new UserDAO();

			if (name.getText().equals("") || name.getText().equals(" ")) { // 이름 입력칸이 비어있을 경우
				JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "INFORMATION_MESSAGE", JOptionPane.ERROR_MESSAGE);
			} else if (nickname.getText().equals("") || nickname.getText().equals(" ")) { // 닉네임 입력칸이 비어있을 경우
				JOptionPane.showMessageDialog(null, "닉네임을 입력하세요.", "INFORMATION_MESSAGE", JOptionPane.ERROR_MESSAGE);
			} else {
				dto.setName(name.getText());
				dto.setNickname(nickname.getText());
				dto.setTel(tel.getText());

				int result = dao.checkOverTel(dto); // 가입된 전화번호 있는지 확인

				if (result == 0) { // 가입된 전화번호가 없다면
					
					// 이름 길이 확인
					if(name.getText().length() > 3) { // 이름이 3글자보다 길다면 
						JOptionPane.showMessageDialog(null, "이름이 너무 깁니다.(3글자 내로 입력해주세요.)", "INFORMATION_MESSAGE", JOptionPane.ERROR_MESSAGE);
					} else { // 이름이 3글자 이내

						boolean resultFlag = dao.insertUsert(dto);

						if (resultFlag == true) { // 회원가입 성공
							name.setText("");
							nickname.setText("");
							tel.setText("");

							/*
							 * 팝업창 띄우는 방법 참조 :
							 * https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=sks6624&
							 * logNo=150173231530
							 */
							JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "INFORMATION_MESSAGE",
									JOptionPane.INFORMATION_MESSAGE);

							CafeMain main = new CafeMain();
							main.setVisible(true);
//						setVisible(false);
							this.dispose();
						} else { // 실패
							JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "INFORMATION_MESSAGE",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} else { // 가입된 전화번호가 있다면
					JOptionPane.showMessageDialog(null, "이미 존재하는 전화번호 입니다.", "INFORMATION_MESSAGE",
							JOptionPane.ERROR_MESSAGE);
					tel.setText("");
					btnTelCerti.setText("전화번호 인증");
					btnTelCerti.setEnabled(true);
				}
			}
		} else if (cmd.equals("취소")) {
			name.setText("");
			nickname.setText("");
			tel.setText("");

			CafeMain main = new CafeMain();
			main.setVisible(true); // main창 띄우기
//			setVisible(false);
			this.dispose(); // 현재 창 닫기
		} else if (cmd.equals("전화번호 인증")) {
//			CafeTelCertified telCertified = new CafeTelCertified();
//			telCertified.setVisible(true);
			
			if(tel.getText().equals("") || tel.getText().equals(" ")) {
				JOptionPane.showMessageDialog(null, "전화번호를 입력하세요.", "INFORMATION_MESSAGE", JOptionPane.ERROR_MESSAGE);
			} else {
			
				// 다이얼로그 창 생성 참조 코드 :
				// https://earthconquest.tistory.com/102

				// 다이얼로그 객체 생성
				CafeTelCerti telCerified = new CafeTelCerti();
				telCerified.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				// 다이얼로그 창 생성
				telCerified.setModal(true);
				// 다이얼로그 창 띄우기
				telCerified.setVisible(true);

				boolean flag = telCerified.getFlag(); // 다이얼로그창(자식 클래스)에서 인증 여부 가져오기

				if (flag == true) {
					btnTelCerti.setText(" 인증 완료 "); // 전화번호 인증버튼 텍스트 변경
					btnTelCerti.setEnabled(false); // 전화번호 인증버튼 비활성화
					btnJoin.setEnabled(true); // 회원가입 버튼 활성화
				}
			}
			
		}
	}

}
