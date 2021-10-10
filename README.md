# JsonSearch
## Scope and preamble
Considering  
1. it is a weekend project,  
2. and the main purpose is to demonstarte that the candidate can write good code in Scala using right data structures and algorithms for simple and performant solution,  
3. and considering the main functionality to implement is search functionality (from a list of entities and related entities)  

I have  

a. Not written much code for obvious defensive coding - e.g. file not found, or invalid input format etc.  
b. Implemented the search only based on a few fields, but the principle I would apply for other fields are more or less similar (except for boolean based search)   
c. The tags search is implemented on individual tags (i.e. search based on any single tag)  
d. The date based search has not been implemented (to save time, among other things, but also one question here is - in what format the date will be input at console by the user for search - this can not be random, and the format must be predefined for parsing (or perhaps format itself given as parameter)

Note : The problem statement specifies "we just want to see that you can code a basic but efficient search application. Ideally, search response times should not increase linearly as the number of documents grows". I tried to keep the solution simple, without too much code, and the search with a lower operation complexity. The principle is demonstarted without applying it to all fields.

## Assumptions
### Environment
Scala/SBT etc. are installed on the machine where the code will be tested.
### Others
1. Input files are of proper format and present
2. The size of input files can fit in the menory of a single machine (Note the problem description specifies "You can assume all data can fit into memory on a single machine."
3. value of search field (except for _id) may be duplicate across rows.


## execution
* This can be executed by running the run.sh from the directory.
Edit the same to put proper value for the inputDir parameter.  
* Please ensure the input files in the right name and format are present in the directory specified in inputDir prior to run.
