/**
 * This class represents a Date object.
 *
 * Author: Daniel Litvak
 * Date: 1.4.2018
 */
public class Date
{
    private int _day;
    private int _month;
    private int _year;

    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;
    private final int MAX_DAY = 31;
    private final int MAX_MONTH = 12;
    private final int MAX_YEAR = 9999;
    private final int MIN_DAY = 1;
    private final int MIN_MONTH = 1;
    private final int MIN_YEAR = 1000;
    // Constructors

    /**
     * Creates a new Date object if the date is valid, otherwise creates the date 1/1/2000.
     *
     * @param day   the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year  the year (4 digits)
     */
    public Date(int day, int month, int year)
    {
        _day = day;
        _month = month;
        _year = year;

        // If the date is invalid set the date to 1/1/2000
        if (!isValid(this))
        {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    /**
     * Copy constructor.
     *
     * @param other the date to be copied
     */
    public Date(Date other)
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    /**
     * Check if 2 dates are the same.
     *
     * @param other the date to compare this date to
     * @return true if the dates are the same
     */
    public boolean equals(Date other)
    {
        // Return true if all of the attributes are the same
        return (_year == other._year) && (_month == other._month) &&
                (_day == other._day);
    }

    /**
     * Check if this date is before other date.
     *
     * @param other the date to compare to
     * @return true if this date is before other date
     */
    public boolean before(Date other)
    {
        if (_year < other._year)
        {
            return true;
        }
        // Continue checking if the years are the same
        else if (_year == other._year)
        {
            if (_month < other._month)
            {
                return true;
            }
            // Continue checking if the months are the same
            else if (_month == other._month)
            {
                if (_day < other._day)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if this date is after other date.
     *
     * @param other the date to compare to
     * @return true if this date is after other date
     */
    public boolean after(Date other)
    {
        // If the other is before this date it means that this date
        // is after the compare date
        return other.before(this);
    }

    /**
     * Calculates the difference in days between two dates.
     *
     * @param other the date to calculate the difference between
     * @return the number of days between the dates
     */
    public int difference(Date other)
    {
        return Math.abs(calculateDate(_day, _month, _year) -
                calculateDate(other._day, other._month, other._year));
    }

    /**
     * Returns a String that represents this date.
     *
     * @return String that represents this date in the following format:
     * day/month/year for example: 2/3/1998
     */
    public String toString()
    {
        return _day + "/" + _month + "/" + _year;
    }

    /**
     * Calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc.
     *
     * @return the day of the week that this date occurs on
     */
    public int dayInWeek()
    {
        int d = _day;
        int m = _month;
        int year = _year;

        // The formula deals with January and February as 13 and 14 of the previous year
        if (m < 3)
            m = m + 2;
        year--;
        // Last 2 digits of year
        int y = year % 100;
        int c = first2Digits(year);

        // Using a given formula to calculate the day in week
        return Math.floorMod((d + (26 * (m + 1)) / 10 + y + y / 4 + c / 4 - 2 * c), 7);
    }

    // Getters

    /**
     * Gets the day.
     *
     * @return the day
     */
    public int getDay()
    {
        return _day;
    }

    /**
     * Gets the month.
     *
     * @return the month
     */
    public int getMonth()
    {
        return _month;
    }

    /**
     * Gets the year.
     *
     * @return the year
     */
    public int getYear()
    {
        return _year;
    }

    // Setters

    /**
     * Sets the day (only if date remains valid).
     *
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet)
    {
        // Keep previous day in case the new date is invalid
        int previousDay = _day;

        _day = dayToSet;

        // If the new date is invalid undo the change
        if (!isValid(this))
        {
            _day = previousDay;
        }
    }

    /**
     * Set the month (only if date remains valid).
     *
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet)
    {
        // Keep previous month in case the new date is invalid
        int previuosMonth = _month;

        _month = monthToSet;

        // If the new date is invalid undo the change
        if (!isValid(this))
        {
            _month = previuosMonth;
        }
    }

    /**
     * Sets the year (only if date remains valid).
     *
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet)
    {
        // Keep previous year in case the new date is invalid
        int previuosYear = _year;

        _year = yearToSet;

        // If the new date is invalid undo the change
        if (!isValid(this))
        {
            _year = previuosYear;
        }
    }

    // Returns the first 2 digits
    private int first2Digits(int year)
    {
        // If the year is more than 2 digits drop the last digit until
        // there are no more than 2 digits
        while (year >= 100)
        {
            year = year / 10;
        }
        // Return the year after it is only the first 2 digits
        return year;
    }

    // Checks the validity of the date
    private boolean isValid(Date date)
    {
        // Year must be positive and only 4 digits
        if (date._year < MIN_YEAR || date._year > MAX_YEAR)
        {
            return false;
        }
        // Month can only be between 1 to 12
        if (date._month < MIN_MONTH || date._month > MAX_MONTH)
        {
            return false;
        }
        // Day can be only between 1 to 31
        if (date._day < MIN_DAY || date._day > MAX_DAY)
        {
            return false;
        }

        // check if the day fits the month and year
        return isValidDay(date);
    }

    // Checks the validity of the day according to the year and month
    private boolean isValidDay(Date date)
    {
        int daysInMonth = 0;
        switch (date._month)
        {
            case 2:
                // February might have 28 or 29 days
                if (isLeapYear(date._year))
                {
                    daysInMonth = 29;
                }
                else
                {
                    daysInMonth = 28;
                }
                break;
            case 4:
                // April has 30 days
                daysInMonth = 30;
                break;
            case 6:
                // June has 30 days
                daysInMonth = 30;
                break;
            case 9:
                // September has 30 days
                daysInMonth = 30;
                break;
            case 11:
                // November has 30 days
                daysInMonth = 30;
                break;
            default:
                // 31 days in all the other months
                daysInMonth = 31;
                break;
        }

        // Return true if the day is within the number of days in the month
        return date._day <= daysInMonth;
    }

    // Check if leap year
    private boolean isLeapYear(int year)
    {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day, int month, int year)
    {
        if (month < 3)
        {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }
}