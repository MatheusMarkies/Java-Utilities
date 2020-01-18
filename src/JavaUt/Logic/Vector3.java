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
    
    float X;
    float Y;
    float Z;

    public Vector3(float X, float Y, float Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
    
    public void setX(int X) {
        this.X = X;
    }

    public void Z() {
        this.Z = Z;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public float getZ() {
        return Z;
    }
    
    public Vector3 addVector(Vector3 a,Vector3 b){
    Vector3 c = new Vector3(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    return c;
    }
    public Vector3 subVector(Vector3 a,Vector3 b){
    Vector3 c = new Vector3(a.getX() - b.getX(), a.getY() - b.getY(),a.getZ() - b.getZ());
    return c;
    }
    public Vector3 multiply(Vector3 a,Vector3 b){
    Vector3 c = new Vector3(a.getX() * b.getX(), a.getY() * b.getY(),a.getZ() * b.getZ());
    return c;
    }
    public Vector3 divide(Vector3 a,Vector3 b){
    Vector3 c = new Vector3(a.getX() / b.getX(), a.getY() / b.getY(),a.getZ() / b.getZ());
    return c;
    }
    
}
