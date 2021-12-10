import loader
# import nn
import matplotlib.pyplot as plt
import network2

training_data, test_inputs = loader.load_data_wrapper()
net = network2.load("python/ml_assignment/formal/regular/model76.txt")
# net = network2.Network([10, 20, 2])
net.SGD(training_data, 100, 258, 1.0, monitor_training_cost=True, monitor_training_accuracy=True)
# net.save("python/ml_assignment/formal/regular/model76.txt")
# net.save_evaluation(test_inputs, "./python/ml_assignment/formal/regular/test-o.txt");


# fpr, tpr, _ = metrics.roc_curve(y_test,  y_pred_proba)
# #create ROC curve
# plt.plot(fpr,tpr)
# plt.ylabel('True Positive Rate')
# plt.xlabel('False Positive Rate')
# plt.show()