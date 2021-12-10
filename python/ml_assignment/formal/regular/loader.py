import pickle
import gzip
import numpy as np

def load_train_data():
    f = gzip.open('python/ml_assignment/formal/train-io.txt.gz', 'rb')
    data = np.loadtxt(f)
    f.close()
    return data

def load_test_data():
    f = gzip.open('python/ml_assignment/formal/test-i.txt.gz', 'rb')
    data = np.loadtxt(f)
    f.close()
    return data

def vectorized_result(j):
    e = np.zeros((2, 1))
    e[j] = 1.0
    return e

def load_data_wrapper():
    train_data = load_train_data()
    np.random.shuffle(train_data)
    test_data = load_test_data()

    tmp_train_input = []
    train_output = []
    test_output = []

    for x in train_data:
        x, y = np.split(x, [10])
        tmp_train_input.append(x)
        train_output.append(vectorized_result(int(y[0])))
    
    train_inputs = [np.reshape(x, (10, 1)) for x in tmp_train_input]
    training_data = list(zip(train_inputs, train_output))
    test_inputs = [np.reshape(x, (10, 1)) for x in test_data]

    return training_data, test_inputs