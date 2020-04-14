# uberlibsbgn

## API goal

```java

// simplest building possible
Macromolecule m = new Macromolecule("PROT") // don't care about id
  .position(10,10)                          // don't care about sizes
  .addUnitOfInfo("receptor", TOP)           // don't care about computing auxiliary bboxes              
  .multimer();                              // don't care about class name management
  
// simple checks
m.hasUnitOfInfo(regexp);
m.isIn(complexOrCompartment);
m.getOutgoingArcs();
m.getDirectNeighbors();

// simple composite management
complex.include(m);
complex.getSubunits();
compartment.getIncluded();

// easy and efficient indexing
map.getAll("macromolecule");
map.getTopLevelGlyphs();

// easy traversing
map.visitRecursive();
map.visitEPNs();
map.visitTopLevel();

```

## TODO

 - finish index manager rework
 - ~~ports~~
 - ~~get rid of fluent glyph setters, impossible to keep it consistent~~
 - start notes and extension
 - style management (find good lib for color and gradient management, javafx should do)
 - RDF (with rdf4j probably)
 - interface with some graph lib (jgraphT probably)
 - and so much more...

## Ideas
 - canonicalize sbgnml with org.apache.xml.security from santuario 
 then diff possible with diff libs
 - possible to convert whole xml to rdf directly using rdf mapper (carml)
 - possible to mine pathway informations directly from any rdf, so building
 sbgn from any detailed enough knowledgebase should be feasible
