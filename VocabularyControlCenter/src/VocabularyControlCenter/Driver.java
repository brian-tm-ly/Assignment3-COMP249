package VocabularyControlCenter;
//Brian Ly (40028072), Valerie Nguyen (40284261)

//COMP249
//Assignment #3
//Due April 15, 2024

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class to run the Vocabulary Control Center program
 * 
 * @author Brian, Valerie
 * @version 1.0
 * @see DoublyLinkedList
 * @see SinglyLinkedList
 * @see Vocab
 */
public class Driver {

    /**
     * Main method to run the Vocabulary Control Center program
     * 
     * @param args An array of strings representing the command line arguments
     */
    public static void main(String[] args) {

        int choice = 0;
        int topic = 0;
        Scanner myScanner = new Scanner(System.in);
        DoublyLinkedList vocab_list = new DoublyLinkedList();

        do {
            displayMenu();
            choice = myScanner.nextInt();

            // validate the user's input
            while (choice < 0 || choice > 9) {
                System.out.println("You must enter a number between 0-9.");
                System.out.print("Enter Your Choice: ");
                choice = myScanner.nextInt();
            }

            switch (choice) {
                // browse a topic
                case 1:
                    topic = pickATopic(vocab_list, myScanner);
                    if (topic != 0) {
                        // find the Vocab object to display
                        Vocab vocabDisplay = vocab_list.find(topic);
                        vocab_list.display(vocabDisplay.getTopic());
                    }
                    break;

                // insert a new topic before another one
                case 2:
                    // if vocab_list is empty, add the new topic at head
                    if (vocab_list.getCount() == 0) {
                        Vocab newVocab = enterATopic(vocab_list, myScanner);
                        vocab_list.addAtHead(newVocab);
                        vocab_list.getVocab().sortWords();
                        vocab_list.display(newVocab.getTopic());
                    } else {
                        topic = pickATopic(vocab_list, myScanner);
                        if (topic != 0) {
                            // find the Vocab object to add before
                            Vocab vocabReference = vocab_list.find(topic);
                            Vocab newVocab = enterATopic(vocab_list, myScanner);
                            vocab_list.addBefore(vocabReference, newVocab);
                            newVocab.sortWords();
                            vocab_list.display(newVocab.getTopic());
                        }
                    }
                    break;

                // insert a new topic after another one
                case 3:
                    // if vocab_list is empty, add the new topic at head
                    if (vocab_list.getCount() == 0) {
                        Vocab newVocab = enterATopic(vocab_list, myScanner);
                        vocab_list.addAtHead(newVocab);
                        vocab_list.getVocab().sortWords();
                        vocab_list.display(newVocab.getTopic());
                    } else {
                        topic = pickATopic(vocab_list, myScanner);
                        if (topic != 0) {
                            // find the Vocab object to add after
                            Vocab vocabReference = vocab_list.find(topic);
                            Vocab newVocab = enterATopic(vocab_list, myScanner);
                            vocab_list.addAfter(vocabReference, newVocab);
                            vocab_list.getVocab().sortWords();
                            vocab_list.display(newVocab.getTopic());
                        }
                    }
                    break;

                // remove a topic
                case 4:
                    topic = pickATopic(vocab_list, myScanner);
                    if (topic != 0) {
                        // find the Vocab object to remove
                        Vocab vocabReference = vocab_list.find(topic);
                        vocab_list.remove(vocabReference.getTopic());
                    }
                    break;

                // modfiy a topic
                case 5:
                    topic = pickATopic(vocab_list, myScanner);
                    if (topic != 0) {
                        // find the Vocab object to modify
                        Vocab vocabReference = vocab_list.find(topic);
                        String modifyChoice = modifyTopicsMenu(myScanner);
                        switch (modifyChoice) {
                            // add a word
                            case "a":
                                System.out.println("Type a word and press Enter, or press Enter to end input.");
                                String newWord = myScanner.nextLine();
                                if (!newWord.isEmpty()) {
                                    vocabReference.addWord(newWord);
                                }
                                break;

                            // remove a word
                            case "r":
                                System.out.println("Enter a word:");
                                String wordToRemove = myScanner.next();
                                vocabReference.removeWord(wordToRemove);
                                break;

                            // change a word
                            case "c":
                                System.out.println("Enter the word to change: ");
                                String oldWord = myScanner.next();
                                myScanner.nextLine();
                                System.out.println("Enter the new word and press Enter, or press Enter to end input.");
                                String wordToChange = myScanner.nextLine();
                                if (!wordToChange.isEmpty()) {
                                    vocabReference.modifyWord(oldWord, wordToChange);
                                }
                                break;

                            // exit
                            case "0":
                                break;
                        }
                        vocab_list.getVocab().sortWords();
                    }
                    break;

                // search topics for a word
                case 6:
                    System.out.println("Enter a word:");
                    String wordToFind = myScanner.next();
                    ArrayList<Vocab> vocabTargets = vocab_list.findWord(wordToFind);
                    String topics = "\n";
                    if (vocabTargets.size() != 0) {
                        for (Vocab vocab : vocabTargets) {
                            topics += vocab.getTopic() + "\n";
                        }
                        System.out.println(wordToFind + " is listed in the topic: " + topics);
                    } else {
                        System.out.println("No existing topics contain the word.");
                    }
                    break;

                // load from a file
                case 7:
                    System.out.println("Enter the name of the input file:");
                    String inputFile = myScanner.next();
                    myScanner.nextLine();
                    vocab_list = loadFile(inputFile, vocab_list, myScanner);
                    break;

                // show all words starting with a given letter
                case 8:
                    ArrayList<String> wordsStartingWith = new ArrayList<String>();
                    System.out.println("Enter a letter:");
                    String letter = myScanner.next();
                    wordsStartingWith = vocab_list.startingWith(letter);
                    // sorting the arraylist alphabetically with bubble sort
                    for (int i = 0; i < wordsStartingWith.size() - 1; i++) {
                        for (int j = 0; j < wordsStartingWith.size() - i - 1; j++) {
                            if (wordsStartingWith.get(j).compareTo(wordsStartingWith.get(j + 1)) > 0) {
                                String temp = wordsStartingWith.get(j);
                                wordsStartingWith.set(j, wordsStartingWith.get(j + 1));
                                wordsStartingWith.set(j + 1, temp);
                            }
                        }
                    }
                    for (String word : wordsStartingWith) {
                        System.out.println(word);
                    }
                    break;

                // save to file
                case 9:
                    System.out.println("Enter the name of the output file (format input_topics_words.txt): ");
                    String file = myScanner.next();
                    vocab_list.saveToFile(file);
                    break;
            }
        } while (choice != 0);

        myScanner.close();

    }

