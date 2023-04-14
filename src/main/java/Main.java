import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.UUID;

public class Main implements Serializable {
    /*
    * make a functional library app using oop
    * run the main program in Main.java and code the oop part in other classes
    * don't forget to add at least 1 librarian to the library to make it functional.
    * *  *** don't limit yourself to our template ***
     */
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        runMenu();
    }

    public static void runMenu()throws IOException{
        printStarting();
        secondMenu();
    }

    public static void secondMenu() throws IOException{
        int choice=getInput();
        Library library=new Library();
        loginOrSignUpMenu(choice,library);
    }

    private static void printStarting() {
        printHeaderMenu();
        printLoginOrSignUpMenu();
    }

    public static boolean headLibrarianLogin(String username,String password,Library library){
        return username.equals(library.headLibrarianUserName) && password.equals(library.headLibrarianPassword);
    }
    public static void printHeaderMenu(){
        System.out.println("         hi          ");
        System.out.println("welcome to my library");
    }
    public static void printLoginOrSignUpMenu(){
        System.out.println("if you are head librarian choose 1");
        System.out.println("if you are a librarian choose 2");
        System.out.println("if you are a new librarian choose 3");
        System.out.println("if you are a user choose 4");
        System.out.println("if you are a new user choose 5");
    }
    static boolean isDone=false;
    static boolean doesExist=true;
    public static void loginOrSignUpMenu(int choice,Library library)throws IOException {
        switch (choice){
            case 1:
                while(!isDone){
                    System.out.println("please enter your username");
                    String headLibrarianUsername=sc.nextLine();
                    System.out.println("please enter your password");
                    String headLibrarianPassword=sc.nextLine();
                    isDone=headLibrarianLogin(headLibrarianUsername,headLibrarianPassword,library);
                    if(isDone){
                        System.out.println("login successful");
                        showHeadLibrarianMenu(library);
                    }
                    else {
                        System.out.println("please try again");
                    }
                }
                break;
            case 2:
                while(!isDone){
                    System.out.println("please enter your username");
                    String LibrarianUsername=sc.nextLine();
                    System.out.println("please enter your password");
                    String LibrarianPassword=sc.nextLine();
                    isDone=library.doesLibrarianExist(LibrarianUsername,LibrarianPassword);
                    if(isDone){
                        System.out.println("login successful");
                        showLibrarianMenu(library);
                    }
                    else {
                        System.out.println("please try again");
                    }
                }
                break;
            case 3:
                while(doesExist){
                    System.out.println("please enter a username");
                    String newLibrarianUsername=sc.nextLine();
                    doesExist=library.doesLibrarianExist(newLibrarianUsername);
                    if(!doesExist){
                        System.out.println("please enter a password");
                        String newLibrarianPassword=sc.nextLine();
                        library.newLibrarian(newLibrarianUsername,newLibrarianPassword);
                        System.out.println("you are now officially a new librarian");
                        showLibrarianMenu(library);
                    }
                    else{
                        System.out.println("this username already exists please choose another username");
                    }
                }
                break;
            case 4:
                while(!isDone){
                    System.out.println("please enter your username");
                    String Username=sc.nextLine();
                    System.out.println("please enter your password");
                    String Password=sc.nextLine();
                    isDone=library.doesUserExist(Username,Password);
                    if(isDone){
                        System.out.println("login successful");
                        showUserMenu(library, Username, Password);
                    }
                    else {
                        System.out.println("please try again");
                    }
                }
                break;
            case 5:
                while(doesExist){
                    System.out.println("please enter a username");
                    String newUserUsername=sc.nextLine();
                    doesExist=library.doesUserExist(newUserUsername);
                    if(!doesExist){
                        System.out.println("please enter a password");
                        String newUserPassword=sc.nextLine();
                        library.newUser(newUserUsername,newUserPassword);
                        System.out.println("you are now officially a new user");
                        showUserMenu(library,newUserUsername,newUserPassword);
                    }
                    else{
                        System.out.println("this username already exists please choose another username");
                    }
                }
                break;
        }
    }

    public static void showUserMenu(Library library, String Username, String Password)throws IOException {
        while(true){
            printUserMenu();
            int choice3=getInput();
            User user=new User(Username, Password);
            userMenu(choice3,user, library);
            if(logout){
                System.out.println("goodbye");
                break;
            }
        }
    }

    public static void showLibrarianMenu(Library library)throws IOException{
        while(true){
            printLibrarianMenu();
            int choice2=getInput();
            librarianMenu(choice2, library);
            if(logout){
                System.out.println("goodbye");
                break;
            }
        }
    }

    public static void showHeadLibrarianMenu(Library library) throws IOException{
        while (true){
            printHeadLibrarianMenu();
            int choice1=getInput();
            headLibrarianMenu(choice1, library);
            if(logout){
                System.out.println("goodbye!");
                break;
            }
        }
    }

    public static void printHeadLibrarianMenu(){
        System.out.println("for removing a user enter 0");
        System.out.println("for searching a user enter 1");
        System.out.println("for updating a user enter 2");
        System.out.println("for removing a librarian enter 3");
        System.out.println("for searching a librarian enter 4");
        System.out.println("for updating a librarian enter 5");
        System.out.println("for logging out enter 6");
    }
    public static void headLibrarianMenu(int choice, Library library)throws IOException{
        switch (choice){
            case 0:
                System.out.println("please enter the username of the user you wish to be removed");
                String removeUserUsername=sc.nextLine();
                library.removeUser(removeUserUsername);
                break;
            case 1:
                System.out.println("please enter the username of the user you want to search");
                String searchUsername=sc.nextLine();
                library.searchUser(searchUsername);
                break;
            case 2:
                System.out.println("please enter the username of the user you want to update");
                String updateUsername=sc.nextLine();
                System.out.println("please enter the new username");
                String oldUsername=sc.nextLine();
                library.updateUser(updateUsername,oldUsername);
                break;
            case 3:
                System.out.println("please enter the username of the librarian you wish to be removed");
                String removeLibrarian=sc.nextLine();
                library.removeLibrarian(removeLibrarian);
                break;
            case 4:
                System.out.println("please enter the username of the librarian you want to search");
                String searchLibrarian=sc.nextLine();
                library.searchLibrarianForHeadLibrarian(searchLibrarian);
                break;
            case 5:
                System.out.println("please enter the username of the librarian you want to update");
                String updateLibrarian=sc.nextLine();
                System.out.println("please enter the new username");
                String newLibrarianUsername=sc.nextLine();
                library.updateLibrarian(updateLibrarian,newLibrarianUsername);
                break;
            case 6:
                logOut();
                break;
        }
    }
    public static void printLibrarianMenu(){
        System.out.println("for searching a user choose 0");
        System.out.println("for searching a librarian choose 1");
        System.out.println("for adding a book choose 2");
        System.out.println("for updating a book choose 3");
        System.out.println("for removing a book choose 4");
        System.out.println("for logging out choose 5");
    }
    public static void librarianMenu(int choice,Library library)throws IOException{
        switch (choice){
            case 0:
                System.out.println("please enter the username of the user you want to search");
                String searchUsername=sc.nextLine();
                library.searchUserForLibrarian(searchUsername);
                break;
            case 1:
                System.out.println("please enter the username of the librarian you want to search");
                String searchLibrarian=sc.nextLine();
                library.searchLibrarian(searchLibrarian);
                break;
            case 2:
                System.out.println("please enter the name of the book you want to add");
                String toBeAddBook=sc.nextLine();
                if(library.checkingExistenceOfBook(toBeAddBook)){
                    System.out.println("this book already exists so please enter how many of this book you want to add");
                    int toBeAddAmount=sc.nextInt();
                    library.increaseBook(toBeAddBook,toBeAddAmount);
                }
                else{
                    System.out.println("please enter name of the author");
                    String authorName=sc.nextLine();
                    System.out.println("please enter the year of the publish");
                    int publishedYear=sc.nextInt();
                    System.out.println("please enter how many of this book you want to add");
                    int quantity=sc.nextInt();
                    library.addBook(toBeAddBook,authorName,publishedYear, UUID.randomUUID(),quantity);
                }
                break;
            case 3:
                System.out.println("please enter the name of the book you want to update");
                String updateBookName=sc.nextLine();
                library.showUpdateBookMenu(updateBookName);
                break;
            case 4:
                System.out.println("please enter the name of the book you wish to be removed");
                String toBeRemovedBookName=sc.nextLine();
                library.removeBook(toBeRemovedBookName);
                break;
            case 5:
                logOut();
                break;
        }
    }
    public static void printUserMenu(){
        System.out.println("for searching and renting a book choose 0");
        System.out.println("for returning a book choose 1");
        System.out.println("for logging out choose 2");
    }
    public static void userMenu(int choice, User user,Library library)throws IOException{
        switch (choice){
            case 0:
                System.out.println("please enter the name of the book");
                String toBeSearchedBook=sc.nextLine();
                user.searchBook(toBeSearchedBook);
                break;
            case 1:
                System.out.println("please enter the name of the book you want to return");
                String toBeReturnedBook=sc.nextLine();
                user.returnBook(library,toBeReturnedBook);
                break;
            case 2:
                logOut();
                break;
        }
    }
    public static int getInput(){
        int choice=-1;
        while(choice<0 || choice>6){
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
    static boolean logout=false;
    public static void logOut(){
        logout=true;
    }
}
