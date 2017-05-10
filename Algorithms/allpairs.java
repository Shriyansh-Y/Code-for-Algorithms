/*
Problem Statement: 
All pairs shortest paths

Problem Definition:
In this problem, we will be dealing with a graph, that's defined by a collection of words, each word representing a vertex.
Words consist of lower-case letters, and there's an edge between two words if the they have the same length and differ at 
just one character position. The weight of the edge is how far apart the differing letters are in alphabetic order. 
Example, the words "help" and "yelp" would be connected by an edge of weight 17 (since 'y' is 17 characters away from 'h' 
in the alphabet). There wouldn't be an edge between "ram" and "arm"; although they contain the same letters, they differ 
in the first character position and in the second.

The program will read a list of words and compute the shortest path matrix. A simple statistic based on the matrix computed as follows:
For each word, w, compute the number of other words one can reach starting from wi and traversing edges of the graph. 
Computing the average number of reachable words, averaged over all words in the list, we get this value (rounded to two fractional digits).

The program will respond to a sequence of queries. Each querry will give a pair of words from the list. In response, the program 
will print out the length of the shortest path from the first word to the second one, followed by the sequence of words on one 
such shortest path. If there isn't a path, it will print the two words, followed by "not reachable". 

The shortest path is discovered by using a recursive function print_shortest_path() thereby implementing a linear time technique instead.

Implementation:
The input file starts with a positive integer n (stored in number_of_words), giving the number of words on the list. 
It be followed by n words, one per line. Words will consist only of lower-case letters, but they may be of different lengths.
The word list is followed by a non-negative integer, m, stored in number_of_queries giving the number of queries. This will be followed 
by m lines, each line containing two words from the list.

Functions (methods) used:
1. compare(): Used to compare if the input doesn't contain words of different lengths.
2. calculate_weight(): Used to calculate the edge weight i.e. the value equal to the difference
					   between the two words differing in one letter at one position only.
3. display(): Displays the required output.
4. print_shortest_path(): Prints the shortest path recursively.

In Windows system command prompt:
Execution:

> javac allpairs.java
> java allpairs input_file_name

Author: Shriyansh Yadav
Algorithm Reference: Introduction to Algorithms, 3rd Edition– Cormen, Leiserson, Rivest and Stein
Programming Language: Java

*/

import java.util.*;
import java.io.*;

class allpairs
{
	public static ArrayList <String> words;			// Stores all the words
	public static ArrayList <String> source;		// Stores the start word of the queries
	public static ArrayList <String> end;			// Stores the destination word of the queries
	public static int weight;
	public static int index;
	public static int count;	
	public static int e[][];						// Matrix that stores edge values
	public static int weights[][];					// Matrix that stores shortest path between each of the vertices.
	public static int next[][];						// Matrix to keep a record of value k that achieves shortest path between each pair of vertices 
	public static int number_of_words;
	public static int number_of_queries;

