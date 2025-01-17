package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"bibliotecaApp"})
public class SpaceInvadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceInvadersApplication.class, args);
	}

}
