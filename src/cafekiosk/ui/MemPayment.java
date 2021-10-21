package cafekiosk.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cafekiosk.domain.OrderDTO;
import cafekiosk.domain.PointDTO;
import cafekiosk.domain.UserDTO;
import cafekiosk.persistence.CafeMenu;
import cafekiosk.persistence.OrderDAO;
import cafekiosk.persistence.PointDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MemPayment extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel model;
	OrderDAO orderDAO = new OrderDAO();
	PointDAO pointDAO = new PointDAO();
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
	UserDTO userDTO = new UserDTO();
	int plusPoint;
	PointDTO pointDTO = pointDAO.getrow();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemPayment frame = new MemPayment();
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
	public MemPayment() {
		setTitle("회원결제");
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
		panel1.setLayout(new GridLayout(5, 2, 0, 0));

		total = new JLabel("총금액");
		total.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(total);

		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("포인트 사용");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_4);

		lblNewLabel_1 = new JLabel("최종결제금액");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_1);

		lblNewLabel_6 = new JLabel("0");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_6);

		lblNewLabel_5 = new JLabel("포인트 적립");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_5);

		lblNewLabel_7 = new JLabel("0");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(lblNewLabel_7);

		lblNewLabel_2.setText(sum + "원");

		point = pointDTO.getPoint();

		lblNewLabel_4.setText(point + "원");

		lblNewLabel_6.setText(sum - point + "원");

		plusPoint = (int) ((sum - point) * 0.01);
		lblNewLabel_7.setText(plusPoint + "원");

		btnNewButton = new JButton("결제");
		btnNewButton.addActionListener(this);
		panel1.add(btnNewButton);

		btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(this);
		panel1.add(btnNewButton_1);

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

		String cmd = e.getActionCommand();

		if (cmd.equals("결제")) {

			pointDAO.plusPoint(pointDTO.getTel(), plusPoint);
			pointDAO.minusPoint(pointDTO.getTel(), point);

			orderDAO.deleteOrderTBL();
			pointDAO.deletePointTBL();
			JOptionPane.showMessageDialog(getParent(), "카드를 넣어주세요");

		} else {
			
			pointDAO.deletePointTBL();
			CafeMenu cm = new CafeMenu();
			cm.setVisible(true);
			this.setVisible(false);

		}
	}

}
