package person;

/** Doubly linked list supporting traversal in both directions. */
public class LInkedlist {
    Node head;
    Node tail;

    public boolean isempty() {
        return head == null;
    }

    void insertatfront(int v) {
        Node n = new Node(v);
        n.next = head;
        if (head != null) head.prev = n;
        else tail = n;
        head = n;
    }

    void insertatback(int v) {
        Node n = new Node(v);
        n.prev = tail;
        if (tail != null) tail.next = n;
        else head = n;
        tail = n;
    }

    void insertbefore(int key, int v) {
        Node current = head;
        while (current != null && current.data != key) current = current.next;
        if (current == null) return;
        if (current == head) {
            insertatfront(v);
            return;
        }
        Node n = new Node(v);
        n.next = current;
        n.prev = current.prev;
        current.prev.next = n;
        current.prev = n;
    }

    void deletefromfront() {
        if (head == null) return;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
    }

    void deletefromback() {
        if (tail == null) return;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = null;
    }

    void deletebyvalue(int key) {
        Node current = head;
        while (current != null && current.data != key) current = current.next;
        if (current == null) return;
        if (current == head) {
            deletefromfront();
            return;
        }
        if (current == tail) {
            deletefromback();
            return;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }

    public int searchrecursive(int key) {
        return searchrecursion(head, key, 0);
    }

    public int searchrecursion(Node n, int key, int index) {
        if (n == null) return -1;
        if (n.data == key) return index;
        return searchrecursion(n.next, key, index + 1);
    }

    void displayforward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + ",");
            current = current.next;
        }
        System.out.println();
    }

    void displaybackward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + ",");
            current = current.prev;
        }
        System.out.println();
    }

    void reverse() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = current.prev;
            current.prev = next;
            current = next;
        }
        Node oldHead = head;
        head = tail;
        tail = oldHead;
    }

    static LInkedlist intersection(LInkedlist a, LInkedlist b) {
        LInkedlist result = new LInkedlist();
        Node first = a.head;
        while (first != null) {
            Node second = b.head;
            boolean found = false;
            while (second != null) {
                if (first.data == second.data) {
                    found = true;
                    break;
                }
                second = second.next;
            }
            if (found && result.searchrecursive(first.data) == -1) {
                result.insertatback(first.data);
            }
            first = first.next;
        }
        return result;
    }

}
