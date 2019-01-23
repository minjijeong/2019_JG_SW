import java.util.Scanner;

/**
 * <pre>
 * https://www.acmicpc.net/problem/10866
 * 정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 여덟 가지이다.

push_front X: 정수 X를 덱의 앞에 넣는다.
push_back X: 정수 X를 덱의 뒤에 넣는다.
pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 덱에 들어있는 정수의 개수를 출력한다.
empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190101_01 {	
	static int size;
	
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cmd_num = sc.nextInt();
		Deque deque = new Deque();
		
		for(int command = 1; command <= cmd_num; command++) {
			String cmd = sc.next();
			
			switch(cmd) {
			case "push_front":
				String num = sc.next();
				deque.push_front(Integer.parseInt(num));
				break;
			case "push_back":
				String num2 = sc.next();
				deque.push_back(Integer.parseInt(num2));
				break;
			case "pop_front":
				System.out.println(deque.pop_front());
				break;
			case "pop_back":
				System.out.println(deque.pop_back());
				break;
			case "size":
				System.out.println(deque.print_size());
				break;
			case "empty":
				System.out.println(deque.print_empty());
				break;
			case "front":
				System.out.println(deque.print_front());
				break;
			case "back":
				System.out.println(deque.print_back());
				break;
			}
		}
	}
}

class Deque {
	private int size;
	private DequeData front, back;
	
	public Deque() {
		this.size = 0;
		this.front = null;
		this.back = null;
	}
	
	private class DequeData{
		private int data;
		private DequeData right, left;
		
		DequeData(int dequeData){
			this.data = dequeData;
			this.right = null;
			this.left = null;
		}
	}
	
	public void push_front (int x) {
		DequeData newData = new DequeData(x);
		if(print_size() <= 0) {
			this.front = newData;
			this.back = newData;
			newData.right = null;
			newData.left = null;
		}
		else {
			newData.right = front;
			newData.left = null;
			this.front.left = newData;
			this.front = newData;
		}
		this.size++;
	}
	
	public void push_back (int x) {
		DequeData newData = new DequeData(x);
		if(print_size() <= 0) {
			this.front = newData;
			this.back = newData;
			newData.right = null;
			newData.left = null;
		}
		else {
			newData.left = back;
			newData.right = null;
			this.back.right = newData;
			this.back = newData;
		}
		this.size++;
	}
	
	public int pop_front () {
		if(this.size <= 0)
			return -1;
		else {
			DequeData temp = this.front;
			if(this.front.right == null) {
				this.front = null;
				this.back = null;
			}
			else {
				this.front = temp.right;
				this.front.left = null;
			}
			this.size--;
			return temp.data;
		}
	}
	
	public int pop_back () {
		if(this.size <= 0)
			return -1;
		else {
			DequeData temp = this.back;
			if(this.back.left == null) {
				this.front = null;
				this.back = null;
			}
			else {
				this.back = temp.left;
				this.back.right = null;
			}
			this.size--;
			return temp.data;
		}
	}
	
	public int print_size () {
		return this.size;
	}
	
	public int print_empty () {
		return this.size > 0 ? 0 : 1;
	}
	
	public int print_front () {
		return print_empty() == 1 ? -1 : front.data;
	}
	
	public int print_back () {
		return print_empty() == 1 ? -1 : back.data;
	}
}
