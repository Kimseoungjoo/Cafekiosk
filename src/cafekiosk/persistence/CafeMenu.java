package cafekiosk.persistence;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cafekiosk.domain.CafeDTO;
import cafekiosk.domain.OrderDTO;
import cafekiosk.ui.CafeMain;
import cafekiosk.ui.CafePayment;
import cafekiosk.ui.MemOrder;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CafeMenu extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCoffee, btnAde, btnTea, btnDessert, btnEtc;
	private JScrollPane scrollMenu;
	private JPanel panel_1, panel_2, panel_3, panel_4;
	private JButton btnGrapeAde, btnGrapefruitsAde, btnLemonAde, btnamericano, btncafelatte, btnespresso, btnEgr,
			btnPam, btnRoob;
	private JTable table = new JTable();
	private String orderList[] = { "음료", "가격", "수량" };
	private DefaultTableModel model;
	private JPanel panel_5;
	private JButton btnNewButton, btnNewButton_1;
	private int count = 0, menuNum = 0; // count 배열로 ade 두번 넣지 않게하기
	private JLabel lblcount = new JLabel((count + 1) + "");

	private Vector<OrderDTO> vetList = new Vector<OrderDTO>(); // 결제 버튼 시 모든 주문 리스트
	private Vector<CafeDTO> vetMenu; // 음료 버튼 시 담는 변수
	private CafeDAO dao = new CafeDAO();

	private CafeDTO dto;
	private OrderDTO ordto;
	private int sum;
	private boolean flag, selFlag;
	private JButton btnSelect;
	private JButton btnDelete;
	private JLabel lblAllpay;
	private JPanel panel_6;
	private JButton btnTurn, btnPayment;
	OrderDAO orderDAO = new OrderDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CafeMenu frame = new CafeMenu();
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
	public CafeMenu() {
		setTitle("메뉴창~ ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel(); // 메뉴창 틀(패널)
		panel.setBackground(Color.YELLOW);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));

		btnCoffee = new JButton("커피"); // 커피 메뉴
		btnCoffee.setBackground(Color.ORANGE);
		btnCoffee.setActionCommand("COFFEE");
		btnCoffee.addActionListener(this);
		panel.add(btnCoffee);// 메뉴창 패널에 추가

		btnAde = new JButton("에이드"); // 에이드 메뉴
		btnAde.setBackground(Color.ORANGE);
		btnAde.setActionCommand("ADE");
		btnAde.addActionListener(this);
		panel.add(btnAde);

		btnTea = new JButton("티"); // 티 메뉴
		btnTea.setBackground(Color.ORANGE);
		btnTea.setActionCommand("TEA");
		btnTea.addActionListener(this);
		panel.add(btnTea);

		btnDessert = new JButton("디저트"); // 디저트 메뉴
		btnDessert.setBackground(Color.ORANGE);
		btnDessert.setActionCommand("DESSERT");
		btnDessert.addActionListener(this);
		panel.add(btnDessert);

		btnEtc = new JButton("기타"); // 기타 메뉴
		btnEtc.setBackground(Color.ORANGE);
		btnEtc.setActionCommand("ETC");
		btnEtc.addActionListener(this);
		panel.add(btnEtc);

		scrollMenu = new JScrollPane();// 메뉴 창 띄우는 패널
		contentPane.add(scrollMenu);

		scrollMenu.setViewportView(coffeeMenuPanel());// 처음 메뉴 창 띄우는 코드

		// 주문리스트(panel) / 수량(panel) / layout(gridbag)
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(null, "주문리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_4, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 232, 0, 232, 0 };
		gbl_panel_4.rowHeights = new int[] { 428, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		// table 안에 내용
		model = new DefaultTableModel(orderList, 0);

		JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
		gbc_scrollPane1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane1.gridx = 0;
		gbc_scrollPane1.gridy = 0;
		panel_4.add(scrollPane1, gbc_scrollPane1);
		table = new JTable(model);
		table.setBackground(Color.yellow);
//            table.setBackground(Color.WHITE);
		table.setBorder(null);
		scrollPane1.setViewportView(table);
		panel_5 = new JPanel();
		panel_5.setBackground(Color.YELLOW);

		panel_5.setBorder(new TitledBorder(null, "수량", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 2;
		gbc_panel_5.gridy = 0;
		panel_4.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 수량 패널에 버튼 추가
		panel_5.add(disCountButton());
		panel_5.add(lblcount);
		panel_5.add(plusCountButton());

		// 주문리스트 밑에 라벨
		lblAllpay = new JLabel("총 금액");
		GridBagConstraints gbc_lblAllpay = new GridBagConstraints();
		gbc_lblAllpay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllpay.gridx = 0;
		gbc_lblAllpay.gridy = 1;
		panel_4.add(lblAllpay, gbc_lblAllpay);

		panel_6 = new JPanel();
		panel_6.setBackground(Color.YELLOW);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 2;
		gbc_panel_6.gridy = 1;
		panel_4.add(panel_6, gbc_panel_6);

		btnTurn = new JButton("이전");
		panel_6.add(btnTurn);
		btnTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CafeMain main = new CafeMain();
				main.setVisible(true);
				setVisible(false);
			}
		});

		// 삭제 EVENT 발생시 DB(orderTBL) / 주문리스트(panel) 테이블 데이터 삭제
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				sum -= Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				model.removeRow(table.getSelectedRow());
//				menuNum -= 1;
//				lblAllpay.setText("총 금액 :" + sum);
				totalSum();
			}
		});
		// 결제하기 버튼 추가 / 이벤트(CafePayMent 클래스로)
		btnPayment = new JButton("결제하기");
		panel_6.add(btnPayment);

		// 결제 창으로
		btnPayment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vetList = new Vector<OrderDTO>();

