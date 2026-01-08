package app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.*;
import services.*;
import utils.CSSHelper;

public class FoodDeliveryGUI extends Application {

    private UserService userService = new UserService();
    private MenuService menuService = new MenuService();
    private OrderService orderService = new OrderService();

    private int loggedInUserId = -1; // -1 means no user logged in

    @Override
    public void start(Stage stage) {
        initDemoData();

        VBox root = new VBox(15);
        root.getStyleClass().add("vbox");

        Label title = new Label("🍕 Food Delivery System");
        title.getStyleClass().add("title-label");

        Button signupBtn = new Button("Signup");
        Button loginBtn = new Button("Login");
        Button viewMenuBtn = new Button("View Menu");
        Button placeOrderBtn = new Button("Place Order");

        CSSHelper.styleButton(signupBtn);
        CSSHelper.styleButton(loginBtn);
        CSSHelper.styleButton(viewMenuBtn);
        CSSHelper.styleButton(placeOrderBtn);

        signupBtn.setOnAction(e -> signupGUI());
        loginBtn.setOnAction(e -> loginGUI());
        viewMenuBtn.setOnAction(e -> showMenuTableView());
        placeOrderBtn.setOnAction(e -> placeOrderGUI());

        root.getChildren().addAll(title, signupBtn, loginBtn, viewMenuBtn, placeOrderBtn);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(new File("styles/app.css").toURI().toString());

        stage.setTitle("Food Delivery GUI");
        stage.setScene(scene);
        stage.show();
    }

    private void initDemoData() {
        userService.signup(new User(101, "Ali", "03001234567"));
        userService.signup(new User(102, "Sara", "03007654321"));

        menuService.addFood(new FoodItem(1, "Burger", 250));
        menuService.addFood(new FoodItem(2, "Pizza", 500));
        menuService.addFood(new FoodItem(3, "Sandwich", 150));
        menuService.addFood(new FoodItem(4, "Fries", 120));
    }

    private void signupGUI() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Signup");
        dialog.setHeaderText("Enter user details as: ID,Name,Contact");

        dialog.showAndWait().ifPresent(input -> {
            try {
                String[] parts = input.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String contact = parts[2].trim();
                userService.signup(new User(id, name, contact));
                showAlert("User signed up successfully!");
            } catch (Exception e) {
                showAlert("Invalid input format!");
            }
        });
    }

    private void loginGUI() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Login");
        dialog.setHeaderText("Enter User ID:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int id = Integer.parseInt(input.trim());
                User user = userService.login(id);
                if (user != null) {
                    loggedInUserId = user.getUserId();
                    showAlert("Login successful!");
                } else {
                    showAlert("User not found!");
                }
            } catch (Exception e) {
                showAlert("Invalid ID!");
            }
        });
    }

    private void showMenuTableView() {
        Stage menuStage = new Stage();
        menuStage.setTitle("🍔 Food Menu");

        TableView<FoodItem> table = new TableView<>();

        TableColumn<FoodItem, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("itemId"));

        TableColumn<FoodItem, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<FoodItem, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(idCol, nameCol, priceCol);
        table.setItems(FXCollections.observableArrayList(menuService.getAllItems()));
        table.setPrefHeight(300);

        VBox root = new VBox(10, table);
        root.getStyleClass().add("vbox");

        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add(new File("styles/app.css").toURI().toString());

        menuStage.setScene(scene);
        menuStage.show();
    }

    private void placeOrderGUI() {
        if (loggedInUserId == -1) {
            showAlert("Please login first!");
            return;
        }

        Stage orderStage = new Stage();
        orderStage.setTitle("🛒 Place Order");

        TableView<OrderRow> table = new TableView<>();

        TableColumn<OrderRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<OrderRow, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<OrderRow, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderRow, Button> plusCol = new TableColumn<>("+");
        plusCol.setCellValueFactory(new PropertyValueFactory<>("plusBtn"));

        TableColumn<OrderRow, Button> minusCol = new TableColumn<>("-");
        minusCol.setCellValueFactory(new PropertyValueFactory<>("minusBtn"));

        ObservableList<OrderRow> rows = FXCollections.observableArrayList();
        for (FoodItem item : menuService.getAllItems()) {
            rows.add(new OrderRow(item));
        }

        table.setItems(rows);
        table.getColumns().addAll(nameCol, priceCol, qtyCol, plusCol, minusCol);

        CheckBox expressChk = new CheckBox("🚀 Express Delivery");

        Button placeBtn = new Button("Place Order");
        CSSHelper.styleButton(placeBtn);

        placeBtn.setOnAction(e -> {
            List<Order.OrderItem> orderItems = new ArrayList<>();
            double total = 0;

            for (OrderRow row : rows) {
                if (row.getQuantity() > 0) {
                    orderItems.add(new Order.OrderItem(row.getName(), row.getQuantity(), row.getPrice()));
                    total += row.getQuantity() * row.getPrice();
                }
            }

            if (orderItems.isEmpty()) {
                showAlert("No items selected!");
                return;
            }

            Order order = new Order(generateOrderId(), loggedInUserId, orderItems, total, expressChk.isSelected());
            orderService.placeOrder(order);

            showAlert("Order placed successfully!");
            rows.forEach(r -> r.setQuantity(0));
            table.refresh();
        });

        VBox root = new VBox(12, table, expressChk, placeBtn);
        root.getStyleClass().add("vbox");
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(new File("styles/app.css").toURI().toString());

        orderStage.setScene(scene);
        orderStage.show();
    }

    private int generateOrderId() {
        return (int) (Math.random() * 10000);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // ---------------- OrderRow ----------------
    public static class OrderRow {
        private String name;
        private double price;
        private int quantity;
        private Button plusBtn = new Button("+");
        private Button minusBtn = new Button("-");

        public OrderRow(FoodItem item) {
            name = item.getName();
            price = item.getPrice();
            quantity = 0;

            plusBtn.setOnAction(e -> quantity++);
            minusBtn.setOnAction(e -> { if (quantity > 0) quantity--; });
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int q) { quantity = q; }
        public Button getPlusBtn() { return plusBtn; }
        public Button getMinusBtn() { return minusBtn; }
    }
}
