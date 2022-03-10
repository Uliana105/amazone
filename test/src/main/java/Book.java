public class Book {
    private final String name;
    private final String author;
    private final String price;
    private final boolean bestseller;
    Book(String name, String author, String price, boolean bestseller) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.bestseller = bestseller;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBestseller() {
        return bestseller;
    }
}
