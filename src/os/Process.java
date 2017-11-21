package os;

public class Process{
	String name;
	int reqMem;
	int[] cpuBurst;
	int[] ioBurst;
	int s = 0;
	
	
	// Critical sections are negative values in the burst time arrays
	
	public Process(String name, int mem, int[] cpuBurstTime, int[] ioBurstTime) {
		this.name = name;
		this.reqMem = mem;
		this.cpuBurst = cpuBurstTime;
		this.ioBurst = ioBurstTime;
	}
	
	
	
	
	
	
}