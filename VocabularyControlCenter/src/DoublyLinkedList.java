public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int count; //to count the number of topics in the list

    public DoublyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public void addAtHead(String newTopic) {
        Node oldHead = head; //store the current head
        Node position = head;
        //Check if topic already exists in the list
        while (position != null) {
            if (position.vocab.getTopic().equals(newTopic)) {
                System.out.println("Sorry, the topic \'" + newTopic + "\' is already listed.");
                return;
            }
            position = position.next;
        }
        //Add topic to list
        Vocab newVocab = new Vocab(newTopic);
        head = new Node(newVocab, null, head);

        if (tail == null) { //if list is empty
            tail = head;
        } else {
            oldHead.prev = head; //set the previous node of the old head to the new head
        }
        count++;
    }

    public void addAtTail(String newTopic) {
        Node position = head;
        //Check if topic already exists in the list
        while (position != null) {
            if (position.vocab.getTopic().equals(newTopic)) {
                System.out.println("Sorry, the topic \'" + newTopic + "\' is already listed.");
                return;
            }
            position = position.next;
        }

        //Add topic to list
        Vocab newVocab = new Vocab(newTopic);

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

    public void addBefore(String topicReference, String newTopic) {
        if (head == null) {
            return;
        } else if (head.vocab.getTopic().equals(topicReference)) {
            addAtHead(newTopic);
        } else {
            //Check if topic already exists in the list
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(topicReference))) {
                if (position.vocab.getTopic().equals(newTopic)) {
                    System.out.println("Sorry, the topic \'" + newTopic + "\' is already listed.");
                    return;
                }
                position = position.next;
            }
            if (position != null) {
                Vocab newVocab = new Vocab(newTopic);
                Node n = new Node(newVocab, position.prev, position);
                position.prev.next = n;
                position.prev = n;
                count++;
            }
        }

    }

    public void addAfter(String topicReference, String newTopic) {
        if (head == null) {
            return;
        } else {
            //Check if topic already exists in the list
            Node position = head;
            while (position != null && !(position.vocab.getTopic().equals(topicReference))) {
                if (position.vocab.getTopic().equals(newTopic)) {
                    System.out.println("Sorry, the topic \'" + newTopic + "\' is already listed.");
                    return;
                }
                position = position.next;
            }
            if (position != null && position.vocab.getTopic().equals(topicReference)) {
                if (position.next == null) {
                    addAtTail(newTopic);

                } else {
                    Vocab newVocab = new Vocab(newTopic);
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
            position.prev.next = position.next;
            position.next.prev = position.prev;
            count--;
        }

        //Check if topic exists in the list
    }

    private class Node {
        private Vocab vocab;
        private Node next;
        private Node prev;

        public Node(Vocab vocab, Node prev, Node next) {
            this.vocab = vocab.clone(); //create a deep copy of the vocab object
            this.next = next;
            this.prev = prev;
        }

    }

}
