/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1polinomios;

/** Entrega 6 de Marzo
 * 
 * Ingresar Polinomio
 * Mostrar Polinomio (Vector - Lista)
 * Armar polinomio Ej: 2x^5+3x^2...
 * Insertar término
 * Eliminar término
 * Evaluar
 * +, *, / 
 * F1 + F2 = F3 
 * 
 * @authors Juan Carlos Salazar Muñoz
 *          Emanuel Muñoz
 */
public class Practica1Polinomios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String sCadena = "-3-x^2+45x^4-x";       //String a convertir
        char aCaracteres[];                    //Instancia de vector de caracteres
        aCaracteres = sCadena.toCharArray();    //Conversión de cadena a arreglo de caracteres
        
        int vectorOrganizador[] = new int[10];  //Vector transitorio de enteros para organizar
        String sGuardar = "";                   //String para guardar dígitos mayores a un caracter
        int b = 0;                              //Incremento para controlar los datos útiles
        
        //int vec[];                              //Vector en forma 1
        
        for (int x = 0; x < aCaracteres.length; x++){ //Ciclo para leer el arreglo de caracteres
            
            // Si es negativo
            if(String.valueOf(aCaracteres[x]).equals("-")){
                if(Character.isDigit(aCaracteres[x + 1]) == false){//Si el siguiente caracter no es numérico
                    vectorOrganizador[b] = -1; //Imprime el -1
                    sGuardar = "";
                    b++;
                }else{
                    sGuardar = "-";//Guarda el -
                }
            }
            
            //Si es positivo antecedido de caracter no numérico
            if(String.valueOf(aCaracteres[x]).equals("+") && Character.isDigit(aCaracteres[x + 1]) == false){
                vectorOrganizador[b] = 1; //Imprime el -1
                sGuardar = "";
                b++;
            }
            
            //Si es numérico
            if(Character.isDigit(aCaracteres[x])){
                sGuardar = sGuardar + aCaracteres[x];//Concatena el dígito
                if(x + 1 < aCaracteres.length){//Si no está en la última posición
                   if(Character.isDigit(aCaracteres[x + 1]) == false){
                        if(b % 2 == 0 && (String.valueOf(aCaracteres[x + 1]).equals("+") 
                                || String.valueOf(aCaracteres[x + 1]).equals("-"))){//Si es coeficiente
                            vectorOrganizador[b] = Integer.parseInt(sGuardar);//Imprime el número
                            vectorOrganizador[b + 1] = 0;//Imprime el exponente de término independiente
                            b = b + 2; //Guarda posición de datos útiles
                            sGuardar = "";
                        }else{
                            vectorOrganizador[b] = Integer.parseInt(sGuardar);//Imprime el número
                            b = b + 1;
                            sGuardar = "";
                        }
                    }
                }else if (x + 1 == aCaracteres.length){//Si es el último valor
                    vectorOrganizador[b] = Integer.parseInt(sGuardar);//Imprime el número
                    if(b % 2 == 0){//Si es coeficiente
                        vectorOrganizador[b + 1] = 0;//Imprime el exponente de término independiente
                        b = b + 2; //Guarda posición de datos útiles
                    }else{
                        b++; //Guarda posición de datos útiles
                    }              
                }
            }

            //Si es una "x"
            if(String.valueOf(aCaracteres[x]).equals("x") || String.valueOf(aCaracteres[x]).equals("X")){
                if(x == 0 && String.valueOf(aCaracteres[x + 1]).equals("^")){ //Si la x está en la primera posición y tiene coeficiente 1
                    vectorOrganizador[b] = 1;
                    b++;
                }
                if (x + 1 == aCaracteres.length){//Si está en última posición
                    vectorOrganizador[b] = 1; //Guarda el exponente 1
                    sGuardar = "";
                    b++;
                }else if(String.valueOf(aCaracteres[x + 1]).equals("^")== false){//Si no está en última posición ni antecedida de "^"
                    vectorOrganizador[b] = 1; //Guarda el exponente 1
                    sGuardar = "";
                    b++;
                }
            }
        }//cierre for
        
        //Imprimir vector organizador
        for (int x = 0; x < b; x++)
            System.out.print(vectorOrganizador[x] + "  ");
        
        System.out.println("");
        
        //Crear vector en FORMA 1 
        int vec[] = new int[b + 2]; //vector en forma 1
        int g = 0;//Grado del constructor
        for(int x = 1; x < vectorOrganizador.length; x = x + 2){ //Posicioines impares
            if (vectorOrganizador[x] > g)
                g = vectorOrganizador[x];
        }
        
        vec[0] = g; //Grado del vector
        for(int x = 1; x < b; x = x + 2) //Posicioines impares
            vec[vec[0] + 1 - vectorOrganizador[x]] = vectorOrganizador[x - 1];
        
        //Imprimir vector en F1
        for (int x = 0; x < vec[0] + 2; x++)//Imprimir vector organizador
            System.out.print(vec[x] + "  ");
        
        System.out.println("");
        
        //Imprimir vector normal
        
        String sImprVec = "";
        
        if(vec[0] > 1){
            sImprVec = sImprVec + vec[1] + "x^" + vec[0];
        }
        
        for(int k = 2; k < vec[0]; k++){
            if(vec[k] != 0){
                if(vec[k] > 1){
                    sImprVec = sImprVec + "+" + vec[k] + "x^" + (vec[0] + 1 - k);
                }else{
                    sImprVec = sImprVec + vec[k] + "x^" + (vec[0] + 1 - k);
                }
            }
        }
        
    }
}
