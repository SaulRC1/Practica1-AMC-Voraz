/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Grafo;
import Modelo.Nodo;
import Modelo.Punto;
import Vista.PanelGrafo;
import Vista.VentanaPrincipal;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author SaulRC1
 */
public class VentanaPrincipalController {

    private VentanaPrincipal vPrincipal;
    private PanelGrafo vGrafo;
    private Grafo Grafo_Puntos;
    private ArrayList<Punto> Puntos;
    private HashMap<Nodo, ArrayList<Nodo>> Solucion = new HashMap();
    private int Peso_Camino_Dijkstra = 0;

    public VentanaPrincipalController() {

        vPrincipal = new VentanaPrincipal();
        vGrafo = new PanelGrafo();
        Grafo_Puntos = new Grafo();
        Puntos = new ArrayList();

        JScrollPane scroll = new JScrollPane(vGrafo);
        scroll.setBounds(350, 0, 910, 680);

        vPrincipal.add(scroll);
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setLayout(new BorderLayout());

        vPrincipal.btnCargar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                CargarFichero(ev);
            }
        });
        vPrincipal.btnDijkstra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                CalcularDijkstra(ev);
            }
        });
        vPrincipal.btnGuardar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent ev){
                GuardarFichero(ev);
            }
        });

        vPrincipal.setTitle("Practica 1 AMC - Dijkstra (Saul Rodriguez Naranjo)");
        vPrincipal.setResizable(false);
        vPrincipal.setVisible(true);

    }

    public void CargarFichero(MouseEvent ev) {
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            Grafo_Puntos = new Grafo();
            File fichero_seleccionado = fc.getSelectedFile();

            vPrincipal.txtNombreFichero.setText(fichero_seleccionado.getName());
            this.Puntos = new ArrayList<Punto>();
            Punto.LeeFichero_TSP(fichero_seleccionado.getAbsolutePath(), Puntos);
            String Mostrar = "";
            vPrincipal.txtDataSet.setText("");
            for (int i = 0; i < Puntos.size(); i++) {
                Mostrar = Mostrar + "\n" + Puntos.get(i);
                vPrincipal.txtDataSet.setText(Mostrar);
            }

            Grafo_Puntos.construyeGrafoTSPLIB(Puntos);

            vPrincipal.cbNodos.setModel(new DefaultComboBoxModel<Punto>());

            for (int i = 0; i < Puntos.size(); i++) {

                vPrincipal.cbNodos.addItem(new Punto(Puntos.get(i)));

            }

            //vPrincipal.cbNodos.setPrototypeDisplayValue(Puntos.get(0));
            //System.out.println(Grafo_Puntos);
        }
    }

    public void CalcularDijkstra(MouseEvent ev) {

        //System.out.println((Punto) vPrincipal.cbNodos.getSelectedItem());
        //System.out.println(Grafo_Puntos);
        Nodo Inicio = Grafo_Puntos.encuentraPunto((Punto) vPrincipal.cbNodos.getSelectedItem());
        long ini = System.currentTimeMillis();
        Solucion = Grafo_Puntos.Dijkstra(Inicio);
        long end = System.currentTimeMillis();
        
        vPrincipal.txtTiempoEjecucion.setText(end - ini + " ms");
        
        Peso_Camino_Dijkstra = 0;
        Iterator entries = Solucion.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) entries.next();
            Nodo key = (Nodo) entry.getKey();
            ArrayList<Nodo> aux = (ArrayList<Nodo>) entry.getValue();
            Peso_Camino_Dijkstra = Peso_Camino_Dijkstra + Grafo_Puntos.getDistanciaRuta(aux);
            //System.out.println("Nodo: " + key.getP() + "Distancia: " + Grafo_Puntos.getDistanciaRuta(aux));

        }
        
        vGrafo.cargarSolucion(Solucion);

    }
    
    public void GuardarFichero(MouseEvent ev){
        JFileChooser guardar = new JFileChooser();
        int seleccion = guardar.showSaveDialog(null);

        if(seleccion == JFileChooser.APPROVE_OPTION){
            System.out.println("Fichero: " + guardar.getSelectedFile().getPath());
            System.out.println("Nombre del fichero: " + guardar.getSelectedFile().getName());
            Grafo_Puntos.guardaArchivoTSP(guardar.getSelectedFile().getPath(), guardar.getSelectedFile().getName(), Peso_Camino_Dijkstra, Solucion);
        }
        
                
               
    }

}
