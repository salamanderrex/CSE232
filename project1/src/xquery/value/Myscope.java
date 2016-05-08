package project1.xquery.value;

/**
 * Created by kezhang on 5/6/16.
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Myscope extends MyQueryElement implements  Map<String, XQueryList> {
    private Map<String, XQueryList> varEnv = new HashMap<>();

    public Myscope() {
    }

    public XQueryList getVar(String varName){
        XQueryList res = varEnv.get(varName);

        if(res != null)
            return res;
        return new XQueryList();
    }

    public Myscope copy(){
        Myscope ve = new Myscope();
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
    public XQueryList get(Object key) {
        return varEnv.get(key);
    }

    @Override
    public XQueryList put(String key, XQueryList value) {
        return varEnv.put(key, value);
    }

    @Override
    public XQueryList remove(Object key) {
        return varEnv.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends XQueryList> m) {
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
    public Collection<XQueryList> values() {
        return varEnv.values();
    }

    @Override
    public Set<Entry<String, XQueryList>> entrySet() {
        return varEnv.entrySet();
    }
}
