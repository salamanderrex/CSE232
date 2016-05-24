__author__ = 'qingyu'
import re
class JoinOptimizer(object):
    def __init__(self,querys,fileName):
        self.querys = querys
        self.fileName = fileName
        self.createForMap(querys)


    def createForMap(self,querys):
        variable_pair = {}
        tokens = self.decomposeQuery(querys)
        print "decompose to...."
        print tokens
        #find inFor
        where_flag = False
        itr = iter(tokens)
        while True:
            word = itr.next()
            if word == "return":
                print "end for assignmentMap"
                break
            if word=="where":
                where_flag = True
                continue
            if where_flag:
                print "meet where"
                var_ = word
                in_ = itr.next()
                val_ = itr.next()
                variable_pair[var_] = val_
        print variable_pair









    def decomposeQuery(self,querys):
        r_list = []
        for query in querys:
            matchObj = re.findall(r'(\S*|\".+?\")\s*',query)
            if (matchObj):
                #if origin has , then we need to remove,
                r_list.append(matchObj)
        return filter(None,reduce(lambda x, y : x + y,r_list))










def main():
    fileName = 'testcase'
    with open(fileName,'r') as myfile:
        data = map(lambda x: x.strip(),myfile.read().splitlines())
        print data
    JoinOptimizer(data,fileName)
if __name__ == "__main__":
    main()
