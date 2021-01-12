/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.ImageUtilities;

import java.awt.Color;

/**
 *
 * @author Matheus Markies
 */
class PatternPixelSet{
    
    int Px;
    Type_Set type;
    int pixelWidth;
    int pixelHeight;
    Color color;
    
    enum Type_Set{
    START_PIXEL,CLOSE_PIXEL,NONE
    }
    
    public int getLine(){
    return this.Px;
    }
    public Type_Set getType(){
    return this.type;
    }
    public int getPixelWidth(){
    return this.pixelWidth;
    }
    public int getPixelHeight(){
    return this.pixelHeight;
    }
    public Color getColor() {
    return color;
    }
    
    public void setType(Type_Set type) {
        this.type = type;
    }

    public void setLine(int Px) {
        this.Px = Px;
    }

    public void setPixelWidth(int pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public void setPixelHeight(int pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
