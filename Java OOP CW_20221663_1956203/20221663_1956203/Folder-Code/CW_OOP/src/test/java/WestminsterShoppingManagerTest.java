import org.example.Clothing;
import org.example.Electronics;
import org.example.Product;
import org.example.WestminsterShoppingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class WestminsterShoppingManagerTest {
    private WestminsterShoppingManager shoppingManager;

    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;


    @BeforeEach
    public void setUp() {
        shoppingManager = new WestminsterShoppingManager();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }
    
    @Test
    public void testDeleteNonExistentProduct() {
        ArrayList<Product> productList = shoppingManager.getProductList();

        // Verify that the product list is initially empty
        assertEquals(0, productList.size());

        // Simulate user input to delete a product that does not exist in the list
        String simulatedInput = "NonExistentProduct\n";  // Product ID that doesn't exist
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner input = new Scanner(System.in);
        shoppingManager.deleteNewProduct(input);

        // Verify that the product list remains empty (no product added)
        assertEquals(0, productList.size());
    }

    @Test
    public void testInvalidMenuChoice() {
        String input = "7\n" +      // Invalid menu choice
                "6\n";       // Exit menu
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        shoppingManager.displayMenu();

        assertTrue(outContent.toString().contains("Invalid Choice. Please enter a valid option."));
    }

    @Test
    public void testExitMenu() {
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        shoppingManager.displayMenu();
        assertTrue(outContent.toString().contains("Exiting from the admin panel"));
    }

    @Test
    public void testPrintEmptyProductList() {
        String input = "3\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        shoppingManager.displayMenu();
        assertTrue(outContent.toString().contains("There aren't any products on the list."));
    }

    @Test
    public void testInvalidProductType() {
        String input = "1\n3\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        shoppingManager.displayMenu();
        assertTrue(outContent.toString().contains("Invalid product type"));
        assertEquals(0, shoppingManager.getProductList().size());
    }























}

