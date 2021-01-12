package JavaUt.ImageUtilities;

import JavaUt.Logic.Vector2;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.imageio.ImageIO;

public class ImagePattern {

    public static void main(String[] args)
            throws FileNotFoundException, AWTException, IOException {
        InputStream is = new BufferedInputStream(new FileInputStream("G:\\OneDrive - Etec Centro Paula Souza\\Documentos\\NetBeansProjects\\Java-Utilities-master\\dist\\TheMobs.png"));
        BufferedImage bufi = ImageIO.read(is);

        Color mainColor = getImageMainColor(bufi, false);

        System.out.println("Main Color: " + mainColor);

        ArrayList<PatternPixelSet> pattern = imagePatternCreate.createImageBorderPattern(bufi, mainColor, false, 10);

        BufferedImage bImg = new BufferedImage(bufi.getWidth() + 1, bufi.getHeight() + 1, 4);
        for (PatternPixelSet pps : pattern) {
            bImg.setRGB(pps.getPixelWidth(), pps.getPixelHeight(), pps.getColor().getRGB());
        }
        File file = new File("Border.png");
        ImageIO.write(bImg, "png", file);

        ArrayList<ImageObject> object = getImageObject(bImg, 2);
        for (int i = 0; i < object.size(); i++) {
            BufferedImage bImg_ = new BufferedImage(((ImageObject) object.get(i)).maxBounds(((ImageObject) object.get(i)).getPixels()).getX() + 1, ((ImageObject) object.get(i)).maxBounds(((ImageObject) object.get(i)).getPixels()).getY() + 1, 4);
            for (PatternPixelSet pps : ((ImageObject) object.get(i)).getPixels()) {
                bImg_.setRGB(pps.getPixelWidth(), pps.getPixelHeight(), pps.getColor().getRGB());
            }
            System.out.println(" Pixels:" + ((ImageObject) object.get(i)).getPixels().size() + "px");
            File file_ = new File("Object " + i + ".png");
            ImageIO.write(bImg_, "png", (File) file_);
        }
        BufferedImage bImg_ = new BufferedImage(bufi.getWidth() + 1, bufi.getHeight() + 1, 4);

        ArrayList<PixelObject> pixelObj = patternAnalizer.getMidLine(bufi, pattern);
        for (Object file_ = pixelObj.iterator(); ((Iterator) file_).hasNext();) {
            PixelObject po = (PixelObject) ((Iterator) file_).next();
            bImg_.setRGB(bufi.getWidth() / 4 + po.getPositionWidth(), po.getPositionHeight(), po.getColor().getRGB());
        }
        File file_ = new File("Mid.png");
        ImageIO.write(bImg_, "png", file_);
    }

    public static Color getImageMainColor(BufferedImage ImageBase_, boolean deleteExtremes)
            throws IOException {
        Color mainColor = null;

        ArrayList<Color> ImageColorList = new ArrayList();
        Set<Color> colorList = new HashSet();

        int U = ImageBase_.getWidth();
        int V = ImageBase_.getHeight();
        for (int h = 0; h < V; h++) {
            for (int w = 0; w < U; w++) {
                Color color = new Color(ImageBase_.getRGB(w, h));
                ImageColorList.add(color);
                colorList.add(color);
            }
        }
        if (deleteExtremes) {
            colorList = deleteExtremes(colorList);
        }
        mainColor = compareColors(hashSetToArray(colorList), ImageColorList);

        return mainColor;
    }

