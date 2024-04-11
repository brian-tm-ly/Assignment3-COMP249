public class SinglyLinkedList {

    private Node head;
    private int count; //to count the number of words in the list

    public SinglyLinkedList() {
        head = null;
        count = 0;

    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public String getWord() {
        return head.word;
    }

    public int size() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    //Add word at beginning of list
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

    //Modify a word in the list
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

    //Remove a word in the list
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

    //Display the list
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

    //Sort the list in lexicographical order
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

    //Private inner class to create nodes

    private class Node {
        private String word;
        private Node next;

        public Node() {
            word = null;
            next = null;

        }

        public Node(String word, Node next) {
            this.word = word;
            this.next = next;
        }

        public String getWord() {
            return word;
        }
    }

}
