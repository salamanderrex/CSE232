__author__ = 'qingyu'
import re
import networkx as nx
#import matplotlib.pyplot as plt
from collections import deque

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

        print "variable in reutrn============="
        self.returnVariable = self.createReturnList(querys)
        print self.returnVariable

        self.DG = self.graphMaker(self.forMap)
        print "test to print the graph ============"
        self.graphPrinter(self.DG)

        print "connented components========"
        self.components = self.componentsFinder(self.DG)
        for c in self.components:
            print "compoents ",c[1],c[0]

        '''
        make assumption here
        how many component means how many join
        '''
        print "regerenate where ========"
        self.new_Clause= self.reGenerate_returnInJoin(self.components,self.returnVariable,self.wherePair)

        print "new clause dictionary ======"
        print self.new_Clause

        print 'make final song'
        print self.finalSongMaker()




    def whereMaker(self,nWhere,indent):
        if not nWhere:
            return ""

        return ' '*indent+ '\twhere ' + ','.join(map(lambda (a,b): a +' eq '+b,nWhere)) +'\n'

    def returnMaker(self,nReturn,indent):
        return  ' '*indent+"\treturn <tuple>" \
                + '\n\t'\
                + '\n\t'.join(map(lambda x : ' '*indent + '<'+x[1:]+'> {' + x+ '}<'+x[1:]+'>', nReturn)) \
                + '\n' +' '*indent+'\t</tuple>,'

    def forMaker(self,nFor,indent):
        return  '\n'+' '*indent+'\tfor'  \
                + "\n\t" \
                +'\n\t'.join(map(lambda x: ' '*indent + x + ' in ' +\
                                          str(map ( lambda c:  c[1]+ c[2] if c[1] else c[2] ,self.DG.edges(x,data="path"))[0]) ,nFor)) \
                + '\t'+ '\n'
    def listMakder(self,nList1,nList2,indent):
        return  "\n"\
                +" "*indent +str(map (lambda x: x[1:],nList1)) + "," + str(map(lambda x: x[1:],nList2))


    def finalSongMaker(self):
        base = ""
        indent = 20
        for x in self.new_Clause:
            if not base:
                base = self.songMaker(x,base,indent)

            base = self.songMaker(x,base,indent)

            indent -=6
        return base

    def songMaker(self,joiner,base_str,indent):
        print "joiner",joiner

        #if empty, make a base
        indent = indent +2
        def make_MetaStr(i):
            return  self.forMaker(joiner['nFor'+i],indent) \
                     + self.whereMaker(joiner['nWhere'+i],indent) \
                    + self.returnMaker(joiner['nReturn'+i],indent)
        if not base_str:
            return make_MetaStr('1') \
                    +"\n"
        else:
            return  ' '*indent+"\tjoin(\n" \
                    + base_str + make_MetaStr('2') \
                    + self.listMakder(joiner['nList1'],joiner['nList2'],indent) \
                    +'\n'+' '*indent+"),"










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
        #print "decompose to...."
        #print tokens
        #find inwhere
        where_flag = False
        itr = iter(tokens)
        while True:
            try:
                word = itr.next()
            except StopIteration:
                break

            if word == "return":
                #print "end for assignmentMap"
                break
            if word=="where":
                where_flag = True
                continue
            if where_flag:
                #print "meet where@",word
                # is a pure variable !!!!!!! , like $a
                matchObj = re.match(r'(\$\w+)',word)
                if matchObj:
                    var1 = matchObj.group(1)
                    in_ = itr.next() #this should be eq
                    var2 = itr.next()
                    variable_pair.append((var1, var2))
        return variable_pair



    def createReturnList(self,querys):
        variables = []
        tokens = self.decomposeQuery(querys)
        #find where
        where_index = tokens.index("return")
        tokens = tokens[where_index+1:]
        #print tokens
        itr = iter(tokens)
        while True:
            try:
                word = itr.next()
            except StopIteration:
                break
            matchObj = re.match(r'.*(\$\w+).*',word)
            if matchObj:
                variables.append(matchObj.group(1))
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
            #print value

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


    def componentsFinder(self,DG):
        uG = DG.to_undirected()
        edge_list = []
        for (a,b) in uG.edges():
            print a,b
            if (a is not None) and (b is not None):
                edge_list.append((a,b))
        #print edge_list
        new_graph_without_None = nx.Graph(edge_list)
        i = 0
        '''
        return_list = []
        for h in nx.connected_components(new_graph_without_None):
            print "component ", i, " : ", h
            return_list.append((h,i))
            i += 1
        i = 0
        '''

        return_list =  sorted([ sorted(list(x)) for x in list(nx.connected_components(new_graph_without_None))], key= lambda x: x[0])
        return zip(return_list,range((len(return_list))))


    #label component number for node
    def label_componentNum(self,components,list):
        list = map (lambda var: (var,-1),list)
        i = 0
        for component in components:
            #print ""
            #print "normal here",component
            list  = map(lambda (var,label): (var, i) if var in component[0] else (var,label),list)
            i += 1
        return sorted(list, key=lambda p : p[1])


    # [($a,$b),($c,$d)]
    def label_componentNum_Pair(self,components,list):
        list = map(lambda (v1,v2): ((v1,-1),(v2,-1)), list)
        i = 0
        for component in components:
            #print ""
            #print "pair here",component
            list = map(lambda ((v1,l1),(v2,l2)):
                                (
                                    (v1, i if v1 in component[0] else l1),
                                    (v2, i if v2 in component[0] else l2)
                                ),
                        list)
            i += 1
        #sort it to make nicer to check
        list = [ ((v1,l1),(v2,l2)) if (l1<l2) else ((v2,l2),(v1,l1)) for ((v1,l1),(v2,l2)) in list]
        list = [ ((v1,l1),(v2,l2)) if (l1!=-1) else ((v2,l2),(v1,l1)) for ((v1,l1),(v2,l2)) in list]
        list = sorted(list,key = lambda x: x[0][1])
        return list

    #find parent of
    def reGenerate_for(self,return_clause):
        #find root first
        random_a_vable = next(iter(return_clause))
        #print "pick",random_a_vable
        pre_root =  random_a_vable
        root = self.DG.successors(random_a_vable)[0]
        #print "regerent",root
        while (root!= None):
            pre_root = root

            root = self.DG.successors(root)
            #print root
            if root !=None:
                root = root[0]
            #print "a new root", root
        print "choose", pre_root, "as the component entry"

        #dfs
        mylist = []
        queue = deque()
        queue.append(pre_root)
        mylist.append(pre_root)
        while len(queue) >0:
            node = queue.pop()
            #print "node is ",node
            a_list = self.DG.predecessors(node)
            for x in a_list:
                mylist.append(x)
                queue.append(x)
        print "for loop variables are", mylist
        return mylist








    def reGenerate_where(self):
        pass
    def reGenerate_returnInJoin(self,components,return_list,where_list):
        labeled_where = self.label_componentNum_Pair(components,where_list)
        print "where  ===>", labeled_where

        labeled_return = self.label_componentNum(components,return_list)
        print "return ===>" , labeled_return


        final_return = []
        #-1 is contant
        for first_x in range(len(components)-1):
            print first_x, "join+++++++++++++++++++++++"
            co1 = first_x
            co2 = first_x+1
            #analysis base on co2

            where_co = []
            for t_co in (co1,co2):
                where_co1 = filter(lambda (a,b): a[1] == t_co and b[1] == -1, labeled_where)
                where_co1 = map(lambda (a,b): (a[0],b[0]),where_co1)
                where_co.append(where_co1)

            print "where co",co1,where_co[0]
            print "where co",co2,where_co[1]


            #return = where + return
            return_co=[]
            for t_co in (co1,co2):
                return_t_co =  filter(lambda (a,b) : a[1] ==t_co or b[1] ==t_co,labeled_where)
                #remove constant
                return_t_co=  filter(lambda (a,b): a[1] is not -1 and b[1] is not  -1,return_t_co)
                return_t_co = map(lambda (a,b): a[0] if a[1] ==t_co else b[0],return_t_co)

                return_var_detector = filter(lambda (a,b): b ==t_co,labeled_return)
                return_var_detector = map(lambda (a,b): a,return_var_detector)
                t_set = set()
                return_t_co = t_set.union(return_t_co,return_var_detector)

                return_co.append(return_t_co)

            print "return co ",co1,return_co[0]
            print "return co " ,co2,return_co[1]





            lists_part = []
            for t_co in (co1,co2):
                if t_co == co1:
                    return_list_co = filter(lambda (a,b): (a[1] == co1 and b[1] <= co2) #different here
                                                        ,labeled_where)
                if t_co == co2:
                    return_list_co = filter(lambda (a,b): (a[1] == co2 and b[1] <= co2)
                                                        or  (b[1] == co2 and a[1] <= co2),labeled_where)
                return_list_co = filter(lambda (a,b): b[1] !=-1,return_list_co)
                return_list_co = map(lambda (a,b): a if a[1] == t_co else b,return_list_co)
                return_list_co = map(lambda (a,b): a,return_list_co)
                lists_part.append(return_list_co)

            print "list co",co1,lists_part[0]
            print "list co",co2,lists_part[1]


            print ""


            newFor_1 = self.reGenerate_for(return_co[0])
            newFor_2 = self.reGenerate_for(return_co[1])


            print 'end',first_x, "join+++++++++++++++++++++++"
            print ''


            some_shit = {}
            some_shit['nFor1'] = newFor_1
            some_shit['nFor2'] = newFor_2
            some_shit['nWhere1'] = where_co[0]
            some_shit['nWhere2'] = where_co[1]
            some_shit['nReturn1'] =  return_co[0]
            some_shit['nReturn2'] = return_co[1]
            some_shit['nList1'] = lists_part[0]
            some_shit['nList2'] = lists_part[1]
            final_return.append(some_shit)
        return final_return
































    def decomposeQuery(self,querys):
        r_list = []
        for query in querys:
            matchObj = re.findall(r'(\S*|\".+?\")\s*',query)
            if (matchObj):
                #if origin has , then we need to remove,
                r_list.append(matchObj)
        return filter(None,reduce(lambda x, y : x + y,r_list))

    def isVariabl(self,str):
        matchObj = re.match(r'\$\w+',str)
        if matchObj:
            return True
        return False











def main():
    fileName = 'testcase'
    with open(fileName,'r') as myfile:
        data = map(lambda x: x.strip(),myfile.read().splitlines())
        print data
    JoinOptimizer(data,fileName)
if __name__ == "__main__":
    main()
