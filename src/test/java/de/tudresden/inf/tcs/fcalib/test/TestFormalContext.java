/**
 * Test implementation for testing attribute exploration in partial contexts.
 * @author Baris Sertkaya
 */
package de.tudresden.inf.tcs.fcalib.test;

import org.junit.jupiter.api.Test;

import org.apache.log4j.BasicConfigurator;

import de.tudresden.inf.tcs.fcaapi.exception.IllegalContextException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalExpertException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcalib.FormalContext;
import de.tudresden.inf.tcs.fcalib.FullObject;
import de.tudresden.inf.tcs.fcalib.action.StartExplorationAction;

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

public class TestFormalContext {

	public TestFormalContext() {
	}

	@Test
	public void testFormalContext() throws IllegalObjectException,
			IllegalExpertException, IllegalContextException {
		BasicConfigurator.configure();

		FormalContext<String, String> context = new FormalContext<String, String>();
		NoExpertFull<String> expert = new NoExpertFull<String>(context);

		context.addAttribute("a");
		context.addAttribute("b");
		context.addAttribute("c");
		// context.addAttribute("d");
		// context.addAttribute("e");
		// context.addAttribute("f");
		// context.addAttribute("g");
		System.out.println("Attributes: " + context.getAttributes());

		expert.addExpertActionListener(context);
		context.setExpert(expert);

		StartExplorationAction<String, String, FullObject<String, String>> action = new StartExplorationAction<String, String, FullObject<String, String>>();
		action.setContext(context);
		expert.fireExpertAction(action);
	}

}
