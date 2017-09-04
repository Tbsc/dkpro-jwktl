Tbsc's JWKTL fork
-----------------

I started using this great library to help me learn Dutch, but my main problem with it was that because of how it was designed (Wiktionary dump is pre-parsed into a database), if some data you need is on Wiktionary but not accessible through the library, chances are it's not in the database either. That meant I had to fork, change the library to also parse whatever I need, change the API to let users access the new data, and parse the dump again.

Changes
-------

As of now, the only differences are:
- Dutch inflections handler
- Getting wikitext of head and body

Parsing
-------

Follow the original instructions [here](https://dkpro.github.io/dkpro-jwktl/documentation/getting-started/), but instead use my fork to create it.  
You **must** use my fork, it won't work otherwise.
Right now I don't have any precompiled binaries up, so you'll have to compile it yourself using `gradle build`.

Original README
---------------

JWKTL
-----
[![Build Status](https://travis-ci.org/dkpro/dkpro-jwktl.svg)](https://travis-ci.org/dkpro/dkpro-jwktl)
[![codecov.io](http://codecov.io/github/dkpro/dkpro-jwktl/coverage.svg?branch=master)](http://codecov.io/github/dkpro/dkpro-jwktl?branch=master)

Summary
-------

JWKTL (Java-based Wiktionary Library) is an application programming 
interface for the free  multilingual online dictionary Wiktionary
(https://www.wiktionary.org). Wiktionary is collaboratively constructed 
by volunteers and continually growing. JWKTL enables efficient and 
structured access to the information encoded in the English and German Wiktionary language editions, including sense definitions, 
part of speech tags, etymology, example sentences, translations, 
semantic relations, and many other lexical information types. The 
API was first described in an LREC 2008 paper.

Further information and documentation is available from the project homepage:
* https://dkpro.github.io/dkpro-jwktl/
* https://www.ukp.tu-darmstadt.de/software/jwktl/


License
-------

JWKTL is available as open source software under the Apache License 
2.0 (ASL). The software thus comes "as is" without any warranty (see
the license text for more details). JWKTL makes use of Berkeley DB Java
Edition 5.0.73 (Sleepycat License), Apache Ant 1.7.1 (ASL), JUnit 4.12 (CPL), and Wikokit (New BSD license). 


Publications
------------

A more detailed description of Wiktionary and JWKTL is available in our 
scientific articles:

* Christian M. Meyer and Iryna Gurevych: Wiktionary: A new rival for 
  expert-built lexicons? Exploring the possibilities of collaborative 
  lexicography, Chapter 13 in S. Granger & M. Paquot (Eds.): *Electronic 
  Lexicography*, pp. 259–291, Oxford: Oxford University Press, November 2012.
  <http://ukcatalogue.oup.com/product/9780199654864.do>
* Christian M. Meyer and Iryna Gurevych: OntoWiktionary – Constructing an 
  Ontology from the Collaborative Online Dictionary Wiktionary, chapter 6 in 
  M. T. Pazienza and A. Stellato (Eds.): *Semi-Automatic Ontology Development: 
  Processes and Resources*, pp. 131–161, Hershey, PA: IGI Global, February 2012.
  <https://www.ukp.tu-darmstadt.de/data/lexical-resources/wiktionary/ontowiktionary/>
* Torsten Zesch, Christof Müller, and Iryna Gurevych: Extracting Lexical 
  Semantic Knowledge from Wikipedia and Wiktionary, in: *Proceedings of the 
  6th International Conference on Language Resources and Evaluation (LREC)*, 
  pp. 1646–1652, May 2008. Marrakech, Morocco.
  <http://lrec-conf.org/proceedings/lrec2008/summaries/420.html>

Please cite a JWKTL-related article if you use the software in your 
scientific work.


Project Background
------------------

Prior to being available as open source software, JWKTL was a research 
project at the Ubiquitous Knowledge Processing (UKP) Lab of Technische 
Universität Darmstadt, Germany. The following people are main contributors 
to this project (in alphabetical order):

* Jan Berkel
* Yevgen Chebotar
* Iryna Gurevych
* Christian M. Meyer
* Christof Müller
* Lizhen Qu
* Torsten Zesch 

This product includes software from third parties. See the respective 
license agreements in the `lib` subdirectory for details.
