# -*- coding: utf-8 -*-
import numpy as np
import loader
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.linear_model import LogisticRegression, SGDClassifier


train_inputs, train_output= loader.load_data_sklearn()
X_train, X_test, y_train, y_test = train_test_split(train_inputs, train_output, test_size=0.2, random_state=50000)

scaler = StandardScaler()
scaler.fit(train_inputs)
X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)


# clf = SGDClassifier(shuffle=True)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

lr = LogisticRegression(multi_class="ovr")
lr.fit(X_train, y_train)
y_predict = lr.predict(X_test)
print("准确率：", lr.score(X_test, y_test))

# print("召回率：", classification_report(y_test, y_predict))

# bagging = BaggingClassifier(KNeighborsClassifier(),  max_samples=0.5, max_features=0.5)
# bagging.fit(X_train, y_train)
# y_predict = bagging.predict(X_test)
# print("准确率：", bagging.score(X_test, y_test))

# clf = RandomForestClassifier(n_estimators=10, max_depth=None,  min_samples_split=2, random_state=0)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# clf = DecisionTreeClassifier(max_depth=None, min_samples_split=2, random_state=0)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# clf = ExtraTreesClassifier(n_estimators=10, max_depth=None, min_samples_split=2, random_state=0)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# clf = AdaBoostClassifier(n_estimators=100)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# clf = GradientBoostingClassifier(n_estimators=100, learning_rate=1.0, max_depth=5, random_state=0)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# clf = HistGradientBoostingClassifier(max_iter=100)
# clf = clf.fit(X_train, y_train)
# y_predict = clf.predict(X_test)
# print("准确率：", clf.score(X_test, y_test))

# est = make_pipeline(StandardScaler(), SGDClassifier())
# est.fit(X_train, y_train)
# y_predict = est.predict(X_test)
# print("准确率：", est.score(X_test, y_test))