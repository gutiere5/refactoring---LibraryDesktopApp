Assignment 03: Group Project Feature & Performance Report 

We have implemented the Library Desktop App as a graphical interface application that allows you to search through records imported from a CSV file. From these records it will display each book’s ID, title, author(s), ISBN, publication year, and average rating. Our application satisfies the following requirements: 

    Import records from CSV file 

    Search by ID or ISBN 

    Sort by Author or Publication Year (Ascending or Descending order) 

    Launch with the top 10 records on screen 

    Two version options to be implemented: 

    ArrayList using binary search 

    LinkedList using linear search 

    A performance testing mode to display how long the search took for each implementation. 

In addition to these requirements, we have made it so that you can sort by any of the implemented columns. Several quality of life features have been added to improve the user experience: 

    Realtime search while typing, which is disabled when engaging the performance test 

    Columns can be rearranged thanks to the JTable implementation 

    Dialog feedback when the search has failed 

    When typing a query into the search field, the “Enter” key will engage the search in addition to clicking the Search button. 

    Book icon added to distinguish the application 

We found the ArrayList (binary search) to have significant improvements over the linear search method, and recommend implementation of this version. 

    Searching ArrayList holding 500 records:
    ID: 238000ns
    ISBN: 157600ns
    Searching ArrayList holding 1000 records:
    ID: 6200ns
    ISBN: 18800ns

    Searching LinkedList holding 500 records:
    ID: 247900ns
    ISBN: 226400ns
    Searching LinkedList holding 1000 records:
    ID: 948600ns
    ISBN: 737800ns
