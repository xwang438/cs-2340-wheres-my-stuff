package edu.gatech.cs2340.wheresmystuff;

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/**
 * M7
 * TextFile.java
 * 
 * @author Kenneth Craig
 * @version 3/29/2013
 **/

/**
 * This class handles the checking, storing, and verification of usernames and
 * passwords for the "Where's My Stuff" App
 **/

public class TextFile {

        private String filename;
        private FileWriter fwriter;
        private PrintWriter inputFile;
        
        private File ourFile;
        private Scanner outputFile;
        
        private StringTokenizer tokenizer;
        
        // Basic constructor, creates a "default" username and password for demo
        // purposes
        public TextFile(String file) throws IOException {
                filename = file;
                
                FileWriter fw = new FileWriter(filename, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.print("");
                pw.close();
        }

        /**
         * 
         * @param index
         *            the index in the array at which the desired username can be
         *            found
         * @return returns the found username as a string
         */
        
        public boolean addUser(User user) throws IOException {
                String info = user.getUsername() + ":" + user.getPassword() + ":" + 
                                user.getFirstname() + ":" + user.getLastname() + ":";
                
                if(user.isLocked())
                        info += "true:";
                else
                        info += "false:";
                
                if(user.isAdmin())
                        info += "true";
                else
                        info += "false";
                        
                
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                inputFile.println(info);
                closeInput();
                
                return true;
        }
        
        public boolean setLocked(String username, boolean lock) throws IOException {
                String info = getRow(username);
                int indexOfUser = getIndexOfUser(username);
                String[] allLines = new String[getNumberOfLines()];
                String newInfo;
                
                if(getLocked(info) == lock)
                        return true;
                else {
                        tokenizer = new StringTokenizer(info,":");
                        newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
                                        tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":";
                        
                        if(lock)
                                newInfo += "true:" + tokenizer.nextToken();
                        else
                                newInfo += "false:" + tokenizer.nextToken();
                        
                }
                
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < indexOfUser; i++) {
                        allLines[i] = outputFile.nextLine();
                        if(i+1 == indexOfUser) {
                                i++;
                                allLines[i] = newInfo;
                                while(outputFile.hasNext()) {
                                        i++;
                                        allLines[i] = outputFile.nextLine();
                                }
                        }
                }
                closeOutput();
                
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                for(int i = 0; i < allLines.length; i++) {
                                inputFile.println(allLines[i]);
                }
                closeInput();
                
                return true;
        }
        
        public boolean setAdmin(String username, boolean admin) throws IOException {
                String info = getRow(username);
                int indexOfUser = getIndexOfUser(username);
                String[] allLines = new String[getNumberOfLines()];
                String newInfo;
                
                if(getAdmin(info) == admin)
                        return true;
                else {
                        tokenizer = new StringTokenizer(info,":");
                        newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
                                        tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":";
                        
                        if(admin)
                                newInfo += tokenizer.nextToken() + ":true";
                        else
                                newInfo += tokenizer.nextToken() + ":false";
                        
                }
                
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < indexOfUser; i++) {
                        allLines[i] = outputFile.nextLine();
                        if(i+1 == indexOfUser) {
                                i++;
                                allLines[i] = newInfo;
                                while(outputFile.hasNext()) {
                                        i++;
                                        allLines[i] = outputFile.nextLine();
                                }
                        }
                }
                closeOutput();
                
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                for(int i = 0; i < allLines.length; i++) {
                                inputFile.println(allLines[i]);
                }
                closeInput();
                
