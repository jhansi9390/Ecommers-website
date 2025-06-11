import java.util.*;

class Product {
    private int id;
    private String name;
    private String description;
    private double price;

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Desc: %s | Price: %.2f", id, name, description, price);
    }
}

public class EcommerceApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> productList = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nE-Commerce Product Management:");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Sort Products by Price (Ascending)");
            System.out.println("6. Sort Products by Price (Descending)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                   	addProduct();
			break;
                case 2:
			viewProducts();
			break;
                case 3:
			updateProduct();
			break;
                case 4:
			deleteProduct();
        		break;
                case 5:
			sortProductsByPrice(true);
			break;
                case 6:
    			sortProductsByPrice(false);
			break;
                case 0:
			System.out.println("Exiting...");
    			break;
                default:
			System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void addProduct() {
        scanner.nextLine(); // clear buffer
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        Product product = new Product(nextId++, name, desc, price);
        productList.add(product);
        System.out.println("Product added successfully.");
    }

    private static void viewProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        productList.forEach(System.out::println);
    }

    private static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        Product product = findProductById(id);
        if (product != null) {
            scanner.nextLine(); // clear buffer
            System.out.print("Enter new name: ");
            product.setName(scanner.nextLine());
            System.out.print("Enter new description: ");
            product.setDescription(scanner.nextLine());
            System.out.print("Enter new price: ");
            product.setPrice(scanner.nextDouble());
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();

        List<Product> toRemove = new ArrayList<>();
        for (Product p : productList) {
            if (p.getId() == id) {
                toRemove.add(p);
            }
        }

        if (toRemove.isEmpty()) {
            System.out.println("No product found with ID: " + id);
        } else {
            System.out.println("Deleting the following product(s):");
            toRemove.forEach(System.out::println);
            productList.removeAll(toRemove);
            System.out.println(toRemove.size() + " product(s) deleted.");
        }
    }

    private static void sortProductsByPrice(boolean ascending) {
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        if (!ascending) {
            Collections.reverse(productList);
        }
        viewProducts();
    }

    private static Product findProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
