/**
 * Description of a partial object represented with two sets. One for the 
 * attributes, the other for the negated attributes. If for an attribute none is set, then it is 
 * question mark, i.e. unknown.
 * @author Baris Sertkaya
 *
 */

package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;


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

public class PartialObjectDescription<T> extends FullObjectDescription<T> {
	
	/**
	 * The negated attributes of this partial description.
	 */
	private Set<T> negatedAttributes;
	
	/**
	 * Creates an empty partial object description.
	 */
	public PartialObjectDescription() {
		super();
		negatedAttributes = new HashSet<T>();
	}
	
	/**
	 * Creates a partial description with the given set of positive attributes. 
	 * The set of negative attributes will be empty.
	 * @param p the set of positive attributes that the description will initially have
	 */
	public PartialObjectDescription(Set<T> p) {
		super(p);
		negatedAttributes = new HashSet<T>();
	}
	
	/**
	 * Creates an partial description with the given  set of attributes and negated attributes.
	 * @param p the set of attributes that the description will initially have
	 * @param m the set of negated attributes that the description will initially have
	 */
	public PartialObjectDescription(Set<T> p, Set<T> m) {
		super(p);
		negatedAttributes = new HashSet<T>(m);
	}
	
	/**
	 * Adds a specified attribute to this description.
	 * @param attribute the attribute to be added
	 * @return <code>true</code> if successfully added
	 * @throws IllegalAttributeException if the description already contains the negation of the
	 * specified attribute
	 */
	public boolean addAttribute(T attribute) throws IllegalAttributeException {
		if (negatedAttributes.contains(attribute)) {
			throw new IllegalArgumentException("The description contains the negation of attribute " + attribute);
		}
		return super.addAttribute(attribute);
	}
	
	/**
	 * Adds a given attribute to the set of negated attributes of this description.
	 * @param attribute the negated attribute to be added to the description
	 * @return <code>true</code> if successfully added, <code>false</code> otherwise
	 * @throws IllegalArgumentException if the description already contains the specified attribute
	 */
	public boolean addNegatedAttribute(T attribute) throws IllegalAttributeException {
		if (super.containsAttribute(attribute)) {
			throw new IllegalAttributeException("The description contains the attribute " + attribute);
		}
		return negatedAttributes.add(attribute);
	}
	
	/**
	 * Checks whether this description contains a given negated attribute.
	 * @param attribute the attribute to be searched in the negated attributes
	 * @return <code>true</code> if this description contains <code>attribute</code> in its
	 * negated attributes, <code>false</code> otherwise
	 */
	public boolean containsNegatedAttribute(T attribute) {
		return negatedAttributes.contains(attribute);
	}
	
	/**
	 * Checks whether this description contains a given set of negated attribute.
	 * @param attrs the set of attributes to be searched in the negated attributes
	 * @return <code>true</code> if this description contains all of the <code>attrs</code> in its
	 * negated attributes, <code>false</code> otherwise
	 */
	public boolean containsNegatedAttributes(Set<T> attrs) {
		return negatedAttributes.containsAll(attrs);
	}
	
	/**
	 * Returns the set of negated attributes of this partial object description.
	 * @return the set of negated attributes
	 */
	public Set<T> getNegatedAttributes() {
		return negatedAttributes;
	}
	
	public Object clone() {
		PartialObjectDescription<T> clone = new PartialObjectDescription<T>(getAttributes(),negatedAttributes);
		return clone;
	}
	
	public String toString() {
		return "plus=" + getAttributes() + " minus=" + getNegatedAttributes();
	}
	
}
