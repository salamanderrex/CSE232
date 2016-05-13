
package project1.xquery.value;

import java.util.*;

public class ScopeList extends MyQueryElement implements List<MyScope> {
    public List<MyScope> varEnvs;

    public ScopeList() {
        varEnvs = new ArrayList<>();
    }

    @Override
    public int size() {
        return varEnvs.size();
    }

    @Override
    public boolean isEmpty() {
        return varEnvs.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return varEnvs.contains(o);
    }

    @Override
    public Iterator<MyScope> iterator() {
        return varEnvs.iterator();
    }

    @Override
    public Object[] toArray() {
        return varEnvs.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return varEnvs.toArray(ts);
    }

    @Override
    public boolean add(MyScope MyScope) {
        return varEnvs.add(MyScope);
    }

    @Override
    public boolean remove(Object o) {
        return varEnvs.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return varEnvs.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends MyScope> collection) {
        return varEnvs.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends MyScope> collection) {
        return varEnvs.addAll(i, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return varEnvs.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return varEnvs.retainAll(collection);
    }

    @Override
    public void clear() {
        varEnvs.clear();

    }

    @Override
    public MyScope get(int i) {
        return varEnvs.get(i);
    }

    @Override
    public MyScope set(int i, MyScope MyScope) {
        return varEnvs.set(i, MyScope);
    }

    @Override
    public void add(int i, MyScope MyScope) {
        varEnvs.add(i, MyScope);

    }

    @Override
    public MyScope remove(int i) {
        return varEnvs.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return varEnvs.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return varEnvs.lastIndexOf(o);
    }

    @Override
    public ListIterator<MyScope> listIterator() {
        return varEnvs.listIterator();
    }

    @Override
    public ListIterator<MyScope> listIterator(int i) {
        return varEnvs.listIterator(i);
    }

    @Override
    public List<MyScope> subList(int i, int i1) {
        return varEnvs.subList(i, i1);
    }
}
