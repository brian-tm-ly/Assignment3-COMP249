package VocabularyControlCenter;
//Brian Ly (40028072), Valerie Nguyen (40284261)

//COMP249
//Assignment #3
//Due April 15, 2024

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * DoublyLinkedList class to create a doubly linked list of topics
 * @author Brian, Valerie
 * @version 1.0
 * @see SinglyLinkedList
 * @see Vocab
 * @see Driver
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int count; // to count the number of topics in the list

    /**
     * Default constructor to create an empty list
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Getter method to get the head of the list
     * @return the head of the list
     */
    public Node getHead() {
        return head;
    }

    /**
     * Getter method to get the tail of the list
     * @return the tail of the list
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Getter method to get number of Vocab objects of the list
     * @return the number of Vocab objects
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Getter method to get the Vocab object of the head of the list
     * @return Vocab object of the head
     */
    public Vocab getVocab() {
        return head.vocab;

    }

    /**
     * Method to save the list to a file
     * @param file the file to save the list to 
     */
    public void saveToFile(String file) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file));
            Node position = head;
            while (position != null) {
                pw.println("#" + position.vocab.getTopic());
                SinglyLinkedList words = position.vocab.getWords();
                for (int i = 0; i < words.size(); i++) {
                    String word = words.getWordAt(i);
                    pw.println(word);
                }
                pw.println();
                position = position.next;
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened.");
        }
    }

    /**
     * Method to store words starting with a specified prefix
     * @param prefix the prefix to search for
     * @return ArrayList of words starting with the specified prefix
     */
    public ArrayList<String> startingWith(String prefix) {
        ArrayList<String> wordsStartingWith = new ArrayList<>(); // to store words starting with the prefix
        Node position = head;
        while (position != null) {
            // Get the words list of the vocab object
            SinglyLinkedList words = position.vocab.getWords();
            // Check if the word starts with the prefix
            for (int i = 0; i < words.size(); i++) {
                String word = words.getWordAt(i);
                if (word.startsWith(prefix)) {
                    wordsStartingWith.add(word);
                }
            }
            position = position.next;
        }
        return wordsStartingWith;
    }

    /**
     * Method to find a Vocab object at a specified index
     * @param topicChoice the index of the Vocab object to find
     * @return the Vocab object at the specified index
     */
    public Vocab find(int topicChoice) {
        Node position = head;
        int counter = 1; // to keep track of the topic number
        //While loop to traverse the list until the specified index
        while (position != null && counter != topicChoice) {
            position = position.next;
            counter++;
        }
        return position.vocab; // return the vocab object

    }

    /**
    * Method to find a Vocab object with a specified topic
    * @param topic the topic of the Vocab object to find
    * @return the Vocab object with the specified topic, or null if no such Vocab exists
    */
    public Vocab findVocabByTopic(String topic) {
        Node position = head;
        // While loop to traverse the list until the specified topic is found
        while (position != null) {
            if (position.vocab.getTopic().equals(topic)) {
                return position.vocab; // Return the Vocab object
            }
            position = position.next;
        }
        return null; // Return null if no Vocab with the specified topic is found
    }

    /**
     * Method to find a Vocab object with a specified topic
     * @param word the word to search for
     * @return the ArrayList of Vocab objects with the specified word
     */
    public ArrayList<Vocab> findWord(String word) {
        ArrayList<Vocab> vocabs = new ArrayList<>();
        Node position = head;
        // While loop to traverse the list until the word is found
        while (position != null) {
            // Get the words list of the vocab object
            SinglyLinkedList words = position.vocab.getWords();
            for (int i = 0; i < words.size(); i++) {
                if (words.getWordAt(i).equals(word) && !(vocabs.contains(position.vocab))) // if the word is found
                    vocabs.add(position.vocab);
            }
            position = position.next;
        }
        return vocabs;
    }

    /**
     * Method to add a Vocab object to the head of the list
     * @param newVocab the Vocab object to add to the list
     */
    public void addAtHead(Vocab newVocab) {
        Node oldHead = head; // store the current head
        Node position = head;
        // Check if topic already exists in the list
        while (position != null) {
            if (position.vocab.getTopic().equals(newVocab.getTopic())) {
                System.out.println("Sorry, the topic \'" + newVocab.getTopic() + "\' is already listed.");
                return;
            }
            position = position.next;
        }
        // Add topic to list
        head = new Node(newVocab, null, head);

        if (tail == null) { // if list is empty
            tail = head; // set the tail to the new head
        } else {
            oldHead.prev = head; // set the previous node of the old head to the new head
        }
        count++;
    }

    /**
     * Method to add a Vocab object to the tail of the list
     * @param newVocab the Vocab object to add to the list
     */
    public void addAtTail(Vocab newVocab) {
        Node position = head;
        // Check if topic already exists in the list
        while (position != null) {
            if (position.vocab.getTopic().equals(newVocab.getTopic())) {
                System.out.println("Sorry, the topic \'" + newVocab.getTopic() + "\' is already listed.");
                return;
            }
            position = position.next;
        }

        // Add topic to list
        if (tail == null) {
            head = new Node(newVocab, null, null); // if list is empty set the head to the new topic
            tail = head; // set the tail to the new head
        } else {
            Node oldTail = tail; // store the current tail
            tail = new Node(newVocab, tail, null); // set the new tail previous node to the old tail
            oldTail.next = tail; // set the next node of the old tail to the new tail
        }
        count++;
    }

    /**
     * Method to add a Vocab object before a specified Vocab object
     * @param vocabReference the Vocab object to add before
     * @param newVocab the Vocab object to add
     */
    public void addBefore(Vocab vocabReference, Vocab newVocab) {
        if (head == null) { // if list is empty
            return;
            // Check if the topic of the new vocab is the same as the head
        } else if (head.vocab.getTopic().equals(vocabReference.getTopic())) {
            addAtHead(newVocab); // add the new vocab to the head
        } else { // if the topic of the new vocab is not the same as the head
            // Check if topic already exists in the list
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(vocabReference.getTopic()))) {
                if (position.vocab.getTopic().equals(newVocab.getTopic())) {
                    System.out.println("Sorry, the topic \'" + newVocab.getTopic() + "\' is already listed.");
                    return;
                }
                position = position.next;
            }
            if (position != null) {
                //Create a new node, 
                //set the previous node of the new node to the previous node of the reference node 
                //and the next node to the reference node
                Node n = new Node(newVocab, position.prev, position);
                //Set the next node of the previous node of the reference node to the new node
                position.prev.next = n;
                //Set the previous node of the reference node to the new node
                position.prev = n;
                count++;
            }
        }

    }

    /**
     * Method to add a Vocab object after a specified Vocab object
     * @param vocabReference the Vocab object to add after
     * @param newVocab the Vocab object to add
     */
    public void addAfter(Vocab vocabReference, Vocab newVocab) {
        if (head == null) {
            return;
        } else {
            // Check if topic already exists in the list
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(vocabReference.getTopic()))) {
                if (position.vocab.getTopic().equals(newVocab.getTopic())) {
                    System.out.println("Sorry, the topic \'" + newVocab.getTopic() + "\' is already listed.");
                    return;
                }
                position = position.next;
            }
            //Check if position is not null
            if (position != null) {
                //Create a new node with the new vocab object
                Node newNode = new Node(newVocab, null, null);
                //Set the previous node of the new node to the reference node
                newNode.next = position.next;
                //Set the next node of the new node to the next node of the reference node
                newNode.prev = position;
                //if the next node of the reference node is not null
                if (position.next != null) {
                    position.next.prev = newNode; //Set the previous node of the next node of the reference node to the new node
                } else {
                    // if the reference node is the tail
                    tail = newNode;
                }
                position.next = newNode;
                count++;
            }

            // // Check if the topic of the new vocab is the same as the tail
            // if (position != null && position.vocab.getTopic().equals(vocabReference.getTopic())) {
            //     if (position.next == null) { // if the reference node is the tail
            //         addAtTail(newVocab); // add the new vocab to the tail

            //     } else {
            //         //Create a new node,
            //         //set the previous node of the new node to the current node's next node
            //         //and the next node to the current node
            //         Node newNode = new Node(newVocab, position.next, position);
            //         //Set the previous node of the current node's next node to the new node
            //         position.next.prev = newNode;
            //         //Set the next node of the current node to the new node
            //         position.next = newNode;
            //         count++;
            //     }
            // }
        }
    }

    /**
     * Method to remove the first Vocab object
     */
    public void removeHead() {
        if (head == null) {
            return;
        } else if (head == tail) {
            head = null;
            tail = null;
            count--;
        } else {
            head = head.next;
            head.prev = null;
            count--;
        }

    }

    /**
     * Method to remove the last Vocab object
     */
    public void removeTail() {
        if (tail == null) {
            return;
        } else if (head == tail) {
            head = null;
            tail = null;
            count--;
        } else {
            tail = tail.prev;
            tail.next = null;
            count--;
        }
    }

    /**
     * Method to remove a Vocab object from the list
     * @param topicToRemove the topic of the Vocab object to remove
     */
    public void remove(String topicToRemove) {
        if (head == null) {
            return;
        } else if (head.vocab.getTopic().equals(topicToRemove)) {
            removeHead();
        } else if (tail.vocab.getTopic().equals(topicToRemove)) {
            removeTail();
        } else {
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(topicToRemove))) {
                position = position.next; // move to the next node
            }

            if (position != null) {
                position.prev.next = position.next;
                position.next.prev = position.prev;

                //Data sanitization
                position.prev = null;
                position.next = null;
                count--;

            }

            count--;
        }

        // Check if topic exists in the list
    }

    /**
     * Method to modify a Vocab object in the list
     * @param topicToDisplay the topic of the Vocab object to modify
     */
    public void display(String topicToDisplay) {
        if (count == 0) {
            System.out.println("The list is empty.");
        } else {
            Node position = head;
            // While loop to traverse the list until the specified topic is found
            while (position != null && !(position.vocab.getTopic().equals(topicToDisplay))) {
                position = position.next;
            }
            if (position != null) {
                System.out.print(position.vocab);
                position.vocab.getWords().display(); // display the words list of the vocab object
            }
        }
    }

    /**
     * Method to display all topics in the list
     */
    public void displayTopics() {
        int wordNum = 1; // to keep track of the topic number
        if (count == 0) {
            System.out.println("The list is empty.");
        } else {
            Node position = head;

            while (position != null) {
                System.out.print(" " + wordNum + " " + position.vocab.getTopic() + "\n");
                position = position.next;
                wordNum++;
            }
        }
    }

    /**
     * Method to display all topics and words in the list
     */
    private class Node {
        private Vocab vocab;
        private Node next;
        private Node prev;

        /**
         * Default constructor to create a node
         * @param vocab the Vocab object of the node
         * @param prev the previous node
         * @param next the next node
         */
        public Node(Vocab vocab, Node prev, Node next) {
            this.vocab = vocab.clone(); // create a deep copy of the vocab object
            this.next = next;
            this.prev = prev;
        }

    }

}
