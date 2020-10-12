/*
 * Linked List
 */
package Practice;
import java.util.*;
/**
 *
 * @author ricks
 */
//-1 represents empty node
class Node{
    Node next;
    int data;
    
    public Node(){
        this.data = -1;
    }
    public Node(int data){
        this.data = data;
    }
    public Node(int data, Node nextNode){
        this.data = data;
        this.next = nextNode;
    }
    
}

public class LinkedListFunctions{
    //add node to end of linked list
    public Node append(Node head, int data){
        if(head.data == -1){
            return new Node(data);
        }
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = new Node(data);
        return head;
    }
    
    //add new node to start of linked lsit
    public Node prepend(Node head, int data){
        if(head.data == -1){
            return new Node(data);
        }
        return new Node(data, head);
    }
    
    //insert node after specific value
    public Node insertAfter(Node head, int data, int key){
        //key not found
        if(head.data == -1){
            throw new RuntimeException("Key not found");
        }
        
        Node current = head;
        while(current != null){
            if(current.data == key){
                Node temp = current.next.next;
                Node next = new Node(data);
                current.next = next;
                next.next = temp;
                return head;
            }
        }
        //if key not found
        throw new RuntimeException("Key not found");
    }
    
    //Deletes node with matching key
    public Node remove(Node head, int key){
        //if empty list
        if(head.data == -1)
            throw new RuntimeException("Cannot delete, empty list");
        //if head is the wanted node
        if(head.data == key){
            return head.next;
        }
        
        Node current = head;
        while(current.next != null){
            if(current.next.data == key){
               current.next = current.next.next;
                return head;
            }
        }
        throw new RuntimeException("key not found in linked list");
    }
    
    //removes last node in linked list
    public Node delete(Node head){
        if(head.data == -1)
            throw new RuntimeException("Empty");
        if(head.next == null){
            return new Node();
        }
        Node current = head;
        while(current.next.next != null){
            current = current.next;
        }
        current.next = null;
        return head;
    }
    
    //clones the linked list and returns the cloned linked list
    /**
     *
     * @return cloned linked list
     */
    public Node cloneLL(Node head){
        Node result = new Node();
        if(head.data == -1)
            return new Node();
        Node current = head;
        while(current != null){
            append(result, current.data);
            current = current.next;
            result = result.next;
        }
        return result;
    }

