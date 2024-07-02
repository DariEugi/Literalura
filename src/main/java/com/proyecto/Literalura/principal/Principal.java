package com.proyecto.Literalura.principal;

import com.proyecto.Literalura.service.EjecucionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    @Autowired
    private EjecucionesService ejecucionesService;

    private Scanner teclado = new Scanner(System.in);

    public void elMenu() {
        int opcion = -1;
        while (opcion != 0){
            var menu = """
                    ----------------------------
                    Elija la opción a través de su número:
                    1 - buscar libro por título
                    2 - listar libros registrados
                    3 - listar autores regístrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    0 - salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1: ejecucionesService.ejecucionUno();
                    break;
                case 2: ejecucionesService.ejecucionDos();
                    break;
                case 3: ejecucionesService.ejecucionTres();
                    break;
                case 4: ejecucionesService.ejecucionCuatro();
                    break;
                case 5: ejecucionesService.ejecucionCinco();
                    break;
                case 0:
                    System.out.println("Biblioteca cerrada, vuelva pronto!!");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    }
}
