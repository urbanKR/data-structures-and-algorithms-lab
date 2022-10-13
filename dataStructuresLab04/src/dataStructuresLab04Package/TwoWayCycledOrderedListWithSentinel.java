package dataStructuresLab04Package;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<? super E>> implements IList<E> {

	private class Element{
		public Element(E e) {
			//TODO
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			//TODO
			this.object = e;
			this.next = next;
			this.prev = prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			//TODO
			elem.next = this.next;
			elem.prev = this;
			this.next.prev = elem;
			this.next = elem;
		}
		// assert it is NOT a sentinel
		public void remove() {
			//TODO
			this.next.prev = this.prev;
			this.prev.next = this.next;
		}
		E object;
		Element next;
		Element prev;
	}


	Element sentinel = null;

	private class InnerIterator implements Iterator<E>{
		//TODO
		Element pos;
		
		public InnerIterator() {
			//TODO
			pos = sentinel.next;
		}
		@Override
		public boolean hasNext() {
			//TODO
			if(pos != sentinel)
			{
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			//TODO
			if(pos == sentinel) 
			{
				throw new NoSuchElementException();
			}
			E x = pos.object;
			pos = pos.next;
			
			return x;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		//TODO
		Element curr;
		public InnerListIterator() {
			//TODO
			curr = sentinel.next;
		}
		@Override
		public boolean hasNext() {
			if(curr != sentinel)
			{
				return curr.next != sentinel;
			}
			return false;
		}

		@Override
		public E next() {
			if(curr.next == sentinel) 
			{
				throw new NoSuchElementException();
			}
			curr = curr.next;
			
			return curr.object;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasPrevious() {
			//TODO
			if(curr != sentinel)
			{
				return true;
			}
			return false;
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public E previous() {
			//TODO
			if(curr == sentinel) 
			{
				throw new NoSuchElementException();
			}
			E x = curr.object;
			curr = curr.prev;
			
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
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		//TODO
		sentinel = new Element(null);
		sentinel.next = sentinel;
		sentinel.prev  = sentinel;

	}
	
	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		//TODO
		Element newEl = new Element(e);
		Element curr = sentinel.next;
		while(curr != sentinel) {
			if(curr.object.compareTo(newEl.object) > 0)
			{
				newEl.next = curr;
				newEl.prev = curr.prev;
				curr.prev.next = newEl;
				curr.prev = newEl;
				return true;
			}
			curr = curr.next;
		}
		
		newEl.next = sentinel;
		newEl.prev = sentinel.prev;
		sentinel.prev.next = newEl;
		sentinel.prev = newEl;
		return true;
	}
	
	private Element getElement(int index) {
		//TODO
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		Element el = sentinel.next;
		int count = 0;
		while(count < index && el != sentinel) {
			count++;
			el = el.next;
		}
		if(el == sentinel)
		{
			throw new IndexOutOfBoundsException();
		}
		return el;
	}

	private Element getElement(E obj) {
		//TODO
		Element el = sentinel.next;
		while(el != sentinel && !obj.equals(el.object)) {
			el = el.next;
		}
		if(el == sentinel)
		{
			return null;
		}
		return el;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		//TODO
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}

	@Override
	public boolean contains(E element) {
		//TODO
		return indexOf(element) != -1;
	}

	@Override
	public E get(int index) {
		//TODO
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		Element el = sentinel.next;
		int count = 0;
		while(el != sentinel && count < index){
			count++;
			el = el.next;
		}
		
		if(el == sentinel) {	
			throw new IndexOutOfBoundsException();
		}
		return el.object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		//TODO
		Element el = sentinel.next;
		int count = 0;
		while(el != sentinel && !element.equals(el)) {
			el = el.next;
			count++;
		}
		if(el == sentinel)
		{
			return -1;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		//TODO
		if(sentinel.next == sentinel)
		{
			return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		//TODO
		Element el = getElement(index);
		el.remove();
		return (E) el;
	}

	@Override
	public boolean remove(E e) {
		//TODO
		Element el = sentinel.next;
		while(el != sentinel && !e.equals(el)) {
			el = el.next;
		}
		el.prev.next = el.next;
		el.next.prev = el.prev;
		
		return true;
	}

	@Override
	public int size() {
		//TODO
		Element el = sentinel.next;
		int count = 0;
		while(el != sentinel) {
			count++;
			el = el.next;
		}
		return count;
	}

	 public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		 if(this.size() == 0) 
	     {
			 sentinel.prev = other.sentinel.prev;
	         sentinel.next = other.sentinel.next;
	         sentinel.next.prev = sentinel;
	         sentinel.prev.next = sentinel;
	    } 
        if(this == other || other == null )
        {
        	return;
        }
        if(other.size() == 0)
        {
        	return;
        }
        else 
        {
        	Element thisEl = sentinel;
            Element otherEl = other.sentinel.next;
            while (otherEl != other.sentinel) {
            	while (thisEl.next != sentinel && thisEl.next.object.compareTo(otherEl.object) <= 0) {
            		thisEl = thisEl.next;
                }
                Element otherListNext = otherEl.next;
                thisEl.addAfter(otherEl);
                thisEl = otherEl;
                otherEl = otherListNext;
            }
        }
        other.clear();
        
    }
	//@SuppressWarnings("unchecked")
//	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
//		//TODO
//		if(other == null || other.size() == 0 || this == other)
//		{
//			return;
//		}
//		if(this.size() == 0)
//		{
//			this.sentinel.next = other.sentinel.next;
//			this.sentinel.prev = other.sentinel.prev;
//		}
//		else
//		{
//			this.sentinel.prev.next = other.sentinel.next;
//	    	other.sentinel.next.prev = this.sentinel.prev.next;
//		}
//		this.sentinel.prev.object = other.sentinel.prev.object;
//		other.clear();
//		if(this.isEmpty() == false)
//		{
//		    	this.sentinel.prev.next = other.sentinel.next;
//		    	other.sentinel.next.prev = this.sentinel.prev.next;
//		}
//		else if(other.equals(this))
//		{
//		    return;
//		}
//		else if(other.isEmpty()) 
//		{
//			return;
//		}
//		else
//		{
//		    this.sentinel.next = other.sentinel.next;
//		}
//		
//		this.sentinel.prev.object = other.sentinel.prev.object;
////		this.size = this.size + other.size();
//		other.clear();
//	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		//TODO
		Element toRemove = new Element(e);
		Element el = sentinel.next;
		while(el != sentinel) {
			if(el.object.equals(toRemove.object))
			{
				el.prev.next = el.next;
				el.next.prev = el.prev;
			}
			el = el.next;
		}
	}

}
