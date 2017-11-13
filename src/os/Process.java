package os;

public class Process {
	int reqMem;
	int[] cpuBurst;
	int[] ioBurst;
	
	public Process(int mem, int[] cpuBurstTime, int[] ioBurstTime) {
		this.reqMem = mem;
		this.cpuBurst = cpuBurstTime;
		this.ioBurst = ioBurstTime;
	}
	

	
	
}
