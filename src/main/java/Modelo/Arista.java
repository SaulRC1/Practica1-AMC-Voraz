/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author SaulRC1
 */
public class Arista {
    
    private Nodo origen;
    private Nodo destino;
    private int peso;

    public Arista(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
        this.peso = this.pesoArista();
    }

    public Nodo getOrigen() {
        return new Nodo(origen);
    }

    public Nodo getDestino() {
        return new Nodo(destino);
    }

    public int getPeso() {
        return peso;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    private int pesoArista(){
        int distancia = (int)Punto.Distancia(origen.getP(), destino.getP());
        
        distancia = (distancia%100) + 1;
        
        return distancia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.origen);
        hash = 59 * hash + Objects.hashCode(this.destino);
        hash = 59 * hash + this.peso;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arista other = (Arista) obj;
        if (this.peso != other.peso) {
            return false;
        }
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "\nArista{" + "origen=" + origen.getP() + ", destino=" + destino.getP() + ", peso=" + peso + '}';
    }
    
    
    
}
