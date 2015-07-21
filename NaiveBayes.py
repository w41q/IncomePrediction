import numpy as np
import csv
reader = csv.reader(open('nb_data.data', 'r'))
dicts = []
limit = 20000
counter = 0
for row in reader:
	counter = counter+1
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
	if(counter==limit):
		break

from sklearn.feature_extraction import DictVectorizer
vec = DictVectorizer()
data = vec.fit_transform(dicts).toarray()
trainname = vec.get_feature_names()
f = open('trainname','w')
for i in range(0,len(trainname)):
	f.write(trainname[i]+'\n')
f.close()
print(len(data))
print(len(data[0]))
reader = csv.reader(open('nb_label.data', 'r'))
label0 = []
counter = 0
for row in reader:
	counter = counter+1
	label0.append(row)
	if(counter==limit):
		break
print(len(label0))
label = np.array(label0)

# from sklearn.naive_bayes import BernoulliNB
# clf = BernoulliNB()
from sklearn.naive_bayes import MultinomialNB
clf = MultinomialNB()
clf.fit(data, label)

a = np.array(['L','H'])

print(clf.predict(data[6])==a[0])
print(clf.predict(data[7])==a[1])

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
testname = vec.get_feature_names()
f = open('testname','w')
for i in range(0,len(testname)):
	f.write(testname[i]+'\n')
f.close()
reader = csv.reader(open('nb_label.test', 'r'))
testlabel0 = []
for row in reader:
	testlabel0.append(row)
print(len(testlabel0))
testlabel = np.array(testlabel0)

count = 0
for i in range(0,len(testdata)):
	if(clf.predict(testdata[i])==testlabel[i]):
		count = count+1
print(count)
print(1.0*count/len(testdata))