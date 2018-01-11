/*
 * Object Oriented Programming Assignment
 * Year 3
 * Group C
 * G00334621 - Christian Olim
*/
package ie.gmit.sw;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class FileParser implements Runnable
{
	private int shingleSize;
	private int b;
	private int documentId;
	private String fileName;
	private List<String> buffer = new LinkedList<>();
	private BlockingQueue<Shingle> queue;
	
	// FileParser method
	public FileParser(String fileName, BlockingQueue<Shingle>queue, int shingleSize, int documentId)
	{
		// TODO Auto-generated constructor stub
		super();
		this.documentId = documentId;			
		this.shingleSize = shingleSize;
		this.queue = queue;
		this.fileName = fileName;
	}// End of FileParser

	// Run method
	public void run()
	{
		BufferedReader bufferedReader = null;
		try
		{
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		String line = null;	
		try
		{			
			while((line = bufferedReader.readLine())!= null)
			{
				String uLine = line.toUpperCase();
				System.out.println(uLine);
				String[] words = uLine.split(" "); // Can also take a regexpression
				addingWordsToBuffer(words);
				Shingle s = getShingle();
				queue.put(s); // Blocking method. Add is not a blocking method
			}
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		try
		{
			flushBuffer();
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			bufferedReader .close();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// End of Run
	
	// getShingle method
	private Shingle getShingle()
	{
		StringBuffer stringBuffer = new StringBuffer();
		int counter = 0;
		while(counter<shingleSize)
		{
			if(((LinkedList<String>) buffer).peek()!=null)
			{
				stringBuffer.append(((LinkedList<String>) buffer).poll());
				counter++;
			}
		}  
		if (stringBuffer.length()>0)
		{
			return(new Shingle(documentId, stringBuffer.toString().hashCode()));
		}
		else
		{
			return(null);
		}
  	}// End of getShingle
	
	// addingWordsToBuffer method
	private void addingWordsToBuffer(String[] words)
	{
		for(String s:words)
		{
			buffer.add(s);
		}
    }// End of addingWordsToBuffer	
	
	// Flush Buffer Method
	private void flushBuffer() throws InterruptedException
	{
		while(buffer.size()>0)
		{
			Shingle s=getShingle();
			if(s!=null)
			{
				queue.put(s);
			}
			else
			{
				queue.put(new PoisonShingle(documentId, 0));
			}
		}
	}// End of Flush Buff
	
}// End