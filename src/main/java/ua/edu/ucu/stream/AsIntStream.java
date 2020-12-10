package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Collections;

public class AsIntStream implements IntStream {
    private final ArrayList<Integer> list;

    private AsIntStream(ArrayList<Integer> iterator) {
        this.list = iterator;
    }


    public static IntStream of(int... values) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int val : values) {
            integers.add(val);
        }
        return new AsIntStream(integers);
    }

    @Override
    public Double average() {
        isEmpty();
        int sum = 0;
        for (int val : this.list) {
            sum += val;
        }
        return (double) sum / this.list.size();
    }

    @Override
    public Integer max() {
        isEmpty();
        return Collections.max(this.list);
    }

    @Override
    public Integer min() {
        isEmpty();
        return Collections.min(this.list);
    }

    @Override
    public long count() {
        return this.list.size();
    }

    @Override
    public Integer sum() {
        return (int) (average() * this.list.size());
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int val : this.list) {
            if (predicate.test(val)) {
                res.add(val);
            }
        }
        return new AsIntStream(res);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int val : this.list) {
            action.accept(val);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int val : this.list) {
            int newval = mapper.apply(val);
            res.add(newval);
        }
        return new AsIntStream(res);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<AsIntStream> res = new ArrayList<>();
        for (int val : this.list) {
            IntStream stream = func.applyAsIntStream(val);
            res.add((AsIntStream) stream);
        }
        return merging(res);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int val : this.list) {
            identity = op.apply(identity, val);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] res = new int[this.list.size()];
        for (int i = 0; i < this.list.size(); i++) {
            res[i] = this.list.get(i);
        }
        return res;
    }

    private void isEmpty() {
        if (this.list.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public AsIntStream merging(ArrayList<AsIntStream> streams) {
        ArrayList<Integer> merged = new ArrayList<>();
        for (AsIntStream stream : streams) {
            merged.addAll(stream.list);
        }
        return new AsIntStream(merged);
    }

}
