import java.util.Scanner;
public class SafeInput
{

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLengthString(Scanner pipe, String prompt)
    {
        String retValue = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retValue = pipe.nextLine();

            if(retValue.length() > 0) {
                done = true;
            }
            else {
                System.out.println("You must enter a few characters!");
            }
        }while(!done);

        return retValue;
    }

    /**
     *
     * @param pipe Scanner to read input
     * @param prompt msg should not include range info
     * @return a valid int with no constraints
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        boolean done = false;
        int retValue = 0;
        String trash = "";

        do {

            System.out.print("\n" + prompt + ": ");

            if(pipe.hasNextInt()) {
              retValue = pipe.nextInt();
              pipe.nextLine();
              done = true;
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    /**
     *
     *
     * @param pipe Scanner to use for input
     * @param prompt Prompt for the user to know what to input
     * @return a valid int with no constraints
     */
    public static double getDouble(Scanner pipe, String prompt)
    {
        boolean done = false;
        double retValue = 0;
        String trash = "";

        do {

            System.out.print("\n" + prompt + ": ");

            if(pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    /**
     *
     * @param pipe input
     * @param prompt prompt for input
     * @param low low value in range
     * @param high high value in range
     * @return valid int with no constraints
     */

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        boolean done = false;
        int retValue = 0;
        String trash = "";

        do {

            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");

            if(pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                if(retValue >= low && retValue <= high) {
                    done = true;
                }
                else {
                    System.out.println("You must enter a value in the range!");
                }
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid int [" + low + " - " + high + "] not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    /**
     * @param pipe input
     * @param prompt prompt for input
     * @param low low value in range
     * @param high high value in range
     * @return valid int with no constraints
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        boolean done = false;
        double retValue = 0;
        String trash = "";

        do {

            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");

            if(pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                if(retValue >= low && retValue <= high) {
                    done = true;
                }
                else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "] not: " + retValue);
                }
            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double [" + low + " - " + high + "] not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    /**
     *
     * @param pipe input
     * @param prompt prompt for
     * @return int with no constraints
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String value = "";
        boolean retValue = false;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[Y/N]: ");
            value = pipe.nextLine();

            if(value.equalsIgnoreCase("Y")) {
                retValue = true;
                done = true;
            }
            else if (value.equalsIgnoreCase("N")){
                retValue = false;
                done = true;
            }
            else {
                System.out.println("You must enter a Y or N not: " + value);
            }
        }while(!done);

        return retValue;
    }


    /**
     *
     * @param pipe input
     * @param prompt prompt for input
     * @param regEx the pattern to match
     * @return valid int with no constraints
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String retValue = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " " + regEx + ": ");
            retValue = pipe.nextLine();

            if(retValue.matches(regEx)) {
                done = true;
            }
            else {
                System.out.println("You must enter a String that matches the pattern: " + regEx);
            }

        }while (!done);

        return retValue;
    }
} 