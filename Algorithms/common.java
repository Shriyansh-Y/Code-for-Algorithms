/*
A program that counts the number of different strings that are substrings of both of two input 
strings. For example,"f", "ab" and "cde" are all substrings of the two strings below. The "ab" substring occurs twice in the
second string, but is counted only once.

String 1= abcdefg
String 2= cdefabab
*/

import java.util.*;
class common
{
public static void main(String args[])
{	//int counti=0;									
	//int countj=0;								
	//int countk=0;
	//int countz=0;
	//Testing loop run length and complexity of algorithm is done using counti,countj,countz,countk;
	
	Scanner sc=new Scanner(System.in);
	String s1=new String();
	String s2=new String();
	String temp1=new String();	
	String q=new String();
	ArrayList <String> myString= new ArrayList <String>();
	int i,j,temp;
	int count=0;
	int outcome;
	
	System.out.println("Enter the first string:");  // Taking Inputs: 2 strings in lower case 
	s1=sc.next();
	s1=s1.toLowerCase();
	System.out.println("Enter the second string:");
	s2=sc.next();
	s2=s2.toLowerCase();
	int m=s1.length();				// Calculating length of input strings
	int n=s2.length();
	
	if(n>m)						//Making sure the loop structure runs without "array index out of bounds" error
	{  
	temp=n;
	n=m;
	m=temp;
	}
	
	for(i=0;i<m;i++)
	{     
		//counti++;
		for(j=i+1;j<=n;j++)
		{
			//countj++;;
			temp1=s1.substring(i,j);
			int p=temp1.length();
			int c=0;
			while((c+p)<=n)
			{	
				//countz++;
				q=s2.substring(c,c+p);
				if(temp1.equals(q) && !(myString.contains(q)))	// Checking the presence of matching string along
				{						// with avoiding any duplicate string possibility 
					myString.add(q);
					//countk++;				// Adding matching unique substrings to list
					break;
				}
				c++;  
			}
		}		
	}	
	//System.out.println( counti +"  "+ countj +"  "+ countz +"  "+ countk);
	System.out.println("\n\nTotal matching substrings: " + myString.size()); // Printing the actual count of matching substrings 
	}
}