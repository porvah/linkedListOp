import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {

    public void addToIndex(int index, int element);

    public void add(int element);

    public int get(int index);

    public void set(int index, int element);

    public void clear();

    public boolean isEmpty();

    public void remove(int index);

    public int size();

    public ILinkedList sublist(int fromIndex, int toIndex);

    public boolean contains(int o);
    public void printList();
    
}
   
class DoubleLinkedList implements ILinkedList {
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
}

interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPolynomial'");
    }

    @Override
    public String print(char poly) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }

    @Override
    public void clearPolynomial(char poly) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearPolynomial'");
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluatePolynomial'");
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subtract'");
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }
}