                return true;
        }
        
        public boolean removeUser(String username) throws IOException {
                int indexOfUser = getIndexOfUser(username);
                
                if(indexOfUser == -1)
                        return true;
                
                String[] allLines = new String[getNumberOfLines()-1];
                
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < indexOfUser; i++) {
                        allLines[i] = outputFile.nextLine();
                        if(i+1 == indexOfUser) {
                                outputFile.nextLine();
                                while(outputFile.hasNext()) {
                                        i++;
                                        allLines[i] = outputFile.nextLine();
                                }
                        }
                }
                closeOutput();
                
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                for(int i = 0; i < allLines.length; i++) {
                                inputFile.println(allLines[i]);
                }
                closeInput();
                
                return true;
        }
        
        public boolean logInUser(int index) throws IOException {
                String info = getRow(index);
                int indexOfUser = index;
                String[] allLines = new String[getNumberOfLines()];
                String newInfo;
                
                System.out.println("LIU1");
                
                tokenizer = new StringTokenizer(info,":");
                newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
                                tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" +
                                tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":loggedIn";
                System.out.println("LIU2");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < indexOfUser; i++) {
                        System.out.println("LIU3");
                        allLines[i] = outputFile.nextLine();
                        if(i+1 == indexOfUser) {
                                i++;
                                allLines[i] = newInfo;
                                while(outputFile.hasNext()) {
                                        System.out.println("LIU4");
                                        i++;
                                        allLines[i] = outputFile.nextLine();
                                }
                        }
                }
                System.out.println("LIU5");
                closeOutput();
                System.out.println("LIU6");
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                for(int i = 0; i < allLines.length; i++) {
                        System.out.println("LIU7");
                        inputFile.println(allLines[i]);
                }
                System.out.println("LIU8");
                closeInput();
                
                return true;
        }
        
        public boolean logOutUser(String username) throws IOException {
                String info = getRow(username);
                int indexOfUser = getIndexOfUser(username);
                String[] allLines = new String[getNumberOfLines()];
                String newInfo;
                
                tokenizer = new StringTokenizer(info,":");
                newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
                                tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" +
                                tokenizer.nextToken() + ":" + tokenizer.nextToken();
                
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < indexOfUser; i++) {
                        allLines[i] = outputFile.nextLine();
                        if(i+1 == indexOfUser) {
                                i++;
                                allLines[i] = newInfo;
                                while(outputFile.hasNext()) {
                                        i++;
                                        allLines[i] = outputFile.nextLine();
                                }
                        }
                }
                closeOutput();
                
                FileWriter fw = new FileWriter(filename, true);
                inputFile = new PrintWriter(fw);
                for(int i = 0; i < allLines.length; i++) {
                                inputFile.println(allLines[i]);
                }
                closeInput();
                
                return true;
        }
        
        public String getLoggedInUser() throws IOException {
                String info;
                String loggedInUser = "";
                
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                while(outputFile.hasNext()) {
                        info = outputFile.nextLine();
                        tokenizer = new StringTokenizer(info,":");
                        if(tokenizer.countTokens() == 7)
                                loggedInUser = tokenizer.nextToken();
                }
                closeOutput();
                
                return loggedInUser;
        }
        
        
        
        
        
        
        
        
        
        public String[] getUsernames() throws IOException {
                String[] usernames = new String[getNumberOfLines()];
                System.out.println("GUs1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < usernames.length; i++) {
                        System.out.println("GUs2");
                        usernames[i] = getUsername(outputFile.nextLine());
                }
                System.out.println("GUs3");
                closeOutput();
                
                return usernames;
        }
        
        public String[] getPasswords() throws IOException {
                String[] passwords = new String[getNumberOfLines()];
                System.out.println("GPs1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                for(int i = 0; i < passwords.length; i++) {
                        System.out.println("GPs2");
                        passwords[i] = getPassword(outputFile.nextLine());
                }
                System.out.println("GPs3");
                closeOutput();
                
                return passwords;
        }
        
        public int getIndexOfUser(String username) throws IOException {
                int i = 0;
                boolean found = false;
                System.out.println("GIoU1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                while(outputFile.hasNext() && !found) {
                        System.out.println("GIoU2");
                        if(username.equals(getUsername(outputFile.nextLine())))
                                found = true;
                        i++;
                }
                System.out.println("GIoU3");
                closeOutput();
                
                if(!found) i = -1;
                System.out.println("GIoU4");
                return i;
        }
        
        public int getNumberOfLines() throws IOException {
                int i = 0;
                System.out.println("GLines1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                while(outputFile.hasNext()) {
                        System.out.println("GLines2");
                        i++;
                }
                closeOutput();
                
                return i;
        }
        
        public String getRow(int rowIndex) throws IOException {
                String rowData = null;
                int i = 0;
                System.out.println("GRowwI1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                while(outputFile.hasNext() && i < rowIndex) {
                        System.out.println("GRowwI2");
                        if(i+1 == rowIndex) {
                                rowData = outputFile.nextLine();
                                System.out.println("GRowwI3");
                        }
                        i++;
                }
                closeOutput();
                
                return rowData;
        }
        
        public String getRow(String name) throws IOException {
                String rowData = null;
                boolean found = false;
                String nameInRow;
                System.out.println("GRowwN1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                while(outputFile.hasNext() && !found) {
                        System.out.println("GRowwN2");
                        nameInRow = getUsername(outputFile.nextLine());
                        if(name == nameInRow) {
                                System.out.println("GRowwN3");
                                rowData = outputFile.nextLine();
                                found = true;
                        }
                }
                closeOutput();
                
                return rowData;
        }
        
        public String getUsername(String line) {
                String username;
                tokenizer = new StringTokenizer(line,":");
                System.out.println("GU1");
                username = tokenizer.nextToken();
                
                return username;
        }
        
        public String getPassword(String line) {
                String password;
                tokenizer = new StringTokenizer(line,":");
                System.out.println("GP1");
                tokenizer.nextToken();
                password = tokenizer.nextToken();
                
                return password;
        }
        
        public String getFirstName(String line) {
                String firstName;
                tokenizer = new StringTokenizer(line,":");
                
                tokenizer.nextToken();
                tokenizer.nextToken();
                firstName = tokenizer.nextToken();
                
                return firstName;
        }
        
        public String getLastName(String line) {
                String lastName;
                tokenizer = new StringTokenizer(line,":");
                
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                lastName = tokenizer.nextToken();
                
                return lastName;
        }
        
        public boolean getLocked(String line) {
                boolean locked;
                String status;
                tokenizer = new StringTokenizer(line,":");
                
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                status = tokenizer.nextToken();
                
                if(status.equals("true"))
                        locked = true;
                else
                        locked = false;
                
                return locked;
        }
        
        public boolean getAdmin(String line) {
                boolean admin;
                String status;
                tokenizer = new StringTokenizer(line,":");
                
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                tokenizer.nextToken();
                status = tokenizer.nextToken();
                
                if(status.equals("true"))
                        admin = true;
                else
                        admin = false;
                
                return admin;
        }
        
        public User getUser(String line) {
                User user = new User(getUsername(line), getPassword(line),
                                getFirstName(line), getLastName(line), getLocked(line),
                                getAdmin(line));
                
                return user;
        }
        
        public void openOutput() throws IOException {
                System.out.println("OO1");
                ourFile = new File(filename);
                outputFile = new Scanner(ourFile);
                System.out.println("OO2");
        }
        
        public void openInput() throws IOException {
                System.out.println("OI1");
                fwriter = new FileWriter(filename, true);
                inputFile = new PrintWriter(fwriter);
                System.out.println("OI2");
        }
        
        public void closeInput() throws IOException {
                System.out.println("CI1");
                inputFile.close();
                System.out.println("CI2");
        }
        
        public void closeOutput() throws IOException {
                System.out.println("CO1");
                outputFile.close();
                System.out.println("CO2");
        }

        /**
         * 
         * @param index
         *            the index in the array at which the desired password can be
         *            found
         * @return returns the found password as a string
         */
        

        /**
         * 
         * @param user
         *            loops through list of usernames and checks if the input
         *            parameter exists in the list
         * @return returns true if the username exists, false is it doesn't
         */
        

        /**
         * 
         * @param user
         *            loops through list of passwords and checks if the input
         *            parameter exists in the list
         * @return returns true if the password exists, false is it doesn't
         */
        

        /**
         * 
         * @param newUser
         *            adds this parameter to the usernames array
         * @param newPassword
         *            adds this parameter to the passwords array
         * @return true if the username and password were successfully added to the
         *         arrays, false if the username already exists
         */
        

        /**
         * 
         * @param username
         *            username parameter to be checked
         * @param password
         *            password parameter to be checked
         * @return true if the password and username match, false if not
         */
        

        /**
         * 
         * @param attempts
         *            a variable describing how many times the user has tried to
         *            login
         * @return true if login may proceed, false if the user has failed too many
         *         times
         */

        /**
         * 
         * @return returns the values of the lists of usernames and passwords as a
         *         string
         */
        

}