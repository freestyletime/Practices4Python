from numpy import random, exp, dot, array


# 正向传播
def forwardPropagation(input, w):
    # 做矩阵点乘
    z = dot(input, w)
    # sigmoid function
    return 1/(1 + exp(-z))


# 反向传播
def backproPagation(rdata, output):
    error = rdata - output
    # gradient
    slope = output * (1 - output)
    # Gradient descent algorithm
    return error * slope


# ================ perceptron simulation ================
# test data
X = array([[0,0,1],[1,1,1],[1,0,1],[0,1,1]])
# correct data
y = array([[0,1,1,0]]).T

random.seed(1)
weights = 2 * random.random((3, 1)) - 1

for it in range(100):
    output = forwardPropagation(X, weights)
    delta = backproPagation(y, output)
    weights = weights + dot(X.T, delta)

print(forwardPropagation([[1,1,1]], weights));
# ================ perceptron simulation ================