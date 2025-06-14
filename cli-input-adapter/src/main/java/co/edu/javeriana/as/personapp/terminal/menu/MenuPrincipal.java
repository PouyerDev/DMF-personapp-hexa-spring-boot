package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.terminal.adapter.EstudiosInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfesionInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.TelefonoInputAdapterCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuPrincipal {
	
	// Beans
	@Autowired
	private PersonaInputAdapterCli personaInputAdapterCli;

	@Autowired
	private ProfesionInputAdapterCli profesionInputAdapterCli;

	@Autowired
	private TelefonoInputAdapterCli telefonoInputAdapterCli;

	@Autowired
	private EstudiosInputAdapterCli estudiosInputAdapterCli;

	private static final int SALIR = 0;
	private static final int MODULO_PERSONA = 1;
	private static final int MODULO_PROFESION = 2;
	private static final int MODULO_TELEFONO = 3;
	private static final int MODULO_ESTUDIOS = 4;

	private final PersonaMenu personaMenu;
	private final ProfesionMenu profesionMenu;
	private final TelefonoMenu telefonoMenu;
	private final EstudiosMenu estudiosMenu;
	private final Scanner keyboard;

    public MenuPrincipal() {
        this.personaMenu = new PersonaMenu();
		this.profesionMenu = new ProfesionMenu();
		this.telefonoMenu = new TelefonoMenu();
		this.estudiosMenu = new EstudiosMenu();
        this.keyboard = new Scanner(System.in);
    }

	public void inicio() {
		boolean isValid = false;
		do {
			mostrarMenu();
			int opcion = leerOpcion();
			switch (opcion) {
			case SALIR:
				isValid = true;
				break;
			case MODULO_PERSONA:
				personaMenu.iniciarMenu(personaInputAdapterCli, keyboard);
				break;
			case MODULO_PROFESION:
				profesionMenu.iniciarMenu(profesionInputAdapterCli, keyboard);
				break;
			case MODULO_TELEFONO:
				telefonoMenu.iniciarMenu(telefonoInputAdapterCli, keyboard);
				break;
			case MODULO_ESTUDIOS:
				estudiosMenu.iniciarMenu(estudiosInputAdapterCli, keyboard);
				break;
			default:
			}

		} while (!isValid);
		keyboard.close();
	}

	private void mostrarMenu() {
		System.out.println("######################");
		System.out.println("MENÚ PRINCIPAL");
		System.out.println(MODULO_PERSONA + " - Módulo de Personas");
		System.out.println(MODULO_PROFESION + " - Módulo de Profesiones");
		System.out.println(MODULO_TELEFONO + " - Módulo de Teléfonos");
		System.out.println(MODULO_ESTUDIOS + " - Módulo de Estudios");
		System.out.println(SALIR + " - Salir");
		System.out.println("######################");
	}

	private int leerOpcion() {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			keyboard.next(); // Limpiar el buffer
			return leerOpcion();
		}
	}
}
