/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practica1polinomios;
import static java.lang.Double.parseDouble;
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

    //Variables globales
    private static PolvF1 p1, b1, r1;
    private static PolvF2 p2, b2, r2;
    private static PolvF3 p3, b3, r3;
    private static final Scanner INGRESO = new Scanner (System.in);

    //Método principal
    public static void main(String[] args) {      
        menu();
    }
    
    //menú de opciones
    public static void menu(){
        System.out.println("\n\n-----------------MENÚ-------------------");
        System.out.println("(1) * Ingresar Polinomio");
        System.out.println("(2) * Mostrar Polinomio (Vector - Lista)");
        System.out.println("(3) * Armar polinomio");
        System.out.println("(4) * Insertar término");
        System.out.println("(5) * Eliminar término");
        System.out.println("(6) * Evaluar");
        System.out.println("(7) * Suma");
        System.out.println("(8) * Multiplicación");
        System.out.println("(9) * Dividir");
        System.out.println("(10)* F1 + F2 = F3 ");
        System.out.println("(0) * Salir ");
        System.out.println("----------------------------------------\n");
        System.out.print("Ingrese una opción (0-7) -> ");
        opciones(INGRESO.nextLine());
    }
    
    //Vuelve a mostrar el menú
    public static void again(){
        System.out.print("\n\nPresiona la tecla ENTER para continuar");
        INGRESO.nextLine();
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
                    print();
                    again();
                case 3:
                    rebuild();
                    again();
                case 4:
                    insertar();
                    again();
                case 5:
                    terminate();
                    again();
                case 6:
                    evaluate();
                    again();
                case 7:
                    plus();
                    again();
                case 8:
                    multiply();
                    again();
                case 9:
                    divide();
                    again();
                case 10:
                    allInOne();
                    again();
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
    
    //Valida si un número es real
    public static boolean isFloat(String s){
        if("".equals(s) || (s == null ? ("\"" + s + "\"") == null : s.equals("\"" + s + "\""))){//Si se envía un enter
            return false;
        }
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-'){//Si en la primera posición no hay "-" o números
            return false;
        }
        int control = 0;
        for (int i = 1; i < s.length(); i++){//valida caracter por caracter si es dígito
            if(!Character.isDigit(s.charAt(i))){
                if (s.charAt(i) == '.' && control == 0) {
                    control++;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    
    //Valida si hay polinomios creados
    public static boolean isNotNull(){
        if(p1 == null){
            System.out.println("\nIngrese primero un polinomio (Opción 1)");
            return false;
        }else
            return true;
    }
    
    //Realiza el ingreso de un polinomio nuevo
    public static void introduce(){
        System.out.print("\nIngrese el polinomio: ");
        vecToShapesP(castString(INGRESO.nextLine()));//Convierte el vector en las tres formas
    }

    //Convierte el string con un polinomio en un vector de coeficientes y exponentes
    //validar dos términos con el mismo grado
    //validar si termina en "^"
    //validar si tiene otros datos diferentes a x, ^, +, -
    
    //Devuelve una cadena de enteros con coeficientes (pares) y exponentes (impares) de un polinomio exp
    public static int[] castString(String sCadena){
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
        return vectorIntFinal;
    }// fin castString
    
    //Convierte el polinomio a las tres formas
    public static void vecToShapesP(int[] vec){
        p1 = PolvF1.toF1(vec);//pasa el vector de enteros a f1
        p2 = PolvF2.toF2(vec);//pasa el vector de enteros a f2
        p3 = PolvF3.toF3(vec);//pasa el vector de enteros a f3
        System.out.println("Vector ingresado.");
    }
    
    //Imprime el polinomio en cada forma
    public static void print(){
        if(isNotNull()){
            p1.printf1();//Imprimir vector en F1  
            p2.printf2();//Imprimir vector en F2 
            p3.printf3();//Imprimir vector en F3
        }
    }
    
    //Desde las tres formas, convierte el polinomio a escritura normal
    public static void rebuild(){
        if(isNotNull()){
            p1.mostrarf1();
            p2.mostrarf2();
            p3.mostrarf3();
        }
    }
    
    //Inserta un nuevo término al polinomio
    public static void insertar(){
        if(isNotNull()){
            System.out.println("\nIngrese un dato para el coeficiente: ");
            String cS = INGRESO.nextLine();
            while(!isNumeric(cS)){//Mientras no sea numérico
                System.out.print("\nIngrese un dato válido para el coeficiente: ");
                cS = INGRESO.nextLine();
            }
            System.out.println("\nIngrese un dato para el exponente: ");
            String eS = INGRESO.nextLine();
            boolean z = true;
            while(z){//Mientras no sea un dato numérico y positivo
                if(!isNumeric(eS)){
                    System.out.print("\nIngrese un dato numérico para el exponente: ");
                    eS = INGRESO.nextLine();
                }else if(Integer.parseInt(eS) < 0){
                    System.out.print("\nIngrese un dato no negativo para el exponente: ");
                    eS = INGRESO.nextLine();
                }else{
                    z = false;
                }
            }            
            p1.insertarTerm(Integer.parseInt(cS), Integer.parseInt(eS));
            p2.insertarTerm(Integer.parseInt(cS), Integer.parseInt(eS));
            p3.insertarTerm(Integer.parseInt(cS), Integer.parseInt(eS));
            System.out.println("Término agregado");
        }
    }
    
    //Elimina término del polinomio por exponente
    public static void terminate(){
        if(isNotNull()){
            System.out.println("\nIngrese un exponente correspondiente al término a eliminar: ");
            String eS = INGRESO.nextLine();
            boolean z = true;
            while(z){//Mientras sea un dato numerico, positivo y menor al grado del polinomio
                if(!isNumeric(eS)){
                    System.out.print("\nIngrese un dato numérico para el exponente: ");
                    eS = INGRESO.nextLine();
                }else if(Integer.parseInt(eS) < 0){
                    System.out.print("\nIngrese un dato no negativo para el exponente: ");
                    eS = INGRESO.nextLine();
                }else if(Integer.parseInt(eS) > p1.getTam()){
                    System.out.print("\nIngrese un dato igual o menor al grado del polinomio: ");
                    eS = INGRESO.nextLine();
                }else{
                    z = false;
                }
            }
            int n = Integer.parseInt(eS);
            p1.eliminarTerm(n);
            p2.eliminarTerm(n);
            p3.eliminarTerm(n);
            System.out.println("Término eliminado");
        }
    }
    
    //Evalua el polinomio
    public static void evaluate(){
        if(isNotNull()){
            System.out.print("Ingrese el valor de x: ");
            String eS = INGRESO.nextLine();
            if(eS.contains(",")){
                eS = eS.replace(",", ".").trim();
            }
            boolean z = true;
            while(z){//Mientras sea un float
                if(!isFloat(eS)){
                    System.out.print("\nIngrese un número real para el exponente: ");
                    eS = INGRESO.nextLine();
                    if(eS.contains(",")){
                        eS = eS.replace(",", ".").trim(); //Cambia las comas por 
                    }
                }else{
                    z = false;
                }
            }
            float f = Float.parseFloat(eS);
            System.out.print("Desde la forma 1 el resultado es: ");
            System.out.println(p1.evaluar(f));
            System.out.print("Desde la forma 2 el resultado es: ");
            System.out.println(p2.evaluar(f));
            System.out.print("Desde la forma 3 el resultado es: ");
            System.out.println(p3.evaluar(f));
        }
    }
    
    //Convierte el polinomio a las tres formas para operar
    public static void vecToShapesB(int[] vec){
        b1 = PolvF1.toF1(vec);//pasa el vector de enteros a f1
        b2 = PolvF2.toF2(vec);//pasa el vector de enteros a f2
        b3 = PolvF3.toF3(vec);//pasa el vector de enteros a f3
    }
    
    //Suma el nuevo polinomio que le ingresen
    public static void plus(){
        if(isNotNull()){
            System.out.print("\nIngrese el polinomio para sumar: ");
            vecToShapesB(castString(INGRESO.nextLine()));//Convierte el vector en las tres formas
            r1 = p1.sumar(b1);
            r2 = p2.sumar(b2);
            r3 = p3.sumar(b3);
            r1.mostrarf1();
            r2.mostrarf2();
            r3.mostrarf3();
        }
        
    }
    
    //Multiplica por el nuevo polinomio que le ingresen
    public static void multiply(){
        if(isNotNull()){
            System.out.print("\nIngrese el polinomio para multiplicar: ");
            vecToShapesB(castString(INGRESO.nextLine()));//Convierte el vector en las tres formas
            r1 = p1.multiplicar(b1);
            r2 = p2.multiplicar(b2);
            r3 = p3.multiplicar(b3);
            r1.mostrarf1();
            r2.mostrarf2();
            r3.mostrarf3();
        }
    }
    
    //Divide por el polinomio que le ingresen
    public static void divide(){
        if(isNotNull()){
            System.out.print("\nIngrese el polinomio para dividir: ");
            vecToShapesB(castString(INGRESO.nextLine()));//Convierte el vector en las tres formas
            if(b1.getTam() <= p1.getTam()){
                r1 = p1.dividir(b1);
                r2 = p2.dividir(b2);
                r3 = p3.dividir(b3);
            System.out.print("\nEl polinomio resutante es: ");
            r1.mostrarf1();
            r2.mostrarf2();
            r3.mostrarf3();
            }else
                System.out.println("El grado del polinomio ingresado no es válido");
            
        }
    }
    
    public static void allInOne(){
        if(isNotNull()){
            System.out.print("\nIngrese el polinomio para sumar: ");
            vecToShapesB(castString(INGRESO.nextLine()));//Convierte el vector en las tres formas
            r3 = p1.sumarF1F2(b2);
            r3.mostrarf3();
        }
    }    
}