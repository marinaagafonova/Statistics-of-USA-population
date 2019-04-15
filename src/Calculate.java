import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Calculate {

    public static List<Population> Top5Regions(List<Population> populations){
        for(Population p : populations){
            p.average = ((p.pop_2014 - p.pop_2013) + (p.pop_2015 - p.pop_2014) + (p.pop_2016 - p.pop_2015))/3;
        }

        Comparator<Population> comparator = new Comparator<Population>() {
            @Override
            public int compare(Population o1, Population o2) {
                return o1.compareTo(o2);
            }
        };
        comparator = Comparator.reverseOrder();
        Collections.sort(populations, comparator);
        ArrayList<Population> result = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            result.add(populations.get(i));
        return result;
    }

}
