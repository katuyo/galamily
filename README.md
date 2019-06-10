#Galamily

### A project of galaxy network shop

#### The combination of Galaxy and Family, make customers feel like in their warm home

##### This a algorithm application to convert between Roman Numeral Sequence and Number value. It would be also able to convert your own symbols to Roman numerals, with your determined exchange rate, then calculate materials' all kinds of worth.

--------
E.g:

After you told it rules such as:
* glob is I
* prok is V
* pish is X
* tegj is L
* glob glob Silver is 34 Credits
* glob prok Gold is 57800 Credits
* pish pish Iron is 3910 Credits

Then ask it questions below:
- how much is pish tegj glob glob ?
- how many Credits is glob prok Silver ?
- how many Credits is glob prok Gold ?
- how many Credits is glob prok Iron ?
- how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

It may reply you like:
+ pish tegj glob glob is 42
+ glob prok Silver is 68 Credits
+ glob prok Gold is 57800 Credits
+ glob prok Iron is 782 Credits
+ I have no idea what you are talking about

--------

There are multi-ways to operate the program like:

You can run
> Main.main to deal with classpath origin.txt and origin_2.txt;

> pom.xml For only Test case use

And divided the issue into 5 modules: application, 2 mappings, roman symbol format, roman symbol validate

Application: The program's entry
Roman digit mapping: Map some word to Roman symbols
Exchange mapping: Map which `ask` can exchange with what `bid`
Roman numeric formatter: For converting number between Roman Symbol Sequence and Arabic Number
Roman numeric validate: Check if a roman symbol Sequence is legal.

Exchange: Something used to be exchanged with another exchange.
ExchangeRate: The rate of exchange between things/exchange.
