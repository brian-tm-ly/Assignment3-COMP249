import java.util.ArrayList;

public class Vocab {
    private String topic;
    //Single linked list to store words within vocab objects
    private SinglyLinkedList words;

    public Vocab() {
        topic = "";
        words = new SinglyLinkedList();
    }

    public Vocab(String topic) {
        this.topic = topic;
        words = new SinglyLinkedList();
    }

    public Vocab(String topic, SinglyLinkedList words) {
        this.topic = topic;
        this.words = words.clone(); //create a deep copy of the words list
    }

    public SinglyLinkedList getWords() {
        return this.words;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addWord(String newWord) {
        words.addAtHead(newWord);
    }

    public void removeWord(String wordToRemove) {
        words.remove(wordToRemove);
    }

    public void modifyWord(String oldWord, String newWord) {
        words.modify(oldWord, newWord);
    }

    @Override
    public String toString() {
        return "Topic: " + topic + "\n";
    }

    @Override
    public Vocab clone() {
        return new Vocab(topic, words.clone()); //return a deep copy of the vocab object
    }

}
