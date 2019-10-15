#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 315
Programming Project #2

Description: This program reads movies and their user reviews and uses item-to-item collaborative filtering to generate recommendations.
             
'''

import pandas as pd
import timeit
import time
from tqdm import tqdm
from IPython.display import display
start = timeit.default_timer()

print("========================================")
print("Reading data files.\n")
movies = pd.read_csv('movies.csv')
links = pd.read_csv('links.csv')
ratings = pd.read_csv('ratings.csv')
tags = pd.read_csv('tags.csv')

data_frame = pd.merge(ratings, movies, on="movieId")

movie_matrix = data_frame.pivot_table(index='userId', columns='title', values='rating')

corr_matrix = movie_matrix.corr(method='pearson', min_periods = 50)
stop = timeit.default_timer()
print("Reading files took (s): %.6f" %(stop - start))
print("========================================\n")

output = open("output.txt", 'w')
output_str = ""
print("Processing Data:")
for i in tqdm(range (1, len(movie_matrix))):
    user_ratings = movie_matrix.iloc[i].dropna()

    recommend = pd.Series()

    for j in range(0, len(user_ratings)):
        similar = corr_matrix[user_ratings.index[j]].dropna()
        similar = similar.map(lambda x: x * user_ratings[j])
        recommend = recommend.append(similar)

    recommend.sort_values(inplace = True, ascending = False)
    stop = timeit.default_timer()

    x = pd.DataFrame(recommend)
    recommend_filter = x[~x.index.isin(user_ratings.index)]
    top_five = recommend_filter.head(5)
    output_str = output_str + str(i)
    list_of_movies = movies.title.to_list()
    for index, row in top_five.iterrows():
        output_str = output_str + " {}".format(list_of_movies.index(index))
    output_str = output_str + "\n"

output.write(output_str)
output.close()

stop = timeit.default_timer()
print("Whole process took (s): %.6f" %(stop - start))
print("========================================\n")