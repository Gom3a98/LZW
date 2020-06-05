package lzw.project;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Compresser {
	private String text ;
	private ArrayList<Integer>tags ;

	
 Compresser()
	{
		text = "";
		tags =  new ArrayList<>();
	}
 Compresser(String x)
 {
	 text =x ;
	tags =  new ArrayList<>();
 }
 
 
 public void Compress() 
 {
	ArrayList<String> Dictionary = new ArrayList<>();
	
        for (int i =0 ;i<128;i++)
        {
            Dictionary.add(String.valueOf((char)i));
        }

    
		int pointer=0;
		
		String sympol="";
                char [] textAsChars = text.toCharArray();
		while (pointer<textAsChars.length)
		{
                    
			
                    String buffer = sympol + textAsChars[pointer];
			
			if (Dictionary.contains(buffer))
			{
                            sympol =buffer;
                        }
			else
                        {
			
                        Dictionary.add(buffer);
                       	tags.add(Dictionary.indexOf(sympol));
                        sympol = "" + textAsChars[pointer];
			}
                        pointer++;
						
		}
                 if (!sympol.equals(""))
                      tags.add(Dictionary.indexOf(sympol));
				
}
        
		

 
 public String GetTags()
 {
     String t ="";
     for (int j : tags)
     {
         
         t+="<";t+=String.valueOf(j);t+=">";
     }
	return t; 
 }
 
 public void read_text_File(String path) throws IOException
 {
	    File file =  new File(path); 
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine())
                    text+=sc.nextLine();
                
                
            }
	  
 }

 public void MainProccess(String path)
 {
	 try
	 {
	 this.read_text_File(path);
	 }
	 catch(IOException e)
	 {
		 System.out.println("File not Found in this path");
	 }
	 
	 try
	 {
		 this.Compress();
	 }
	 catch(Exception e)
	 {
		 System.out.println("All of things is handled... :D");
	 }
	 try
	 {
	 this.WriteOnCompressedFile();
	 }
	 
	 catch(IOException e)
	 {
		 System.out.println("Error in writng to File");
	 }
	 
 }
 
 public void WriteOnCompressedFile()  throws IOException 
 {
		    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("compressed_File.txt"))) {
                for (Integer i : tags)
                {
                    
                    writer.write("<"+i+">");
                }    }
}

 public void PrintTags()
{
	for (int obj : tags)
	{
		System.out.println("<"+obj+">");
	}
}

}