package os;

import java.util.*;

public class FIFO {
public static void main(String [] args) {
	System.out.println("Enter Burst time. Type \"Enter\" to submit");
	Scanner input = new Scanner(System.in);
	
	int index = 1; 
	ArrayList<Task> tasks = new ArrayList<Task>();
	while(input.hasNextInt()) {
		System.out.println("Enter Burst time. Type \"Enter\" to submit");
		int burst = input.nextInt();
		tasks.add(new Task(index, burst));
		index++;
	}
	int numTasks = tasks.size();
	
	int waitingTime = 0;
	int time = 0;
	while(! tasks.isEmpty()) {
		Task t = tasks.remove(0);
		if(! tasks.isEmpty()) {
			waitingTime += waitingTime + t.burstTime;
		}
		time += t.burstTime;
		System.out.println("- P" + t.index + " - " + time);
	}
	System.out.println("\nAverage waiting time: " + waitingTime + "/" + numTasks + " = " + ((1.0*waitingTime)/numTasks));
	
	input.close();
}
}