    /**
     * Displays the main menu
     */
    public static void displayMenu() {
        System.out.println("-----------------------------");
        System.out.println("  Vocabulary Control Center");
        System.out.println("-----------------------------");
        System.out.println(" 1 browse a topic");
        System.out.println(" 2 insert a new topic before another one");
        System.out.println(" 3 insert a new topic after another one");
        System.out.println(" 4 remove a topic");
        System.out.println(" 5 modify a topic");
        System.out.println(" 6 search topics for a word");
        System.out.println(" 7 load from a file");
        System.out.println(" 8 show all words starting with a given letter");
        System.out.println(" 9 save to file");
        System.out.println(" 0 exit");
        System.out.println("-----------------------------");
        System.out.print("Enter Your Choice: ");
    }

    /**
     * Reads from an input file and stores the Vocab object into a DoublyLinkedList
     * @param file       A string representing the name of the file
     * @param vocab_list A DoublyLinkedList containing all vocabs
     * @param myScanner  A scanner for the user's input
     */
    public static DoublyLinkedList loadFile(String file, DoublyLinkedList vocab_list, Scanner myScanner) {
        String line = "";
        String topic = "";
        SinglyLinkedList words = null;
        Vocab existingVocab = null;

        try {
            myScanner = new Scanner(new FileInputStream(file));
            while (myScanner.hasNextLine()) {
                line = myScanner.nextLine().trim();

                // if the line starts with '#', a new topic is found
                if (line.startsWith("#")) {

                    if (topic != null && words != null) {
                        existingVocab = vocab_list.findVocabByTopic(topic); // check if the topic already exists
                        //if the topic does not exist, add the new vocab
                        if (existingVocab == null) {
                            //  create a new Vocab object and add it at the end of the DoublyLinkedList
                            Vocab vocab = new Vocab(topic, words);
                            vocab_list.addAtTail(vocab);
                        }
                    }
                    topic = line.substring(1); // get the topic
                    words = new SinglyLinkedList();
                    existingVocab = vocab_list.findVocabByTopic(topic); // check if the topic already exists
                } else if (!line.equals("")) {
                    //add the word to the existing vocab
                    if (existingVocab != null) {
                        existingVocab.getWords().addAtHead(line);
                        existingVocab.getWords().sort();
                    } else {
                        // add the word to the SinglyLinkedList
                        words.addFromFile(line);
                        words.sort();
                    }

                }
            }

            // add the last vocab
            if (existingVocab == null) {
                if (topic != null && words != null) {
                    Vocab vocab = new Vocab(topic, words);
                    vocab_list.addAtTail(vocab);
                }

            }

            System.out.println("Done loading");
        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened.");
        }
        return vocab_list;
    }

