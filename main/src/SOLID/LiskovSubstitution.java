package SOLID;

// Created By Barbara Liskov
// Objects of a Superclass should be replaced by objects of Subclasses without
// breaking the application

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return height*width; }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = size;
        height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

public class LiskovSubstitution {
    static void useIt(Rectangle rectangle) {
        int width = rectangle.getWidth();
        rectangle.setHeight(10); // no caso de ser um quadrado, ira settar a width para 10 tbm
        // area = width * 10
        System.out.println("Expected area of " + (width * 10) + ", got " +
                rectangle.getArea());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 3);
        Rectangle square = new Square(2);

        useIt(rectangle);
        useIt(square);
    }
}
