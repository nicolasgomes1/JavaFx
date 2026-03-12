
package com.qaflow.desktopdemo.controller;

import com.qaflow.desktopdemo.model.ToDo;
import com.qaflow.desktopdemo.repository.ToDoRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ToDoController {

    @FXML
    private TableView<ToDo> todoTable;
    @FXML
    private TableColumn<ToDo, Number> idColumn;
    @FXML
    private TableColumn<ToDo, String> titleColumn;
    @FXML
    private TableColumn<ToDo, String> descriptionColumn;
    @FXML
    private TableColumn<ToDo, Boolean> completedColumn;

    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private CheckBox completedCheckBox;

    private final ToDoRepository repository = new ToDoRepository();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cell ->
                new SimpleLongProperty(cell.getValue().getId() == null ? 0 : cell.getValue().getId()));
        titleColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getTitle()));
        descriptionColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getDescription()));
        completedColumn.setCellValueFactory(cell ->
                new SimpleBooleanProperty(cell.getValue().isCompleted()));

        todoTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                titleField.setText(selected.getTitle());
                descriptionArea.setText(selected.getDescription());
                completedCheckBox.setSelected(selected.isCompleted());
            }
        });

        loadTodos();
    }

    @FXML
    private void onAdd() {
        if (titleField.getText() == null || titleField.getText().isBlank()) {
            showAlert("Validation Error", "Title is required.");
            return;
        }

        ToDo toDo = new ToDo(
                titleField.getText().trim(),
                descriptionArea.getText(),
                completedCheckBox.isSelected()
        );

        repository.save(toDo);
        clearForm();
        loadTodos();
    }

    @FXML
    private void onUpdate() {
        ToDo selected = todoTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Error", "Please select a ToDo to update.");
            return;
        }

        if (titleField.getText() == null || titleField.getText().isBlank()) {
            showAlert("Validation Error", "Title is required.");
            return;
        }

        selected.setTitle(titleField.getText().trim());
        selected.setDescription(descriptionArea.getText());
        selected.setCompleted(completedCheckBox.isSelected());

        repository.update(selected);
        clearForm();
        loadTodos();
    }

    @FXML
    private void onDelete() {
        ToDo selected = todoTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Selection Error", "Please select a ToDo to delete.");
            return;
        }

        repository.delete(selected);
        clearForm();
        loadTodos();
    }

    @FXML
    private void onClear() {
        clearForm();
    }

    private void loadTodos() {
        todoTable.setItems(FXCollections.observableArrayList(repository.findAll()));
    }

    private void clearForm() {
        todoTable.getSelectionModel().clearSelection();
        titleField.clear();
        descriptionArea.clear();
        completedCheckBox.setSelected(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}