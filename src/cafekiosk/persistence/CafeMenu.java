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
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CafeMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnCoffee,btnAde,btnTea,btnDessert,btnEtc;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnGrapeAde,btnGrapefruitsAde,btnLemonAde;
	
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
		contentPane.add(panel,BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
		
		 btnCoffee = new JButton("커피"); // 커피 메뉴
		 btnCoffee.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		panel.add(btnCoffee);// 메뉴창 패널에 추가 
		
		btnAde = new JButton("에이드"); // 에이드 메뉴 
		btnAde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAde);
		
		 btnTea = new JButton("티");
		panel.add(btnTea);
		
		 btnDessert = new JButton("디저트");
		panel.add(btnDessert);
		
		 btnEtc = new JButton("기타");
		panel.add(btnEtc);
		
		scrollPane = new JScrollPane();// 메뉴 창 띄우는 패널
		contentPane.add(scrollPane);
		scrollPane.setViewportView(adeMenuPanel()); 
		
//		panel_1 = new JPanel();
//		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
//		panel_1.setBounds(0, 0, 200, 350);
		
//		btnGrapeAde = new JButton("");
//		btnGrapeAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapeade.png")));
//		panel_1.add(btnGrapeAde);
//		
//		btnGrapefruitsAde = new JButton("");
//		btnGrapefruitsAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapefruitade.png")));
//		panel_1.add(btnGrapefruitsAde);
//		
//		btnLemonAde = new JButton("");
//		btnLemonAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/lemon.png")));
//		panel_1.add(btnLemonAde);
		setSize(500,300);
		
	}
	public JPanel adeMenuPanel() {
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		panel_1.setBounds(0, 0, 200, 350);
		btnGrapeAde = new JButton("");
		btnGrapeAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapeade.png")));
		panel_1.add(btnGrapeAde);
		
		btnGrapefruitsAde = new JButton("");
		btnGrapefruitsAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/grapefruitade.png")));
		panel_1.add(btnGrapefruitsAde);
		
		btnLemonAde = new JButton("");
		btnLemonAde.setIcon(new ImageIcon(CafeMenu.class.getResource("/image/lemon.png")));
		panel_1.add(btnLemonAde);
		return panel_1;
	}
	
	

}
