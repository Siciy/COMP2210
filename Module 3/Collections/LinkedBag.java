public class LinkedBag<T> {
    private class Node {
        private Node prev;
        private int value;
        private Node next;
        public Node(int value) {
            value = this.value;
        }
        public Node(int value, Node next) {
            next = this.next;
            value = this.value;
        }
        public Node(Node prev, int value, Node next) {
            prev = this.prev;
            value = this.value;
            next = this.next;
        }
        
    }
}
