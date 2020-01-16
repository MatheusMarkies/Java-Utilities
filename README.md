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

**API mode: https://www.dropbox.com/s/kxuon3mivxsc2it/Java_Utilities1.4v.jar?dl=0**
ImageUtilities -->

```java
public class DINO_NeuronSimulation {
   
    public static void main(String[] args) {
        
               InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\Matheus Markies\\Desktop\\il_570xN.1649501246_g4qj.png"));
               
        BufferedImage bufi = ImageIO.read(is);

        Color mainColor = getImageMainColor(bufi);

        System.out.println("Main Color: " + mainColor);

        ArrayList<PatternPixelSet> pattern = imagePatternCreate.createImageBorderPattern(bufi, mainColor);

        BufferedImage bImg = new BufferedImage(bufi.getWidth() + 1, bufi.getHeight() + 1, BufferedImage.TYPE_INT_BGR);
        for (PatternPixelSet pps : pattern) {

            System.out.println(pps.getType() + " | line: " + pps.getLine() + " Color: " + pps.getColor());
           bImg.setRGB(pps.getPixelWidth(), pps.getPixelHeight(), pps.getColor().getRGB());
        }
        File file = new File("Fii" + ".png");
        ImageIO.write(bImg, "png", file);

    ArrayList<BufferedImage> io = imageTreatment.frameAnallyzer(mainColor, bufi);
      
    ArrayList<Color> FramesMainColor = new ArrayList<>();
    
    for(int t =0;t<io.size();t++)
    FramesMainColor.add(getImageMainColor(io.get(t)));

    System.out.println("Recognizer...");
    
    String result = imageRecognizer.getImageQuadrantWithTolerance(FramesMainColor, mainColor, 20,true,false,false,false);
    System.out.println(result);
    
    }
    
}
```

*Current version: 1.4v*

**By Matheus Markies**
