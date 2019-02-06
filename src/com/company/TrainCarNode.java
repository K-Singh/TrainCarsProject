package com.company;

/**
 * The TrainCarNode is a wrapper class for the TrainCar. The TrainCarNode is a component of the TrainLinkedList
 * and it gives each TrainCar a place in the TrainLinkedList. All TrainCarNodes have a previous and next TrainCarNode,
 * as well as their own unique TrainCar that holds all the real info.
 */
public class TrainCarNode {
    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * Constructs the TrainCarNode with its own TrainCar.
     * @param currentCar
     *      The TrainCar assigned to this TrainCarNode.
     */
    public TrainCarNode(TrainCar currentCar) {
        car = currentCar;
        prev = null;
        next = null;
    }

    /**
     * Constructs the TrainCarNode without a TrainCar, meaning that all private variables are null. Mostly used for
     * temporary Nodes in situations involving list traversal.
     */
    public TrainCarNode() {
        car = null;
        prev = null;
        next = null;
    }

    /**
     * Gets the next node in the TrainLinkedList.
     * @return
     *      Returns the next node, or null if this TrainCarNode is the tail.
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Gets the previous node in the TrainLinkedList.
     * @return
     *      Returns the previous node, or null if this TrainCarNode is the head.
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * @return
     *       Returns the current TrainCar assigned to this node.
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Sets the previous node to some new TrainCarNode
     * @param newNode
     *      The new node that is to be placed behind this one.
     */
    public void setPrev(TrainCarNode newNode) {
        prev = newNode;
    }

    /**
     * Sets the next node to some new TrainCarNode
     * @param newNode
     *      The new node that is to be placed behind this one.
     */
    public void setNext(TrainCarNode newNode) {
        next = newNode;
    }

    /**
     * Sets the TrainCar for this node.
     * @param newCar
     *      The new TrainCar for the node.
     */
    public void setCar(TrainCar newCar) {
        car = newCar;
    }

    /**
     * Outputs all the info about this TrainCarNode, including the length, weight, as well as the info about
     * the ProductLoad held on the TrainCar.
     * @return
     *      A neatly formatted table containing all the info about the TrainCar.
     */
    public String toString() {
        String name = "EMPTY";
        double loadWeight = 0;
        double value = 0;
        String dangerousString = "NO";

        String s = "Length (m)    Weight (t)  |    Name      Weight (t)     Value ($)   Dangerous\n"
                + "-----------------------------------------------------------------------------\n";
        if (car != null) {
           if(!car.isEmpty()){
               value = car.getCarLoad().getValue();
               loadWeight = car.getCarLoad().getWeight();
               name = car.getCarLoad().getName();
               if(car.getCarLoad().isDangerous()){
                   dangerousString = "YES";
               }
           }
            String line = String.format("%-14.1f%-17.1f%-10s%-15.1f%-12.2f%-16s", car.getCarLength(), car.getCarWeight(), name, loadWeight,
                    value, dangerousString);
           s += line;
        }
        return s;
    }
}
