
public class Vocab {
    private String topic;
    //Single linked list to store words within vocab objects
    private SinglyLinkedList words;

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

}
