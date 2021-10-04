from numpy import random, exp, dot, array


# 正向传播
def forwardPropagation(input):
    # sigmoid function
    layer1 = 1/(1 + exp(-dot(input, w0)))
    layer2 = 1/(1 + exp(-dot(layer1, w1)))
    return layer1, layer2


# 反向传播
def backproPagation(rdata, l1, l2):
    error = rdata - l2
    slope = l2 * (1 - l2)
    l1_delta = error * slope

    l0_slope = l1 * (1-l1)
    l0_error = l1_delta.dot(w1.T)
    l0_delta = l0_error * l0_slope
    return l0_delta, l1_delta


# ================ multiple neural network ================
# test data
X = array([[0,0,1],[0,1,1],[1,0,1],[1,1,1],[1,0,0]])
# correct data
y = array([[0,1,1,0,1]]).T

random.seed(1)
# input layer -> hidden layer - 5个神经元
w0 = 2 * random.random((3, 5)) - 1
# hidden layer -> output layer - 1个输出
w1 = 2 * random.random((5, 1)) - 1

for it in range(500):
    layer0 = X
    layer1, layer2 = forwardPropagation(layer0)
    delta_layer0, delta_layer1 = backproPagation(y, layer1, layer2)

    w1 = w1 + dot(layer1.T, delta_layer1)
    w0 = w0 + dot(layer0.T, delta_layer0)

print(forwardPropagation([0,1,0])[1])

# ================ multiple neural network ================