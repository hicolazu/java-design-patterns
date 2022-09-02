package SOLID;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE;
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE;
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

@Deprecated
class ProductFilter {
    // Sem o padrão Specification, teríamos que criar um método para cada atríbuto
    // (criteria) que queremos filtrar
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }
}

// Ao invés de modificar a classe ProductFilter a cada novo filtro, usaremos
// a herança (extension) para adicionar novos critérios (criteria)
interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class BetterProductFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(spec::isSatisfied);
    }
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

// OCP: Open for Extension, Closed for Modification
public class OpenClosedPrinciple {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        // bad solution
        ProductFilter productFilter = new ProductFilter();
        System.out.println("Green products (bad filter): ");
        productFilter.filterByColor(products, Color.GREEN)
                .forEach(product -> System.out.println(" - " + product.name + " is green"));

        // better solution using Specification pattern
        BetterProductFilter betterProductFilter = new BetterProductFilter();
        System.out.println("Green products (better filter): ");
        betterProductFilter.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(product -> System.out.println(" - " + product.name + " is green"));

        System.out.println("Green and large products (better filter): ");
        betterProductFilter.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.GREEN),
                new SizeSpecification(Size.LARGE)
        )).forEach(product -> System.out.println(" - " + product.name + " is green and large"));


    }
}
