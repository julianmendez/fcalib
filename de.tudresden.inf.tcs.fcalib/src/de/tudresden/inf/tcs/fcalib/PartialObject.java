/**
 * Implementation of partial object.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib;

import java.util.Set;
import java.util.HashSet;

// import org.apache.log4j.Logger;

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

public class PartialObject<A,I> implements FCAObject<A,I> {

	/**
	 * The identifier of this object.
	 */
	private I identifier;
	
	/**
	 * Name of this partial object.
	 */
	private String name;
	
	/**
	 * Description of this partial object.
	 */
	private PartialObjectDescription<A> description;
	
	/**
	 * The logger.
	 */
	// private static final Logger logger = Logger.getLogger(PartialObject.class);
	
	/**
	 * Creates a new partial object with empty name and description.
	 */
	public PartialObject(I id) {
		identifier = id;
		name = "";
		description = new PartialObjectDescription<A>();
	}
	
	/**
	 * Creates a new partial object with a given name and a given set of attributes
	 * @param n the name
	 * @param attrs the initial set of positive attributes
	 */
	public PartialObject(I id, Set<A> attrs) {
		identifier = id;
		name = "";
		description = new PartialObjectDescription<A>(attrs);
	}
	
	/**
	 * Creates a new partial object with a given name and a given set of attributes, and a given
	 * set of negated attributes
	 * @param n the name
	 * @param attrs the initial set of attributes
	 * @param negatedAttrs the initial set of negated attributes
	 */
	public PartialObject(I id, Set<A> attrs, Set<A> negatedAttrs) {
		identifier = id;
		name = "";
		description = new PartialObjectDescription<A>(attrs,negatedAttrs);
	}
	
	// public boolean addNegatedAttribute(A attr) {
	// 	return description.addNegatedAttribute(attr);
	// }
	
	// public Set<A> getNegatedAttributes() {
	// 	return description.getNegatedAttributes();
	// }
	
	// public Set<A> getAttributes() {
	// 	return description.getAttributes();
	// }
	
	public boolean respects(FCAImplication<A> imp) {
		Set<A> tmp = new HashSet<A>(imp.getConclusion());
		tmp.retainAll(getDescription().getNegatedAttributes());
		// return description.containsAttributes(imp.getPremise()) && tmp.isEmpty();
		return !getDescription().containsAttributes(imp.getPremise()) || tmp.isEmpty();
	}
	
	public boolean refutes(FCAImplication<A> imp) {
		return !respects(imp);
	}
	
	// /**
	//  * Checks if the description of this partial object contains the negation of a given 
	//  * attribute.
	//  * @param attr the given attribute
	//  * @return <code>true</code> if this partial object has the negation of <code>attr</code>
	//  */
	// public boolean hasNegatedAttribute(A attr) {
	// 	return description.containsNegatedAttribute(attr);
	// }
	
	public PartialObjectDescription<A> getDescription() {
		return description;
	}

	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the identifier of this object.
	 * @return identifier of this object
	 */
	public I getIdentifier() {
		return identifier;
	}

	public String toString() {
		// return "{name: " + getName() + " plus: " + description.getAttributes() + " minus: " + description.getNegatedAttributes() + "}";
		return "{id=" + getIdentifier() + " " + getDescription() + "}";
	}
}
