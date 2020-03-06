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
        for(int i = 1; i < vec.length - 2; i = i + 2){//Ordena el vector
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
            PolvF2 R = new PolvF2(getTam() - j);//Crea el objeto polinomio con el nuevo número de términos
            int k = 1;
            for (int i = 1; i < vec.length; i = i + 2) {//copia los datos útiles al nuevo vector
                if(vec[i] != 0){
                    R.vec[k] = vec[i];
                    R.vec[k + 1] = vec[i + 1];
                    k = k + 2;
                }
            }
            this.termino0 = R.getTam();
            this.vec = R.vec;//Asigna el nuevo vector al objeto original
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
    
    public void eliminarTerm(int exp){
        if (exp <= vec[2]){//Si el término a ingresar es menor que el grado del polinomio
            for(int i = 2; i < vec.length; i = i + 2){
                if (vec[i] == exp){
                    vec[i - 1] = 0;
                    vec[i] = 0;
                }
            }
            this.ajustar();
        }
    }
    
    //Suma dos polinomios
    public PolvF2 sumar (PolvF2 B){
        int k = 1, j = 1, pos = 1;
        PolvF2 R = new PolvF2(getTam() + B.getTam()); //Crea el polinomio resultante
        while ((k < vec.length) && (j < B.vec.length)){ //Recorre ambos vectores
            if(vec[k + 1] > B.vec[j + 1]){//Si el exponente del primer sumando es mayor
                R.setDato((int)vec[k], pos);//Asigna el coeficiente
                R.setDato((int)vec[k + 1], pos + 1);//Asigna el exponente
                k = k + 2;//Incrementa posición del primer sumando
            }else{
                if(B.vec[j + 1] > vec[k + 1]){//Si el exponente del segundo sumando es mayor que el primero
                    R.setDato(B.getDato(j), pos);//Asigna el coeficiente
                    R.setDato(B.getDato(j + 1), pos + 1);//Asigna el exponente
                    j = j + 2;//Incrementa posición del segundo sumando
                }else{//Si ambos exponetes son iguales
                    R.setDato((int)vec[k] + B.getDato(j), pos);//Asigna el coeficiente
                    R.setDato(B.getDato(j + 1), pos + 1);//Asigna el exponente
                    k = k + 2;//Incrementa posición del primer sumando
                    j = j + 2;//Incrementa posición del segundo sumando
                }
            }
            pos = pos + 2;//Incrementa la posición del vector resultante
        }
        while((k < vec.length)){ //Termina de sumar si quedan términos en el primer sumando
            R.setDato(vec[k], pos); //Coeficiente
            R.setDato(vec[k + 1], pos + 1);//Exponente
            k = k + 2;
            pos = pos + 2;
        }
        while((j < B.vec.length)){ //Termina de sumar si quedan términos en el segundo sumando
            R.setDato(B.vec[j], pos); //Coeficiente
            R.setDato(B.vec[j + 1], pos + 1);//Exponente
            j = j + 2;
            pos = pos + 2;
        }
        R.ajustar();
        return (R);
    }
    
    //multiplica dos polinomios
    public PolvF2 multiplicar (PolvF2 B){
        PolvF2 ps = new PolvF2(B.getTam());
        PolvF2 pr = new PolvF2(B.getTam());
        int pos;
        for (int i = 2; i < vec.length; i = i + 2){
            pos = 2;
            ps.clean();
            for(int j = 2; j < B.vec.length; j = j + 2){
                ps.setDato(vec[i - 1] * B.vec[j - 1], pos - 1);
                ps.setDato(vec[i] + B.vec[j], pos);
                pos = pos + 2;
            }
            pr = pr.sumar(ps);
        }
        pr.ajustar();
        return (pr);
    }
    
    //divide dos polinomios
    public PolvF2 dividir (PolvF2 B){
        PolvF2 cociente = new PolvF2(getDato(2) + B.getDato(2));//Crea el cociente
        PolvF2 aux = new PolvF2(getDato(2) + B.getDato(2));
        PolvF2 residuo = new PolvF2(getTam());
        float dato;
        int pos = 1;//Controla la posición del cociente
        System.arraycopy(vec, 1, residuo.vec, 1, vec.length - 1);
        while (residuo.getDato(2) >= B.getDato(2)) {            
            dato = residuo.vec[1] / B.vec[1];
            cociente.setDato(dato, pos);//Coeficiente
            cociente.setDato(residuo.vec[2] - B.vec[2], pos + 1);//Exponente
            for(int j = 1; j < B.vec.length; j = j + 2){//Multiplica el término del cociente por el dividendo
                dato = -cociente.vec[pos] * B.vec[j];//Coeficiente
                aux.setDato(dato, j);
                aux.setDato(cociente.vec[pos + 1] + B.vec[j + 1], j + 1);//Exponente
            }
            residuo = residuo.sumar(aux);
            residuo.ajustar();
            aux.clean();
            pos = pos + 2;
        }
        cociente.ajustar();
        return(cociente);
    }

}
