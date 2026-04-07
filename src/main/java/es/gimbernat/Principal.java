package es.gimbernat;

import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args){
        System.out.println("Hola mundo");
        BBDD bbdd = new BBDD();
        boolean ok = bbdd.init();
        System.out.println("Conexión establecida: " + ok);

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Listar Empleados");
            System.out.println("3. Actualizar Empleado");
            System.out.println("4. Eliminar Empleado");

            System.out.println("5. Insertar Departamento");
            System.out.println("6. Listar Departamentos");
            System.out.println("7. Actualizar Departamento");
            System.out.println("8. Eliminar Departamento");

            System.out.println("0. Salir");

            System.out.print("Elige opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.next();

                    System.out.print("Salario: ");
                    double salario = sc.nextDouble();

                    bbdd.insertEmpleado(new Empleado(0, nombre, salario));
                    break;

                case 2:
                    List<Empleado> empleados = bbdd.getAllEmpleados();
                    empleados.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idUp = sc.nextInt();

                    System.out.print("Nombre: ");
                    String nomUp = sc.next();

                    System.out.print("Salario: ");
                    double salUp = sc.nextDouble();

                    bbdd.updateEmpleado(new Empleado(idUp, nomUp, salUp));
                    break;

                case 4:
                    System.out.print("ID a eliminar: ");
                    int idDel = sc.nextInt();
                    bbdd.deleteEmpleado(idDel);
                    break;

                case 5:
                    System.out.print("Nombre: ");
                    String nombreDep = sc.next();

                    System.out.print("Localidad: ");
                    String loc = sc.next();

                    bbdd.insertDepartamento(new Departamento(0, nombreDep, loc));
                    break;

                case 6:
                    List<Departamento> departamentos = bbdd.getAllDepartamentos();
                    departamentos.forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("ID: ");
                    int idDepUp = sc.nextInt();

                    System.out.print("Nombre: ");
                    String nomDepUp = sc.next();

                    System.out.print("Localidad: ");
                    String locUp = sc.next();

                    bbdd.updateDepartamento(new Departamento(idDepUp, nomDepUp, locUp));
                    break;

                case 8:
                    System.out.print("ID a eliminar: ");
                    int idDepDel = sc.nextInt();
                    bbdd.deleteDepartamento(idDepDel);
                    break;
            }

        } while (opcion != 0);
    }
}
