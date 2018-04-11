/**
 * This class represents a Book object.
 *
 * Author: Daniel Litvak
 * Date: 1.4.2018
 */
public class Book
{
    private String _title;
    private String _author;
    private int _yearPublished;
    private int _noOfPages;
    private boolean _borrowed;
    private String _studentName;
    private Date _borrowDate;
    private Date _returnDate;

    private final int MAX_BORROW_DAYS = 30;
    private final int DOUBLE_PENALTY_DAYS = 60;
    private final int PENALTY_PER_DAY = 5;
    private final int MAX_YEAR_PUBLISHED = 2018;
    private final int MIN_YEAR_PUBLISHED = 1800;
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_PAGES = 1;
    private final int MIN_PAGES = 1;

    // Constructors

    /**
     * Constructs a new Book object. If year is not Valid it will be set to default 2000.
     * If number of pages in not valid it will be set to default 1. _borrowed field is set to false.
     * All other field is set to null.
     *
     * @param title         book title
     * @param author        book author
     * @param yearPublished the year the book was published
     * @param noOfPages     book number of pages
     */
    public Book(String title, String author, int yearPublished, int noOfPages)
    {
        if (yearPublished < MIN_YEAR_PUBLISHED || _yearPublished > MAX_YEAR_PUBLISHED)
            yearPublished = DEFAULT_YEAR;
        if (noOfPages < MIN_PAGES)
            noOfPages = DEFAULT_PAGES;


        _title = title;
        _author = author;
        _yearPublished = yearPublished;
        _noOfPages = noOfPages;
        _borrowed = false;
        _borrowDate = null;
        _returnDate = null;
        _studentName = null;

    }

    /**
     * Copy constructor for Book object.
     *
     * @param other book to be copied
     */
    public Book(Book other)
    {
        if (other != null)
        {
            _title = other._title;
            _author = other._author;
            _yearPublished = other._yearPublished;
            _noOfPages = other._noOfPages;
            _borrowed = other._borrowed;
            _studentName = other._studentName;

            // Checking if there is a borrow date
            if (other._borrowDate != null)
            {
                // Create a copy to avoid aliasing
                _borrowDate = new Date(other._borrowDate);
            }
            else
            {
                _borrowDate = null;
            }
            // Checking if there is a return date
            if (other._returnDate != null)
            {
                // Create a copy to avoid aliasing
                _returnDate = new Date(other._returnDate);
            }
            else
            {
                _returnDate = null;
            }
        }
    }

    /**
     * Checks if this book equals to other book.
     *
     * @param other the book to compare this book to
     * @return true if this book and other are the same; false otherwise
     */
    public boolean equals(Book other)
    {
        return (_title.equals(other._title) && _author.equals(other._author) &&
                _yearPublished == other._yearPublished && _noOfPages == other._noOfPages);
    }

    /**
     * Returns a string representation of this book.
     *
     * @return representation of this book in the following format, for example,
     * Title: Pride and Prejudice Author: Jane Austen Year: 1813, 350 pages
     */
    public String toString()
    {
        return "Title: " + _title + "\tAuthor: " + _author + "\tYear: " +
                _yearPublished + ", " + _noOfPages + " pages";
    }

    /**
     * Checks if this book is older than other book.
     *
     * @param other the book to compare to
     * @return true if this book is older than other book; false otherwise
     */
    public boolean olderBook(Book other)
    {
        return this._yearPublished < other._yearPublished;
    }

    /**
     * Checks if this book and other book have the same author.
     *
     * @param other the book to compare to
     * @return true if this book and other book have the same author; false otherwise
     */
    public boolean sameAuthor(Book other)
    {
        return this._author.equals(other._author);
    }

    /**
     * Gets student name and borrow date and updates the appropriate book attributes.
     *
     * @param name the student name
     * @param d    borrow date
     */
    public void borrowBook(String name, Date d)
    {
        if (!_borrowed)
        {
            _studentName = name;
            _borrowDate = new Date(d);
            _borrowed = true;
        }
    }

