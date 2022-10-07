/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Aplicacion;

/*import Modelo.Grafo;
import Modelo.Nodo;
import Modelo.Punto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author SaulRC1
 */
//public class Pruebas {

    /**
     * @param args the command line arguments
     */
  //  public static void main(String[] args) {
        // TODO code application logic here
        /*ArrayList<Punto> Puntos = new ArrayList<Punto>();

        Punto.LeeFichero_TSP("E:/3º Ingenieria Informatica/AMC/Practica 1/dataset_amc_1920/berlin52.tsp/berlin52.tsp", Puntos);
        
        Grafo G = new Grafo();
        
        G.construyeGrafoTSPLIB(Puntos);*/
        // TODO code application logic here

        /*Nodo A = new Nodo(new Punto(1, 1));
        Nodo B = new Nodo(new Punto(2, 3));
        Nodo C = new Nodo(new Punto(2, 1));
        Nodo D = new Nodo(new Punto(3, 2));
        Nodo E = new Nodo(new Punto(1, 3));
        Nodo F = new Nodo(new Punto(4, 2));

        Arista AB = new Arista(A, B);
        AB.setPeso(10);
        Arista BA = new Arista(B, A);
        BA.setPeso(10);
        Arista AF = new Arista(A, F);
        AF.setPeso(9);
        Arista FA = new Arista(F, A);
        FA.setPeso(9);
        Arista BC = new Arista(B, C);
        BC.setPeso(5);
        Arista CB = new Arista(C, B);
        CB.setPeso(5);
        Arista CD = new Arista(C, D);
        CD.setPeso(4);
        Arista DC = new Arista(D, C);
        DC.setPeso(4);
        Arista DE = new Arista(D, E);
        DE.setPeso(2);
        Arista ED = new Arista(E, D);
        ED.setPeso(2);
        Arista EF = new Arista(E, F);
        EF.setPeso(15);
        Arista FE = new Arista(F, E);
        FE.setPeso(15);
        Arista BE = new Arista(B, E);
        BE.setPeso(13);
        Arista EB = new Arista(E, B);
        EB.setPeso(13);
        Arista BD = new Arista(B, D);
        BD.setPeso(8);
        Arista DB = new Arista(D, B);
        DB.setPeso(8);
        Arista FC = new Arista(F, C);
        FC.setPeso(3);
        Arista CF = new Arista(C, F);
        CF.setPeso(3);
        Arista FD = new Arista(F, D);
        FD.setPeso(5);
        Arista DF = new Arista(D, F);
        DF.setPeso(5);

        //Ponemos Aristas Pertenecientes al Nodo A
        A.anadirArista(AB);
        A.anadirArista(AF);

        //Ponemos Aristas Pertenecientes al Nodo B
        B.anadirArista(BA);
        B.anadirArista(BC);
        B.anadirArista(BE);
        B.anadirArista(BD);
        //Ponemos Aristas Pertenecientes al Nodo C
        C.anadirArista(CB);
        C.anadirArista(CD);
        C.anadirArista(CF);
        //Ponemos Aristas Pertenecientes al Nodo D
        D.anadirArista(DC);
        D.anadirArista(DE);
        D.anadirArista(DB);
        D.anadirArista(DF);
        //Ponemos Aristas Pertenecientes al Nodo E
        E.anadirArista(EB);
        E.anadirArista(ED);
        E.anadirArista(EF);
        //Ponemos Aristas Pertenecientes al Nodo F
        F.anadirArista(FA);
        F.anadirArista(FC);
        F.anadirArista(FD);
        F.anadirArista(FE);

        Grafo G = new Grafo();

        ArrayList<Nodo> Nodos = new ArrayList();

        Nodos.add(A);
        Nodos.add(B);
        Nodos.add(C);
        Nodos.add(D);
        Nodos.add(E);
        Nodos.add(F);

        G.setNodos(Nodos);

        HashMap<Nodo, ArrayList<Nodo>> Dijsktra = G.Dijkstra(A);

        //System.out.println("Dijsktra" + Dijsktra);
        Iterator entries = Dijsktra.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) entries.next();
            Nodo key = (Nodo) entry.getKey();
            ArrayList<Nodo> aux = (ArrayList<Nodo>) entry.getValue();

            System.out.println("Nodo: " + key.getP() + "Distancia: " + G.getDistanciaRuta(aux));
        }*/

       /* ArrayList<Punto> Puntos = new ArrayList<Punto>();

        Punto.LeeFichero_TSP("E:/3º Ingenieria Informatica/AMC/Practica 1/dataset_amc_1920/berlin52.tsp/berlin52.tsp", Puntos);
        
        Grafo G = new Grafo();
        
        G.construyeGrafoTSPLIB(Puntos);
        
        //System.out.println(G);
        
        HashMap<Nodo, ArrayList<Nodo>> Dijsktra = G.Dijkstra(G.getNodos().get(0));

        //System.out.println("Dijsktra" + Dijsktra);
        int suma_distancias = 0;
        Iterator entries = Dijsktra.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) entries.next();
            Nodo key = (Nodo) entry.getKey();
            ArrayList<Nodo> aux = (ArrayList<Nodo>) entry.getValue();

            System.out.println("Nodo: " + key.getP() + "Distancia: " + G.getDistanciaRuta(aux));
            
            suma_distancias = suma_distancias + G.getDistanciaRuta(aux);
        }
        
        G.guardaArchivoTSP("E:/3º Ingenieria Informatica/AMC/Practica 1/dataset_amc_1920/berlin52.tsp", "berlin52sol2", suma_distancias, Dijsktra);
    }
    
}*/
