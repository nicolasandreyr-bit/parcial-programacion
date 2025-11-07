import java.util.ArrayList;
import java.util.Scanner;

//# Clase base que representa un material bibliográfico
class Material {
    protected String titulo;
    protected String autor;
    protected boolean prestado;

    public Material(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public void mostrarInfo() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | Prestado: " + (prestado ? "Sí" : "No"));
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void prestar() {
        prestado = true;
    }

    public void devolver() {
        prestado = false;
    }
}

// Clase derivada que hereda de Material
class Libro extends Material {
    private String genero;

    public Libro(String titulo, String autor, String genero) {
        super(titulo, autor); // Llama al constructor de la clase padre
        this.genero = genero;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | Género: " + genero + " | Prestado: " + (prestado ? "Sí" : "No"));
    }
}

// Clase principal
public class BibliotecaApp {
    static ArrayList<Libro> inventario = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ BIBLIOTECA ---");
            System.out.println("1. Inventario");
            System.out.println("2. Préstamo");
            System.out.println("3. Devolución");
            System.out.println("4. Multas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpia el buffer

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    prestarLibro();
                    break;
                case 3:
                    devolverLibro();
                    break;
                case 4:
                    calcularMulta();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        } while (opcion != 5);
    }

    // --- OPCIÓN 1: INVENTARIO ---
    public static void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Inventario vacío. Agregando algunos libros de ejemplo...");
            inventario.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "Realismo mágico"));
            inventario.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "Clásico"));
        }
        System.out.println("\n--- INVENTARIO ---");
        for (int i = 0; i < inventario.size(); i++) {
            System.out.print((i + 1) + ". ");
            inventario.get(i).mostrarInfo();
        }
    }

    // --- OPCIÓN 2: PRÉSTAMO ---
    public static void prestarLibro() {
        mostrarInventario();
        System.out.print("\nIngrese el número del libro a prestar: ");
        int indice = sc.nextInt() - 1;

        if (indice >= 0 && indice < inventario.size()) {
            Libro libro = inventario.get(indice);
            if (!libro.isPrestado()) {
                libro.prestar();
                System.out.println("El libro '" + libro.titulo + "' ha sido prestado.");
            } else {
                System.out.println("Ese libro ya está prestado.");
            }
        } else {
            System.out.println("Número inválido.");
        }
    }

    // --- OPCIÓN 3: DEVOLUCIÓN ---
    public static void devolverLibro() {
        mostrarInventario();
        System.out.print("\nIngrese el número del libro a devolver: ");
        int indice = sc.nextInt() - 1;

        if (indice >= 0 && indice < inventario.size()) {
            Libro libro = inventario.get(indice);
            if (libro.isPrestado()) {
                libro.devolver();
                System.out.println("El libro '" + libro.titulo + "' ha sido devuelto correctamente.");
            } else {
                System.out.println("Ese libro no estaba prestado.");
            }
        } else {
            System.out.println("Número inválido.");
        }
    }

    // --- OPCIÓN 4: MULTAS ---
    public static void calcularMulta() {
        System.out.print("Ingrese los días de retraso en la devolución: ");
        int dias = sc.nextInt();
        double multa = dias * 500; // $500 por día
        System.out.println("La multa total es de: $" + multa);
    }
}
