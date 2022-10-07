/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author SaulRC1
 */
public class Nodo {

    private Punto P;
    private ArrayList<Arista> Aristas;

    public Nodo(Punto P) {
        this.P = P;
        Aristas = new ArrayList<Arista>();

    }

    public Nodo(Nodo N) {
        this.P = new Punto(N.P);
        this.Aristas = new ArrayList();

        for (int i = 0; i < N.Aristas.size(); i++) {
            this.Aristas.add(N.Aristas.get(i));
        }
    }

    @Override
    public String toString() {
        return "\nNodo{" + "P=" + P + ", Aristas=" + Aristas + '}';
    }

    public Punto getP() {
        return P;
    }

    public ArrayList<Arista> getAristas() {

        ArrayList<Arista> copia = new ArrayList();

        for (int i = 0; i < this.Aristas.size(); i++) {
            copia.add(this.Aristas.get(i));
        }

        return copia;
    }

    public void setP(Punto P) {
        this.P = P;
    }

    public void anadirArista(Arista A) {
        Aristas.add(A);
    }

    public void setAristas(ArrayList<Arista> Aristas) {
        this.Aristas = Aristas;
    }

    public ArrayList<Nodo> getAdyacentes() {
        ArrayList<Nodo> Adyacentes = new ArrayList();

        for (int i = 0; i < this.Aristas.size(); i++) {
            Adyacentes.add(this.Aristas.get(i).getDestino());
        }

        return Adyacentes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.P.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        Nodo other = (Nodo) obj;

        if (this.P.equals(other.P)) {
            return true;
        } else {
            return false;
        }

    }

    public Nodo getAntecesorMenorPeso(Nodo inicio, Nodo Restriccion) {

        int peso = Integer.MAX_VALUE;
        Nodo MenorPeso = null;

        boolean EstaInicio = compruebaInicio(inicio, Aristas);

        if (EstaInicio) {
            System.out.println("Esta Inicio");
            MenorPeso = new Nodo(inicio);
        } else {

            for (int i = 0; i < this.Aristas.size(); i++) {
                if (this.Aristas.get(i).getPeso() < peso && !this.Aristas.get(i).getDestino().equals(Restriccion)) {
                    peso = this.Aristas.get(i).getPeso();
                    MenorPeso = this.Aristas.get(i).getDestino();
                }
            }
        }

        return MenorPeso;
    }

    public boolean compruebaInicio(Nodo inicio, ArrayList<Arista> Aristas) {

        boolean encontrado = false;
        int i = 0;

        while (i < Aristas.size() && !encontrado) {
            Nodo destino = Aristas.get(i).getDestino();
            if (destino.equals(inicio)) {
                encontrado = true;
            }
            else{
                i++;
            }
        }

        return encontrado;

    }

}
