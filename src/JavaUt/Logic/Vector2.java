/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.Logic;

/**
 *
 * @author Matheus Markies
 */
public class Vector2 {
    
    int X;
    int Y;

    public Vector2(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    
    public void setY(int Y) {
        this.Y = Y;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }
    
}
