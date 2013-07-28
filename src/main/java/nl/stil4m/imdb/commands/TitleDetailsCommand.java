package nl.stil4m.imdb.commands;

import java.io.UnsupportedEncodingException;

public class TitleDetailsCommand implements Command {

    private final String id;

    public TitleDetailsCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUrlExtension() throws UnsupportedEncodingException {
        return "/title/" + id;
    }

}
