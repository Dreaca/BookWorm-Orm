package org.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.SaveBranchBo;
import org.example.dto.BranchDto;

public class AddBranchController {
    public Label txtBranchId;
    public TextField txtLocation;
    public TextField txtNoBooks;
    public TextField txtBranchMan;
    public JFXButton btnSave;

    private SaveBranchBo bo = (SaveBranchBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.SAVE_BRANCH);

    public void saveOnAction(ActionEvent actionEvent) {
        String location = txtLocation.getText();
        int bookNo = Integer.parseInt(txtNoBooks.getText());
        String branchMan= txtBranchMan.getText();

        boolean b = bo.saveBranch(new BranchDto(location,bookNo,branchMan));
        if (b) {
            Stage stage = (Stage) txtBranchMan.getScene().getWindow();
            stage.close();
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtBranchId.getScene().getWindow();
        stage.close();
    }
    public void initialize(){
    }
}
