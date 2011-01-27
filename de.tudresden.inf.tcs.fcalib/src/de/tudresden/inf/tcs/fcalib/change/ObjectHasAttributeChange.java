package de.tudresden.inf.tcs.fcalib.change;

import de.tudresden.inf.tcs.fcaapi.FCAObject;
import de.tudresden.inf.tcs.fcaapi.change.ContextChange;


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

public class ObjectHasAttributeChange<A> implements ContextChange<A> {

	private FCAObject<A,?> object;
	private A attribute;
	
	public ObjectHasAttributeChange(FCAObject<A,?> o, A a) {
		object = o;
		attribute = a;
	}
	public void undo() {
		object.getDescription().removeAttribute(attribute);
	}
	
	public FCAObject<A,?> getObject() {
		return object;
	}
	
	public A getAttribute() {
		return attribute;
	}
	
	public int getType() {
		return ContextChange.OBJECT_HAS_ATTRIBUTE_MODIFICATION;
	}
}
