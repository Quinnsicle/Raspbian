package os;

public class Memory {
	int memoryCells [] = new int [10];
public void memory(int pageNumber, int size) {
	//System.out.println("Got memory of " + size);
	//System.out.println("On page " + pageNumber);
	memoryCells[pageNumber] = size;
}
public int returnStorage(int pageNumber) {
	return memoryCells[pageNumber];
}
}