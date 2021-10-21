package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnConfirm = new JButton("확인");
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(this);
		panelBottom.add(btnConfirm);

		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panelBottom.add(btnCancel);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnShowCertiNum = new JButton("인증번호");
		btnShowCertiNum.addActionListener(this);
		panel.add(btnShowCertiNum);

		lblCertified = new JLabel("인증번호 : ");
		panel.add(lblCertified);

		txtCkCertifiedNum = new JTextField();
		panel.add(txtCkCertifiedNum);
		txtCkCertifiedNum.setColumns(10);

		btnCkCertified = new JButton("인증 확인");
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
		boolean flag = false;

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
