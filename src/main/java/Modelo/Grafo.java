package Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grafo {

    private ArrayList<Nodo> Nodos;

    public Grafo() {
        this.Nodos = new ArrayList<Nodo>();
    }

    public void setNodos(ArrayList<Nodo> Nodos) {
        this.Nodos = Nodos;
    }

    public ArrayList<Nodo> getNodos() {
        return Nodos;
    }

    public void anadirNodo(Nodo N) {
        Nodos.add(N);
    }

    public ArrayList<Nodo> copiarNodos() {
        ArrayList<Nodo> copia = new ArrayList();

        for (int i = 0; i < this.Nodos.size(); i++) {
            copia.add(this.Nodos.get(i));
        }

        return copia;
    }

    @Override
    public String toString() {
        return "Grafo{" + "Nodos=" + Nodos + '}';
    }

    public HashMap<Nodo, ArrayList<Nodo>> Dijkstra(Nodo inicio) {

        //int[] distancias = new int[Nodos.size()];
        ArrayList<Nodo> Ruta = new ArrayList();
        ArrayList<Nodo> A_visitados = this.copiarNodos();

        //Guardamos el nodo permanente
        Nodo permanente;
        //Creamos diccionario que nos guarde las rutas mas cortas para cada nodo
        HashMap<Nodo, ArrayList<Nodo>> Rutas = new HashMap();

        //Creamos diccionario de nodos visitados
        HashMap<Nodo, Boolean> Visitados = new HashMap();
        for (int i = 0; i < this.Nodos.size(); i++) {
            Visitados.put(this.Nodos.get(i), Boolean.FALSE);
        }

        //Creamos diccionario para las distancias
        HashMap<Nodo, Integer> Distancias = new HashMap();
        for (int i = 0; i < this.Nodos.size(); i++) {
            Nodo aux = this.Nodos.get(i);
            Distancias.put(aux, Integer.MAX_VALUE);
        }

        //Asignamos parametros iniciales
        Distancias.replace(inicio, 0);//Nuestro nodo inicial estara en el primer lugar del array
        permanente = new Nodo(inicio);
        Visitados.replace(inicio, Boolean.TRUE);
        Ruta.add(inicio);
        A_visitados.remove(inicio);
        Rutas.put(permanente, Ruta);

        while (!A_visitados.isEmpty()) {
            //ArrayList<Nodo> Adyacentes = permanente.getAdyacentes();
            ArrayList<Arista> Aristas = permanente.getAristas();

            for (int i = 0; i < Aristas.size(); i++) {
                int distancia_preliminar = Distancias.get(permanente);
                Nodo examinar = Aristas.get(i).getDestino();
                if (!Visitados.get(examinar)) {
                    distancia_preliminar = distancia_preliminar + Aristas.get(i).getPeso();

                    if (distancia_preliminar < Distancias.get(examinar)) {
                        Distancias.replace(examinar, distancia_preliminar);
                        //Y aqui metemos el path
                        Ruta = new ArrayList<Nodo>();
                        ArrayList<Nodo> aux = Rutas.get(permanente);

                        for (int j = 0; j < aux.size(); j++) {
                            Ruta.add(aux.get(j));
                        }

                        //Ruta.add(permanente);
                        Ruta.add(examinar);

                        Rutas.put(examinar, Ruta);
                    }
                }
            }

            permanente = this.getMenorDistancia(Distancias, Visitados);
            A_visitados.remove(permanente);
            Visitados.replace(permanente, Boolean.TRUE);

            //Ruta = calculaRuta(inicio, permanente);
        }

        /*Iterator entries = Distancias.entrySet().iterator();
        while(entries.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)entries.next();
            Nodo key = (Nodo)entry.getKey();
            int dist = (int) entry.getValue();
            
            System.out.println("Nodo: " + key + "Distancia: " + dist);
        }*/
 /*Iterator entries = Rutas.entrySet().iterator();
        while(entries.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)entries.next();
            Nodo key = (Nodo)entry.getKey();
            ArrayList<Nodo> aux = (ArrayList<Nodo>) entry.getValue();
            
            System.out.println("Nodo: " + key + "Distancia: " + aux);
        }*/
        return Rutas;
    }

    public ArrayList<Nodo> Prueba1(Nodo inicio) {
        ArrayList<Nodo> Ruta = new ArrayList();

        ArrayList<Boolean> sptSet = new ArrayList();

        for (int i = 0; i < this.Nodos.size(); i++) {
            //Ruta.add(Integer.MAX_VALUE);
        }

        return Ruta;
    }

    public Nodo getMenorDistancia(HashMap<Nodo, Integer> Distancias, HashMap<Nodo, Boolean> Visitados) {

        int menor = Integer.MAX_VALUE;
        Nodo Nodo_Menor = null;

        for (int i = 0; i < this.Nodos.size(); i++) {

            if (!Visitados.get(this.Nodos.get(i)) && Distancias.get(this.Nodos.get(i)) < menor) {
                menor = Distancias.get(this.Nodos.get(i));
                Nodo_Menor = this.Nodos.get(i);
            }
        }

        return Nodo_Menor;
    }

    public void recalcularRuta(ArrayList<Nodo> Ruta, Nodo Siguiente) {

        ArrayList<Arista> Aristas = Ruta.get(Ruta.size() - 1).getAristas();
        boolean siguiente_es_destino = false; //Comprobamos que nuestro nuevo 
        //vertice, sea destino del anterior, si no, lo eliminamos.
        int i = 0;

        while (!siguiente_es_destino && i < Aristas.size()) {
            if (Aristas.get(i).getDestino().equals(Siguiente)) {
                siguiente_es_destino = true;
            } else {
                i++;
            }
        }

        if (siguiente_es_destino) {
            Ruta.add(Siguiente);
        } else {
            Ruta.remove(Ruta.get(Ruta.size() - 1));
            recalcularRuta(Ruta, Siguiente);
        }

    }

    public int getDistanciaRuta(ArrayList<Nodo> Ruta) {

        int distanciaRuta = 0;

        for (int i = 0; i < Ruta.size(); i++) {
            ArrayList<Arista> Aristas = Ruta.get(i).getAristas();

            for (int j = 0; j < Aristas.size(); j++) {
                if (i + 1 == Ruta.size()) {
                    break;
                } else {
                    if (Aristas.get(j).getDestino().equals(Ruta.get(i + 1))) {
                        distanciaRuta = distanciaRuta + Aristas.get(j).getPeso();
                    }
                }

            }
        }

        return distanciaRuta;

    }

    public ArrayList<Nodo> calculaRuta(Nodo inicio, Nodo Siguiente) {

        ArrayList<Nodo> Ruta = new ArrayList();

        Nodo ultimo = Siguiente;

        Ruta.add(inicio);

        while (!Siguiente.equals(inicio)) {
            //Nodo aux = new Nodo(Siguiente);

            Siguiente = Siguiente.getAntecesorMenorPeso(inicio, ultimo);
            System.out.println("Siguiente: " + Siguiente);
            if (!Siguiente.equals(inicio)) {
                Ruta.add(Siguiente);
            }

        }

        Ruta.add(ultimo);

        return Ruta;
    }

    public void construyeGrafoTSPLIB(ArrayList<Punto> Puntos) {

        /*for (int i = 0; i < Puntos.size(); i++) {
            Nodo aux = new Nodo(Puntos.get(i));
            
            for (int j = 0; j < Puntos.size(); j++) {
                
                if(!aux.getP().equals(Puntos.get(j))){
                    Nodo destino = new Nodo(Puntos.get(j));
                    Arista arista_aux = new Arista(aux, destino);
                    aux.anadirArista(arista_aux);
                    
                }
                
                
            }
            
            this.Nodos.add(aux);  
        }*/
        Nodo[] aux = new Nodo[Puntos.size()];
        ArrayList<Arista> Aristas = new ArrayList<>();

        for (int i = 0; i < Puntos.size(); i++) {

            aux[i] = new Nodo(Puntos.get(i));

        }

        /*System.out.println("Longitud de las aristas: " + aristas.length);

        for (int i = 0; i < aristas.length; i++) {
            for (int j = 0; j < Puntos.size(); j++) {
                for (int k = 0; k < Puntos.size(); k++) {
                    
                    if (!aux[j].equals(aux[k])) {
                        aristas[i] = new Arista(aux[j], aux[k]);
                        
                        if(aux[j].equals(aristas[i].getOrigen())){
                            aux[j].anadirArista(aristas[i]);
                        }
                        //System.out.println(aristas[i]);
                        
                    }
                    
                    System.out.println("i: " + i );
                    
                }

            }

        }*/
        for (int i = 0; i < Puntos.size(); i++) {
            for (int j = 0; j < Puntos.size(); j++) {
                if (!aux[i].equals(aux[j])) {

                    aux[i].anadirArista(new Arista(aux[i], aux[j]));
                    //Aristas.add(new Arista(aux[i], aux[j]));
                }
            }

            this.Nodos.add(aux[i]);
        }

        /*for (int i = 0; i < aux.length; i++) {
            System.out.println(aux[i]);
        }*/
        //System.out.println(this.Nodos.get(0).getAristas().get(0).getDestino());
    }
    
    public void generaGrafoAleatorio(int tam){
        
        ArrayList<Punto> Puntos = Punto.generaPuntosAleatorios(tam);
        
        Nodo[] aux = new Nodo[tam];
        ArrayList<Arista> Aristas = new ArrayList<>();

        for (int i = 0; i < Puntos.size(); i++) {

            aux[i] = new Nodo(Puntos.get(i));

        }
        
        for (int i = 0; i < Puntos.size(); i++) {
            for (int j = 0; j < Puntos.size(); j++) {
                if (!aux[i].equals(aux[j])) {

                    aux[i].anadirArista(new Arista(aux[i], aux[j]));
                    //Aristas.add(new Arista(aux[i], aux[j]));
                }
            }

            this.Nodos.add(aux[i]);
        }
        
    }

    public void guardaArchivoTSP(String ruta, String nombre, int solucion, HashMap<Nodo, ArrayList<Nodo>> Dijkstra) {
        try {
            FileWriter fichero = new FileWriter(ruta + ".opt.tour");

            fichero.write("NAME : " + nombre + ".opt.tour\n");
            fichero.write("TYPE : TOUR\n");
            fichero.write("DIMENSION : " + this.Nodos.size() + "\n");
            fichero.write("SOLUTION : " + solucion + "\n");

            Iterator entries = Dijkstra.entrySet().iterator();
            while (entries.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) entries.next();
                Nodo key = (Nodo) entry.getKey();
                ArrayList<Nodo> aux = (ArrayList<Nodo>) entry.getValue();

                fichero.write(this.getDistanciaRuta(aux) + " - ");
                fichero.write(aux.get(0).getP() + "");
                for (int i = 1; i < aux.size(); i++) {
                    fichero.write("," + aux.get(i).getP() );
                }
                fichero.write("\n");
            }

            fichero.write("-1\n");
            fichero.write("EOF");
            fichero.close();

        } catch (IOException ex) {
            Logger.getLogger(Grafo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Nodo encuentraPunto(Punto P){
        boolean encontrado = false;
        int i = 0;
        Nodo devolver = null;
        while(i< this.Nodos.size() && !encontrado){
            //System.out.println("Hola");
            //System.out.println(this.Nodos.get(i).getP());
            if(this.Nodos.get(i).getP().equals(P)){
                encontrado = true;
                devolver = new Nodo(this.Nodos.get(i));
            }
            else{
                i++;
            }
            
        }
        
        return devolver;
        
    }

    //public void calculaRuta(ArrayList<Nodo> Ruta, Nodo inicio, Nodo Siguiente)
}
