package ru.clevertec.check.csvWriter;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;
import ru.clevertec.check.entity.DiscountCard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Component
public class DiscountCardWriter {
    public void writeDiscountCardToCsv(DiscountCard discountCard) {
        String csvFile = "C:\\Users\\37529\\IdeaProjects\\Check-Runner\\src\\main\\resources\\static\\discount_card.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {

            File file = new File(csvFile);
            if (file.length() == 0) {
                String[] headerOfDiscountCard = {"Id", "Number", "Amount"};
                writer.writeNext(headerOfDiscountCard);
            }

            String[] discountData = {String.valueOf(discountCard.getId()),
                    String.valueOf(discountCard.getNumber()),
                    String.valueOf(discountCard.getAmount())};
            writer.writeNext(discountData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
