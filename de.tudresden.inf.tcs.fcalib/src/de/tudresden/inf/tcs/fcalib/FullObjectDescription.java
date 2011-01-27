/**
 * An ObjectDescription implementation.
 */
package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

import de.tudresden.inf.tcs.fcaapi.ObjectDescription;


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

public class FullObjectDescription<T> implements ObjectDescription<T> {

	/**
	 * The set of attributes in this description.
	 */
	private Set<T> attributes;
	
	/**
	 * Creates an empty description.
	 */
	public FullObjectDescription() {
		attributes = new HashSet<T>();
	}
	
	/**
	 * Creates a description with the given set of attributes. 
	 * @param p the set of attributes that the description will initially have
	 */
	public FullObjectDescription(Set<T> p) {
		attributes = new HashSet<T>(p);
	}
	
	/**
	 * Checks whether this description contains a given attribute.
	 * @param attribute the attribute to be checked
	 * @return <code>true</code> if this description contains <code>attribute</code>
	 */
	public boolean containsAttribute(T attribute) {
		return attributes.contains(attribute);
	}

	/**
	 * Checks whether this description contains all of the attributes in a given set.
	 * @param attrs the set of attributes to be checked
	 * @return  <code>true</code> if this description contains all of the attributes in 
	 * <code>attrs</code>, <code>false</code> otherwise
	 */
	public boolean containsAttributes(Set<T> attrs) {
		return attributes.containsAll(attrs);
	}

	/**
	 * Adds a specified attribute to this description.
	 * @param attribute the attribute to be added
	 * @return <code>true</code> if <code>attribute</code> is successfully added,
	 * <code>false</code> otherwise
	 */
	public boolean addAttribute(T attribute) {
		return attributes.add(attribute);
	}
	
	/**
	 * Adds a given set of attributes to this description.
	 * @param attrs the attributes to be added
	 * @return <code>true</code> if all of the attributes in <code>attrs</code> are successfully
	 * added, <code>false</code> otherwise
	 */
	public boolean addAttributes(Set<T> attrs) {
		boolean retCode = true;
		for (T attr : attrs) {
			retCode = retCode && addAttribute(attr);
		}
		return retCode;
	}
	
	/**
	 * Removes a specified attribute from this description.
	 * @param attribute the attribute to be removed
	 * @return <code>true</code> if <code>attribute</code> is successfully removed,
	 * <code>false</code> otherwise
	 */
	public boolean removeAttribute(T attribute) {
		return attributes.remove(attribute);
	}
	
	/**
	 * Returns the set of attributes in this description.
	 * @return the set of attributes in this description
	 */
	public Set<T> getAttributes() {
		return attributes;
	}
	
	/**
	 * Produces a deep copy of this description.
	 * @return a deep copy of this description
	 */
	public Object clone() {
		FullObjectDescription<T> clone = new FullObjectDescription<T>(attributes);
		return clone;
	}
}
