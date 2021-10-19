package cafekiosk.persistence;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;

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

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CafeMenu extends JFrame implements ActionListener {
   
   private JPanel contentPane;
   private JButton btnCoffee, btnAde, btnTea, btnDessert, btnEtc;
   private JScrollPane scrollPane;
   private JPanel panel_1, panel_2, panel_3, panel_4;
   private JButton btnGrapeAde, btnGrapefruitsAde, btnLemonAde, btnamericano, btncafelatte, btnespresso, btnEgr,
         btnPam, btnRoob;
   private JTable table;
   private String orderList[] = { "타입", "음료","수량" };
   
   private DefaultTableModel model;
   private JPanel panel_5;
   private JButton btnNewButton;
   private JButton btnNewButton_1;
   private int count = 0, menuNum = 0;
   private JLabel lblcount = new JLabel((count+1)+"");
   private Vector<CafeDTO> vetMenu;
   CafeDTO dto;
   private JButton btnNewButton_2,btnNewButton_3;

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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new BorderLayout(0, 0));

      JPanel panel = new JPanel(); // 메뉴창 틀(패널)
      contentPane.add(panel, BorderLayout.NORTH);
      panel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));

      btnCoffee = new JButton("커피"); // 커피 메뉴
      btnCoffee.setActionCommand("COFFEE");
      btnCoffee.addActionListener(this);
      panel.add(btnCoffee);// 메뉴창 패널에 추가

      btnAde = new JButton("에이드"); // 에이드 메뉴
      btnAde.setActionCommand("ADE");
      btnAde.addActionListener(this);
      panel.add(btnAde);

      btnTea = new JButton("티"); // 티 메뉴
      btnTea.setActionCommand("TEA");
      btnTea.addActionListener(this);
      panel.add(btnTea);

      btnDessert = new JButton("디저트"); // 디저트 메뉴
      btnDessert.setActionCommand("DESSERT");
      btnDessert.addActionListener(this);
      panel.add(btnDessert);

      btnEtc = new JButton("기타"); // 기타 메뉴
      btnEtc.setActionCommand("ETC");
      btnEtc.addActionListener(this);
      panel.add(btnEtc);

      scrollPane = new JScrollPane();// 메뉴 창 띄우는 패널
      contentPane.add(scrollPane);

      panel_4 = new JPanel();
      panel_4.setBorder(new TitledBorder(null, "\uC8FC\uBB38\uB9AC\uC2A4\uD2B8", TitledBorder.LEADING,
            TitledBorder.TOP, null, null));
      contentPane.add(panel_4, BorderLayout.SOUTH);
      GridBagLayout gbl_panel_4 = new GridBagLayout();
      gbl_panel_4.columnWidths = new int[]{232, 232, 0};
      gbl_panel_4.rowHeights = new int[]{428, 0, 0};
      gbl_panel_4.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
      panel_4.setLayout(gbl_panel_4);
      model = new DefaultTableModel(orderList, 0);
      
            JScrollPane scrollPane1 = new JScrollPane();
            GridBagConstraints gbc_scrollPane1 = new GridBagConstraints();
            gbc_scrollPane1.fill = GridBagConstraints.BOTH;
            gbc_scrollPane1.insets = new Insets(0, 0, 5, 5);
            gbc_scrollPane1.gridx = 0;
            gbc_scrollPane1.gridy = 0;
            panel_4.add(scrollPane1, gbc_scrollPane1);
            table = new JTable(model);
            table.setBorder(null);
            
            scrollPane1.setViewportView(table);
      panel_5 = new JPanel();
      
            panel_5.setBorder(new TitledBorder(null, "수량", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            GridBagConstraints gbc_panel_5 = new GridBagConstraints();
            gbc_panel_5.insets = new Insets(0, 0, 5, 0);
            gbc_panel_5.fill = GridBagConstraints.BOTH;
            gbc_panel_5.gridx = 1;
            gbc_panel_5.gridy = 0;
            panel_4.add(panel_5, gbc_panel_5);
            panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            // 수량 체크 
            panel_5.add(disCountButton());
            panel_5.add(lblcount);
            panel_5.add(plusCountButton());
            
            btnNewButton_2 = new JButton("결제하기 ");
            GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
            gbc_btnNewButton_2.gridx = 1;
            gbc_btnNewButton_2.gridy = 1;
            panel_4.add(btnNewButton_2, gbc_btnNewButton_2);
            
            // db에 사용자 이용 값 넣기/ 수량 넣기  
            btnNewButton_3 = new JButton("선택완료");
            btnNewButton_3.addActionListener(new ActionListener() {
               
               @Override
               public void actionPerformed(ActionEvent e) {
                  table.setValueAt(count+"", menuNum, 2);
                  menuNum += 1;
                  count = 0;
                  lblcount.setText(count+"");
               }
            });
            panel_5.add(btnNewButton_3);
      setSize(500, 700);
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
      btnGrapeAde.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            if ( count== 0) {
               String cmd = e.getActionCommand();
               if (cmd.equals("청포도에이드")) {
                  count +=1; 
                  CafeDAO dao = new CafeDAO();
                  vetMenu = new Vector<CafeDTO>();
//                  dto = new CafeDTO();
//                  dto = dao.getList(cmd);
//                  String list[] = { dto.getType(), dto.getName() };
                  vetMenu= dao.getList(cmd);
                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
                  model.addRow(list);
                  
               }
            } else {
               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
            }
            panel_5.revalidate();
         }
      });

      btnGrapefruitsAde = new JButton("");
      btnGrapefruitsAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapefruitade.png")));
      panel_1.add(btnGrapefruitsAde);
      btnGrapefruitsAde.setActionCommand("자몽에이드");
      btnGrapefruitsAde.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (count == 0) {
               String cmd = e.getActionCommand();
               if (cmd.equals("자몽에이드")) {
                  CafeDAO dao = new CafeDAO();
                  dto = new CafeDTO();
//                  dto = dao.getList(cmd);
                  vetMenu = new Vector<CafeDTO>();
                  vetMenu = dao.getList(cmd);
//                  String list[] = { dto.getType(), dto.getName() };
                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
                  model.addRow(list);
//                  countAde[i]+=1;
//                  panel_5.add(disCountButton(i));
//                  panel_5.add(lblAde2);
//                  panel_5.add(plusCountButton(i));

               }
            } else {
               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
            }
            panel_5.revalidate();
         }
      });

      btnLemonAde = new JButton("");
      btnLemonAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/lemon.png")));
      panel_1.add(btnLemonAde);
      btnLemonAde.setActionCommand("레몬에이드");
      btnLemonAde.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            if (count == 0) {
               String cmd = e.getActionCommand();
               if (cmd.equals("레몬에이드")) {
                  CafeDAO dao = new CafeDAO();
                  dto = new CafeDTO();
//                  dto = dao.getList(cmd);
                  vetMenu = new Vector<CafeDTO>();
                  vetMenu = dao.getList(cmd);
//                  String list[] = { dto.getType(), dto.getName() };
//                  vetMenu.add(dto);
                  
                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
                  model.addRow(list);

               }
            } else {
               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
            }
            panel_5.revalidate();
         }
      });
      
      
      return panel_1;
   }

   // 수량 줄이는 메소드
   public JButton disCountButton() {
      JButton btn = new JButton("-");
      btn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            count -= 1;
            lblcount.setText(count+"");
//            table.setValueAt(count, ERROR/*?*/, ABORT/*?*/);
            if (count == 0) {
               JOptionPane.showMessageDialog(getParent(), "더 이상 줄일 수 없습니다");
            }
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
         count += 1;
         lblcount.setText(count+"");
//            table.setValueAt(count, ERROR/*?*/, ABORT/*?*/);
         }
      });

      return btn;
   }

   // 메뉴 버튼 이벤트시 메뉴창 띄우는 메소드 불러오기
   @Override
   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      if (cmd.equals("COFFEE")) {
         scrollPane.setViewportView(coffeeMenuPanel());
      } else if (cmd.equals("ADE")) {
         scrollPane.setViewportView(adeMenuPanel());
      } else if (cmd.equals("TEA")) {
         scrollPane.setViewportView(TeaMenuPanel());
      }
   }

   public JPanel coffeeMenuPanel() {
      panel_2 = new JPanel();
      panel_2.setLayout(new GridLayout(0, 3, 0, 0));
      panel_2.setBounds(0, 0, 200, 350);
      btnamericano = new JButton("");
      btnamericano.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/americano.png")));
      panel_2.add(btnamericano);

      btncafelatte = new JButton("");
      btncafelatte.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/cafelatte.png")));
      panel_2.add(btncafelatte);

      btnespresso = new JButton("");
      btnespresso.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/espresso.png")));
      panel_2.add(btnespresso);
      return panel_2;
   }

   public JPanel TeaMenuPanel() {
      panel_3 = new JPanel();
      panel_3.setLayout(new GridLayout(0, 3, 0, 0));
      panel_3.setBounds(0, 0, 200, 350);
      btnEgr = new JButton("");
      btnEgr.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/earlgray.jpg")));
      btnEgr.setActionCommand("얼그레이");
      btnEgr.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 if (count == 0) {
	               String cmd = e.getActionCommand();
	               if (cmd.equals("얼그레이")) {
	                  CafeDAO dao = new CafeDAO();
	                  dto = new CafeDTO();

	                  vetMenu = new Vector<CafeDTO>();
	                  vetMenu = dao.getList(cmd);

	                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
	                  model.addRow(list);

	               }
	            } else {
	               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
	            }
	            panel_5.revalidate();
	         
			
		}
	});
      panel_3.add(btnEgr);

      btnPam = new JButton("");
      btnPam.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/Paper.jpg")));
      btnPam.setActionCommand("페퍼민트");
      btnPam.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 if (count == 0) {
	               String cmd = e.getActionCommand();
	               if (cmd.equals("페퍼민트")) {
	                  CafeDAO dao = new CafeDAO();
	                  dto = new CafeDTO();

	                  vetMenu = new Vector<CafeDTO>();
	                  vetMenu = dao.getList(cmd);
	                  
	                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
	                  model.addRow(list);

	               }
	            } else {
	               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
	            }
	            panel_5.revalidate();
	         }
			
		
	});
      panel_3.add(btnPam);

      btnRoob = new JButton("");
      btnRoob.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/roibos.jpg")));
      btnRoob.setActionCommand("루이보스");
      btnRoob.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 if (count == 0) {
	               String cmd = e.getActionCommand();
	               if (cmd.equals("루이보스")) {
	                  CafeDAO dao = new CafeDAO();
	                  dto = new CafeDTO();

	                  vetMenu = new Vector<CafeDTO>();
	                  vetMenu = dao.getList(cmd);
	                  
	                                   
	                  String list[] = { vetMenu.get(0).getType(), vetMenu.get(0).getName(), 0+""};
	                  model.addRow(list);

	               }
	            } else {
	               JOptionPane.showMessageDialog(getParent(), "이미 주문리스트에 추가되었습니다 \n수량을 체크하세요");
	            }
	            panel_5.revalidate();
	         }
			
		
	});
      panel_3.add(btnRoob);
      
      return panel_3;
   }

}