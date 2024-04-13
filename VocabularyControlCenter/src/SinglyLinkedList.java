//Brian Ly (40028072), Valerie Nguyen (40284261)

//COMP249
//Assignment #3
//Due April 15, 2024

/**
 * SinglyLinkedList class to create a singly linked list of words
 * @author Brian, Valerie
 * @version 1.0
 * @see DoublyLinkedList
 * @see Vocab
 * @see Driver
 */
public class SinglyLinkedList {

    private Node head;
    private int count; //to count the number of words in the list

    /**
     * Default constructor to create an empty list
     */
    public SinglyLinkedList() {
        head = null;
        count = 0;

    }

    /**
     * Getter method to get the head of the list
     * @return Node head
     */
    public Node getHead() {
        return head;
    }

    /**
     * Setter method to set the head of the list
     * @param head
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Getter method to get the word at the head of the list
     * @return String head.word
     */
    public String getWord() {
        return head.word;
    }

    /**
     * Getter method to get the number of words in the list
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * Method to get the word at a specified index
     * @param index the index of the word to get from the list
     * @return the word at the specified index
     */
    public String getWordAt(int index) {
        Node current = head;
        int counter = 0;
        while (current != null) {
            if (counter == index) {
                return current.word; //return the word at the specified index
            }
            current = current.next;
            counter++;
        }
        return null; //return null if index is out of bounds
    }

    /**
     * Method to add a word to the head of the list
     * @param newWord the word to add to the list
     */
    public void addAtHead(String newWord) {
        //Check if word already exists in the list
        Node position = head;
        while (position != null) {
            if (position.word.equals(newWord)) {
                System.out.println("Sorry, the word \'" + newWord + "\' is already listed.");
                return;
            }
            //if word is not found, move to next node
            position = position.next;
        }
        //Add word to list
        head = new Node(newWord, head);
        count++;

    }

    /**
     * Method to add a word to the list from a file input
     * @param newWord the word to add to the list
     */
    public void addFromFile(String newWord) {
        Node position = head;
        while (position != null) {
            position = position.next;
        }
        //Add word to list
        head = new Node(newWord, head);
        count++;
    }

    /**
     * Method to add a word to the end of the list
     * @param newWord
     */
    public void addAtEnd(String newWord) {
        if (head == null) {
            addAtHead(newWord);
        } else {
            //Check if word already exists in the list
            Node position = head;
            while (position.next != null) {
                if (position.word.equals(newWord)) {
                    System.out.println("Sorry, the word \'" + newWord + "\' is already listed.");
                    return;
                }
                //if word is not found, move to next node
                position = position.next;

            }
            //Add word to end of list
            Node newNode = new Node(newWord, null);
            position.next = newNode;
            count++;
        }
    }

    /**
     * Method to modify a word in the list with a new word
     * @param oldWord the word to modify in the list
     * @param newWord the new word to replace the old word
     */
    public void modify(String oldWord, String newWord) {
        if (head == null) {
            System.out.println("There are no words associated with this topic.");
            return;
        } else {
            Node position = head;
            while (position != null) {
                //Check if word is found
                if (position.word.equals(oldWord)) {
                    //Modify the word
                    position.word = newWord;
                    return;
                } else { //if word is not found, move to next node
                    position = position.next;
                }
            }
        }

    }

    /**
     * Method to remove a word from the list by word
     * @param wordToRemove the word to remove from the list
     * @return the word that was removed from the list
     */
    public String remove(String wordToRemove) {
        if (head == null) {
            System.out.println("There are no words associated with this topic.");
            return ("");
            //Check if word to remove is at the head
        } else if (head.word.equals(wordToRemove)) {
            Node temp = head;
            head = head.next; //Move head to next node
            count--;
            return temp.word;
            //Check if word to remove is in the middle or end of the list
        } else {
            Node position = head;
            //While the next node is not null and the word is not found, move to next node
            while (position.next != null && !(position.next.word.equals(wordToRemove))) {
                position = position.next;
            }
            //If the word is found, remove it
            if (position.next != null && position.next.word.equals(wordToRemove)) {
                Node temp = position.next; //Store the word to remove
                position.next = position.next.next; //Set the next node to the node after the word to remove
                count--;
                return temp.word;
            } else {
                System.out.println("Sorry, there is no word: " + wordToRemove);
                return ("");
            }
        }

    }

    /**
     * Method to display the list of words
     * Display 4 words per row as formatted in the sample output
     */
    public void display() {
        if (head == null) {
            System.out.println("There are no words associated with this topic.");
        } else {
            Node position = head;
            int columnCount = 0; //counter to display 4 words per row
            int wordNum = 1; //counter to display the word number in the list
            while (position != null) {
                //Display 4 words per row as formatted in the sample output
                System.out.printf("%2d", wordNum++);
                System.out.printf("%-20s", ": " + position.word);
                position = position.next;
                columnCount++;
                //If 4 words have been displayed or the end of the list is reached, move to the next line
                if (columnCount == 4 || position == null) {
                    System.out.println();
                    columnCount = 0;
                }
            }
        }
    }

    /**
     * Method to sort the list of words alphabetically
     */
    public void sort() {
        Node position = head;
        while (position != null) {
            Node nextPosition = position.next;
            while (nextPosition != null) {
                if (position.word.compareTo(nextPosition.word) > 0) {
                    String temp = position.word;
                    position.word = nextPosition.word;
                    nextPosition.word = temp;
                }
                nextPosition = nextPosition.next; //Move to next node inside inner loop
            }
            position = position.next; //Move to next node inside outer loop
        }

    }

    /**
     * Method to clone the list of words
     * @return a deep copy of the list of words
     */
    @Override
    public SinglyLinkedList clone() {
        SinglyLinkedList copy = new SinglyLinkedList();
        Node position = head;
        while (position != null) {
            copy.addAtEnd(position.word);
            position = position.next;
        }
        return copy;
    }

    /**
     * Node class to create a node object for the singly linked list
     */
    private class Node {
        private String word;
        private Node next;

        /**
         * Default constructor to create a node with null values
         */
        public Node() {
            word = null;
            next = null;

        }

        /**
         * Constructor to create a node with a word and next node
         * @param word the word to store in the node
         * @param next the next node in the list
         */
        public Node(String word, Node next) {
            this.word = word;
            this.next = next;
        }

    }

}
