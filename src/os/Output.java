package os;

import java.util.*;


public class Output extends Thread{
	//
	// DATA
	//
	
	// CPU
	int time = 0;
	String currentP;
	String[] readyQ = {"poop"}; //should be process list
	String[] ioQ = {"butts"};
	
	// MEM
	//Process[] loaded;
	int totalSpace = 512;
	int usedSpace = 0;
	int avalSpace = totalSpace;
	
	
	String p1name = "P1";
	String p2name = "P2";
	int mem = 32;
	int[] cputime = {2,3,4,0};
	int[] iotime = {3,4,0,0};
	Process p1 = new Process(p1name, mem, cputime, iotime);
	Process p2 = new Process(p2name, mem, cputime, iotime);
	
	Process[] processList = {p1, p2};
	Process[] loaded = {p1, p2};
	
	//
	// METHODS
	//
	
	public void updateUsedSpace() {
		int updated = 0;
		for(Process l : loaded) {
			usedSpace = l.reqMem + usedSpace;
		}
		usedSpace = updated;
	}
	
	public String processListToString(Process[] list) {
		String[] array = new String[list.length];
		int i = 0;
		for(Process p : list) {
			array[i] = p.name;
			i++;
		}
		return Arrays.toString(array);
	}
	
	public void run() {
		
		for(Process p : processList) {
			//time
			System.out.println("@time: " + time);
			time = p.cpuBurst[0] + time;
			
			//cpu usage
			currentP = p.name;
			System.out.println("CPU Usage: " + currentP + " Running");
			
			//cpu queues
			System.out.println("Ready Queue: " + readyQ[0]);
			System.out.println("I/O Waiting Queue: " + ioQ[0]);
			
			//mem
			System.out.println("Loaded: " + processListToString(loaded));
			avalSpace = totalSpace - usedSpace;
			System.out.println("Used Space: " + usedSpace);
			System.out.println("Available Space: " + avalSpace);
			
			
			
		}
		
	}
	
	
	

	public static void main(String[] args) {
		Output poop = new Output();
		poop.start();
		System.out.println("Starting poop");

	}

}
