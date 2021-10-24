package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class CafeTelCerti extends JDialog implements ActionListener {

//	private final JPanel contentPanel = new JPanel();
//	private JTextField txtCertiNum;

	private JPanel contentPane;
	private JTextField txtCkCertifiedNum;
	private JLabel lblCertified;
	private JButton btnCkCertified;
	private JButton btnConfirm;
	private Boolean flag; // 부모 클래스와 공유할 데이터

	// 인증번호를 위한 변수
	private int certCharLength = 8;
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
											'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
											'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	private String certifiedNum;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			CafeTelCerti dialog = new CafeTelCerti();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public CafeTelCerti() {
		setTitle("솔 카페"); // 창 제목
		setIconImage(Toolkit.getDefaultToolkit().getImage(CafeMain.class.getResource("/image/bubble-tea.png"))); // 창 아이콘
		
		Color bgColor = new Color(245, 245, 245); // 레이아웃 배경 색
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 18); // 버튼 폰트 종류, 크기
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(bgColor);
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnConfirm = new JButton("확인");
		btnConfirm.setBackground(btnBgColor); // 버튼 배경색
		btnConfirm.setForeground(Color.WHITE); // 버튼 글씨
		btnConfirm.setFont(btnFont); // 글씨 폰트 종류, 크기
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(this);
		panelBottom.add(btnConfirm);

		JButton btnCancel = new JButton("취소");
		btnCancel.setBackground(btnBgColor);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(btnFont);
		btnCancel.addActionListener(this);
		panelBottom.add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBackground(bgColor);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		/*
		 * 화면에서 임의로 위치 조정 참조 코드 : 
		 * https://m.blog.naver.com/javaking75/140157948347 
		 */
		
		JButton btnShowCertiNum = new JButton("인증번호");
		btnShowCertiNum.setBounds(80, 60, 100, 30); // 화면에서의 위치
		btnShowCertiNum.setBackground(new Color(191, 160, 237)); // 버튼 배경색
		btnShowCertiNum.setForeground(Color.WHITE); // 글자색
		btnShowCertiNum.setFont(new Font("맑은 고딕", Font.BOLD, 14)); // 글자 폰트 종류, 크기
		btnShowCertiNum.addActionListener(this);
		panel.add(btnShowCertiNum);

		lblCertified = new JLabel("인증번호 : ");
		lblCertified.setBounds(190, 60, 200, 30); // 회면에서의 위치
		lblCertified.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(lblCertified);

		txtCkCertifiedNum = new JTextField();
		txtCkCertifiedNum.setBounds(80, 100, 150, 30); // 화면에서의 위치
		panel.add(txtCkCertifiedNum);
		txtCkCertifiedNum.setColumns(10);

		btnCkCertified = new JButton("인증 확인");
		btnCkCertified.setBounds(240, 100, 100, 30); // 화면에서의 위치
		btnCkCertified.setBackground(new Color(191, 160, 237));
		btnCkCertified.setForeground(Color.WHITE);
		btnCkCertified.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnCkCertified.setEnabled(false);
		btnCkCertified.addActionListener(this);
		panel.add(btnCkCertified);
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("인증번호")) {

			certifiedNum = excuteGenerate();
			lblCertified.setText("인증번호 : " + certifiedNum);
			btnCkCertified.setEnabled(true);

		} else if (cmd.equals("인증 확인")) {

			if (txtCkCertifiedNum.getText().equals(certifiedNum)) {
				btnCkCertified.setText("인증 완료");
				btnCkCertified.setEnabled(false);
				btnConfirm.setEnabled(true);

				JOptionPane.showMessageDialog(null, "인증에 성공했습니다.", "INFORMATION_MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "인증번호가 일치하지 않습니다.", "INFORMATION_MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (cmd.equals("확인")) {
			CafeTelCerti.this.setFlag(true);
			CafeTelCerti.this.dispose();
		}

		if (cmd.equals("취소")) {
			CafeTelCerti.this.setFlag(false);
			CafeTelCerti.this.dispose();
		}

	}

	// 랜덤 인정번호 문자+숫자 참조코드 :
	// https://ktko.tistory.com/entry/%EC%9E%90%EB%B0%94-%EB%9E%9C%EB%8D%A4%ED%95%A8%EC%88%98%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9D%B8%EC%A6%9D%EB%B2%88%ED%98%B8-%EC%9D%B8%EC%A6%9D%EB%AC%B8%EC%9E%90-%EB%A7%8C%EB%93%A4%EA%B8%B0
	public String excuteGenerate() {
		Random random = new Random(System.currentTimeMillis());
		int tablelength = characterTable.length;
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < certCharLength; i++) {
			buf.append(characterTable[random.nextInt(tablelength)]);
		}

		return buf.toString();
	}
	
	// 부모 클래스와 데이터를 공유하기 위한 메소드
	public int getCertCharLength() { return certCharLength; }
	public void setCertCharLength(int certCharLength) { this.certCharLength = certCharLength; }
}
