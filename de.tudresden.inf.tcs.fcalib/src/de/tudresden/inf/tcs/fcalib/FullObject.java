/**
 * Implementation of (full) object. 
 * @author Baris Sertkaya
 * 
 */

package de.tudresden.inf.tcs.fcalib;

import java.util.Set;

import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;


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

public class FullObject<A,I> implements FCAObject<A,I> {

	/**
	 * The identifier of this object.
	 */
	private I identifier;
	
	/**
	 * Name of this full object.
	 */
	private String name;
	
	/**
	 * Description of this object.
	 */
	private FullObjectDescription<A> description;
		
	/**
	 * Creates a new full object with the given identifier, and empty name and description.
	 * @param id the identifier
	 */
	public FullObject(I id) {
		identifier = id;
		name = "";
		description = new FullObjectDescription<A>();
	}
	
	/**
	 * Creates a new full object with the given identifier and description.
	 * @param id the identifier
	 * @param attrs the description
	 */
	public FullObject(I id, Set<A> attrs) {
		identifier = id;
		name = "";
		description = new FullObjectDescription<A>(attrs);
	}
	
	public void setName(String n) {
		name = n;
	}
	
	// public boolean addAttribute(A attr) {
	// 	return description.addAttribute(attr);
	// }
	
	// public boolean addAttributes(Set<A> attrs) {
	// 	return description.addAttributes(attrs);
	// }
	
	// /**
	//  * Checks if this full object has a given attribute.
	//  * @param a the given attribute 
	//  * @return <code>true</code> if this full object has the attribute <code>a</code>, 
	//  * <code>false</code> otherwise
	//  */
	// public boolean hasAttribute(A a) {
	// 	return description.containsAttribute(a);
	// }
	
	// /**
	//  * Checks if this full object has the attributes in a given attribute set.
	//  * @param attrs the given attribute set
	//  * @return <code>true</code> if this full object has the attributes in <code>a</code>, 
	//  * <code>false</code> otherwise
	//  */
	// public boolean hasAttributes(Set<A> attrs) {
	// 	return description.containsAttributes(attrs);
	// }

	public boolean respects(FCAImplication<A> imp) {
		return !description.containsAttributes(imp.getPremise()) ||
			description.containsAttributes(imp.getConclusion());
	}
	
	public boolean refutes(FCAImplication<A> imp) {
		return !respects(imp);
	}

	// public Set<A> getAttributes() {
	// 	return description.getAttributes();
	// }
	
	/**
	 * Returns the description of this object.
	 * @return description of this object
	 */
	public FullObjectDescription<A> getDescription() {
		return description;
	}
	
	/**
	 * Returns the identifier of this object.
	 * @return identifier of this object
	 */
	public I getIdentifier() {
		return identifier;
	}
	
	/**
	 * Returns the name of this object.
	 * @return name of this object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a string representation of this full object.
	 */
	public String toString() {
		return "{id: " + getIdentifier() + " attributes: " + description.getAttributes() + "}";
	}

}
