package de.tudresden.inf.tcs.fcaapi.utils;

import java.util.Set;

/*
 * FCAAPI: An API for Formal Concept Analysis tools
 * Copyright (C) 2009  Baris Sertkaya
 *
 * This file is part of FCAAPI.
 * FCAAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FCAAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FCAAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Interface describing set that keeps elements in the insertion order and whose
 * elements can be reached with indices.
 * 
 * @author Baris Sertkaya Technische Universtaet Dresden
 *         sertkaya@tcs.inf.tu-dresden.de
 */

public interface IndexedSet<T> extends Set<T> {

	/**
	 * Returns the index of a given element in this ordered set.
	 * 
	 * @param e
	 *            the given element
	 * @return the index of <code>e</code>, or -1 if <code>e</code> is not
	 *         contained in this ordered set
	 */
	int getIndexOf(T e);

	/**
	 * Returns the element at a given index
	 * 
	 * @param index
	 *            index of the element to return
	 * @return the element at index <code>index</code>
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index &lt; 0 || index &gt;= size())
	 */
	T getElementAt(int index) throws IndexOutOfBoundsException;

	/**
	 * Changes the order of the elements in this indexed set. Required for
	 * skipping questions in attribute exploration.
	 */
	void changeOrder();

}
