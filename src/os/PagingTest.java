package os;

public class PagingTest {
public static void main (String [] args ) {
	Paging takeInput = new Paging();

	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);
	takeInput.fifo(123);

	int i = 0;
	System.out.println("Page "+i+": "+  " Memory: " + takeInput.returnThing(i));
	i++;
	System.out.println("Page "+i+": "+  " Memory: " + takeInput.returnThing(i));
	
}
}
