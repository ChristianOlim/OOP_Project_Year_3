/*
 * Object Oriented Programming Assignment
 * Year 3
 * Group C
 * G00334621 - Christian Olim
*/
package ie.gmit.sw;

import java.io.IOException;
import java.util.*;

public class UI 
{
	// Scanner to take user input
	private Scanner scanner = new Scanner(System.in);
	
	// Variables
	private String fileName1;
	private String fileName2;
	private boolean keepMenuAlive = true;
	private int choice = -1;
	private int blockingQueue;
	private int shingleSize;
	
	// Show method
	public void show() throws IOException{
		do
		{
			// Menu/UI
			System.out.println("==============================================================");
			System.out.println("Object Oriented Programming Yr 3 - G00334621 - Christian Olim");
			System.out.println("==============================================================");
			System.out.println("\nMain Menu \n1. Compare Files.\n2) Exit.\n");
			choice = scanner.nextInt();
			switch(choice)
			{
				// Compare Files method
				case 1:
					compareFiles();
					break;
				// Exit program
				case 2:
					keepMenuAlive = false;
					System.out.println("Goodbye!");
					break;
			}
		}while(keepMenuAlive);
	}
	
	// Compare Files method
	private void compareFiles() throws IOException
	{
		System.out.println("Comparing Files");
		System.out.println("---------------");
		
		// Enter details here
		System.out.print("Enter File Name / URL 1> ");
		fileName1 = scanner.next();
		System.out.print("Enter File Name / URL 2> ");
		fileName2 = scanner.next();
		System.out.print("Enter size of Shingle> ");
		shingleSize = scanner.nextInt();	
		System.out.print("Enter size of Blocking Queue> ");
		blockingQueue = scanner.nextInt();
		
		System.out.println("Processing.. Please wait");
		
		new Launcher().launch(fileName1, fileName2, shingleSize);
	}
}
/*
  Enter File Name / URL 1> ./WarAndPeace.txt
  Enter File Name / URL 2> ./Caesar.txt
  */ 

