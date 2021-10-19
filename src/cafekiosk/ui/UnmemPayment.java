package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cafekiosk.domain.OrderDTO;
import cafekiosk.persistence.CafeMenu;
import cafekiosk.persistence.OrderDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class UnmemPayment extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel model;
	OrderDAO orderDAO = new OrderDAO();
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
		setTitle("비회원결제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		String columnNames[] = { "번호", "이름", "가격", "수량" };

		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);

		scrollPane.setViewportView(table);

		showTable();

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new GridLayout(2, 2, 0, 0));

	

		lblNewLabel_1 = new JLabel("최종결제금액");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_1);

		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_6);

		

		btnNewButton = new JButton("결제");
		btnNewButton.addActionListener(this);
		panel1.add(btnNewButton);

		btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(this);
		panel1.add(btnNewButton_1);

	

		lblNewLabel_6.setText(sum+ "원");
		
	

	}

	public void showTable() {

		Vector<OrderDTO> list = orderDAO.payOrder();
		sum = 0;

		if (!list.isEmpty()) {

			for (OrderDTO dto : list) {

				Vector<Object> vec = new Vector<Object>();
				vec.add(dto.getNo());
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
		orderDAO.deleteOrderTBL();
		JOptionPane.showMessageDialog(getParent(), "카드를 넣어주세요");
		
		
	} else {
		
		CafeMenu cm = new CafeMenu();
		cm.setVisible(true);
		this.setVisible(false);
		
	}
		
		

	}

}
