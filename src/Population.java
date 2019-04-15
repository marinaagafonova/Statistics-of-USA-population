public class Population implements Comparable<Population>{
    String geo_name;
    String geo_sumlevel;
    String id_pop;
    int pop_2013;
    int pop_2014;
    int pop_2015;
    int pop_2016;
    double average;

    @Override
    public int compareTo(Population population) {
        return Double.compare(average, population.average);
    }
}
