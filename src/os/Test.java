package os;

public class Test {

	public static void main(String[] args) {
		System.out.println("Hello Raspbian!");
		
		int[] cputime = {2,3,4,0};
		int[] iotime = {3,4,0,0};
		int mem = 256;
		Process p1 = new Process("poop", mem, cputime, iotime);
		Process p2 = new Process("poop", 100, cputime, iotime);
		
		int i = 0;
		while(i<4){
			System.out.println("cpu burst time "+ (i+1) + ": " + p1.cpuBurst[i]);
			i++;
		}
		
		
		int synch = 0;
		
		p1.Wait();
		p1.Signal();
		
		

	}
}
