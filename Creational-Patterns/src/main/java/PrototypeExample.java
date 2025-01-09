import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class PrototypeExample {
    /*
    * Task: Shape Cloning System
        You are building a Shape Drawing Application that supports different types of shapes
        like circles and rectangles. These shapes can have various properties, including color,
        dimensions, and positions on a canvas. Your goal is to implement a Prototype-based cloning system so
        that new shapes can be created by copying existing ones instead of initializing them from scratch.

    Requirements
        - Create an interface Shape with the clone() method.
        - Implement concrete shape classes Circle and Rectangle with properties like:
            Circle: radius, color
            Rectangle: width, height, color
        - Use a shape registry (similar to a document registry) to store prototype instances of Circle and Rectangle.
        - Create a client that:
        - Clones a circle and rectangle from the registry.
        - Modifies some properties of the cloned shapes.
        - Prints out their details to verify they were successfully cloned
          and modified without affecting the prototypes.
    Example Output
        Original Circle: radius=5, color=Red
        Cloned Circle: radius=5, color=Blue

        Original Rectangle: width=10, height=15, color=Green
        Cloned Rectangle: width=20, height=15, color=Yellow
    * */
    public static void main(String[] args) {
        Map<String, Shape> shapeRegistry = new HashMap<>();
        shapeRegistry.put("Rectangle", new Rectangle(10,15, "Green"));
        shapeRegistry.put("Circle", new Circle(5, "Red"));

        Shape clonedCircle = shapeRegistry.get("Circle").clone();
        Shape clonedRectangle = shapeRegistry.get("Rectangle").clone();

        if (clonedCircle instanceof Circle circle) {
            circle.setColor("Blue");
        }

        if (clonedRectangle instanceof Rectangle rectangle) {
            rectangle.setWidth(20);
            rectangle.setColor("Yellow");
        }

        System.out.printf("Original %s", shapeRegistry.get("Circle"));
        System.out.printf("Cloned %s", clonedCircle);
        System.out.printf("Original %s", shapeRegistry.get("Rectangle"));
        System.out.printf("Cloned %s", clonedRectangle);
    }
}

interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

@Setter
class Rectangle implements Shape {
    private int width;
    private int height;
    private String color;

    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.width, this.height, this.color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }

    @Override
    public String toString() {
        return "Rectangle: " +
                "width=" + width +
                ", height=" + height +
                ", color=" + color +
                '\n';
    }
}

@Setter
class Circle implements Shape {
    private int radius;
    private String color;

    public Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Circle clone() {
        return new Circle(this.radius, this.color);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    public String toString() {
        return "Circle: " +
                "radius=" + radius +
                ", color=" + color +
                '\n';
    }
}

