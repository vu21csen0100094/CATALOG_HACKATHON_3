package auction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AuctionItem {
    private String itemName;
    private double startingPrice;
    private double highestBid;
    private String highestBidder;

    public AuctionItem(String itemName, double startingPrice) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.highestBid = startingPrice;
        this.highestBidder = "No bids yet";
    }

    public String getItemName() {
        return itemName;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            System.out.println("Bid placed successfully!");
        } else {
            System.out.println("Bid amount too low. Please bid higher than the current highest bid.");
        }
    }
}

class AuctionSystem {
    private List<AuctionItem> auctions;

    public AuctionSystem() {
        auctions = new ArrayList<>();
    }

    public void createAuction(String itemName, double startingPrice) {
        auctions.add(new AuctionItem(itemName, startingPrice));
        System.out.println("Auction created for " + itemName + " with starting price of $" + startingPrice);
    }

    public void showAuctions() {
        if (auctions.isEmpty()) {
            System.out.println("No auctions available.");
        } else {
            for (int i = 0; i < auctions.size(); i++) {
                AuctionItem auction = auctions.get(i);
                System.out.println((i + 1) + ". " + auction.getItemName() +
                        " - Highest Bid: $" + auction.getHighestBid() +
                        " by " + auction.getHighestBidder());
            }
        }
    }

    public void placeBid(int auctionIndex, String bidderName, double bidAmount) {
        if (auctionIndex >= 1 && auctionIndex <= auctions.size()) {
            AuctionItem auction = auctions.get(auctionIndex - 1);
            auction.placeBid(bidderName, bidAmount);
        } else {
            System.out.println("Invalid auction selected.");
        }
    }
}

public class Auction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuctionSystem auctionSystem = new AuctionSystem();
        
        while (true) {
            System.out.println("\n1. Create Auction");
            System.out.println("2. View Auctions");
            System.out.println("3. Place Bid");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter starting price: ");
                    double startingPrice = scanner.nextDouble();
                    auctionSystem.createAuction(itemName, startingPrice);
                    break;
                case 2:
                    auctionSystem.showAuctions();
                    break;
                case 3:
                    auctionSystem.showAuctions();
                    System.out.print("Select auction to bid on (number): ");
                    int auctionIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your name: ");
                    String bidderName = scanner.nextLine();
                    System.out.print("Enter your bid amount: ");
                    double bidAmount = scanner.nextDouble();
                    auctionSystem.placeBid(auctionIndex, bidderName, bidAmount);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
