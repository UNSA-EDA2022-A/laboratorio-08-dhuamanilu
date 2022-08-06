package com.example.project;

public class Exercise1 {

    public HashLinearProbing tb;

    public static void main(String[] args) {
        Exercise1 obj = new Exercise1();

        obj.tb = new HashLinearProbing(100);

        obj.insertarPersona(new Persona("12345678", "Jorge Chamby"));
        obj.eliminarPersona("12345678");
        obj.insertarPersona(new Persona("29123456", "Raul Gonzalez"));
        obj.insertarPersona(new Persona("17893665", "Cristiano Ronaldo"));
        obj.insertarPersona(new Persona("17865665", "Lionel Messi"));
        obj.insertarPersona(new Persona("14563665", "Luka Modric"));
        obj.insertarPersona(new Persona("93254565", "Lucas Moura"));
        obj.insertarPersona(new Persona("87521665", "James Rodriguez"));
        obj.eliminarPersona("14563665");
        obj.eliminarPersona("93254565");

        String result = obj.encontrarPersona("17893665");
        //System.out.println(result);
        //obj.tb.displayHashtable();
    }

    public void insertarPersona(Persona obj){
        tb.insertHash(obj);
    }

    public void eliminarPersona(String dni){
        tb.deleteHash(dni);
    }

    // Retorna NULL quando no se encontro el dni, y el nombre de la persona si lo encontro
    public String encontrarPersona(String dni){
        return tb.findHash(dni);
    }    
}
