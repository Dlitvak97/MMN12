public class BookTester
{
    public static void main(String[] args) {
        System.out.println("********** Test Book - Started **********");
        System.out.println("\n1. Testing Constructors and toString:");
        String title1 = new String("Pride and Prejudice");
        String author1 = new String("Jane Austen");
        int year1 = 1813;
        int numPages1 = 350;
        // test concstructor which takes 4 parameters
        Book book1 = new Book(title1, author1, year1, numPages1);
        System.out.println("\tBook 1: " + book1);
        // test copy concstructor
        Book book2 = new Book(book1);
        
        System.out.println("\n2. Testing accessors and mutators:");
        // test getters
        System.out.println("\tBook 1 title: " + book1.getTitle());
        System.out.println("\tBook 1 author: " + book1.getAuthor());
        System.out.println("\tBook 1 year published : " + book1.getYear());
        System.out.println("\tBook 1 number of pages: " + book1.getPages());
        // test setters   
        book2.setTitle("The Catcher in the Rye");
        book2.setAuthor("J. D. Salinger");
        book2.setYear(1951);
        book2.setPages(214);
        System.out.println("\tBook 2: " + book2);
        
        System.out.println("\n3. Testing comparison methods:");
        System.out.println("\tBook 1 and Book 2 are equal: " + book1.equals(book2));
        System.out.println("\tBook 1 is older than Book 2: " + book1.olderBook(book2));
        System.out.println("\tBook 1 and Book 2 have same author: " + book1.sameAuthor(book2));           
        
        System.out.println("\n4. Testing borrow and return methods:");
        String student = "Israel";
        Date borrowDate = new Date(4,  2, 2018);
        Date returnDate = new Date(10, 3, 2018);        
        Date todaysDate = new Date(15, 3, 2018);
        book2.borrowBook(student, borrowDate);
        System.out.println("\tBook 2 is borrowed: " + book2.getBorrowed());        
        System.out.println("\tBook 2 was borrowed by student: " + book2.getStudentName());
        System.out.println("\tBook 2 borrowed date: " + book2.getBorrowedDate());
        System.out.print("\tToday's date is: " + todaysDate);
        System.out.println(", Book 2 is borrowed for " + book2.howLongBorrowed(todaysDate) + " days");
        System.out.println("\tBook 2 is available: " + book2.isAvailable(todaysDate));        
        System.out.println("\tBook 2 penalty: " + book2.computePenalty(returnDate) + " NIS");
        book2.returnBook(returnDate);
        System.out.println("\tBook 2 return date: " + book2.getReturnDate());
        System.out.println("\n********** Test Book - Finished **********\n");
    }
}