    /**
     * Displays all topics from the DoublyLinkedList
     * 
     * @param vocab_list A DoublyLinkedList containing all vocabs
     * @param myScanner  A scanner for the user's input
     * @return An integer representing the topic selected by the user
     */
    public static int pickATopic(DoublyLinkedList vocab_list, Scanner myScanner) {
        System.out.println("-----------------------------");
        System.out.println("        Pick a topic");
        System.out.println("-----------------------------");
        vocab_list.displayTopics();
        System.out.println(" 0 Exit");
        System.out.println("-----------------------------");
        System.out.print("Enter Your Choice: ");

        int topicChoice = myScanner.nextInt();

        // validate the user's input
        while (topicChoice < 0 || topicChoice > vocab_list.getCount()) {
            System.out.println("Invalid choice. Please try again.");
            System.out.print("Enter Your Choice: ");
            topicChoice = myScanner.nextInt();
        }
        return topicChoice;
    }

    /**
     * Creates a new Vocab object
     * 
     * @param vocab_list A DoublyLinkedList containing all vocabs
     * @param myScanner  A scanner for the user's input
     * @return A Vocab object created by the user
     */
    public static Vocab enterATopic(DoublyLinkedList vocab_list, Scanner myScanner) {
        System.out.print("Enter a topic: ");
        String topic = myScanner.next();
        myScanner.nextLine();
        Vocab newVocab = new Vocab(topic);
        String word = "";
        System.out.println("Enter a word - to quit press Enter:");

        do {
            word = myScanner.nextLine();
            if (!word.isEmpty()) {
                newVocab.addWord(word);
            }
        } while (!word.isEmpty());

        return newVocab;
    }

    /**
     * Displays the Modify Topics Menu
     * 
     * @param myScanner A scanner for the user's input
     * @return A string representing the option selected by the user
     */
    public static String modifyTopicsMenu(Scanner myScanner) {
        System.out.println("-----------------------------");
        System.out.println("    Modify Topics Menu");
        System.out.println("-----------------------------");
        System.out.println(" a add a word");
        System.out.println(" r remove a word");
        System.out.println(" c change a word");
        System.out.println(" 0 Exit");
        System.out.println("-----------------------------");
        System.out.print("Enter Your Choice: ");

        String modifyChoice = myScanner.next();

        // validate the user's input
        while (!(modifyChoice.toLowerCase().equals("a") ||
                modifyChoice.toLowerCase().equals("r") ||
                modifyChoice.toLowerCase().equals("c") ||
                modifyChoice.equals("0"))) {
            System.out.println("Invalid choice. Please try again.");
            System.out.print("Enter Your Choice: ");
            modifyChoice = myScanner.next();
        }
        myScanner.nextLine();
        return modifyChoice;
    }

}
