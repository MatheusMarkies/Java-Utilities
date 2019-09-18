### **Java Utilities:**

 **Advanced features to accelerate JAVA project development.**


## Resources:

**-Tools for Creating, Reading, Writing TXT files (Package:TXT_Utilities).**

- -**IA for handling TXT files with features:**

-    -Search words (Return: Word line (String) and line number (int)).

-    -Get number of lines from a TXT file (Return: int).


**-JavaFX Features.**

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

    static File file;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    TXT_Utilities.Create_TXT.Create_TXT_File(file);
    
    }
    
}
```

*Current version: 1.4v*

**By Matheus Markies**
