/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 * Clase polinomio en forma 1
 */
public class PolvF1 extends PolinomioVector{
    
    //Constructor
    public PolvF1 (int grado){
        this.termino1 = grado;
        this.vec = new int[grado + 2];
        vec[0] = grado;
    }
    
    //Métodos
    
    //Convierte un string de coeficientes y exponentes en un polinomio forma 1
    public static PolvF1 toF1(int[] vec){
        //capturar el grado del polinomio
        int grade = 0;
        for(int i = 1; i < vec.length; i = i + 2){
            if (vec[i] > grade){
                grade = vec[i];
            }
        }
        //Crear vector en FORMA 1
        PolvF1 p = new PolvF1(grade); //vector en forma 1
        for(int x = 1; x < vec.length; x = x + 2){ //Posicioines impares
            p.vec[grade + 1 - vec[x]] = vec[x - 1];
        }
        return p;
    }
    
    //Convierte el polinomio de forma 1 a escritura normal
    public void mostrarf1(){
        System.out.println("\nDel vector en forma 1 queda: ");
        for(int k = 1; k < vec[0] + 2; k++){
            if(vec[k] != 0){ //Si el coeficiente es diferente de cero
                switch (vec[0] + 1 - k) {
                    case 0: //Si es el término independiente                        
                        if(k == 1 || vec[k] < 0){//Si está en la primera posicíón o es menor que cero
                            System.out.print(vec[k]);
                        }else{
                            System.out.print("+" + vec[k]);
                        }   break;
                    case 1: //Si es el termino x^1                        
                        if((k == 1 && vec[k] > 1) || vec[k] < -1 ){//Si está en la primera posicíón y el coeficiente es mayor que 1, o el coeficiente menor que -1
                            System.out.print(vec[k] + "x");
                        }else if (k == 1 && vec[k] == 1){//si el coeficiente es 1 en la primera posición
                            System.out.print("x");
                        }else if(vec[k] == 1){//Si el coeficiente es 1
                            System.out.print("+x");
                        }else if(vec[k] == -1){
                            System.out.print("-x");
                        }else{
                            System.out.print("+" + vec[k] + "x");
                        }   break;
                    default:
                        if((k == 1 && vec[k] > 1) || vec[k] < -1 ){//Si está en la primera posicíón y el coeficiente es mayor que 1, o el coeficiente menor que -1
                            System.out.print(vec[k] + "x^" + (vec[0] + 1 - k));
                        }else if (k == 1 && vec[k] == 1){//si el coeficiente es 1 en la primera posición
                            System.out.print("x^" + (vec[0] + 1 - k));
                        }else if(vec[k] == 1){//Si el coeficiente es 1
                            System.out.print("+x^" + (vec[0] + 1 - k));
                        }else if(vec[k] == -1){
                            System.out.print("-x^" + (vec[0] + 1 - k));
                        }else{
                            System.out.print("+" + vec[k] + "x^" + (vec[0] + 1 - k));
                        }   break;
                }
            }
        }
    }
    
    //Imprime el polinomio en forma 1
    public void printf1(){
        System.out.println("\nEl vector en forma 1 queda: ");
        for(int i = 0; i < vec[0] + 2; i++){
            System.out.print(vec[i] + " ");
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
    
    //Copia el vector en uno con grado superior
    public void redimensionar(int exp){
        PolvF1 R = new PolvF1(exp);
        R.vec[0] = exp;
        for(int i = 1; i < vec.length; i++){
            R.vec[R.vec[0] + 1 - (vec[0] + 1 - i)] = vec[i];
        }
        termino1 = R.termino1;
        vec = R.vec;
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

    //Inserta término, si hay término del mismo grado los suma
    public void insertarTerm(int coef, int exp){
        if (exp <= vec[0]){//Si el término a ingresar es menor que el grado del polinomio
                vec[vec[0] + 1 - exp] = vec[vec[0] + 1 - exp] + coef;
                this.ajustar();
            }else{
                this.redimensionar(exp);
                vec[1] = coef;
            }
    }
    
    public void eliminarTerm(){
        
    }

    //Reduce el tamaño de vector si hay ceros en los primeros términos del polinomio
    public void ajustar(){
        int k = 1, j = vec[0];
        while(vec[k] == 0){//Halla el grado del nuevo polinomio
            j = j - 1;
            k++;
        }
        PolvF1 R = new PolvF1(j);
        for (int i = 1; i < R.vec.length; i++) {//copia los datos útiles al nuevo vector
            R.vec[i] = vec[vec[0] + 1 - (R.vec[0] + 1 - i)];
        }
        vec = R.vec;//Asigna el nuevo vector al objeto original
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
