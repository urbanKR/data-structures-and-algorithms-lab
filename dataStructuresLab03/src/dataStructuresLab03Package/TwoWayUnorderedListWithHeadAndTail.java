package dataStructuresLab03Package;



import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object=e;
		}
		public Element(E e, Element next, Element prev) {
			//TODO
			object = e;
			this.next = next;
			this.prev = prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}

	Element head;
	Element tail;
	// can be realization with the field size or without
	private int size;

	private class InnerIterator implements Iterator<E>{
		Element pos;
		// TODO maybe more fields....

		public InnerIterator() {
			//TODO
			pos = head;
		}
		@Override
		public boolean hasNext() {
			if(pos != null)
			{
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if(pos == null) 
			{
				throw new NoSuchElementException();
			}
			E x = pos.object;
			pos = pos.next;
			
			return x;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		Element p;
		// TODO maybe more fields....
		public InnerListIterator() {
			p = head;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();

		}

		@Override
		public boolean hasNext() {
			if(p != null)
			{
				return p.next!=null;
			}
			return false;
		}

		@Override
		public boolean hasPrevious() {
			if(p != null)
			{
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if(p.next == null) 
			{
				throw new NoSuchElementException();
			}
			p = p.next;
			
			return p.object;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			if(p == null) 
			{
				throw new NoSuchElementException();
			}
			
			E x = p.object;
			p = p.prev;
			
			return x;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

		@Override
		public void set(E e) {
			p.object = e;
		}
	}

	public TwoWayUnorderedListWithHeadAndTail() {
		// make a head and a tail	
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean add(E e) {
		//TODO
		if(tail == null) 
		{
			tail = new Element(e);
			head = tail;
		}
		else
		{
			Element curr = head;
			while(curr.next != null) {
				curr = curr.next;
			}
			tail.next = new Element(e, null, tail);
			tail = tail.next;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		Element curr = head;
		Element newEl = new Element(element);
		if(index < 0 || index > size())
		{
			throw new NoSuchElementException();
		}
		else if(index == size())
		{
			add(element);
			return;
		}
		if(index == 0)
		{
			Element x = head;
			head = newEl;
			head.next = x;
			x.prev = head;
			return;
		}
			int count = 0;
			while(count < index) {
				curr = curr.next;
				count++;
			}
				newEl.next = curr;
				newEl.prev = newEl.next.prev;
				newEl.prev.next = newEl;
				
				newEl.next.prev = newEl;
				size++;
	}

	@Override
	public void clear() {
		head = null;
		//head.next = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean contains(E element) {
		Element elem = head.next;
		
		while(elem != null) {
			if(elem.object.equals(element))
			{
				return true;
			}
			elem = elem.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		Element nextEl = head;
		int count = 0;
		while(count < index) {
			nextEl = nextEl.next;
			count++;
		}
		return nextEl.object;
	}

	@Override
	public E set(int index, E element) {
		if(index < 0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		Element nextEl = head;
		int count = 0;
		while(count < index) {
			nextEl = nextEl.next;
			count++;
		}
		E copy = nextEl.object;
		nextEl.object = element;
		return copy;
	}

	@Override
	public int indexOf(E element) {
		if(isEmpty())
		{
			return -1;
		}
		int count = 0;
		Element nextEl = head;
		while(nextEl.next != null) {
			if(element.equals (nextEl.object)) 
			{
				return count;
			}
			nextEl = nextEl.next;
			count++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		//TODO
		return head == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) { 
		if(index < 0 ||  index  >= size)
		{
			throw new NoSuchElementException("error");
		}
		Element nextEl = head;
		int count = 0;
		while(count < index) {
			
			count++;
			nextEl = nextEl.next;
		}
	
		if(size < 2) 
		{
			clear();
			return nextEl.object;
		}
		if(index == size - 1) 
		{
			tail = tail.prev;
			tail.next = null;
		}
		else 
		{
			nextEl.next.prev = nextEl.prev;
		}
		if(index == 0) 
		{
			head = head.next;    
			head.prev = null;
		}
		else 
		{
			nextEl.prev.next = nextEl.next;
		}
		size--;
		return nextEl.object;
	
	}

	@Override
	public boolean remove(E e) {
		int pos = indexOf(e);
		if(pos == -1) 
		 {
			return false;
		}
		remove(pos);
		
		return true;
	}

	@Override
	public int size() {
		return size;
	}
	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
		while(iter.hasNext())
			iter.next();
		String retStr="";
		//TODO use reverse direction of the iterator 
		while(iter.hasPrevious()) {
			retStr = retStr + "\n" + iter.previous().toString();
		}
		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		if(this.isEmpty() == false)
		{
		    	this.tail.next = other.head;
		    	other.head.prev = this.tail;
		}
		else if(other.isEmpty())
		{
		    return;
		}
		else if(other.equals(this)) 
		{
			return;
		}
		else
		{
		    this.head = other.head;
		}
		
		this.tail = other.tail;
		this.size = this.size + other.size();
		other.clear();
	}
}

