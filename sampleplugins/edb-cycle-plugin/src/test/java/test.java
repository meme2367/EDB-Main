import org.edb.cycle.CycleMode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class test {

    @Test
    public void testEnum(){
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();

        arrayList1.add(3);
        arrayList1.add(5);

        arrayList2.add(3);

        arrayList1.retainAll(arrayList2);
        System.out.println(arrayList1.toString());
    }
}