    public static ArrayList<Color> getRepeatedColor(ArrayList<Color> listl, boolean ignoreExtremes) {
        ArrayList<Color> ListColors = listl;
        ArrayList<Color> newArray = new ArrayList();
        for (int cord = 0; cord < ListColors.size(); cord++) {
            for (int c = 0; c < ListColors.size(); c++) {
                if ((ListColors.get(c) == ListColors.get(cord))
                        && (c > cord)) {
                    ListColors.remove(cord);
                }
            }
        }
        if (ignoreExtremes) {
            boolean Clean = false;
            if (!Clean) {
                boolean Check = false;
                for (int c = 0; c < ListColors.size(); c++) {
                    if (((Color) ListColors.get(c)).getAlpha() == 0) {
                        ListColors.remove(c);
                    }
                    if ((((Color) ListColors.get(c)).getBlue() == 255) && (((Color) ListColors.get(c)).getGreen() == 255) && (((Color) ListColors.get(c)).getRed() == 255)) {
                        ListColors.remove(c);
                    }
                    if ((((Color) ListColors.get(c)).getBlue() == 0) && (((Color) ListColors.get(c)).getGreen() == 0) && (((Color) ListColors.get(c)).getRed() == 0)) {
                        ListColors.remove(c);
                    }
                }
                for (int c = 0; c < ListColors.size(); c++) {
                    if (((Color) ListColors.get(c)).getAlpha() == 0) {
                        Check = true;
                    }
                    if ((((Color) ListColors.get(c)).getBlue() == 255) && (((Color) ListColors.get(c)).getGreen() == 255) && (((Color) ListColors.get(c)).getRed() == 255)) {
                        Check = true;
                    }
                    if ((((Color) ListColors.get(c)).getBlue() == 0) && (((Color) ListColors.get(c)).getGreen() == 0) && (((Color) ListColors.get(c)).getRed() == 0)) {
                        Check = true;
                    }
                }
                if (!Check) {
                    Clean = false;
                }
            }
        }
        newArray = ListColors;
        return newArray;
    }

    public static Set<Color> deleteExtremes(Set<Color> hasset_) {
        Set<Color> NewHash = new HashSet();
        for (Color color : hasset_) {
            boolean Check = false;
            if (color.getAlpha() == 0) {
                Check = true;
            }
            if ((color.getBlue() == 255) && (color.getGreen() == 255) && (color.getRed() == 255)) {
                Check = true;
            }
            if ((color.getBlue() == 0) && (color.getGreen() == 0) && (color.getRed() == 0)) {
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
                mainColor = (Color) colors.get(t);
                mostRepeated = repeated;
            }
        }
        return mainColor;
    }

    public static ArrayList<Color> hashSetToArray(Set<Color> hash) {
        ArrayList<Color> newArray = new ArrayList();
        for (Color color : hash) {
            newArray.add(color);
        }
        return newArray;
    }

