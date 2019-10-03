### **Java Utilities:**

 **Advanced features to accelerate JAVA project development.**


## Resources:

**-Tools for Creating, Reading, Writing TXT files (Package:TXT_Utilities).**

- -**IA for handling TXT files with features:**

-    -Search words (Return: Word line (String) and line number (int)).

-    -Get number of lines from a TXT file (Return: int).


**-JavaFX Features.**


**-Hardware Resources**

-    -New Device Detector.

**-Create new encrypted file extensions**

## How to use:

**Copy lines or scripts and add to your project.**

*(TXT --> file (TXT FILE ONLY))*

Exemple: (Create TXT)
    
```java
static File TXT_File = new File("\\File.txt"); // file directory + file
    
    public static void main(String[] args) {
        
        Create_TXT_File(TXT_File); //Call method from Java_Utilities
        
    }
    
    public static void Create_TXT_File(File file){  //Method copied from Java_Utilities (TXT_Utilities)
        
        File TXT_File = new File("FilePatch\\FileName.txt");
        
        TXT_File = file;
        
            try {
            TXT_File.createNewFile();
            FileWriter IndexWriter = null;
            IndexWriter = new FileWriter(TXT_File.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(IndexWriter);
            
            //bw.write("Write...");
            bw.close();  
            
             } catch (IOException ex) {
                 
             }
        
    }
    
    
}
```

**API mode: https://www.dropbox.com/s/9f6normcwz9jc9s/Java_Utilities.jar?dl=0**

```java
public class DINO_NeuronSimulation {

    static File file = new File("FilePatch\\FileName.txt");
   
    public static void main(String[] args) {
        
        File File = = new File("FilePatch\\FileName.txt");
        
        int index = TXTUtilities.TXTIA.GetTXTLineIndex.Get_TXT_File_LineIndex(File);
        
        ArrayList<String> content = TXTUtilities.ReadTXT.Read_TXT_File(File, index);
        
        String[] Content_Array = new String[content.size()];
        
        TXTUtilities.CreateTXT.CreateTXTFile(file);
        
        for(int i = 0;i<content.size();i++){
        Content_Array[i] = content.get(i);//Convert array to list
        }
        
        TXTUtilities.WriteTXT.Write_Array_TXT_File(file, Content_Array);
    
    }
    
}
```

*Current version: 1.4v*

**By Matheus Markies**
