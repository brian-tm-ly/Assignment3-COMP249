import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner sc = null;
        String line = "";
        String topic = "";
        SinglyLinkedList words = null;        
        DoublyLinkedList vocabList = new DoublyLinkedList();

        try {
            sc = new Scanner(new FileInputStream("A3_input_file.txt"));
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.startsWith("#")) {
                    
                    if (topic != null && words != null) {
                        Vocab vocab = new Vocab(topic, words);
                        vocabList.addAtTail(vocab);
                    }
                    
                    topic = line.substring(1);
                    words = new SinglyLinkedList();
                } else if (!line.equals("")) {
                    words.addAtEnd(line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened.");
        }

        vocabList.display();



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

        /*SinglyLinkedList l1 = new SinglyLinkedList();
        l1.addAtHead("backpack");
        l1.addAtHead("go(with/together)");
        l1.addAtHead("perfume");
        l1.addAtHead("sweatshirt");
        l1.addAtHead("bag");
        DoublyLinkedList dList = new DoublyLinkedList();
        Vocab v1 = new Vocab("Clothes and Accessories", l1);
        SinglyLinkedList l2 = new SinglyLinkedList();
        l2.addAtHead("gold");
        l2.addAtHead("orange");
        l2.addAtHead("silver");
        l2.addAtHead("black");
        l2.addAtHead("golden");
        Vocab v2 = new Vocab("Colours", l2);

        v1.getWords().display();
        dList.addAtHead(v1);
        dList.display();
        dList.addAfter(v1, v2);
        System.out.println("After adding new vocab:");
        dList.display();
        dList.remove(v1.getTopic());
        System.out.println("After removing:");
        dList.display();*/

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
}
