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

public class FullObject<T> implements FCAObject<T> {

	/**
	 * Name of this full object.
	 */
	private String name;
	
	/**
	 * Description of this object.
	 */
	private FullObjectDescription<T> description;
		
	/**
	 * Creates a new full object with empty name and description.
	 */
	public FullObject(String n) {
			name = n;
			description = new FullObjectDescription<T>();
	}
	
	public FullObject(String n, Set<T> attrs) {
		name = n;
		description = new FullObjectDescription<T>(attrs);
	}
	
	// public boolean addAttribute(T attr) {
	// 	return description.addAttribute(attr);
	// }
	
	// public boolean addAttributes(Set<T> attrs) {
	// 	return description.addAttributes(attrs);
	// }
	
	// /**
	//  * Checks if this full object has a given attribute.
	//  * @param a the given attribute 
	//  * @return <code>true</code> if this full object has the attribute <code>a</code>, 
	//  * <code>false</code> otherwise
	//  */
	// public boolean hasAttribute(T a) {
	// 	return description.containsAttribute(a);
	// }
	
	// /**
	//  * Checks if this full object has the attributes in a given attribute set.
	//  * @param attrs the given attribute set
	//  * @return <code>true</code> if this full object has the attributes in <code>a</code>, 
	//  * <code>false</code> otherwise
	//  */
	// public boolean hasAttributes(Set<T> attrs) {
	// 	return description.containsAttributes(attrs);
	// }

	public boolean respects(FCAImplication<T> imp) {
		return !description.containsAttributes(imp.getPremise()) ||
			description.containsAttributes(imp.getConclusion());
	}
	
	public boolean refutes(FCAImplication<T> imp) {
		return !respects(imp);
	}

	// public Set<T> getAttributes() {
	// 	return description.getAttributes();
	// }
	
	/**
	 * Returns the description of this object.
	 */
	public FullObjectDescription<T> getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the name of this object, not a copy!
	 */
	public String toString() {
		return "{name: " + getName() + " attributes: " + description.getAttributes() + "}";
	}

}