//                  DB(orderTBL) 변수 넣기 
				for (int i = 0; i < table.getRowCount(); i++) {
					ordto = new OrderDTO(); // DB(orderTBL)변수
					ordto.setNo(i);
					ordto.setName((String) table.getValueAt(i, 0));
					ordto.setPrice(Integer.parseInt((String) table.getValueAt(i, 1)));
					ordto.setCount(Integer.parseInt(table.getValueAt(i, 2).toString()));

					// db insert
					dao.insertList(ordto);

					// 결제
					vetList.add(ordto);

				}

				CafePayment payment = new CafePayment();
				payment.setSum(sum);
				payment.setVisible(true);
				setVisible(false);
			}
		});

		panel_5.add(btnDelete);
		setSize(500, 700);
	}

	// 메뉴 버튼 이벤트시 메뉴창 띄우는 메소드 불러오기
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("COFFEE")) {
			scrollMenu.setViewportView(coffeeMenuPanel());
		} else if (cmd.equals("ADE")) {
			scrollMenu.setViewportView(adeMenuPanel());
		} else if (cmd.equals("TEA")) {
			scrollMenu.setViewportView(TeaMenuPanel());
		} else if (cmd.equals("자몽에이드")) {
			count += 1;
			dao = new CafeDAO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);
			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);
		} else if (cmd.equals("레몬에이드")) {
			count += 1;
			dao = new CafeDAO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);

			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		} else if (cmd.equals("아메리카노")) {
			count += 1;
			dao = new CafeDAO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);
			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);
		} else if (cmd.equals("청포도에이드")) {
			count += 1; // 이떄의 count는 수량 1개!
//           dao = new CafeDAO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);
			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" }; // model.addRow(vetMenu)를
																									// 해줘야하지만 테이블에 보여주는
																									// 데이터가 다르기 떄문에
			// String list[] 에 필요 데이터만 받아서 model.addRow작업
			model.addRow(list);
		} else if (cmd.equals("카페라떼")) {
			dao = new CafeDAO();
			dto = new CafeDTO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);
			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		} else if (cmd.equals("에스프레소")) {
			dao = new CafeDAO();
			dto = new CafeDTO();
			vetMenu = new Vector<CafeDTO>();

			vetMenu = dao.getList(cmd);

			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		} else if (cmd.equals("얼그레이")) {
			dao = new CafeDAO();

			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);

			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		} else if (cmd.equals("페퍼민트")) {
			dao = new CafeDAO();
			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);

			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		} else if (cmd.equals("루이보스")) {
			dao = new CafeDAO();

			vetMenu = new Vector<CafeDTO>();
			vetMenu = dao.getList(cmd);

			String list[] = { vetMenu.get(0).getName(), vetMenu.get(0).getPrice() + "", 1 + "" };
			model.addRow(list);

		}
		totalSum();

		panel_5.revalidate();
	}

	// 메뉴 창 이미지 판넬 메소드
	public JPanel adeMenuPanel() {// PANEL이라고 보시면됩니다
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		panel_1.setBounds(0, 0, 200, 350);
		btnGrapeAde = new JButton("");
		btnGrapeAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapeade.png")));
		panel_1.add(btnGrapeAde);
		btnGrapeAde.setActionCommand("청포도에이드");
		btnGrapeAde.addActionListener(this);

		btnGrapefruitsAde = new JButton("");
		btnGrapefruitsAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapefruitade.png")));
		panel_1.add(btnGrapefruitsAde);
		btnGrapefruitsAde.setActionCommand("자몽에이드");
		btnGrapefruitsAde.addActionListener(this);

		btnLemonAde = new JButton("");
		btnLemonAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/lemon.png")));
		panel_1.add(btnLemonAde);
		btnLemonAde.setActionCommand("레몬에이드");
		btnLemonAde.addActionListener(this);

		return panel_1;
	}

	// 수량 줄이는 메소드
	public JButton disCountButton() {
		JButton btn = new JButton("-");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow = table.getSelectedRow();

				if (selectRow == -1) {
					selectRow = 0;
					lblcount.setText(Integer.parseInt(lblcount.getText())-1+"");
				}

				int num = Integer.parseInt(table.getValueAt(selectRow, 2).toString());

				num -= 1;
				table.setValueAt(num, selectRow, 2);
				lblcount.setText(Integer.parseInt(table.getValueAt(selectRow, 2).toString())-1+"");
				totalSum();
			}
		});
		return btn;
	}

