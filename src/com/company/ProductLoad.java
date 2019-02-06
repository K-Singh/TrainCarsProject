package com.company;

/**
 * The ProductLoad class holds various information about the product held on the TrainCar. Loads can have a value,
 * weight, name, and can be designated as dangerous or not.
 *
 * @author Kirat Singh
 */
public class ProductLoad {
    private String name;
    private double weight;
    private double value;
    private boolean isDangerous;

    /**
     * Constructs the ProductLoad object and assigns the various fields to the parameters that were passed in.
     * @param prodName
     *      The load's name.
     * @param prodWeight
     *      The weight of the load as a double.
     * @param prodValue
     *      The value of the load as a double.
     * @param dangerous
     *      A boolean representing whether or not the load contains a dangerous item.
     */
    public ProductLoad(String prodName, double prodWeight, double prodValue, boolean dangerous){
        name = prodName;
        setWeight(prodWeight);
        setValue(prodValue);
        isDangerous = dangerous;
    }

    /**
     * @return
     *      Returns whether or not the load is dangerous.
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * @return
     *      Returns the value of the load.
     */
    public double getValue() {
        return value;
    }

    /**
     * @return
     *      Returns the weight of the load.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return
     *      Returns the name of the load.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets whether or not the load is dangerous.
     * @param dangerous
     *      Boolean that represents if the load is dangerous.
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    /**
     * Sets the name of the load.
     * @param pName
     *      The new name.
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * Sets the new value of the load
     * @param pValue
     *      The new value.
     * @throws IllegalArgumentException
     *      Thrown if the new value is < 0, which represents an invalid argument.
     */
    public void setValue(double pValue) throws IllegalArgumentException{
        if(pValue >= 0)
        value = pValue;
        else throw new IllegalArgumentException("The value must be greater than or equal to 0!");
    }

    /**
     * Sets the new weight of the load.
     * @param pWeight
     *      The new weight.
     * @throws IllegalArgumentException
     *      Thrown when the weight is <= 0, representing an invalid argument for the method.
     */
    public void setWeight(double pWeight) throws IllegalArgumentException {
        if(pWeight > 0)
        weight = pWeight;
        else throw new IllegalArgumentException("The weight must be greater than 0!");
    }
}
