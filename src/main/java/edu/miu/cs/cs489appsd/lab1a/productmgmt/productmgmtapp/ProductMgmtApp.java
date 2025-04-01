package edu.miu.cs.cs489appsd.lab1a.productmgmt.productmgmtapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.miu.cs.cs489appsd.lab1a.productmgmt.model.Product;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {

    public static void main(String[] args) throws IOException {

        Product [] products = {

                new Product(3128874119L, "Banana", LocalDate.parse("2023-01-24"), 124, 0.55),
                new Product(2927458265L, "Apple", LocalDate.parse("2022-12-09"), 18, 1.09),
                new Product(9189927460L, "Carrot", LocalDate.parse("2023-03-31"), 89, 2.99)


        };

        Arrays.sort(products, Comparator.comparing(Product::getUnitPrice).reversed());

        System.out.println("====== JSON Output =======");

        printJson(products);

        System.out.println("\n====== XML Output =======");

        printXml(products);

        System.out.println("\n====== CSV Output =======");

        printCsv(products);



    }

    private static void printJson(Product[] products) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println(json);
    }

    private static void printXml(Product[] products) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

        xmlMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println(xml);
    }

    private static void printCsv(Product[] products) {
        System.out.println("Product ID, Name, Date Supplied, Quantity In Stock, Unit Price");
        for (Product p : products) {
            System.out.printf("%d, %s, %s, %d, %.2f\n",
                    p.getProductId(),
                    p.getName(),
                    p.getDateSupplied(),
                    p.getQuantityInStock(),
                    p.getUnitPrice());
        }

    }
}
