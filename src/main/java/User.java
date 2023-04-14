import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {
    //User should have a list of books
    //User should have a username and a password
    private String username;
    private String password;
    private ArrayList<Book> rentedBooks=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
    public User(String username,String password, ArrayList<Book> rentedBooks){
        this.username=username;
        this.password=password;
        this.rentedBooks=rentedBooks;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setRentedBooks(ArrayList<Book> rentedBooks){
        this.rentedBooks=rentedBooks;
    }
    public ArrayList<Book> getRentedBooks(){
        return this.rentedBooks;
    }
    public void searchBook(String bookName)throws IOException {
        Library library=new Library();
        library.loadBooks();
        for(int i=0; i<library.bookInfos.size();i++){
            if(library.bookInfos.get(i).contains(bookName)){
                System.out.println(library.bookInfos.get(i));
                if(library.availableBooks.get(i).getQuantity()>0){
                    System.out.println("do you want to rent it?");
                    System.out.println("1: YES");
                    System.out.println("2: NO");
                    if(getInput()==1){
                        Book book=new Book(library.availableBooks.get(i).getBookName(),library.availableBooks.get(i).getBookAuthor(),library.availableBooks.get(i).getPublishedYear(),library.availableBooks.get(i).getISBN(),1);
                        rentedBooks.add(book);
                        rentBook(book,library);
                        System.out.println("done!");
                        Main.showUserMenu(library,getUsername(),getPassword());
                    }
                }
                else{
                    System.out.println("this book isn`t available");
                    Main.showUserMenu(library,getUsername(),getPassword());
                }
            }
        }
    }

    public void rentBook(Book book,Library library){
        rentedBooks.add(book);
        book.setQuantity(book.getQuantity()-1);
        library.allRentedBooks.add(book);
    }

    public void returnBook(Library library,String bookName)throws IOException{
        for(int i=0 ; i<library.availableBooks.size();i++){
            if(bookName.equals(library.availableBooks.get(i))){
                library.availableBooks.get(i).setQuantity(library.availableBooks.get(i).getQuantity()+1);
                System.out.println("done!");
                Main.showUserMenu(library,getUsername(),getPassword());
            }
            else{
                System.out.println("this book does not exist in our library");
                Main.showUserMenu(library,getUsername(),getPassword());
            }
        }
    }
    public int getInput() {
        int choice = -1;
        while (choice < 0 || choice > 1) {
            try {
                System.out.println("enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("invalid selection, try again");
            }
        }
        return choice;
    }
}
