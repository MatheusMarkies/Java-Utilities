/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.ImageUtilities;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 *
 * @author Matheus Markies
 */
public class ImagePattern {
    
    public static void main(String[] args) throws FileNotFoundException, AWTException, IOException {
 
    InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\Matheus Markies\\Desktop\\tt.png"));
    BufferedImage bufi = ImageIO.read(is);
    
    Color mainColor = getImageMainColor(bufi);
    
    System.out.println("Main Color: "+mainColor);
        
    ArrayList<BufferedImage> io = Reconizer.frameAnallyzer(mainColor, bufi);
        
    }
        
    public static Color getImageMainColor(BufferedImage ImageBase_) throws IOException{
        
        Color mainColor = null;
        
        ArrayList<Color> ImageColorList = new ArrayList<>();
        Set<Color> colorList = new HashSet<>();
        
        int U = ImageBase_.getWidth();
        int V = ImageBase_.getHeight();
        
        for(int h =0;h<V;h++)
        for(int w = 0;w<U;w++){
        Color color = new Color(ImageBase_.getRGB( w, h ));
        ImageColorList.add(color);
        colorList.add(color);
        }
        
        colorList = deleteExtremes(colorList);
        mainColor = compareColors(hashSetToArray(colorList), ImageColorList);
        
        return mainColor;
    }
    static ArrayList<Color> getRepeatedColor(ArrayList<Color> listl,boolean ignoreExtremes){
      
        System.out.println("Old: "+listl.size());
        
        ArrayList<Color> ListColors = listl;
        ArrayList<Color> newArray = new ArrayList<>();
        
        for(int cord = 0;cord<ListColors.size();cord++)
        for(int c = 0;c<ListColors.size();c++){
        if(ListColors.get(c) == ListColors.get(cord))
        if(c>cord)
        ListColors.remove(cord);
        }

    if(ignoreExtremes){
          
         boolean Clean = false;

         while (!Clean) {
            
        boolean Check = false;
             
          for(int c =0;c<ListColors.size();c++){
              
          if(ListColors.get(c).getAlpha() == 0){
          ListColors.remove(c);
          System.out.println("Removed");
          }
          if(ListColors.get(c).getBlue() == 255&&ListColors.get(c).getGreen() == 255&&ListColors.get(c).getRed() == 255){
          ListColors.remove(c);
          System.out.println("Removed");
          }
          if(ListColors.get(c).getBlue() == 0&&ListColors.get(c).getGreen() == 0&&ListColors.get(c).getRed() == 0){
          ListColors.remove(c);
          System.out.println("Removed");
          }

         }

          for(int c =0;c<ListColors.size();c++){
              
          System.out.println("Color: "+ListColors.get(c));
              
          if(ListColors.get(c).getAlpha() == 0){
          Check = true;
          }
          if(ListColors.get(c).getBlue() == 255&&ListColors.get(c).getGreen() == 255&&ListColors.get(c).getRed() == 255){
          Check = true;
          }
          if(ListColors.get(c).getBlue() == 0&&ListColors.get(c).getGreen() == 0&&ListColors.get(c).getRed() == 0){
          Check = true;
          }

         }
         
        if(!Check)
        Clean = false;
        break;
        }
        
    }
          
    newArray = ListColors;
    System.out.println("New: "+ListColors.size());
        System.out.println(ListColors);
    return newArray;
    }
    static Set<Color> deleteExtremes(Set<Color> hasset_){
     
        Set<Color> NewHash = new HashSet<>();
        
        for(Color color : hasset_){
        boolean Check = false;
        if(color.getAlpha() == 0){
          Check = true;
          }
          if(color.getBlue() == 255&&color.getGreen() == 255&&color.getRed() == 255){
          Check = true;
          }
          if(color.getBlue() == 0&&color.getGreen() == 0&&color.getRed() == 0){
          Check = true;
          }  
        if(!Check)
        NewHash.add(color);
        }
        
     return NewHash;
    }
    static Color compareColors(ArrayList<Color> colors,ArrayList<Color> imageColors){
        
        Color mainColor = null;
        
        int mostRepeated = 0;
        
        for(int t = 0;t<colors.size();t++){
        int repeated = 0;
            
            for(Color color : imageColors)
            if(color == colors.get(t))
            repeated ++;
                
        if(t == 0){
        mostRepeated = repeated;
        }
        if(repeated >= mostRepeated){
        mainColor = colors.get(t);
        mostRepeated = repeated;
        }
        
        }
        
        return mainColor;
    }
    static ArrayList<Color> hashSetToArray(Set<Color> hash){
        
        ArrayList<Color> newArray = new ArrayList<>();
        
        for(Color color : hash)
        newArray.add(color);
            
        return newArray;
    }

