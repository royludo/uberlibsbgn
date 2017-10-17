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
