package nl.stil4m.imdb.commands;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchTitleCommand implements Command {

    private final String searchName;

    public SearchTitleCommand(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public String getUrlExtension() throws UnsupportedEncodingException {
        return "/find?q="+ URLEncoder.encode(searchName, "UTF-8")+"&s=tt";
    }

}