	public static void main(String args[])	throws IOException
	{
		if (0 < args.length) 
		{
			String filename = args[0];
			Scanner s = new Scanner(new File(filename));
			ArrayList <Integer> A = new ArrayList <Integer>();
			ArrayList <String> B = new ArrayList <String>();
			
			//Reading the input file contents

			while (s.hasNextLine())
			{
				if(s.hasNextInt())
					A.add(Integer.parseInt(s.nextLine()));
				else
					B.add(s.nextLine());
			}
			
			int i,j,k,u,v;
			source = new ArrayList <String>();
			end = new ArrayList <String>();
			
			number_of_words=A.get(0);
			number_of_queries=A.get(1);
			
			words = new ArrayList <String>();
			e=new int[number_of_words][number_of_words];
			
			for(i=0;i<number_of_words;i++)			//Adding the words in an Arraylist 'words'
				words.add(B.get(i));
			
				
			for(i=number_of_words;i<B.size();i++)	// Storing start and destination word of the queries
			{
				String x = B.get(i);
				String[] elements = x.split(" ");				
				source.add(elements[0]);
				end.add(elements[1]);
			}

			
			// Calculate the Edge weights i.e. difference between the words differing in only one letter
			// at only one position
			
			for(i=0;i<number_of_words;i++)
			{
				weight=0;
				for(j=0;j<number_of_words;j++)
				{
					if(i==j)
						e[i][j]=0;
					else
					{
						if(compare(words.get(i),words.get(j))==1)
						{
							weight=calculate_weight(words.get(i),words.get(j));
							e[i][j]=weight;
						}
						else
							e[i][j]=999;
					}
				}
			}
			
			
			// Matrix used for implementing the Floyd-Warshall algorithm
			weights=new int[number_of_words][number_of_words];
			
			// Matrix used to keep a record of value k, that achieves shortest path between each pair of vertices 
			next=new int[number_of_words][number_of_words];
			
			
			//Initializing weights matrix
			for(i=0;i<number_of_words;i++)
				for(j=0;j<number_of_words;j++)
				{
					if(i==j)
						weights[i][j]=0;
					else
						weights[i][j]=e[i][j];
				}

			
			// Initializing next matrix
			for(i=0;i<number_of_words;i++)
				for(j=0;j<number_of_words;j++)
				{
					if (i==j || weights[i][j] == 999)
						next[i][j] =999;
					else
						next[i][j] =i;
				}		

		
			// Floyd Warshall Algorithm Implementation
			
			for(k=0;k<number_of_words;k++)
				for(i=0;i<number_of_words;i++)
					for(j=0;j<number_of_words;j++)
					{
						if(weights[i][j]>(weights[i][k]+weights[k][j]))
						{
							weights[i][j]=weights[i][k]+weights[k][j];
							next[i][j]=next[k][j];
						}
					}
			
			// Displays the required output
			display();
		}
	}
	
	// Printing the shortest path between the vertices/words using recursive function
	public static void print_shortest_path(int y, int z)
    {	
		if(y == z)
			System.out.print(words.get(y)+" ");
		else if (next[y][z] == 999)
			System.out.println(words.get(y)+" " + words.get(z)+" "+"not reachable");
		else
		{
			print_shortest_path(y, next[y][z]);
			System.out.print(words.get(z) +" ");
		}
	}
	
	
	//To compare if the query doesn't ask to use words of different lengths
	public static int compare(String i,String j)
	{
		String s1=i;
		String s2=j;
		if(s1.length()==s2.length())
			return 1;
		else
			return 0;
	}
			
	public static int calculate_weight(String i, String j)
	{
		int x;
		int count=0;
		int a1,a2;
		String s1=i;
		String s2=j;
		
		// Using the ASCII value of the letters to compare the words w.r.t. to each index
		for(x=0;x<s1.length();x++)
		{
			if(s1.charAt(x)!=s2.charAt(x))
			{
				count++;
				index=x;
			}
		}
		
		if(count==1)
		{
			a1=(int)s1.charAt(index);
			a2=(int)s2.charAt(index);
		}
		else
			return 999;
			
		// Returning the difference 	
		if(a1>a2)
			return (a1-a2);
		else
			return (a2-a1);
	}
	
	public static void display()
	{
		int i,j;
		
		// Calculating the number of not reachable pair of words
		for(i=0;i<number_of_words;i++)
				for(j=0;j<number_of_words;j++)
					if(weights[i][j]==999)
						count=count+1;
					
		// Calculating the number of words reachable from each of the vertices.		
		float pv=(number_of_words*number_of_words)-count;
		
		//Printing the value: the average number of reachable words, averaged over all words in the list.
		System.out.printf("%.2f\n",pv/number_of_words);		
		
		
		//Printing the shortest paths between the pair of words in the queries
		
		for(i=0;i<number_of_queries;i++)
		{
			String a=source.get(i);
			String b=end.get(i);
			int y=words.indexOf(a);
			int z=words.indexOf(b);
			if(weights[y][z]==999)
				System.out.print("");
			else
				System.out.print(weights[y][z] + " ");
				print_shortest_path(y,z);
				
			System.out.println();
		}
	}
}