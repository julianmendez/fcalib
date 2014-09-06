package de.tudresden.inf.tcs.fcalib;

import java.util.Set;

import org.apache.log4j.Logger;

import de.tudresden.inf.tcs.fcaapi.Context;
import de.tudresden.inf.tcs.fcaapi.FCAImplication;
import de.tudresden.inf.tcs.fcaapi.FCAObject;
// import de.tudresden.inf.tcs.fcaapi.Expert;
import de.tudresden.inf.tcs.fcaapi.action.ExpertAction;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalAttributeException;
import de.tudresden.inf.tcs.fcaapi.exception.IllegalObjectException;
import de.tudresden.inf.tcs.fcaapi.utils.IndexedSet;
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

/**
 * Abstract context implementation to be extended.
 * 
 * @author Baris Sertkaya Technische Universtaet Dresden
 *         sertkaya@tcs.inf.tu-dresden.de
 */

public abstract class AbstractContext<A, I, O extends FCAObject<A, I>>
		implements Context<A, I, O> {

	/**
	 * Attributes of this abstract context.
	 */
	private final IndexedSet<A> attributes = new ListSet<A>();

	/**
	 * The implication set to be used in attribute exploration
	 */
	private ImplicationSet<A> implications;

	// /**
	// * The expert for this context.
	// */
	// private Expert<A,I,O> expert = null;

	/**
	 * The last question asked to the expert.
	 */
	private FCAImplication<A> currentQuestion = null;

	/**
	 * The logger.
	 */
	private static final Logger logger = Logger
			.getLogger(AbstractContext.class);

	/**
	 * Returns the set of attributes of this context.
	 * 
	 * @return the set of attributes
	 */
	@Override
	public IndexedSet<A> getAttributes() {
		return this.attributes;
	}

	/**
	 * Returns the number of attributes of this context.
	 * 
	 * @return number of attributes
	 */
	@Override
	public int getAttributeCount() {
		return getAttributes().size();
	}

	/**
	 * Returns the set of objects of this context.
	 * 
	 * @return the set of objects
	 */
	@Override
	public abstract IndexedSet<O> getObjects();

	/**
	 * Returns the number of objects of this context.
	 * 
	 * @return number of objects
	 */
	@Override
	public int getObjectCount() {
		return getObjects().size();
	}

	/**
	 * Returns the attribute specified by its index.
	 * 
	 * @param index
	 *            index of the attribute whose name is requested
	 * @return name of the attribute at index <code>index</code>
	 */
	@Override
	public A getAttributeAtIndex(int index) {
		return this.attributes.getElementAt(index);
	}

	/**
	 * Adds a given attribute to the attributes of this context.
	 * 
	 * @param attribute
	 *            the attribute to be added
	 * @return <code>true</code> if the <code>attribute</code> is successfully
	 *         added
	 * @throws IllegalAttributeException
	 *             if the given attribute is already in the set of attributes
	 */
	@Override
	public boolean addAttribute(A attribute) throws IllegalAttributeException {
		boolean added = getAttributes().add(attribute);
		if (!added) {
			throw new IllegalAttributeException("Attribute " + attribute
					+ " has already been added");
		}
		return added;
	}

	/**
	 * Adds the attributes in a given set to the set of attributes of this
	 * context.
	 * 
	 * @param attrs
	 *            the set of attributes to be added
	 * @return <code>true</code> if all of the <code>attributes</code> are
	 *         successfully added
	 * @throws IllegalAttributeException
	 *             if one of the attributes in <code>attributes</code> already
	 *             occurs in the attributes of this context
	 */
	@Override
	public boolean addAttributes(Set<A> attrs) throws IllegalAttributeException {
		boolean allAdded = true;
		for (A attribute : attrs) {
			allAdded = allAdded && addAttribute(attribute);
		}
		return allAdded;
	}

	/**
	 * Adds a given object to the set of objects of this context.
	 * 
	 * @param object
	 *            the object to be added
	 * @return <code>true</code> if the <code>object</code> is successfully
	 *         added
	 * @throws IllegalObjectException
	 *             if an object with the same name already exists in this
	 *             context
	 */
	@Override
	public abstract boolean addObject(O object) throws IllegalObjectException;

	/**
	 * Removes an object given with its name from the set of objects of this
	 * context.
	 * 
	 * @param id
	 *            identifier of the object to be removed
	 * @return <code>true</code> if the object named <code>name</code> is
	 *         successfully removed, <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if an object named <code>name</code> does not exist in this
	 *             context
	 */
	@Override
	public abstract boolean removeObject(I id) throws IllegalObjectException;

	/**
	 * Removes a given object from the set of objects of this context.
	 * 
	 * @param object
	 *            the object to be removed
	 * @return <code>true</code> if the object <code>object</code> is
	 *         successfully removed, <code>false</code> otherwise
	 * @throws IllegalObjectException
	 *             if the object <code>object</code> does not exist
	 */
	@Override
	public abstract boolean removeObject(O object)
			throws IllegalObjectException;

	/**
	 * Checks if this context contains an object that has a given name.
	 * 
	 * @param id
	 *            identifier of the object to be searched
	 * @return <code>true</code> the an object with name <code>name</code> is
	 *         found, <code>false</code> otherwise
	 */
	@Override
	public boolean containsObject(I id) {
		if (getObject(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Adds the objects in a given set of objects to the set of objects of this
	 * context.
	 * 
	 * @param s
	 *            the set of objects to be added
	 * @return <code>false</code> if at least one of the <code>objects</code> is
	 *         not successfully added
	 */
	@Override
	public boolean addObjects(Set<O> s) throws IllegalObjectException {
		boolean retCode = true;
		for (O object : s) {
			retCode = retCode && addObject(object);
		}
		return retCode;
	}

	/**
	 * Clears the set of objects.
	 */
	@Override
	public void clearObjects() {
		getObjects().clear();
	}

	/**
	 * Adds a given attribute to the attributes of the given object.
	 * 
	 * @param attribute
	 *            the attribute to be added
	 * @param id
	 *            identifier of the object where <code>attribute</code> is to be
	 *            added
	 * @return <code>true</code> if of the <code>attribute</code> is
	 *         successfully added, <code>false</code> otherwise
	 * @throws IllegalAttributeException
	 *             if <code>object</code> already has the attribute
	 * @throws IllegalObjectException
	 *             if the <code>object</code> does not exist in this context
	 */
	@Override
	public abstract boolean addAttributeToObject(A attribute, I id)
			throws IllegalAttributeException, IllegalObjectException;

	/**
	 * Returns the current set of implications of this context.
	 * 
	 * @return the current set of implications
	 */
	@Override
	public Set<FCAImplication<A>> getImplications() {
		return this.implications;
	}

	/**
	 * Sets the current question to the given implication.
	 * 
	 * @param imp
	 *            the given implication
	 */
	protected void setCurrentQuestion(FCAImplication<A> imp) {
		this.currentQuestion = imp;
	}

	/**
	 * Returns the last question asked to the expert.
	 * 
	 * @return the last question asked to the expert
	 */
	public FCAImplication<A> getCurrentQuestion() {
		return this.currentQuestion;
	}

	/**
	 * Given a premise, returns the next one.
	 * 
	 * @param currentPremise
	 *            the given premise
	 * @return the next premise coming after <code>currentPremise</code>
	 */
	public Set<A> getNextPremise(Set<A> currentPremise) {
		return this.implications.nextClosure(currentPremise);
	}

	/**
	 * Returns the double prime of a given attribute set of this context.
	 * 
	 * @param x
	 *            the given attribute set
	 * @return the attribute set that is the double prime of <code>x</code>
	 */
	@Override
	public abstract Set<A> doublePrime(Set<A> x);

	/**
	 * Returns the stem base of this context.
	 * 
	 * @return the stem base
	 */
	@Override
	public abstract Set<FCAImplication<A>> getStemBase();

	/**
	 * Checks whether this context refutes a given implication, i.e., there
	 * exists an object that does not respect the given implication.
	 * 
	 * @param imp
	 *            the implication to be tested
	 * @return <code>true</code> if all objects in this context respect
	 *         <code>imp</code>, <code>false</code> otherwise
	 */
	public abstract boolean refutes(FCAImplication<A> imp);

	/**
	 * Checks whether a counterexample provided by the expert is valid, i.e., it
	 * is really a counterexample to the given implication.
	 * 
	 * @param counterExample
	 *            the counterexample to be checked for validity
	 * @param imp
	 *            the given implication
	 * @return <code>true</code> if <code>counterExample</code> is a valid
	 *         counterexample to <code>imp</code>, <code>false</code> otherwise
	 */
	public abstract boolean isCounterExampleValid(O counterExample,
			FCAImplication<A> imp);

	/**
	 * Checks whether the expert has already been set.
	 * 
	 * @return <code>true</code> if the <code>expert</code> has already been
	 *         set, <code>false</code> otherwise
	 */
	@Override
	public boolean isExpertSet() {
		return getExpert() != null;
	}

	// /**
	// * Sets the expert for this context to the given expert
	// * @param e the given expert
	// */
	// public void setExpert(Expert<A,I,O> e) {
	// expert = e;
	// }

	// /**
	// * Returns the expert of this context.
	// * @return the expert of this context
	// */
	// public <E extends Expert<A,I,O>> E getExpert() {
	// return expert;
	// }

	/**
	 * Calls the
	 * {@link de.tudresden.inf.tcs.fcaapi.action.ExpertAction#actionPerformed}
	 * method of the caught action.
	 */
	@Override
	public void expertPerformedAction(ExpertAction action) {
		action.actionPerformed(null);
	}

	// public void expertPerformedAction(ExpertAction<A,O> action) {
	// switch (action.getType()) {
	// case Expert.CONFIRMED_QUESTION:
	// questionConfirmed(action.getQuestion());
	// break;
	// case Expert.REJECTED_QUESTION:
	// questionRejected(action.getQuestion());
	// break;
	// case Expert.PROVIDED_COUNTEREXAMPLE:
	// counterExampleProvided(action.getCounterExample(), action.getQuestion());
	// break;
	// case Expert.STARTED_EXPLORATION:
	// try {
	// startExploration();
	// }
	// catch (IllegalExpertException e) {
	// e.printStackTrace();
	// logger.fatal("First set the expert!");
	// System.exit(-1);
	// }
	// break;
	// case Expert.STOPPED_EXPLORATION:
	// stopExploration();
	// break;
	// case Expert.RESET_EXPLORATION:
	// resetExploration();
	// break;
	// case Expert.RESUMED_EXPLORATION:
	// resumeExploration();
	// break;
	// default:
	// // this shouldn't be the case
	// logger.fatal("Illegal expert action type, this shouldn't happen: " +
	// action.getType());
	// // System.exit(-1);
	// }
	// }

	/**
	 * Initializes the data structures used in exploration.
	 */
	public void initializeExploration() {
		this.implications = new ImplicationSet<A>(this);
	}

	// /**
	// * Performs the necessary operations when the expert confirms a question.
	// For instance add the
	// * confirmed implication to the base. Extensions of GenericContext class
	// can override it to
	// * perform extra operations like for instance for a DL context add the
	// implication to the knowledge base
	// * as a GCI.
	// * @param question the question that the expert has confirmed
	// */
	// public synchronized void questionConfirmed(FCAImplication<A> question) {
	// logger.info("Expert confirmed implication: " + question);
	// implications.add(question);
	// premise = implications.nextClosure(premise);
	// continueExploration();
	// }

	// /**
	// * Performs the necessary operations when the expert rejects a question.
	// For instance, ask the expert
	// * for a counterexample.
	// * @param question the question that the expert has rejected
	// */
	// public synchronized void questionRejected(FCAImplication<A> question) {
	// logger.info("Expert rejected implication: " + question);
	// expert.requestCounterExample(question);
	// }

	// /**
	// * Performs the necessary operations when the expert provides a
	// counterexample. For instance, check
	// * whether it is valid, and so on.
	// * @param counterExample the counterexample that the expert has provided
	// * @param question the question that has been rejected by the expert, and
	// has resulted in
	// * <code>counterExample</code>
	// */
	// public synchronized void counterExampleProvided(O counterExample,
	// FCAImplication<A> question) {
	// logger.info("Expert provided counterexample: " + counterExample);
	// // check whether counterexample is valid
	// if (isCounterExampleValid(counterExample, question)) {
	// try {
	// addObject(counterExample);
	// // continueExploration(question.getPremise());
	// continueExploration();
	// }
	// catch (IllegalObjectException e) {
	// expert.counterExampleInvalid(counterExample,
	// Expert.COUNTEREXAMPLE_EXISTS);
	// expert.requestCounterExample(question);
	// }
	// }
	// else {
	// expert.counterExampleInvalid(counterExample,
	// Expert.COUNTEREXAMPLE_INVALID);
	// expert.requestCounterExample(question);
	// }
	// }

	/**
	 * Performs the necessary operations when the exploration terminates.
	 */
	protected void explorationFinished() {
		logger.info("=== Exploration finished ===");
		getExpert().explorationFinished();
	}

	/**
	 * Checks whether an implication already follows from some background
	 * knowledge, in case it exists. For instance, in a DL context it would
	 * check if a given implication (in this case a subsumption relation)
	 * already follows from the DL knowledge base.
	 * 
	 * @param implication
	 *            implication
	 * @return <code>true</code> if <code>implication</code> already follows
	 *         from background knowledge, <code>false</code> otherwise
	 */
	protected abstract boolean followsFromBackgroundKnowledge(
			FCAImplication<A> implication);

	// /**
	// * Starts attribute exploration with empty premise.
	// * @throws IllegalExpertException if the expert has not been set before
	// starting exploration
	// */
	// public synchronized void startExploration() throws IllegalExpertException
	// {
	// if (!isExpertSet()) {
	// throw new IllegalExpertException();
	// }
	// premise = new LinkedHashSet<A>();
	// implications = new ImplicationSet<A>(this);
	// continueExploration();
	// logger.info("== Exploration started ===");
	// }

	// /**
	// * Takes necessary actions upon a stop request from the expert.
	// */
	// public synchronized void stopExploration() {
	// logger.info("=== Expert stopped exploration ===");
	// }

	// /**
	// * Resets attribute exploration. Clears the set of implications, the set
	// of objects, the set of
	// * attributes and the premise. After a call to this method, this context
	// is ready for a fresh exploration.
	// */
	// public synchronized void resetExploration() {
	// attributes.clear();
	// clearObjects();
	// if (implications != null) {
	// implications.clear();
	// }
	// if (premise != null) {
	// premise.clear();
	// }
	// logger.info("Expert reset exploration");
	// }

	// /**
	// * Resumes a stopped exploration.
	// */
	// public synchronized void resumeExploration() {
	// logger.info("Expert resumed exploration");
	// continueExploration();
	// }

	/**
	 * The main method that keeps the exploration going. Given a set of
	 * attributes, it computes a new left handside using the current
	 * implications of this context, computes a right handside by using the
	 * current objects of this context, first checks if the new implication
	 * already follows from some background knowledge. If yes, the implication
	 * is added to the current set of implications and the method calls itself
	 * with the next premise computed by using the new set of implications. If
	 * not, the expert is requested to answer the implication question.
	 * Extensions of this class should implement the abstract method
	 * {@link #followsFromBackgroundKnowledge(FCAImplication)} for their
	 * specific purposes.
	 * 
	 * @param premise
	 *            premise
	 */
	public void continueExploration(Set<A> premise) {
		Set<A> conclusion = null;
		Implication<A> implication = null;

		logger.debug("premise: " + premise);
		if (premise != null) {
			conclusion = doublePrime(premise);
			logger.debug("conclusion: " + conclusion);
			if (!premise.equals(conclusion)) {
				conclusion.removeAll(premise);
				implication = new Implication<A>(premise, conclusion);
				this.currentQuestion = implication;
				if (followsFromBackgroundKnowledge(implication)) {
					logger.debug("Follows from background knowledge: "
							+ implication);
					this.implications.add(implication);
					premise = this.implications.nextClosure(premise);
					continueExploration(premise);
				} else {
					// if the implication does not follow from the background
					// knowledge
					// ask the expert
					getExpert().askQuestion(implication);
				}
			} else {
				// if the premise is equal to the conclusion
				// trivial implication, compute the next premise
				premise = this.implications.nextClosure(premise);
				// and continue with the next question
				continueExploration(premise);
			}
		} else {
			// if the premise is null
			// stop the exploration
			explorationFinished();
			logger.debug("objects: " + getObjects());
		}
	}

}
