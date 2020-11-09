package sample;

import java.io.Serializable;
import java.util.ArrayList;


public class Jokes implements Serializable {
    private ArrayList<Joke> jokes;


    public Jokes(){
        jokes = new ArrayList<>();
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public void add(Joke joke) {
        this.jokes.add(joke);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Joke j : jokes) {
            result.append(j).append(System.lineSeparator());
        }
        return result.toString();
    }

}
