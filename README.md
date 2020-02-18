### **Java Utilities:**

 **Advanced features to accelerate JAVA project development.**


## Resources:

**-Tools for Creating, Reading, Writing TXT files (Package:TXT_Utilities).**

- -**IA for handling TXT files with features:**

-    -Search words (Return: Word line (String) and line number (int)).

-    -Get number of lines from a TXT file (Return: int).


**-Hardware Resources**

-    -New Device Detector.

**-Create new encrypted file extensions**

## How to use:

**API: https://www.dropbox.com/s/hslvbo0hjs3mn1d/JavaUt%201.7v.jar?dl=0**

*(ImageUtilities)*

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

[![Image Treatment Test!](http://img.youtube.com/vi/dWkSWJJ7J4c/0.jpg)](https://www.youtube.com/watch?v=dWkSWJJ7J4c "Little red riding hood - Click to Watch!")

*Current version: 1.6v*

**By Matheus Markies**
