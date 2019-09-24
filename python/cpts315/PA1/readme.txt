Disclamer: This program takes a long time to run on big datasets. It was tested on dataset that contains 568 rows and 6553 total items.
Otherwise it will take hours to run on anything larger. I tried even using efficient-apriori library and it took longer.

P.S. It seems like the issue is not the algorithm, but conversion of output into python list. As just running the algorithm on 380824 items dataset
takes 0.000003 s for pairs. But conversion takes hours.

To use program you can do in 3 steps:

1. run it as "python cross-selling.py" and give it an argument to a text file.

2. For Windows run bat file called "run_code.bat"

3. For Linux run shell script file called "run_code.sh"