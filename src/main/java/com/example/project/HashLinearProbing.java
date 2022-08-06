package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private String AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = ""+Integer.MIN_VALUE;
        this.size = 0;
    }

    public int hashing(String key) {
    	
    	int hash=0;
    	for(char c: key.toCharArray()) {
    		hash+=c; // Toma su valor ASCII 
    		hash+=(hash<<10); // EL operador << desplaza (en este caso 10) bits de hash a su izquierda
    							// >> de manera analoga hace lo mismo pero a la derecha
    		hash^=(hash >> 6) ;  // Operacion (^ ->XOR) ,es el or exclusivo de lógica 
    	}
    	hash+=(hash<<3);  
    	hash^=(hash>>11);
    	hash+=(hash<<15);
    	while(hash<0) {
    		hash+=hsize;
    	}
    	return hash%hsize;
    }

    public void insertHash(Persona key) {
        int hash = hashing(key.DNI);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }
        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null || buckets[hash].DNI.compareTo(AVAILABLE)==0) {
                buckets[hash] = key;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String key) {
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(key)) {
                //Se hace para que la busqueda no tenga errores
            	buckets[hash].DNI = AVAILABLE;
            	//Mantenemos actualizado el size
                size--;
                return;
            }
            //Imita el comportamiento de % hsize
            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i].DNI.compareTo(AVAILABLE)==0) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public String findHash(String key) {
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(key)) {
                    return buckets[hash].nombre;
                }
            } catch (Exception E) {
            	
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

}
