import java.util.List;
import java.util.ArrayList;
public class LinkedListDeque<T> implements Deque<T>{
    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(sentinel,x,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(sentinel.prev,x,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node s = sentinel.next;
        while (s.item != null){
            returnList.add(s.item);
            s = s.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(this.size() == 0){
            return null;
        }else {
            this.sentinel.next.next.prev = this.sentinel;
            this.sentinel.next = this.sentinel.next.next;
            size = size - 1;
            return this.sentinel.item;
        }
    }

    @Override
    public T removeLast() {
        if(this.size() == 0){
            return null;
        }else {
            this.sentinel.prev.prev.next = this.sentinel;
            this.sentinel.prev = this.sentinel.prev.prev;
            size = size -1;
            return this.sentinel.item;
        }
    }

    @Override
    public T get(int index) {

        Node ptr = this.sentinel;
        if (index > this.size()){
            return null;
        }else if(index < 1){
            return null;
        }else
            while (ptr.next != sentinel){
            ptr = ptr.next;
            if (index == 1){
                return ptr.item;
            }
            index = index -1;
        }
        return null;
    }


    public T getRecursivehelper(int index,Node ptr){
        if (index == 1){
            return ptr.item;
        }
        return getRecursivehelper(index-1,ptr.next);
    }
    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0){
            return null;
        }
        Node ptr = sentinel.next;
        return getRecursivehelper(index,ptr);
    }

    public class Node{
        Node prev;
        T item;
        Node next;

        public Node(Node x,T y,Node z){
            prev = x;
            item = y;
            next = z;
        }
    }

    int size = 0;
    Node sentinel;


    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
}