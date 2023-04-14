import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

public class Library implements Serializable {
    /*
    * The library should have a list of books.
    * The library should have a map of books ISBNs which is linked to the amount of book
    -> (for example: harry potter -> 4 means there are currently 4 harry potter books)
    * The library should have a list of users and a list of librarians.
     */
    String headLibrarianUserName="nima";
    String headLibrarianPassword="nima1383";
    Scanner sc=new Scanner(System.in);
    //book related functions
    ArrayList<Book> availableBooks=new ArrayList<>();
    ArrayList<Book> allRentedBooks=new ArrayList<>();
    ArrayList<User> allUsers=new ArrayList<>();
    ArrayList<Librarian> allLibrarians=new ArrayList<>();
    ArrayList<String> usersPasswords=new ArrayList<>();
    ArrayList<String> usersUsernames=new ArrayList<>();
    ArrayList<String> librariansPasswords=new ArrayList<>();
    ArrayList<String> librariansUsernames=new ArrayList<>();
    ArrayList<String> bookInfos=new ArrayList<>();
    public Library(){}
    public void addBook(String bookName, String bookAuthor, int publishedYear, UUID ISBN, int quantity)throws IOException{
        Book book=new Book(bookName,bookAuthor,publishedYear,ISBN,quantity);
        availableBooks.add(book);
        System.out.println("done!");
        Main.showLibrarianMenu(new Library());
        String bookInfo=bookName+";"+bookAuthor+";"+publishedYear+";"+ISBN+";"+quantity;
        saveBooks(bookInfo);
    }

    public void removeBook(String bookName)throws IOException{
        for(int i=0 ;i<availableBooks.size();i++){
            if(bookName.equals(availableBooks.get(i).getBookName())){
                availableBooks.remove(i);
                System.out.println("done!");
                Main.showLibrarianMenu(new Library());
            }
            else{
                System.out.println("this book does not exist or has been already deleted");
                Main.showLibrarianMenu(new Library());
            }
        }
    }
    public boolean checkingExistenceOfBook(String bookName)throws IOException{
        loadBooks();
        for(int i=0 ;i<bookInfos.size();i++){
            if(bookInfos.get(i).contains(bookName)){
                return true;
            }
        }
        return false;
    }
    public int gettingExistenceOfBook(String bookName){
        int x=0;
        for(int i=0 ;i<bookInfos.size();i++){
            if(bookInfos.get(i).contains(bookName)){
                x=i;
            }
        }
        return x;
    }
    public void showUpdateBookMenu(String bookName) throws IOException{
        printUpdatingBookMenu();
        int choice=getInput();
        if(checkingExistenceOfBook(bookName)){
            updatingBookCommands(choice,bookName,gettingExistenceOfBook(bookName));
            Main.showLibrarianMenu(new Library());
        }
    }

    public void increaseBook(String bookName,int quantity)throws IOException{
        for(int i=0; i<availableBooks.size();i++){
            if(bookName.equals(availableBooks.get(i).getBookName())){
                availableBooks.get(i).setQuantity(availableBooks.get(i).getQuantity()+quantity);
                System.out.println("done!");
                Main.showLibrarianMenu(new Library());
            }
        }
    }

    public void decreaseBook(String bookName,int quantity)throws IOException{
        for(int i=0; i<availableBooks.size();i++){
            if(bookName.equals(availableBooks.get(i).getBookName())){
                availableBooks.get(i).setQuantity(availableBooks.get(i).getQuantity()-quantity);
                System.out.println("done!");
                Main.showLibrarianMenu(new Library());
            }
        }
    }

    //user related functions
    public void removeUser(String username)throws IOException{
        loadingUsersUsernames();
        for(int i=0 ;i<usersUsernames.size();i++){
            allUsers.get(i).setUsername(usersUsernames.get(i));
        }
        for(int i=0 ;i<allUsers.size();i++){
            if(username.equals(allUsers.get(i).getUsername())){
                allUsers.remove(i);
                System.out.println("done!");
                Main.showHeadLibrarianMenu(new Library());
            }
            else{
                System.out.println("this user does not exist or already has been deleted");
                Main.showHeadLibrarianMenu(new Library());
            }
        }
    }

