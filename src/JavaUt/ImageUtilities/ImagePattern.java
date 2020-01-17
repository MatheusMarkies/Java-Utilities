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

        InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\Matheus Markies\\Desktop\\OliverCat.png"));
        BufferedImage bufi = ImageIO.read(is);

        Color mainColor = getImageMainColor(bufi);

        System.out.println("Main Color: " + mainColor);

        ArrayList<PatternPixelSet> pattern = imagePatternCreate.createImageBorderPattern(bufi, mainColor,false);

        BufferedImage bImg = new BufferedImage(bufi.getWidth() + 1, bufi.getHeight() + 1, BufferedImage.TYPE_INT_BGR);
        for (PatternPixelSet pps : pattern) {
        System.out.println(pps.getType() + " | line: " + pps.getLine() + " Color: " + pps.getColor());
        bImg.setRGB(pps.getPixelWidth(), pps.getPixelHeight(), pps.getColor().getRGB());
        }
        File file = new File("Border" + ".png");
        ImageIO.write(bImg, "png", file);
        
        BufferedImage bImg_ = new BufferedImage(bufi.getWidth() + 1, bufi.getHeight() + 1, BufferedImage.TYPE_INT_BGR);

        ArrayList<PixelObject> pixelObj = patternAnalizer.getMidLine(bufi, pattern);
        for (PixelObject po : pixelObj) {
            System.out.println("X: "+po.getPositionWidth()+" Y: "+ po.getPositionHeight());
        bImg_.setRGB((bufi.getWidth()/4)+po.getPositionWidth(), po.getPositionHeight(), po.getColor().getRGB());
        }
        File file_ = new File("Mid" + ".png");
        ImageIO.write(bImg_, "png", file_);
         
