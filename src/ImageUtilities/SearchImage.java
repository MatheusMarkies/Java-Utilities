/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageUtilities;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Matheus Markies
 */
public class SearchImage {
    
    static ArrayList<Color> ArrayImageBase = new ArrayList<Color>();
    static ArrayList<Color> ArrayImageFrame = new ArrayList<Color>();
    
    //static int AREA_Img_A;
    //static int AREA_Img_B;
    static int prop;
    
    static int tolerance = 50;//50%
    
    public static Color c = null;
    public static Color cA = null;
    
    public static boolean isPixel = false;
    
    static ArrayList<ArrayList<Color>> ArrayListF = new ArrayList<ArrayList<Color>>();
               
    static ArrayList<Integer> ArrayStartPixelWidthMin = new ArrayList<Integer>();
    static ArrayList<Integer> ArrayStartPixelWidthMax = new ArrayList<Integer>();
               
    static ArrayList<Integer> ArrayStartPixelHeightMin = new ArrayList<Integer>();
    static ArrayList<Integer> ArrayStartPixelHeightMax = new ArrayList<Integer>();
    
    static int IndexRep = 3;
    static int ListIndex = 0;
    static int a = 1;
    static int b = 0;
               
    static int ui = 0;
               
    static int heightIndex = 0;
    
    static int LengthX;
    static int LengthY;
    
    public static void ImageFinder_NotUse_InBuilder(InputStream ImageBase) throws AWTException {
        
        //new Processor_Image();
        
        	try {
               //InputStream isInput = new FileInputStream(Img_Mouse_Suspect);		
	       BufferedImage bufi = ImageIO.read(ImageBase);
                
                int g = bufi.getHeight() - 10;
                int y = bufi.getWidth() - 10;
		for ( int i = 0; i < y; i++ ) {
		 for ( int j = 0; j < g; j++ ) {
                            
			c = new Color( bufi.getRGB( i, j ) );
                               
			ArrayImageBase.add(c);
                        //System.out.println(c.toString());
                                //c.getRGB()
			}
			//System.out.println();
		}
	      // System.out.println(ArrayImageBase);
               
               Robot robot = new Robot();
               BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));     
               
               //AREA_Img_A = bufi.getHeight()* bufi.getWidth();
               //AREA_Img_B = bi.getHeight() * bi.getWidth();
               
               //ArrayListF.add(new ArrayList<Color>());
               
               ArrayStartPixelWidthMin.add(0);
               ArrayStartPixelWidthMax.add(0);
               
               ArrayStartPixelHeightMin.add(0);
               ArrayStartPixelHeightMax.add(0);
               
