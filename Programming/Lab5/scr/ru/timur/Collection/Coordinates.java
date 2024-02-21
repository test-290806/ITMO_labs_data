package ru.timur.Collection;

/**
 * Class to store and operate with coordinates
 */
public class Coordinates implements Comparable<Coordinates>{
    /**
     * x coordinate
     * <p>Max value is 657
     */
    private double x;

    /**
     * y coordinate
     */
    private double y;

    /**
     * Coordinate class constructor
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get formatted string representation of Coordinate
     * @return String value
     */
    @Override
    public String toString() {
        return "Coordinates:\n" +
                "\tx: " + x + "\n" +
                "\ty: " + y + "\n";
    }

    /**
     * Method to get x coordinate
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Method to get y coordinate
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Method to compare two coordinates
     * <p>Coordinates are compared by their distance from (0, 0) point
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Coordinates o) {
        return (int) (((this.x * this.x) + (this.y * this.y)) - ((o.x * o.x) + (o.y * o.y)));
    }
}
