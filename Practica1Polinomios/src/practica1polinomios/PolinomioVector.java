/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 *Clase padre de los polinomios en forma 1 y 2 
 */
public class PolinomioVector {
    //Declaración de atributos
    protected int termino0; //Grado o el número de términos
    protected float[] vec; //Vector

    //Métodos
    public int getDato (int pos){
        return ((int)vec[pos]);
    }
    
    public void setDato (float dato, int pos){
        vec[pos] = dato;
    }
    
    public int getTam(){
        return termino0;
    }
    
    //Limpia los datos útiles del vector después de la posición 1
    public void clean(){
        for(int i = 1; i < vec.length; i++ )
            setDato(0, i);
    }
}
