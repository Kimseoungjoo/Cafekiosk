package cafekiosk.persistence;

import java.util.ArrayList;
import java.util.Scanner;

import cafekiosk.domain.CafeDTO;

public class CafeMain {

	public static void main(String[] args) {

		boolean run = true;
		Scanner sc = new Scanner(System.in);
		OrderDAO dao = new OrderDAO();
		
		CafeDTO dto = dao.getRow(7);
		
		System.out.println("\n메뉴이름\t가격\t종류");
		
		System.out.println(dto.getName() + "\t" + dto.getPrice() + "\t" + dto.getType());
			
		
		
			
			

		

	}
}
