package com.Challenges.BibliotecaYd.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.Challenges.BibliotecaYd.model.Autor;
import com.Challenges.BibliotecaYd.model.DatosLibro;
import com.Challenges.BibliotecaYd.model.DatosTotales;
import com.Challenges.BibliotecaYd.model.Libro;
import com.Challenges.BibliotecaYd.repository.AutorRepository;
import com.Challenges.BibliotecaYd.repository.LibroRepository;
import com.Challenges.BibliotecaYd.service.ConsumoApi;
import com.Challenges.BibliotecaYd.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Main {

    private final String URL_BASE = "http://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    @Autowired
    public Main(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.libros = new ArrayList<>();
        this.autores = new ArrayList<>();
    }

     public void muestraElMenu() {
          var opcion = -1;
          while (opcion != 0) {
               var menu = """
            
            1 - Buscar libro web
            2- Total de Libros 
            3- Mostrar total de autores registrados
            4- Buscar autores por año especifico 
            5- mostrar libros por lenguaje 

            0 - Salir
            """;
               System.out.println(menu);

               try {
                    opcion = teclado.nextInt();
                    teclado.nextLine(); // limpiar el buffer
               } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    teclado.nextLine(); // limpiar el buffer
                    continue; // volver al inicio del bucle
               }

               switch (opcion) {
                    case 1:
                         buscarLibroWeb();
                         break;
                    case 2:
                          mostrarLibrosBuscados();
                          break;
                   case 3:
                       mostrarAutores();
                       break;
                   case 4:
                       encuentraAutoresPorAño();
                       break;
                   case 5:
                       muestraLibrosPorLengua();
                       break;
                    case 0:
                         System.out.println("Cerrando la aplicación...");
                         break;
                    default:
                         System.out.println("Opción inválida");
               }
          }
     }

     private DatosLibro getDatosLibro() {
          System.out.println("Escribe el nombre del libro que deseas buscar");
          var nombreLibro = teclado.nextLine();
          Optional<Libro> libro = libros.stream()
                  .filter(s -> s.getTitulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                  .findFirst();
          var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
          System.out.println(json);
          DatosTotales datos = conversor.obtenerDatos(json, DatosTotales.class);
         if (datos.resultados().isEmpty()) {
             throw new RuntimeException("No se encontraron libros con ese título.");
         }
          return datos.resultados().get(0);
     }

     public void buscarLibroWeb (){
          DatosLibro datos = getDatosLibro();
          //Guardar en el repositorio
         Libro libroPresente = libroRepository.findByTituloContainingIgnoreCase(datos.titulo());

         if (libroPresente != null) {
             System.out.println("No se puede registar el libro, ya existe");
         } else if (datos != null) {
             //Busco y/o guardo autores
             List<Autor> listaAutor = datos.autores().stream()
                     .map(datosAutor -> {
                         return autorRepository.findByNombreContainingIgnoreCase(datosAutor.nombre())
                                 .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

                     })
                     .collect(Collectors.toList());
             // Crear el nuevo libro
             Libro nuevoLibro = new Libro(datos, listaAutor);
             // Guardar el nuevo libro en la base de datos
             nuevoLibro.setAutores(listaAutor);
             libroRepository.save(nuevoLibro);
             System.out.println(nuevoLibro);
         } else {
             System.out.println("Libro no encontrado");
         }

     }

     private void mostrarLibrosBuscados() {
         libros = libroRepository.findAllWithAutores();
         libros.stream()
                 .sorted(Comparator.comparing(Libro::getTitulo))
                 .forEach(System.out::println);
          }

    private void mostrarAutores() {
       autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private void encuentraAutoresPorAño() {
        try {
            System.out.println("Ingrese un año para buscar autores");
            int añoBusqueda = teclado.nextInt();
            autores = autorRepository.autorPorFecha(añoBusqueda);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos para ese año");
            } else {
                System.out.println(" ----- Autores vivos en ese año -----\n");
                autores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un año válido como número entero.");
            teclado.next(); // Limpiar el buffer del teclado
        }
    }


    public void muestraLibrosPorLengua() {
        System.out.println("Ingrese el idioma para buscar libros:\n" +
                "es - Español\n" +
                "en - Inglés\n" +
                "fr - Francés\n" +
                "pt - Portugués");

        String lenguaje = teclado.nextLine().trim().toLowerCase();

        // Lista de idiomas válidos
        List<String> validLanguages = Arrays.asList("es", "en", "fr", "pt");

        if (!validLanguages.contains(lenguaje)) {
            System.out.println("Opción de idioma inválida. Por favor, elija una de las opciones proporcionadas.");
            return;
        }

        List<Libro> libros = libroRepository.findByLenguajesContains(lenguaje);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + lenguaje + "'.");
        } else {
            System.out.println("Libros en el idioma '" + lenguaje + "':");
            libros.forEach(libro -> {
                System.out.println(libro);
            });
        }
    }
}
