import java.util.*;

public class Scoundrel {
  private int life;
  private Card[] dungeon = new Card[4];
  private Queue<Card> deck = new LinkedList<>();
  private CardHandler handler;
  private Random rand = new Random();
  private Scanner input;
  private boolean run;
  private boolean lastSkiped;
  private int weapon;
  private int weapDamage;
  private boolean usedHealth;
  private int removed;

  public Scoundrel(){
    life = 20;
    handler = new CardHandler();
    input = new Scanner(System.in);
    lastSkiped = false;
    weapon = 0;
    weapDamage = 0;
    usedHealth = false;
    run();
  }

  //GAME
  private void run(){
    initialize();
    setDungeon();
    run = true;
    boolean valid = false;
    int action;

    printDungeon();
    while(run){
      if(dungeon[1]==null && !deck.isEmpty()){
        setDungeon();
        printDungeon();
      }
      else if(deck.isEmpty()){
        if(dungeon[0]==null) {
          run = false;
          System.out.println("CONGRATULATION! YOU HAVE ESCAPED THE DUNGEONS.");
        }
      }
      action = getCardInput();
      while(!valid) {
        if(action<=6 && action>=1){
          valid = true;
        }
        else{
          System.out.println("INVALID");
          action = getCardInput();
        }
      }
      valid = false;
      handleInput(action);
      if (life<=0){
        run = false;
      }
    }
  }

  private void initialize(){
    for(int i = 2; i <=14; i++){
      deck.add(new Card(i,"SPADE"));
      deck.add(new Card(i,"CLUB"));
    }
    for(int i = 2; i <=10; i++){
      deck.add(new Card(i,"DIAMOND"));
      deck.add(new Card(i,"HEART"));
    }
    for(int i = 0; i < rand.nextInt(12)+7; i++){
      shuffleDeck();
    }

  }

  //DUNGEON
  private void setDungeon(){
    lastSkiped = false;
    removed = 0;
    usedHealth=false;
    int i = 0;
    while(!deck.isEmpty() && i<4){
      if(dungeon[i]==null) {
        dungeon[i] = deck.remove();
      }
      i++;
    }
    deselectAll();
  }

  private void printDungeon(){
    System.out.println(handler.concat(dungeon));
    System.out.print("\nLIFE: " +Style.RED+life+Style.RESET +"\nWEAPON: "+
        Style.BLUE+weapon+Style.RESET);
    if(weapDamage!=0){
      System.out.println(Style.BLUE+" ("+weapDamage+")"+Style.RESET);
    }
    else{
      System.out.println();
    }
  }

  private void skipDungeon(){
    boolean valid = true;
    int j = 0;
    while(valid && j < 4){
      if(dungeon[j] == null){
        System.out.println("CANNOT SKIP. YOU ARE IN TOO DEEP!");
        return;
      }
      j++;
    }


    if(lastSkiped==false) {
      deselectAll();
      lastSkiped = true;
      LinkedList<Card> list = new LinkedList<>();
      for (int i = 0; i < dungeon.length; i++) {
        if (dungeon[i] != null) {
          list.add(dungeon[i]);
          dungeon[i] = null;
        }
      }
      Collections.shuffle(list);
      while (!list.isEmpty()) {
        deck.add(list.remove());
      }
      setDungeon();
    }
    else{
      System.out.println("LAST TURN WAS SKIPPED. YOU MUST COMPLETE THIS ROOM!");
    }
    printDungeon();
  }

  //USER INPUT
  private int getCardInput(){
    String in = input.next();
    int num;
    if(in.equalsIgnoreCase("skip")){
      return 5;
    }
    if(in.equalsIgnoreCase("quit")){
      return 6;
    }
    try{
      num = Integer.parseInt(in);
    } catch(Exception e){
      return -1;
    }
    if(num>4-removed || num<1){
      return -1;
    }
    return num;
  }

  private void handleInput(int action){
    if(action == 5){
      skipDungeon();
    }
    else if(action == 6){
      run = false;
    }
    else{
      //printDungeon();
      Card card = selectCard(action);
      if(card!=null){
        playCard(card);
      }
    }
  }

  private void shiftDown(){
    for(int i = 0; i < dungeon.length - 1; i++){
      int j = i+1;
      while(dungeon[i]==null && j < 4){
        dungeon[i]=dungeon[j];
        dungeon[j]=null;
        j++;
      }
    }
  }

  //DECK HANDLING
  private void shuffleDeck(){
    LinkedList<Card> list = new LinkedList<>(deck);
    Collections.shuffle(list);
    deck = new LinkedList<>(list);
  }

  private Card selectCard(int i){
    Card card = dungeon[i-1];
    if(card!=null){
      deselectAll();
      card.select();
    }
    else{
      return null;
    }
    printDungeon();
    System.out.println("PLAY THE "+card.numString() + " OF "+card.suitString()+"S? " +
        "(y/n)");
    if(input.next().substring(0,1).equalsIgnoreCase("y")){
      dungeon[i-1] = null;
      deselectAll();
      shiftDown();
    }
    else{
      deselectAll();
      printDungeon();
      return null;
    }
    //printDungeon();
    return card;
  }

  private void deselectAll(){
    for(int i = 0; i < dungeon.length; i++){
      if(dungeon[i]!=null){
        dungeon[i].deselect();
      }
    }
  }


  //GAME MECHANICS
  private void playCard(Card card){
    switch (card.suit){
      case "SPADE":
      case "CLUB":
        fight(card);
        break;
      case "HEART":
        heal(card);
        break;
      case "DIAMOND":
        equip(card);
        break;
    }
    removed++;
    printDungeon();
  }

  private void fight(Card card){
    if(weapDamage==0){
      life -= Math.max(0,card.value-weapon);
      if(weapon!=0) {
        weapDamage = card.value;
      }
      return;
    }

    int monster = card.value;
    if(monster>weapDamage){
      System.out.println("YOUR WEAPON IS TOO DAMAGED. YOU CANNOT FIGHT THIS BATTLE.");
      System.out.println("THROW OUT YOUR WEAPON? (y/n)");
      if(input.next().substring(0,1).equalsIgnoreCase("y")){
        equip(new Card(0,"DIAMOND"));
      }
      dungeon[3] = card;
      removed--;
      deselectAll();
      shiftDown();
      return;
    }

    weapDamage=monster;
    life -= Math.max(0,monster-weapon);


  }

  private void heal(Card card){
    if(!usedHealth){
      life = Math.min(20,life+card.value);
    }
    usedHealth = true;
  }

  private void equip(Card card){
      weapon = card.value;
      weapDamage = 0;
  }
}
