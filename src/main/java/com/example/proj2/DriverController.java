package com.example.proj2;

import com.example.proj2.datastructure.*;
import com.example.proj2.helpers.FileManagement;
import com.example.proj2.model.Brand;
import com.example.proj2.model.Car;
import com.example.proj2.model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.security.auth.login.CredentialException;
import java.net.IDN;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DriverController {


    public TextField YearUpdateCarCo;
    public TextField ColorUpdateCarCo;
    public TextField PriceUpdateCarCo;
    public ComboBox<Brand> BrandSearchCarCo;
    public ComboBox<String> ModelSearchCarCo;
    public ComboBox<Integer> YearSearchCarCo;
    public ComboBox<String> colorSearchCA;
    public ComboBox<String> priceSearchCA;
    public TextField BrandUpdateCarCo;
    public TextField ModelUpdateCarCo;
    public ComboBox<Brand> BrandDeleteCarCo;
    public ComboBox<String> ModelDeleteCarCo;
    public ComboBox<Integer> YearDeleteCarCo;
    public ComboBox<String> ColorDeleteCarCo;
    public ComboBox<String> PriceDeleteCarCo;
    public TextField carBrandATF;
    public TextField carModelATF;
    public TextField carYearATF;
    public TextField carColorATF;
    public TextField CustomerNameATF;
    public TextField CustomerPhoneATTF;
    public Label CustomerPhoneATF;
    public Button nextB;
    public TextField carPriceATF1;
    public Button proccessBA;
    public Button cancelBA;
    public Button enqueueBA;
    public Button showAllB;
    public Button lastTenB;
    public TextArea textArea;
    public AnchorPane Uanchor;
    public AnchorPane Aanchor;
    public ComboBox<Brand> Ubrand;
    public Button Usearch;
    public ComboBox<String> Umodel;
    public ComboBox<Integer> Uyear;
    public ComboBox<String> Ucolor;
    public ComboBox<Car> Uresults;
    public TextField Uphone;
    public TextField Uname;
    public Button UClear;
    DLinkedList<Brand> brands = new DLinkedList<>();
    @FXML
    private Button AddCarBt;
    @FXML
    private ComboBox<Brand> BrandCarCo;
    @FXML
    private TextField CarColorTF;
    @FXML
    private TextField CarPriceTF;
    @FXML
    private TextField CarYearTF;
    @FXML
    private TextField CarmodelAddTF;
    @FXML
    private TextField CarmodelDeleteTF;
    @FXML
    private TextField CarmodelSearchTF;
    @FXML
    private TextField CarmodelUpdateTF;
    @FXML
    private Button DeleteCarBt;
    @FXML
    private Button SearchCarBt;
    @FXML
    private Button UpdateCarBt;
    @FXML
    private Button addBrandB;
    @FXML
    private TextField addBrandTF;
    @FXML
    private Button deleteBrandB;
    @FXML
    private TextField deleteBrandTF;
    @FXML
    private Button readCarsB;
    @FXML
    private Button searchBrandB;
    @FXML
    private TextField searchBrandTF;
    @FXML
    private Button updateBrandB;
    @FXML
    private TextField updateBrandNTF;
    @FXML
    private TextField updateBrandTF;
    private StackCa<Order> finishedOrders;
    private Queue<Order> orderQueue;



    @FXML
    void readCarsBA(ActionEvent event) {
        FileManagement fm = new FileManagement("");
        brands = fm.readCars();
        updateComboBox();
    }

    @FXML
    void addBrandBA(ActionEvent event) {

        if(addBrandTF.getText().equals("")) {
            return;
        }
        NodeD<Brand> x = brands.findNode(new Brand(addBrandTF.getText()));
        if (x == null) {
            Brand b = new Brand(addBrandTF.getText());
            brands.insertSorted(b);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("The Brand IS Already Added !!");
            alert.show();
        }
        updateComboBox();
    }

    @FXML
    void deleteBrandBA(ActionEvent event) {

        if(deleteBrandTF.getText().equals("")) {
            return;
        }

        NodeD<Brand> x = brands.findNode(new Brand(deleteBrandTF.getText()));
        if (x == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("The Brand IS Already Added !!");
            alert.show();
        } else {
            System.out.println(deleteBrandTF.getText().trim());
            brands.Deleteloc(new Brand(deleteBrandTF.getText().trim()));
        }
        updateComboBox();
    }

    @FXML
    void searchBrandBA(ActionEvent event) {
        if(searchBrandTF.getText().equals("")) {
            return;
        }

        NodeD<Brand> x = brands.findNode(new Brand(searchBrandTF.getText()));
        if (x == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("The Brand IS Already Added !!");
            alert.show();
        } else {
            StringBuilder record = new StringBuilder();

            Brand b = x.getData();

            Node<Car> curr = b.getCars().getHead().getNext();

            while(curr != null) {
                record.append(curr.getData().toString()).append("\n");

                curr = curr.getNext();
            }

            Stage stage = new Stage();
            Pane p = new Pane();
            TextArea ta = new TextArea();
            ta.setText(record.toString());
            p.getChildren().add(ta);
            Scene s = new Scene(p);
            stage.setScene(s);
            stage.show();
        }
        updateComboBox();
    }

    @FXML
    void updateBrandBA(ActionEvent event) {
        if(updateBrandTF.getText().equals("")) {
            return;
        }
        if(updateBrandNTF.getText().equals("")) {
            return;
        }



        NodeD<Brand> x = brands.findNode(new Brand(updateBrandTF.getText()));
        NodeD<Brand> y = brands.findNode(new Brand(updateBrandNTF.getText()));
        if (y != null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("The Brand IS Already Added !!");
            alert.show();
            return;
        }
        if (x == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("The Brand IS Already Added !!");
            alert.show();
        } else { // time O(n*m)

            Node<Car>curr = x.getData().getCars().getHead().getNext();
            while (curr!=null){
                curr.getData().setBrand(new Brand(updateBrandNTF.getText()));
                curr = curr.getNext();
            }
            x.getData().setBrand(updateBrandNTF.getText());

        }
        updateComboBox();
    }

    @FXML
    void AddCarBta(ActionEvent event) {
        if(CarmodelAddTF.getText().isEmpty() || CarYearTF.getText().isEmpty() || CarColorTF.getText().isEmpty() || CarPriceTF.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROE!");
            alert.setContentText("fill all TF's");
            alert.show();
            return;
        }


        NodeD<Brand> x= brands.findNode(new Brand(BrandCarCo.getValue().getBrand()));
        if (x!=null){
            if (!FileManagement.isValidN(CarYearTF.getText().trim())){
                return;
            }
            x.getData().getCars().Insertsorted(new Car(BrandCarCo.getValue().getBrand(),CarmodelAddTF.getText(),
                    Integer.parseInt(CarYearTF.getText().trim()),CarColorTF.getText(),CarPriceTF.getText()));
        }

    }

    @FXML
    void DeleteCarBta(ActionEvent event) {
        if (BrandDeleteCarCo.getValue()!=null||ModelDeleteCarCo.getValue()!=null||YearDeleteCarCo.getValue()!=null
                ||ColorDeleteCarCo.getValue()!=null||PriceDeleteCarCo.getValue()!=null) {
            Node<Car> prev = BrandDeleteCarCo.getValue().getCars().getHead();
            Node<Car> curr = BrandDeleteCarCo.getValue().getCars().getHead().getNext();
            while (curr!=null){
                if (curr.getData().getBrand().getBrand().equals(BrandDeleteCarCo.getValue().getBrand()) && curr.getData().getYear() == YearDeleteCarCo.getValue() && curr.getData().getModel().equals(ModelDeleteCarCo.getValue()+ "") && curr.getData().getColor().equals(ColorDeleteCarCo.getValue()+ "") && curr.getData().getPrice().equals(PriceDeleteCarCo.getValue() + "")){
                    prev.setNext(curr.getNext());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Done!");
                    alert.setContentText("The car is Deleted !!");
                    alert.show();
                    return;
                }
                prev = prev.getNext();
                curr = curr.getNext();
            }
        }
    }


    @FXML
    void readCustomerDataFileA(ActionEvent event) {
        FileManagement fileManagement = new FileManagement("");

        fileManagement.readCustomerOrders(brands);

        finishedOrders = fileManagement.getFinishedOrder();
        orderQueue = fileManagement.getOrderQueue();



    }


    Node<Car> change;

    @FXML
    void SearchCarBta(ActionEvent event) {
        try {

        if (BrandSearchCarCo.getValue()!=null||ModelSearchCarCo.getValue()!=null || YearSearchCarCo.getValue()!=null || colorSearchCA.getValue()!=null||priceSearchCA.getValue()!=null){
            BrandUpdateCarCo.setEditable(true);
            ModelUpdateCarCo.setEditable(true);
            YearUpdateCarCo.setEditable(true);
            ColorUpdateCarCo.setEditable(true);
            PriceUpdateCarCo.setEditable(true);
            UpdateCarBt.setDisable(false);
            BrandUpdateCarCo.setText(BrandSearchCarCo.getValue().getBrand());
            ModelUpdateCarCo.setText(ModelSearchCarCo.getValue());
            YearUpdateCarCo.setText(YearSearchCarCo.getValue() + "");
            ColorUpdateCarCo.setText(colorSearchCA.getValue());
            PriceUpdateCarCo.setText(priceSearchCA.getValue());

            SLinkedList<Car> cars = brands.findNode(new Brand(BrandSearchCarCo.getValue().getBrand())).getData().getCars();

            Node<Car> curr = cars.getHead().getNext();

            while (curr != null) {
                if(curr.getData().equals(new Car(BrandUpdateCarCo.getText(), ModelUpdateCarCo.getText(), Integer.parseInt(YearUpdateCarCo.getText()), ColorUpdateCarCo.getText(), PriceUpdateCarCo.getText()))) {
                    change = curr;
                    break;
                }
                curr = curr.getNext();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setContentText("Select a Valid Combos !!");
            alert.show();
        }
        }catch (Exception e){
            BrandUpdateCarCo.setEditable(false);
            ModelUpdateCarCo.setEditable(false);
            YearUpdateCarCo.setEditable(false);
            ColorUpdateCarCo.setEditable(false);
            PriceUpdateCarCo.setEditable(false);
            UpdateCarBt.setDisable(true);
            BrandUpdateCarCo.setText("");
            ModelUpdateCarCo.setText("");
            YearUpdateCarCo.setText("");
            ColorUpdateCarCo.setText("");
            PriceUpdateCarCo.setText("");
        }
    }

    @FXML
    void UpdateCarBta(ActionEvent event) {
        if(BrandUpdateCarCo.getText().equals("") || ModelUpdateCarCo.getText().equals("") ||YearUpdateCarCo.getText().equals("") ||ColorUpdateCarCo.getText().equals("") ||PriceUpdateCarCo.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROE!");
            alert.setContentText("Some of the TF's Are Empty !!");
            alert.show();
        } else {
            if (!BrandUpdateCarCo.getText().equals(change.getData().getBrand().getBrand()))
                change.getData().setBrand(new Brand(BrandUpdateCarCo.getText()));
            if (!ModelUpdateCarCo.getText().equals(change.getData().getModel()))
                change.getData().setModel(ModelUpdateCarCo.getText());
            if (!YearUpdateCarCo.getText().equals(change.getData().getYear() + ""))
                change.getData().setYear(Integer.parseInt(YearUpdateCarCo.getText()));
            if (!ColorUpdateCarCo.getText().equals(change.getData().getColor()))
                change.getData().setColor(ColorUpdateCarCo.getText());
            if (!PriceUpdateCarCo.getText().equals(change.getData().getPrice()))
                change.getData().setPrice(PriceUpdateCarCo.getText());

            BrandUpdateCarCo.setEditable(false);
            ModelUpdateCarCo.setEditable(false);
            YearUpdateCarCo.setEditable(false);
            ColorUpdateCarCo.setEditable(false);
            PriceUpdateCarCo.setEditable(false);
            UpdateCarBt.setDisable(true);
            BrandUpdateCarCo.setText("");
            ModelUpdateCarCo.setText("");
            YearUpdateCarCo.setText("");
            ColorUpdateCarCo.setText("");
            PriceUpdateCarCo.setText("");
        }
    }

    private void updateComboBox() {
        NodeD<Brand>curr = brands.getHead().getNext();
        BrandCarCo.getItems().clear();
        BrandDeleteCarCo.getItems().clear();
        BrandSearchCarCo.getItems().clear();
        while (curr!=null){
            BrandCarCo.getItems().add(curr.getData());
            curr=curr.getNext();
        }
        BrandDeleteCarCo.setItems(BrandCarCo.getItems());
        BrandSearchCarCo.setItems(BrandCarCo.getItems());
        Ubrand.setItems(BrandCarCo.getItems());
    }
    private void updateModelComboBox(ComboBox<String> model, Brand brand) {
        Node<Car> curr = brand.getCars().getHead().getNext();
        while (curr != null) {
            if(model.getItems().contains(curr.getData().getModel())){
                curr = curr.getNext();
                continue;
            }
            model.getItems().add(curr.getData().getModel());
            curr = curr.getNext();
        }
    }
    private void updateYearComboBox(ComboBox<Integer> model, Brand brand, String modelCar) {
        Node<Car> curr = brand.getCars().getHead().getNext();
        while (curr != null) {
            if(model.getItems().contains(curr.getData().getYear())){
                curr = curr.getNext();
                continue;
            }
            if(!curr.getData().getModel().equals(modelCar)) {
                curr = curr.getNext();
                continue;
            }
            model.getItems().add(curr.getData().getYear());
            curr = curr.getNext();
        }
    }
    private void updateColorComboBox(ComboBox<String> model, Brand brand, String modelCar, int year) {
        Node<Car> curr = brand.getCars().getHead().getNext();

        while (curr != null) {

            if(model.getItems().contains(curr.getData().getColor())){
                curr = curr.getNext();

                continue;
            }

            if(!curr.getData().getModel().equals(modelCar)) {

                curr = curr.getNext();
                continue;
            }
            if (curr.getData().getYear()!=year){
                curr = curr.getNext();
                continue;
            }

            model.getItems().add(curr.getData().getColor());
            curr = curr.getNext();
        }
    }
    private void updatePriceComboBox(ComboBox<String> model, Brand brand, String modelCar, int year,String color) {
        Node<Car> curr = brand.getCars().getHead().getNext();

        while (curr != null) {

            if(model.getItems().contains(curr.getData().getPrice())){
                curr = curr.getNext();

                continue;
            }

            if(!curr.getData().getModel().equals(modelCar)) {

                curr = curr.getNext();
                continue;
            }
            if (curr.getData().getYear()!=year){
                curr = curr.getNext();
                continue;
            }
            if (!curr.getData().getColor().equals(color)){
                curr = curr.getNext();
                continue;
            }

            model.getItems().add(curr.getData().getPrice());
            curr = curr.getNext();
        }
    }
    public void BrandDeleteCarA(ActionEvent actionEvent) {
        if(BrandDeleteCarCo.getValue() != null) {
            ModelDeleteCarCo.getItems().clear();
            updateModelComboBox(ModelDeleteCarCo, BrandDeleteCarCo.getValue());
        }
    }
    public void ModelDeleteCarA(ActionEvent actionEvent) {
        if(ModelDeleteCarCo.getValue() != null) {
            YearDeleteCarCo.getItems().clear();
            updateYearComboBox(YearDeleteCarCo, BrandDeleteCarCo.getValue(), ModelDeleteCarCo.getValue());
        }
    }
    public void ColorDeleteCarA(ActionEvent actionEvent) {
        try {
            PriceDeleteCarCo.getItems().clear();
            updatePriceComboBox(PriceDeleteCarCo, BrandDeleteCarCo.getValue(), ModelDeleteCarCo.getValue(),YearDeleteCarCo.getValue(),ColorDeleteCarCo.getValue());
        } catch (Exception e) {

        }
    }
    public void YearDeleteCarA(ActionEvent actionEvent) {
        try {
            ColorDeleteCarCo.getItems().clear();
            updateColorComboBox(ColorDeleteCarCo, BrandDeleteCarCo.getValue(), ModelDeleteCarCo.getValue(),YearDeleteCarCo.getValue());
        } catch (Exception e) {

        }
    }
    public void BrandSearchCarA(ActionEvent actionEvent) {
        if(BrandSearchCarCo.getValue() != null) {
            ModelSearchCarCo.getItems().clear();
            updateModelComboBox(ModelSearchCarCo, BrandSearchCarCo.getValue());
        }
    }
    public void ModelSearchCarA(ActionEvent actionEvent) {
        if(ModelSearchCarCo.getValue() != null) {
            YearSearchCarCo.getItems().clear();
            updateYearComboBox(YearSearchCarCo, BrandSearchCarCo.getValue(), ModelSearchCarCo.getValue());
        }
    }
    public void YearSearchCarA(ActionEvent actionEvent) {
        try {
            colorSearchCA.getItems().clear();
            updateColorComboBox(colorSearchCA, BrandSearchCarCo.getValue(), ModelSearchCarCo.getValue(),YearSearchCarCo.getValue());
        } catch (Exception e) {

        }
    }
    public void ColorSearchCarA(ActionEvent actionEvent) {
        try {
            priceSearchCA.getItems().clear();
            updatePriceComboBox(priceSearchCA, BrandSearchCarCo.getValue(), ModelSearchCarCo.getValue(),YearSearchCarCo.getValue(),colorSearchCA.getValue());
        } catch (Exception e) {

        }
    }
    public void PriceSearchCarA(ActionEvent actionEvent) {}
    public void PriceDeleteCarA(ActionEvent actionEvent) {}

    Order currOrder;

    public void proccessBA(ActionEvent actionEvent) {

        System.out.println(currOrder.getCar().getBrand().getBrand());
        SLinkedList<Car> b = brands.findNode(new Brand(currOrder.getCar().getBrand().getBrand().trim())).getData().getCars();

        if (finishedOrders == null) {
            finishedOrders = new StackCa<>(999);
        }

        Node<Car>curr=b.getHead().getNext();

        Node<Car> prev = b.getHead();
        boolean flag = false;
        while (curr!=null){
            if (curr.getData().getBrand().getBrand().equals(currOrder.getCar().getBrand().getBrand().trim().trim()) && curr.getData().getYear() == currOrder.getCar().getYear() && curr.getData().getModel().equals(currOrder.getCar().getModel()+ "") && curr.getData().getColor().equals(currOrder.getCar().getColor()+ "") && curr.getData().getPrice().equals(currOrder.getCar().getPrice() + "")){
                        currOrder.setStatus("Finished");
                        prev.setNext(curr.getNext());

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Done!");
                        alert.setContentText("The car is Sold !!");
                        alert.show();
                        flag=true;
                       break;
                    }
                    prev = prev.getNext();
                    curr = curr.getNext();
        }
        if(!flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROE!");
            alert.setContentText("The car is Not Found !!");
            alert.show();
            proccessBA.setDisable(true);
        }else {
            finishedOrders.puch(currOrder);
            nextB.setDisable(false);
            enqueueBA.setDisable(true);
            proccessBA.setDisable(true);
            cancelBA.setDisable(true);
            CustomerNameATF.clear();
            CustomerPhoneATTF.clear();
            carBrandATF.clear();
            carModelATF.clear();
            carYearATF.clear();
            carPriceATF1.clear();
            carColorATF.clear();
        }


    }

    public void cancelBA(ActionEvent actionEvent) {
        currOrder=null;
        nextB.setDisable(false);
        enqueueBA.setDisable(true);
        proccessBA.setDisable(true);
        cancelBA.setDisable(true);
        CustomerNameATF.clear();
        CustomerPhoneATTF.clear();
        carBrandATF.clear();
        carModelATF.clear();
        carYearATF.clear();
        carPriceATF1.clear();
        carColorATF.clear();
    }

    public void enqueueBA(ActionEvent actionEvent) {
        orderQueue.enqueue(currOrder);
        nextB.setDisable(false);
        enqueueBA.setDisable(true);
        proccessBA.setDisable(true);
        cancelBA.setDisable(true);
        CustomerNameATF.clear();
        CustomerPhoneATTF.clear();
        carBrandATF.clear();
        carModelATF.clear();
        carYearATF.clear();
        carPriceATF1.clear();
        carColorATF.clear();
    }

    public void nextBA(ActionEvent actionEvent) {
        try {


        System.out.println(orderQueue.isEmpty());
        if (orderQueue.isEmpty()){
            System.out.println("here");
            currOrder = orderQueue.dequeue();
            nextB.setDisable(true);
            CustomerNameATF.setText(currOrder.getCustomerName());
            CustomerPhoneATTF.setText(currOrder.getCustomerPhone());
            carBrandATF.setText(currOrder.getCar().getBrand().getBrand());
            carModelATF.setText(currOrder.getCar().getModel());
            carYearATF.setText(currOrder.getCar().getYear()+"");
            carPriceATF1.setText(currOrder.getCar().getPrice());
            carColorATF.setText(currOrder.getCar().getColor());
            enqueueBA.setDisable(false);
            proccessBA.setDisable(false);
            cancelBA.setDisable(false);
        }
        }catch (Exception e){

        }
    }

    public void showAllBA(ActionEvent actionEvent) {
        // finishedOrders
        textArea.clear();
        StackCa<Order> temp = new StackCa<>(500);
        StringBuilder txt = new StringBuilder();
        try {


            while (!finishedOrders.isEmpty()) {
                temp.puch(finishedOrders.peek());
                txt.append(finishedOrders.peek()).append("\n");
                finishedOrders.pop();
            }

            while (!temp.isEmpty()) {
                finishedOrders.puch(temp.peek());
                temp.pop();
            }
        }catch (Exception e){

        }

        textArea.setText(txt.toString());

    }

    public void lastTenBA(ActionEvent actionEvent) {
        textArea.clear();
        StackCa<Order> temp = new StackCa<>(500);
        StringBuilder txt = new StringBuilder("");
        try {
            for (int i = 0;i<10;i++){
                if (!finishedOrders.isEmpty()){
                    temp.puch(finishedOrders.pop());
                    txt.append(temp.peek()).append("\n");
                }
            }
            for (int i = 0;i<10;i++){
                if (!temp.isEmpty()){
                    finishedOrders.puch(temp.pop());

                }
            }

        }catch (Exception e){

        }

        textArea.setText(txt.toString());

    }

    public void Ubrand(ActionEvent actionEvent) {
        System.out.println("test");
        if(Ubrand.getValue() != null) {
            Umodel.getItems().clear();
            updateModelComboBox(Umodel, Ubrand.getValue());
        }
    }

    public void Ucolor(ActionEvent actionEvent) {

    }

    public void Umodel(ActionEvent actionEvent) {
        if(Umodel.getValue() != null) {
            Uyear.getItems().clear();
            updateYearComboBox(Uyear, Ubrand.getValue(), Umodel.getValue());
        }
    }

    public void Uyear(ActionEvent actionEvent) {
        try {
            if (Uyear.getValue()!=null){
                Ucolor.getItems().clear();
                updateColorComboBox(Ucolor,Ubrand.getValue(),Umodel.getValue(),Uyear.getValue());
            }
        }catch (Exception e){

        }
    }

    SLinkedList<Car> list = new SLinkedList<>();

    public void Usearch(ActionEvent actionEvent) {
        list = new SLinkedList<>();
        if (Ubrand.getValue()==null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Please Select one Of Brand");
            alert.show();
        }

        if (Ubrand.getValue()!=null) {
            Uresults.getItems().clear();
            SLinkedList<Car> originalList = brands.findNode(new Brand(Ubrand.getValue().getBrand())).getData().getCars();
            Node<Car> curr = originalList.getHead().getNext();
            while (curr != null) {
                list.Insertsorted(curr.getData());
                curr = curr.getNext();
            }
            Ubrand.setDisable(true);
            Umodel.setDisable(true);
            Uyear.setDisable(true);
            Ucolor.setDisable(true);
            UClear.setDisable(false);
        }

        if (Umodel.getValue()!=null){
            Node<Car> curr = list.getHead().getNext();
            while (curr!=null){
                if(!curr.getData().getModel().equals(Umodel.getValue()))
                    list.Delete(curr.getData());
                curr=curr.getNext();
            }
        }
        if (Uyear.getValue()!=null){
            Node<Car> curr = list.getHead().getNext();
            while (curr!=null){
                if(curr.getData().getYear() != Uyear.getValue())
                    list.Delete(curr.getData());
                curr=curr.getNext();
            }
        }
        if (Ucolor.getValue()!=null){
            Node<Car> curr = list.getHead().getNext();
            while (curr!=null){
                if(!curr.getData().getColor().equals(Ucolor.getValue()))
                    list.Delete(curr.getData());
                curr=curr.getNext();
            }
        }

        Node<Car> curr = list.getHead().getNext();
        while (curr!=null){
            Uresults.getItems().add(curr.getData());
            curr=curr.getNext();
        }
    }

    public void Uresults(ActionEvent actionEvent) {

    }


    public void UClear(ActionEvent actionEvent) {
        Ubrand.setDisable(false);
        Umodel.setDisable(false);
        Uyear.setDisable(false);
        Ucolor.setDisable(false);
        UClear.setDisable(true);

        Ubrand.getSelectionModel().clearSelection();
        Umodel.getSelectionModel().clearSelection();
        Uyear.getSelectionModel().clearSelection();
        Ucolor.getSelectionModel().clearSelection();
        Uresults.getItems().clear();
    }

    public void buyBA(ActionEvent actionEvent) {

        if(orderQueue == null) {
            orderQueue = new Queue<>(999);
        }

        if(Uresults.getValue() == null || Uname.getText().isEmpty() || Uphone.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROE!");
            alert.setContentText("Some of the fields are empty");
            alert.show();
            return;
        }

        orderQueue.enqueue(new Order(Uname.getText(), Uphone.getText(), Uresults.getValue(), "InProcess", LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"))));
        UClear(new ActionEvent());
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setContentText("An order has been added");
        alert.show();
    }

    public void saveCars(ActionEvent actionEvent) {
        FileManagement fileManagement = new FileManagement("");

        if(brands == null)
            return;

        fileManagement.saveCarsOnfile(brands);
    }

    public void saveOrders(ActionEvent actionEvent) {

        if(finishedOrders == null || orderQueue == null) {
            return;
        }
        FileManagement fileManagement = new FileManagement("");
        fileManagement.saveCarsOnfile(finishedOrders, orderQueue);


    }
}