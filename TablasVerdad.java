import java.util.Scanner;
import java.util.regex.Pattern;
/**
*	Clase que implementa metodos para obtener las tablas de verdad para una proposicion
*	logica.
*	@author Vazquez Alvarez Angel Eduardo
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/
public class TablasVerdad
{
	/**
	*	Metodo principal.
	*	@param args No utilizado.
	*/
    public static void main(String[] args)
    {
        //	Para leer la proposición lógica.
		Scanner s = new Scanner(System.in);        
		String expr;
		System.out.println("Programa que calcula la tabla de verdad de las siguientes proposiciones lógicas:");
		System.out.println("\t>> 1) ->\n\t>> 2) v\n\t>> 3) ^\n\t>> 4) 7\n\t>> 5) <->\n");
		System.out.print("Ingresa una proposición lógica como \"A^B\" para calcular su tabla de verdad: ");
		expr = s.nextLine();
        
        //  Contiene los operandos 
        String[] operandos = expr.split("\\s{0,}(\\^|v|->|<->)\\s{0,}");
        
        //  Contiene el operador en el índice 1 del arreglo
        String[] operador = expr.split("^(7?)[a-zA-Z]{1}\\s{0,}|\\s{0,}(7?)[a-zA-Z]{1}$");

        //  Si detectó la operacion:
        if(operador.length == 2)
        {
            switch(operador[1])
            {
                case "^":
                   generarAnd(operandos);
                   break;
                case "v":
                    generarOr(operandos);
                    break;
                case "->":
                    generarImplicacion(operandos);
                    break;
                case "<->":
                    generarBicondicional(operandos);
                    break;
                default:
                    System.out.println("No se ingresó una proposición válida.");
                    break;
            }
        }
        
        if(operandos.length == 1)
        {
            if(operandos[0].startsWith("7"))
            {
                generarNegacion(operandos);
            }
        }
    }
    
    /**
    *	Metodo que genera una tabla de verdad para la negacion de un operando. Ejemplo: 7a
    *	@param operandos Un arreglo que contiene al operando
    *
    */
    public static void generarNegacion(String[] operandos)
    {
        //  No se pueden utilizar ni T ni F
        for(int i = 0; i < operandos.length; i++)
        {
            if(operandos[i].contains("T") || operandos[i].contains("F"))
            {
                return;
            }
        }
            
        
        if(operandos.length == 1)
        {
            String operando = operandos[0].substring(1);
            
            boolean resultados[] = { true, false };
            
            System.out.printf("\t%8s\t|\t%8s\t\n", operando, operandos[0]);
            for(int i = 0; i < resultados.length; i++)
            {
                System.out.printf("\t%8s\t|\t%8s\t\n", 
                        resultados[i] ? "T" : "F", 
                        !resultados[i] ? "T" : "F");
            }
            
        }
    }
    
    /**
    *	Metodo que genera una tabla de verdad para la conectiva AND. Ejemplo: a ^ b
    *	@param operandos Un arreglo que contiene los operandos
    */
    public static void generarAnd(String[] operandos)
    {
        
        //  No se pueden utilizar ni T ni F
        for(int i = 0; i < operandos.length; i++)
        {
            if(operandos[i].contains("T") || operandos[i].contains("F"))
            {
                return;
            }
        }
        
        //  No se puede usar el mismo operando.
        if(operandos.length == 2 && operandos[0].equals(operandos[1]))
        {
            return;
        }
        
        if(("7" + operandos[0]).equals(operandos[1]))
        {
            boolean combinaciones[][] = { {true, false}, {false, true} };
            
            boolean resultados[] = new boolean[combinaciones.length];
            
            for(int i = 0; i < resultados.length; i++)
            {
                resultados[i] = combinaciones[i][0] && combinaciones[i][1];
            }
            
            System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)^(%s)",operandos[0],operandos[1]));
            for(int i = 0; i < resultados.length; i++)
            {
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        combinaciones[i][0] ? "T" : "F", 
                        combinaciones[i][1] ? "T" : "F", 
                        resultados[i] ? "T" : "F");
            }
        }
        else
        {
            // a b ;   7a b ;   a 7b;  7a, 7b
            if(operandos[0].startsWith("7") && operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, false}, {false, true}, 
                                          {true, false}, {true, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] && combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  7b   (7a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        operandos[0].substring(1), operandos[1].substring(1), 
                        operandos[0], operandos[1], String.format("(%s)^(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
                
            }
            else if(operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {true, false}, {true, true}, 
                                          {false, false}, {false, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] && combinacionesNegadas[i][1];
                }
                
                //  a  b  7b  (a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1].substring(1),
                        operandos[1], String.format("(%s)^(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else if(operandos[0].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, true}, {false, false}, 
                                          {true, true}, {true, false} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] && combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  (7a)^(b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0].substring(1), operandos[1],
                        operandos[0], String.format("(%s)^(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
            
                boolean resultados[] = new boolean[combinaciones.length];

                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinaciones[i][0] && combinaciones[i][1];
                }

                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)^(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F", 
                            resultados[i] ? "T" : "F");
                }
            }
        }
    }
    
    /**
    *	Metodo que genera una tabla de verdad para la conectiva OR. Ejemplo: X v Y
    *	@param operandos Un arreglo de cadenas que contiene los operandos
    */
    public static void generarOr(String[] operandos)
    {
        
        //  No se pueden utilizar ni T ni F
        for(int i = 0; i < operandos.length; i++)
        {
            if(operandos[i].contains("T") || operandos[i].contains("F"))
            {
                return;
            }
        }
        
        //  No se puede usar el mismo operando.
        if(operandos.length == 2 && operandos[0].equals(operandos[1]))
        {
            return;
        }
        
        if(("7" + operandos[0]).equals(operandos[1]))
        {
            boolean combinaciones[][] = { {true, false}, {false, true} };
            
            boolean resultados[] = new boolean[combinaciones.length];
            
            for(int i = 0; i < resultados.length; i++)
            {
                resultados[i] = combinaciones[i][0] || combinaciones[i][1];
            }
            
            System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)v(%s)",operandos[0],operandos[1]));
            for(int i = 0; i < resultados.length; i++)
            {
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        combinaciones[i][0] ? "T" : "F", 
                        combinaciones[i][1] ? "T" : "F", 
                        resultados[i] ? "T" : "F");
            }
        }
        else
        {
             // a b ;   7a b ;   a 7b;  7a, 7b
            if(operandos[0].startsWith("7") && operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, false}, {false, true}, 
                                          {true, false}, {true, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  7b   (7a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        operandos[0].substring(1), operandos[1].substring(1), 
                        operandos[0], operandos[1], String.format("(%s)v(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
                
            }
            else if(operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {true, false}, {true, true}, 
                                          {false, false}, {false, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7b  (a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1].substring(1),
                        operandos[1], String.format("(%s)v(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else if(operandos[0].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, true}, {false, false}, 
                                          {true, true}, {true, false} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  (7a)^(b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0].substring(1), operandos[1],
                        operandos[0], String.format("(%s)v(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
            
                boolean resultados[] = new boolean[combinaciones.length];

                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = combinaciones[i][0] || combinaciones[i][1];
                }

                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)v(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F", 
                            resultados[i] ? "T" : "F");
                }
            }
            
        }
    }
    
    /**
    *	Metodo que genera una tabla de verdad para la conectiva de implicacion.
    *	Ejemplo: a -&gt; b 
   	*	@param operandos Un arreglo de cadenas que contiene los operandos
    */
    public static void generarImplicacion(String[] operandos)
    {
        
        //  No se pueden utilizar ni T ni F
        for(int i = 0; i < operandos.length; i++)
        {
            if(operandos[i].contains("T") || operandos[i].contains("F"))
            {
                return;
            }
        }
        
        //  No se puede usar el mismo operando.
        if(operandos.length == 2 && operandos[0].equals(operandos[1]))
        {
            return;
        }
        
        if(("7" + operandos[0]).equals(operandos[1]))
        {
            boolean combinaciones[][] = { {true, false}, {false, true} };
            
            boolean resultados[] = new boolean[combinaciones.length];
            
            for(int i = 0; i < resultados.length; i++)
            {
                resultados[i] = !combinaciones[i][0] || combinaciones[i][1];
            }
            
            System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)->(%s)",operandos[0],operandos[1]));
            for(int i = 0; i < resultados.length; i++)
            {
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        combinaciones[i][0] ? "T" : "F", 
                        combinaciones[i][1] ? "T" : "F", 
                        resultados[i] ? "T" : "F");
            }
        }
        else
        {
             // a b ;   7a b ;   a 7b;  7a, 7b
            if(operandos[0].startsWith("7") && operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, false}, {false, true}, 
                                          {true, false}, {true, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = !combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  7b   (7a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        operandos[0].substring(1), operandos[1].substring(1), 
                        operandos[0], operandos[1], String.format("(%s)->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
                
            }
            else if(operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {true, false}, {true, true}, 
                                          {false, false}, {false, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = !combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7b  (a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1].substring(1),
                        operandos[1], String.format("(%s)->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else if(operandos[0].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, true}, {false, false}, 
                                          {true, true}, {true, false} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = !combinacionesNegadas[i][0] || combinacionesNegadas[i][1];
                }
                
                //  a  b  7a  (7a)^(b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0].substring(1), operandos[1],
                        operandos[0], String.format("(%s)->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
            
                boolean resultados[] = new boolean[combinaciones.length];

                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = !combinaciones[i][0] || combinaciones[i][1];
                }

                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F", 
                            resultados[i] ? "T" : "F");
                }
            }
            
        }
    }
    
    /**
    *	Metodo que genera una tabla de verdad para la conectiva bicondicional.
    *	Ejemplo: X &lt;-&gt; Y
    *	@param operandos Un arreglo de cadenas que contiene a los operandos.
    */
    public static void generarBicondicional(String[] operandos)
    {
        
        //  No se pueden utilizar ni T ni F
        for(int i = 0; i < operandos.length; i++)
        {
            if(operandos[i].contains("T") || operandos[i].contains("F"))
            {
                return;
            }
        }
        
        //  No se puede usar el mismo operando.
        if(operandos.length == 2 && operandos[0].equals(operandos[1]))
        {
            return;
        }
        
        if(("7" + operandos[0]).equals(operandos[1]))
        {
            boolean combinaciones[][] = { {true, false}, {false, true} };
            
            boolean resultados[] = new boolean[combinaciones.length];
            
            for(int i = 0; i < resultados.length; i++)
            {
                resultados[i] = (!combinaciones[i][0] || combinaciones[i][1]) && (!combinaciones[i][1] || combinaciones[i][0]);
            }
            
            System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)<->(%s)",operandos[0],operandos[1]));
            for(int i = 0; i < resultados.length; i++)
            {
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        combinaciones[i][0] ? "T" : "F", 
                        combinaciones[i][1] ? "T" : "F", 
                        resultados[i] ? "T" : "F");
            }
        }
        else
        {
             // a b ;   7a b ;   a 7b;  7a, 7b
            if(operandos[0].startsWith("7") && operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, false}, {false, true}, 
                                          {true, false}, {true, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    //  A <-> B = (A -> B) ^ (B -> A) =  (¬A v B) ^ (¬B v A)
                    resultados[i] = (!combinacionesNegadas[i][0] || combinacionesNegadas[i][1]) 
                            && (!combinacionesNegadas[i][1] || combinacionesNegadas[i][0]);
                }
                
                //  a  b  7a  7b   (7a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                        operandos[0].substring(1), operandos[1].substring(1), 
                        operandos[0], operandos[1], String.format("(%s)<->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
                
            }
            else if(operandos[1].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {true, false}, {true, true}, 
                                          {false, false}, {false, true} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = (!combinacionesNegadas[i][0] || combinacionesNegadas[i][1]) 
                            && (!combinacionesNegadas[i][1] || combinacionesNegadas[i][0]);
                }
                
                //  a  b  7b  (a)^(7b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1].substring(1),
                        operandos[1], String.format("(%s)<->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][1] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else if(operandos[0].startsWith("7"))
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
                
                boolean combinacionesNegadas[][] = { {false, true}, {false, false}, 
                                          {true, true}, {true, false} };
                
                boolean resultados[] = new boolean[combinaciones.length];
                
                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = (!combinacionesNegadas[i][0] || combinacionesNegadas[i][1]) 
                            && (!combinacionesNegadas[i][1] || combinacionesNegadas[i][0]);
                }
                
                //  a  b  7a  (7a)^(b)
                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0].substring(1), operandos[1],
                        operandos[0], String.format("(%s)<->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F",
                            combinacionesNegadas[i][0] ? "T" : "F",
                            resultados[i] ? "T" : "F");
                }
            }
            else
            {
                boolean combinaciones[][] = { {true, true}, {true, false}, 
                                          {false, true}, {false, false} };
            
                boolean resultados[] = new boolean[combinaciones.length];

                for(int i = 0; i < resultados.length; i++)
                {
                    resultados[i] = (!combinaciones[i][0] || combinaciones[i][1]) 
                            && (!combinaciones[i][1] || combinaciones[i][0]);
                }

                System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", operandos[0], operandos[1], String.format("(%s)<->(%s)",operandos[0],operandos[1]));
                for(int i = 0; i < resultados.length; i++)
                {
                    System.out.printf("\t%8s\t|\t%8s\t|\t%8s\t\n", 
                            combinaciones[i][0] ? "T" : "F", 
                            combinaciones[i][1] ? "T" : "F", 
                            resultados[i] ? "T" : "F");
                }
            }
        }
    }    
}