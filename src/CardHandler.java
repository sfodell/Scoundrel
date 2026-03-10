public class CardHandler {

  public String concat(Card[] cards) {
    String color;
    String builder = "";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        builder += color + " ----------- "+Style.RESET;
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        if(cards[j].value==10){
          builder +=
              color + "| " + Style.RESET + cards[j].numString() + color + "        |" + Style.RESET;
        }
        else {
          builder +=
              color + "| " + Style.RESET + cards[j].numString() + color + "         |" + Style.RESET;
        }
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        builder += color + "|           |"+Style.RESET;
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        builder += color + "|     "+Style.RESET + cards[j].suitString() + color + "     |"+Style.RESET;
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        builder += color + "|           |"+Style.RESET;
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        if(cards[j].value==10){
          builder +=
              color + "|        " + Style.RESET + cards[j].numString() + color + " |" + Style.RESET;
        }
        else {
          builder +=
              color + "|         " + Style.RESET + cards[j].numString() + color + " |" + Style.RESET;
        }
      }
    }
    builder += " \n";
    for (int j = 0; j < cards.length; j++) {
      if (cards[j] != null) {
        if(cards[j].selected){
          color = Style.YELLOW;
        }
        else{
          color = Style.WHITE;
        }
        builder += color + " ----------- "+Style.RESET;
      }
    }
    return builder;
  }
}
