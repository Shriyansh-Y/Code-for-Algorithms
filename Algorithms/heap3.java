//A ternary heap where every node can have three children; with a complete tree except for a
//last row that may be partially full from the left.
//The root node stored in element zero in an arraylist, its three children will be stored in 
//elements 1, 2, 3, and so on. 
//Functions that map from the index of a node to the index of its parent: parent(i)
//Functions that map from the index of a node to the index of each of its children: 
//left_child(i), center_child(i), right_child(i)

//Implementation:
//The heap is a min-heap, stored in an ArrayList (in Java). 
//Functions (methods) for manipulating the heap:
//1. An insert() function will add an arbitrary value to the heap.
//2. A remove_min() function will remove the smallest value and return it.
//In Windows system command prompt:
//Execution:
//> javac heap3.java
//> java heap3 input_file_name

//For getting output in a text file:
//> java heap3 input_file_name

//Author: Shriyansh Yadav
//Algorithm Reference: Introduction to Algorithms, 3rd Edition– Cormen, Leiserson, Rivest and Stein
//Programming Language: Java


import java.util.*;
import java.io.*;

class heap3
{
	public static void main(String args[]) throws IOException
	{
		if (0 < args.length) 
		{
			String filename = args[0];
			Scanner s = new Scanner(new File(filename));
			ArrayList <Integer> A = new ArrayList <Integer>();
			
			while(s.hasNextLine())
			{
                String x = s.nextLine();
                String[] steps = x.split(" ");
				if(steps[0].equals("add"))
				{	
					int key = Integer.parseInt(steps[1]);
					insert(A, key);
				}
                else if(steps[0].equals("remove"))
				{
					int minimum=remove_min(A);
					System.out.println(minimum);
				}
				else
				{
					System.out.println("Invalid command found");
					s.next();
				}
            }

			s.close();
		}
	}	
	
	public static void insert(ArrayList<Integer> A, int key)
	{
		A.add(key);
		int i = A.size()-1;
		while(A.get(parent(i))> A.get(i))
		{
			Collections.swap(A, i, parent(i));
			i=parent(i);
			if(i==0)
				break;
		}
	}

	public static void minheapify(ArrayList<Integer> A, int i)
	{
			int l= left_child(i);
			int c= center_child(i);
			int r= right_child(i);
			int smallest;
						
			if(l<=A.size()-1 && A.get(l)<A.get(i))
				smallest=l;
			else 
				smallest=i;	
			
			if(c<=A.size()-1 && A.get(c)<A.get(smallest))
				smallest=c;
			
			if(r<=A.size()-1 && A.get(r)<A.get(smallest))
				smallest=r;
			
			if(smallest!=i)
			{
				Collections.swap(A, i, smallest);
				minheapify(A,smallest);

			}
	}
	

	static int remove_min(ArrayList<Integer> A)
	{
		int min = A.get(0);
		int z = A.size() ;
		Collections.swap(A, 0, z-1);
		A.remove(z-1);
		minheapify(A,0);
		return min;
	}
		
	
	static int left_child(int i)
	{
		return ((3*i)+1);
	}

	static int center_child(int i)
	{
		return ((3*i)+2);
	}

	static int right_child(int i)
	{
		return ((3*i)+3);
	}

	static int parent(int i)
	{
		return ((i-1)/3);
	}		
}	