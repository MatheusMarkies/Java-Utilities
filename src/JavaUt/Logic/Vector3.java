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
public class Vector3 {
    
    int X;
    int Y;
    int Z;

    public Vector3(int X, int Y, int Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
    
    public void setX(int X) {
        this.X = X;
    }

    public void setZ(int Z) {
        this.Z = Z;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getZ() {
        return Z;
    }
    
}
