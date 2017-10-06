import java.io.*;
import java.util.*;

class Tian{
	public static void main(String[] args) {

		//Read the file
		BTree t = readFile();
		/*t.view();*/
		Scanner in = new Scanner(System.in);
		String input = "...";

		while(true){
			System.out.println("Which word you wanna search?");
			input = in.next();
			if(input.compareTo("q")==0) break;
			if(t.contains(input)) System.out.println("The word exist");
			else{
				System.out.println("The word do not exist in our dictionary");
				spellCheck1(t,input);
				spellCheck2(t,input);
				spellCheck3(t,input);
				spellCheck4(t,input);
			}
		}
		int d=t.depth();
		long sum=0;
		System.out.println("Depth = " + d);
		for(int i=0;i<d;i++){
			long help = t.count(i);
			sum+=help*i;
			System.out.println("Level " + i + " contains " + help + " nodes.");
		}
		System.out.println("Average depth is: " + (double)sum/t.size());
		System.out.println("The first word is: " + t.first() + " and the last is " + t.last());
		//System.out.println(t.size());
	}

	public static BTree readFile(){
		BTree t = new BTree();
	 	try{
		  FileInputStream fstream = new FileInputStream("dictionary.txt");
		  DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  while ((strLine = br.readLine()) != null){
			  t.insert(strLine);
		  }
		  in.close();
		}
		catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		}
		
		return t;
	}

	public static void spellCheck1(BTree t,String w){ //O(n) -> n=lenght of word
		char[] charArray = w.toCharArray();
		char s; //swap
		for(int i=0;i<charArray.length-1;i++){
			s = charArray[i];
			charArray[i] = charArray[i+1];
			charArray[i+1]=s;
			//System.out.println(charArray);
			String ss = new String(charArray);
			if(t.contains(ss)) System.out.println(ss + " exist");
			s = charArray[i];
			charArray[i] = charArray[i+1];
			charArray[i+1]=s;
		}
		//System.out.println("spellCheck1 ended");
	}
	public static void spellCheck2(BTree t,String w){ //26*L -> O(n)
		char[] charArray = w.toCharArray();
		char s;
		for(int i=0;i<charArray.length;i++){
			s=charArray[i];
			for(int j=0;j<26;j++){
				int lol = 'a' + j;
				charArray[i]=(char) lol;
				String ss = new String(charArray);
				//System.out.println(ss);
				if(t.contains(ss)) System.out.println(ss + " exist");
			}
		charArray[i]=s;	
		}
	//System.out.println("spellCheck2 ended");
	}
	public static void spellCheck3(BTree t,String w){ //26*L -> O(n)
		char[] charArray = w.toCharArray();
		int nl=charArray.length+1;
		for(int i=0;i<nl;i++){
			char[] newArray = new char[nl];
			int h=0; //iterate over new array

			while(i!=h){newArray[h]= charArray[h]; h++;} //before the new char
			newArray[h] = 'a';
			h++;
			while(h<nl){newArray[h]=charArray[h-1];h++;} //after the new char

			for(int j=0;j<26;j++){
				int lol = 'a' + j;
				newArray[i]=(char) lol;
				String ss = new String(newArray);
				//System.out.println(ss);
				if(t.contains(ss)) System.out.println(ss + " exist");
			}
		}
	//System.out.println("spellCheck3 ended");
	}

	public static void spellCheck4(BTree t,String w){ // O(n)
		char[] charArray = w.toCharArray();
		int nl=charArray.length-1;
		for(int i=0;i<charArray.length;i++){
			char[] newArray = new char[nl];
			int h=0; //iterate over new array

			while(i!=h){newArray[h]= charArray[h]; h++;} //before the new char
			while(h<nl){newArray[h]=charArray[h+1];h++;} //after the new char

			String ss = new String(newArray);
			//System.out.println(ss);
			if(t.contains(ss)) System.out.println(ss + " exist");
		}
	//System.out.println("spellCheck4 ended");
	}


}