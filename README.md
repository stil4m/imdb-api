Travis says: <img src="https://api.travis-ci.org/stil4m/imdb-api.png" />

# IMDb API

This is a Java library which can be used to extract little pieces of information of the IMDb. The library uses screen scraping as technique to obtain this data.

## Usage
You have to create a instance of the `IMDB` type. For standard usage, you should do this by using the `IMDBFactory`. The sample code shows this:

```
InputStream inputStream = getClass().getResourceAsStream("/nl/stil4m/imdb/parsing.properties");
Properties properties = new Properties();
properties.load(inputStream);
IMDBFactory factory = new IMDBFactory();
IMDB imdb = factory.createInstance(properties);
```

The reason you have to provide a `Properties` object to the factory is to allow [external configuration](http://www.kirkk.com/modularity/2009/12/external-configuration/). Because the library uses screen scraping, the library is highly dependent on the HTML layout of the IMDb. The properties file that is loaded from the classpath contains a list of CSS selectors mapped to keys that are used in the code. If the library breaks, you are able to fix the issue by fixing the CSS selector in the `Properties` object before instantiating the the `IMDB` instance. When you do so, please report a [bug](https://github.com/stil4m/imdb-api/issues).


## Advanced Configuration

TODO


## Maven repository
The module is available on my own Maven [repository](https://github.com/stil4m/maven-repository) (hosted on GitHub).

