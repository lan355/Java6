package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Controller
{
    public Button read;
    public Button sort;
    public Button remove;
    JSONArray jasonArray;
    Jokes jokes;
    ObservableList<Joke> Jokes;

    @FXML
    private TableView<Joke> table;

    @FXML
    private TableColumn<Joke, Long> id;

    @FXML
    private TableColumn<Joke, String> type;

    @FXML
    private TableColumn<Joke, String> setup;

    @FXML
    private TableColumn<Joke, String> punchline;

    public void read()
    {
        Jason jason = new Jason();
        Jason.url = "https://official-joke-api.appspot.com/random_ten";
        jason.start();

        System.out.println("Waiting for data...");
        String jsonString = jason.jason;
        System.out.println(jsonString);

        // Считываем json
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println();

        jasonArray = (JSONArray) obj;
        System.out.println(jasonArray.toJSONString());
        System.out.println();
    }

    public void display()
    {
        jokes = new Jokes();

        for (Object jsonObject : jasonArray)
        {
            JSONObject object = (JSONObject) jsonObject;
            Joke joke = new Joke((Long)object.get("id"), (String) object.get("type"),(String) object.get("setup"),(String) object.get("punchline"));
            jokes.add(joke);
        }

        System.out.println("Imported data after parsing:\n" + jokes);
        WriteArray();
    }
    public void WriteArray()
    {
        Jokes = FXCollections.observableArrayList(jokes.getJokes());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        setup.setCellValueFactory(new PropertyValueFactory<>("setup"));
        punchline.setCellValueFactory(new PropertyValueFactory<>("punchline"));

        table.setItems(Jokes);
    }
    public void sort() {
        jokes.getJokes().sort(Joke.byIdAsc);
        System.out.println("After sorting by ID ascending:\n" + jokes);
        WriteArray();
    }

    public void remove() {
        jokes.getJokes().clear();
        Jokes.clear();
        table.refresh();
    }

}
