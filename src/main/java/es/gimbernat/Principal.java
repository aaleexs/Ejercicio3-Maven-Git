package es.gimbernat;

public class Principal {
    public static void main(String[] args){
        System.out.println("Hola mundo");
        BBDD bbdd = new BBDD();
        boolean ok = bbdd.init();
        System.out.println("Conexión establecida: " + ok);
    }
}
