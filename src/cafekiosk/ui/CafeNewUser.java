package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CafeNewUser extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField name;
	private JTextField nickname;
	private JTextField tel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.SOUTH);
		panelMain.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(this);
		panelMain.add(btnJoin);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panelMain.add(btnCancel);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panelBottom.add(lblName);
		
		name = new JTextField();
		panelBottom.add(name);
		name.setColumns(10);
		
		JLabel lblNickname = new JLabel("닉네임");
		lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
		panelBottom.add(lblNickname);
		
		nickname = new JTextField();
		panelBottom.add(nickname);
		nickname.setColumns(10);
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		panelBottom.add(lblTel);
		
		tel = new JTextField();
		panelBottom.add(tel);
		tel.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		if(cmd.equals("회원가입")) {
			
		} else if(cmd.equals("취소")) {
			name.setText("");
			nickname.setText("");
			tel.setText("");
		}
	}

}
