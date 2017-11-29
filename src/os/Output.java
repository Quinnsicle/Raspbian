package os;

import java.util.*;
import java.util.concurrent.Semaphore;

// to do
//process termination
//memory 1. removing terminated processes from loaded
//		 2. only loading processes that fill aval space
// cpu scheduling 1. ready Queue
//				  2. IO queue
// semaphore	1. waiting queue for d

public class Output extends Thread{
	//--------------------------------------------------
	// DATA
	//--------------------------------------------------
	
	// Process info
	Semaphore sem = new Semaphore(1);
	// p1
	String p1name = "P1";
	int p1mem = 64;
	double[] p1btimes = {3, .14, 3, -2, 2};
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
	static int time = 0;
//	int endtime = 0;
	Process currentP;
	Queue<Process> readyQ = new LinkedList<Process>();
	Queue<Process> waitingQ = new LinkedList<Process>();
	
	// MEM
	List<Process> loaded = new LinkedList<Process>();
	int totalSpace = 256;
	int usedSpace = 0;
	int avalSpace = totalSpace;
	
	// General
	List<Process> pList = new LinkedList<Process>();
	int j;	// set to the pList size in run()
	String termMsg = "";
	String idleMsg = "";
	Boolean sysIdle = false;
	
	
	//--------------------------------------------------
	// METHODS
	//--------------------------------------------------
	
	public void updateUsedSpace() {
		int updated = 0;
		for(Process p : loaded) {
			updated = p.reqMem + updated;
		}
		usedSpace = updated;
		avalSpace = totalSpace - updated;
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
	
	public String processQueueToString(Queue<Process> Q) {
		if(Q.size() == 0) {
			return "-";
		}else {
			String[] array = new String[Q.size()];
			int i = 0;
			for(Process p : Q) {
				array[i] = p.name;
				i++;
			}
			return Arrays.toString(array);
		}
	}
	
	
//	public String isRunning() {
//		if(currentP.idle == true) {
//			return "idle ";
//		}else {
//			return currentP.name + " running ";
//		}
//	}
	
	public String inCS() {
		if(currentP.cs == true) {
			return "(in CS) ";
		}else {
			return "";
		}
	}
	

	
	public void updateTime() {
		//time
		System.out.println("@time: " + time);
		time = currentP.bTime + time;
	}
	
	public void updateCPU() {
		//cpu usage
		System.out.println("CPU Usage: "+ currentP.name + " running " + inCS() + termMsg);
		System.out.println("	Ready Queue: " + processQueueToString(readyQ));
		System.out.println("	I/O Waiting Queue: " + processQueueToString(waitingQ));
		
		cpuMSG = "";
		termMsg = "";
	}
	
	public void updateMem() {
		//updates usedSpace
		int updated = 0;
		for(Process p : loaded) {
			updated = p.reqMem + updated;
		}
		usedSpace = updated;
		avalSpace = totalSpace - updated;
		
		//mem		
		System.out.println("Memory Usage:");
		System.out.println("	Loaded: " + processListToString(loaded));
//		avalSpace = totalSpace - usedSpace;
		System.out.println("	Used Space: " + usedSpace);
		System.out.println("	Available Space: " + avalSpace);
		System.out.println();
	}
	
	public void updateQs() {
		for(Process p : pList) {
			if(p.flag == "CPU" || p.flag == "CS" && !readyQ.contains(p)) {
				readyQ.add(p);
			} else if(p.flag == "IO" && !waitingQ.contains(p)){
				waitingQ.add(p);
				p.ioStartTime = time;
				p.ioEndTime = p.ioTime + p.ioStartTime;

			}
		}
	}
	
	public void checkWaitingQ() {
		for(Process p : waitingQ) {
			if(time >= p.ioEndTime) {
				readyQ.add(p);
				waitingQ.remove(p);
				p.ioTime = 0;
			}
		}
	}
	
	//--------------------------------------------------
	// THREAD
	//--------------------------------------------------
	public void run() {
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		
		readyQ.add(p1);
		readyQ.add(p2);
		readyQ.add(p3);
		
		
		loaded.add(p1);
		loaded.add(p2);
		loaded.add(p3);
		
		
		while(true) {		
			
			
			if(!readyQ.isEmpty()) {
				currentP = readyQ.remove();
				cpuMSG = currentP.name + inCS() + termMsg;
				currentP.run();
				
				//time
				updateTime();
				
				//cpu usage
				updateCPU();
				
				//mem
				updateMem();
				
				updateQs();
				
				
				if(currentP.terminated) {
					termMsg = "- " + currentP.name + " terminated";
					loaded.remove(currentP);
//					pList.remove(currentP);
				}
				
				
			} else if(!waitingQ.isEmpty()) {
				sysIdle = true;
				cpuMSG = "idle " + termMsg;
				if(termMsg.length() > 1) {
					//time
					System.out.println("@time: " + time);
					
					//cpu usage
					System.out.println("CPU Usage: "+ cpuMSG);
					System.out.println("	Ready Queue: " + processQueueToString(readyQ));
					System.out.println("	I/O Waiting Queue: " + processQueueToString(waitingQ));
					
					//mem
					updateMem();
					
					updateQs();
					termMsg = "";
				}
				
				time++;
				//checkWaitingQ();
				
				if(time == 100) {
					System.out.print("time = 100, ending program");
					break;
				}
				
			} else {
				//time
				System.out.println("@time: " + time);
				
				//cpu usage
				System.out.println("CPU Usage: "+ termMsg);
				System.out.println("	Ready Queue: " + processQueueToString(readyQ));
				System.out.println("	I/O Waiting Queue: " + processQueueToString(waitingQ));
				
				//mem
				updateMem();
				
				System.out.println("\n" + "********************" + "\n" + "END OF OS SIMULATION" + "\n" + "********************");
				break;
			}
			
			checkWaitingQ();
			
			

			currentP = null;
			
			
		}	
	}
	
	
	

	public static void main(String[] args) {
		Output test = new Output();
		test.start();
		
		
	}

}
