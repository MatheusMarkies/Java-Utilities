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
class PixelObject {
    
    int positionWidth;
    int positionHeight;
    Color color;

    public void setPositionHeight(int positionHeight) {
        this.positionHeight = positionHeight;
    }

    public void setPositionWidth(int positionWidth) {
        this.positionWidth = positionWidth;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPositionHeight() {
        return positionHeight;
    }

    public int getPositionWidth() {
        return positionWidth;
    }

    public Color getColor() {
        return color;
    }
    
}
