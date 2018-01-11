/*
 * Object Oriented Programming Assignment
 * Year 3
 * Group C
 * G00334621 - Christian Olim
*/
package ie.gmit.sw;

import java.io.*;
import java.util.concurrent.*;

public class Launcher 
{
	// Launch method
	public void launch(String fileName1, String fileName2, int shingleSize) throws IOException
	{	
		BlockingQueue<Shingle> q = new LinkedBlockingDeque<>();
	
		Thread t1 = new Thread(new FileParser(fileName1, q, shingleSize, 1), "Thread1");
		t1.start();
		Thread t2 = new Thread(new FileParser(fileName2, q, shingleSize, 2), "Thread2");
		t2.start();	
	}// End of Launch

}// End 