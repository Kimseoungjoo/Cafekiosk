package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cafekiosk.persistence.CafeMenu;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CafeMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private boolean flag = false;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeMain frame = new CafeMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CafeMain() {
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 18); // 버튼 폰트 종류, 크기
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("주문");
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewUser = new JButton("회원가입");
		btnNewUser.setBackground(btnBgColor);
		btnNewUser.setForeground(Color.WHITE);
		btnNewUser.setFont(btnFont);
		btnNewUser.addActionListener(this);
		panelBottom.add(btnNewUser);
		
		JButton btnOrder = new JButton("주문");
		btnOrder.setBackground(btnBgColor);
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(btnFont);
		btnOrder.addActionListener(this);
		panelBottom.add(btnOrder);
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.WHITE);
		contentPane.add(panelImg, BorderLayout.CENTER);
		panelImg.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		/*
		 * 이미지 크기 조절 참조 링크 :
		 * https://velog.io/@yu-jin-song/JAVA-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%83%9D%EC%84%B1-%EB%B0%8F-%ED%81%AC%EA%B8%B0-%EC%A1%B0%EC%A0%88
		 */
		
		JLabel lblMainImg = new JLabel(""); // 라벨 생성
		lblMainImg.setHorizontalAlignment(SwingConstants.CENTER);
		// 이미지 아이콘 생성
		ImageIcon iconImg = new ImageIcon(CafeMain.class.getResource("/image/cafeKiosk-main.jpg"));
		// iconMain에서 image 추출
		Image imgTemp = iconImg.getImage();
		// 추출된 이미지의 크기를 조절하여 새로운 이미지 객체 생성
		Image mainImg = imgTemp.getScaledInstance(500, 440, Image.SCALE_SMOOTH);
		// 새로운 이미지 객체(크기가 조정된)로 imageIcon 객체 생성 
		ImageIcon iconMain = new ImageIcon(mainImg);
		// 라벨에 사이즈가 조절된 imageIcon 삽입
		lblMainImg.setIcon(iconMain);
		panelImg.add(lblMainImg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("회원가입")) {
			/*
			 * 화면 프레임 연결 코드 참조 :
			 * https://2dowon.netlify.app/java/swing---frame-connection/
			 */
			CafeNewUser newUser = new CafeNewUser();
			newUser.setVisible(true);
//			setVisible(false);
			this.dispose();
		} else if(cmd.equals("주문")){
			CafeMenu menu = new CafeMenu();
			menu.setVisible(true);
//			setVisible(false);
			this.dispose();
		}
	}
}
