import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuilderExample {
    /*
    * Exercise
    * ========
        Create a Pizza class using the Builder pattern.
    The pizza should have the following customizable properties:
        Size (mandatory): String — small, medium, or large.
        Cheese (optional): boolean.
        Pepperoni (optional): boolean.
        Bacon (optional): boolean.
        Extra Toppings (optional): List<String>.
    Requirements
        Implement the Builder class inside the Pizza class.
        Make the size a required parameter, while all other toppings are optional.
        Add a build() method that returns a fully constructed Pizza object.
        Use a toString() method to print the final pizza details.
        Write a main method that demonstrates creating different pizzas with various toppings.
    Bonus Challenge
        Add a Director class to create a default pizza or a custom pizza using predefined configurations.
        Ensure thread safety for the Pizza object if needed.
* */
    public static void main(String[] args) {
        Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder(3);
        PizzaDirector pizzaDirector = new PizzaDirector(pizzaBuilder);

        Pizza defaultPizza = pizzaDirector.makeDefaultPizza();
        Pizza pepperoniWithBaconPizza = pizzaDirector.makePepperoniWithBaconPizza();
        Pizza cheeseWithBaconPizza = pizzaDirector.makeCheeseWithBaconPizza();
        Pizza pineapplePizza = pizzaDirector.makePineapplePizza();

        System.out.println(defaultPizza);
        System.out.println(pepperoniWithBaconPizza);
        System.out.println(cheeseWithBaconPizza);
        System.out.println(pineapplePizza);
    }
}

class Pizza {
    private final int size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean bacon;
    private List<String> extraToppings;

    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.bacon = builder.bacon;
        this.extraToppings = builder.extraToppings;
    }

    public static class PizzaBuilder {
        private final int size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean bacon;
        private List<String> extraToppings;

        public PizzaBuilder(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public PizzaBuilder setCheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder setPepperoni(boolean pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }

        public PizzaBuilder setBacon(boolean bacon) {
            this.bacon = bacon;
            return this;
        }

        public PizzaBuilder setExtraToppings(List<String> extraToppings) {
            this.extraToppings = extraToppings;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        Map<String, Boolean> pizzaMainToppings = Map.of(
                "Cheese", cheese,
                "Pepperoni", pepperoni,
                "Bacon", bacon
        );
        return "Pizza of size " + size + (pizzaMainToppings.entrySet().stream().filter(Map.Entry::getValue).toList().isEmpty()? "" : " and ") +
                pizzaMainToppings.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.joining(" and ")) +
                (extraToppings == null? "" : " and " + extraToppings.stream().map(topping -> topping).collect(Collectors.joining(", ")));
    }
}

class PizzaDirector {
    private final Pizza.PizzaBuilder builder;

    public PizzaDirector(Pizza.PizzaBuilder builder) {
        this.builder = builder;
    }

    public Pizza makeDefaultPizza() {
        return new Pizza.PizzaBuilder(builder.getSize()).setCheese(true).build();
    }

    public Pizza makePepperoniWithBaconPizza() {
        return new Pizza.PizzaBuilder(builder.getSize()).setPepperoni(true).setBacon(true).build();
    }

    public Pizza makeCheeseWithBaconPizza() {
        return new Pizza.PizzaBuilder(builder.getSize()).setCheese(true).setBacon(true).build();
    }

    public Pizza makePineapplePizza() {
        return new Pizza.PizzaBuilder(builder.getSize()).setExtraToppings(List.of("pineapple")).build();
    }
}
