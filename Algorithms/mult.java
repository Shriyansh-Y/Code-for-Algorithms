/*
Problem Statement: 
Matrix Chain Multiplication Problem


Implementation:
The input file contains the number of matrices, that are to be multiplied,
in the first line and the next (n+1) lines give the values as - the first number is the height 
of the first matrix and the next nn values are the height of the next matrices in the chain as 
well as they also denote the width of the previous matrix. This will continue upto the last 
value that gives the width of the last matrix.
This program solves the matrix chain multiplication problem and reports the least expensive way
to multiply them as a fully parenthesized product of matrices.

Functions (methods) used to solve the matrix chain multiplication:
1. matrix_chain_order().
2. print_parentheses() function will print the least expensive way to multiply them as a fully 
parenthesized product of matrices.

In Windows system command prompt:
Execution:

> javac mult.java
> java mult input_file_name

Author: Shriyansh Yadav
Algorithm Reference: Introduction to Algorithms, 3rd Edition– Cormen, Leiserson, Rivest and Stein
Programming Language: Java

*/

import java.util.*;
import java.io.*;

class mult
{
	static int c=0;
	public static void main(String args[]) throws IOException
	{
		if (0 < args.length) 
		{
			String filename = args[0];
			Scanner s = new Scanner(new File(filename));
			ArrayList <Integer> A = new ArrayList <Integer>();

			while (s.hasNextLine())
			{
				if(s.hasNextInt())
					A.add(Integer.parseInt(s.nextLine()));
				else
					s.next();
			}
			
			int n = A.get(0);				// number of matrices in the chain
			int i = 1;
			ArrayList <Integer> p = new ArrayList <Integer>();  // the arraylist p stores the given height (first matrix)
			while(i<A.size())									// and the widths of the next matrices in the chain
			{
				p.add(A.get(i));
				i++;
			}
			s.close();
			matrix_chain_order(p,n);		// function that decides the order in which matrices are to be multiplied
		}
	}
	
	public static void matrix_chain_order(ArrayList<Integer> p, int n)
	{	
		n=n+1;
		int[][] m = new int[n][n];		//The m[i][j] values give the costs of optimal solutions to subproblems
		int[][] s = new int[n-1][n];
		
		//s[i][j] gives value of k at which we split the product of matrices in an optimal parenthesization. In simple words,
		//s[i][j] equals a value k such that m[i][j] = m[i][k+1] + p(i-1)*p(k)*p(j).
		
		for(int y=0;y<n;y++)
		{
			m[0][y]=0;
			m[y][0]=0;
		}
	
		int i,j,k,l,q;
		
		for(i=1; i<n; i++)
		{
			m[i][i]=0;
		}
		
		for(l=2;l<n;l++)
		{
			for(i=1;i<n-l+1;i++)
			{
				j=i+l-1;
				m[i][j]=999999;
				for(k=i;k<=j-1;k++)
				{
					q = m[i][k] + m[k+1][j] + (p.get(i-1) * p.get(k) * p.get(j));
					if(q < m[i][j])
					{
						m[i][j]=q;
						s[i][j]=k;
					}
				}
			}
		}
		print_parentheses(s,1,n-1,n-1);
	}
	
	public static void print_parentheses(int[][] s, int i, int j, int n)	// Printing the parenthesization of the matrices 
	{
		if(i==j)
		{
			System.out.print(" M"+(i)+" ");	
			c++;
		}
		else
		{
			if(c!=0 && c!=n)				// conditions to avoid printing of extra parenthesis
				System.out.print(" ( ");
			
			print_parentheses(s,i,s[i][j],n);
			System.out.print(" * ");
			print_parentheses(s,s[i][j] + 1,j,n);
			
			if(c!=0 && c!=n)
				System.out.print(" ) ");		    
		}
	}
}
