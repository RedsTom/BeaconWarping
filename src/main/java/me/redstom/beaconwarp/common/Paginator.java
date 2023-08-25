package me.redstom.beaconwarp.common;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class Paginator <T> {

    private final List<T> list;
    private final int     pageSize;

    public List<T> page(int index) {
        return list.subList(index * pageSize, Math.min((index + 1) * pageSize, list.size()));
    }

    public int totalPageCount() {
        return ((list.size() - (list.size() % pageSize)) / pageSize) + 1;
    }

    public <U> List<U> generatePages(Supplier<U> accumulatorSupplier, BiConsumer<U, T> filler) {
        List<U> accumulators = new ArrayList<>();
        for (int i = 0 ; i < totalPageCount() ; i++) {
            U       accumulator = accumulatorSupplier.get();
            List<T> page        = page(i);
            page.forEach(element -> filler.accept(accumulator, element));
            accumulators.add(accumulator);
        }

        return accumulators;
    }

    public int offset(int page, int offset) {
        return Math.max(0, Math.min(totalPageCount() - 1, page + offset));
    }
}
