package models;

public class Book {
    private int id;
    private String bookName;
    private int price;
    private String description;
    private String author;
    private int sold;
    private int profit;

    public Book(String bookName, int price, String description, String author, int sold) {
        this.bookName = bookName;
        this.price = price;
        this.description = description;
        this.author = author;
        this.sold = sold;
        this.profit = price * sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
