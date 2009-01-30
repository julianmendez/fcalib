package de.tudresden.inf.tcs.fcalib.change;

import de.tudresden.inf.tcs.fcaapi.Context;
import de.tudresden.inf.tcs.fcaapi.change.ContextChange;
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

public class NewImplicationChange<A> implements ContextChange<FCAImplication<A>> {

	private FCAImplication<A> implication;
	private Context<A,?> context;
	
	public NewImplicationChange(Context<A,?> c, FCAImplication<A> i) {
		implication = i;
		context = c;
	}
	
	public void undo() {
		context.getImplications().remove(implication);
	}
	
	public FCAImplication<A> getImplication() {
		return implication;
	}

	public int getType() {
		return ContextChange.NEW_IMPLICATION_MODIFICATION;
	}
}