    static class Reconizer extends ImagePattern{
    
        static ArrayList<BufferedImage> frameAnallyzer(Color mainColor,BufferedImage in) throws AWTException, IOException{
            
            ArrayList<BufferedImage> newArray = new ArrayList<>();
            
               Robot robot = new Robot();
               BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));   
            
               BufferedImage bufi = in;
               
            ArrayList<BufferedImage> imagesToAnalizer = divideImages(bi, bufi);
             for (int n = 0; n < imagesToAnalizer.size(); n++) {
                String Name = "Image" + n;
                File file = new File(Name + ".png");

                int U = imagesToAnalizer.get(n).getWidth();
                int V = imagesToAnalizer.get(n).getHeight();

                for (int h = 0; h < V; h++) {
                    for (int w = 0; w < U; w++) {
                        Color color = new Color(imagesToAnalizer.get(n).getRGB(w, h));
                        //System.out.println(color);
                    }
                }
                //ImageIO.write(imagesToAnalizer.get(n), "png", file);
            }
            
            return newArray;
        }
        
        static ArrayList<BufferedImage> divideImages(BufferedImage bigImage,BufferedImage SmallImage) {

            ArrayList<BufferedImage> bs = new ArrayList<BufferedImage>();
            BufferedImage smallImage = null;
            
            //if(SmallImage.getWidth() < bigImage.getWidth() && SmallImage.getHeight()< bigImage.getHeight())
            smallImage = SmallImage;
            //else
            //smallImage = resizeImage(SmallImage, 570, 532);
            
             float sizeFactorU = bigImage.getWidth()/smallImage.getWidth();
             float sizeFactorV = bigImage.getHeight()/smallImage.getHeight();
             
             int Y = 0;
             int X = 0;
             
             int MinX = 0;
             int MinY = 0;
             
             int images = (int)sizeFactorU * (int)sizeFactorV;
             
             for(int i = 0;i<images;i++){
             BufferedImage bImg = new BufferedImage(smallImage.getWidth(),smallImage.getHeight(),BufferedImage.TYPE_INT_BGR);
             for(int y =0;y<bigImage.getHeight();y++){
             
             for(int x = 0;x<bigImage.getWidth();x++){
 
             if(x < MinX && y < MinY){
             int Xcord = 0;
             int Ycord = 0;
             if(x < smallImage.getWidth())
             Xcord = x;   
             else{
             Xcord = x-smallImage.getWidth()*X;
             }
             if(y < smallImage.getHeight()){
             Ycord = y;   
             }else{
             Ycord = y-smallImage.getHeight()*Y;
             }
             //System.out.println(bigImage.getRGB(x, y));
             bImg.setRGB(Xcord, Ycord, bigImage.getRGB(x, y));
             }//if
             }//For X
             
             }//For Y
             
             if(Y < (int)sizeFactorV){
             Y+=1;
             MinY = (int)sizeFactorU * Y;           
             }
             if(X < (int)sizeFactorU){
             X+=1;
             System.out.println("Scale: "+(int)sizeFactorU);
             MinX = (int)sizeFactorU * X;
             }
             System.out.println("Min: "+MinX);
             
             bs.add(bImg);
             }//For Images
             
        return bs;
        }
    
        static BufferedImage resizeImage(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
  
  }
    
}
