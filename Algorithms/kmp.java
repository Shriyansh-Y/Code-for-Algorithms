/*
Problem Statement: 
KMP and Performance evaluation

Problem Definition:
Compare string searching algorithms, including the standard string search mechanism for the
chosen programming language. This will compare the performance of the naive, O(nm) string search
algorithm, the KMP string search algorithm and the standard string search technique.
In Java, we'll say it's the indexOf() method on String, that can be considered as the standard search.

Program will take one optional command-line argument giving the name of a file, which should
contain two lines, the first giving the text supposed to be search through (the haystack) and the
second containing the pattern supposed to be look for (the needle). 
If no command-line argument is given, program uses needle and haystack strings designed
by you.

Functions (methods) used:
1. naive(): Performs naive search 
2. kmp(P,T): Performs KMP search 
 
In Windows system command prompt:
Execution:

> javac kmp.java
> java kmp input_file_name  OR  
> java kmp (This generates random strings for haystack and needle so everytime the result will be different) 
Author: Shriyansh Yadav
Algorithm Reference: 
1. Introduction to Algorithms, 3rd Edition– Cormen, Leiserson, Rivest and Stein

Programming Language: Java

*/


import java.util.*;
import java.util.Random;
import java.io.*;

class kmp
{
	public static String haystack;
	public static String needle;
	public static int pi[];
	public static ArrayList <String> A;
	public static StringBuilder x;
	
	public static void main(String args[])	throws IOException
	{			
		if (0 < args.length) 
		{
			String filename = args[0];
			Scanner s = new Scanner(new File(filename));		
			
			A = new ArrayList <String>();
			
			while (s.hasNextLine())
					A.add(s.nextLine());
			
			int i,j,k,u,v;
		
			haystack=A.get(0);
			needle=A.get(1);
		}
		else
		{
			x = new StringBuilder();
			Random r= new Random();
			String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			for(int l=0;l<9999999;l++)
			{
					x=x.append(alphabet.charAt(r.nextInt(alphabet.length())));
			}
			haystack="";
			haystack=x.toString();
			
			//Manipulating the range of generation of string region and string length
			int Low_a = 2500000;
			int High_a = 7500000;
			int a = r.nextInt(High_a-Low_a) + Low_a;
			int Low_b = 10;
			int High_b = 30;
			int b = r.nextInt(High_b-Low_b) + Low_b;
			needle="";
			needle=x.substring(a,a+b);
		}
			
			//Step 1:
			//Implementing the naive search technique
			long t0 = getMilliseconds();
			int b =naive();
			long t1 = getMilliseconds();
			
			if(b!=0)
			{
				System.out.print(" found at: " + b + "\n naive search time: ");
				System.err.println( t1 - t0 );
			}


			//Step 2:
			//Implementing the standard search technique
			long t2 = getMilliseconds();
			int a= haystack.indexOf(needle);
			long t3 = getMilliseconds();
			System.out.print(" found at: " + a + "\n standard search time: ");
			System.err.println( t3 - t2 );
			
			
			// Step 3:
			////Implementing the kmp search technique
			String P="0"+needle;
			String T="0"+haystack;
			pi=new int[haystack.length()+1];
			long t4 = getMilliseconds();
			int c =kmp(P,T);
			long t5 = getMilliseconds();
			
			if(c!=0)
			{
				System.out.print(" found at: " + c + "\n kmp search time: ");
				System.err.println( t5 - t4 );
			}
	}
	
	
	public static int naive()
	{
		int n=haystack.length();
		int m=needle.length();
		int flag=0;
		int s,x;
		
		for(s=0;s<n-m;s++)
		{
			for(x=0;x<m;x++)
				if(needle.charAt(x)!=haystack.charAt(s+x))
					break;
			if(x==m)
				return s;
		}
		return -1;
	}
	
	public static int kmp(String P, String T)
	{
		int n=haystack.length();
		int m=needle.length();
		pi = compute_prefix_function(P);
		int i;
		int q=0;
		for(i=1;i<=n;i++)
		{
			while(q>0 && P.charAt(q+1)!=T.charAt(i))
				q=pi[q];
			if(P.charAt(q+1)==T.charAt(i))
				q=q+1;
			if(q==m)
			{
				return (i-q);
			}
		}
		return 0;
	}
	
	
	// Computing the prefix function
	public static int[] compute_prefix_function(String P)
	{
		int m=P.length();
		pi[0]=0;
		pi[1]=0;
		int q,k=0;
		for(q=2;q<m;q++)
		{
			while(k>0 && P.charAt(k+1)!=P.charAt(q))
				k=pi[k];
			
			if(P.charAt(k+1)==P.charAt(q))
				k=k+1;
			pi[q]=k;
		}
		return pi;
	}
	
	public static long getMilliseconds() 
	{
		return System.currentTimeMillis();
	}	
}
