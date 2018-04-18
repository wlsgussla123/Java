package datastructure;

import java.util.NoSuchElementException;

class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

class Queue {
	protected int size;
	protected Node front, rear;
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void enque(int data) {
		Node oldRear = rear;
		rear = new Node(data);
		
		if(isEmpty()) {
			front = rear;
		} else {
			oldRear.next = rear;
		}
		
		size++;
	}
	
	public int deque() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		int data = front.data;
		front = front.next;
	
		size--;
		if(isEmpty()) {
			rear = null;
		}
		
		return data;
	}
}

class BlockingQueue extends Queue {
	private final int capacity;
	
	public BlockingQueue(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	@Override
	public synchronized boolean isEmpty() {
		return front == null;
	}
	
	public synchronized boolean isFull() {
		return size == capacity;
	}

	@Override
	public synchronized void enque(int data) {
		while(isFull()) {
			try {
				System.out.println("enque wait");
				wait();
				System.out.println("enque notify");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		super.enque(data);
		notifyAll(); // deque에 데이터가 없어서 wait 상태였으면 깨우자
	}

	@Override
	public synchronized int deque() {
		while(isEmpty()) {
			try {
				System.out.println("deque wait");
				wait();
				System.out.println("deque notified");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		return super.deque();
	}
}

public class BlockingQueueMain {
	public static void main(String[] args) {
		BlockingQueue q = new BlockingQueue(1);
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(200);
				q.enque(1);
				Thread.sleep(200);
				q.enque(2);
				q.enque(3);
			} catch(InterruptedException e) {}
		});
		
		t.setName("work");
		t.start();
		
		try {
			System.out.println(q.deque());
			Thread.sleep(1000);
			System.out.println(q.deque());
			System.out.println(q.deque());
		} catch(InterruptedException e) {}
	}
}
