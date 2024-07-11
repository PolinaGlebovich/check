package ru.clevertec.check.csvWriter;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;
import ru.clevertec.check.entity.Check;
import ru.clevertec.check.entity.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CheckWriter {

    public void writeCheckToCsv(Check check) {
        String csvFile = "C:\\\\Users\\\\37529\\\\IdeaProjects\\\\Check-Runner\\\\src\\\\main\\\\result.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            if (check == null || check.getProducts() == null || check.getProducts().isEmpty()) {
                writer.writeNext(new String[]{"BAD REQUEST"});
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String currentDate = dateFormat.format(new Date());
                String currentTime = timeFormat.format(new Date());

                String[] creationDateTime = {"Date;", "Time", "", "", ""};
                writer.writeNext(creationDateTime);

                String[] dateTime = {currentDate + ";", currentTime, "", "", ""};
                writer.writeNext(dateTime);

                String[] headerOfProducts = {"Qty", "Description", "Price", "Discount", "Total Price"};
                writer.writeNext(headerOfProducts);

                for (Product product : check.getProducts()) {
                    String[] data = {
                            String.valueOf(product.getQuantity()),
                            product.getDescription(),
                            String.valueOf(product.getPrice()),
                            String.valueOf(product.getDiscount()),
                            String.valueOf(product.getTotalProductPrice()),
                    };

                    writer.writeNext(data);
                }

                String[] discountCardNumber = {"", "", "", "Discount Card Number:", (check.getDiscountCardNumber() + "$")};
                writer.writeNext(discountCardNumber);

                String[] discountCardAmount = {"", "", "", "Discount Percentage:", (check.getDiscountCardAmount() + "%")};
                writer.writeNext(discountCardAmount);

                String[] totalData = {"", "", "", "Total Price:", String.format("%.2f", check.getTotalPriceWithoutDiscount()) + "$"};
                writer.writeNext(totalData);

                String[] discountData = {"", "", "", "Total Discount:", String.format("%.2f", check.getTotalDiscount()) + "$"};
                writer.writeNext(discountData);

                String[] totalDiscountData = {"", "", "", "Total with Discount:", String.format("%.2f", check.getTotalPriceWithDiscount()) + "$"};
                writer.writeNext(totalDiscountData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}