package co.edu.javeriana.as.personapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.javeriana.as.personapp.terminal.menu.MenuPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PersonAppCli implements CommandLineRunner {
	
	@Autowired
	private MenuPrincipal menuPrincipal;

	public static void main(String[] args) {
		SpringApplication.run(PersonAppCli.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuPrincipal.inicio();
	}

}