//    ArrayList<BufferedImage> io = imageTreatment.frameAnallyzer(mainColor, bufi);1
//      
//    ArrayList<Color> FramesMainColor = new ArrayList<>();
//    
//    for(int t =0;t<io.size();t++)
//    FramesMainColor.add(getImageMainColor(io.get(t)));
//
//    System.out.println("Recognizer...");
//    
//    String result = imageRecognizer.getImageQuadrantWithTolerance(FramesMainColor, mainColor, 20,true,false,false,false);
//    System.out.println(result);
    }

    public static Color getImageMainColor(BufferedImage ImageBase_) throws IOException {

        Color mainColor = null;

        ArrayList<Color> ImageColorList = new ArrayList<>();
        Set<Color> colorList = new HashSet<>();

        int U = ImageBase_.getWidth();
        int V = ImageBase_.getHeight();

        for (int h = 0; h < V; h++) {
            for (int w = 0; w < U; w++) {
                Color color = new Color(ImageBase_.getRGB(w, h));
                ImageColorList.add(color);
                colorList.add(color);
            }
        }

        colorList = deleteExtremes(colorList);
        mainColor = compareColors(hashSetToArray(colorList), ImageColorList);

        return mainColor;
    }
    public static ArrayList<Color> getRepeatedColor(ArrayList<Color> listl, boolean ignoreExtremes) {

        ArrayList<Color> ListColors = listl;
        ArrayList<Color> newArray = new ArrayList<>();

        for (int cord = 0; cord < ListColors.size(); cord++) {
            for (int c = 0; c < ListColors.size(); c++) {
                if (ListColors.get(c) == ListColors.get(cord)) {
                    if (c > cord) {
                        ListColors.remove(cord);
                    }
                }
            }
        }

        if (ignoreExtremes) {

            boolean Clean = false;

            while (!Clean) {

                boolean Check = false;

                for (int c = 0; c < ListColors.size(); c++) {

                    if (ListColors.get(c).getAlpha() == 0) {
                        ListColors.remove(c);
                    }
                    if (ListColors.get(c).getBlue() == 255 && ListColors.get(c).getGreen() == 255 && ListColors.get(c).getRed() == 255) {
                        ListColors.remove(c);
                    }
                    if (ListColors.get(c).getBlue() == 0 && ListColors.get(c).getGreen() == 0 && ListColors.get(c).getRed() == 0) {
                        ListColors.remove(c);
                    }

                }

                for (int c = 0; c < ListColors.size(); c++) {

                    if (ListColors.get(c).getAlpha() == 0) {
                        Check = true;
                    }
                    if (ListColors.get(c).getBlue() == 255 && ListColors.get(c).getGreen() == 255 && ListColors.get(c).getRed() == 255) {
                        Check = true;
                    }
                    if (ListColors.get(c).getBlue() == 0 && ListColors.get(c).getGreen() == 0 && ListColors.get(c).getRed() == 0) {
                        Check = true;
                    }

                }

                if (!Check) {
                    Clean = false;
                }
                break;
            }

        }

        newArray = ListColors;
        return newArray;
    }
    public static Set<Color> deleteExtremes(Set<Color> hasset_) {

        Set<Color> NewHash = new HashSet<>();

        for (Color color : hasset_) {
            boolean Check = false;
            if (color.getAlpha() == 0) {
                Check = true;
            }
            if (color.getBlue() == 255 && color.getGreen() == 255 && color.getRed() == 255) {
                Check = true;
            }
            if (color.getBlue() == 0 && color.getGreen() == 0 && color.getRed() == 0) {
                Check = true;
            }
            if (!Check) {
                NewHash.add(color);
            }
        }

        return NewHash;
    }
    public static Color compareColors(ArrayList<Color> colors, ArrayList<Color> imageColors) {

        Color mainColor = null;

        int mostRepeated = 0;

        for (int t = 0; t < colors.size(); t++) {
            int repeated = 0;

            for (Color color : imageColors) {
                if (color == colors.get(t)) {
                    repeated++;
                }
            }

            if (t == 0) {
                mostRepeated = repeated;
            }
            if (repeated >= mostRepeated) {
                mainColor = colors.get(t);
                mostRepeated = repeated;
            }

        }

        return mainColor;
    }
    public static ArrayList<Color> hashSetToArray(Set<Color> hash) {

        ArrayList<Color> newArray = new ArrayList<>();

        for (Color color : hash) {
            newArray.add(color);
        }

        return newArray;
    }
    
    static class imageTreatment extends ImagePattern {

        static ArrayList<BufferedImage> frameAnallyzer(Color mainColor, BufferedImage in) throws AWTException, IOException {

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
                ImageIO.write(imagesToAnalizer.get(n), "png", file);
            }
            newArray = imagesToAnalizer;
            return newArray;
        }

        static ArrayList<BufferedImage> divideImages(BufferedImage bigImage, BufferedImage SmallImage) {

            ArrayList<BufferedImage> bs = new ArrayList<BufferedImage>();
            BufferedImage smallImage = null;

            if (SmallImage.getWidth() < bigImage.getWidth() && SmallImage.getHeight() < bigImage.getHeight()) {
                smallImage = SmallImage;
            } else {
                smallImage = resizeImage(SmallImage, 400, 340);
            }

            float sizeFactorU = bigImage.getWidth() / smallImage.getWidth();//Pegar numera maximo de imagens na largura da imagem grande
            float sizeFactorV = bigImage.getHeight() / smallImage.getHeight();//Pegar numera maximo de imagens na altura da imagem grande

            int M = 0;//Multiplicador de Largura
            int N = 0;//Multiplicador de Altura

            int MinX = 0;//Largura minima para gravacao
            int MinY = 0;//Altura minima para gravacao
            int MaxX = 0;//Largura Maxima para gravacao
            int MaxY = 0;//Altura Maxima para gravacao

            int images = (int) sizeFactorU * (int) sizeFactorV;//Numero de imagens pequenas

            for (int i = 0; i < images; i++) {
                BufferedImage bImg = new BufferedImage(smallImage.getWidth() + 1, smallImage.getHeight() + 1, BufferedImage.TYPE_INT_BGR);

                MinX = smallImage.getWidth() * M;
                MaxX = (smallImage.getWidth() * (M + 1)) - 1;

                MinY = smallImage.getHeight() * N;
                MaxY = (smallImage.getHeight() * (N + 1)) - 1;

                for (int y = 0; y < bigImage.getHeight(); y++) {
                    for (int x = 0; x < bigImage.getWidth(); x++) {

                        if (y >= MinY && y <= MaxY) //Se y for maior ou igual a "Altura minima para gravacao"  e y for menor ou igual "Altura maxima para gravacao"
                        {
                            if (x >= MinX && x <= MaxX) { //Se x for maior ou igual a "Largura minima para gravacao"  e x for menor ou igual "Largura maxima para gravacao"

                                int xcord = 0;
                                int ycord = 0;

                                if (MinX >= smallImage.getWidth()) {
                                    xcord = x - smallImage.getWidth();
                                } else if (MinX <= smallImage.getWidth()) {
                                    xcord = x;
                                }

                                if (MinY >= smallImage.getHeight()) {
                                    ycord = y - smallImage.getHeight();
                                } else if (MinY <= smallImage.getHeight()) {
                                    ycord = y;
                                }

                                if (ycord >= smallImage.getHeight()) {
                                    ycord = smallImage.getHeight() - 1;
                                }

                                if (xcord >= smallImage.getWidth()) {
                                    xcord = smallImage.getWidth() - 1;
                                }

                                Color color = new Color(bigImage.getRGB(x, y));
                                //System.out.println(color);
                                bImg.setRGB(xcord, ycord, bigImage.getRGB(x, y));//Gravar nova imagem

                            }
                        }

                    }

                }

                if (M < (int) sizeFactorU) {
                    M++;
                } else {
                    M = 0;
                    N++;
                }

                bs.add(bImg);
            }

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

    static class imageRecognizer extends ImagePattern {

        static String getImageQuadrantWithTolerance(ArrayList<Color> framesColors, Color mainColor, int Tolerance, boolean redActive, boolean greenActive, boolean blueActive, boolean SmartTolerance) {

            if (SmartTolerance) {

                if (mainColor.getRed() == 0) {
                    redActive = false;
                } else {
                    redActive = true;
                }
                if (mainColor.getGreen() == 0) {
                    greenActive = false;
                } else {
                    greenActive = true;
                }
                if (mainColor.getBlue() == 0) {
                    blueActive = false;
                } else {
                    blueActive = true;
                }

            }

            String result = "Image: ";

            int R = 0;
            int G = 0;
            int B = 0;
            ArrayList<String> resultQuad = new ArrayList<String>();

            for (int u = 0; u < 3; u++) {

                if (u == 0 && redActive) {

                    int tolerance = (Tolerance * mainColor.getRed()) / 100;
                    int lenght = tolerance * 2;

                    for (int y = 0; y < lenght; y++) {

                        int red = (mainColor.getRed() - tolerance) + y;
                        if (red < 0) {
                            red = 0;
                        }

                        if (R == 0) {
                            for (int r = 0; r < framesColors.size(); r++) {
                                if (framesColors.get(r).getRed() == red) {
                                    R++;
                                    resultQuad.add(r + "");
                                    break;
                                }
                            }
                        } else {
                            break;
                        }

                    }

                }
                if (u == 1 && greenActive) {

                    int toleranceG = (Tolerance * mainColor.getGreen()) / 100;
                    int lenghtG = toleranceG * 2;

                    for (int y = 0; y < lenghtG; y++) {

                        int green = (mainColor.getRed() - toleranceG) + y;
                        if (green < 0) {
                            green = 0;
                        }

                        if (G == 0) {
                            for (int r = 0; r < framesColors.size(); r++) {
                                if (framesColors.get(r).getRed() == green) {
                                    G++;
                                    resultQuad.add(r + "");
                                    break;
                                }
                            }
                        } else {
                            break;
                        }

                    }

                }
                if (u == 2 && blueActive) {

                    int toleranceB = (Tolerance * mainColor.getBlue()) / 100;
                    int lenghtB = toleranceB * 2;

                    for (int y = 0; y < lenghtB; y++) {

                        int blue = (mainColor.getRed() - toleranceB) + y;
                        if (blue < 0) {
                            blue = 0;
                        }

                        if (B == 0) {
                            for (int r = 0; r < framesColors.size(); r++) {
                                if (framesColors.get(r).getRed() == blue) {
                                    B++;
                                    resultQuad.add(r + "");
                                    break;
                                }
                            }
                        } else {
                            break;
                        }

                    }

                }

            }

            if (R != 0 || B != 0 || G != 0) {
                for (String s : resultQuad) {
                    result = result + s + " " + framesColors.get(Integer.parseInt(s));
                }
            }

            return result;
        }

    }

    static class imagePatternCreate extends ImagePattern {

    public static ArrayList<PatternPixelSet> createImageBorderPattern(BufferedImage imageBase, Color mainColor,boolean WithMainColor) {

            ArrayList<PatternPixelSet> pattern = new ArrayList<>();

            int U = imageBase.getWidth();
            int V = imageBase.getHeight();

            for (int h = 0; h < V; h++) {
             boolean CreateStartPx = false;
                for (int w = 0; w < U; w++) {

                        Color color = new Color(imageBase.getRGB(w, h));

                        if(WithMainColor){
                           
                            if (color.getAlpha() != 0) {
                            if (color.getBlue() != 255 && color.getGreen() != 255 && color.getRed() != 255) {
                                if (color.getRGB() == mainColor.getRGB()) {

                                if (!CreateStartPx) {

                                    PatternPixelSet pps = new PatternPixelSet();
                                    pps.setPixelHeight(h);
                                    pps.setPixelWidth(w);

                                    pps.setType(PatternPixelSet.Type_Set.START_PIXEL);
                                    pps.setLine(w);
                                    pps.setColor(color);

                                    pattern.add(pps);
                                    CreateStartPx = true;     
                                }

                             }
                            }
                        }

                        if (color.getRGB() != mainColor.getRGB()) {

                            if (CreateStartPx) {

                                PatternPixelSet pps = new PatternPixelSet();
                                pps.setPixelHeight(h);
                                pps.setPixelWidth(w);

                                pps.setType(PatternPixelSet.Type_Set.CLOSE_PIXEL);
                                pps.setLine(w);
                                pps.setColor(color);

                                pattern.add(pps);
                                CreateStartPx = false;

                            }

                        }
   
                        }else{
                        
                        if (color.getAlpha() != 0) {
                            if (color.getBlue() != 255 && color.getGreen() != 255 && color.getRed() != 255) {
                                //if (color.getBlue() != 0 && color.getGreen() != 0 && color.getRed() != 0) {

                                if (!CreateStartPx) {

                                    PatternPixelSet pps = new PatternPixelSet();
                                    pps.setPixelHeight(h);
                                    pps.setPixelWidth(w);

                                    pps.setType(PatternPixelSet.Type_Set.START_PIXEL);
                                    pps.setLine(w);
                                    pps.setColor(color);

                                    pattern.add(pps);
                                    CreateStartPx = true;     
                                }

                                //}//Black Lock
                            }
                        }

                        if (color.getAlpha() == 0) {

                            if (CreateStartPx) {

                                PatternPixelSet pps = new PatternPixelSet();
                                pps.setPixelHeight(h);
                                pps.setPixelWidth(w);

                                pps.setType(PatternPixelSet.Type_Set.CLOSE_PIXEL);
                                pps.setLine(w);
                                pps.setColor(color);

                                pattern.add(pps);
                                CreateStartPx = false;

                            }

                        }
                        if (color.getBlue() == 255 && color.getGreen() == 255 && color.getRed() == 255) {

                            if (CreateStartPx) {
                                
                                PatternPixelSet pps = new PatternPixelSet();
                                pps.setPixelHeight(h);
                                pps.setPixelWidth(w);

                                pps.setType(PatternPixelSet.Type_Set.CLOSE_PIXEL);
                                pps.setLine(w);
                                pps.setColor(color);

                                pattern.add(pps);
                                CreateStartPx = false;

                            }

                        }

                        }
                        
                    }

                }
            

            return pattern;
        }

    }

    static class patternAnalizer extends ImagePattern{
        
        enum Format{
        Square, Circle, Triangle, NonEuclideanTriangle, None
        }
        
        public static Format getPatternFormat(ArrayList<PatternPixelSet> pps){
         Format form = null;
            
         
         
        return form;
        }
        public static ArrayList<PixelObject> getMidLine(BufferedImage imageBase,ArrayList<PatternPixelSet> pps){
        ArrayList<PixelObject> PO = new ArrayList<>();
         
            int U = imageBase.getWidth();
            int V = imageBase.getHeight();

            for (int h = 0; h < V; h++) {
                PatternPixelSet StartPx = null;
                PatternPixelSet ClosePx = null;
              
                 for(PatternPixelSet pp : pps){
                 if(pp.getPixelHeight() == h && pp.getType() == PatternPixelSet.Type_Set.START_PIXEL)
                    StartPx = pp;
                 else if(pp.getPixelHeight() == h && pp.getType() == PatternPixelSet.Type_Set.CLOSE_PIXEL)
                    ClosePx = pp;

                  if(ClosePx != null && StartPx != null)
                  break;
                     
                 }
                 
                 if(ClosePx != null && StartPx != null){
                 PixelObject p = new PixelObject();
                 
                 p.setColor(Color.GREEN);
                 p.setPositionHeight(h);
                 
                 try{
                 p.setPositionWidth((ClosePx.getPixelWidth() + StartPx.getPixelWidth())/2);
                 }catch(ArithmeticException e){
                 System.out.println(e);
                 } 
                 
                 if(StartPx.getPixelWidth() == 0 || ClosePx.getPixelWidth() == 0)
                 p.setPositionWidth(0);
                     
                 PO.add(p);
                 }
                
            }
        
        return PO;
        }
        
    }
    
}