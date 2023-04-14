import java.io.Serializable;
import java.util.ArrayList;

public class Librarian implements Serializable {
    /*
    * The librarian should have a username and a password
    * The librarian should be able to search users, librarians and books
    * The librarian should be able to add remove update user add\remove update_
    _ librarian and add\remove update book
     */
    private String librarianUsername;
    private String librarianPassword;
    private ArrayList<Book> addedBooks=new ArrayList<>();
    public Librarian(String librarianUsername,String librarianPassword,ArrayList<Book> addedBooks){
        this.addedBooks=addedBooks;
        this.librarianUsername=librarianUsername;
        this.librarianPassword=librarianPassword;
    }
    public void setLibrarianUsername(String librarianUsername){
        this.librarianUsername=librarianUsername;
    }
    public String getLibrarianUsername(){
       return this.librarianUsername;
    }
    public void setLibrarianPassword(String librarianPassword){
        this.librarianPassword=librarianPassword;
    }
    public String getLibrarianPassword(){
        return this.librarianPassword;
    }
    public ArrayList<Book> getAddedBooks(){
        return this.addedBooks;
    }
    public void setAddedBooks(ArrayList<Book> addedBooks){
        this.addedBooks=addedBooks;
    }
}
