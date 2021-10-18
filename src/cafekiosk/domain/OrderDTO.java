package cafekiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderDTO {
	
	private int no;
	private String name;
	private int price;
	private int count;
	private int point;

}
