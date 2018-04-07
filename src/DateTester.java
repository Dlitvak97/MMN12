public class DateTester
{
    public static void main(String[] args)
    {
        test2();
    }

    public static void test1()
    {
        System.out.println("********** Test Date - Started **********");
        System.out.println("\n1. Testing Constructors and toString:");
        Date d1 = new Date(3, 5, 1998);
        System.out.println("\td1:" + d1);
        Date d2 = new Date(d1);
        System.out.println("\td2:" + d2);
        System.out.println("\n2. Testing accessors and mutators:");
        d1.setDay(8);
        d1.setMonth(10);
        d1.setYear(2016);
        System.out.println("\td1:" + d1);
        System.out.println("\tday of d1:" + d1.getDay());
        System.out.println("\tmonth of d1:" + d1.getMonth());
        System.out.println("\tyear of d1:" + d1.getYear());
        System.out.println("\n3. Testing equals method:");
        Date d3 = new Date(3, 5, 1998);
        Date d4 = new Date(3, 5, 1998);
        System.out.println("\td3:" + d3);
        System.out.println("\td4:" + d4);
        if (d3.equals(d4))
            System.out.println("\td3 is the same date as d4");
        else
            System.out.println("\td3 isn't the same date as d4");
        System.out.println("\n4. Testing before method:");
        if (d3.before(d1))
            System.out.println("\td3 is before d1");
        else
            System.out.println("\td3 isn't before d1");
        System.out.println("\n5. Testing after method:");
        if (d3.after(d1))
            System.out.println("\td3 is after d1");
        else
            System.out.println("\td3 isn't after d1");
        System.out.println("\n6. Testing difference method:");
        System.out.println("\tThe difference in days between dates d1 and d3 is : " + d1.difference(d3));
        System.out.println("\n7. Testing dayInWeek method:");
        Date d5 = new Date(6, 11, 2016);
        System.out.println("\t" + d5 + " occurs on : " + d5.dayInWeek());

        System.out.println("\n********** Test Date - Finished **********\n");
    }

    public static void test2()
    {
        System.out.println("********** Test2 Date - Started **********");
        System.out.println("1. testing constructor handling wrong dates");
        Date d1 = new Date(3, 5, 19988);
        System.out.println("\td1:" + d1);
        Date d2 = new Date(3, 13, 1998);
        System.out.println("\td2:" + d2);
        Date d3 = new Date(3, 5, 999);
        System.out.println("\td3:" + d3);
        Date d4 = new Date(3, 13, 0);
        System.out.println("\td4:" + d4);
        Date d5 = new Date(3, 0, 2000);
        System.out.println("\td5:" + d5);
        Date d6 = new Date(3, 12, 1420);
        System.out.println("\td6:" + d6);
        Date d7 = new Date(31, 12, 1420);
        System.out.println("\td7:" + d7);
        Date d8 = new Date(31, 4, 1420);
        System.out.println("\td8:" + d8);
        Date d9 = new Date(30, 4, 1420);
        System.out.println("\td9:" + d9);
        Date d10 = new Date(0, 12, 1420);
        System.out.println("\td10:" + d10);
        Date d11 = new Date(29, 2, 1420);
        System.out.println("\td11:" + d11);
        Date d12 = new Date(28, 2, 1420);
        System.out.println("\td12:" + d12);
        Date d13 = new Date(29, 2, 1421);
        System.out.println("\td13:" + d13);
        Date d14 = new Date(29, 2, 1000);
        System.out.println("\td14:" + d14);
        Date d15 = new Date(29, 2, 1200);
        System.out.println("\td15:" + d15);
        Date d16 = new Date(31, 12, 9999);
        System.out.println("\td16:" + d16);
        Date d17 = new Date(1, 1, 1000);
        System.out.println("\td17:" + d17);
        System.out.println("\n2. Testing accessors and mutators:");
        d1.setDay(1);
        d1.setMonth(1);
        d1.setYear(3900);
        System.out.println("\td1:" + d1);
        System.out.println("\tday of d1:" + d1.getDay());
        System.out.println("\tmonth of d1:" + d1.getMonth());
        System.out.println("\tyear of d1:" + d1.getYear());
        System.out.println("\n2. Testing methods:");
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());
        d1.setDay(3);
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());
        d1.setDay(5);
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());
        d1.setDay(30);
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());
        d1.setDay(1);
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());
        d1.setMonth(5);
        System.out.println("\td1:" + d1 + " - day in week is: " + d1.dayInWeek());

    }
}
    