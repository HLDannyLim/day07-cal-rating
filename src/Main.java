import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        Reader r = new FileReader("googleplaystore.csv");
        BufferedReader br = new BufferedReader(r);

        List<App> apps = br.lines()
            // skip the header line
            .skip(1)
            // String -> String[]
            .map(l -> l.split(","))
            // .map(cols -> {
            // if (cols.length <= 14)
            // return cols;
            // cols[1] = "%s %s".formatted(cols[0], cols[1]);
            // String[] dest = new String[cols.length - 1];
            // System.arraycopy(cols, 1, dest, 0, dest.length);
            // return dest;
            // })
            .filter(cols -> cols.length <= 14)
            .filter(cols -> !cols[2].trim().toLowerCase().equals("nan"))
            // String[] -> App
            .map(cols -> {
                App app = new App();
                app.setName(cols[0]);
                app.setCategory(cols[1]);
                app.setRating(Float.parseFloat(cols[2]));
                return app;
            })
            .toList();

        br.close();
        r.close();

        //System.out.println(apps);

        Map<String, List<App>> groupByCategory = apps.stream()
            .collect(
                Collectors.groupingBy(app -> app.getCategory())
            );

        for (String c: groupByCategory.keySet()) {
            List<App> listOfApps = groupByCategory.get(c);
            System.out.printf("Categories: %s - %d\n", c, listOfApps.size());
        }

        for (App a: groupByCategory.get("EVENTS"))
            System.out.println(a);

    }


    }



        // HashMap<String , List<String>> bookMap = new HashMap<String , List<String>>();
        // bookMap = readfile();
        // Float ratings = 0f;
        // Float total = 0f;
        // int count = 0;

        // // System.out.println(bookMap.keySet());
        // Scanner sc = new Scanner(System.in);

        // String line = sc.nextLine();
        // bookMap.forEach((k,v) ->{
        //     if (v.get(10).contains(line)){
        //         // if (v.get(2) != "NaN"){
        //         //     // total = Float.parseFloat(v.get(2));
        //         //     // count ++;
        //         // }
        //         // System.out.println(v.get(2));
        //         // System.out.println(v);
        //         // List<String> array = v ;
        //         // bookRatingList.put(k, v);
        //     }
        // }
        // );

        // ratings = total/count ;
        // System.out.printf("The Ave Ratings of %s:  %d",line,ratings);

        // // System.out.println(bookRatingList);
        
    

    // public static HashMap<String, List<String>> readfile() throws Exception{
    //     HashMap<String , List<String>> bookMap = new HashMap<String , List<String>>();
    //     String line ;
    //     BufferedReader br = new BufferedReader(new FileReader("googleplaystore.csv"));
                
    //     List<app> apps = br.lines()
    //         .skip(1)
    //         //Input going in is string -->output Array String[]
    //         .map(l -> l.split(","))
    //         // .map(cols -> {
    //         //     if(cols.length <= 14){
    //         //         return cols;
    //         //     cols[1] = "%s %s".formatted(cols[0], cols[1]);
    //         //     String[] dest = new String[cols.length -1 ];
    //         //     System.arraycopy(cols, 1, dest, 0, dest.length);
    //         //     return dest ;
    //         //     }
    //         // })
    //         .filter(cols -> cols.length <= 14)
    //         .filter(cols -> !cols[2].trim().toLowerCase().equals("nan"))
    //         .map(cols -> {
    //             App app = new App();
    //             app.setName(cols[0]);
    //             app.setCategory([cols[1]]);
    //             app.setRating([Float.parseFloat(cols[2])]);
    //             return app;
    //         })
    //         .toList();

    //     br.close();
        
    //     System.out.println(apps);

    //     apps.stream()
    //         .collects.grouppingBy()
    //     // while((line=br.readLine()) != null){
    //     //     String[] arrList ;
    //     //     ArrayList <String> googleList = new ArrayList<String>();
    //     //     arrList = line.split(",");
        
    //     //     for(String w:arrList){
    //     //         googleList.add(w);
    //     //     }
    //     //     bookMap.put(googleList.get(0), googleList);
        
    //     // }
    //     // br.close();
    //     return bookMap;
        
    // }
    

