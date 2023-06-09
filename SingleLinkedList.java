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
    void printList();

}

class SinglyLinkedList implements ILinkedList{

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
        Node dummy = new Node(0);
        Node tailDummy = this.tail;
        this.head = dummy;
        this.tail = dummy;
        head.next = tailDummy;
        tail.next = null;
    }

    //add to the end of the list
    public void add(int element){
        Node newNode = new Node(element);
        if(head.next == null){
            head.next = newNode;
        }else{
            Node lastNode = head.next;
            while(lastNode.next != tail){
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
            
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
            new_Node.next = head.next;
            head.next = new_Node;
            return;
        }
        Node currentCheck = head;
        for(int i = 0; i < index ;i++){
            currentCheck = currentCheck.next;
        }
        new_Node.next = currentCheck.next;
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
            prevNode.next = nextNode;
            currentNode.next = null;

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
        ILinkedList sub_list = new SinglyLinkedList();
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

    
}

public class SingleLinkedList{
    public static void main(String[] args){
        ILinkedList list = new SinglyLinkedList();
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
            ILinkedList New_list = new SinglyLinkedList();
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