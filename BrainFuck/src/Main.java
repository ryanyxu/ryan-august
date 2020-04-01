import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("BrainFuck.txt"));
		String text = "";
		ArrayList<Integer> list = new ArrayList<>();
		int index = 0;
		while (sc.hasNext()) {
			text += sc.next();
		}
		sc.close();
		Scanner console = new Scanner(System.in);
		int i = 0;
		int count = 0;
		while (i < text.length()) {
			System.out.println(list);
			if (index >= list.size()) {list.add(index, 0);}
			switch (text.charAt(i)) {
			case '<' : 
				if (index == 0) {throw new IndexOutOfBoundsException();}
				index--;
				i++;
				break;
			case '>' :
				index++;
				i++;
				break;
			case '+' : 
				if (list.get(index) == 255) {list.set(index, 0);}
				else {list.set(index, list.get(index) + 1);}
				i++;
				break;
			case '-' :
				if (list.get(index) == 0) {list.set(index, 255);}
				else {list.set(index, list.get(index) - 1);}
				i++;
				break;
			case '.' :
				System.out.print((char)(int)list.get(index));
				i++;
				break;
			case ',' :
				list.set(index, (int)console.next().charAt(0));
				i++;
				break;
			case '[' :
				if (list.get(index) == 0) {
					i++;
					while (count != 0 || text.charAt(i) != ']') {
						if (text.charAt(i) == '[') {count++;}
						else if (text.charAt(i) == ']') {count--;}
						i++;
					}
					break;
				} else {
					i++;
					break;
				}
			case ']' :
				if (list.get(index) != 0) {
					i--;
					while (count != 0 || text.charAt(i) != '[') {
						if (text.charAt(i) == ']') {count++;}
						else if (text.charAt(i) == '[') {count--;}
						i--;
					}
					break;
				} else {
					i++;
					break;
				}
			default:
				i++;
			}
		}
		console.close();
	}
}
