import java.util.Scanner;

// Clase para almacenar los datos personales
class Persona {
    String nombre;
    int edad;
    String direccion;

    public Persona(String nombre, int edad, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }
}

// Nodo para la lista enlazada
class Nodo {
    Persona persona;
    Nodo siguiente;

    public Nodo(Persona persona) {
        this.persona = persona;
        this.siguiente = null;
    }
}

// Tabla hash
class TablaHash {
    Nodo[] tabla;

    public TablaHash(int capacidad) {
        tabla = new Nodo[capacidad];
    }

    // Función de hash: Aritmética Modular
    private int funcionHashModular(String clave) {
        return Math.abs(clave.hashCode() % tabla.length);
    }

    // Función de hash: Plegamiento
    private int funcionHashPlegamiento(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash += clave.charAt(i);
        }
        return hash % tabla.length;
    }

    // Agregar dato a la tabla
    public void agregar(String clave, Persona persona) {
        int indice = funcionHashModular(clave);
        Nodo nuevoNodo = new Nodo(persona);
        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            Nodo actual = tabla[indice];
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    // Eliminar dato de la tabla
    public void eliminar(String clave) {
        int indice = funcionHashModular(clave);
        if (tabla[indice] == null) {
            System.out.println("No se encontró ningún dato con la clave proporcionada.");
        } else {
            tabla[indice] = null;
            System.out.println("Dato eliminado correctamente.");
        }
    }

    // Consultar dato en la tabla
    public void consultar(String clave) {
        int indice = funcionHashModular(clave);
        Nodo actual = tabla[indice];
        while (actual != null) {
            if (actual.persona.nombre.equals(clave)) {
                System.out.println("Nombre: " + actual.persona.nombre);
                System.out.println("Edad: " + actual.persona.edad);
                System.out.println("Dirección: " + actual.persona.direccion);
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("No se encontró ningún dato con la clave proporcionada.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TablaHash tablaHash = null;

        System.out.println("Ingrese la capacidad de la tabla hash:");
        int capacidad = scanner.nextInt();
        tablaHash = new TablaHash(capacidad);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar dato");
            System.out.println("2. Eliminar dato");
            System.out.println("3. Consultar dato");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Ingrese el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la edad:");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Ingrese la dirección:");
                    String direccion = scanner.nextLine();
                    tablaHash.agregar(nombre, new Persona(nombre, edad, direccion));
                    break;
                case 2:
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Ingrese el nombre a eliminar:");
                    String nombreEliminar = scanner.nextLine();
                    tablaHash.eliminar(nombreEliminar);
                    break;
                case 3:
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.println("Ingrese el nombre a consultar:");
                    String nombreConsultar = scanner.nextLine();
                    tablaHash.consultar(nombreConsultar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }
}
