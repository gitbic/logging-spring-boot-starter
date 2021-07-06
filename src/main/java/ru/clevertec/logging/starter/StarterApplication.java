package ru.clevertec.logging.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.clevertec.logging.starter.entity.MyPrototype;

@SpringBootApplication
@RequiredArgsConstructor
public class StarterApplication {

//	private final ObjectProvider<MyPrototype> myPrototypeProvider;

	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("hello world, I have just started up");
//		final MyPrototype myPrototype = myPrototypeProvider.getObject("hello");
//		myPrototype.action();
	}

}
