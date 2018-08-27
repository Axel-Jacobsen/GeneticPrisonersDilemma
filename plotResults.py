"""
Python file to plot the results of tournaments; Python 2 or 3
"""

import matplotlib.pyplot as plt
import numpy as np
from matplotlib.animation import FuncAnimation


def gather_data():

    xs = []
    ys = []

    tempX = []
    tempY = []

    with open('geneticRes.txt', 'r') as fromFile:

        for line in fromFile:
            if line != '-------\n':
                l = line.strip().split(' ')
                tempX.append(l[0])
                tempY.append(l[1])
            else:
                xs.append(tempX)
                ys.append(tempY)
                tempX = []
                tempY = []

    return xs, ys

# def plot_weight_to_score(from_filename, t):

def update(i):
    global xs
    global ys
    ax.scatter(xs[i], ys[i], s=1)

if __name__ == '__main__':

    xs, ys = gather_data()

    fig, ax = plt.subplots()
    fig.set_tight_layout(True)
    # plt.axis('off')

    # plt.title('Genetic Evaluation')
    plt.xlabel('Weights')
    plt.ylabel('Tournament Scores')
    ax.set_yticklabels([])
    ax.set_xticklabels([])

    plt.xlim(-0.1, 1.1)
    print(len(ys))
    anim = FuncAnimation(fig, update, frames=np.arange(0, len(ys)), interval=40, repeat_delay=0)
    print("doneeroo")
    anim.save('line.gif', dpi=120, writer='imagemagick')