    //displays linked list
    public void displayLL(Node head){
        if(head.data == -1){
            System.out.println("Empty");
            return;
        }
        Node current = head;
        while(current != null){
            if(current.data == -1)
                System.out.println("Empty ");
            else
                System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("");
    }
    
    
    public int countNodes(Node head){
        if(head.data == -1)
            return 0;
        Node current = head;
        int number = 0;
        while(current != null){
            number++;
            current = current.next;
        }
        return number;
    }
    
    
    //populate linked list with random values
    public Node populateLinkedList(Node head){
        Random rand = new Random();

        //appending data
        for(int i = rand.nextInt(10); i > 0; i--){
            head = append(head, rand.nextInt(100) );
        }        
        //prepending data
        for(int i = rand.nextInt(10); i > 0; i--){
            head = prepend(head, rand.nextInt(100) );
        }
        return head;
    }
    
    //retruns values of node in middle of linked list or 2nd element in middle if even
    public int middleValue(Node head){
        if(head.data == -1)
            throw new RuntimeException("Empty");
        else if(head.next == null)
            return head.data;
        
        Node turtle = head;//1X speed
        Node rabbit = head;//2X speed
        while(rabbit != null && rabbit.next != null){
            turtle = turtle.next;
            rabbit = rabbit.next.next;
        }
        
        return turtle.data;
    }
    
    //reverses a linked list
    public Node reverseLL(Node head){
        if(head.data == -1 || head.next == null)
            return head;
        
        Node current = head.next;
        Node prev = null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    //decimal vlaue from linked list that contains a binary number
    public int binary2Decimal(Node head){
        if(head == null)
            return 0;
        int result = 0;
        Node runner = head;
        int power = countNodes(head);
        while(runner != null){
            result += (1<<power)*runner.data;
            runner = runner.next;
            power--;
        }
        return result;
    }
    
    //Nth value from end of linked list
    public int nthValueFromEnd(Node head, int dist){
        if(countNodes(head) < dist)
            throw new RuntimeException("Not in range");
        Node first = head;
        Node second = head;
        for(int i = dist; i > 0; i--)
            first = first.next;
        while(first.next != null){
            second = second.next;
            first = first.next;
        }
        return second.data;
    }
    
    //merging 2 sorted linked lists which are not empty
    public Node mergeLL(Node A, Node B){
        //check empty linked list cases
        if(A == null)
            return B;
        else if(B == null)
            return A;
        
        //choose starting node and recursive case 
        if(A.data <= B.data){
            A.next = mergeLL(A.next, B);
            return A;
        }
        else{
            B.next = mergeLL(B.next, A);
            return B;
        }
    }
    
    //removes duplicate nodes in a sorted linked list
    public Node deleteDuplicates(Node head){
        if(head == null)
            return null;
        
        Node current = head;
        while(current != null && current.next != null){
            if(current.data == current.next.data){
                current.next = current.next.next;
            }
            else
                current = current.next;
        }
        return head;
    }
    
    //makes a cycle in linked list assumption that there is no cycle in it
    /**
     *
     * @param head beginning of linked list
     * @param connector which node to connect the end on the linked list with
     * @return a linked list that contains a cycle
     */
    public Node makeCycle(Node head, Node connector){
        if(head == null)
            return null;
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = connector;
        
        return head;
    }
    
    //checks if linked list contains cycle
    public boolean hasCycle(Node head){
        if(head == null || head.next == null)
            return false;
        
        Node turtle = head;//1X speed
        Node rabbit = head.next;//2X speed
        
        while(rabbit != null && rabbit.next != null){
            if(rabbit == turtle){
                return true;
            }
            turtle = turtle.next;
            rabbit = rabbit.next.next;
        }
        
        return false;
    }
    
    /*finds where the intersection of two linked lists starts
    * Assumptions:
    * not circular
    * 
    */
    public Node getIntersectionNode(Node A, Node B){
        if(A == null || B == null)
            return null;
        
        Node a_pointer = A;
        Node b_pointer = B;
        while(a_pointer.data != b_pointer.data){
            if(a_pointer == null)
                a_pointer = B;
            
            if(b_pointer == null)
                b_pointer = A;
            
            if(a_pointer == b_pointer)
                return a_pointer;
        
            a_pointer = a_pointer.next;
            b_pointer = b_pointer.next;
        }
        
        return a_pointer;
    }
    
    /**
     *
     * @param head a linked list
     * @return an int array of values that contains the next biggest data in linked list
     */
    public int[] nextLargerNodes(Node head){
        if(head == null)
            return null;
        
        int[] result = new int [countNodes(head)];
        Node runner, current = head;
        
        for(int i = 0; current != null; i++){
            runner = current.next;
            
            while(runner != null){
                if(runner.next == null && runner.data < current.data){
                    result[i] = 0;
                    break;
                }
                if(runner.data > current.data){
                    result[i] = runner.data;
                    break;
                }
                runner = runner.next;
            }
            
            current = current.next;
        }
        
        
        return result;
    }
    
    //check if the linked list is a palindrome
    public boolean isPalindrome(Node head){
        //check if empty linked list
        if(head == null)
            throw new RuntimeException("Empty linked list");
        
        Stack stack = new Stack();
        Node runner = head;
        
        //stack will contain reversed linked list
        while(runner != null){
            stack.push(runner.data);
            runner = runner.next;
        }
        
        //compare linked list and stack data 
        while(head != null){
            if(stack.pop() != head.data)
                return false;
            head = head.next;
        }
  
        return true;
    }
    
    public Node addOne(Node head){
        if(head == null)
            return new Node(1);
        
        head = reverseLL(head);
        Node runner = head;
        
        while( (runner.data+1)%10 == 0){
            if(runner.next == null){
                runner.data = 1;
                runner.next = new Node(1);
                break;
            }
            else{
                runner.data = 0;
                
            }
            runner = runner.next;
                
        }
        
        head = reverseLL(head);
        return head;
    }
    
}