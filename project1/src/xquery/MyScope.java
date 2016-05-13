
package project1.xquery;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyScope extends MyQueryElement implements Map<String, NodeTextList> {
    private Map<String, NodeTextList> varEnv = new HashMap<>();

    public MyScope() {
    }

    public NodeTextList getVar(String varName){
        NodeTextList res = varEnv.get(varName);
        if(res != null)
            return res;
        return new NodeTextList();
    }

    public MyScope copy(){
        MyScope ve = new MyScope();
        ve.varEnv.putAll(this.varEnv);
        return ve;
    }

    @Override
    public int size() {
        return varEnv.size();
    }

    @Override
    public boolean isEmpty() {
        return varEnv.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return varEnv.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return varEnv.containsValue(value);
    }

    @Override
    public NodeTextList get(Object key) {
        return varEnv.get(key);
    }

    @Override
    public NodeTextList put(String key, NodeTextList value) {
        return varEnv.put(key, value);
    }

    @Override
    public NodeTextList remove(Object key) {
        return varEnv.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends NodeTextList> m) {
        varEnv.putAll(m);
    }

    @Override
    public void clear() {
        varEnv.clear();
    }

    @Override
    public Set<String> keySet() {
        return varEnv.keySet();
    }

    @Override
    public Collection<NodeTextList> values() {
        return varEnv.values();
    }

    @Override
    public Set<Entry<String, NodeTextList>> entrySet() {
        return varEnv.entrySet();
    }
}
