package os;

import java.util.concurrent.Semaphore;

public class Process extends Thread{
	// Process Data
	String name;
	int reqMem;	
	double[] btimes;
	
	int time = 0;
	int i = 0;
	
	// Semaphore
	Semaphore sem;

	
	public Process(String name, int mem, double[] bursttimes, Semaphore sem) {
		this.name = name;
		this.reqMem = mem;
		this.btimes = bursttimes;
	}
	
	public void run() {
		if(btimes[i] >= 1) { 
		// CPU Burst Time (positive integers)
			time = time + (int) btimes[i];
			
		}else if(btimes[i] > 0) { 
		// IO Burst Times (positive decimals)
			time = time + (int)(btimes[i] * 100);
			
		}else if(btimes[i] < 0) { 
		// Critical Section (negative integers)
			time = time + (int)Math.abs(btimes[i]);
			
		}else {
			// Something went wrong
			System.out.println("burst time error");
		}
		
		i++;
		
	}
	
	
}