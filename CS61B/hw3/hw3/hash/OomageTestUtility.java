package hw3.hash;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();

        ArrayList<ArrayList<Oomage>> buckets = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            buckets.add(new ArrayList<Oomage>());
        }

        for (Oomage oom : oomages) {
            int index = (oom.hashCode() & 0x7FFFFFFF) % M;
            ArrayList<Oomage> bucket = buckets.get(index);
            bucket.add(oom);
            buckets.set(index, bucket);
        }

        for (ArrayList bucket : buckets) {
            if (bucket.size() < N / 50 || bucket.size() > N / 2.5) {
                return false;
            }
        }

        return true;
    }
}
