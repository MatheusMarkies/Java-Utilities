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
    
    public Vector2 addVector(Vector2 a,Vector2 b){
    Vector2 c = new Vector2(a.getX() + b.getX(), a.getY() + b.getY());
    return c;
    }
    public Vector2 subVector(Vector2 a,Vector2 b){
    Vector2 c = new Vector2(a.getX() - b.getX(), a.getY() - b.getY());
    return c;
    }
    public Vector2 multiply(Vector2 a,Vector2 b){
    Vector2 c = new Vector2(a.getX() * b.getX(), a.getY() * b.getY());
    return c;
    }
    public Vector2 divide(Vector2 a,Vector2 b){
    Vector2 c = new Vector2(a.getX() / b.getX(), a.getY() / b.getY());
    return c;
    }
    
}