    public void searchUser(String username)throws IOException{
        loadingUsersUsernames();
        for(int i=0 ;i<usersUsernames.size();i++){
            allUsers.get(i).setUsername(usersUsernames.get(i));
        }
        for(int i=0 ;i<allUsers.size();i++){
            if(username.equals(allUsers.get(i).getUsername())){
                System.out.println("this user username is"+allUsers.get(i).getUsername());
                System.out.println("this user rented books are:");
                for(int j=0; j<allUsers.get(i).getRentedBooks().size();j++){
                    System.out.println(allUsers.get(i).getRentedBooks().get(i).getBookName());
                    Main.showHeadLibrarianMenu(new Library());
                }
            }
            else{
                System.out.println("this username does not exist");
                Main.showHeadLibrarianMenu(new Library());
            }
        }
    }
    public void searchUserForLibrarian(String username)throws IOException{
        for(int i=0 ;i<allUsers.size();i++){
            if(username.equals(allUsers.get(i).getUsername())){
                System.out.println("this user username is"+allUsers.get(i).getUsername());
                System.out.println("this user rented books are:");
                for(int j=0; j<allUsers.get(i).getRentedBooks().size();j++){
                    System.out.println(allUsers.get(i).getRentedBooks().get(i).getBookName());
                    Main.showLibrarianMenu(new Library());
                }
            }
            else{
                System.out.println("this username does not exist");
                Main.showLibrarianMenu(new Library());
            }
        }
    }

    public void updateUser(String username,String newUsername)throws IOException{
        if(doesUserExist(username)){
            allUsers.get(gettingUserExistence(username)).setUsername(newUsername);
            System.out.println("done!");
            Main.showHeadLibrarianMenu(new Library());
        }
        else{
            System.out.println("this username does not exist");
            Main.showHeadLibrarianMenu(new Library());
        }
    }

    public boolean doesUserExist(String username,String password)throws IOException{
        loadingUsersPasswords();
        loadingUsersUsernames();
        for(int i=0 ;i<usersUsernames.size();i++){
            if(username.equals(usersUsernames.get(i)) && password.equals(usersPasswords.get(i))){
                return true;
            }
        }
        return false;
    }
    public boolean doesUserExist(String username)throws IOException{
        loadingUsersUsernames();
        for(int i=0 ;i<usersUsernames.size();i++){
            if(username.equals(usersUsernames.get(i))){
                return true;
            }
        }
        return false;
    }
    public int gettingUserExistence(String username)throws IOException{
        loadingUsersUsernames();
        for(int i=0 ;i<usersUsernames.size();i++){
            allUsers.get(i).setUsername(usersUsernames.get(i));
        }
        int x=0;
        for(int i=0 ;i<allUsers.size();i++){
            if(username.equals(allUsers.get(i).getUsername())){
                x=i;
            }
        }
        return x;
    }
    //librarian related functions
    public void removeLibrarian(String librarianUsername) throws IOException{
        loadingLibrariansUsernames();
        for(int i=0 ;i<librariansUsernames.size();i++){
            allLibrarians.get(i).setLibrarianUsername(librariansUsernames.get(i));
        }
        for(int i=0 ;i<allLibrarians.size();i++){
            if(librarianUsername.equals(allLibrarians.get(i).getLibrarianUsername())){
                allLibrarians.remove(i);
                System.out.println("done!");
                Main.showHeadLibrarianMenu(new Library());
            }
            else{
                System.out.println("this Librarian does not exist or already has been deleted");
                Main.showHeadLibrarianMenu(new Library());
            }
        }
    }