// 수량 늘리는 메소드
	public JButton plusCountButton() {
		JButton btn = new JButton("+");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				// 현재 선택된 상품의 수량 가져오기
				int selectRow = table.getSelectedRow();

				if (selectRow == -1) {
					lblcount.setText(Integer.parseInt(lblcount.getText())+1+"");
					selectRow = 0;
				}

				lblcount.setText(Integer.parseInt(table.getValueAt(selectRow, 2).toString())+1+"");
				int num = Integer.parseInt(table.getValueAt(selectRow, 2).toString());

				num += 1;

				table.setValueAt(num, selectRow, 2);
				totalSum();
			}
		});

		return btn;
	}

// 카페 패널 작성 / 버튼EVENT(DB(menuTBL)가져오기) / 테이블 추가 
	public JPanel coffeeMenuPanel() {
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		panel_2.setBounds(0, 0, 200, 350);
		btnamericano = new JButton("");
		btnamericano.setBackground(new Color(255, 255, 224));
		btnamericano.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/americano.png")));
		panel_2.add(btnamericano);

		btnamericano.setActionCommand("아메리카노");
		btnamericano.addActionListener(this);

		btncafelatte = new JButton("");
		btncafelatte.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/cafelatte.png")));
		panel_2.add(btncafelatte);
		btncafelatte.setActionCommand("카페라떼");
		btncafelatte.addActionListener(this);

		btnespresso = new JButton("");
		btnespresso.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/espresso.png")));
		panel_2.add(btnespresso);
		btnespresso.setActionCommand("에스프레소");
		btnespresso.addActionListener(this);

		return panel_2;
	}

	public JPanel TeaMenuPanel() {
		panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		panel_3.setBounds(0, 0, 200, 350);
		btnEgr = new JButton("");
		btnEgr.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/earlgray.jpg")));
		btnEgr.setActionCommand("얼그레이");
		btnEgr.addActionListener(this);
		panel_3.add(btnEgr);

		btnPam = new JButton("");
		btnPam.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/Paper.jpg")));
		btnPam.setActionCommand("페퍼민트");
		btnPam.addActionListener(this);
		panel_3.add(btnPam);

		btnRoob = new JButton("");
		btnRoob.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/roibos.jpg")));
		btnRoob.setActionCommand("루이보스");
		btnRoob.addActionListener(this);

		panel_3.add(btnRoob);

		return panel_3;
	}

	public void showOrder() {

		Vector<OrderDTO> list = orderDAO.payOrder();

		int sum = 0;

		if (!list.isEmpty()) {

			for (OrderDTO dto : list) {

				String list1[] = { dto.getName(), dto.getPrice() + "", dto.getCount() + "" };
				sum += dto.getPrice();
				model.addRow(list1);

			}
			lblAllpay.setText("총금액 : " + sum);

		}

	}

	public void totalSum() {
		int sum = 0;
		// 합계 구하기
		for (int i = 0; i < table.getRowCount(); i++) {

			sum += Integer.parseInt(table.getValueAt(i, 1).toString())
					* Integer.parseInt(table.getValueAt(i, 2).toString());

		}
		lblAllpay.setText("총 금액 :" + sum);
		this.sum=sum;
	}
}