    static class imageTreatment
            extends ImagePattern {

        static ArrayList<BufferedImage> frameAnallyzer(Color mainColor, BufferedImage in)
                throws AWTException, IOException {
            ArrayList<BufferedImage> newArray = new ArrayList();

            Robot robot = new Robot();
            BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            BufferedImage bufi = in;

            ArrayList<BufferedImage> imagesToAnalizer = divideImages(bi, bufi);
            for (int n = 0; n < imagesToAnalizer.size(); n++) {
                String Name = "Image" + n;
                File file = new File(Name + ".png");

                int U = ((BufferedImage) imagesToAnalizer.get(n)).getWidth();
                int V = ((BufferedImage) imagesToAnalizer.get(n)).getHeight();
                for (int h = 0; h < V; h++) {
                    for (int w = 0; w < U; w++) {
                        Color localColor = new Color(((BufferedImage) imagesToAnalizer.get(n)).getRGB(w, h));
                    }
                }
                ImageIO.write((RenderedImage) imagesToAnalizer.get(n), "png", file);
            }
            newArray = imagesToAnalizer;
            return newArray;
        }

        static ArrayList<BufferedImage> divideImages(BufferedImage bigImage, BufferedImage SmallImage) {
            ArrayList<BufferedImage> bs = new ArrayList();
            BufferedImage smallImage = null;
            if ((SmallImage.getWidth() < bigImage.getWidth()) && (SmallImage.getHeight() < bigImage.getHeight())) {
                smallImage = SmallImage;
            } else {
                smallImage = resizeImage(SmallImage, 400, 340);
            }
            float sizeFactorU = bigImage.getWidth() / smallImage.getWidth();
            float sizeFactorV = bigImage.getHeight() / smallImage.getHeight();

            int M = 0;
            int N = 0;

            int MinX = 0;
            int MinY = 0;
            int MaxX = 0;
            int MaxY = 0;

            int images = (int) sizeFactorU * (int) sizeFactorV;
            for (int i = 0; i < images; i++) {
                BufferedImage bImg = new BufferedImage(smallImage.getWidth() + 1, smallImage.getHeight() + 1, 4);

                MinX = smallImage.getWidth() * M;
                MaxX = smallImage.getWidth() * (M + 1) - 1;

                MinY = smallImage.getHeight() * N;
                MaxY = smallImage.getHeight() * (N + 1) - 1;
                for (int y = 0; y < bigImage.getHeight(); y++) {
                    for (int x = 0; x < bigImage.getWidth(); x++) {
                        if ((y >= MinY) && (y <= MaxY)) {
                            if ((x >= MinX) && (x <= MaxX)) {
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

                                bImg.setRGB(xcord, ycord, bigImage.getRGB(x, y));
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
            Image tmp = img.getScaledInstance(width, height, 4);
            BufferedImage resized = new BufferedImage(width, height, 2);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            return resized;
        }
    }

    static class imageRecognizerByColor
            extends ImagePattern {

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
            ArrayList<String> resultQuad = new ArrayList();
            for (int u = 0; u < 3; u++) {
                if ((u == 0) && (redActive)) {
                    int tolerance = Tolerance * mainColor.getRed() / 100;
                    int lenght = tolerance * 2;
                    for (int y = 0; y < lenght; y++) {
                        int red = mainColor.getRed() - tolerance + y;
                        if (red < 0) {
                            red = 0;
                        }
                        if (R != 0) {
                            break;
                        }
                        for (int r = 0; r < framesColors.size(); r++) {
                            if (((Color) framesColors.get(r)).getRed() == red) {
                                R++;
                                resultQuad.add(r + "");
                                break;
                            }
                        }
                    }
                }
                if ((u == 1) && (greenActive)) {
                    int toleranceG = Tolerance * mainColor.getGreen() / 100;
                    int lenghtG = toleranceG * 2;
                    for (int y = 0; y < lenghtG; y++) {
                        int green = mainColor.getRed() - toleranceG + y;
                        if (green < 0) {
                            green = 0;
                        }
                        if (G != 0) {
                            break;
                        }
                        for (int r = 0; r < framesColors.size(); r++) {
                            if (((Color) framesColors.get(r)).getRed() == green) {
                                G++;
                                resultQuad.add(r + "");
                                break;
                            }
                        }
                    }
                }
                if ((u == 2) && (blueActive)) {
                    int toleranceB = Tolerance * mainColor.getBlue() / 100;
                    int lenghtB = toleranceB * 2;
                    for (int y = 0; y < lenghtB; y++) {
                        int blue = mainColor.getRed() - toleranceB + y;
                        if (blue < 0) {
                            blue = 0;
                        }
                        if (B != 0) {
                            break;
                        }
                        for (int r = 0; r < framesColors.size(); r++) {
                            if (((Color) framesColors.get(r)).getRed() == blue) {
                                B++;
                                resultQuad.add(r + "");
                                break;
                            }
                        }
                    }
                }
            }
            if ((R != 0) || (B != 0) || (G != 0)) {
                for (String s : resultQuad) {
                    result = result + s + " " + framesColors.get(Integer.parseInt(s));
                }
            }
            return result;
        }
    }

    static class imagePatternCreate
            extends ImagePattern {

        public static ArrayList<PatternPixelSet> createAllPixelsPattern(BufferedImage imageBase) {
            ArrayList<PatternPixelSet> pattern = new ArrayList();

            int U = imageBase.getWidth();
            int V = imageBase.getHeight();
            for (int h = 0; h < V; h++) {
                for (int w = 0; w < U; w++) {
                    Color color = new Color(imageBase.getRGB(w, h));

                    PatternPixelSet pps = new PatternPixelSet();
                    pps.setPixelHeight(h);
                    pps.setPixelWidth(w);

                    pps.setType(PatternPixelSet.Type_Set.START_PIXEL);
                    pps.setLine(w);
                    pps.setColor(color);

                    pattern.add(pps);
                }
            }
            return pattern;
        }

        public static boolean compareColors(Color a, Color b) {
            if ((a.getRed() == b.getRed()) && (a.getGreen() == b.getGreen()) && (a.getBlue() == b.getBlue())) {
                return true;
            }
            return false;
        }

        public static ArrayList<PatternPixelSet> createImageBorderPattern(BufferedImage imageBase, Color mainColor, boolean WithMainColor, int tolerance) {
            ArrayList<PatternPixelSet> pattern = new ArrayList();

            int U = imageBase.getWidth();
            int V = imageBase.getHeight();
            for (int h = 0; h < V; h++) {
                boolean CreateStartPx = false;
                for (int w = 0; w < U; w++) {
                    Color color = new Color(imageBase.getRGB(w, h));
                    if (WithMainColor) {
                        if ((color.getAlpha() != 0)
                                && (color.getBlue() != 255) && (color.getGreen() != 255) && (color.getRed() != 255)
                                && (color.getRGB() == mainColor.getRGB())) {
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
                    } else {
                        if (w - 1 >= 0) {
                            for (int i = -tolerance; i < tolerance; i++) {
                                if ((!compareColors(new Color(imageBase.getRGB(w - 1, h)), new Color(imageBase.getRGB(w, h) + i)))
                                        && (!CreateStartPx)) {
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
                        if ((w - 1 >= 0) && (w + 1 < imageBase.getWidth())) {
                            if ((imageBase.getRGB(w + 1, h) != imageBase.getRGB(w - 1, h))
                                    && (CreateStartPx)) {
                                PatternPixelSet pps = new PatternPixelSet();
                                pps.setPixelHeight(h);
                                pps.setPixelWidth(w);

                                pps.setType(PatternPixelSet.Type_Set.CLOSE_PIXEL);
                                pps.setLine(w);
                                pps.setColor(color);

                                pattern.add(pps);
                                CreateStartPx = false;
                            }
                        } else {
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
            return pattern;
        }
    }

    static class patternAnalizer
            extends ImagePattern {

        static enum Format {
            Square, Circle, Triangle, NonEuclideanTriangle, None;

            private Format() {
            }
        }

        public static ArrayList<Format> getPatternFormat(BufferedImage imageBase, ArrayList<PatternPixelSet> pps, ArrayList<PixelObject> pos) {
            ArrayList<Format> form = new ArrayList();

            return form;
        }

        public static ArrayList<PixelObject> getMidLine(BufferedImage imageBase, ArrayList<PatternPixelSet> pps) {
            ArrayList<PixelObject> PO = new ArrayList();

            int U = imageBase.getWidth();
            int V = imageBase.getHeight();
            for (int h = 0; h < V; h++) {
                PatternPixelSet StartPx = null;
                PatternPixelSet ClosePx = null;
                for (PatternPixelSet pp : pps) {
                    if ((pp.getPixelHeight() == h) && (pp.getType() == PatternPixelSet.Type_Set.START_PIXEL)) {
                        StartPx = pp;
                    } else if ((pp.getPixelHeight() == h) && (pp.getType() == PatternPixelSet.Type_Set.CLOSE_PIXEL)) {
                        ClosePx = pp;
                    }
                    if ((ClosePx != null) && (StartPx != null)) {
                        break;
                    }
                }
                if ((ClosePx != null) && (StartPx != null)) {
                    PixelObject p = new PixelObject();

                    p.setColor(Color.GREEN);
                    p.setPositionHeight(h);
                    try {
                        p.setPositionWidth((ClosePx.getPixelWidth() - StartPx.getPixelWidth()) / 2);
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    }
                    if ((StartPx.getPixelWidth() == 0) || (ClosePx.getPixelWidth() == 0)) {
                        p.setPositionWidth(0);
                    }
                    PO.add(p);
                }
            }
            return PO;
        }
    }

    public static ImageObject createObject() {
        return new ImageObject();
    }

    public static PatternPixelSet createPixelPattern(int u, int v, Color color) {
        PatternPixelSet pps = new PatternPixelSet();
        pps.setPixelHeight(u);
        pps.setPixelWidth(v);

        pps.setType(PatternPixelSet.Type_Set.NONE);
        pps.setLine(v);
        pps.setColor(color);

        return pps;
    }

    public static ArrayList<ImageObject> getImageObject(BufferedImage borderPattern, int tolerance) {
        ArrayList<ImageObject> objects = new ArrayList();

        int U = borderPattern.getWidth();
        int V = borderPattern.getHeight();
        for (int h = 0; h < V; h++) {
            for (int w = 0; w < U; w++) {
                Color color = new Color(borderPattern.getRGB(w, h));
                PatternPixelSet pps = createPixelPattern(w, h, color);
                if ((color.getRed() != 0) && (color.getGreen() != 0) && (color.getBlue() != 0)) {
                    boolean CreateOBJ = false;
                    for (int i = 1; i < tolerance; i++) {
                        ArrayList<PatternPixelSet> ppsSquare = new ArrayList();
                        if ((w - i >= 0) && (w - i < borderPattern.getWidth())) {
                            ppsSquare.add(createPixelPattern(w - i, h, new Color(borderPattern.getRGB(w - i, h))));
                        } else {
                            ppsSquare.add(createPixelPattern(0, 0, new Color(0, 0, 0)));
                        }
                        if ((w - i >= 0) && (h - i >= 0) && (w - i < borderPattern.getWidth()) && (h - i < borderPattern.getHeight())) {
                            ppsSquare.add(createPixelPattern(w - i, h - i, new Color(borderPattern.getRGB(w - i, h - 1))));
                        } else {
                            ppsSquare.add(createPixelPattern(0, 0, new Color(0, 0, 0)));
                        }
                        if ((h - i >= 0) && (h - i < borderPattern.getHeight())) {
                            ppsSquare.add(createPixelPattern(w, h - i, new Color(borderPattern.getRGB(w, h - i))));
                        } else {
                            ppsSquare.add(createPixelPattern(0, 0, new Color(0, 0, 0)));
                        }
                        if ((w + i >= 0) && (h - i >= 0) && (w + i < borderPattern.getWidth()) && (h - i < borderPattern.getHeight())) {
                            ppsSquare.add(createPixelPattern(w + i, h - i, new Color(borderPattern.getRGB(w + i, h - i))));
                        } else {
                            ppsSquare.add(createPixelPattern(0, 0, new Color(0, 0, 0)));
                        }
                        if (objects.size() == 0) {
                            objects.add(createObject());
                            ((ImageObject) objects.get(0)).addPixel(pps);
                        } else if ((((PatternPixelSet) ppsSquare.get(0)).getColor().getRed() != 0) && (((PatternPixelSet) ppsSquare.get(0)).getColor().getGreen() != 0) && (((PatternPixelSet) ppsSquare.get(0)).getColor().getBlue() != 0)) {
                            for (int o = 0; o < objects.size(); o++) {
                                for (int p = 0; p < ((ImageObject) objects.get(o)).getPixels().size(); p++) {
                                    if ((((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelHeight() == ((PatternPixelSet) ppsSquare.get(0)).getPixelHeight()) && (((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelWidth() == ((PatternPixelSet) ppsSquare.get(0)).getPixelWidth())) {
                                        ((ImageObject) objects.get(o)).addPixel(pps);
                                        CreateOBJ = false;
                                    }
                                }
                            }
                        } else if ((((PatternPixelSet) ppsSquare.get(1)).getColor().getRed() != 0) && (((PatternPixelSet) ppsSquare.get(1)).getColor().getGreen() != 0) && (((PatternPixelSet) ppsSquare.get(1)).getColor().getBlue() != 0)) {
                            for (int o = 0; o < objects.size(); o++) {
                                for (int p = 0; p < ((ImageObject) objects.get(o)).getPixels().size(); p++) {
                                    if ((((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelHeight() == ((PatternPixelSet) ppsSquare.get(1)).getPixelHeight()) && (((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelWidth() == ((PatternPixelSet) ppsSquare.get(1)).getPixelWidth())) {
                                        ((ImageObject) objects.get(o)).addPixel(pps);
                                        CreateOBJ = false;
                                    }
                                }
                            }
                        } else if ((((PatternPixelSet) ppsSquare.get(2)).getColor().getRed() != 0) && (((PatternPixelSet) ppsSquare.get(2)).getColor().getGreen() != 0) && (((PatternPixelSet) ppsSquare.get(2)).getColor().getBlue() != 0)) {
                            for (int o = 0; o < objects.size(); o++) {
                                for (int p = 0; p < ((ImageObject) objects.get(o)).getPixels().size(); p++) {
                                    if ((((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelHeight() == ((PatternPixelSet) ppsSquare.get(2)).getPixelHeight()) && (((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelWidth() == ((PatternPixelSet) ppsSquare.get(2)).getPixelWidth())) {
                                        ((ImageObject) objects.get(o)).addPixel(pps);
                                        CreateOBJ = false;
                                    }
                                }
                            }
                        } else if ((((PatternPixelSet) ppsSquare.get(3)).getColor().getRed() != 0) && (((PatternPixelSet) ppsSquare.get(3)).getColor().getGreen() != 0) && (((PatternPixelSet) ppsSquare.get(3)).getColor().getBlue() != 0)) {
                            for (int o = 0; o < objects.size(); o++) {
                                for (int p = 0; p < ((ImageObject) objects.get(o)).getPixels().size(); p++) {
                                    if ((((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelHeight() == ((PatternPixelSet) ppsSquare.get(3)).getPixelHeight()) && (((PatternPixelSet) ((ImageObject) objects.get(o)).getPixels().get(p)).getPixelWidth() == ((PatternPixelSet) ppsSquare.get(3)).getPixelWidth())) {
                                        ((ImageObject) objects.get(o)).addPixel(pps);
                                        CreateOBJ = false;
                                    }
                                }
                            }
                        } else {
                            CreateOBJ = true;
                        }
                    }
                    if (CreateOBJ) {
                        int index = objects.size();
                        objects.add(createObject());
                        ((ImageObject) objects.get(index)).addPixel(pps);
                    }
                }
            }
        }
        for (int s = 0; s < 3; s++) {
            for (int i = 0; i < objects.size(); i++) {
                if (((ImageObject) objects.get(i)).getPixels().size() < 2) {
                    objects.remove(i);
                }
            }
        }
        System.out.println("Objects: " + objects.size());
        for (ImageObject i : objects) {
            i.setPixels(i.formatObject(i.getPixels()));
        }
        return bledObjects(objects);
    }

    public static boolean comparePixelsPattern(PatternPixelSet a, PatternPixelSet b, int tolerance) {
        for (int i = -tolerance; i < tolerance; i++) {
            if ((a.getPixelHeight() == b.getPixelHeight() + i) && (a.getPixelWidth() == b.getPixelWidth() + i)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<ImageObject> bledObjects(ArrayList<ImageObject> objects) {
        ArrayList<ImageObject> blend = new ArrayList();
        int index = 0;
        boolean equalPixels = false;
        for (ImageObject o : objects) {
            for (ImageObject z : objects) {
                for (PatternPixelSet objectPixel : o.getPixels()) {
                    for (PatternPixelSet secondPixel : z.getPixels()) {
                        if ((comparePixelsPattern(objectPixel, secondPixel, 1)) && (!equalPixels)) {
                            blend.add(createObject());
                            blend.get(index).addPixelArray(o.getPixels());
                            blend.get(index).addPixelArray(z.getPixels());
                            index++;
                            equalPixels = true;
                            break;
                        }
                    }
                }
            }
        }
        if (equalPixels) {
            equalPixels = false;
        }

        return blend;
    }
}
