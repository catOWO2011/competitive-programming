import java.util.HashMap;

public class p146 {

  class LRUCache {
    class Node<V> {
      Node<V> prev = null;
      Node<V> next = null;
      V value;

      public Node(V value) {
        this.value = value;
      }
    }

    Node<Integer> head = null;
    Node<Integer> tail = null;
    HashMap<Integer, Node<Integer>> nodeMap = null;
    HashMap<Integer, Integer> cacheMap = null;
    int capacity;

    public LRUCache(int capacity) {
      this.capacity = capacity;
      nodeMap = new HashMap<>();
      cacheMap = new HashMap<>();
    }

    public int get(int key) {
      if (cacheMap.containsKey(key)) {
        removeNode(nodeMap.get(key));
        Node<Integer> newNode = addNode(key);
        nodeMap.put(key, newNode);
        return cacheMap.get(key);
      }
      return -1;
    }

    public void put(int key, int value) {
      if (cacheMap.containsKey(key)) {
        removeNode(nodeMap.get(key));
      } else if (cacheMap.size() == capacity) {
        Node<Integer> node = removeNode(head);
        nodeMap.remove(node.value);
        cacheMap.remove(node.value);
      }
      Node<Integer> newNode = addNode(key);
      nodeMap.put(key, newNode);
      cacheMap.put(key, value);
    }

    private Node<Integer> removeNode(Node<Integer> node) {
      if (node == head && node == tail) {
        head = tail = null;
      } else if (node == head) {
        head = node.next;
      } else if (node == tail) {
        tail = node.prev;
      }

      if (node.prev != null) {
        node.prev.next = node.next;
      }

      if (node.next != null) {
        node.next.prev = node.prev;
      }

      node.prev = node.next = null;

      return node;
    }

    private Node<Integer> addNode(int value) {
      Node<Integer> newNode = new Node<Integer>(value);
      if (head == null) {
        head = tail = newNode;
      } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
      }
      return newNode;
    }
  }

  public void solve() {
    LRUCache lruCache = new LRUCache(2);
    System.out.println();
  }

  public static void main(String[] args) {
    new p146().solve();
  }
}