               for(int e =0;e<(bi.getHeight()-1);e++){
                  if(e > 0){
                  heightIndex += 1;
                  ListIndex = 0;
                  }
                  ui = 0;
                for(int i =0;i<(bi.getWidth() - 1);i++){   
                    
                    //X = i | Y = e
                    
                    LengthX = i;
                    LengthY = y;
                    
                    //System.out.println("X "+i);
                    //System.out.println("Y "+e);
                    cA = new Color( bi.getRGB( i, e ) );
                        
                      //if(ArrayImageBase.get(r).getRed() == cA.getRed() && ArrayImageBase.get(r).getGreen() == cA.getGreen() && ArrayImageBase.get(r).getBlue() == cA.getBlue()){
                        //System.out.println("ArrayInd "+ ArrayImageBase.get(r).getRed() +" "+ArrayImageBase.get(r).getGreen()+" "+ArrayImageBase.get(r).getBlue());
                        //System.out.println("CA "+ cA.getRed() +" "+cA.getGreen()+" "+cA.getBlue());
                        //isPixel = true;
                        //break;
                      //}
                      
                   ImageComparador rc = new ImageComparador();
                   Thread t = new Thread(rc);
                   t.start();

                   if(isPixel){
                       
                   //System.out.println("IsPixel");
                   int j = ListIndex + 1;

                    //Create array or add index in array
                    //System.out.println("X "+i);
                    //System.out.println("Y "+e);
                    //System.out.println("List "+ListIndex);
                    //System.out.println("Arrays "+ArrayListF.size());
                    
                   boolean CreateNew = false;
                    
                   if(ArrayListF.size() == 0){
                       CreateArrays();
                   }
                   
                    if(IndexRep > 1){
                        
                        for(int l = 0;l<ArrayListF.size();l++){
                        int MinDist = i+2;
                            if(ArrayStartPixelWidthMin.get(l) <= MinDist){
                              if(ArrayStartPixelHeightMax.get(l) < e){
                              ListIndex = l;
                              CreateNew = false;
                              }else{
                               CreateNew = true;
                              }
                            }else{
                              CreateNew = true;
                            }
                            
                        }
                        
                        if(CreateNew){
                        CreateArrays();
                        }
                        
                    }
                         
                    ArrayListF.get(ListIndex).add(cA);
                    
                    if(LengthX < ArrayStartPixelWidthMin.get(ListIndex)){
                    ArrayStartPixelWidthMin.set(ListIndex, LengthX);
                    }
                    
                    if(ListIndex < ArrayStartPixelHeightMax.size()){
                    ArrayStartPixelHeightMax.set(ListIndex, LengthY);
                    }
                    
                    b = 0;
                    
                    IndexRep = 0;
                    isPixel = false;
                    a = 0;
                    }else{
                    IndexRep += 1;
                    }

                    if(IndexRep > 0){
                      if(b == 0){
                          if(LengthX > ArrayStartPixelWidthMax.get(ListIndex)){
                          ArrayStartPixelWidthMax.set(ListIndex,LengthX);
                          }
                          b = 1;
                      }
                    }
                   
                    //System.out.println("Indice: "+ IndexRep);
                    
                    if(ui > 0 && IndexRep > 0){
                    //break;
                    }
                    
                    
                    //System.out.println(i+"X"+e);
                    //Processor_Image.SetXY(i, e);
                
              for(int l = 0;l<ArrayStartPixelWidthMin.size();l++){
              
              if(ArrayStartPixelWidthMin.get(l)>i){
              int DistanceX = ArrayStartPixelWidthMin.get(l) - i;
                if(DistanceX < 5){
                 int distY = ArrayStartPixelHeightMax.get(l) - e;
                   if(distY < 2){
                    ListIndex = l;
                     System.out.println("sad "+l);
                     }else{
                      break;
                     }
                 }
                }
              }
                
            }
             if(ArrayListF.size() > 30){
             break;
             }

               System.out.println("Line: "+e+" Result: "+ArrayListF.size());
              }
                          
              System.out.println("Final "+ArrayListF.size());
              
             for(int n=0;n<ArrayListF.size();n++){
                  
              int X = ArrayStartPixelWidthMax.get(n) - ArrayStartPixelWidthMin.get(n);
              int Y = ArrayStartPixelHeightMax.get(n) - ArrayStartPixelHeightMin.get(n);
              
              BufferedImage bImage;
              
              try{
              bImage = new BufferedImage(X,Y,BufferedImage.TYPE_INT_RGB);
              }catch(Exception e){
              break;
              }
              
              System.out.println("Dimensions X:"+n+" "+X);
              System.out.println("Dimensions Y:"+n+" "+Y);
              System.out.println("Position X:"+n+" "+(X/2));
              System.out.println("Position Y:"+n+" "+(Y/2));
              
                 System.out.println("");
              
              for(int o = 0;o<(Y-1);o++){
                for(int p = 0;p<(X-1);p++){
 
                    int INDEX = (o * Y) + p;
                    //System.out.println("Index: "+INDEX);
                    //System.out.println("Index Array List: "+ArrayListF.get(n).size());
                    if(INDEX<ArrayListF.get(n).size()){
                    bImage.setRGB(p, o, ArrayListF.get(n).get(INDEX).getRGB());
                    }
                
                }
              
              }     
              String Name = "Image"+n;
              File file = new File(Name+".jpg");
              ImageIO.write(bImage, "jpg", file); 
          }
               
	} catch ( IOException exc ) {
		
		exc.printStackTrace();
		
	} catch (AWTException ex) {
           
        }
        
    }
    
    static void CreateArrays(){
        
    ArrayListF.add(new ArrayList<Color>());
                       
    ArrayStartPixelWidthMin.add(LengthX);
    ArrayStartPixelWidthMax.add(LengthX);
                       
    ArrayStartPixelHeightMin.add(LengthY);
    ArrayStartPixelHeightMax.add(LengthY);
        
    }
    


    
}
