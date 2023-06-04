package com.example.proj2.helpers;

import com.example.proj2.datastructure.*;
import com.example.proj2.model.Brand;
import com.example.proj2.model.Car;
import com.example.proj2.model.Order;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;

public class FileManagement {


    private Queue<Order> orderQueue = new Queue<>(500);
    private StackCa<Order> finishedOrder = new StackCa<>(500);
    private String path;

    public FileManagement(String path) {
        this.path = path;
    }

    public DLinkedList<Brand> readCars() {
        DLinkedList<Brand> brands = new DLinkedList<>();
        Stage stage2 = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage2);// open stage file chooser
        if (file != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                String csvDelimiter = ",";
                while ((line = br.readLine()) != null) {

                    String[] data = line.split(csvDelimiter);

                    if(data[0].equals("Brand"))
                        continue;

                    if(!isValidN(data[2])) {
                        NodeD<Brand> x = brands.findNode(new Brand(data[0]));
                        if (x == null) {
                            Brand b = new Brand(data[0]);
                            b.getCars().Insertsorted(new Car(data[0], data[1], Integer.parseInt(data[2].trim()), data[3], data[4]));
                            brands.insertSorted(b);
                        } else {
                            Brand b = x.getData();
                            b.getCars().Insertsorted(new Car(data[0], data[1], Integer.parseInt(data[2].trim()), data[3], data[4]));
                        }
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done!");
                alert.setContentText("the item has been added!!");
                alert.show();
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();

            }catch (NumberFormatException q) {
                System.out.println(q.getMessage());
            }
            catch (Exception e1) {
                System.out.println(e1.toString());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error!");
                alert.setContentText("Please Select a valid file !!");
                alert.show();
            }
        }
        return brands;
    }

    public void readCustomerOrders(DLinkedList<Brand> brands) {
        Stage stage2 = new Stage();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage2);// open stage file chooser
        if (file != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                String csvDelimiter = ",";
                while ((line = br.readLine()) != null) {

                    String[] data = line.split(csvDelimiter);

                    if(data[0].equals("CustomerName"))
                        continue;

                    NodeD<Brand> x = brands.findNode(new Brand(data[2].trim()));
                    if(x != null) {
                        if(isValidN(data[4].trim())) {
                            if(data[8].trim().equalsIgnoreCase("Finished")) {
                                finishedOrder.puch(new Order(data[0], data[1], new Car(data[2], data[3], Integer.parseInt(data[4].trim()), data[5],
                                        data[6]), data[8], data[7]));
                            } else {
                                orderQueue.enqueue(new Order(data[0], data[1], new Car(data[2], data[3], Integer.parseInt(data[4].trim()), data[5],
                                        data[6]), data[8], data[7]));
                            }
                        }
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done!");
                alert.setContentText("the item has been added!!");
                alert.show();
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();

            }catch (NumberFormatException q) {
                System.out.println(q.getMessage());
            }
            catch (Exception e1) {
                System.out.println(e1.toString());
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error!");
                alert.setContentText("Please Select a valid file !!");
                alert.show();
            }
        }
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(Queue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    public StackCa<Order> getFinishedOrder() {
        return finishedOrder;
    }

    public void setFinishedOrder(StackCa<Order> finishedOrder) {
        this.finishedOrder = finishedOrder;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public static boolean isValidN(String date){
        try{
            Integer.parseInt(date);
        }catch (NumberFormatException e){
            System.out.print("");
            return false;
        }
        return true;
    }


    public void saveCarsOnfile(DLinkedList<Brand> brands) {

        Stage stage3 = new Stage();

        FileChooser fc = new FileChooser();
        fc.setTitle("Select a folder to save the file");
        //fc.setInitialDirectory(new File(System.getProperty("output")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFolder = fc.showSaveDialog(stage3);

        if (selectedFolder != null) {
            try {
                File file = new File(selectedFolder.getAbsolutePath());
                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                br.write("Brand, Model, Year, Color, Price\n");

                NodeD<Brand> currBrand = brands.getHead().getNext();
                while (currBrand != null) {
                    Node<Car> currCar = currBrand.getData().getCars().getHead().getNext();
                    while (currCar != null) {
                        br.write(currCar.getData().getBrand()+ ","+ currCar.getData().getModel()+ "," + currCar.getData().getYear()
                                + ","+currCar.getData().getColor() + ","+currCar.getData().getPrice() + "\n");
                        currCar = currCar.getNext();
                    }
                    currBrand = currBrand.getNext();
                }
                br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }


    public void saveCarsOnfile(StackCa<Order> finishedOrder, Queue<Order> orderQueue) {

        Stage stage3 = new Stage();

        FileChooser fc = new FileChooser();
        fc.setTitle("Select a folder to save the file");
        //fc.setInitialDirectory(new File(System.getProperty("output")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFolder = fc.showSaveDialog(stage3);

        if (selectedFolder != null) {
            try {
                File file = new File(selectedFolder.getAbsolutePath());
                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                br.write("CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, OrderDate, OrderStatus\n");
                StackCa<Order> tmp = new StackCa<>(1);
                Order curr = finishedOrder.peek();

                while (curr != null) {
                    tmp.puch(curr);
                    br.write(curr.getCustomerName() .trim()+ ", " + curr.getCustomerPhone().trim() + ", " + curr.getCar().getBrand().getBrand().trim() + ", "
                            + curr.getCar().getModel().trim() + ", "+ curr.getCar().getYear() + ", " + curr.getCar().getColor().trim() + ", "+
                            curr.getCustomerPhone().trim() + ", "+
                            curr.getCar().getPrice().trim() + ", " + curr.getDate().trim() + ", "+ curr.getStatus().trim() + "\n");
                    finishedOrder.pop();
                    curr = finishedOrder.peek();
                }

                curr = tmp.peek();
                while (curr != null) {
                    finishedOrder.puch(curr);
                    tmp.pop();

                    curr = tmp.peek();
                }
                //Queue
                Queue<Order>temp = new Queue<>(1);
                Order curro = orderQueue.getFront();
                while (curro != null) {
                    temp.enqueue(curr);
                    br.write(curro.getCustomerName() .trim()+ ", " + curro.getCustomerPhone().trim() + ", " + curro.getCar().getBrand().getBrand().trim() + ", "
                            + curro.getCar().getModel().trim() + ", "+ curro.getCar().getYear() + ", " + curro.getCar().getColor().trim() + ", "+
                            curro.getCustomerPhone().trim() + ", "+
                            curro.getCar().getPrice().trim() + ", " + curro.getDate().trim() + ", "+ curro.getStatus().trim() + "\n");
                    orderQueue.dequeue();
                    curro = orderQueue.getFront();
                }

                curro = temp.getFront();
                while (curro != null) {
                    orderQueue.enqueue(curro);
                    temp.dequeue();

                    curro = temp.dequeue();
                }

                br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }

}
