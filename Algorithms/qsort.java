/*
This Quick sort program will read a sequence of integers from a standard input. It will store them in
an an arraylist, sort them with quicksort, then print the sorted results to standard output, one value per
line/ the screen itself. It prints the execution time in milliseconds.

The program takes a command-line argument, 'limit'. This tells it when to change sorting strategies.
When recursion reaches a list size of k or smaller, this quicksort will just sort the list using 
selection sort, rather than continuing to quicksort it recursively. Thiswill let us easily experiment 
with different trade-off points between quicksort and an asymptotically
slower sort that might actually be faster for small enough lists.

In order to run the code on Windows command-prompt

> javac qsort.java
> java qsort input_file_name limit_value

In order to get the output in a output.txt filename

> javac qsort.java
> java qsort input_file_name limit_value > output.txt


limit-value can be any integer better use, 1,3,10,30,100

Author: Shriyansh Yadav
Algorithm Reference: Introduction to Algorithms, 3rd Edition– Cormen, Leiserson, Rivest and Stein
Programming Language: Java

*/

import java.util.*;
import java.io.*;

class qsort
{
	public static void main(String args[]) throws IOException
	{
		if (0 < args.length) 
		{
			String filename = args[0];
			int limit = Integer.parseInt(args[1]);
			Scanner s = new Scanner(new File(filename));
			ArrayList <Integer> A = new ArrayList <Integer>();

			while (s.hasNextLine())
			{
				if(s.hasNextInt())
					A.add(Integer.parseInt(s.nextLine()));
				else
					s.next();
			}
			s.close();
			
			
			long t0 = getMilliseconds();
			quicksort(A, 0, A.size()-1, limit);
			long t1 = getMilliseconds();
			
			System.err.println( t1 - t0 );
			for (Integer element : A) 
				System.out.println(element);
		}
	}
	
	public static void quicksort(ArrayList<Integer> A, int p, int r, int limit)
	{
		if(p<r)
		{
			int q;
			q = partition(A, p, r);
			
			if((q-1)-p <= limit)
				quicksort(A, p, q-1, limit);
			else
				selection_sort(A, p, q-1);
			
			if(r-(q+1) <= limit)
				quicksort(A, q+1, r, limit);
			else
				selection_sort(A, q+1, r);
		}
	}

	public static int partition(ArrayList<Integer> A, int p, int r)
	{
		int i,j,x;
		
		x = A.get(r);
		i = p-1;
		for(j=p; j<=r-1; j++)
		{
			if(A.get(j)<=x)
			{
				i = i + 1;
				Collections.swap(A, i, j);	
			}	
		}
		Collections.swap(A, i+1, r);
	return (i+1);
	}
	
	public static long getMilliseconds() 
	{
		return System.currentTimeMillis();
	}
	
	public static void selection_sort(ArrayList<Integer> A, int i, int j)
	{
		int a,b;
		for(a=i; a<=j; a++)
				for(b=a+1; b<=j; b++)
					if(A.get(b)<A.get(a))
						Collections.swap(A, a, b);
	}
}