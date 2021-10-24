package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cafekiosk.domain.OrderDTO;
import cafekiosk.persistence.CafeDAO;
import cafekiosk.persistence.CafeMenu;
import cafekiosk.persistence.OrderDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class UnmemPayment extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel model;
	OrderDAO orderDAO = new OrderDAO();
	CafeDAO dao = new CafeDAO();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel total;
	int sum;
	int point;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnmemPayment frame = new UnmemPayment();
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
	public UnmemPayment() {
		setTitle("솔 카페"); // 창 제목
		setIconImage(Toolkit.getDefaultToolkit().getImage(CafeMain.class.getResource("/image/bubble-tea.png"))); // 창 아이콘
		Color bgColor = new Color(245, 245, 245); // 레이아웃 배경 색
		Color btnBgColor = new Color(191, 160, 237); // 버튼 배경 색
		Font btnFont = new Font("맑은 고딕", Font.BOLD, 16); // 버튼 폰트 종류, 크기
		Font lblFont = new Font("맑은 고딕", Font.BOLD, 14); // 라벨 폰트 종류, 크기

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(bgColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("결제 확인");
		lblNewLabel.setFont(lblFont);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		String columnNames[] = { "이름", "가격", "수량" };

		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);

		scrollPane.setViewportView(table);

		showTable();

		JPanel panel1 = new JPanel();
		panel1.setBackground(bgColor);
		contentPane.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new GridLayout(2, 2, 0, 0));

	

		lblNewLabel_1 = new JLabel("최종결제금액");
		lblNewLabel_1.setFont(lblFont);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_1);

		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setFont(lblFont);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_6);

		

		btnNewButton = new JButton("결제");
		btnNewButton.setBackground(btnBgColor);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(btnFont);
		btnNewButton.addActionListener(this);
		panel1.add(btnNewButton);

		btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setBackground(btnBgColor);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(btnFont);
		btnNewButton_1.addActionListener(this);
		panel1.add(btnNewButton_1);

	

		lblNewLabel_6.setText(sum+ "원");
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	           orderDAO.deleteOrderTBL();
	           System.exit(0);
	        }
	    });
	

	}

	public void showTable() {

		Vector<OrderDTO> list = orderDAO.payOrder();
		sum = 0;

		if (!list.isEmpty()) {

			for (OrderDTO dto : list) {

				Vector<Object> vec = new Vector<Object>();
			
				vec.add(dto.getName());
				vec.add(dto.getPrice());
				vec.add(dto.getCount());

				model.addRow(vec);
				sum += dto.getPrice();
			}

		}

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	String cmd= e.getActionCommand();
	
	if(cmd.equals("결제")) {
		if (!orderDAO.payOrder().isEmpty()) {

			for (OrderDTO dto : orderDAO.payOrder()) {
				dao.inOrderList(dto);					
			}
		}
		orderDAO.deleteOrderTBL();
	
		Object[] options = { "OK" };
		int n = JOptionPane.showOptionDialog(getParent(), "카드를 넣어주세요", "결제창", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (n == 0) {
			CafeMain cm = new CafeMain();
			cm.setVisible(true);
			setVisible(false);
		}
		
		
	} else {
		
		
		CafeMenu cm = new CafeMenu();
		cm.setVisible(true);
		this.setVisible(false);
		cm.showOrder();
		orderDAO.deleteOrderTBL();
		
	}
		
		

	}

}
