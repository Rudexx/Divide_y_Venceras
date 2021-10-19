package co.edu.unbosque.view;

import javax.swing.*;

public class View {
    /** Función para mostrar informacion en una ventana emergente
     *      @param mensages el mensaje que se va a imprimir
     */
    public void mostrarInformacion(String mensages){
        JOptionPane.showMessageDialog(null, mensages );
    }

    /** Función para permitirle al usuario ingresar un valor en especifico
     *      @param mensage el mensaje que se mostrará en la ventana
     *      @return el valor ingresado por el usuario
     */

    public String ingresarInformacion(String mensage){
        return JOptionPane.showInputDialog(mensage);
    }

    /** Función para mostrarle dos opciones al usuario y que escoja una de ellas
     *      @param mensaje el mensaje que se mostrará en la ventana
     *      @param op1 opcion 1
     *      @param op2 opcion 2
     *      @return 0: si el usuario escoje la opcion 1, 1: si el usuario escoje la opción 2
     */

    public Integer mostrarOpcion(String mensaje, String op1, String op2) {
        Object[] options = { op1, op2};
       return JOptionPane.showOptionDialog(null, "Tipo de metodo", mensaje,
        	    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
        	    options, options[0]);
        
    }



}
