package com.company;

/**
 * The TrainCar class holds the information about the TrainCar. Each TrainCar has an associated length and weight
 * assigned to it. TrainCar's also have a ProductLoad that maybe null, or could be set using the setCarLoad()
 * method.
 *
 * @author Kirat Singh
 */
public class TrainCar {
    private double carLength;
    private double carWeight;
    private ProductLoad carLoad;

    /**
     * Creates the TrainCar object and assigns the length and weight of the Car. The load is null.
     * @param length
     *      The new length of the TrainCar.
     * @param weight
     *      The new weight of the TrainCar.
     */
    public TrainCar(double length, double weight){
        carLength = length;
        carWeight = weight;
        carLoad = null;
    }

    /**
     * @return
     *      Returns the length of the TrainCar.
     */
    public double getCarLength(){
        return carLength;
    }

    /**
     * @return
     *      Returns the weight of the TrainCar.
     */
    public double getCarWeight(){
        return carWeight;
    }

    /**
     * @return
     *      Returns the ProductLoad held by the TrainCar.
     */
    public ProductLoad getCarLoad() {
        return carLoad;
    }

    /**
     * Sets the TrainCar's ProductLoad.
     * @param load
     *      The new ProductLoad.
     */
    public void setCarLoad(ProductLoad load) {
        carLoad = load;
    }

    /**
     * Tells the user whether or not the TrainCar is empty, i.e. the carLoad is currently null.
     * @return
     *      Returns true if there is no load, false if there is.
     */
    public boolean isEmpty(){
        if(carLoad == null){
            return true;
        }
        return false;
    }
}
