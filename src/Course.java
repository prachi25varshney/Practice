import java.util.*;

public class Course {

    public static void main(String[] args){

        String[][] input = new String[][]{{"A","B"},{"A","C"},{"B","K"},{"C","K"},{"L","M"},{"L","N"},{"D","E"},
                {"D","J"},{"E","F"},{"E","G"},{"F","H"},{"G","I"}};
        String[] course = new String[]{"A","L","D","K"};
        List<List<String>> result = getFinalCourse(input, course);
        for(List<String> list : result){
            list.forEach(System.out::print);
            System.out.println();
        }
    }

    static Map<String, List<String>> map = new HashMap();

    private static List<List<String>> getFinalCourse(String[][] input, String[] queries) {
        for(String[] course : input){
            if(!map.containsKey(course[0]))
                map.put(course[0],new ArrayList<>());
            map.get(course[0]).add(course[1]);
        }

        List<List<String>> finalCourseList = new ArrayList<>();
        for(String query: queries){
            Set<String> finalCourse = new HashSet<>();
            getFinalCourseForQuery(query, finalCourse, query);
            finalCourseList.add(new ArrayList<>(finalCourse));
        }

        return finalCourseList;
    }

    private static void getFinalCourseForQuery(String sourceQ, Set<String> result, String currQ) {

        if(!map.containsKey(currQ) && !currQ.equals(sourceQ)){
            result.add(currQ);
        }
        List<String> courses = map.get(currQ);
        if(courses!=null){
            for (String course : courses)
                getFinalCourseForQuery(sourceQ, result, course);
        }
    }
}