    public void searchLibrarian(String librarianUsername)throws IOException{
        loadingLibrariansUsernames();
        loadingLibrariansPasswords();
        for(int i=0 ;i<librariansUsernames.size();i++){
            allLibrarians.get(i).setLibrarianUsername(librariansUsernames.get(i));
        }
        for(int i=0 ;i<librariansPasswords.size();i++){
            allLibrarians.get(i).setLibrarianPassword(librariansPasswords.get(i));
        }
        for(int i=0 ;i<allLibrarians.size();i++){
            if(librarianUsername.equals(allLibrarians.get(i).getLibrarianUsername())){
                System.out.println("this Librarian username is"+allLibrarians.get(i).getLibrarianUsername());
                Main.showLibrarianMenu(new Library());
            }
            else{
                System.out.println("this librarian username does not exist");
                Main.showLibrarianMenu(new Library());
            }
        }
    }
    public void searchLibrarianForHeadLibrarian(String librarianUsername)throws IOException{
        loadingLibrariansUsernames();
        loadingLibrariansPasswords();
        for(int i=0 ;i<librariansUsernames.size();i++){
            allLibrarians.get(i).setLibrarianUsername(librariansUsernames.get(i));
        }
        for(int i=0 ;i<librariansPasswords.size();i++){
            allLibrarians.get(i).setLibrarianPassword(librariansPasswords.get(i));
        }
        for(int i=0 ;i<allLibrarians.size();i++){
            if(librarianUsername.equals(allLibrarians.get(i).getLibrarianUsername())){
                System.out.println("this Librarian username is"+allLibrarians.get(i).getLibrarianUsername());
                Main.showHeadLibrarianMenu(new Library());
            }
            else{
                System.out.println("this librarian username does not exist");
                Main.showHeadLibrarianMenu(new Library());
            }
        }
    }
    public void updateLibrarian(String LibrarianUsername,String newLibrarianUsername)throws IOException{
        if(doesLibrarianExist(LibrarianUsername)){
            allLibrarians.get(gettingLibrarianExistence(LibrarianUsername)).setLibrarianUsername(newLibrarianUsername);
            System.out.println("done!");
            Main.showHeadLibrarianMenu(new Library());
        }
        else{
            System.out.println("this librarian username does not exist");
            Main.showHeadLibrarianMenu(new Library());
        }
    }
    public int gettingLibrarianExistence(String librarianUsername) throws IOException{
        loadingLibrariansUsernames();
        for(int i=0 ;i<librariansUsernames.size();i++){
            allLibrarians.get(i).setLibrarianUsername(librariansUsernames.get(i));
        }
        int x=0;
        for (int i = 0; i < allLibrarians.size(); i++) {
            if (librarianUsername.equals(allLibrarians.get(i).getLibrarianUsername())) {
                x=i;
            }
        }
        return x;
    }
    public boolean doesLibrarianExist(String librarianUsername,String librarianPassword)throws IOException {
        loadingLibrariansUsernames();
        loadingLibrariansPasswords();
        for (int i = 0; i < librariansUsernames.size(); i++) {
            if (librarianUsername.equals(librariansUsernames.get(i)) && librarianPassword.equals(librariansPasswords.get(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean doesLibrarianExist(String librarianUsername)throws IOException{
        loadingLibrariansUsernames();
        for(int i=0 ;i<librariansUsernames.size();i++){
            if(librarianUsername.equals(librariansUsernames.get(i))){
                return true;
            }
        }
        return false;
    }
    public void printUpdatingBookMenu(){
        System.out.println("if you want to change the name of the book choose 1");
        System.out.println("if you want to change the name of the author of the book choose 2");
        System.out.println("if you want to change the year which the book was published choose 3");
        System.out.println("if you want to change the quantity of the book choose 4");
    }
    public int getInput(){
        int choice=-1;
        while(choice<0 || choice>4){
            try{
                System.out.println("enter your choice: ");
                choice=Integer.parseInt(sc.nextLine());

            }
            catch(NumberFormatException e){
                System.out.println("invalid selection, try again");
            }
        }
        return choice;
    }
    public void updatingBookCommands(int choice,String bookName,int number)throws IOException{
        switch (choice){
            case 1:
                System.out.println("please enter the new name for the book");
                String newBookName=sc.nextLine();
                availableBooks.get(number).setBookName(newBookName);
                break;
            case 2:
                System.out.println("please enter the new author name for the book");
                String newAuthorName=sc.nextLine();
                availableBooks.get(number).setBookAuthor(newAuthorName);
                break;
            case 3:
                System.out.println("please enter the new year which the book is published ");
                int newPublishedYear= sc.nextInt();
                availableBooks.get(number).setPublishedYear(newPublishedYear);
                break;
            case 4:
                System.out.println("for increasing book quantity choose 1 and for decreasing book quantity choose 2");
                int choice1=getInput();
                switch (choice1){
                    case 1:
                        System.out.println("please enter the amount you want to add");
                        int addAmount=sc.nextInt();
                        increaseBook(bookName,addAmount);
                        break;
                    case 2:
                        System.out.println("please enter the amount you want to remove");
                        int removeAmount= sc.nextInt();
                        decreaseBook(bookName,removeAmount);
                        break;
                }
                break;
        }
    }
    public void newLibrarian(String username,String password)throws IOException{
        ArrayList<Book> addedBooks=new ArrayList<>();
        Librarian librarian=new Librarian(username,password,addedBooks);
        allLibrarians.add(librarian);
        saveLibrariansUserName(username);
        saveLibrariansPasswords(password);
    }
    public void newUser(String username,String password)throws IOException{
        ArrayList<Book> rentedBooks=new ArrayList<>();
        User user=new User(username,password,rentedBooks);
        allUsers.add(user);
        saveUsersUserName(username);
        saveUsersPasswords(password);
    }
    public void saveUsersUserName(String username)throws IOException{
        FileWriter userUsernames=new FileWriter("usersUsernames.txt");
        userUsernames.write(username+'\n');
        userUsernames.close();
    }
    public void saveUsersPasswords(String password)throws IOException{
        FileWriter usersPassword=new FileWriter("usersPasswords.txt");
        usersPassword.write(password+'\n');
        usersPassword.close();
    }
    public void loadingUsersUsernames()throws IOException{
        FileReader file=new FileReader("usersUsernames.txt");
        BufferedReader bf=new BufferedReader(file);
        String line=bf.readLine();
        while (line!=null){
            usersUsernames.add(line);
            line=bf.readLine();
        }
        bf.close();
    }
    public void loadingUsersPasswords()throws IOException{
        FileReader file=new FileReader("usersPasswords.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while ((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String password=tokens.nextToken();
            usersPasswords.add(password);
        }
        bf.close();
    }
    public void saveLibrariansUserName(String username)throws IOException{
        FileWriter librarianUsername=new FileWriter("librariansUsernames.txt");
        librarianUsername.write(username+'\n');
        librarianUsername.close();
    }
    public void saveLibrariansPasswords(String password)throws IOException{
        FileWriter librarianPassword=new FileWriter("librariansPasswords.txt");
        librarianPassword.write(password+'\n');
        librarianPassword.close();
    }
    public void loadingLibrariansUsernames()throws IOException{
        FileReader file=new FileReader("librariansUsernames.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while ((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String username=tokens.nextToken();
            librariansUsernames.add(username+'\n');
        }
        bf.close();
    }
    public void loadingLibrariansPasswords()throws IOException{
        FileReader file=new FileReader("librariansPasswords.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while ((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String password=tokens.nextToken();
            librariansPasswords.add(password);
        }
        bf.close();
    }
    public void saveBooks(String BookInfo)throws IOException{
        FileWriter booksInfo=new FileWriter("bookInfos.txt");
        booksInfo.write(BookInfo+'\n');
        booksInfo.close();
    }
    public void loadBooks()throws IOException{
        FileReader file=new FileReader("bookInfos.txt");
        BufferedReader bf=new BufferedReader(file);
        String line="";
        while ((line=bf.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(line,";");
            String BookInfo=tokens.nextToken();
            bookInfos.add(BookInfo);
        }
        bf.close();
    }
}
