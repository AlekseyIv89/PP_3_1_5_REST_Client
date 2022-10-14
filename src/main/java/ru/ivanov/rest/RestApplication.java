package ru.ivanov.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.ivanov.rest.entity.User;
import ru.ivanov.rest.service.Communication;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestApplication.class, args);

		Communication communication = context.getBean("communication", Communication.class);

		communication.getAllUsers();
		String result = communication.saveUser(new User(3L, "James", "Brown", (byte) 50));
		result += communication.updateUser(new User(3L, "Thomas", "Shelby", (byte) 50));
		result += communication.deleteUser(3L);

		System.out.println(result + ", size = " + result.length());
	}
}
