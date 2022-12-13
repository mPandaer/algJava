package space.pandaer.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/*
加强堆：在对象内部属性发生变化的时候也能动态调整成一个堆，而且效率很高。
核心思路：建立反向索引表，对于普通堆而言 只能从 index -> instance,
而加强堆 既能 instance -> index 也能 index -> instance
于是我们就能得到他多了一个操作 -- 更新任何位置的值，并且能维持堆
下面我们来实现一个加强堆
 */
public class GreaterHeap<T> {
    private ArrayList<T> heap = new ArrayList<>(); //堆的底层实现--动态数组
    private HashMap<T, Integer> map = new HashMap<>(); //反向索引表
    private Comparator<T> comparator;
    int heapSize = 0;

    public GreaterHeap(Comparator<T> comp) {
        this.comparator = comp;
    }

    public void push(T data) {
        heap.add(heapSize, data);
        map.put(data,heapSize);
        heapSize++;
        //调整堆
        heapInsert(heapSize - 1);
    }

    public T pop() {
        if (heapSize <= 0) {
            throw new IllegalArgumentException("加强堆中没有数");
        }
        T tmp = heap.get(0);
        swap(0, heapSize - 1);
        map.remove(tmp);
        heapSize--;
        //调整堆
        heapify(0);
        return tmp;
    }

    public void remove(T value) {
        Integer index = map.get(value);
        if (index == null) {
            return;
        }
        swap(index, --heapSize);
        map.remove(value);
        resign(index);

    }

    public void resign(int index) {
        heapInsert(index);
        heapify(index);
    }

    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //找到较大的孩子
            int maxChildIndex = left + 1 < heapSize &&
                    comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;

            int maxIndex = comparator.compare(heap.get(maxChildIndex), heap.get(index)) < 0 ? maxChildIndex : index;
            if (maxIndex == index) {
                break;
            }
            swap(index, maxIndex);
            index = maxIndex;
            left = index * 2 + 1;

        }
    }


    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            //满足条件 上浮
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    public void swap(int i, int j) {
        //更新索引表
        map.put(heap.get(i), j);
        map.put(heap.get(j), i);
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);

    }


    static class Inner<T> {
        T value;

        public Inner(T value) {
            this.value = value;
        }
    }


    public static void selectRandom(GreaterHeap<Integer> heap, int maxNum) {
        int select = (int) (Math.random() * 3); //[0,3)
        if (select == 0) {
            heap.push(randomNum(maxNum));
        } else if (select == 1) {
            heap.pop();
        } else {
            heap.remove(randomNum(maxNum));
        }

    }

    public static int randomNum(int maxNum) {
        return (int) (Math.random() * maxNum) + 1;
    }

    public static void main(String[] args) {
        HashMap<Integer,Integer> map = new HashMap<>();

        //建立大跟堆
        GreaterHeap<Integer> heap = new GreaterHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int testTime = 1000;
        int maxNum = 100;
        int tmp = 0;
//        heap.push(1);
//        heap.push(2);
//        heap.pop();

        for (int i = 0; i < testTime; i++) {
            try {
                selectRandom(heap, maxNum);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (heap.heapSize > 0) {
            int num = heap.pop();
            if (tmp == 0) tmp = num;
            else {
                if (num > tmp) {
                    System.out.println("失败了");
                    return;
                }
            }
        }
        System.out.println("成功了");

    }


}
