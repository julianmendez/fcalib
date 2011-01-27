package de.tudresden.inf.tcs.fcalib.change;

import de.tudresden.inf.tcs.fcaapi.Context;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
import de.tudresden.inf.tcs.fcaapi.FCAObject;


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

public class NewObjectChange<A> implements ContextChange<FCAObject<A,?>> {

	private Context<A,?,FCAObject<A,?>> context;
	private FCAObject<A,?> object;
	
	public NewObjectChange(Context<A,?,FCAObject<A,?>> c, FCAObject<A,?> o) {
		context = c;
		object = o;		
	}
	
	public void undo() {
		try {
			context.removeObject(object);
		}
		catch (IllegalObjectException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public FCAObject<A,?> getObject() {
		return object;
	}
	
	public int getType() {
		return ContextChange.NEW_OBJECT_MODIFICATION;
	}
}
