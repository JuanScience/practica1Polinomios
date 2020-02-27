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
    
    //menú de opciones
    public static void menu(){
        System.out.println("\n-----------------MENÚ-------------------");
        System.out.println("(1) * Ingresar Polinomio");
        System.out.println("(2) * Mostrar Polinomio (Vector - Lista)");
        System.out.println("(3) * Armar polinomio");
        System.out.println("(4) * Insertar término");
        System.out.println("(5) * Eliminar término");
        System.out.println("(6) * Evaluar");
        System.out.println("(7) * +, *, / ");
        System.out.println("(8) * F1 + F2 = F3 ");
        System.out.println("(0) * Salir ");
        System.out.println("----------------------------------------\n");
        System.out.print("Ingrese una opción (0-7) -> ");
        Scanner ingreso = new Scanner (System.in);
        opciones(ingreso.nextLine());
    }
    
    //Vuelve a mostrar el menú
    public static void again(){
        Scanner waitForKeypress = new Scanner(System.in);
        System.out.print("\n\nPresiona la tecla ENTER para continuar");
        waitForKeypress.nextLine();
        menu();
    }

    //Desición de elección del menú
    public static void opciones(String o){
        if(isNumeric(o)){
            int n = Integer.parseInt(o);
            switch (n){
                case 1:
                    introduce();
                    again();
                case 2:
                    print(p1);
                    again();
                case 3:
                    rebuild(p1);
                    again();
                case 4:
                    insertar();
                    again();
                case 5:
                    terminate();
                    again();
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Ingreso no válido");
                    again();
            }
        }else{
            System.out.println("Ingreso no válido");
            again();
        }
    }
    
    //Valida si un string es numérico
    public static boolean isNumeric(String s){
        if("".equals(s) || (s == null ? ("\"" + s + "\"") == null : s.equals("\"" + s + "\""))){//Si se envía un enter
            return false;
        }
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-'){//Si en la primera posición no hay "-" o números
            return false;
        }
        for (int i = 1; i < s.length(); i++){//valida caracter por caracter si es dígito
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    //Realiza el ingreso de un polinomio
    public static void introduce(){
        System.out.print("\nIngrese el polinomio: ");
        Scanner ingreso = new Scanner (System.in);
        castString(ingreso.nextLine());
    }
    
    //Convierte el string con un polinomio en un vector de coeficientes y exponentes
    public static int[] castString(String sCadena){
        //String sCadena = "-3-x^2+45x^4-x";    //String a convertir
        char aCaracteres[];                    //Instancia de vector de caracteres
        aCaracteres = sCadena.toCharArray();    //Conversión de cadena a arreglo de caracteres
        
        int vectorOrganizador[] = new int[sCadena.length() + 1];  //Vector transitorio de enteros para organizar
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
        
        vectorIntFinal = new int[b];//vector para retornar
        System.arraycopy(vectorOrganizador, 0, vectorIntFinal, 0, b);//Copia el vector
        vecToShapes(vectorIntFinal);//Convierte el vector en las tres formas
        return vectorIntFinal;
    }// fin castString
    
    //Convierte el polinomio a las tres formas
    public static void vecToShapes(int[] vec){
        p1 = PolvF1.toF1(vec);//pasa el vector de enteros a f1
        System.out.println("Vector ingresado.");
    }
    
    //Imprime el polinomio en cada forma
    public static void print(PolvF1 p){
        if(p == null){
            System.out.println("Ingrese primero un polinomio (Opción 1)");
        }else{
            p.printf1();//Imprimir vector en F1  
        }
    }
    
    //Desde las tres formas, convierte el polinomio a escritura normal
    public static void rebuild(PolvF1 p){
        if(p == null){
            System.out.println("Ingrese primero un polinomio (Opción 1)");
        }else{
            p.mostrarf1();
        }
    }
    
    //Inserta un nuevo término al polinomio
    public static void insertar(){
        if(p1 == null){
            System.out.println("\nIngrese primero un polinomio (Opción 1)");
        }else{
            System.out.println("\nIngrese un dato para el coeficiente: ");
            Scanner ingreso = new Scanner (System.in);
            String cS = ingreso.nextLine();
            while(!isNumeric(cS)){
                System.out.print("\nIngrese un dato válido para el Coeficiente: ");
                cS = ingreso.nextLine();
            }
            System.out.println("\nIngrese un dato para el exponente: ");
            String eS = ingreso.nextLine();
            boolean z = false;
            while(z){
                if(!isNumeric(eS)){
                    System.out.print("\nIngrese un dato numérico para el Coeficiente: ");
                    eS = ingreso.nextLine();
                }else if(Integer.parseInt(eS) < 0){
                    System.out.print("\nIngrese un dato no negativo para el Coeficiente: ");
                    eS = ingreso.nextLine();
                }else{
                    z = true;
                }
            }

            while(!isNumeric(eS)){
                System.out.print("\nIngrese un dato válido para el Coeficiente: ");
                eS = ingreso.nextLine();
            }
            
            p1.insertarTerm(Integer.parseInt(cS), Integer.parseInt(eS));
            System.out.println("Término agregado");
        }
    }
    
    public static void terminate(){
        
    }
    
}