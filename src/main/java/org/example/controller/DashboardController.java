package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.AdminDashBo;
import org.example.dto.LogDto;
import org.example.dto.UserDto;
import org.example.entity.Branch;
import org.example.entity.User;
import org.example.model.BranchTM;
import org.example.model.LogTm;
import org.example.model.UserTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DashboardController {

    //BranchTable
    public TableView branchTable;
    public TableColumn branchesColBranchId;
    public TableColumn branchesColLocation;
    public TableColumn branchesColNoOfBooks;
    public TableColumn branchesColBM;
    public TableColumn branchesMod;
    public TableColumn branchesColBooks;

    //UserTable
    public TableView userTable;
    public TableColumn UserColUID;
    public TableColumn UserColUname;
    public TableColumn userColName;
    public TableColumn userColEmail;
    public TableColumn userColMod;
    public TableColumn userColDet;

    //LogBook
    public TableColumn LogTID;
    public TableColumn logColBookName;
    public TableColumn logColUserName;
    public TableColumn logBorrowDate;
    public TableColumn logColRetDate;
    public TableColumn logColStatus;
    public TableColumn logColOptions;
    public TableView logTable;

    //PROFILE PANE
    public JFXButton btnProfCancel;
    public JFXButton editDone;
    public TextField changePass;
    public TextField changeEmail;
    public TextField changeName;
    public TextField changeUName;
    public Label txtPassWord;
    public Label txtEmail;
    public Label txtName;
    public Label txtUName;
    public Label txtUID;
    public Label txtUserName;
    public AnchorPane root;

    AdminDashBo bo = (AdminDashBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ADMIN);

    public void addNewBranchOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddBranch.fxml"));
        Object load = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
        loadBranches();
    }

    public void initialize(){
        setCellValueFactory();
        loadBranches();
        loadUsers();
        loadLogs();
    }

    private void loadLogs() {
        ObservableList<LogTm> oblist = FXCollections.observableArrayList();
        List<LogDto> list = bo.getAllLogs();
        for (LogDto d : list){
            oblist.add(
                    new LogTm(
                            d.getTid(),
                            d.getBookName(),
                            d.getUserName(),
                            d.getBorrwedDate(),
                            d.getReturnedDate(),
                            d.isStatus(),
                            getNewDetButton()
                    )
            );
            logTable.setItems(oblist);
            logTable.refresh();
            for (int i = 0; i < oblist.size(); i++) {
                int finalI = i;
                int finalI2 = i;
                int finalI3 = i;
                int finalI4 = i;
                oblist.get(i).getMod().setOnAction(actionEvent -> {
                    try {
                        JFXButton bt = oblist.get(finalI).getMod();
                        double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                        double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();

                        ContextMenu con =  loadlogPopup(oblist.get(finalI).getMod());

                        con.getItems().get(0).setOnAction(actionEvent1 -> {
                            String tid = oblist.get(finalI2).getTid();
                            bo.updateTransaction(tid);
                            loadLogs();
                        });
                        con.getItems().get(1).setOnAction(actionEvent1 -> {

                        });
                        con.show(bt, x, y);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
    public ContextMenu loadlogPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Returned Book");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);

        return con;
    }

    public void setCellValueFactory(){
        //branches
        branchesColBranchId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchesColLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        branchesColBM.setCellValueFactory(new PropertyValueFactory<>("branchMan"));
        branchesColNoOfBooks.setCellValueFactory(new PropertyValueFactory<>("bookNo"));
        branchesMod.setCellValueFactory(new PropertyValueFactory<>("modButton"));
        branchesColBooks.setCellValueFactory(new PropertyValueFactory<>("booksButton"));

        //users

        userColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userColMod.setCellValueFactory(new PropertyValueFactory<>("modButton"));
        UserColUID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UserColUname.setCellValueFactory(new PropertyValueFactory<>("userName"));

        //logs
        LogTID.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        logColBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        logColUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        logBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrwedDate"));
        logColRetDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        logColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        logColOptions.setCellValueFactory(new PropertyValueFactory<>("mod"));
    }

    public void loadBranches(){
        ObservableList<BranchTM> oblist = FXCollections.observableArrayList();
       List<Branch> list = bo.getAllBranches();
       for (Branch d : list){
            oblist.add(
                    new BranchTM(
                    d.getBranchId(),
                    d.getLocation(),
                    d.getBranchManager(),
                    d.getNoOfBooks(),
                    getNewModButton(),
                    getNewBookButton()
                    )
            );
            branchTable.setItems(oblist);
            branchTable.refresh();
       }
        for (int i = 0; i < oblist.size(); i++) {
            int finalI1 = i;
            oblist.get(i).getBooksButton().setOnAction(actionEvent -> {
                try {
                    loadBranchBooks(oblist.get(finalI1).getBranchId(),oblist.get(finalI1).getLocation());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            int finalI = i;
            int finalI2 = i;
            int finalI3 = i;
            int finalI4 = i;
            oblist.get(i).getModButton().setOnAction(actionEvent -> {
                try {
                    JFXButton bt = oblist.get(finalI).getModButton();
                    double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                    double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();

                    ContextMenu con =  loadPopup(oblist.get(finalI).getModButton());

                    con.getItems().get(0).setOnAction(actionEvent1 -> {
                        try {
                            openUpdateWindow(oblist.get(finalI2).getBranchId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    con.getItems().get(1).setOnAction(actionEvent1 -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Are you sure to delete " + oblist.get(finalI3).getLocation() + "?");
                        alert.setContentText("Choose your option.");

                        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                        Optional<ButtonType> result = alert.showAndWait();
                        result.ifPresent(response -> {
                            if (response == buttonTypeYes) {
                                bo.deleteBranch(oblist.get(finalI4).getBranchId());
                                loadBranches();
                            } else {
                                alert.close();
                            }
                        });
                    });
                    con.show(bt, x, y);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void loadBranchBooks(String branchId, String location) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/books.fxml"));
        Object load = loader.load();
        BookFormController controller = loader.getController();
        controller.setBranchNameId(branchId,location);
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        stage.show();
    }

    private void openUpdateWindow(String branchId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddBranch.fxml"));
        Object load = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) load);
        stage.setScene(scene);
        AddBranchController controller = loader.getController();
        controller.txtBranchId.setText(branchId);
        stage.show();
    }

    private JFXButton getNewBookButton() {
        return new JFXButton("books");
    }

    private JFXButton getNewModButton() {
        return new JFXButton("modify");
    }

   void loadUsers(){
       ObservableList<UserTm> oblist = FXCollections.observableArrayList();
       List<User> list = bo.getAllUsers();
       for (User d : list){
           oblist.add(
                   new UserTm(
                           d.getUserId(),
                           d.getUserName(),
                           d.getName(),
                           d.getEmail(),
                           getNewModifyButton()
                   )
           );

           userTable.setItems(oblist);
           userTable.refresh();
       }
       for (int i = 0; i < oblist.size(); i++) {
           int finalI = i;
           int finalI2 = i;
           int finalI3 = i;
           int finalI4 = i;
           oblist.get(i).getModButton().setOnAction(actionEvent -> {
               oblist.get(finalI).getModButton().setOnAction(actionEvent1 -> {
                    String userId = oblist.get(finalI2).getUserId();
                    bo.deleteUser(userId);
                    loadUsers();
                });

           });
       }
   }

    private JFXButton getNewModifyButton() {
        return new JFXButton("delete user");
    }

    private JFXButton getNewDetButton() {
        return new JFXButton("details");
    }
    public ContextMenu loadPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Update");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);

        return con;
    }

    public void reloadOnAction(ActionEvent actionEvent) {
        loadBranches();
    }

    public void editOnAction(ActionEvent actionEvent) {

        changeEmail.setVisible(true);
        changeName.setVisible(true);
        changeUName.setVisible(true);
        changePass.setVisible(true);
        btnProfCancel.setVisible(true);
        editDone.setVisible(true);

        changeEmail.setText(txtEmail.getText());
        changeName.setText(txtName.getText());
        changeUName.setText(txtUName.getText());
        changePass.setText(txtPassWord.getText());
    }

    public void updateUser(ActionEvent actionEvent) {
        bo.updateUser(new UserDto(txtUID.getText(),
                changeUName.getText(),
                changeName.getText(),
                changeEmail.getText(),
                changePass.getText()
        ));
        setUp(changeUName.getText());
        changeEmail.setVisible(false);
        changeName.setVisible(false);
        changeUName.setVisible(false);
        changePass.setVisible(false);
        btnProfCancel.setVisible(false);
        editDone.setVisible(false);

    }

    public void logoutOnAction(ActionEvent actionEvent) {
        Stage window = (Stage) root.getScene().getWindow();
        window.close();
    }

    public void cancelUpdate(ActionEvent actionEvent) {
        changeEmail.setVisible(false);
        changeName.setVisible(false);
        changeUName.setVisible(false);
        changePass.setVisible(false);
        btnProfCancel.setVisible(false);
        editDone.setVisible(false);
    }
    public void setUp(String userName){
        UserDto user = bo.getUser(userName);
        txtUID.setText(user.getUserId());
        txtUserName.setText(user.getUserName());
        txtName.setText(user.getName());
        txtPassWord.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
        txtUName.setText(user.getUserName());
        loadLogs();
    }

    public void addNewAdminOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/AdminSignUp.fxml"));
        Object load = loader.load();
        Scene scene = new Scene((Parent) load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void checkOverdueOnAction(ActionEvent actionEvent) {
        ObservableList<LogTm> oblist = FXCollections.observableArrayList();
        List<LogDto> list = bo.getOverDueList();
        for (LogDto d : list) {
            oblist.add(
                    new LogTm(
                            d.getTid(),
                            d.getBookName(),
                            d.getUserName(),
                            d.getBorrwedDate(),
                            d.getReturnedDate(),
                            d.isStatus(),
                            getNewDetButton()
                    )
            );
        }
        logTable.setItems(oblist);
        logTable.refresh();
        for (int i = 0; i < oblist.size(); i++) {
            int finalI = i;
            int finalI2 = i;
            oblist.get(i).getMod().setOnAction(event -> {
                try {
                    JFXButton bt = oblist.get(finalI).getMod();
                    double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                    double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();

                    ContextMenu con =  loadlogPopup(oblist.get(finalI).getMod());

                    con.getItems().get(0).setOnAction(actionEvent1 -> {
                        String tid = oblist.get(finalI2).getTid();
                        bo.updateTransaction(tid);
                        checkOverdueOnAction(event);
                    });
                    con.getItems().get(1).setOnAction(actionEvent1 -> {

                    });
                    con.show(bt, x, y);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void overDueUsersOnAction(ActionEvent actionEvent) {
        ObservableList<UserTm> oblist = FXCollections.observableArrayList();
        List<UserDto> list = bo.getOverDueUsers();
        for (UserDto d : list){
            oblist.add(
                    new UserTm(
                            d.getUserId(),
                            d.getUserName(),
                            d.getName(),
                            d.getEmail(),
                            getNewModifyButton()
                    )
            );

            userTable.setItems(oblist);
            userTable.refresh();
        }
//        for (int i = 0; i < oblist.size(); i++) {
//            int finalI = i;
//            int finalI2 = i;
//            int finalI3 = i;
//            int finalI4 = i;
//            oblist.get(i).getModButton().setOnAction(Event -> {
//                oblist.get(finalI).getModButton().setOnAction(actionEvent1 -> {
//                    String userId = oblist.get(finalI2).getUserId();
//                    bo.deleteUser(userId);
//                    loadUsers();
//                });
//
//            });
//        }
    }
}
