package com.company;

/**
 * The TrainLinkedList class organizes all of the TrainCarNodes into a doubly linked list. It contains all the methods
 * needed to add and remove from the linked list. The TrainLinkedList contains references to the head and tail of the
 * list, as well a moving cursor that points at a different part of the list at any given point during RunTime. The
 * TrainLinkedList class also holds information about the total length, weight, value, dangerousness of the Train as
 * a whole.
 *
 * @author Kirat Singh
 */
public class TrainLinkedList {
    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;

    private int size;
    private double length;
    private double weight;
    private double value;
    private int numDangerousCars;
    private boolean isDangerous;

    /**
     * Creates an empty TrainLinkedList, with no head, tail, or cursor.
     *
     * Postcondition:
     *      This TrainCar has been initialized to an empty list.
     *      Head, tail, and cursor are all null.
     */
    public TrainLinkedList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Precondition:
     *      The list is not empty.
     * @return
     *      Returns the TrainCar of the current TrainCarNode that the cursor is pointing at.
     * @throws
     *      Exception thrown when list is emptu.
     */
    public TrainCar getCursorData() throws IllegalArgumentException{
        if(cursor != null)
            return cursor.getCar();
        else
            throw new IllegalArgumentException("The List is currently empty!");
    }

    /**
     * Places car in the node currently referenced by the cursor.
     * Precondition:
     *      The list is not empty (cursor is not null).
     * Postcondition:
     *      The cursor node now contains a reference to car as its data.
     * @param car
     *      The new TrainCar for the node.
     */
    public void setCursorData(TrainCar car){
        if(cursor != null)
            cursor.setCar(car);
        else
            throw new IllegalArgumentException("The List is currently empty!");
    }

