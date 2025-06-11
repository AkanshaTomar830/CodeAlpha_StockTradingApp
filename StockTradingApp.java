import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> stocks = new HashMap<>();
    double balance;

    Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost <= balance) {
            balance -= cost;
            stocks.put(stock.name, stocks.getOrDefault(stock.name, 0) + quantity);
            System.out.println("✅ Bought " + quantity + " of " + stock.name);
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    void sellStock(Stock stock, int quantity) {
        if (stocks.getOrDefault(stock.name, 0) >= quantity) {
            balance += stock.price * quantity;
            stocks.put(stock.name, stocks.get(stock.name) - quantity);
            System.out.println("✅ Sold " + quantity + " of " + stock.name);
        } else {
            System.out.println("❌ Not enough stock to sell.");
        }
    }

    void viewPortfolio() {
        System.out.println("\n📊 Portfolio Summary:");
        for (String stock : stocks.keySet()) {
            System.out.println("🔹 " + stock + ": " + stocks.get(stock) + " shares");
        }
        System.out.printf("💰 Balance: ₹%.2f\n\n", balance);
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000.0); // Starting balance

        Map<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("HDFC", new Stock("HDFC", 2700));

        while (true) {
            System.out.println("\n📈 Stock Trading Platform");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n📊 Current Market Prices:");
                    for (Stock s : market.values()) {
                        System.out.println("🔸 " + s.name + " - ₹" + s.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock name to buy (TCS, INFY, HDFC): ");
                    String buyStock = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    if (market.containsKey(buyStock)) {
                        portfolio.buyStock(market.get(buyStock), qty);
                    } else {
                        System.out.println("❌ Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock name to sell (TCS, INFY, HDFC): ");
                    String sellStock = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    if (market.containsKey(sellStock)) {
                        portfolio.sellStock(market.get(sellStock), sellQty);
                    } else {
                        System.out.println("❌ Stock not found.");
                    }
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("👋 Exiting. Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }
}
