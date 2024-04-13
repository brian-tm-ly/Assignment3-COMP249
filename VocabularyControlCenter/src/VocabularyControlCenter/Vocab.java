package VocabularyControlCenter;
//Brian Ly (40028072), Valerie Nguyen (40284261)

//COMP249
//Assignment #3
//Due April 15, 2024

/**
 * Vocab class to create a vocabulary object with a topic and a list of words
 * @author Brian, Valerie
 * @version 1.0
 * @see DoublyLinkedList
 * @see SinglyLinkedList
 * @see Driver
 */
public class Vocab {

    private String topic;
    //Single linked list to store words within vocab objects
    private SinglyLinkedList words;

    /**
     * Default constructor to create a vocab object with an empty topic and an empty list of words
     */
    public Vocab() {
        topic = "";
        words = new SinglyLinkedList();
    }

    /**
     * Constructor to create a vocab object with a topic and an empty list of words
     * @param topic the topic of the vocab object
     */
    public Vocab(String topic) {
        this.topic = topic;
        words = new SinglyLinkedList();
    }

    /**
     * Constructor to create a vocab object with a topic and a list of words
     * @param topic the topic of the vocab object
     * @param words the list of words in the vocab object
     */
    public Vocab(String topic, SinglyLinkedList words) {
        this.topic = topic;
        this.words = words.clone(); //create a deep copy of the words list
    }

    /**
     * Getter method to get the list of words in the vocab object
     * @return SinglyLinkedList words
     */
    public SinglyLinkedList getWords() {
        return this.words.clone();
    }

    /**
     * Getter method to get the topic of the vocab object
     * @return String topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Setter method to set the topic of the vocab object
     * @param topic the topic of the vocab object
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Method to add a word to the list of words in the vocab object
     * @param newWord the word to be added
     */
    public void addWord(String newWord) {
        words.addAtHead(newWord);
    }

    /**
     * Method to remove a word from the list of words in the vocab object
     * @param wordToRemove the word to be removed
     */
    public void removeWord(String wordToRemove) {
        words.remove(wordToRemove);
    }

    /**
     * Method to modify a word in the list of words in the vocab object
     * @param oldWord the word to be modified
     * @param newWord the new word to replace the old word
     */
    public void modifyWord(String oldWord, String newWord) {
        words.modify(oldWord, newWord);
    }

    /**
     * Method to sort the list of words in the vocab object
     */
    public void sortWords() {
        words.sort();
    }

    /**
     * Method to display the list of words in the vocab object
     */
    @Override
    public String toString() {
        return "Topic: " + topic + "\n";
    }

    /**
     * Method to clone a vocab object
     */
    @Override
    public Vocab clone() {
        return new Vocab(topic, words.clone()); //return a deep copy of the vocab object
    }

}
