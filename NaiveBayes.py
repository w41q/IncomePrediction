import numpy as np
import csv
reader = csv.reader(open('nb_data.data', 'r'))
dicts = []

# Read in training feature
for row in reader:
	dict = {}
	dict['age'] = row[0]
	dict['workclass'] = row[1]
	dict['fnlwgt'] = row[2]
	dict['education'] = row[3]
	dict['education-num'] = row[4]
	dict['marital-status'] = row[5]
	dict['occupation'] = row[6]
	dict['relationship'] = row[7]
	dict['race'] = row[8]
	dict['sex'] = row[9]
	dict['capital-gain'] = row[10]
	dict['capital-loss'] = row[11]
	dict['hours-per-week'] = row[12]
	dict['native-country'] = row[13]
	dicts.append(dict)

# Import and initialize dictionary vetorizer
from sklearn.feature_extraction import DictVectorizer
vec = DictVectorizer()
data = vec.fit_transform(dicts).toarray()
print(len(data))
print(len(data[0]))

# Read in training label
reader = csv.reader(open('nb_label.data', 'r'))
label0 = []
for row in reader:
	label0.append(row)
print(len(label0))
label = np.array(label0)

# Import and initialize data model
from sklearn.naive_bayes import MultinomialNB
clf = MultinomialNB()
clf.fit(data, label)

a = np.array(['L','H'])

print(clf.predict(data[6])==a[0])
print(clf.predict(data[7])==a[1])

# Read in testing feature
reader = csv.reader(open('nb_data.test', 'r'))
dicts2 = []
for row in reader:
	dict = {}
	dict['age'] = row[0]
	dict['workclass'] = row[1]
	dict['fnlwgt'] = row[2]
	dict['education'] = row[3]
	dict['education-num'] = row[4]
	dict['marital-status'] = row[5]
	dict['occupation'] = row[6]
	dict['relationship'] = row[7]
	dict['race'] = row[8]
	dict['sex'] = row[9]
	dict['capital-gain'] = row[10]
	dict['capital-loss'] = row[11]
	dict['hours-per-week'] = row[12]
	dict['native-country'] = row[13]
	dicts2.append(dict)

testdata = vec.fit_transform(dicts2).toarray()
print(len(testdata))
print(len(testdata[0]))

# Read in testing label
reader = csv.reader(open('nb_label.test', 'r'))
testlabel0 = []
for row in reader:
	testlabel0.append(row)
print(len(testlabel0))
testlabel = np.array(testlabel0)

# Predict and calculate accuracy
count = 0
for i in range(0,len(testdata)):
	if(clf.predict(testdata[i])==testlabel[i]):
		count = count+1
print(count)
print(1.0*count/len(testdata))
