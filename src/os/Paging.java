package os;

public class Paging {
	public int storage = 0; 
	public  int pageN = 0;
	Memory mem = new Memory();
public void fifo(int input) {
	storage = storage + input;
	if(storage>512) {
		//System.out.println("Overflow");
		storage = storage-input;
		CreatePageTable();
	}else { 
		//int remain = 512 - storage;
		//System.out.println("Remaining: "+ remain);
	}
}
public void CreatePageTable() {
	//System.out.println("Page "+pageN + ": "+ storage + "/512");
	mem.memory(pageN, storage);
	storage = 0;
	pageN++;
}

public int returnPageN() {
	return pageN;
}
public int returnStorage() {
	return storage;
}
public int returnThing(int pageNumber) {
	return mem.returnStorage(pageNumber);
}
}
