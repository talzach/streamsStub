import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class StreamsUgly {
    public static void main(String [ ] args) {
        System.out.print(parseQuery("action=validate&key=10&key=22&key=10&key=33"));
    }

    private static Map<String, List<String>> parseQuery(String query) {
        return (query == null) ? null :
                Arrays.stream(query.split("&"))
                .collect(groupingBy(s -> (s.split("="))[0],
                                    mapping(s -> (s.split("="))[1], toList())));
    }
}
