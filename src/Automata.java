public class Automata {
    private int palabrasReservadas;
    private int identificadores;
    private int operadoresRelacionales;
    private int operadoresLogicos;
    private int operadoresAritmeticos;
    private int incrementos;
    private int decrementos;
    private int asignaciones;
    private int numerosEnteros;
    private int numerosDecimales;
    private int cadenasCaracteres;
    private int comentarios;
    private int comentariosLinea;
    private int parentesis;
    private int llaves;
    private int errores;
    private String[] listaPalabrasReservadas =
            {"if", "main", "else", "switch",
                    "case", "default", "for", "do",
                    "while", "break", "int", "String",
                    "double", "char", "print"};
    private String palabraCompleta;
    public void evaluar(String palabra) {
        palabraCompleta = palabra;
        palabra = palabra.concat(" ");
        char simboloActual = palabra.charAt(0);
        //identificadores
        if (Character.isAlphabetic(simboloActual)) {
            identificador(palabra.substring(1));
        }
        //operadores relacionales
        if (simboloActual == '<' || simboloActual == '>') {
            menorMayorQue(palabra.substring(1));
        }

        //operadores logicos
        if (simboloActual == '&') {
            and(palabra.substring(1));
        }
        if (simboloActual == '|') {
            or(palabra.substring(1));
        }
        if (simboloActual == '!') {
            admiracion(palabra.substring(1));
        }
        //operadores aritmeticos
        if (simboloActual == '*' || simboloActual == '%') {
            operadorAritmetico(palabra.substring(1));
        }
        if (simboloActual == '/') {
            diagonal(palabra.substring(1));
        }

        //suma
        if (simboloActual == '+') {
            suma(palabra.substring(1));
        }
        //resta
        if (simboloActual == '-') {
            resta(palabra.substring(1));
        }
        //asignacion
        if (simboloActual == '=') {
            asignacion(palabra.substring(1));
        }
        //numeros
        if (Character.isDigit(simboloActual)) {
            numeroEntero(palabra.substring(1));//revisa si empieza con un numero
        }
        if (simboloActual == '.') {//FALTA ARREGLAR
            numeroDecimal(palabra.substring(1));//revisa si empieza con un .
        }
        //cadenas de caracteres FALTA ARREGLAR
        if (simboloActual == '"') {
            textoCadenaCaracteres(palabra.substring(1));
        }
        //parentesis
        if (simboloActual == '(' || simboloActual == ')') {
            parentesis(palabra.substring(1));
        }
        //llaves
        if (simboloActual == '{' || simboloActual == '}') {
            llave(palabra.substring(1));
        }

    }

    private void identificador(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (Character.isLetterOrDigit(simboloActual) || simboloActual == '_') {
            identificador(palabra.substring(1));
        } else if (simboloActual == ' ') {
            for(String id : listaPalabrasReservadas){
                if(palabraCompleta.equals(id)){
                    palabrasReservadas++;
                    identificadores--;
                }
            }
            identificadores++;
        } else {
            errores++;
        }
    }

    private void operadorRelacional(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadoresRelacionales++;
        } else {
            errores++;
        }
    }

    private void admiracion(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadorLogico(palabra);
        } else if (simboloActual == '=') {
            operadorRelacional(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void and(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '&') {
            operadorLogico(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void or(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '|') {
            operadorLogico(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void asignacion(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '=') {
            operadorRelacional(palabra.substring(1));
        } else if (simboloActual == ' ') {
            asignaciones++;
        } else {
            errores++;
        }
    }

    private void operadorLogico(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadoresLogicos++;
        } else {
            errores++;
        }
    }

    private void menorMayorQue(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadorRelacional(palabra);
        } else if (simboloActual == '=') {
            operadorRelacional(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void operadorAritmetico(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadoresAritmeticos++;
        } else {
            errores++;
        }
    }

    private void diagonal(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            operadorAritmetico(palabra);
        } else if (simboloActual == '*') {
            asterisco(palabra.substring(1));
        } else if (simboloActual == '/') {
            comentarioLinea(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void suma(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '+') {
            incremento(palabra.substring(1));
        } else if (simboloActual == ' ') {
            operadorAritmetico(palabra);
        } else {
            errores++;
        }
    }

    private void resta(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '-') {
            decremento(palabra.substring(1));
        } else if (simboloActual == ' ') {
            operadorAritmetico(palabra);
        } else if (Character.isDigit(simboloActual)) {
            numeroEntero(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void incremento(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            incrementos++;
        } else {
            errores++;
        }
    }

    private void decremento(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            decrementos++;
        } else {
            errores++;
        }
    }

    private void numeroEntero(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (Character.isDigit(simboloActual)) {
            numeroEntero(palabra.substring(1));
        } else if (simboloActual == '.') {
            numeroDecimal(palabra.substring(1));
        } else if (simboloActual == ' ') {
            numerosEnteros++;
        } else {
            errores++;
        }
    }

    private void numeroDecimal(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            numerosDecimales++;
        } else if (Character.isDigit(simboloActual)) {
            numeroDecimal(palabra.substring(1));
        } else {
            errores++;
        }
    }

    private void cadenaCaracteres(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            cadenasCaracteres++;
        } else {
            errores++;
        }
    }

    private void textoCadenaCaracteres(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '"') {
            cadenaCaracteres(palabra.substring(1));
        } else if (simboloActual == ' ') {
            errores++;
        } else {
            textoCadenaCaracteres(palabra.substring(1));
        }
    }

    private void comentario(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (palabra.isBlank()) {
            comentarios++;
        } else {
            errores++;
        }
    }

    private void asterisco(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == '*') {
            asterisco(palabra.substring(1));
        } else if (simboloActual == '/') {
            comentario(palabra.substring(1));
        } else if (simboloActual == ' ') {
            errores++;
        } else {
            textoComentario(palabra.substring(1));
        }
    }

    private void textoComentario(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            errores++;
        } else if (simboloActual == '*') {
            asterisco(palabra.substring(1));
        } else {
            textoComentario(palabra.substring(1));
        }
    }

    private void comentarioLinea(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            comentariosLinea++;
        } else {
            comentarioLinea(palabra.substring(1));
        }
    }

    private void parentesis(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            parentesis++;
        } else {
            errores++;
        }
    }

    private void llave(String palabra) {
        char simboloActual = palabra.charAt(0);
        if (simboloActual == ' ') {
            llaves++;
        } else {
            errores++;
        }
    }


    public int getPalabrasReservadas() {
        return palabrasReservadas;
    }

    public int getIdentificadores() {
        return identificadores;
    }

    public int getOperadoresRelacionales() {
        return operadoresRelacionales;
    }

    public int getOperadoresLogicos() {
        return operadoresLogicos;
    }

    public int getOperadoresAritmeticos() {
        return operadoresAritmeticos;
    }

    public int getIncrementos() {
        return incrementos;
    }

    public int getDecrementos() {
        return decrementos;
    }

    public int getAsignaciones() {
        return asignaciones;
    }

    public int getNumerosEnteros() {
        return numerosEnteros;
    }

    public int getNumerosDecimales() {
        return numerosDecimales;
    }

    public int getCadenasCaracteres() {
        return cadenasCaracteres;
    }

    public int getComentarios() {
        return comentarios;
    }

    public int getComentariosLinea() {
        return comentariosLinea;
    }

    public int getParentesis() {
        return parentesis;
    }

    public int getLlaves() {
        return llaves;
    }

    public int getErrores() {
        return errores;
    }
}