    /**
     * Inserts a car into the train after the cursor position.
     * Preconditions:
     * This TrainCar object has been instantiated
     * Postconditions:
     * The new TrainCar has been inserted into the train after the position of the cursor.
     * All TrainCar objects previously on the train are still on the train, and thier order has been preserved.
     * The cursor now points to the inserted car.
     *
     * @param newCar
     *      The newCar that the TrainCarNode will hold.
     * @throws IllegalArgumentException
     *      Thrown when the new car is null or weight/value are invalid.
     */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException{
        if(newCar != null) {
            TrainCarNode node = new TrainCarNode(newCar);

            double carLength = node.getCar().getCarLength();
            double carWeight = node.getCar().getCarWeight();
            if(carLength > 0 && carWeight > 0) {
                double prodWeight = 0;
                double prodValue = 0;
                boolean dangerous = false;
                if (!node.getCar().isEmpty()) {
                    prodWeight = node.getCar().getCarLoad().getWeight();
                    prodValue = node.getCar().getCarLoad().getValue();
                    dangerous = node.getCar().getCarLoad().isDangerous();
                }

                if (head == null && tail == null) {
                    head = node;
                    tail = node;
                } else if (cursor == tail) {
                    tail.setNext(node);
                    node.setPrev(tail);
                    node.setNext(null);
                    tail = node;
                } else {
                    node.setNext(cursor.getNext());
                    node.setPrev(cursor);
                    cursor.getNext().setPrev(node);
                    cursor.setNext(node);
                }

                length += carLength;
                weight += carWeight + prodWeight;
                value += prodValue;
                if (dangerous) {
                    if (numDangerousCars == 0) {
                        isDangerous = true;
                    }
                    numDangerousCars++;
                }
                size++;
                cursor = node;
            }else
                throw new IllegalArgumentException("Weight/length values must be greater than 0!");
        }else
            throw new IllegalArgumentException("That car has not been created!");
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns the TrainCar contained within the node.
     * Preconditions:
     * The cursor is not null.
     * Postconditions:
     * The TrainCarNode referenced by the cursor has been removed from the train.
     * The cursor now references the next node, or the previous node if no next node exists.
     * @throws IllegalArgumentException
     *      Thrown when the list is empty.
     */
    public void removeCursor() throws IllegalArgumentException{
        if(cursor != null) {

            double carLength = cursor.getCar().getCarLength();
            double carWeight = cursor.getCar().getCarWeight();
            double prodWeight = 0;
            double prodValue = 0;
            boolean dangerous = false;
            String info = cursor.toString();
            if(!cursor.getCar().isEmpty()){
                prodWeight = cursor.getCar().getCarLoad().getWeight();
                prodValue = cursor.getCar().getCarLoad().getValue();
                dangerous = cursor.getCar().getCarLoad().isDangerous();
            }

            if(cursor != head && cursor != tail){
                TrainCarNode nextNode = cursor.getNext();
                TrainCarNode prevNode = cursor.getPrev();
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                cursor.setPrev(null);
                cursor.setNext(null);
                cursor = nextNode;
            }else if(cursor == head && cursor != tail){
                TrainCarNode nextNode = cursor.getNext();
                nextNode.setPrev(null);
                cursor.setNext(null);
                cursor = nextNode;
                head = nextNode;
            }else if(cursor == tail && cursor != head){
                TrainCarNode prevNode = cursor.getPrev();
                prevNode.setNext(null);
                cursor.setPrev(null);
                cursor = prevNode;
                tail = prevNode;
            }else{
                cursor = null;
                head = null;
                tail = null;
            }

            length -= carLength;
            weight -= carWeight + prodWeight;
            value -= prodValue;
            size--;
            if(dangerous){
                if(numDangerousCars == 1){
                    isDangerous = false;
                }
                numDangerousCars--;
            }
            System.out.println("Car successfully unlinked, the following car was unlinked:");
            System.out.println(info +"\n");

        }else
            throw new IllegalArgumentException("The list is currently empty!");
    }

    /**
     * Moves the cursor to point at the next TrainCarNode.
     * Preconditions:
     * The list is not empty (cursor is not null) and cursor does not currently reference the tail of the list.
     * Postconditions:
     * The cursor has been advanced to the next TrainCarNode, or has remained at the tail of the list.
     */
    public void cursorForward() {
        if (cursor != null) {
            if (cursor != tail) {
                cursor = cursor.getNext();
                System.out.println("Cursor moved forward successfully!");
            }else
                System.out.println("The cursor is at the end of the list.");

        }else
            System.out.println("No cars are in the train!");
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     * Preconditions:
     * The list is not empty (cursor is not null) and the cursor does not currently reference the head of the list.
     * Postconditions:
     * The cursor has been moved back to the previous TrainCarNode, or has remained at the head of the list.
     */
    public void cursorBackward(){
        if(cursor != null) {
            if (cursor != head) {
                cursor = cursor.getPrev();
                System.out.println("Cursor moved backwards successfully!");
            }else
                System.out.println("The cursor is at the beginning of the list.");
        }else
            System.out.println("No cars are in the train!");
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order of the cars in the train.
     * Postconditions:
     * All dangerous cars have been removed from this train.
     * The order of all non-dangerous cars must be maintained upon the completion of this method.
     */
    public void removeDangerousCars(){
        if(isDangerous){
            if(head != null && tail != null){
                TrainCarNode traverser = head;
                do {
                    if(!traverser.getCar().isEmpty()) {
                        if (traverser.getCar().getCarLoad().isDangerous()) {
                            cursor = traverser;
                            removeCursor();
                            traverser = cursor;
                            if (traverser == tail)
                                break;
                            continue;
                        }
                    }
                    traverser = traverser.getNext();
                }while(traverser != null);
                System.out.println("Dangerous cars have been removed from the train");
            }
        }else
            System.out.println("The train has no dangerous cars in it!");
    }

    /**
     * Prints a neatly formatted table of the car number, car length, car weight, load name, load weight, load value,
     * and load dangerousness for all of the car on the train.
     */
    public void printManifest(){
        String s = "Num   Length (m)    Weight (t)  |    Name      Weight (t)     Value ($)   Dangerous\n"
                + "-----------------------------------------------------------------------------------";
        System.out.println(s);
        if(head != null) {
            TrainCarNode traverser = head;
            int counter = 1;
            do {
                double tLength = 0;
                double tWeight = 0;
                String name = "EMPTY";
                double loadWeight = 0;
                double tValue = 0;
                String dangerousString = "NO";
                boolean tDangerous = false;
                String cursorPos = "";

                if(counter != 1 && traverser != tail)
                    traverser = traverser.getNext();
                tLength = traverser.getCar().getCarLength();
                tWeight = traverser.getCar().getCarWeight();

                if(!traverser.getCar().isEmpty()) {
                    name = traverser.getCar().getCarLoad().getName();
                    loadWeight = traverser.getCar().getCarLoad().getWeight();
                    tValue = traverser.getCar().getCarLoad().getValue();
                    tDangerous = traverser.getCar().getCarLoad().isDangerous();

                    if (tDangerous) {
                        dangerousString = "YES";
                    } else
                        dangerousString = "NO";

                }

                if(traverser == cursor)
                    cursorPos = "<- [Cursor]";
                String line = String.format("%-6d%-14.1f%-17.1f%-10s%-15.1f%-12.2f%-16s%-6s", counter, tLength, tWeight, name, loadWeight,
                        tValue, dangerousString, cursorPos);
                System.out.println(line);
                cursorPos = "";
                counter++;
            } while (traverser != tail);
        }

    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name and sums together their weight and value
     * (Also keeps track of whether the product is dangerous or not), then prints a single ProductLoad record to the console.
     * @param name
     *      The name of the product being searched for
     * @throws IllegalArgumentException
     *      Thrown when the product name produces no results.
     */
    public void findProduct(String name) throws IllegalArgumentException{
        TrainCarNode traverser = head;

        double prodWeight = 0;
        double prodValue = 0;
        String dangerousString = "NO";
        String line = "";
        boolean found = false;
        while(traverser != null){
            if(!traverser.getCar().isEmpty()) {
                if (traverser.getCar().getCarLoad().getName().equalsIgnoreCase(name)) {
                    prodWeight += traverser.getCar().getCarLoad().getWeight();
                    prodValue += traverser.getCar().getCarLoad().getValue();
                    if (traverser.getCar().getCarLoad().isDangerous())
                        dangerousString = "YES";
                    found = true;
                }
                traverser = traverser.getNext();
            }
        }
        if(found) {
            System.out.println("The following products were found: ");
            System.out.print("Name      Weight (t)     Value ($)   Dangerous\n");
            System.out.print("----------------------------------------------\n");
            line = String.format("%-10s%-15.1f%-12.2f%-16s", name, prodWeight,
                    prodValue, dangerousString);
            System.out.println(line);
        }else
            throw new IllegalArgumentException("No product with that name was found!");
    }

    /**
     * Returns the total length of the train in meters.
     * @return
     *      The sum of the lengths of each TrainCar in the train.
     */
    public double getLength() {
        return length;
    }
    /**
     * Determines the number of TrainCar objects currently on the train.
     * @return
     *      The number of cars in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the total weight of the Train, including loads and cars.
     * @return
     *      The total weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the number of dangerous cars on the Train, needed to determine whether or not the car is dangerous.
     * @return
     *      The number of dangerous cars on the train.
     */
    public int getNumDangerousCars() {
        return numDangerousCars;
    }

    /**
     * Returns the total value of all the loads on the train.
     * @return
     *      The total value of the train.
     */
    public double getValue() {
        return value;
    }

    /**
     * A boolean value representing if the Train has even a single dangerous car on it.
     * @return
     *      True if the train contains at least one dangerous train, false otherwise.
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Sets the weight of the Train.
     * @param newWeight
     *      The new weight.
     */
    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    /**
     * Sets the value of the Train.
     * @param newValue
     *      The new value.
     */
    public void setValue(double newValue) {
        value = newValue;
    }

    /**
     * Sets whether or not the Train is dangerous.
     * @param dangerous
     *      The updated value for the Train's dangerousness.
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    /**
     * Sets the length of the train.
     * @param newLength
     *      The new length of the train.
     */
    public void setLength(double newLength) {
        length = newLength;
    }

    /**
     * Sets the number of dangerous cars on the train.
     * @param newNumDangerousCars
     */
    public void setNumDangerousCars(int newNumDangerousCars) {
        numDangerousCars = newNumDangerousCars;
    }

    /**
     * Sets the new size of the LinkedList.
     * @param newSize
     */
    public void setSize(int newSize) {
        size = newSize;
    }

    /**
     * Returns a neatly formatted String representation of the train.
     * @return
     *      A neatly formatted string containing information about the train, including it's size (number of cars),
     *      length in meters, weight in tons, value in dollars, and whether it is dangerous or not and the number of
     *      dangerous cars on the Train.
     */
    public String toString(){
        String isDangerous = "NOT DANGEROUS";
        if(isDangerous())
            isDangerous = "DANGEROUS";

        String s = "Train: "+ size()+" cars, "+String.format("%.1f", getLength())+" meters, "
                +String.format("%.1f", getWeight())+" tons, $" + String.format("%.2f", getValue())+" value, " +
                isDangerous+" with "+numDangerousCars+" dangerous cars found on the train.";

        return s;
    }
}
