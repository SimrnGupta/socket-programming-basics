

public class KnockKnockProtocol {

    private static final int WAITING = 0;
    private static final int SENTKK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;
    String[] clues = {"Dexter", "Atch", "Who", "Mustache", "Nana"};
    String[] answers = {"Dexter the halls with boughs of holly",
                        "Bless you!",
                        "Is this an echo?",
                        "Mustache you a question but will shave it for later",
                        "Nana your business"};

    String processInput(String userInput) {
        String outputLine = "";
        if(state == WAITING) {
            outputLine = "Knock Knock";
            state = SENTKK;

        }
        else if (state == SENTKK) {
            if (userInput.equalsIgnoreCase("Who's there")) {
                outputLine = clues[currentJoke];
                state = SENTCLUE;
            }
            else {
                outputLine = "Idiot, reply with \"Who's there \" Try Again. Knock Knock";
                state = SENTKK;
            }

        }
        else if (state == SENTCLUE) {
            if (userInput.equalsIgnoreCase(clues[currentJoke] + " who")){
                outputLine = answers[currentJoke] + "  Wanna hear another one? (y/n)";
                state = ANOTHER;

            }
            else {
                outputLine = "You are supposed to say " + clues[currentJoke] + " who? Try again.";
                state = SENTCLUE;
            }

        }
        else if (state == ANOTHER) {
            if (userInput.equalsIgnoreCase("y")){
                outputLine = "Knock Knock";

                if(currentJoke == (NUMJOKES - 1))
                    {
                        currentJoke = 0;
                        state = SENTKK;
                }
                else 
                    {
                        currentJoke++;
                        state = SENTKK;
                }
                
            }
            else {
                outputLine = "Bye";
            }
        }




        return outputLine;
    }
}