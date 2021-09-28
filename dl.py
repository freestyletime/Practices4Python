from numpy import random, exp, dot, array

X = array([[0,0,1],[1,1,1],[1,0,1],[0,1,1]])
y = array([[0,1,1,0]]).T

random.seed(1)
weights = 2 * random.random((3, 1)) - 1

for it in range(20000):
    # 做矩阵点乘
    z = dot(X, weights)
    # sigmoid function
    output = 1/(1 + exp(-z))
    error = y - output
    slope = output * (1 - output)
    delta = error * slope
    weights = weights + dot(X.T, delta)

print(weights)