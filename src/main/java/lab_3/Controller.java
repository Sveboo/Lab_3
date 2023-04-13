package lab_3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Controller {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField patronymic;

    @FXML
    private DatePicker dataOfBirth;

    private boolean isValidData(){
        try{
            LocalDate begin = dataOfBirth.getValue();
            return name.getText().matches("[а-яА-Я]+") && surname.getText().matches("[а-яА-Я]+") &&
                    patronymic.getText().matches("[а-яА-Я]+");
        }
        catch (Exception e){
            return false;
        }
    }

    private String getSex(String patronymic){
        switch (Character.toLowerCase(patronymic.charAt(patronymic.length() - 1))){
            case 'а' -> {
                return "Ж";
            }
            case 'ч' -> {
                return "М";
            }

            default -> {
                return "-";
            }
        }
    }

    private String getInitials(String name, String patronymic){
        return Character.toUpperCase(name.charAt(0)) + "." + Character.toUpperCase(patronymic.charAt(0)) + ".";
    }

    private String getSurname(String surname){
        return Character.toUpperCase(surname.charAt(0)) + surname.substring(1).toLowerCase();
    }

    private String getYears(LocalDate begin){
        LocalDate end = LocalDate.now();
        int years = (int) ChronoUnit.YEARS.between(begin, end);
        int lastDigit = years % 10;

        if (lastDigit == 1 && years != 11){
            return years + " год";
        } else if (lastDigit > 1 && lastDigit < 5 && (years % 100 < 10 || years % 100 >= 20)) {
            return years + " года";
        }else{
            return years + " лет";
        }
    }

    private void setNormalStyle(){
        String normalStyle = "-fx-border-color: #A9A9A9;";
        name.setStyle(normalStyle);
        surname.setStyle(normalStyle);
        patronymic.setStyle(normalStyle);
        dataOfBirth.setStyle(normalStyle);
    }

    private void setErrorStyle() {
        String errorStyle = "-fx-border-color: RED;";
        name.setStyle(errorStyle);
        surname.setStyle(errorStyle);
        patronymic.setStyle(errorStyle);
        dataOfBirth.setStyle(errorStyle);
    }

    public void printUser(){
        if (isValidData()){
            setNormalStyle();
            String userName = name.getText();
            String userSurname = surname.getText();
            String userPatronymic = patronymic.getText();
            LocalDate begin = dataOfBirth.getValue();
            Alert message = new Alert(Alert.AlertType.NONE);
            String userInfo = getSurname(userSurname) + " " + getInitials(userName, userPatronymic) +
                    " " + getSex(userPatronymic) + " " + getYears(begin);
            message.setContentText(userInfo);
            message.getDialogPane().getButtonTypes().add(ButtonType.OK);
            message.showAndWait();
        }
        else{
            setErrorStyle();
        }
    }
}