import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        int choice = 0;
        int topic = 0;
        Scanner myScanner = new Scanner(System.in);
        DoublyLinkedList vocab_List = new DoublyLinkedList();

        do {
            displayMenu();
            choice = myScanner.nextInt();
            switch (choice) {
                case 1:
                    pickATopic(vocab_List); 
                    topic = myScanner.nextInt();
                    break;
                case 7:
                    System.out.println("Enter the name of the input file:");
                    String inputFile = myScanner.next();
                    myScanner.nextLine();
                    vocab_List = loadFile(inputFile, vocab_List);
                    break;
            }
        } while (choice != 0);

        myScanner.close();

        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtHead("apple");
        list.addAtHead("banana");
        list.addAtHead("cherry");
        list.addAtHead("avocado");
        list.addAtHead("Blueberry");
        list.addAtHead("blackberry");
        list.addAtHead("grape");
        list.addAtHead("kiwi");
        list.addAtHead("lemon");
        list.addAtHead("mango");
        list.addAtHead("orange");
        list.addAtHead("peach");
        list.addAtHead("pear");
        list.addAtHead("plum");
        list.addAtHead("raspberry");
        list.addAtEnd("strawberry");
        // list.display();

        /*
         * SinglyLinkedList l1 = new SinglyLinkedList();
         * l1.addAtHead("backpack");
         * l1.addAtHead("go(with/together)");
         * l1.addAtHead("perfume");
         * l1.addAtHead("sweatshirt");
         * l1.addAtHead("bag");
         * DoublyLinkedList dList = new DoublyLinkedList();
         * Vocab v1 = new Vocab("Clothes and Accessories", l1);
         * SinglyLinkedList l2 = new SinglyLinkedList();
         * l2.addAtHead("gold");
         * l2.addAtHead("orange");
         * l2.addAtHead("silver");
         * l2.addAtHead("black");
         * l2.addAtHead("golden");
         * Vocab v2 = new Vocab("Colours", l2);
         * 
         * v1.getWords().display();
         * dList.addAtHead(v1);
         * dList.display();
         * dList.addAfter(v1, v2);
         * System.out.println("After adding new vocab:");
         * dList.display();
         * dList.remove(v1.getTopic());
         * System.out.println("After removing:");
         * dList.display();
         */

        // System.out.println(list.remove("apple"));
        // System.out.println("\nAfter removing word:");
        // list.display();
        // System.out.println("\nAfter sorting:");
        // list.sort();
        // list.display();
        // list.modify("apple", "melon");
        // System.out.println("\nAfter modifying word:");
        // list.display();
        // list.sort();
        // System.out.println("\nAfter sorting:");
        // list.display();
        // System.out.println("\nAfter cloning:");
        // SinglyLinkedList copy = list.clone();
        // copy.display();

    }

    public static void displayMenu() {
        System.out.println("-----------------------------");
        System.out.println("  Vocabulary Control Center");
        System.out.println("-----------------------------");
        System.out.println("1. browse a topic");
        System.out.println("2. insert a new topic before another one");
        System.out.println("3. insert a new topic after another one");
        System.out.println("4. remove a topic");
        System.out.println("5. modify a topic");
        System.out.println("6. search topics for a word");
        System.out.println("7. load from a file");
        System.out.println("8. show all words starting with a given letter");
        System.out.println("9. save to file");
        System.out.println("0. exit");
        System.out.println("-----------------------------");
        System.out.print("Enter Your Choice: ");
    }

    public static DoublyLinkedList loadFile(String file, DoublyLinkedList vocab_List) {
        Scanner sc = null;
        String line = "";
        String topic = "";
        SinglyLinkedList words = null;

        try {
            sc = new Scanner(new FileInputStream(file));
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.startsWith("#")) {

                    if (topic != null && words != null) {
                        Vocab vocab = new Vocab(topic, words);
                        vocab_List.addAtTail(vocab);
                    }

                    topic = line.substring(1);
                    words = new SinglyLinkedList();
                } else if (!line.equals("")) {
                    words.addAtEnd(line);
                }
            }

            // add the last vocab
            if (topic != null && words != null) {
                Vocab vocab = new Vocab(topic, words);
                vocab_List.addAtTail(vocab);
            }

            System.out.println("Done loading");
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened.");
        }
        return vocab_List;
    }

    public static void pickATopic(DoublyLinkedList vocab_list) {
        System.out.println("-----------------------------");
        System.out.println("        Pick a topic");
        System.out.println("-----------------------------");
        vocab_list.displayTopics();
        System.out.println("0 Exit");
        System.out.println("-----------------------------");
        System.out.print("Enter Your Choice: ");
    }
}
