import pickle
import gzip
import numpy as np

def load_train_data():
    f = gzip.open('/Users/chenchristian/Development/PycharmProjects/practices/python/ml_assignment/formal/train-io.txt.gz', 'rb')
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

def load_data_sklearn():
    data = load_train_data()
    np.random.shuffle(data)
    train_inputs = []
    train_output = []

    for x in data:
        x, y = np.split(x, [10])
        train_inputs.append(x)
        train_output.append(int(y[0]))
    
    return train_inputs, train_output

def load_data_wrapper():
    data = load_train_data()
    # test_data = load_test_data()
    train_data, test_data = np.split(data, [80000])

    tmp_train_input = []
    train_output = []
    tmp_test_input = []
    test_output = []

    for x in train_data:
        x, y = np.split(x, [10])
        tmp_train_input.append(x)
        train_output.append(vectorized_result(int(y[0])))
    
    for x in test_data:
        x, y = np.split(x, [10])
        tmp_test_input.append(x)
        test_output.append(int(y[0]))
    
    train_inputs = [np.reshape(x, (10, 1)) for x in tmp_train_input]
    training_data = list(zip(train_inputs, train_output))
    test_inputs = [np.reshape(x, (10, 1)) for x in tmp_test_input]
    varification_data = list(zip(test_inputs, test_output))

    return training_data, varification_data