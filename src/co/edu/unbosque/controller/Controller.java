package co.edu.unbosque.controller;

 import java.util.Iterator;
import java.util.Random;

import co.edu.unbosque.model.*;
 import co.edu.unbosque.view.View;

public class Controller {

    private View view;
    private Torneo torneo;
    private Multiplicacion_matriz matriz;

	/** Constructor de la clase
	 */

    public  Controller() {
        view = new View();
        torneo = new Torneo();
        matriz = new Multiplicacion_matriz();
        run();
    }

	/** Funcion que inicia el funcionamiento del programa
	 */


    public void run(){
    	view.mostrarInformacion("Bienvenido Al programa de algoritmos de divide y Venceras");
    	int opcion = view.mostrarOpcion("Seleccione el algoritmo que desea utilizar",
    			"Partido de Tennis" , "Multiplicacion de Matrices");

    	if(opcion == 0) {
    	try {
			int n = Integer.parseInt(view.ingresarInformacion("Ingrese el numero de jugadores (Maximo 50)"));
			
			if(n <=1 || n>50) {
				throw new Exception();
			}else {
				long startTime = System.nanoTime();
				torneo.torneo(n);
				long endTime = System.nanoTime();
				view.mostrarInformacion("Proceso Terminado en: " + (endTime-startTime)/1e6 + " ms");
				view.mostrarInformacion(torneo.imprimirTabla(n));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			view.mostrarInformacion("Debe ingresar un numero no mayor a 50 y mayor a 1");
			run();
		}
    	}else if(opcion == 1) {
    		opcion = view.mostrarOpcion("�Desea llenar la matriz manual o automaticamente?",
    				"Automatico" , "Manual");
    		int opcion2 = view.mostrarOpcion("�Que algoritmo desea usar?",
    				"Divide y venceras (Stressen)" , "Generico");
    		try {
    			int n = Integer.parseInt(view.ingresarInformacion("Ingrese el tamano de la matriz (Debe ser potencia de 2 para el algoritmo de Strassen)"));
    			if(opcion2 == 0 && !isPowerOfTwo(n)) {
    				while(!isPowerOfTwo(n)) {
    					n = Integer.parseInt
    							(view.ingresarInformacion
    									("Error: el tama�o de la matriz debe ser potencia de 2; " 
    											+ "\nIngrese el tamano de la matriz"));
    				}
    			}
    			
    			
    			
    			int [][] A= llenarMatriz(n, opcion);
    			int [][] B= llenarMatriz(n, opcion);

    			
    
    			
    			int[][] C = null;
    			String impresion = "Matriz A\n";
    			impresion = impresion + imprimirMatriz(A);
    			impresion = impresion + "\n\nMatriz B\n" + imprimirMatriz(B);
    			
    			
        				
    			if(opcion == 0) {
    				C = matriz.strassen(A, B);
    				impresion = impresion + "\n\nMatriz C= AxB \n" + imprimirMatriz(C);
    			}else {
    				C = matriz.regular(A, B);
    				impresion = impresion + "\n\nMatriz C= AxB \n" + imprimirMatriz(C);
    			}
    			view.mostrarInformacion(impresion);
    		}catch(Exception e) {
    			view.mostrarInformacion("Error: Ingrese un numero valido y mayor a 0");
				run();
    		}
    	}else {
    		
    	}
    }
	/** Funcion que imprime una matriz especifica
	 *      @param matriz la matriz para imprimir
	 *      @return un mensaje con la informacion de la matriz
	 */
    
    public String imprimirMatriz(int[][] matriz) {
    	String resultado = "";
    	for (int i = 0; i < matriz.length; i++) {
    		resultado = resultado +" \n";
			for (int j = 0; j < matriz.length; j++) {
				resultado = resultado + matriz[i][j] + " ";
			}
		}
    	return resultado;
    }

	/** Funcion que permite llenar una matriz de forma automática o manual
	 *      @param n tamano de la matriz
	 *      @param opcion define si se va a llenar manual o automaticamente
	 *      @return devuelve la matriz resultante
	 */
    
    public int[][] llenarMatriz(int n, int opcion){
    	int[][] matriz = new int[n][n];
    	Random r = new Random();
    	if(opcion == 0) {
    		for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					matriz[i][j] = r.nextInt(100);
				}
			}
    	}else {
    		for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz.length; j++) {
					try {
						matriz[i][j] = Integer.parseInt(view.ingresarInformacion("Ingrese el valor ["+ 
								i + "] [" + j + "]"));
					}catch(Exception e) {
						view.mostrarInformacion("Error: Ingrese un numero valido");
						run();
					}				
				}
    		}
    	}
    	return matriz;
    }

	/** Funcion que permite verifica si un numero es potencia de 2
	 *      @param n El numero que se quiere verificar
	 *      @return true: si es potencia de 2, false: si no es potencia de 2
	 */
    
    public static boolean isPowerOfTwo(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }
}
