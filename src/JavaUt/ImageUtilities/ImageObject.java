package JavaUt.ImageUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathe
 */
import JavaUt.Logic.Vector2;
import java.awt.Color;
import java.util.ArrayList;

public class ImageObject
{
  ArrayList<PatternPixelSet> pixels = new ArrayList();
  
  public void addPixel(PatternPixelSet pixel)
  {
    this.pixels.add(pixel);
  }
  
  public ArrayList<PatternPixelSet> getPixels()
  {
    return this.pixels;
  }
  
  public void setPixels(ArrayList<PatternPixelSet> pps)
  {
    this.pixels = pps;
  }
  
  public static PatternPixelSet createPixelPattern(int u, int v, Color color)
  {
    PatternPixelSet pps = new PatternPixelSet();
    pps.setPixelHeight(u);
    pps.setPixelWidth(v);
    
    pps.setType(PatternPixelSet.Type_Set.NONE);
    pps.setLine(v);
    pps.setColor(color);
    
    return pps;
  }
  
  public ArrayList<PatternPixelSet> formatObject(ArrayList<PatternPixelSet> pixels)
  {
    ArrayList<PatternPixelSet> pps = new ArrayList();
    
    int minU = 0;
    int minV = 0;
    for (int i = 0; i < pixels.size(); i++) {
      if (i == 0)
      {
        minU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        minV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
      }
      else
      {
        if (((PatternPixelSet)pixels.get(i)).getPixelWidth() <= minU) {
          minU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        }
        if (((PatternPixelSet)pixels.get(i)).getPixelHeight() <= minV) {
          minV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
        }
      }
    }
    for (PatternPixelSet z : pixels)
    {
      PatternPixelSet pps_ = createPixelPattern(z.getPixelWidth() - minU, z.getPixelHeight() - minV, z.getColor());
      pps.add(pps_);
    }
    return pps;
  }
  
  public Vector2 maxBounds(ArrayList<PatternPixelSet> pixels)
  {
    int maxU = 0;
    int maxV = 0;
    for (int i = 0; i < pixels.size(); i++) {
      if (i == 0)
      {
        maxU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        maxV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
      }
      else
      {
        if (((PatternPixelSet)pixels.get(i)).getPixelWidth() >= maxU) {
          maxU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        }
        if (((PatternPixelSet)pixels.get(i)).getPixelHeight() >= maxV) {
          maxV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
        }
      }
    }
    return new Vector2(maxU, maxV);
  }
  
  public Vector2 minBounds(ArrayList<PatternPixelSet> pixels)
  {
    int minU = 0;
    int minV = 0;
    for (int i = 0; i < pixels.size(); i++) {
      if (i == 0)
      {
        minU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        minV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
      }
      else
      {
        if (((PatternPixelSet)pixels.get(i)).getPixelWidth() <= minU) {
          minU = ((PatternPixelSet)pixels.get(i)).getPixelWidth();
        }
        if (((PatternPixelSet)pixels.get(i)).getPixelHeight() <= minV) {
          minV = ((PatternPixelSet)pixels.get(i)).getPixelHeight();
        }
      }
    }
    return new Vector2(minU, minV);
  }
  
  public void addPixelArray(ArrayList<PatternPixelSet> ppsList)
  {
    for (PatternPixelSet pps : ppsList) {
      addPixel(pps);
    }
  }
}