/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.ImageUtilities;

import JavaUt.Logic.Vector2;

/**
 *
 * @author Matheus Markies
 */
class FormatObject {
    
    int Radius;
    Vector2 Center;
    int StartHeightPx;
    int FinalHeightPx;

    public Vector2 getCenter() {
        return Center;
    }

    public int getRadius() {
        return Radius;
    }

    public int getStartHeightPx() {
        return StartHeightPx;
    }

    public int getFinalHeightPx() {
        return FinalHeightPx;
    }

    public void setCenter(Vector2 Center) {
        this.Center = Center;
    }

    public void setRadius(int Radius) {
        this.Radius = Radius;
    }

    public void setStartHeightPx(int StartHeightPx) {
        this.StartHeightPx = StartHeightPx;
    }

    public void setFinalHeightPx(int FinalHeightPx) {
        this.FinalHeightPx = FinalHeightPx;
    }
    
    
    
}
