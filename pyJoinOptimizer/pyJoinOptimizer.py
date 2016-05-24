__author__ = 'qingyu'
import re
import networkx as nx
import matplotlib.pyplot as plt
from networkx.drawing.nx_pydot import write_dot

class JoinOptimizer(object):
    def __init__(self,querys,fileName):
        self.querys = querys
        self.fileName = fileName
        self.forMap = self.createForMap(querys)
        print "for map is ============"
        print self.forMap
        self.wherePair = self.createWhereMap(querys)
        print "where Pair is=============== "
        print self.wherePair
        self.DG = self.graphMaker(self.forMap)
        print "test to print the graph ============"
        print ""
        self.graphPrinter(self.DG)

        print "variable in reutrn============="
        self.returnVariable = self.createReturnList(querys)
        print self.returnVariable

    def createForMap(self,querys):
        variable_pair = {}
        tokens = self.decomposeQuery(querys)
        print "decompose to...."
        print tokens
        #find inFor
        for_flag = False
        itr = iter(tokens)
        while True:
            word = itr.next()
            if word == "return":
                #print "end for assignmentMap"
                break
            if word=="for":
                for_flag = True
                continue
            if (word == "where" or word == "return"):
                break
            if for_flag:
                #print "meet for"
                var_ = word
                in_ = itr.next()
                val_ = itr.next()
                variable_pair[var_] = val_
                #print "insert" + var_ + ":" + val_
        #print  "for is====== "
        #print variable_pair
        return variable_pair


    def createWhereMap(self,querys):
        variable_pair = []
        tokens = self.decomposeQuery(querys)
        print "decompose to...."
        print tokens
        #find inwhere
        where_flag = False
        itr = iter(tokens)
        while True:
            word = itr.next()
            if word == "return":
                #print "end for assignmentMap"
                break
            if word=="where":
                where_flag = True
                continue
            if where_flag:
                #print "meet where@",word
                var1 = word
                in_ = itr.next() #this should be eq
                var2 = itr.next()
                variable_pair.append((var1, var2))
        return variable_pair



    def createReturnList(self,querys):
        variables = []
        tokens = self.decomposeQuery(querys)
        #find where
        where_index = tokens.index("return")
        tokens = tokens[where_index:]
        itr = iter(tokens)
        while True:
            try:
                word = itr.next()
            except StopIteration:
                break
            matchObj = re.match(r'\$\w+',word)
            if matchObj:
                print "match@",word
                variables.append(matchObj.group())
        return variables

    def graphMaker(self,forMap):
        #create a directed graph
        #print "in graph marker"
        #print forMap
        DG = nx.DiGraph()
        for key, value in forMap.iteritems():
            DG.add_node(key)
            #if something like $b/dsfs/sfdsf
            #print "value is "
            print value

            matchObj = re.match(r'(\$\w+)?(.+)', value)
            if matchObj:
                # $b
                target_node = matchObj.group(1)
                path = matchObj.group(2)
                DG.add_edge(key,target_node,path=str(path))
        return DG

    def graphPrinter(self,DG):
        for n,nbrs in DG.adjacency_iter():
            for nbr,eattr in nbrs.items():
                data=eattr['path']
                print('(%s, %s, %s)' % (n,nbr,data))
        write_dot(DG,'file.dot')














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
