package ru.clevertec.check.csvWriter;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;
import ru.clevertec.check.entity.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ProductWriter {

    public void writeProductToCsv(Product product) {
        String csvFile = "C:\\Users\\37529\\IdeaProjects\\Check-Runner\\src\\main\\resources\\static\\products.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            File file = new File(csvFile);
            if (file.length() == 0) {
                String[] headerOfProducts = {"Id", "Description", "price,$", "quantity in stock", "wholesale product"};
                writer.writeNext(headerOfProducts);
            }
            String wholesaleSymbol = product.isWholesale() ? "+" : "-";
            String[] productData = {String.valueOf(product.getId()),
                    product.getDescription(),
                    String.valueOf(String.format("%.2f", product.getPrice())),
                    String.valueOf(product.getQuantityInStock()), wholesaleSymbol};
            writer.writeNext(productData);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

