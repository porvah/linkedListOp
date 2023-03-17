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
    
    void setPolynomial(char poly, int[][] terms) throws Exception;
  
    String print(char poly);
  
    void clearPolynomial(char poly);
  
    float evaluatePolynomial(char poly, float value);
  
    int[][] add(char poly1, char poly2);

    int[][] subtract(char poly1, char poly2);

    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{
    ILinkedList A = new DoubleLinkedList();
    ILinkedList B = new DoubleLinkedList();
    ILinkedList C = new DoubleLinkedList();
    ILinkedList R = new DoubleLinkedList();
    public static void main(String[] args) {
        
        IPolynomialSolver solver = new PolynomialSolver();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            switch(str){
                case "set":
                    char setPolyChar = sc.nextLine().toCharArray()[0];
                    String setstr = sc.nextLine();
                  
                    String[] setString = setstr.replace("[", "").replace("]", "").split(",");
                    int[][] setArr = new int[setString.length][2];
                    if(!str.equals("[]")){
                        for(int i = 0; i < setString.length; i++){
                            setArr[i][0] = Integer.parseInt(setString[i]);
                            setArr[i][1] = setString.length - 1 - i;
                        }
                        try{
                            solver.setPolynomial(setPolyChar, setArr);
                        }catch(Exception e){
                            System.out.println("Error");
                        }
                        
                    }
                    break;
                case "print":
                    char printPolyChar = sc.nextLine().toCharArray()[0];
                    try{System.out.println(solver.print(printPolyChar));}catch(Exception e){System.out.println("Error");}
                    break;
                case "add":
                    char addCharA = sc.nextLine().toCharArray()[0];
                    char addCharB = sc.nextLine().toCharArray()[0];
                    try{
                        int[][] addRes = solver.add(addCharA, addCharB);
                        solver.setPolynomial('R', addRes);
                        System.out.println(solver.print('R'));
                    }catch(Exception e){System.out.print("Error");}
                    break;
                case "sub":
                    char subCharA = sc.nextLine().toCharArray()[0];
                    char subCharB = sc.nextLine().toCharArray()[0];
                    try{
                        int[][] subRes = solver.subtract(subCharA, subCharB);
                        solver.setPolynomial('R', subRes);
                        System.out.println(solver.print('R'));
                    }catch(Exception e){System.out.print("Error");}
                    break;
                case "mult":
                    char multCharA = sc.nextLine().toCharArray()[0];
                    char multCharB = sc.nextLine().toCharArray()[0];
                    try{
                        int[][] multRes = solver.multiply(multCharA, multCharB);
                        solver.setPolynomial('R', multRes);
                        System.out.println(solver.print('R'));
                    }catch(Exception e){System.out.print("Error");}
                    break;
                case "clear":
                    char clearChar = sc.nextLine().toCharArray()[0];
                    try{
                        solver.clearPolynomial(clearChar);
                        System.out.println(solver.print(clearChar));
                    }catch(Exception e){System.out.println("Error");}
                    break;
                case "eval":
                    char evalChar = sc.nextLine().toCharArray()[0];
                    float evalValue = sc.nextFloat();
                    try{
                        System.out.println(solver.evaluatePolynomial(evalChar, evalValue));
                    }catch(Exception e){System.out.println("Error");}
                    
                    break;
                default:
                    System.out.println("Error");
            }
        }
        sc.close();
    }
    ILinkedList listFinder(char poly) throws Exception{
        ILinkedList myList = new DoubleLinkedList();
        boolean found = true;
        switch(poly){
            case 'A':
                myList = A;
                break;
            case 'B':
                myList = B;
                break;
            case 'C':
                myList = C;
                break;
            case 'R':
                myList = R;
                break;
            default:
                found = false;
        }
        if(found){
            return myList;
        }else{
            throw new Exception();
        }
    }
    
//comment 3beet
    @Override
    public void setPolynomial(char poly, int[][] terms) throws Exception {
        ILinkedList list = new DoubleLinkedList();
        try{
            list = listFinder(poly);
        }catch(Exception e){throw new Exception();}
        for(int i = 0; i < terms.length; i++){
            list.add(terms[i][0]);
        }
    }

    @Override
    public String print(char poly) {
        // TODO Auto-generated method stub
        ILinkedList list = listFinder(poly);
        String str_print = "[";
        for(int i = 0; i < list.size(); i++){
            str_print += (char) list.get(i);
                if(i < list.size() - 1)
                str_print += ",";
        }
        str_print += "]"
        return str_print;
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