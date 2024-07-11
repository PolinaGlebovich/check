//package ru.clevertec.check.csvWriter;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//
//@Component
//public class CsvInitializer implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        clearCsvFile();
//    }
//
//    private void clearCsvFile() {
//        String csvFilePath = "C:\\Users\\37529\\IdeaProjects\\check\\src\\main\\resources\\products.csv";
//        try (PrintWriter writer = new PrintWriter(csvFilePath)) {
//            writer.print("");
//            System.out.println("CSV file cleared");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
