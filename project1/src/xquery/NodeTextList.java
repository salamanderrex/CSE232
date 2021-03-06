package project1.xquery;
import project1.xquery.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class NodeTextList extends MyQueryElement implements Iterable<XMLElement>, Collection<XMLElement>, List<XMLElement> {
    public List<XMLElement> values;

    public NodeTextList(int size) {
        this.values = Collections.synchronizedList( new ArrayList<>(size));
    }

    public NodeTextList() {
        this(10);
    }



    public NodeTextList(XMLElement e) {
        this(1);
        values.add(e);
    }

    public NodeTextList(List<XMLElement> values) {
        this.values = values;
    }

    public NodeTextList unique(){
        if (values == null)
            return null;
        NodeTextList results = new NodeTextList();

        values.stream().filter(e -> !containsRef(results, e)).forEach(results::add);
        return results;
    }

    private boolean containsRef(List<XMLElement> list, XMLElement elem) {
        for (XMLElement x : list)
            if (!(x == null) && x.equalsRef(elem))
                return true;
        return false;
    }

    public XQueryBoolean equalsId(NodeTextList o) {
        for(XMLElement x : this)
            for(XMLElement y : o)
                if(x.equalsRef(y))
                    return XQueryBoolean.XQueryBooleanFactory(true);
        return XQueryBoolean.XQueryBooleanFactory(false);
    }

    public XQueryBoolean empty() {
        if(this.size() == 0)
            return  XQueryBoolean.XQueryBooleanFactory(true);
        return XQueryBoolean.XQueryBooleanFactory(false);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof NodeTextList) {
            NodeTextList o = ((NodeTextList) obj);
            return equalsVal(o).booleanFlag == true;
        }

        return false;
    }

    public XQueryBoolean equalsVal(NodeTextList o) {
        for(XMLElement x : this)
            for(XMLElement y : o)
                if(x.equals(y))
                    return  XQueryBoolean.XQueryBooleanFactory(true);
        return XQueryBoolean.XQueryBooleanFactory(false);
    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return values.contains(o);
    }

    @Override
    public Iterator<XMLElement> iterator() {
        return values.iterator();
    }

    @Override
    public Object[] toArray() {
        return values.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return values.toArray(a);
    }

    @Override
    public boolean add(XMLElement e) {
        return e != null && values.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return values.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends XMLElement> c) {
        boolean result = false;
        for (XMLElement x : c)
            result |= add(x);
        return result;
    }

    public boolean gentalAdd(Collection<? extends XMLElement> c) {
        boolean result = false;
        for (XMLElement x : c) {
            result |= add(x);
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends XMLElement> c) {
        return values.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return values.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super XMLElement> filter) {
        return values.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return values.retainAll(c);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public XMLElement get(int index) {
        if (values.size() > 0)
            return values.get(index);
        else
            return null;
    }

    @Override
    public XMLElement set(int index, XMLElement element) {
        return values.set(index, element);
    }

    @Override
    public void add(int index, XMLElement element) {
        values.add(index, element);
    }

    @Override
    public XMLElement remove(int index) {
        return values.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return values.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return values.lastIndexOf(o);
    }

    @Override
    public ListIterator<XMLElement> listIterator() {
        return values.listIterator();
    }

    @Override
    public ListIterator<XMLElement> listIterator(int index) {
        return values.listIterator(index);
    }

    @Override
    public List<XMLElement> subList(int fromIndex, int toIndex) {
        return values.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer action) {
        values.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return values.spliterator();
    }

    @Override
    public Stream<XMLElement> stream() {
        return values.stream();
    }

    @Override
    public Stream<XMLElement> parallelStream() {
        return values.parallelStream();
    }
}
