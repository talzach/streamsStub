import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ceated by Tzach on 11/11/2017.
 */
public class StreamsBad {

    public static void main(String [ ] args) throws UnsupportedEncodingException {
        parseQuery("action=validate&key=10&key=22&key=10&key=33", new HashMap<String, String>());
    }

    private static void parseQuery(String query, Map parameters) throws UnsupportedEncodingException {
        if (query != null) {
            String pairs[] = query.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;

                if (param.length > 0)
                    key = URLDecoder.decode(param[0], "UTF-8");

                if (param.length > 1)
                    value = URLDecoder.decode(param[1], "UTF-8");

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);

                    if (obj instanceof List) {
                        List values = (List) obj;
                        values.add(value);
                    } else if (obj instanceof String) {
                        List values = new ArrayList();
                        values.add(obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else
                    parameters.put(key, value);
            }

            System.out.print(parameters);
        }
    }
}

