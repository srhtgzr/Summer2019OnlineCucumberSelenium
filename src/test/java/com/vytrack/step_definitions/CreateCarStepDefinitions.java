package com.vytrack.step_definitions;

import com.vytrack.pages.CreateCarPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class CreateCarStepDefinitions {

    CreateCarPage createCarPage;
    VehiclesPage vehiclesPage;

    public CreateCarStepDefinitions(CreateCarPage createCarPage, VehiclesPage vehiclesPage) {
        this.createCarPage = createCarPage;
        this.vehiclesPage = vehiclesPage;
    }

    @Then("user click on {string} button")
    public void user_click_on_button(String string) {
        vehiclesPage.waitUntilLoaderMaskDisappear();
        if (string.equals("Create Car")) {
            vehiclesPage.clickToCreateACar();
        }
    }

    @Then("user adds new car information:")
    public void user_adds_new_car_information(List<Map<String, String>> dataTable) {
        createCarPage.waitUntilLoaderMaskDisappear();
        System.out.println(dataTable);
        int row = 1;
        for (Map<String, String> map : dataTable) {
            createCarPage.licensePlateElement.sendKeys(map.get("License Plate"));
            createCarPage.driverElement.sendKeys(map.get("Driver"));
            createCarPage.locationElement.sendKeys(map.get("Location"));
            createCarPage.modelYearElement.sendKeys(map.get("Model Year"));
            createCarPage.colorElement.sendKeys(map.get("Color"));
            BrowserUtils.wait(2);//for demo

            if (row == dataTable.size()) {
                //if it's a last row - click save and close
                createCarPage.clickSaveAndClose();
            } else {
                //if it's not the last row - click save and add new
                createCarPage.clickSaveAndAddNew();
            }

            BrowserUtils.wait(2);//for demo
            row++;
        }
    }

}
