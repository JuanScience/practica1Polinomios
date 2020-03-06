/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 *Objeto Nodo de la forma 3
 */
public class Nodo {
    
    //Declaración de atributos
    protected float coef;
    protected int exp;
    protected Nodo liga; //Nodo
    
    //Constructor
    public Nodo (float co, int ex){
        coef = co;
        exp = ex;
        liga = null;
    }
    
    //Métodos
    
    //Retornoa el coeficiente
    public float getCoef(){
        return coef;
    }
    
    //Asigna un valor al coeficiente
    public void setCoef(float co){
        coef = co;
    }
    
    //Retorna el exponente
    public int getExp(){
       return exp; 
    }
    
    //Asigna un valor al exponente
    public void setExp(int ex){
        exp = ex;
    }
    
    //Retorna la liga
    public Nodo getLiga(){
       return liga; 
    }
    
    //Asigna un valor a la liga
    public void setLiga(Nodo lig){
        liga = lig;
    }
}
