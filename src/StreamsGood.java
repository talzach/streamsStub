import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class StreamsGood {
    public static void main(String [ ] args) {
        Map<String, List<String>> query =  parseQuery("action=validate&key=10&key=22&key=10&key=33");
        System.out.print(query);
    }

    private static Map<String, List<String>> parseQuery(String query) {
        return Arrays.stream(query.split("&"))
                .map(StreamsGood::splitKeyValue)
                .filter(Objects::nonNull)
                .collect(groupingBy(Map.Entry::getKey,
                        mapping(Map.Entry::getValue, toList())));
    }


    private static Map.Entry<String, String> splitKeyValue(String keyValue) {
        String[] parts = keyValue.split("=", 2);

        try {
            return Map.entry(URLDecoder.decode(parts[0], "UTF-8"),
                    URLDecoder.decode(parts[1], "UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            System.out.print("Exception processing key-value pair");
            return null;
        }
    }
}
