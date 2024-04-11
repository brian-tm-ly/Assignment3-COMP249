import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int count; // to count the number of topics in the list

    public DoublyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public Vocab getVocab() {
        return head.vocab;
    }

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

    public ArrayList<String> startingWith(String prefix) {
        ArrayList<String> wordsStartingWith = new ArrayList<>();
        Node position = head;
        while (position != null) {
            SinglyLinkedList words = position.vocab.getWords();
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

    //Find topic in list based on topic number
    public Vocab find(int topicChoice) {
        Node position = head;
        int counter = 1; // to keep track of the topic number
        while (position != null && counter != topicChoice) {
            position = position.next;
            counter++;
        }
        return position.vocab; // return the vocab object

    }

    public Vocab findWord(String word) {
        Node position = head;
        while (position != null) {
            SinglyLinkedList words = position.vocab.getWords();
            for (int i = 0; i < words.size(); i++) {
                if (words.getWordAt(i).equals(word))
                    return position.vocab;
            }
            position = position.next;
        }
        return null;
    }

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
            tail = head;
        } else {
            oldHead.prev = head; // set the previous node of the old head to the new head
        }
        count++;
    }

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
            head = new Node(newVocab, null, null);
            tail = head;
        } else {
            Node oldTail = tail;
            tail = new Node(newVocab, tail, null);
            oldTail.next = tail;
        }
        count++;
    }

    public void addBefore(Vocab vocabReference, Vocab newVocab) {
        if (head == null) {
            return;
        } else if (head.vocab.getTopic().equals(vocabReference.getTopic())) {
            addAtHead(newVocab);
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
            if (position != null) {
                Node n = new Node(newVocab, position.prev, position);
                position.prev.next = n;
                position.prev = n;
                count++;
            }
        }

    }

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
            if (position != null && position.vocab.getTopic().equals(vocabReference.getTopic())) {
                if (position.next == null) {
                    addAtTail(newVocab);

                } else {
                    Node newNode = new Node(newVocab, position.next, position);
                    position.next.prev = newNode;
                    position.next = newNode;
                    count++;
                }
            }
        }
    }

    public void remove(String topicToRemove) {
        if (head == null) {
            return;
        } else {
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(topicToRemove))) {
                position = position.next;
            }

            if (position == head) { // if the topic to be removed is the head
                head = position.next;
            }

            if (position.prev != null) {
                position.prev.next = position.next;
            }

            if (position.next != null) {
                position.next.prev = position.prev;
            }

            count--;
        }

        // Check if topic exists in the list
    }

    public void display(String topicToDisplay) {
        if (count == 0) {
            System.out.println("The list is empty.");
        } else {
            Node position = head;

            while (position != null && !(position.vocab.getTopic().equals(topicToDisplay))) {
                position = position.next;
            }
            System.out.print(position.vocab);
            position.vocab.getWords().display();
        }
    }

    public void displayTopics() {
        int wordNum = 1;
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

    private class Node {
        private Vocab vocab;
        private Node next;
        private Node prev;

        public Node(Vocab vocab, Node prev, Node next) {
            this.vocab = vocab.clone(); // create a deep copy of the vocab object
            this.next = next;
            this.prev = prev;
        }

    }

}
