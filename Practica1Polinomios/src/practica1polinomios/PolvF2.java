/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 * Objeto Polinomio en forma 2
 */
public class PolvF2 extends PolinomioVector{
    
    //Constructor
    public PolvF2 (int terms){
        this.termino0 = terms;
        this.vec = new float[terms * 2 + 1];
        vec[0] = terms;
    }
    
    //Métodos
    
    //Convierte un string de coeficientes y exponentes en un polinomio forma 2
    public static PolvF2 toF2(int[] vec){
        //capturar el número de términos del polinomio
        int terms = vec.length / 2;
        //Crear vector en FORMA 2
        PolvF2 p = new PolvF2(terms); //vector en forma 2
        //ordenar polinomio
        int a;
        for(int i = 1; i < vec.length - 2; i = i + 2){
            for(int j = 1; j < vec.length - 2; j = j + 2){
                int x = vec[i]; int y = vec[i + 2];
                if(vec[j] < vec[j + 2]){
                    a = vec[j - 1];
                    vec[j - 1] = vec[j + 1];
                    vec[j + 1] = a;
                    a = vec[j];
                    vec[j] = vec[j + 2];
                    vec[j + 2] = a;
                }
            }
        }
        for(int x = 0; x < vec.length; x++){ //Posicioines impares
            p.vec[x + 1] = vec[x];
        }
        return p;
    }
    
    //Convierte el polinomio de forma 1 a escritura normal
    public void mostrarf2(){
        System.out.println("\nDel vector en forma 2 queda: ");
        for(int k = 1; k < vec.length - 1; k = k + 2){
            switch ((int) vec[k + 1]) {
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
                        System.out.print(vec[k] + "x^" + (vec[k + 1]));
                    }else if (k == 1 && vec[k] == 1){//si el coeficiente es 1 en la primera posición
                        System.out.print("x^" + (vec[k + 1]));
                    }else if(vec[k] == 1){//Si el coeficiente es 1
                        System.out.print("+x^" + (vec[k + 1]));
                    }else if(vec[k] == -1){
                        System.out.print("-x^" + (vec[k + 1]));
                    }else{
                        System.out.print("+" + vec[k] + "x^" + (vec[k + 1]));
                    }   break;
            }
        }
    }
    
    //Imprime el polinomio en forma 2
    public void printf2(){
        System.out.println("\nEl vector en forma 2 queda: ");
        for(int i = 0; i < vec.length; i++){
            System.out.print(vec[i] + " ");
        }
    }
    
    //Evalúa la variable del polinomio
    public float evaluar(float x){
        float r = 0;
        for(int i = 1; i < vec.length; i = i + 2){
            r  = (float) (r + (vec[i] * Math.pow(x,(vec[i + 1]))));
        }
        return r;
    }
    
    //Verifica si existe un término en el polinomio de un grado determinado
    public int exists (int exp){
        int pos = 0;
        for(int i = 2; i < vec.length; i = i + 2){
            if(exp == vec[i])
                pos = i;
        } 
        return pos;
    }
    
    //Inserta término, si hay término del mismo grado los suma
    public void insertarTerm(int coef, int exp){
        int pos = this.exists(exp);
        if(pos != 0){
            vec[pos - 1] = vec[pos - 1] + coef;
            this.ajustar();
        }else{
            this.redimensionar((int)vec[0] + 1, coef, exp);
        }
    }
    
    //Reduce el tamaño de vector si hay ceros en los primeros términos del polinomio
    public void ajustar(){
        int j = 0;
        for(int k = 1; k < vec.length; k = k + 2){//Cuenta los términos con coeficiente cero
            if (vec[k] == 0)
                j++;
        }
        if(j != 0){
            PolvF2 R = new PolvF2((int)vec[0] - j);//Crea el objeto polinomio con el nuevo número de términos
            int k = 1;
            for (int i = 1; i < vec.length; i = i + 2) {//copia los datos útiles al nuevo vector
                if(vec[i] != 0){
                    R.vec[k] = vec[i];
                    R.vec[k + 1] = vec[i + 1];
                    k = k + 2;
                }
            }
            termino0 = termino0 - j;
            vec = R.vec;//Asigna el nuevo vector al objeto original
        }
    }
    
    //Copia el vector en otro con grado superior e inserta un nuevo término
    public void redimensionar(int terms, int coef, int exp){
        PolvF2 R = new PolvF2(terms);
        int b = 1;
        boolean z = true;
        for(int i = 1; i < R.vec.length; i = i + 2){
            if(vec[b + 1] < exp && z){
                R.vec[i] = coef;
                R.vec[i + 1] = exp;
                z = false;
            }else{
                R.vec[i] = vec[b];
                R.vec[i + 1] = vec[b + 1];
                b = b + 2;
            }
        }
        termino0 = R.termino0;
        vec = R.vec;
    }
    

}
