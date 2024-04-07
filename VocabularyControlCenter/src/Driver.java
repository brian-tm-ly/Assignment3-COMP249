public class Driver {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addAtHead("apple");
        // list.addAtHead("banana");
        // list.addAtHead("cherry");
        // list.addAtHead("avocado");
        // list.addAtHead("Blueberry");
        // list.addAtHead("blackberry");
        // list.addAtHead("grape");
        // list.addAtHead("kiwi");
        // list.display();
        System.out.println(list.remove("apple"));
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
    }
}
