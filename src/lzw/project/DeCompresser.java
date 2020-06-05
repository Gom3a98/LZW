package lzw.project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeCompresser {
        
    private String text ;
    private ArrayList<Integer>tags = new ArrayList<>() ;
    
    public DeCompresser()
	{
		text = "";
	}
   public DeCompresser(String t)
	{
   
         String []temp;
         temp = t.split(">");
         for (int i =0 ;i<temp.length;i++)
         {
            tags.add(Integer.parseInt(temp[i].substring(1,temp[i].length())));
         }
	}
		
        
	public void ReadTagsFromFile(String Path) throws IOException
	{

         BufferedReader reader = new BufferedReader(new FileReader(Path));
         
         String buffer = reader.readLine();
       
         String []temp;
         temp = buffer.split(">");
         for (int i =0 ;i<temp.length;i++)
         {
            tags.add(Integer.parseInt(temp[i].substring(1,temp[i].length())));
         }
         reader.close();
	}

	
	public void DeCompressProcess(String path)
	{
		 try
		 {
		 this.ReadTagsFromFile(path);
		 }
		 catch(Exception e)
		 {
			 System.out.println("File not Found in this path");
		 }
		 try
		 {
			 
		 this.De_Compress();

		 }
		 catch(Exception e)
		 {
			 System.out.println("Error in DeCompression");
		 }
		 try
		 {

		 this.WriteTextToFile();
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println("Error in writng to File");
		 }
		 System.out.println("Done .... Congrats :D");

		
	}
	
        
        public String GetText()
	{
		return text;
	}
	
        public void WriteTextToFile() throws IOException{
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Original.txt"));
	    writer.write(GetText());
	    writer.close();

	}
        
        public  void De_Compress() {


        int dictSize = 128;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        
        for (int i = 0; i < 128; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(int)tags.remove(0);
        String result = w;
        //BAABABBA
        
        for (int Item : tags) {
            String buffer;
            if (dictionary.containsKey(Item))
                buffer = dictionary.get(Item);
            else if (Item == dictSize)
                buffer = w + w.charAt(0);
            else
            {
                throw new IllegalArgumentException("Bad compressed Item: " + Item);
            }
 
            result+=(buffer);
 
            dictionary.put(dictSize, w + buffer.charAt(0));
            dictSize++;
            w = buffer;
        }
        text = result;
    }
    
}
