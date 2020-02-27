/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1polinomios;
import java.util.Scanner;

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

    private static PolvF1 p1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }//main
    
    public static void menu(){
        System.out.println("\n-----------------MENÚ-------------------");
        System.out.println("(0) * Ingresar Polinomio");
        System.out.println("(1) * Mostrar Polinomio (Vector - Lista)");
        System.out.println("(2) * Armar polinomio");
        System.out.println("(3) * Insertar término");
        System.out.println("(4) * Eliminar término");
        System.out.println("(5) * Evaluar");
        System.out.println("(6) * +, *, / ");
        System.out.println("(7) * F1 + F2 = F3 ");
        System.out.println("(8) * Salir ");
        System.out.println("----------------------------------------\n");
        System.out.print("Ingrese una opción (0-7) -> ");
        Scanner ingreso = new Scanner (System.in);
        opciones(ingreso.nextLine());
    }
    
    public static void opciones(String o){
        if(isNumeric(o)){
            int n = Integer.parseInt(o);
            switch (n){
                case 0:
                    System.out.println("Ingrese el polinomio: ");
                    Scanner ingreso = new Scanner (System.in);
                    castString(ingreso.nextLine());
                    break;
                case 1:
                    print(p1);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Ingreso no válido");
                    menu();
            }
        }else{
            System.out.println("Ingreso no válido");
            menu();
        }
    }
    
    public static boolean isNumeric(String s){
        for (int i = 0; i < s.length(); i++){
            if( !Character.isDigit(s.charAt(i)) )
                return false;
        }
        return true;            
    }
    
    public static int[] castString(String sCadena){
        //String sCadena = "-3-x^2+45x^4-x";    //String a convertir
        char aCaracteres[];                    //Instancia de vector de caracteres
        aCaracteres = sCadena.toCharArray();    //Conversión de cadena a arreglo de caracteres
        
        int vectorOrganizador[] = new int[sCadena.length()];  //Vector transitorio de enteros para organizar
        int vectorIntFinal[];
        String sGuardar = "";                   //String para guardar dígitos mayores a un caracter
        int b = 0;                              //Incremento para controlar los datos útiles
        
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
        
        vectorIntFinal = new int[b];
        //Imprimir vector organizador
        System.arraycopy(vectorOrganizador, 0, vectorIntFinal, 0, b);
            
        vecToShapes(vectorIntFinal);
        return vectorIntFinal;
    }// fin castString
    
    public static void vecToShapes(int[] vec){
        //capturar el grado del polinomio
        int grade = 0;
        for(int i = 1; i < vec.length; i = i + 2){
            if (vec[i] > grade){
                grade = vec[i];
            }
        }
        //Crear vector en FORMA 1
        p1 = new PolvF1(grade); //vector en forma 1
        for(int x = 1; x < vec.length; x = x + 2){ //Posicioines impares
            p1.vec[grade + 1 - vec[x]] = vec[x - 1];
        }
        menu();
    }
    
    public static void print(PolvF1 p){
        
        //Imprimir vector en F1
        System.out.println("El vector en forma 1 queda: ");
        for(int i = 0; i < p.vec[0] + 2; i++){
            System.out.print(p.vec[i] + " ");
        }
        
        menu();
        
        //Imprimir vector normal
        
//        String sImprVec = "";
//        
//        if(p1.vec[0] > 1){
//            sImprVec = sImprVec + p1.vec[0] + "x^" + p1.vec[0];
//        }
        
//        for(int k = 2; k < vec[0]; k++){
//            if(vec[k] != 0){
//                if(vec[k] > 1){
//                    sImprVec = sImprVec + "+" + vec[k] + "x^" + (vec[0] + 1 - k);
//                }else{
//                    sImprVec = sImprVec + vec[k] + "x^" + (vec[0] + 1 - k);
//                }
//            }
//        }
    }
    
}