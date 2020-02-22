/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 * Polinomio en forma 1
 */
public class PolvF1 extends PolinomioVector{
    
    //Constructor
    public PolvF1 (int grado){
        this.termino1 = grado;
        this.vec = new int[grado + 2];
        vec[0] = grado;
    }
    
    //Métodos    
    public void mostrar(){
        for(int k = 1; k < vec[0] + 1; k++){
            if(vec[k] != 0){
                if(vec[k] > 1){
                    System.out.print(vec[k] + "x^" + (vec[0] + 1 - k));
                }
            }
        }
    }
    
    public int getDato (int pos){
        return (vec[pos]);
    }
    
    public void setDato (int dato, int pos){
        vec[pos] = dato;
    }
    
    public int getTam(){
        return termino1;
    }
    
    public float evaluar(float x){
        return x;
    }
    
    public void redimensionar(int exp){
        
    }
    
    public void almacenar(int coef, int exp){
        int pos;
        if (exp >= 0 && exp <= vec[0]){
            pos = vec[0] + 1 - exp;
            if(vec[pos] == 0){
                vec[pos] = coef;
            }else{
                System.out.println("No puede almacenar el término.");
            }
        }
    }

    public void insertarTerm(int coef, int exp){
        int pos;
        if(exp < 0){
            System.out.println("El exponente no es válido");
        }else{
            if (exp <= vec[0]){
                pos = vec[0] + 1 - exp;
                vec[pos] = vec[pos] + coef;
                //this.ajustar();
            }else{
                //this.redimensionar(exp);
                vec[0] = exp;
                vec[1] = coef;
            }
        }
    }
    
    public void eliminarTerm(){
        
    }
    
    public void ajustar(){
        int k = 1, j = vec[0];
        while(vec[k] == 0){
            j = j - 1;
        }

    }
    
    public PolvF1 sumar (PolvF1 B){
        int k = 1, j = 1, expA, expB, pos, My;
        if(vec[0] < B.getDato(0)){
            My = vec[0];
        }else{
            My = B.getDato(0);
        }
        PolvF1 R = new PolvF1(My);
        while ((k < vec[0] + 2) && (j < B.getDato(0) + 2)){
            expA = vec[0] + 1 - k;
            expB = B.getDato(0) + 1 - j;
            if(expA > expB){
                pos = R.getDato(0) + 1 - expA;
                R.setDato(vec[k], pos);
                k++;
            }else{
                if(expB > expA){
                    pos = R.getDato(0) + 1 - expB;
                    R.setDato(B.getDato(j), pos);
                    j++;
                }else{
                    pos = R.getDato(0) + 1 - expA;
                    R.setDato(vec[k] + B.getDato(j), pos);
                    k++;
                    j++;
                }
            }
        }
        R.ajustar();
        return (R);
    }
    
}
