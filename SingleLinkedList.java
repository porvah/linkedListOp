import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

  interface ILinkedList {
    /**
    * Inserts a specified element at the specified position in the list.
    * @param index
    * @param element
    */
    //public void add(int index, Object element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element
    */
    public void add(int element);
    /**
    * @param index
    * @return the element at the specified position in this list.
    */
    /*public Object get(int index);

    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    //public void set(int index, Object element);
    /**
    * Removes all of the elements from this list.
    */
    //public void clear();
    /**
    * @return true if this list contains no elements.
    */
    //public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index
    */
    //public void remove(int index);
    /**
    * @return the number of elements in this list.
    */
    //public int size();
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    //public ILinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    //public boolean contains(Object o); 

}




class SinglyLinkedList {

    class Node{
        int Value;
        Node next;
        Node(int Value){
            this.Value = Value;
            this.next = null;
        }
    }

    Node head;
    Node tail;
    SinglyLinkedList(){
        head.next = tail;
        tail.next = null;
    }

    //add to the end of the list
    public void add(int element){
        Node newNode = new Node(element);
        Node lastNode = head;
        while(lastNode.next != tail){
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;
        newNode.next = tail;
    }
    //printing the linked list method
    void printList() {
        Node currNode = head;
        System.out.print("[");
        while (currNode != null) {
            if( currNode.next != null)
            System.out.print(currNode.Value + ", ");
            else
            System.out.print(currNode.Value);
            currNode = currNode.next;
        }
        System.out.print("]");
    }
}

public class SingleLinkedList {
    public static void main(String[] args){
        SinglyLinkedList list = new SinglyLinkedList();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String oper = sc.nextLine();
        String Value_1 = sc.nextLine();
        str = str.replace("[", "").replace("]", "");
        String[] s = str.split(", ");
        for(int i = 0; i < s.length; i++){
            list.add(Integer.parseInt(s[i]));
        }
        list.add(356);
        list.printList();
    }
}
