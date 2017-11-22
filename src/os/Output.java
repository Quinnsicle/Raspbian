package os;

import java.util.*;
import java.util.concurrent.Semaphore;

import model.Guard;


public class Output extends Thread{
	//--------------------------------------------------
	// DATA
	//--------------------------------------------------
	
	// Process info
	Semaphore sem = new Semaphore(1);
	// p1
	String p1name = "P1";
	int p1mem = 64;
	double[] p1btimes = {3, .14, -2, 2};
	// p2
	String p2name = "P2";
	int p2mem = 32;
	double[] p2btimes = {5, .07, 1, -2, 1};
	// p3
	String p3name = "P3";
	int p3mem = 32;
	double[] p3btimes = {6};
	
	Process p1 = new Process(p1name, p1mem, p1btimes, sem);
	Process p2 = new Process(p2name, p2mem, p2btimes, sem);
	Process p3 = new Process(p3name, p3mem, p3btimes, sem);
	
	// CPU
	String cpuMSG;
	int time = 0;
	Process currentP;
	List<Process> readyQ = new LinkedList<Process>();
	List<Process> ioQ = new LinkedList<Process>();
	
	// MEM
	List<Process> loaded = new LinkedList<Process>();
	int totalSpace = 512;
	int usedSpace = 0;
	int avalSpace = totalSpace;
	
	// General
	List<Process> pList = new LinkedList<Process>();

	
	
	//--------------------------------------------------
	// METHODS
	//--------------------------------------------------
	
	public void updateUsedSpace() {
		int updated = 0;
		for(Process l : loaded) {
			usedSpace = l.reqMem + usedSpace;
		}
		usedSpace = updated;
	}
	
	
	// Takes in a process queue and returns a string to be printed
	public String processListToString(List<Process> list) {
		if(list.size() == 0) {
			return "-";
		}else {
			String[] array = new String[list.size()];
			int i = 0;
			for(Process p : list) {
				array[i] = p.name;
				i++;
			}
			return Arrays.toString(array);
		}
	}
	
	public void addP () {
		pList.add(p2);
	}
	
	//--------------------------------------------------
	// THREAD
	//--------------------------------------------------
	public void run() {
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		cpuMSG = p1.name;
		int j = pList.size();
		for(int i=0; i < j; i++) { // Need to change
			currentP = pList.get(i);
			currentP.run();
			
			//time
			System.out.println("@time: " + time);
			time = currentP.time + time;
			
			//cpu usage
			System.out.println("CPU Usage: " + cpuMSG + " Running");
			System.out.println("	Ready Queue: " + processListToString(readyQ));
			System.out.println("	I/O Waiting Queue: " + processListToString(ioQ));
			
			//mem
			System.out.println("Memory Usage:");
			System.out.println("	Loaded: " + processListToString(loaded));
			avalSpace = totalSpace - usedSpace;
			System.out.println("	Used Space: " + usedSpace);
			System.out.println("	Available Space: " + avalSpace);
			System.out.println();
			
			cpuMSG = currentP.name;
			
		}
		
	}
	
	
	

	public static void main(String[] args) {
		Output test = new Output();
		test.start();

	}

}
