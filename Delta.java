//importing essential classes for taking user input

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Delta { //creating class named as Delta
    static BufferedReader br;// Static variable created to take input from other methods and static main method
    String[] name = new String[100];// to store of names of students
    byte[] roll = new byte[100];// to store roll numbers of students
    double[][] marks = new double[100][3];// to store marks of each students' marks of 3 subjects respectively
    double[] avg = new double[100];// to store average of each student
    char[] grade = new char[100];// to store grade scored by students
    int index = 0;// to store index of each students' detail
    int length;// to store number of students registered
    boolean check_exception;// to prevent the existence of exceptions
    //below are variable for running while() loops and certain condition
    boolean b;//to run first while() loop
    boolean bb;//to run while() loop in a while() loop
    boolean bbb;//to run while() loop in a while() loop which is in a while() loop or certain condition

    Delta() {// a default constructor - to create object of BufferedReader & to initialize the roll[]
        br = new BufferedReader(new InputStreamReader(System.in));// object of BufferedReader is being created and its reference is being stored
        // initialization of roll[]
        for (int j = 0; j < 100; j++)
            this.roll[j] = (byte) (j + 1);
    }

    void setter() throws Exception {// to take input from user and store it in respective arrays
        // initialization of arrays by taking inputs
        for (int j = index; j < 100; j++, index++) {
            System.out.println("Enter exit to cancel taking input while entering name!");
            System.out.print("Enter name: ");

            // checking exception and storing it local variable
            String c = check_exception(br.readLine().trim());// variable c is a local variable created to check whether the user want to exit or not
            // to confirm exit to user
            if (c.equalsIgnoreCase("Exit")) {
                System.out.println("Registration done!");
                length = index; // storing number of students
                break;// getting out of input loop
            }
            name[j] = c;// storing names entered by user to name[]
            // checking marks entered by user and storing at respective indexes
            System.out.print("Enter Physics marks: ");
            marks[j][0] = check_exception(br.readLine(), (double) 0);
            System.out.print("Enter Chemistry marks: ");
            marks[j][1] = check_exception(br.readLine(), (double) 0);
            System.out.print("Enter Maths marks: ");
            marks[j][2] = check_exception(br.readLine(), (double) 0);
            System.out.println();
        }
        sort();// arranging details of student & updating arrays according to alphabets in entered name
    }

    private void sort() {// for arranging details entered according to alphabets in names entered by user
        String[] name_copy = new String[length];//to store exact copy of name[] for sorting
        for (int j = 0; j < length; j++)//loop to copy array
            name_copy[j] = name[j];
        for (int j = 0; j < length; j++)//loop to remove all spaces from all elements
            name_copy[j] = name_copy[j].replace(" ", "");
        for (int j = 0; j < name_copy.length; j++)//loop to convert all copied names to lower case
            name_copy[j] = name_copy[j].toLowerCase();
        String secure;//to secure name
        double secure_marks, secure_avg;//to secure average and marks
        char secure_grade;//to secure grade
        int min = 0;//to store length of shortest name
        for (int j = 1; j < name_copy.length; j++) {//loop storing length of shortest name
            min = Math.min(name_copy[j].length(), name_copy[j - 1].length());
        }
        //using bubble sort to sort names
        for (int j = 0; j < name_copy.length; j++) {
            for (int k = 0; j < name_copy.length - 1 - k; j++) {
                for (int l = 0; l < min; )
                    if (name_copy[k].charAt(k) > name_copy[k + 1].charAt(k)) {//swap details if the selected character of same index of adjacent names are not at sorted
                        secure = name_copy[k];
                        name_copy[k] = name_copy[k + 1];
                        name_copy[k + 1] = secure;
                        secure = name[k];
                        name[k] = name[k + 1];
                        name[k + 1] = secure;
                        secure_marks = marks[k][0];
                        marks[k][0] = marks[k + 1][0];
                        marks[k + 1][0] = secure_marks;
                        secure_marks = marks[k][1];
                        marks[k][1] = marks[k + 1][1];
                        marks[k + 1][1] = secure_marks;
                        secure_marks = marks[k][2];
                        marks[k][2] = marks[k + 1][2];
                        marks[k + 1][2] = secure_marks;
                        secure_avg = avg[k];
                        avg[k] = avg[k + 1];
                        avg[k + 1] = secure_avg;
                        secure_grade = grade[k];
                        grade[k] = grade[k + 1];
                        grade[k + 1] = secure_grade;

                    } else if (name_copy[k].charAt(k) == name_copy[k + 1].charAt(k))//move to next index if the selected character of same index of adjacent names are same
                        l++;
                    else//do not swap is Characters are already sorted
                        break;
            }
        }
    }

    void searchMenu() throws Exception {//to search for details of student by roll, name, marks or grade
        if (length == 0)//asks user to enter data if list is empty
            System.out.println("Enter data first!\nThe list is empty.");
        else {//continue to search if list is not empty
            b = true;
            while (b) {//loop to enter choice again and again if invalid choice is entered
                String cancel;
                byte i;//variable to store choice
                bb = true;
                //print statement to ask user the way to search
                System.out.println("Enter the way you want to search:-");
                System.out.println("1. Search by roll number");
                System.out.println("2. Search by name");
                System.out.println("3. Search by marks");
                System.out.println("4. Search by grade");
                System.out.println("5. Cancel searching");
                System.out.print("Enter choice: ");
                i = check_exception(br.readLine().trim(), (byte) 0);//store choice after checking exception and logical errors
                switch (i) {// to check which choice the user chose & respond accordingly
                    case 1://to search student by its roll number
                        while (true) {//loop to enter roll again and again until a valid roll is entered
                            System.out.print("Enter roll to search: ");
                            byte roll = check_exception(br.readLine().trim(), (byte) 0);//store roll after checking for exceptions and logical errors
                            for (int j = 0; j < length; j++)//loop to check each roll in roll[]
                                if (this.roll[j] == roll) {//check if the roll entered is assigned to a student or not
                                    //print details
                                    System.out.println(this.roll[j] + ".\t" + this.name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    bb = false;//to exit the while() loop for choices
                                    break;//exit for() loop if roll is matched with a student
                                }
                            if (bb) {
                                //print statement if roll is not assigned to any student
                                System.out.println("The roll number you searched for is not found");
                                //print statement to ask user if he/she wants to cancel searching or not
                                System.out.print("To cancel searching enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of searchMenu() if cancel if entered
                                    return;
                            } else//get the compiler out of while loop if roll entered is matched
                                break;
                        }
                        b = false;//get the compiler out of while loop for choices
                        break;
                    case 2://to search student by its name
                        while (true) {//loop to input name again and again until a name is entered whose name matches with any student
                            String[] name_copy = new String[length];//to store exact copy of name[] for searching
                            for (int j = 0; j < length; j++)//loop to convert all copied names to upper case
                                name_copy[j] = this.name[j].toUpperCase();
                            System.out.print("Enter new name: ");
                            String part = check_exception(br.readLine().trim());//to store name for searching after checking for logical errors
                            //print statement to print details of student with matching name
                            System.out.println("Below are the details of students with matching name:-");
                            for (int j = 0; j < length; j++) {//loop to check each name
                                if (name_copy[j].indexOf(part) >= 0) {//check if name entered matches with any name in name[]
                                    System.out.println("Below is the data you searched for");
                                    System.out.println(this.roll[j] + ".\t" + name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    bb = false;
                                }
                            }
                            if (bb) {//to check that any student's name matches with the name entered
                                System.out.println("No one in the list have the given name.");
                                //print statement to ask user if he/she wants to cancel searching or not
                                System.out.print("To cancel searching enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of searchMenu() if cancel if entered
                                    return;
                            } else//get the compiler out of while loop if roll entered is matched
                                break;
                        }
                        b = false;//get the compiler out of while loop for choices
                        break;
                    case 3://to search student by its marks in any three subjects
                        while (true) {//loop to enter marks again and again until a name is entered whose name matches with any student
                            System.out.print("Enter marks: ");
                            double marks = check_exception(br.readLine(), (double) 0);//to store marks for searching after checking for exceptions and logical errors
                            for (int j = 0; j < length; j++)//loop to check each marks of each subject
                                if (this.marks[j][0] == marks || this.marks[j][1] == marks || this.marks[j][2] == marks) {//check if marks entered matches with any marks
                                    System.out.println("Below is the data you searched for");
                                    System.out.println(this.roll[j] + ".\t" + name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    bb = false;
                                }
                            if (bb) {//to check that any student's name matches with the name entered
                                System.out.println("No one scored the marks you entered.");
                                System.out.print("To cancel searching enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of searchMenu() if cancel if entered
                                    return;
                            } else//get the compiler out of while loop if roll entered is matched
                                break;
                        }
                        b = false;//get the compiler out of while loop for choices
                        break;
                    case 4://to search student by its grade
                        while (true) {//loop to enter grade again and again until a name is entered whose name matches with any student
                            System.out.print("Enter grade you want to search for: ");
                            char c = check_exception(br.readLine(), ' ');//to store marks for searching after checking for exceptions and logical errors
                            for (int j = 0; j < length; j++)//loop to check each marks of each subject
                                if (grade[j] == c) {//check if grade entered matches with any grade in grade[]
                                    System.out.println("Below is the data you searched for");
                                    System.out.println(this.roll[j] + ".\t" + name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    bb = false;
                                }
                            if (bb) {//to check that any student's name matches with the name entered
                                System.out.println("No one scored the grade you entered");
                                System.out.print("To cancel searching enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of searchMenu() if cancel if entered
                                    return;
                            } else//get the compiler out of while loop if roll entered is matched
                                break;
                        }
                        b = false;//get the compiler out of while loop for choices
                        break;
                    case 5://to get out of searchMenu() if user do not want to perform any action in searchMenu()
                        return;//get the compiler out of searchMenu()
                    default:
                        //print statement for invalid choice
                        System.out.println("Enter only number from 1 to 5\nEnter again");
                }
            }
        }
    }

    /**/
    void deleter() throws Exception {//this function is used to delete all data or to remove a student from the list along with his/her details by entering roll, name, marks, grade
        if (length == 0)//asks user to enter data if list is empty
            System.out.println("Enter data first!\nThe list is empty.");
        else {//continue to search if list is not empty
            b = true;
            while (b) {//loop to enter choice again and again if invalid choice is entered
                String cancel;
                int i;//variable to store choice
                //print statement to ask user the way to delete
                System.out.println("Enter the way you want to delete:-");
                System.out.println("1. Delete by roll number");
                System.out.println("2. Delete by name");
                System.out.println("3. Delete by marks");
                System.out.println("4. Delete by grade");
                System.out.println("5. Delete all data");
                System.out.println("6. Cancel deleting");
                System.out.print("Enter: ");
                i = check_exception(br.readLine().trim(), (byte) 0);//store choice after checking exception and logical errors
                byte roll = -1;//to store roll entered
                byte cnt;// to store total number of selected student
                byte[] selected;//to store roll of selected student
                switch (i) {// to check which choice the user chose & respond accordingly
                    case 1://to delete student by its roll number
                        while (true) {//loop to enter roll again and again until a valid roll is entered
                            System.out.print("Enter roll: ");
                            roll = check_exception(br.readLine().trim(), (byte) 0);//store roll after checking for exceptions and logical errors
                            roll = confirm_delete(roll);//to confirm that the user want to delete the student's details whose roll is entered
                            if (roll == -128)//check whether the user confirm to delete or not
                                return;//if user confirms to delete the return statement will get the compiler out of deleter()
                            if (roll >= 0 && roll <= length) {//check if roll entered is assigned to a student or not
                                if (roll != length) {//check if the roll entered is not the last roll in the list
                                    for (int j = 0; j < length; j++) {//loop to check each roll in roll[]
                                        if (roll == this.roll[j]) {//search for the roll in the list which is entered
                                            for (int k = roll - 1; k < length; k++) {//loop to shift the details of students below roll up from the list by one
                                                this.name[k] = this.name[k + 1];
                                                this.marks[k][0] = this.marks[k + 1][0];
                                                this.marks[k][1] = this.marks[k + 1][1];
                                                this.marks[k][2] = this.marks[k + 1][2];
                                                this.avg[k] = this.avg[k + 1];
                                                this.grade[k] = this.grade[k + 1];
                                            }
                                        }
                                    }
                                }
                                length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                                index--;//decreasing the index of students by 1
                                break;//exit while() loop if roll is matched with a student
                            } else {
                                //print statement if roll entered is not assigned to any student
                                System.out.println("Invalid roll entered!");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                            }
                        }
                        b = false;//get the compiler out of while loop for choices
                        show();//print the updated list
                        break;
                    case 2://to delete student by its name
                        String[] name_copy = new String[length];//to make exact copy of name[]
                        selected = new byte[length];
                        cnt = 0;
                        bb = true;
                        for (int j = 0; j < length; j++)//copying name[]
                            name_copy[j] = this.name[j].toUpperCase();//storing uppercase form of name[]
                        while (bb) {//loop to input name again and again until a name is entered whose name matches with any student
                            System.out.print("Enter name: ");
                            String part = check_exception(br.readLine().trim()).toUpperCase();//to store name for deleting after checking for logical errors
                            //print statement to print details of student with matching name
                            System.out.println("Below are the details of students with matching name:-");
                            for (int j = 0; j < length; j++) {//loop to check each name
                                if (name_copy[j].indexOf(part) >= 0) {//check if name entered matches with any name in name[]
                                    System.out.println(this.roll[j] + ".\t" + this.name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    selected[cnt] = this.roll[j];
                                    cnt++;
                                    bb = false;
                                }
                            }
                            if (bb) {//to check that any student's name matches with the name entered
                                System.out.println("No one in the list have the given name.\nEnter again");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                            }
                        }
                        System.out.println("From the above given list enter the roll of the student whose details you want to delete");
                        bb = true;
                        while (bb) {//loop to enter roll again and again until a valid roll is entered
                            bb = false;
                            System.out.print("Enter roll: ");
                            roll = check_exception(br.readLine().trim(), (byte) 0);//store roll after checking for exceptions and logical errors
                            roll = confirm_delete(roll);//to confirm that the user want to delete the student's details whose roll is entered
                            if (roll == -128)//check whether the user confirm to delete or not
                                return;//if user confirms to delete the return statement will get the compiler out of deleter()
                            for (int j = 0; j < cnt; j++) {//loop to check the roll entered is in selected[] one by one
                                if (roll == selected[j]) {//to check the roll entered is in selected[]
                                    bb = false;//to access the print statement for invalid roll
                                    break;//to get out of for() loop if roll entered is in selected[]
                                }
                            }
                            if (bb) {
                                //print statement if roll entered is not of any selected student
                                System.out.println("The roll entered is not given above, enter again");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                                bb = true;//to continue while(bbb) loop
                            }
                        }
                        if (roll != length) {//check if the roll entered is not the last roll in the list
                            for (int j = 0; j < length; j++) {//loop to check each roll in roll[]
                                if (roll == this.roll[j]) {//search for the roll in the list which is entered
                                    for (int k = roll - 1; k < length; k++) {//loop to shift the details of students below roll up from the list by one
                                        this.name[k] = this.name[k + 1];
                                        this.marks[k][0] = this.marks[k + 1][0];
                                        marks[k][1] = this.marks[k + 1][1];
                                        this.marks[k][2] = this.marks[k + 1][2];
                                        this.avg[k] = this.avg[k + 1];
                                        this.grade[k] = this.grade[k + 1];
                                        length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                                        index--;//decreasing the index of students by 1
                                    }
                                }
                            }
                        }
                        else{
                            length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                            index--;//decreasing the index of students by 1
                        }
                        b = false;//get the compiler out of while loop for choices
                        show();//print the updated list
                        break;
                    case 3://to delete student by its marks
                        bb = true;
                        cnt = 0;
                        selected = new byte[length];
                        while (bb) {//loop to input marks again and again until a marks is entered which matches with any student's marks
                            System.out.print("Enter marks: ");
                            double marks = check_exception(br.readLine(), (double) 0);//to store marks for deleting after checking for logical errors
                            //print statement to print details of student with matching name
                            System.out.println("Below are the details of students with matching name:-");
                            for (int j = 0; j < length; j++)//loop to check each student's marks
                                if (this.marks[j][0] == marks || this.marks[j][1] == marks || this.marks[j][2] == marks) {//check if name entered matches with any name in name[]
                                    System.out.println(this.roll[j] + ".\t" + this.name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    selected[cnt] = this.roll[j];
                                    cnt++;
                                    bb = false;
                                }
                            if (bb) {//to check that any student's marks matches with the marks entered
                                System.out.println("No one scored the marks you entered.");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                            }
                        }
                        System.out.println("From the above given list enter the roll of the student whose details you want to delete");
                        bb = true;
                        while (bb) {//loop to enter roll again and again until a valid roll is entered
                            bb = false;
                            System.out.print("Enter roll: ");
                            roll = check_exception(br.readLine().trim(), (byte) 0);//store roll after checking for exceptions and logical errors
                            roll = confirm_delete(roll);//to confirm that the user want to delete the student's details whose roll is entered
                            if (roll == -128)//check whether the user confirm to delete or not
                                return;//if user confirms to delete the return statement will get the compiler out of deleter()
                            for (int j = 0; j < cnt; j++) {//loop to check the roll entered is in selected[] one by one
                                if (roll == selected[j]) {//to check the roll entered is in selected[]
                                    bb = false;//to access the print statement for invalid roll
                                    break;//to get out of for() loop if roll entered is in selected[]
                                }
                            }
                            if (bb) {
                                //print statement if roll entered is not of any selected student
                                System.out.println("The roll entered is not given above, enter again");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                                bb = true;//to continue while(bbb) loop
                            }
                        }
                        if (roll != length) {//check if the roll entered is not the last roll in the list
                            for (int j = 0; j < length; j++) {//loop to check each roll in roll[]
                                if (roll == this.roll[j]) {//search for the roll in the list which is entered
                                    for (int k = roll - 1; k < length; k++) {//loop to shift the details of students below roll up from the list by one
                                        this.name[k] = this.name[k + 1];
                                        this.marks[k][0] = this.marks[k + 1][0];
                                        marks[k][1] = this.marks[k + 1][1];
                                        this.marks[k][2] = this.marks[k + 1][2];
                                        this.avg[k] = this.avg[k + 1];
                                        this.grade[k] = this.grade[k + 1];
                                        length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                                        index--;//decreasing the index of students by 1
                                    }
                                }
                            }
                        }
                        else{
                            length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                            index--;//decreasing the index of students by 1
                        }
                        b = false;//get the compiler out of while loop for choices
                        show();//print the updated list
                        break;
                    case 4://to delete student by its grade
                        bb = true;
                        cnt = 0;
                        selected = new byte[length];
                        while (bb) {
                            System.out.print("Enter grade: ");
                            char c = check_exception(br.readLine(), ' ');
                            //print statement to print details of student with matching grade
                            System.out.println("Below are the details of students with matching grade:-");
                            for (int j = 0; j < length; j++)//loop to check each student's grade
                                if (grade[j] == c) {//check if name entered matches with any name in name[]
                                    System.out.println(this.roll[j] + ".\t" + this.name[j] + "\t" + this.marks[j][0] + "\t" + this.marks[j][1] + "\t" + this.marks[j][2] + "\t" + this.avg[j] + "\t" + this.grade[j]);
                                    selected[cnt] = this.roll[j];
                                    cnt++;
                                    bb = false;
                                }
                            if (bb) {//to check that any student's name matches with the name entered
                                System.out.println("No one scored the grade you entered");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                            }
                        }
                        System.out.println("From the above given list enter the roll of the student whose details you want to delete");
                        bb = true;
                        while (bb) {//loop to enter roll again and again until a valid roll is entered
                            bb = false;
                            System.out.print("Enter roll: ");
                            roll = check_exception(br.readLine().trim(), (byte) 0);//store roll after checking for exceptions and logical errors
                            roll = confirm_delete(roll);//to confirm that the user want to delete the student's details whose roll is entered
                            if (roll == -128)//check whether the user confirm to delete or not
                                return;//if user confirms to delete the return statement will get the compiler out of deleter()
                            for (int j = 0; j < cnt; j++) {//loop to check the roll entered is in selected[] one by one
                                if (roll == selected[j]) {//to check the roll entered is in selected[]
                                    break;//to get out of for() loop if roll entered is in selected[]
                                }
                            }
                            if (bb) {
                                //print statement if roll entered is not of any selected student
                                System.out.println("The roll entered is not given above, enter again");
                                //print statement to ask user if he/she wants to cancel deleting or not
                                System.out.print("To cancel deleting enter cancel below and to continue enter anything else\nEnter: ");
                                cancel = br.readLine();
                                if (cancel.equalsIgnoreCase("cancel"))//get the compiler out of deleter() if cancel if entered
                                    return;
                                bb = true;//to continue while(bbb) loop
                            }
                        }
                        if (roll != length) {//check if the roll entered is not the last roll in the list
                            for (int j = 0; j < length; j++) {//loop to check each roll in roll[]
                                if (roll == this.roll[j]) {//search for the roll in the list which is entered
                                    for (int k = roll - 1; k < length; k++) {//loop to shift the details of students below roll up from the list by one
                                        this.name[k] = this.name[k + 1];
                                        this.marks[k][0] = this.marks[k + 1][0];
                                        marks[k][1] = this.marks[k + 1][1];
                                        this.marks[k][2] = this.marks[k + 1][2];
                                        this.avg[k] = this.avg[k + 1];
                                        this.grade[k] = this.grade[k + 1];
                                        length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                                        index--;//decreasing the index of students by 1
                                    }
                                }
                            }
                        }
                        else{
                            length--;//prevent the compiler from accessing the last roll which would be same as the previous roll
                            index--;//decreasing the index of students by 1
                        }
                        b = false;//get the compiler out of while loop for choices
                        show();//print the updated list
                        break;
                    case 5:
                        bb = true;
                        while (bb) {//to confirm that the user want to clear all data
                            System.out.println("WARNING!\tEverything will get deleted\nWant to continue?(Yes or No)");
                            String confirm = check_exception(br.readLine());//to store string for deleting after checking for logical errors
                            if (confirm.equalsIgnoreCase("yes"))//exit while loop
                                bb = false;
                            else if (confirm.equalsIgnoreCase("no")) {//cancels deleting procedure
                                System.out.println("Cancelling deleting procedure.....");
                                return;
                            } else//ask for valid response
                                System.out.println("Enter a valid response");
                        }
                        length = 0;
                        index = 0;
                        b = false;//get the compiler out of while loop for choices
                        break;
                    case 6://to get out of searchMenu() if user do not want to perform any action in searchMenu()
                        return;//get the compiler out of searchMenu()
                    default:
                        //print statement for invalid choice
                        System.out.println("Invalid data entered\nEnter again");
                }
            }
        }
    }

    /*The function confirm_delete() returns -128 if the user entered 'no' while confirming*/
    private byte confirm_delete(byte roll) throws Exception { // to confirm that user want to delete or not especially for deleter()
        String confirm;
        while (true) {//loop to confirm that the input is valid or not
            System.out.println("Are you sure you want to delete the details?(Yes or No)");
            confirm = check_exception(br.readLine());//store input after checking logical errors
            if (confirm.equalsIgnoreCase("yes"))//break while() loop if user entered yes
                break;
            else if (confirm.equalsIgnoreCase("no")) {//return a certain number if user entered no
                System.out.println("Cancelling deleting procedure.....");
                return -128;
            } else
                //print statement for in valid input
                System.out.println("Enter a valid response");
        }
        return roll;//return roll if user confirmed to delete
    }

    private void grader() { // to find grade scored by students and assign it to grade[]
        for (int j = 0; j < length; j++) {
            avg[j] = (marks[j][0] + marks[j][1] + marks[j][2]) / 3; // finding average of marks and and storing it avg[]
            /*using if() to grade marks on basis of average
              0 to 39 gets F
              40 to 49 gets E
              50 to 59 gets D
              60 to 69 gets C
              70 to 79 gets B
              80 to 89 gets A
              90 to 100 gets S*/
            if (avg[j] <= 100 && avg[j] >= 90)
                grade[j] = 'S';
            else if (avg[j] <= 89 && avg[j] >= 80)
                grade[j] = 'A';
            else if (avg[j] <= 79 && avg[j] >= 70)
                grade[j] = 'B';
            else if (avg[j] <= 69 && avg[j] >= 60)
                grade[j] = 'C';
            else if (avg[j] <= 59 && avg[j] >= 50)
                grade[j] = 'D';
            else if (avg[j] <= 49 && avg[j] >= 40)
                grade[j] = 'E';
            else
                grade[j] = 'F';
        }
    }

    void editor() throws Exception {//to edit details of students
        if (length == 0)//asks user to enter data if list is empty
            System.out.println("Enter data first!\nThe list is empty.");
        else {//print list if it is not empty
            b = true;
            byte roll = 0, i;
            show();//calling show()
            while (b) {//loop to enter the roll again and again until valid input is given
                System.out.print("Enter the roll of student whose details you want to edit: ");
                roll = check_exception(br.readLine().trim(), (byte) 0);//calling check_exception() for byte to check for exception and logical error
                for (int j = 0; j < length; j++) {//loop to check the roll entered is assigned to a student or not
                    if (roll == this.roll[j]) {//break while loop if roll entered is assigned to a student
                        b = false;
                        break;
                    }
                }
                if (b)//print statement for invalid input
                    System.out.println("The roll you entered is invalid!");
            }
            byte k = (byte) (roll - 1);//store index of the student whose roll is entered
            //print the data which is going to change
            System.out.println("Below is the data you are going to change.");
            System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
            b = true;
            while (b) {//loop to enter choice again and again until valid choice is given
                System.out.println("What do you want to edit:-\n1. Name\n2. Physics marks\n3. Chemistry marks\n4. Maths marks\n5. Everything\n6. Cancel editing");
                i = check_exception(br.readLine().trim(), (byte) 0);//storing input after checking for  exception and logical errors
                switch (i) {// to check which choice the user chose & respond accordingly
                    case 1://to edit name of the student
                        System.out.print("Enter new name: ");
                        this.name[k] = check_exception(br.readLine().trim());//store new name after checking for exceptions and logical errors
                        sort();//call sort()
                        b = false;//to get out of while loop
                        System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
                        break;//to get out of switch
                    case 2://to edit Physics marks of the student
                        System.out.print("Enter new Physics marks: ");
                        this.marks[roll - 1][0] = check_exception(br.readLine(), (double) 0);//store new marks after checking for exceptions and logical errors
                        grader();//call grader()
                        b = false;//to get out of while loop
                        System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
                        break;//to get out of switch
                    case 3://to enter Chemistry marks of student
                        System.out.print("Enter new Chemistry marks: ");
                        this.marks[roll - 1][1] = check_exception(br.readLine(), (double) 0);//store new marks after checking for exceptions and logical errors
                        grader();//call grader()
                        b = false;//to get out of while loop
                        System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
                        break;
                    case 4://to edit Maths marks of student
                        System.out.print("Enter new Maths marks: ");//store new marks after checking for exceptions and logical errors
                        this.marks[roll - 1][2] = check_exception(br.readLine(), (double) 0);
                        grader();//call grader()
                        b = false;//to get out of while loop
                        System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
                        break;
                    case 5://to edit all details of student
                        System.out.print("Enter new name: ");
                        name[k] = check_exception(br.readLine().trim());//store new name after checking for exceptions and logical errors
                        System.out.print("Enter new Physics marks: ");
                        this.marks[roll - 1][0] = check_exception(br.readLine(), (double) 0);//store new marks after checking for exceptions and logical errors
                        System.out.print("Enter new Chemistry marks: ");
                        this.marks[roll - 1][1] = check_exception(br.readLine(), (double) 0);//store new marks after checking for exceptions and logical errors
                        System.out.print("Enter new Maths marks: ");
                        this.marks[roll - 1][2] = check_exception(br.readLine(), (double) 0);//store new marks after checking for exceptions and logical errors
                        grader();//call grader()
                        sort();//call sort()
                        b = false;//to get out of while loop
                        System.out.println(this.roll[k] + ".\t" + this.name[k] + "\t" + this.marks[k][0] + "\t" + this.marks[k][1] + "\t" + this.marks[k][2] + "\t" + this.avg[k] + "\t" + this.grade[k]);
                        break;
                    case 6://to get out of editor() if user do not want to perform any action in editor()
                        return;
                    default:
                        //print statement for invalid choice
                        System.out.println("Invalid data entered\nEnter again");
                }
            }
        }
    }

    void show() {// to print details in a format
        if (length == 0)//asks user to enter data if list is empty
            System.out.println("The list of student is empty.\nFor further working please enter details of student.");
        else {//print list if it is not empty
            System.out.println("-----------------------------------------------------------------------------\n                                Student detail                         \n-----------------------------------------------------------------------------");
            for (int j = 0; j < length; j++) {
                System.out.println("\nRoll: " + roll[j]);
                System.out.println("Name: " + name[j]);
                System.out.println("Marks:- ");
                System.out.println("  |  Physics: " + marks[j][0]);
                System.out.println("  |  Chemistry: " + marks[j][1]);
                System.out.println("  |  Maths: " + marks[j][2]);
                System.out.println("Average: " + avg[j]);
                System.out.println("Grade: " + grade[j] + "\n");
            }
        }
    }

    private String check_exception(String str) throws Exception {// to check exception related to String input
        check_exception = true;
        while (check_exception) {//loop to take input again and again until user enters a number
            for (int j = 0; j < str.length(); j++)//loop to check each character of String
                if (!Character.isLetter(str.charAt(j))) {//to check whether the user has given an input other than letters
                    check_exception = true;
                    break;
                } else check_exception = false;//to break while loop if the input given is a number
            if (!check_exception) {//taking input again in case of an input other than numbers
                continue;
            }
            //taking input again in case of any logical errors
            System.out.print("Enter letters!\nEnter again: ");
            str = br.readLine();
        }
        return str;//return the string after checking for exceptions and logical errors
    }

    private byte check_exception(String str, byte b) throws Exception {// to check exception and logical errors related to byte input
        check_exception = true;
        while (check_exception) {//loop to take input again and again until user enters a number
            for (int j = 0; j < str.length(); j++)//loop to check each character of String
                if (!Character.isDigit(str.charAt(j))) {//to check whether the user has given an input other than numbers
                    check_exception = true;
                    break;
                } else check_exception = false;//to break while loop if the input given is a number
            if (check_exception) {//taking input again in case of an input other than numbers
                System.out.print("Enter Integer!\nEnter again: ");
                str = br.readLine();
            } else if (Double.parseDouble(str) < 0 || Double.parseDouble(str) > 100) {
                System.out.print("Enter valid data!\nEnter again: ");
                str = br.readLine();
            }
        }
        return Byte.parseByte(str);//return the string in a form of byte type after checking for exceptions and logical errors
    }

    private char check_exception(String str, char c) throws Exception {// to check exception and logical errors related to char input
        check_exception = true;
        while (check_exception) {//loop to take input again and again until user enters a single letter
            if (str.length() != 1) {//check whether the input is single letter or not
                System.out.print("Enter only one letter!");
                str = br.readLine();
                continue;
            }
            check_exception = !Character.isLetter(str.charAt(0));//to check whether the user has given an input other than letter
            if (!check_exception) {
                continue;
            }
            //taking input again in case of any logical errors
            System.out.print("Enter letters!\nEnter again: ");
            str = br.readLine();
        }
        return Character.toUpperCase(str.charAt(0));//return the string in a form of Character after checking for exceptions and logical errors
    }

    private double check_exception(String str, double d) throws Exception {//to check exception and logical errors related to double input
        check_exception = true;
        while (check_exception) {//loop to take input again and again until user enters a number
            for (int j = 0; j < str.length(); j++)//loop to check each character of String
                if (str.charAt(j) == '.')//to check whether the input is floating value or not
                    check_exception = false;
                else if (!Character.isDigit(str.charAt(j))) {//to check whether the user has given an input other than numbers
                    check_exception = true;
                    System.out.println("Enter a valid input like numbers");
                    break;
                } else check_exception = false;//to break while loop if the input given is a number
            if (check_exception) {//taking input again in case of an input other than numbers
                System.out.print("Enter a number!\nEnter again: ");
                str = br.readLine();
            } else if (Double.parseDouble(str) < 0 || Double.parseDouble(str) > 100) {//taking input again in case of an input which is not between 0 and 100
                System.out.print("Enter marks from 0 to 100!\nEnter again: ");
                str = br.readLine();
            }
        }
        return Double.parseDouble(str);//taking input again in case of an input which is not between 0 and 100
    }

    public static void main(String[] ar) throws Exception { // to act as assembly of function and work on start menu
        Delta obj = new Delta();// creating object of current class
        System.out.println("\n <--------------Welcome to Delta student management app !-------------->");
        while (true) {// infinite loop to prevent crashing of program
            // printing menu
            System.out.println("What do you want to do ?");
            System.out.println(" 1. Register new student \n 2. Search  \n 3. Delete  \n 4. Edit  \n 5. Print details \n 6. Exit(0)");
            //taking input
            System.out.print("Enter choice: ");
            byte i = obj.check_exception(br.readLine().trim(), (byte) 0);
            // calling functions according to choice
            switch (i) {
                case 1:
                    obj.setter();//sends compiler to setter()
                    obj.grader();//sends compiler to grader()
                    obj.show();//sends compiler to show()
                    break;
                case 2:
                    obj.searchMenu();//sends compiler to searchMenu()
                    break;
                case 3:
                    obj.deleter();//sends compiler to deleter()
                    break;
                case 4:
                    obj.editor();//sends compiler to editor()
                    break;
                case 5:
                    obj.show();//sends compiler to show()
                    break;
                case 0:
                case 6:// case to exit program
                    return;
                default:// default case to prevent unwanted inputs
                    System.out.println("Enter only given choices\n");
            }
        }
    }
}
