package de.tudresden.inf.tcs.fcalib.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;

/*
 * FCAlib: An open-source extensible library for Formal Concept Analysis
 *         tool developers
 * Copyright (C) 2009  Baris Sertkaya
 *
 * This file is part of FCAlib.
 * FCAlib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FCAlib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FCAlib.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A set implementation whose elements are ordered and can be accessed with
 * indices.
 * 
 * @author Baris Sertkaya Technische Universtaet Dresden
 *         sertkaya@tcs.inf.tu-dresden.de
 */

public class ListSet<T> implements IndexedSet<T> {

	/**
	 * Elements of this list set.
	 */
	private final ArrayList<T> elements;

	/**
	 * Creates an empty ListSet
	 *
	 */
	public ListSet() {
		this.elements = new ArrayList<>();
	}

	/**
	 * Creates a new ListSet that contains the elements of a given collection.
	 * Duplicate elements in the given collection are inserted once,
	 * <code>null</code> element is not allowed.
	 * 
	 * @param c
	 *            the collection whose elements are to be contained in this
	 *            ListSet initially
	 */
	public ListSet(Collection<? extends T> c) {
		this.elements = new ArrayList<>();
		addAll(c);
	}

	/**
	 * Adds a given object to this list set. Does not allow duplicates.
	 * 
	 * @param o
	 *            the object to be added
	 * @return <code>true</code> if this list set did not already contain
	 *         <code>o</code>
	 * @throws NullPointerException
	 *             if the given object is <code>null</code>
	 */
	@Override
	public boolean add(T o) {
		if (o == null) {
			throw new NullPointerException();
		}
		for (T element : this.elements) {
			if (o.equals(element)) {
				return false;
			}
		}
		this.elements.add(o);
		return true;
	}

	/**
	 * Adds the elements in a given collection to this list set.
	 * 
	 * @param c
	 *            the collection of elements to be added
	 * @return <code>true</code> if this list set changed as a result of the
	 *         call
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean retCode = false;
		for (T e : c) {
			retCode = retCode || add(e);
		}
		return retCode;
	}

	/**
	 * Removes all of the elements from this list set.
	 */
	@Override
	public void clear() {
		this.elements.clear();
	}

	/**
	 * Checks if a given element occurs in this list set.
	 * 
	 * @param o
	 *            the element whose presence is to be tested
	 * @return <code>true</code> if <code>o</code> occurs in this list set,
	 *         <code>false</code> otherwise
	 * @throws NullPointerException
	 *             if <code>o</code> is <code>null</code>
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {
		if (o == null) {
			throw new NullPointerException();
		}
		for (T element : this.elements) {
			if (o.equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the elements of a given collection are contained in this
	 * ListSet.
	 * 
	 * @param c
	 *            the given collection whose elements are checked for presence
	 * @return <code>true</code> if all elements of <code>c</code> are contained
	 *         in this ListSet, <code>false</code> otherwise
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Iterator<?> it = c.iterator(); it.hasNext();) {
			if (!contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if this ListSet is equal to a given set, i.e., the given set is
	 * contained in this ListSet and vice versa. For this, it is enough to check
	 * whether the two sets have the same sizes and this set contains the given
	 * set.
	 * 
	 * @param c
	 *            collection
	 * @return <code>true</code> if this ListSet is equal to <code>c</code>
	 */
	public boolean equals(Collection<?> c) {
		return (c.size() == size()) && containsAll(c);
	}

	/**
	 * Checks if this ListSet is empty.
	 * 
	 * @return <code>true</code> if this ListSet does not contain any elements,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	/**
	 * Iterator class for ListSet
	 * 
	 * @author Baris Sertkaya
	 *
	 */
	public class ListSetIterator implements Iterator<T> {
		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return this.currentIndex < ListSet.this.elements.size();
		}

		@Override
		public T next() {
			return getElementAt(this.currentIndex++);
		}

		@Override
		public void remove() {
		}
	}

	/**
	 * Returns an iterator for this ListSet
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListSetIterator();
	}

	/**
	 * Removes a given element from this ListSet.
	 * 
	 * @param o
	 *            the element to be removed
	 * @return <code>true</code> if this ListSet contained the given element,
	 *         <code>false</code> otherwise
	 * @throws NullPointerException
	 *             if <code>o</code> is <code>null</code>
	 */
	@Override
	public boolean remove(Object o) throws NullPointerException {
		if (o == null) {
			throw new NullPointerException();
		}
		if (contains(o)) {
			this.elements.remove(o);
			return true;
		}
		return false;
	}

	/**
	 * Removes the elements of a given collection from this ListSet.
	 * 
	 * @param c
	 *            the collection whose elements are to be removed
	 * @return <code>true</code> if this ListSet changed as a result of the
	 *         call, <code>false</code> otherwise
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean retCode = false;
		for (Iterator<?> it = c.iterator(); it.hasNext();) {
			retCode = retCode || remove(it.next());
		}
		return retCode;
	}

	/**
	 * Removes elements of this ListSet that are not contained in a given
	 * collection.
	 * 
	 * @param c
	 *            the collection that defines which elements are going to remain
	 * @return <code>true</code> if this set changed as a result of the call
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean retCode = false;
		for (T element : this) {
			if (!c.contains(element)) {
				retCode = retCode || remove(element);
			}
		}
		return retCode;
	}

	/**
	 * Returns the number of elements of this ListSet.
	 * 
	 * @return the size of this ListSet
	 */
	@Override
	public int size() {
		return this.elements.size();
	}

	/**
	 * Returns an array containing the elements of this ListSet.
	 * 
	 * @return an array containing the elements of this ListSet
	 */
	@Override
	public Object[] toArray() {
		return this.elements.toArray();
	}

	/**
	 * Returns an array containing the elements of this ListSet.
	 * 
	 * @param a
	 *            the array, into which the elements of this ListSet is to be
	 *            stored
	 * @return an array containing the elements of this ListSet
	 */
	@Override
	public <E> E[] toArray(E[] a) {
		return this.elements.toArray(a);
	}

	/**
	 * Returns the index of a given element.
	 * 
	 * @param e
	 *            the element whose index is requested
	 * @return the index of <code>e</code>, <code>-1</code> if <code>e</code> is
	 *         not found
	 */
	@Override
	public int getIndexOf(T e) {
		for (int i = 0; i < size(); ++i) {
			if (e == getElementAt(i)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the element at a specified index.
	 * 
	 * @param i
	 *            the index of the requested element
	 * @return the element at index <code>i</code>
	 * @throws IndexOutOfBoundsException
	 *             if the specified index is out of bounds
	 */
	@Override
	public T getElementAt(int i) throws IndexOutOfBoundsException {
		return this.elements.get(i);
	}

	/**
	 * Changes the order of elements by shifting them right.
	 */
	@Override
	public void changeOrder() {
		T tmp = getElementAt(0);

		for (int i = 0; i < (size() - 1); ++i) {
			this.elements.set(i, getElementAt(i + 1));
		}
		this.elements.set(size() - 1, tmp);
	}

	/**
	 * Returns a string representation of this ordered set.
	 * 
	 * @return string representation
	 */
	@Override
	public String toString() {
		if (isEmpty()) {
			return "{ }\n";
		}
		String tmp = "{ ";
		for (Iterator<?> it = iterator(); it.hasNext();) {
			tmp = tmp + it.next() + " ";
		}
		return tmp + "}";
	}
}
