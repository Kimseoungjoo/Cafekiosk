package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CafeMain extends JFrame implements ActionListener {

	private JPanel contentPane;

	
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
		btnNewUser.addActionListener(this);
		panelBottom.add(btnNewUser);
		
		JButton btnOrder = new JButton("주문");
		panelBottom.add(btnOrder);
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(Color.WHITE);
		contentPane.add(panelImg, BorderLayout.CENTER);
		panelImg.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JLabel lblMainImg = new JLabel(""); // 라벨 생성
		lblMainImg.setHorizontalAlignment(SwingConstants.CENTER);
		// 이미지 아이콘 생성
		ImageIcon iconImg = new ImageIcon(CafeMain.class.getResource("/cafekiosk/img/cafeKiosk-main.jpg"));
		// iconMain에서 image 추출
		Image imgTemp = iconImg.getImage();
		// 추출된 이미지의 크기를 조절하여 새로운 이미지 객체 생성
		Image mainImg = imgTemp.getScaledInstance(500, 450, Image.SCALE_SMOOTH);
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
			setVisible(false);
		} else {
			
		}
	}
}
