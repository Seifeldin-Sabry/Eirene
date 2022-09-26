import java.util.*;

public class Temperatures implements List<Temperature>{

    private final List<Temperature> temperatures;

    public Temperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public Temperatures() {
        this.temperatures = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Temperatures{" +
                "temperatures=" + temperatures +
                '}';
    }

    @Override
    public int size() {
        return temperatures.size();
    }

    @Override
    public boolean isEmpty() {
        return temperatures.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return temperatures.contains(o);
    }

    @Override
    public Iterator<Temperature> iterator() {
        return temperatures.iterator();
    }

    @Override
    public Object[] toArray() {
        return temperatures.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return temperatures.toArray(a);
    }

    @Override
    public boolean add(Temperature temperature) {
        return temperatures.add(temperature);
    }

    @Override
    public boolean remove(Object o) {
        return temperatures.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return temperatures.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends Temperature> c) {
        return temperatures.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Temperature> c) {
        return temperatures.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return temperatures.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return temperatures.retainAll(c);
    }

    @Override
    public void clear() {
        temperatures.clear();
    }

    @Override
    public Temperature get(int index) {
        return temperatures.get(index);
    }

    @Override
    public Temperature set(int index, Temperature element) {
        return temperatures.set(index, element);
    }

    @Override
    public void add(int index, Temperature element) {
        temperatures.add(index, element);
    }

    @Override
    public Temperature remove(int index) {
        return temperatures.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return temperatures.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return temperatures.lastIndexOf(o);
    }

    @Override
    public ListIterator<Temperature> listIterator() {
        return temperatures.listIterator();
    }

    @Override
    public ListIterator<Temperature> listIterator(int index) {
        return temperatures.listIterator(index);
    }

    @Override
    public List<Temperature> subList(int fromIndex, int toIndex) {
        return temperatures.subList(fromIndex, toIndex) ;
    }
}

