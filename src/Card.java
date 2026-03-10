public class Card {
  public String color;
  boolean selected;
  final String[] SUITS = {"CLUB","SPADE","HEART","DIAMOND","JOKER"};
  int value=0;
  String suit;


  public Card(int val, String s){
    value = val;
    suit = s;
    switch (s){
      case "SPADE":
        color = Style.BLACK;
        break;
      case "CLUB":
        color = Style.GREEN;
        break;
      case "DIAMOND":
        color = Style.BLUE;
        break;
      case "HEART":
        color = Style.RED;
        break;
      default:
        break;
    }
    selected = false;
  }

  public void setValue(int val){
    value = val;
  }

  public int getValue(){
    return value;
  }

  public void setSuit(String suit){
    this.suit = suit;
    switch (suit){
      case "SPADE":
        color = Style.BLACK;
        break;
      case "CLUB":
        color = Style.GREEN;
        break;
      case "DIAMOND":
        color = Style.BLUE;
        break;
      case "HEART":
        color = Style.RED;
        break;
      default:
        break;
    }
  }

  public String getSuit(){
    return suit;
  }

  public String getColor(){
    return color;
  }


  public boolean validate(){
    if(value <= 1 || suit == null){
      return false;
    }
    if(value > 14){
      return false;
    }

    boolean validSuit = false;
    int i = 0;
    while (i< SUITS.length && !validSuit){
      if(suit.equalsIgnoreCase(SUITS[i])){
        validSuit = true;
      }
      i++;
    }
    if(!validSuit){
      return false;
    }
    return true;
  }

  public String suitString(){
    switch (suit){
      case "CLUB":
        return(color+"♣"+Style.RESET);
      case "SPADE":
        return(color+"♠"+Style.RESET);
      case "HEART":
        return(color+"❤"+Style.RESET);
      case "DIAMOND":
        return(color+"♦"+Style.RESET);
      case "JOKER":
      default:
        return("ERROR");
    }
  }

  public String numString(){
    if(value<=10 && value>1){
      return String.valueOf(value);
    }
    switch (value){
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      case 14:
        return "A";
      default:
        return "ERROR";
    }
  }

  public void select(){
    selected = true;
  }

  public void deselect(){
    selected = false;
  }

  @Override
  public String toString(){
    return " -----------\n" +
           "| "+numString()+"         |\n" +
           "|           |\n" +
           "|     "+suitString()+"     |\n" +
           "|           |\n" +
           "|         "+numString()+" |\n" +
           " -----------";
  }




}
