//Task_6
public class Task_6 
{
    static class Node 
    {
        int data;
        Node next;

        Node(int data) 
        {
            this.data = data;
            this.next = null;
        }
    }

    public boolean hasCycle(Node head) 
    {
        if (head == null) 
        {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) 
        {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) 
            {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) 
    {
        Task_6 list = new Task_6();

        // Creating a linked list with a cycle for testing
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head.next.next; // Creating a cycle

        boolean result = list.hasCycle(head);
        System.out.println(result);  // It should print true
    }
}
