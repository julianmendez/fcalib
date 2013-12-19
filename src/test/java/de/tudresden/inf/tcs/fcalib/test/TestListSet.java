package de.tudresden.inf.tcs.fcalib.test;

import junit.framework.TestCase;
import de.tudresden.inf.tcs.fcalib.utils.ListSet;

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

public class TestListSet extends TestCase {

	public TestListSet() {
	}

	public void testListSet() {
		ListSet<String> ls = new ListSet<String>();

		ls.add("a");
		ls.add("b");
		ls.add("c");
		ls.add("d");

		System.out.println("ls:" + ls);
		ls.changeOrder();
		System.out.println("ls:" + ls);
		ls.changeOrder();
		System.out.println("ls:" + ls);
		ls.changeOrder();
		System.out.println("ls:" + ls);
		ls.changeOrder();
		System.out.println("ls:" + ls);
		ls.changeOrder();
		System.out.println("ls:" + ls);
	}

}
