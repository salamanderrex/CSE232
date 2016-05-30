__author__ = 'kezhang'
import pyJoinOptimizer as Op

def main(i):
    fileName = 'testcase' + str(i)
    with open(fileName,'r') as myfile:
        origindata = myfile.read()
        data = map(lambda x: x.strip(),origindata.splitlines())
        print data
    Op.JoinOptimizer(data,fileName,origindata)
if __name__ == "__main__":
    testcase_number = 4
    for i in range(1,testcase_number + 1):
        print "============= Start test case" , i , "=================="
        main(i)
        print "=============  end test case " + str(i) + "==============="
