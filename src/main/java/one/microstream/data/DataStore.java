package one.microstream.data;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import one.microstream.domain.Pizza;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

@ApplicationScoped
public class DataStore {

	private List<Pizza> pizzas = new ArrayList<>();

	public void postConstruct(@Observes @Initialized(ApplicationScoped.class) Object o) {
		try {
			Thread.sleep(2_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (this.pizzas.isEmpty()) {

			this.pizzas.add(new Pizza("P 1", "Hawaiian", 2080, "The controversy"));
			this.pizzas.add(new Pizza("P 2", "Margherita", 1000, "Nice and easy"));
			this.pizzas.add(new Pizza("P 3", "Prosciutto e funghi", 1890, "It's e me Mario"));
			this.pizzas.add(new Pizza("P 4", "Quattro Formaggi", 3000, "All the cheese"));
			this.pizzas.add(new Pizza("P 5", "Pepperoni", 2220, "On a pizza?"));
			this.pizzas.add(new Pizza("P 6", "Ham and Cheese", 2980, "Ham with more Cheese"));
			this.pizzas.add(new Pizza("P 7", "Nacho", 3100, "Mexican Style"));

			// Initialize a storage manager ("the database") with purely defaults.
			EmbeddedStorageManager storage = EmbeddedStorage.start(this.pizzas, Path.of("my.database1"));
			storage.storeRoot();
			storage.shutdown();
		}
	}


	public List<Pizza> getPizzas() {
		return pizzas;
	}

}