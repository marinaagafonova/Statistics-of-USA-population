import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class task3 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = getFileFromResources("Data_USA_Cart.csv");
        List<Population> populations = ParseProductCsv(br);
        List<Population> result = Calculate.Top5Regions(populations);
        PrintResultWithPrediction(result);
    }
    private static BufferedReader getFileFromResources(String fileName) {

        InputStream in = new task3().getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader;
    }

    private  static void  PrintResultWithPrediction(List<Population> populations){
        System.out.println("Прогноз для топ-5 регионов:");
        for(Population p : populations)
            System.out.println(p.geo_name + " - " + "2017: " + (p.pop_2016+p.average));
    }


    private static List<Population> ParseProductCsv( BufferedReader br/*String filePath*/) throws IOException {
        List<Population> products = new ArrayList<Population>();
        int count = 0;
        String line ="";
        while((line = br.readLine()) != null)
        {
            if (line.isEmpty()) {
                break;
            }
            String[] splitedText = line.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + ","+ splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            int size = columnList.size();
            if (size < 7)
                continue;
            if(columnList.get(0).equals("geo_name") || columnList.get(3).equals("") || columnList.get(4).equals("") || columnList.get(5).equals("") || columnList.get(6).equals(""))
                continue;

            Population population = new Population();

            population.geo_name = columnList.get(0);
            population.geo_sumlevel = columnList.get(1);
            population.id_pop = columnList.get(2);

            population.pop_2013 = Integer.parseInt(columnList.get(3));
            population.pop_2014 = Integer.parseInt(columnList.get(4));
            population.pop_2015 = Integer.parseInt(columnList.get(5));
            population.pop_2016 = Integer.parseInt(columnList.get(6));
            population.average = 0;
            products.add(population);
        }

        return products;
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

}
