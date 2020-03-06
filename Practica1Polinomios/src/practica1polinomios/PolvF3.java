/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1polinomios;

/**
 * Objeto Polinomio en forma 3
 */
public class PolvF3 {
    
    //Declaración de atributos
    private Nodo cab;
    
    //Constructor
    public PolvF3 (){
        cab = null;
    }
    
    //Métodos
    //Obtener cabeza de lista
    public Nodo getCab(){
        return cab;
    }
    
    //Convierte un string de coeficientes y exponentes en un polinomio forma 3
    public static PolvF3 toF3(int[] vec){ 
        //Crear vector en FORMA 3
        PolvF3 R = new PolvF3(); //vector en forma 3
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
        for(int i = 0; i < vec.length; i = i + 2){ //Posicioines impares
           R.almacenarTerm(vec[i], vec[i + 1]);
        }
        return R;
    }
    
    //Imprime el polinomio en forma 3
    public void printf3(){
        Nodo x = getCab();
        System.out.println("\nEl vector en forma 3 queda: ");
        while(x != null){
            System.out.print("(" + x.getCoef() + "," + x.getExp() + ") ");
            x = x.getLiga();
        }
    }
    
    //Mostrar los términos del polinomio en listas
    public void mostrarf3(){
        Nodo p = cab;
        if(p == null){
            System.out.println("El polinomio está vacío");
        }else{
            System.out.println("\nDel vector en forma 3 queda: ");
            while(p != null){
                if(p.getCoef() != 0){ //Si el coeficiente es diferente de cero
                    switch (p.getExp()) {
                    case 0: //Si es el término independiente                        
                        if(p == cab || p.getCoef() < 0){//Si está en la primera posicíón o es menor que cero
                            System.out.print(p.getCoef());
                        }else{
                            System.out.print("+" + p.getCoef());
                        }   break;
                    case 1: //Si es el termino x^1                        
                        if((p == cab && p.getCoef() > 1) || p.getCoef() < -1 ){//Si está en la primera posicíón y el coeficiente es mayor que 1, o el coeficiente menor que -1
                            System.out.print(p.getCoef() + "x");
                        }else if (p == cab && p.getCoef() == 1){//si el coeficiente es 1 en la primera posición
                            System.out.print("x");
                        }else if(p.getCoef() == 1){//Si el coeficiente es 1
                            System.out.print("+x");
                        }else if(p.getCoef() == -1){
                            System.out.print("-x");
                        }else{
                            System.out.print("+" + p.getCoef() + "x");
                        }   break;
                    default:
                        if((p == cab && p.getCoef() > 1) || p.getCoef() < -1 ){//Si está en la primera posicíón y el coeficiente es mayor que 1, o el coeficiente menor que -1
                            System.out.print(p.getCoef() + "x^" + p.getExp());
                        }else if (p == cab && p.getCoef() == 1){//si el coeficiente es 1 en la primera posición
                            System.out.print("x^" + p.getCoef());
                        }else if(p.getCoef() == 1){//Si el coeficiente es 1
                            System.out.print("+x^" + p.getCoef());
                        }else if(p.getCoef() == -1){
                            System.out.print("-x^" + p.getCoef());
                        }else{
                            System.out.print("+" + p.getCoef() + "x^" + p.getCoef());
                        }   break;
                    }
                }
                p = p.getLiga();//Captura la liga del siguiente término
            }
        }
    }
    
    //Almacenar un término en el polinomio
    public void almacenarTerm(float co, int ex){
        Nodo x, ant = null, p = cab;
        while(p != null && p.getExp() > 0){
            ant = p;
            p = p.getLiga();
        }
        if (p != null && p.getExp() == ex) {
            System.out.println("Ya existe un término con ese exponente");
        }else{
            x = new Nodo(co, ex);
            x.setLiga(p);
            if(p == cab){
                cab = x;
            }else{
                ant.setLiga(x);
            } 
        }
    }
    
    //Evaluar el polinomio
    public float evaluar(float x){
        float resultado = 0;
        Nodo p = cab;
        if (p == null) {
            System.out.println("El polinomio está vacío");
        }else{
            while (p != null) {
                resultado = (float) (resultado + (p.getCoef() * Math.pow(x, p.getExp())));
                p = p.getLiga();
            }
        }
        return resultado;
    }
    
