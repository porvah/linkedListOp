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
    class Node {
        int Value;
        Node next;
        Node prev;

        Node(int Value) {
            this.Value = Value;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;

    DoubleLinkedList() {
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

    // add to the end of the list
    public void add(int element) {
        Node newNode = new Node(element);
        if (head.next == null || head.next == tail) {
            head.next = newNode;
            newNode.prev = this.head;
            newNode.next = this.tail;
            tail.prev = newNode;
        } else {
            Node lastNode = tail.prev;
            lastNode.next = newNode;
            this.tail.prev = newNode;
            newNode.prev = lastNode;
        }
        newNode.next = tail;
    }

    public void addToIndex(int index, int element) {
        Node new_Node = new Node(element);
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                Node nextNode = head.next;
                nextNode.prev = new_Node;
                new_Node.next = nextNode;
                head.next = new_Node;
                new_Node.prev = head;

                return;
            }
            Node currentCheck = head;
            for (int i = 0; i < index; i++) {
                currentCheck = currentCheck.next;
            }
            Node nodeI = currentCheck.next;
            nodeI.prev = new_Node;
            new_Node.next = nodeI;
            new_Node.prev = currentCheck;
            currentCheck.next = new_Node;
        }
    }

    // get the element by index
    public int get(int index) {
        int value;
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Node currentNode = this.head;
            while (index >= 0) {
                currentNode = currentNode.next;

                index--;
            }
            value = currentNode.Value;
            return value;
        }

    }

    // set by index
    public void set(int index, int value) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Node currentNode = this.head;
            while (index >= 0) {
                currentNode = currentNode.next;

                index--;
            }
            currentNode.Value = value;
        }
    }

    // get the size of list
    public int size() {
        int res = 0;
        Node currentNode = head;
        while (currentNode.next != null && currentNode.next != tail) {
            currentNode = currentNode.next;
            res++;
        }
        return res;
    }

    // isEmpty method
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(int element) {
        Node currentCheck = this.head.next;
        while (currentCheck != this.tail && currentCheck != null) {
            if (currentCheck.Value == element)
                return true;
            currentCheck = currentCheck.next;
        }
        return false;
    }

    // remove at index method
    public void remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Node prevNode = this.head;
            Node currentNode = this.head.next;
            while (index > 0) {
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

    // clear list method
    public void clear() {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            this.remove(0);
        }
    }

    @Override
    public ILinkedList sublist(int start, int end) {
        ILinkedList sub_list = new DoubleLinkedList();
        if (start >= 0 && end < size() && start <= end) {
            for (int i = start; i <= end; i++) {
                sub_list.add(this.get(i));
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return sub_list;
    }

    // printing the linked list method
    public void printList() {
        Node currNode = head;
        System.out.print("[");
        while (currNode.next != null && currNode.next != tail) {
            currNode = currNode.next;
            System.out.print(currNode.Value);
            if (currNode.next != tail) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}

interface IPolynomialSolver {

    void setPolynomial(char poly, int[][] terms) throws Exception;

    String print(char poly) throws Exception;

    void clearPolynomial(char poly) throws Exception;

    float evaluatePolynomial(char poly, float value) throws Exception;

    int[][] add(char poly1, char poly2) throws Exception;

    int[][] subtract(char poly1, char poly2) throws Exception;

    int[][] multiply(char poly1, char poly2) throws Exception;
}

public class PolynomialSolver implements IPolynomialSolver {
    ILinkedList A = new DoubleLinkedList();
    ILinkedList B = new DoubleLinkedList();
    ILinkedList C = new DoubleLinkedList();
    ILinkedList R = new DoubleLinkedList();

    public static void main(String[] args) {

        IPolynomialSolver solver = new PolynomialSolver();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            try {
                switch (str) {
                    case "set":
                        char setPolyChar = sc.nextLine().toCharArray()[0];
                        String setstr = sc.nextLine();

                        String[] setString = setstr.replace("[", "").replace("]", "").split(",");
                        int[][] setArr = new int[setString.length][2];
                        if (!str.equals("[]")) {
                            for (int i = 0; i < setString.length; i++) {
                                setArr[i][0] = Integer.parseInt(setString[i]);
                                setArr[i][1] = setString.length - 1 - i;
                            }
                            solver.setPolynomial(setPolyChar, setArr);
                        }
                        break;
                    case "print":
                        char printPolyChar = sc.nextLine().toCharArray()[0];
                        System.out.println(solver.print(printPolyChar));
                        break;
                    case "add":
                        char addCharA = sc.nextLine().toCharArray()[0];
                        char addCharB = sc.nextLine().toCharArray()[0];
                        int[][] addRes = solver.add(addCharA, addCharB);
                        solver.setPolynomial('R', addRes);
                        System.out.println(solver.print('R'));
                        break;
                    case "sub":
                        char subCharA = sc.nextLine().toCharArray()[0];
                        char subCharB = sc.nextLine().toCharArray()[0];
                        int[][] subRes = solver.subtract(subCharA, subCharB);
                        solver.setPolynomial('R', subRes);
                        System.out.println(solver.print('R'));
                        break;
                    case "mult":
                        char multCharA = sc.nextLine().toCharArray()[0];
                        char multCharB = sc.nextLine().toCharArray()[0];
                        int[][] multRes = solver.multiply(multCharA, multCharB);
                        solver.setPolynomial('R', multRes);
                        System.out.println(solver.print('R'));
                        break;
                    case "clear":
                        char clearChar = sc.nextLine().toCharArray()[0];
                        solver.clearPolynomial(clearChar);
                        System.out.println("[]");
                        break;
                    case "eval":
                        char evalChar = sc.nextLine().toCharArray()[0];
                        float evalValue = sc.nextFloat();
                        System.out.println((int)solver.evaluatePolynomial(evalChar, evalValue));
                        break;
                    default:
                        System.out.println("Error");
                }
            } catch (Exception e) {
                System.out.println("Error");
                break;
            }
        }
        sc.close();
    }

    ILinkedList listFinder(char poly) throws Exception {
        ILinkedList myList = new DoubleLinkedList();
        boolean found = true;
        switch (poly) {
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
        if (found) {
            return myList;
        } else {
            throw new Exception();
        }
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) throws Exception {
        ILinkedList list = new DoubleLinkedList();
        try {
            list = listFinder(poly);

        } catch (Exception e) {
            throw new Exception();
        }
        for (int i = 0; i < terms.length; i++) {
            list.add(terms[i][0]);
        }
    }

    @Override
    public String print(char poly) throws Exception {
        ILinkedList list = new DoubleLinkedList();
        try {
            list = listFinder(poly);
        } catch (Exception e) {
            throw new Exception();
        }
        if (list.isEmpty()) {
            throw new IllegalStateException();
        }
        String str_print = "";
        boolean null_pol = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                null_pol = true;
                break;
            }
        }
        if (null_pol)
            str_print += "0";
        else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != 1 && list.get(i) != 0) {
                    if (list.get(i) != -1)
                        str_print += String.valueOf(list.get(i));
                    else
                        str_print += "-";
                }
                if (i < list.size() - 1 && list.get(i) != 0) {
                    str_print += "x";
                    if (list.size() - i - 1 > 1)
                        str_print += "^" + String.valueOf(list.size() - i - 1);
                    if (list.get(i + 1) >= 0)
                        str_print += "+";
                }
            }
        }
        return str_print;
    }

    @Override
    public void clearPolynomial(char poly) throws Exception {
        ILinkedList list = new DoubleLinkedList();
        try {
            list = listFinder(poly);
        } catch (Exception e) {
            throw new Exception();
        }
        list.clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) throws Exception{
        // TODO Auto-generated method stub
        float result = 0;
        ILinkedList list = new DoubleLinkedList();
        try{
            list = listFinder(poly);
        }catch (Exception e){ throw new Exception();}
        if(list.isEmpty())
        throw new Exception();
        for(int i = 0; i < list.size(); i++)
            result += list.get(i) * Math.pow(value , list.size() - 1 -i); 
        return result;
    }

    @Override
    public int[][] add(char poly1, char poly2) throws Exception {
        // TODO Auto-generated method stub
        ILinkedList list_1 = new DoubleLinkedList();
        ILinkedList list_2 = new DoubleLinkedList();
        try {
            list_1 = listFinder(poly1);
            list_2 = listFinder(poly2);
        } catch (Exception e) {
            throw new Exception();
        }
        int i = 0;
        int size = 0;
        if (list_1.size() <= list_2.size())
            size = list_2.size();
        else
            size = list_1.size();

        int[][] sum = new int[size][2];
        while (i < list_1.size() && i < list_2.size()) {
            sum[size - i - 1][0] = list_1.get(list_1.size() - i - 1) + list_2.get(list_2.size() - i - 1);
            sum[size - i - 1][1] = i;
            i++;
        }
        if (i != list_1.size()) {
            while (i < list_1.size()) {
                sum[size - i - 1][0] = list_1.get(list_1.size() - i - 1);
                sum[size - i - 1][1] = i;
                i++;
            }
        } else {
            while (i < list_2.size()) {
                sum[size - i - 1][0] = list_2.get(list_2.size() - i - 1);
                sum[size - i - 1][1] = i;
                i++;
            }
        }
        return sum;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) throws Exception {
        // TODO Auto-generated method stub
        ILinkedList list_1 = new DoubleLinkedList();
        ILinkedList list_2 = new DoubleLinkedList();
        try {
            list_1 = listFinder(poly1);
            list_2 = listFinder(poly2);
        } catch (Exception e) {
            throw new Exception();
        }
        int i = 0;
        int size = 0;
        if (list_1.size() <= list_2.size())
            size = list_2.size();
        else
            size = list_1.size();

        int[][] sub = new int[size][2];
        while (i < list_1.size() && i < list_2.size()) {
            sub[size - i - 1][0] = list_1.get(list_1.size() - i - 1) - list_2.get(list_2.size() - i - 1);
            sub[size - i - 1][1] = i;
            i++;
        }
        if (i != list_1.size()) {
            while (i < list_1.size()) {
                sub[size - i - 1][0] = list_1.get(list_1.size() - i - 1);
                sub[size - i - 1][1] = i;
                i++;
            }
        } else {
            while (i < list_2.size()) {
                sub[size - i - 1][0] = -list_2.get(list_2.size() - i - 1);
                sub[size - i - 1][1] = i;
                i++;
            }
        }
        return sub;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) throws Exception {
        // TODO Auto-generated method stub
        ILinkedList list_1 = new DoubleLinkedList();
        ILinkedList list_2 = new DoubleLinkedList();
        try {
            list_1 = listFinder(poly1);
            list_2 = listFinder(poly2);
        } catch (Exception e) {
            throw new Exception();
        }
        if (list_1.isEmpty() || list_2.isEmpty())
            throw new Exception();
        int[][] mult = new int[list_1.size() + list_2.size() - 1][2];
        for (int i = 0; i < list_1.size(); i++) {
            for (int j = 0; j < list_2.size(); j++) {
                mult[i + j][0] += list_1.get(i) * list_2.get(j);
                mult[i + j][1] = list_1.size() + list_2.size() - 1 - i;
            }
        }
        return mult;
    }
}