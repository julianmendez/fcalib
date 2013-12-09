fcalib — A library for algorithms in Formal Concept Analysis
============================================================

Overview
--------

FCAlib is an open-source, extensible library for Formal Concept Analysis (FCA)
tool developers that implements the FCAAPI. It provides basic functionalities
that are needed for building an FCA tool. It supports incomplete contexts and
includes efficient implementations of basic FCA algorithms like implicational
closure, next-closed set, etc. It implements the attribute exploration
algorithm in such a way that it can be used together with a custom implemented
expert that supports FCAAPI.  Javadoc for FCAlib can be found here. FCAlib is
extended by OntoComPlib for using attribute exploration together with OWL
ontologies. The following code segment shows how to create a formal context,
add attributes to it, create an expert for this context, and start attribute
exploration:

```java
  // Create a formal context whose attributes are of type String, and whose objects have
  // identifiers of type String
  FormalContext<String,String> context = new FormalContext<String,String>();
  
  // Create an expert for this context
  MyExpertClass<String> expert = new MyExpertClass<String>(context);
        
  // Add attributes to this context
  context.addAttribute("a");
  context.addAttribute("b");
  context.addAttribute("c");
        
  // Set expert for this context
  context.setExpert(expert);
  // Context listens to the actions of the expert
  expert.addExpertActionListener(context);
        
  // Create an expert action for starting attribute exploration           
  StartExplorationAction<String,String,FullObject<String,String>> action = 
          new StartExplorationAction<String,String,FullObject<String,String>>();
  action.setContext(context);
  // Fire the action, exploration starts...
  expert.fireExpertAction(action);
```

The following code segment shows how to create a set of implications for the
above context, add implications to it, and compute next-closure:

```java
  // Create a set of implications for the above context. Attributes are of type String
  ImplicationSet<String> = new ImplicationSet<String>(context);
          
  // Create a new implication with empty premise and conclusion
  Implication<String> imp = new Implication<String>();
          
  // Add attribute "a" to the premise
  imp.getPremise().add("a");
          
  // Add attribute "b" to the conclusion
  imp.getConclusion().add("b");
          
  // Add this implication to the implication set
  implications.add(imp);
          
  // Compute the next-closed set after mySet, and update mySet
  mySet = implications.nextClosure(mySet);
```

LICENSE
-------

See [LICENSE](LICENSE)

Developers
----------

Original Developer: Barış Sertkaya  
Additional Developers: [Julian Mendez](http://github.com/julianmendez), [Francesco Kriegel](http://github.com/francesco-kriegel), [Daniel Borchmann](http://github.com/exot)

