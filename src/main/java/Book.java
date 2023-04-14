import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {
    //Book should contain name,author,year of publish and ISBN
    private String bookName;
    private String bookAuthor;
    private int publishedYear;
    private UUID ISBN=UUID.randomUUID();
    private int quantity;
    public Book(String bookName,String bookAuthor,int publishedYear,UUID ISBN,int quantity){
        this.bookName=bookName;
        this.bookAuthor=bookAuthor;
        this.publishedYear=publishedYear;
        this.ISBN=ISBN;
        this.quantity=quantity;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }
    public String getBookName(){
        return this.bookName;
    }
    public void setBookAuthor(String bookAuthor){
        this.bookAuthor=bookAuthor;
    }
    public String getBookAuthor(){
        return this.bookAuthor;
    }
    public void setPublishedYear(int publishedYear){
        this.publishedYear=publishedYear;
    }
    public int getPublishedYear(){
        return publishedYear;
    }
    public void setISBN(UUID ISBN){
        this.ISBN=ISBN;
    }
    public UUID getISBN(){
        return this.ISBN;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }


}