    //Insertar un término en el polinomio
    public void insertarTerm(float co, int ex){
        if(co != 0){
            Nodo x, ant = null, p = cab;
            while(p != null && p.getExp() > ex){//Mientras el término del polinomio sea mayor al término ingresado
                ant = p;
                p = p.getLiga();
            }
            if (p != null && p.getExp() == ex) {//Si el término del polinomio es igual al insertado
                if (p.getCoef() + co != 0) { //Si la suma no es cero
                    p.setCoef(p.getCoef() + co);//Suma
                }else{
                    if (p == cab) {
                        cab = cab.getLiga();//Elimina el primer término
                    }else{
                        ant.setLiga(p.getLiga());//Elimina el término
                    }
                }
            }else{//Si el término ingresado es de menor grado que el último término del polinomio
                x = new Nodo(co,ex);
                x.setLiga(p);
                if(p == cab){//Si es el primer término ingtresado
                    cab = x;
                }else{
                    ant.setLiga(x);
                }
            }
        }
    }
    
    public void eliminarTerm(int exp){
        Nodo ant = null, p = cab;
        while(p != null && p.getExp() > 0){
            ant = p;
            p = p.getLiga();
        }
        if (p.getExp() == exp) {
            ant.setLiga(p.getLiga());
        }
    }
    
    //Sumar dos polinomios
    public PolvF3 sumar(PolvF3 B){
        Nodo p, q;
        p = cab;
        q = B.getCab();
        PolvF3 R = new PolvF3();
        while (p != null && q != null) {
            if (p.getExp() == q.getExp()) {
                if(p.getCoef() + q.getCoef() !=0){
                    R.insertarTerm(p.getCoef() + q.getCoef(), p.getExp());
                    p = p.getLiga();
                    q = q.getLiga();
                }
            }else{
                if (p.getExp() > q.getExp()) {
                    R.insertarTerm(p.getCoef(), p.getExp());
                    p = p.getLiga();
                }else{
                    R.insertarTerm(q.getCoef(), q.getExp());
                    q = q.getLiga();
                }
            }
        }
        while(p != null){
                R.insertarTerm(p.getCoef(), p.getExp());
                p = p.getLiga();
            }
            while(q != null){
                R.insertarTerm(q.getCoef(), q.getExp());
                q = q.getLiga();
            }
        return R;
    }
    
    //Multiplica dos polinomios
    public PolvF3 multiplicar (PolvF3 B){
        PolvF3 R = new PolvF3();
        Nodo i = getCab();
        while(i != null){
            PolvF3 aux = new PolvF3();
            Nodo j = B.getCab();
            while(j != null){
                aux.insertarTerm(i.getCoef() * j.getCoef(), i.getExp() + j.getExp());
                j = j.getLiga();
            }
            R = R.sumar(aux);
            i = i.getLiga();
        }        
        return R;
    }
    
    //Dividir dos polinomios
    public PolvF3 dividir(PolvF3 B){
        Nodo p = getCab(), q = B.getCab(), z;
        int expt, expA;
        float coeft, coeA;
        if (p == null || q == null) {
            System.out.println("Uno de los polinomios está vacío");
            return null;
        }else{
            if (cab.getExp() > q.getExp()) {
                PolvF3 copia = this.hacerCopia();
                PolvF3 R = new PolvF3();
                p = copia.getCab();
                while(p != null && q != null && (p.getExp() >= q.getExp())){
                    expt = p.getExp() - q.getExp();
                    coeft = p.getCoef() / q.getCoef();
                    R.insertarTerm(coeft, expt);
                    z = B.getCab();
                    while(z != null){
                        expA = expt + z.getExp();
                        coeA = coeft * -z.getCoef();
                        copia.insertarTerm(coeA, expA);
                        z = z.getLiga();
                    }
                    p = copia.getCab();
                    q = B.getCab();
                }
                return R;
            }else{
                System.out.println("No se pueden dividir los polinomios");
                return null;
            }
        }
    }
    
    //Hace una copia de la lista ligada del polinomio
    public PolvF3 hacerCopia(){
        PolvF3 R = new PolvF3();
        Nodo x = getCab();
        while(x != null){
            R.insertarTerm(x.getCoef(), x.getExp());
            x = x.getLiga();
        }
        return R;
    }
}
