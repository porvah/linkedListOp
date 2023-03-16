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
public void addToIndex(int index, int element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(int element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public int get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, int element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(int o);
public void printList();

}


public class DoubleLinkedList implements ILinkedList {


	class Node{
        int Value;
        Node next;
        Node prev;
        Node(int Value){
            this.Value = Value;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    DoubleLinkedList(){
        Node dummy = new Node(0);
        Node tailDummy = this.tail;
        Node headDummy = this.head;
        this.head = dummy;
        this.tail = dummy;
        head.next = tailDummy;
        tail.next = null;
        head.prev = null;
        tail.prev = headDummy;
    }

    //add to the end of the list
    public void add(int element){
        Node newNode = new Node(element);
        if(head.next == null || head.next == tail){
            head.next = newNode;
            newNode.prev = this.head;
            newNode.next = this.tail;
            tail.prev = newNode;
        }else{
            Node lastNode = tail.prev;
            lastNode.next = newNode;
            this.tail.prev = newNode;
            newNode.prev = lastNode;
        }
        newNode.next = tail;
    }
    public void addToIndex(int index , int element){
        Node new_Node = new Node(element);
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            if (index == 0){
                Node nextNode = head.next;
                nextNode.prev = new_Node;
                new_Node.next = nextNode;
                head.next = new_Node;
                new_Node.prev = head;

                return;
            }
            Node currentCheck = head;
            for(int i = 0; i < index ;i++){
                currentCheck = currentCheck.next;
            }
            Node nodeI = currentCheck.next;
            nodeI.prev = new_Node;
            new_Node.next = nodeI;
            new_Node.prev = currentCheck;
            currentCheck.next = new_Node;
        }
    }
    //get the element by index
    public int get(int index){
        int value;
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }else{
            Node currentNode = this.head;
            while(index >= 0){
                currentNode = currentNode.next;

                index--;
            }
            value = currentNode.Value;
            return value;
        }

        
    }
    //set by index
    public void set(int index, int value){
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }else{
            Node currentNode = this.head;
            while(index >= 0){
                currentNode = currentNode.next;

                index--;
            }
            currentNode.Value = value;
        }
    }

    //get the size of list
    public int size(){
        int res = 0;
        Node currentNode = head;
        while(currentNode.next != null && currentNode.next != tail){
            currentNode = currentNode.next;
            res++;
        }
        return res;
    }
    
    //isEmpty method
    public boolean isEmpty(){
        if(this.size() == 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean contains(int element){
        Node currentCheck = this.head.next;
        while (currentCheck != this.tail && currentCheck != null) {
            if(currentCheck.Value == element)
            return true;
            currentCheck = currentCheck.next;
        }
        return false;
    }
    //remove at index method
    public void remove(int index){
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }else{
            Node prevNode = this.head;
            Node currentNode = this.head.next;
            while(index > 0){
                prevNode = currentNode;
                currentNode = currentNode.next;
                index--;
            }
            Node nextNode = currentNode.next;
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
            currentNode.next = null;
            currentNode.prev = null;

        }
    }
    //clear list method
    public void clear(){
        int size = this.size();
        for(int i = 0; i < size; i++){
            this.remove(0);
        }
    }
    @Override
    public ILinkedList sublist(int start, int end){
        ILinkedList sub_list = new DoubleLinkedList();
        if(start >= 0 && end < size() && start <= end){
            for(int i = start ; i <= end;i++){
                sub_list.add(this.get(i));
            }
        }
        else{
            throw new IndexOutOfBoundsException();
        }
        return sub_list;
    }
    //printing the linked list method
    public void printList() {
        Node currNode = head;
        System.out.print("[");
        while (currNode.next != null && currNode.next != tail) {
            currNode = currNode.next;
            System.out.print(currNode.Value);
            if(currNode.next != tail){
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
    public static void main(String[] args) {
        ILinkedList list = new DoubleLinkedList();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String oper = sc.nextLine();
        if(!str.equals("[]")){
            str = str.replace("[", "").replace("]", "");
            String[] s = str.split(", ");
            for(int i = 0; i < s.length; i++){
                list.add(Integer.parseInt(s[i]));
                }
        } 
        switch(oper){
            case "add":
                String temp1 = sc.nextLine();
                if(!temp1.equals("")){
                int val1 = Integer.parseInt(temp1);
                list.add(val1);}
                list.printList();
                break;
            case "addToIndex":
                int index = sc.nextInt();
                int val2  = sc.nextInt();
                try{list.addToIndex(index,val2);
                list.printList();
                }catch(Exception e){System.out.print("Error");}
                break;
            case "get":
                int getIndex = sc.nextInt();
                try{
                System.out.print(list.get(getIndex));
                }catch(Exception e){System.out.print("Error");}
                break;
            case "set":
                int setIndex = sc.nextInt();
                int setVal = sc.nextInt();
                try{
                list.set(setIndex, setVal);
                list.printList();
                }catch(Exception e){System.out.println("Error");}
                break;
            case "isEmpty":
                System.out.print(list.isEmpty()? "True" : "False");
                break;
            case "size":
                System.out.print(list.size());
                break;
            case "remove":
                int remIndex = sc.nextInt();
                try{
                    list.remove(remIndex);
                    list.printList();
                }catch(Exception e){System.out.print("Error");}
                break;
            case "clear":
                list.clear();
                list.printList();
                break;
            case "contains":
            int val3 = sc.nextInt();
            if(list.contains(val3))
            System.out.print("True");
            else
            System.out.print("False");
            break;
            case "sublist":
            int start = sc.nextInt();
            int end = sc.nextInt();
            ILinkedList New_list = new DoubleLinkedList();
            try{
            New_list = list.sublist(start, end);
            New_list.printList();
            }catch(Exception e){System.out.println("Error");}
            break;
            default:
            System.out.print("Error");
        } 
    }
}