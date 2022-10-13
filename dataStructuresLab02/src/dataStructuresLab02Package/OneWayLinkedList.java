package dataStructuresLab02Package;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E> {

	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;
	}

	Element sentinel;

	private class InnerIterator implements Iterator<E> {
		Element currEl;
		public InnerIterator() {
			currEl = sentinel;
		}
		@Override
		public boolean hasNext() {
			if(currEl.next != null)
			{
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			currEl = currEl.next;
			return currEl.object;
		}
	}

	public OneWayLinkedList() {
		// make a sentinel	
		this.sentinel = new Element(null);
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
	public boolean add(E e) {
		// TODO Auto-generated method stub
		Element nextEl = sentinel;
		while(nextEl.next != null) {
			nextEl = nextEl.next;
		}
		nextEl.next = new Element(e);
		return true;
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {
		// TODO Auto-generated method stub
		Element nextEl = sentinel;
		Element elem = new Element(element);
		if(index < 0 || index > size())
		{
			throw new NoSuchElementException();
		}
		else if(index == size())
		{
			add(element);
			return;
		}
			int count = 0;
			while(count < index) {
				nextEl = nextEl.next;
				count++;
			}
			elem.next = nextEl.next;
			nextEl.next = elem;
	}


	@Override
	public void clear() {
		sentinel.next = null;
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		//return indexOf(element)!=-1;
		Element elem = sentinel.next;
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
	public E get(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size())
		{
			throw new NoSuchElementException();
		}
		Element nextEl = sentinel.next;
		int count = 0;
		while(count < index) {
			nextEl = nextEl.next;
			count++;
		}
		return nextEl.object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException();
		}
		Element nextEl = sentinel.next;
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
		// TODO Auto-generated method stub
		if(isEmpty())
		{
			return -1;
		}
		int count = 0;
		Element nextEl = sentinel.next;
		while(count < size() && nextEl.object != null && nextEl.object.equals(element) != true) {
			nextEl = nextEl.next;
			count++;
		}
		if(nextEl != null)
		{
			return count;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return sentinel.next == null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(index == -1 ||  index  >= size())
		{
			throw new NoSuchElementException("error");
		}
		Element nextEl = sentinel;
		int count = 0;
		while(index != count) {
			count++;
			nextEl = nextEl.next;
		}
		Element copy = nextEl.next;
		nextEl.next = nextEl.next.next;
		return copy.object;
	}

	@Override
	public boolean remove(E e) {
		// TODO Auto-generated method stub
		try {
			Element nextEl = sentinel;
			int count = 0;
			while(count < size() && nextEl.object != null && nextEl.object.equals(e) != true) {
				nextEl = nextEl.next;
				count++;
			}
			remove(count);
		} 
		catch(NoSuchElementException exception) {
			return false;
		}
		return true;
	}

	@Override
	public int size() {
		int size = -1;
		Element nextEl = sentinel;
		while(nextEl != null) {
			nextEl = nextEl.next;
			size++;
		}
		return size;
	}


}