    /**
     * Gets return date and updates the appropriate book attributes
     *
     * @param d return date
     * @return true if student is late or the book is not borrowed; false otherwise
     */
    public boolean returnBook(Date d)
    {
        if (_borrowed)
        {
            boolean isLate = false;
            // Check if the book is returned late
            if (howLongBorrowed(d) > 30)
                isLate = true;

            // Update return date, remove student name, change borrow status
            _returnDate = new Date(d);
            _studentName = null;
            _borrowed = false;
            return isLate;
        }
        return true;
    }

    /**
     * Gets today's date and if book is borrowed returns how many days the book is borrowed;
     * otherwise returns 0.
     *
     * @param d today's date
     * @return how many days the book is borrowed
     */
    public int howLongBorrowed(Date d)
    {

        if (_borrowed)
        {
            // if today's date is before the borrow date return 0
            if (d.before(_borrowDate))
            {
                return 0;
            }
            return d.difference(_borrowDate);
        }
        return 0;
    }

    /**
     * Checks if the book is available
     *
     * @param d today's date
     * @return false if the book is borrowed; otherwise if the book is not borrowed return
     * false if today's day is Friday or Saturday; otherwise return true
     */
    public boolean isAvailable(Date d)
    {
        if (_borrowed)
        {
            return false;
        }
        int dayInWeek = d.dayInWeek();
        if (dayInWeek == 0 || dayInWeek == 6)
        {
            return false;
        }
        return true;
    }

    /**
     * Computes penalty given return date.
     *
     * @param d return date
     * @return penalty if book is borrowed and student is late; 0 otherwise
     */
    public int computePenalty(Date d)
    {
        if (_borrowed)
        {
            int daysBorrowed = howLongBorrowed(d);

            if (daysBorrowed < MAX_BORROW_DAYS)
                return 0;

            if (daysBorrowed < DOUBLE_PENALTY_DAYS)
                return (PENALTY_PER_DAY * (daysBorrowed - MAX_BORROW_DAYS));

            return (PENALTY_PER_DAY * (daysBorrowed - MAX_BORROW_DAYS)) +
                    (2 * PENALTY_PER_DAY * (daysBorrowed - DOUBLE_PENALTY_DAYS));
        }
        return 0;
    }

    // Getters

    /**
     * Returns the book title.
     *
     * @return the book title
     */
    public String getTitle()
    {
        return _title;
    }

    /**
     * Returns the book author.
     *
     * @return the book author
     */
    public String getAuthor()
    {
        return _author;
    }

    /**
     * Returns the year the book was published.
     *
     * @return the year the book was published
     */
    public int getYear()
    {
        return _yearPublished;
    }

    /**
     * Returns the book number of pages.
     *
     * @return the book number of pages
     */
    public int getPages()
    {
        return _noOfPages;
    }

    /**
     * Returns true if the book is borrowed; false otherwise.
     *
     * @return true if the book is borrowed; false otherwise
     */
    public boolean getBorrowed()
    {
        return _borrowed;
    }

    /**
     * Returns the student name.
     *
     * @return the student name
     */
    public String getStudentName()
    {
        return _studentName;
    }

    /**
     * Returns the book borrowed date.
     *
     * @return the book borrowed date
     */
    public Date getBorrowedDate()
    {
        return new Date(_borrowDate);
    }

    /**
     * Returns the book return date.
     *
     * @return the book return date
     */
    public Date getReturnDate()
    {
        return new Date(_returnDate);
    }

    // Setters

    /**
     * Sets the book title.
     *
     * @param s the new book title String to be set
     */
    public void setTitle(String s)
    {
        _title = s;
    }

    /**
     * Sets the book author.
     *
     * @param s the new book author to be set
     */
    public void setAuthor(String s)
    {
        _author = s;
    }

    /**
     * Sets the year the book was published (only if valid).
     *
     * @param n the book published year to be set
     */
    public void setYear(int n)
    {
        if (n < 2018 && n > 1800)
            _yearPublished = n;
    }

    /**
     * Sets the book number of pages (only if valid).
     *
     * @param n the number of pages to be set
     */
    public void setPages(int n)
    {
        if (n > 0)
            _noOfPages = n;
    }
}