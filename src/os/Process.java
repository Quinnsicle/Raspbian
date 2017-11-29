package os;

import java.util.concurrent.Semaphore;

public class Process extends Thread{
	// Process Data
	String name;
	int reqMem;	
	double[] btimes;
	String flag;
	
	int time = 0;
	int bTime = 0;
	int ioTime = 0;
	int ioStartTime = 0;
	int ioEndTime = 0;
	int i = 0;
	Boolean terminated = false;
	Boolean idle = false;
	Boolean cs = false;
	
	// Semaphore
	Semaphore sem;

	
	public Process(String name, int mem, double[] bursttimes, Semaphore sem) {
		this.name = name;
		this.reqMem = mem;
		this.btimes = bursttimes;
	}
	
	public void checkTime() {
		if(Output.time >= this.time) {
			idle = false;
		}
	}
	
	public void updateFlag() {
		if (btimes[i] >= 1) {
			// CPU Burst Time (positive integers)
			flag = "CPU";

		} else if (btimes[i] > 0 && btimes[i] < 1.0) {
			// IO Burst Time (positive decimals)
			ioTime = (int) (btimes[i] * 100);
			flag = "IO";
			i++;

		} else if (btimes[i] < 0) {
			// Critical Section (negative integers)
			flag = "CS";

		} else {
			// Something went wrong
			System.out.println("flag Error");
		}
	}
	
	
	public void run() {
		checkTime();

			cs = false;
			idle = false;
			flag = "";
		
			
			
			if (btimes[i] >= 1) {
				// CPU Burst Time (positive integers)
				time = time + (int) btimes[i];
				bTime = (int) btimes[i];

			} else if (btimes[i] > 0 && btimes[i] < 1.0) {
				// IO Burst Time (positive decimals)
				time = time + (int) (btimes[i] * 100);
				bTime = (int) (btimes[i] * 100);
				idle = true;

			} else if (btimes[i] < 0) {
				// Critical Section (negative integers)
				time = time + (int) Math.abs(btimes[i]);
				bTime = (int) Math.abs(btimes[i]);
				cs = true;

			} else {
				// Something went wrong
				System.out.println("burst time error");
			}
			
			i++;

			

			if (btimes.length == i) {
				terminated = true;
				
			} 
			else {
				updateFlag();
			}

			
				
	}	
}