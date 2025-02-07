import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

class Product
{
    private String productID, name;
    private int quantity;
    private Location location;

    public Product(String productID, String name, int quantity, Location location) throws InvalidLocationException
    {
        if (location == null)
        {
            throw new InvalidLocationException("Invalid location assigned to product: " + productID);
        }
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductID() { return productID; }
    public int getQuantity() { return quantity; }
    public void updateQuantity(int qty) { this.quantity += qty; }
    public Location getLocation() { return location; }
    
    @Override
    public String toString()
    {
        return String.format("%-10s %-15s Qty: %-5d %s", productID, name, quantity, location);
    }
}

class Location
{
    private int aisle, shelf, bin;

    public Location(int aisle, int shelf, int bin)
    {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    @Override
    public String toString()
    {
        return String.format("Aisle %-2d | Shelf %-2d | Bin %-2d", aisle, shelf, bin);
    }
}

class Order
{
    private String orderID;
    private List<String> productIDs;
    private Priority priority;

    public enum Priority { STANDARD, EXPEDITED }

    public Order(String orderID, List<String> productIDs, Priority priority)
    {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public List<String> getProductIDs() { return productIDs; }
    public Priority getPriority() { return priority; }
    
    @Override
    public String toString()
    {
        return String.format("Order %-5s | Priority: %-9s", orderID, priority);
    }
}

class OrderComparator implements Comparator<Order>
{
    @Override
    public int compare(Order o1, Order o2)
    {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}

class OutOfStockException extends Exception
{
    public OutOfStockException(String message)
    {
        super(message);
    }
}

class InvalidLocationException extends Exception
{
    public InvalidLocationException(String message)
    {
        super(message);
    }
}

class InventoryManager
{
    private static final Logger logger = Logger.getLogger(InventoryManager.class.getName());
    private Map<String, Product> products = new ConcurrentHashMap<>();
    private PriorityBlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>(10, new OrderComparator());
    
    public void addProduct(Product product)
    {
        products.put(product.getProductID(), product);
    }
    
    public void processOrders()
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        while (!orderQueue.isEmpty())
        {
            Order order = orderQueue.poll();
            executor.execute(() -> fulfillOrder(order));
        }
        executor.shutdown();
    }
    
    private void fulfillOrder(Order order)
    {
        System.out.println("Processing " + order);
        for (String productID : order.getProductIDs())
        {
            Product product = products.get(productID);
            try
            {
                if (product != null && product.getQuantity() > 0)
                {
                    product.updateQuantity(-1);
                    logger.info("Order Processed: " + order + " | Product: " + product);
                    System.out.println("  - Fulfilled: " + product);
                }
                else
                {
                    throw new OutOfStockException("Product " + productID + " is out of stock.");
                }
            }
            catch (OutOfStockException e)
            {
                logger.warning(e.getMessage());
                System.out.println("  - " + e.getMessage());
            }
        }
    }
    
    public void addOrder(Order order)
    {
        orderQueue.add(order);
    }
    
    public void saveInventoryToFile(String filename) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (Product product : products.values())
            {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }
    
    public void loadInventoryFromFile(String filename) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            System.out.println("\nLoaded Inventory Data:");
            while ((line = reader.readLine()) != null)
            {
                System.out.println("  " + line); 
            }
        }
    }
    
    public void printFinalInventory()
    {
        System.out.println("\nFinal Inventory State:");
        for (Product product : products.values())
        {
            System.out.println("  " + product);
        }
    }
}

public class WarehouseSystem
{
    public static void main(String[] args) throws InvalidLocationException, IOException
    {
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addProduct(new Product("P001", "Laptop", 10, new Location(1, 1, 1)));
        inventoryManager.addProduct(new Product("P002", "Phone", 5, new Location(2, 1, 3)));
        inventoryManager.addProduct(new Product("P003", "Tablet", 7, new Location(3, 2, 2)));
        inventoryManager.addProduct(new Product("P004", "Mac Mini", 4, new Location(4, 3, 1)));
        inventoryManager.addProduct(new Product("P005", "VR Headset", 6, new Location(5, 2, 5)));
        
        List<String> order1Items = Arrays.asList("P001", "P002");
        Order order1 = new Order("O001", order1Items, Order.Priority.EXPEDITED);
        
        inventoryManager.addOrder(order1);
        inventoryManager.processOrders();
        inventoryManager.printFinalInventory();
        
        inventoryManager.saveInventoryToFile("inventory.txt");
        inventoryManager.loadInventoryFromFile("inventory.txt");
    }
}
