"""
Python file to plot the results of tournaments; Python 2 or 3
"""

import matplotlib.pyplot as plt


def plot_weight_to_score(from_filename):

    xs = []
    ys = []

    with open(from_filename, 'r') as fromFile:

        for line in fromFile:
            l = line.strip().split(' ')
            xs.append(l[0])
            ys.append(l[1])

    plt.scatter(xs, ys)
    plt.xlabel('Weights')
    plt.ylabel('Tournament Scores')
    plt.show()

if __name__ == '__main__':
    plot_weight_to_score('geneticRes.txt')
    plot_weight_to_score('staticRes.txt')