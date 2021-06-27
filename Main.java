import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

public class Main
{
    public interface inter
    {
        public float getHP();
    }
    public abstract static class Player
    {
        protected float HP;
        protected int playernum;
        protected char type;
        public abstract void ddd();
    }

    public static class Mafia extends Player implements inter
    {
        public Mafia(int playernum)
        {
            super();
            this.HP = 2500;
            this.playernum = playernum;
            this.type = 'M';
        }
        @Override
        public void ddd()
        {
            System.out.println(playernum);
        }
        public float getHP()
        {
            return HP;
        }
    }
    public static class Detective extends Player implements inter
    {
        public Detective(int playernum)
        {
            this.HP = 800;
            this.playernum = playernum;
            this.type = 'D';
        }
        @Override
        public void ddd()
        {
            System.out.println(HP);
        }
        public float getHP()
        {
            return HP;
        }
    }
    public static class Healer extends Player implements inter
    {
        public Healer(int playernum)
        {
            this.HP = 800;
            this.playernum = playernum;
            this.type = 'H';
        }
        @Override
        public void ddd()
        {
            System.out.println(type);
        }
        public float getHP()
        {
            return HP;
        }
    }
    public static class Commoner extends Player implements inter
    {
        public Commoner(int playernum)
        {
            this.HP = 1000;
            this.playernum = playernum;
            this.type = 'C';
        }
        @Override
        public void ddd()
        {
            System.out.println(HP);
        }
        public float getHP()
        {
            return HP;
        }
    }

    public static void main(String[] args) throws InputMismatchException
    {
        Scanner inp = new Scanner(System.in);
        Random r = new Random();
        ArrayList<Object> playing = new ArrayList<>();

// Number of Players
        System.out.println("Welcome to Mafia");
        int chk1 = 0;
        int N = 0;
        while(chk1==0)
        {
            System.out.print("Enter Number of Players: ");
            try
            {
                N = Integer.parseInt(inp.nextLine());
                if (N <= 0)
                {
                    throw new InputMismatchException("e1");
                }
                else
                {
                    chk1 = 1;
                }
            }
            catch(InputMismatchException | NumberFormatException e1)
            {
                System.out.println("Invalid Input");
                chk1 = 0;
            }
        }

// Random Roles
        int mafianum = N/5;
        int detectnum = N/5;
        int healernum = Math.max(1,N/10);
        int commonnum = N - mafianum - detectnum - healernum;
        float totalmafiahp = mafianum*2500;
        int mafiasalive = mafianum;
        int nonmafiasalive = N-mafiasalive;

        Set<Integer> listS = new LinkedHashSet<Integer>();
        while(listS.size()<N)
        {
            listS.add(r.nextInt(N)+1);
        }
        Integer listI[] = new Integer[N];
        listI = listS.toArray(listI);

        char players[] = new char[N];
        int j = 0;
        for(int i=0;i<mafianum;i++)
        {
            players[listI[j]-1] = 'M';
            j++;
        }
        for(int i=0;i<detectnum;i++)
        {
            players[listI[j]-1] = 'D';
            j++;
        }
        for(int i=0;i<healernum;i++)
        {
            players[listI[j]-1] = 'H';
            j++;
        }
        for(int i=0;i<commonnum;i++)
        {
            players[listI[j]-1] = 'C';
            j++;
        }

// Creating Data
        ArrayList<Integer> optnsformafia = new ArrayList<>();
        ArrayList<Integer> optnsfordetective = new ArrayList<>();
        ArrayList<Integer> optnsforhealer = new ArrayList<>();
        ArrayList<Integer> optnsforhealer2 = new ArrayList<>();
        ArrayList<Integer> playingInt = new ArrayList<>();

        for(int i=0;i<N;i++)
        {
            if(players[i]=='M')
            {
                optnsfordetective.add(i+1);
                optnsforhealer.add(i+1);
                optnsforhealer2.add(i+1);
                playingInt.add(i+1);
                Mafia m = new Mafia(i+1);
                playing.add(m);
            }
            if(players[i]=='D')
            {
                optnsformafia.add(i+1);
                optnsforhealer.add(i+1);
                optnsforhealer2.add(i+1);
                playingInt.add(i+1);
                Detective d = new Detective(i+1);
                playing.add(d);
            }
            if(players[i]=='H')
            {
                optnsformafia.add(i+1);
                optnsfordetective.add(i+1);
                optnsforhealer.add(i+1);
                playingInt.add(i+1);
                Healer h = new Healer(i+1);
                playing.add(h);
            }
            if(players[i]=='C')
            {
                optnsformafia.add(i+1);
                optnsfordetective.add(i+1);
                optnsforhealer.add(i+1);
                optnsforhealer2.add(i+1);
                playingInt.add(i+1);
                Commoner c = new Commoner(i+1);
                playing.add(c);
            }
        }

// Character Menu
        System.out.println("Choose a Character");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
        int chk2 = 0;
        int o1 = 0;
        while(chk2==0)
        {
            try
            {
                o1 = Integer.parseInt(inp.nextLine());
                if (o1 <= 0 || o1>5)
                {
                    throw new InputMismatchException("e2");
                }
                else
                {
                    chk2 = 1;
                }
            }
            catch(InputMismatchException | NumberFormatException e2)
            {
                System.out.println("Invalid Input");
                chk2 = 0;
            }
        }

        char oplist[] = {'M','D','H','C'};
        int playernum = 0;
        if(o1==5)
        {
            o1 = r.nextInt(4) + 1;
        }
        for(int i=0;i<N;i++)
        {
            if(players[i]==oplist[o1-1])
            {
                playernum = i+1;
                break;
            }
        }
        System.out.println("You are Player" + playernum + ".");

        if(o1==1)
        {
            System.out.print("You are a Mafia. Other Mafias are: [");
        }
        if(o1==2)
        {
            System.out.print("You are a Detective. Other Detectives are: [");
        }
        if(o1==3)
        {
            System.out.print("You are a Healer. Other Healers are: [");
        }
        if(o1==4)
        {
            System.out.print("You are a Commoner. Other Commoners are: [");
        }
        for(int i=0;i<N;i++)
        {
            if(players[i]==oplist[o1-1] && i!=playernum-1)
            {
                System.out.print(" Player" + (i+1) + " ");
            }
        }
        System.out.print("]\n");

// Game Begins
        int game = 1;
        int gamedecision = -1;
        int playersalive = N;
        int roundnum = 0;
        while(game==1)
        {
// Round Begins
            roundnum++;
            System.out.println("Round " + roundnum + ":");
            System.out.print(playing.size() + " Players are Remaining: ");
            for(int i=0;i<playing.size();i++)
            {
                if(players[playingInt.get(i)-1]=='M')
                {
                    System.out.print(" Player" + ((Mafia)playing.get(i)).playernum + " ");
                }
                if(players[playingInt.get(i)-1]=='D')
                {
                    System.out.print(" Player" + ((Detective)playing.get(i)).playernum + " ");
                }
                if(players[playingInt.get(i)-1]=='H')
                {
                    System.out.print(" Player" + ((Healer)playing.get(i)).playernum + " ");
                }
                if(players[playingInt.get(i)-1]=='C')
                {
                    System.out.print(" Player" + ((Commoner)playing.get(i)).playernum + " ");
                }
            }
            System.out.print("are alive.\n");

// Choice Part
            int mafiachoice = 0;
            int detectivechoice = 0;
            int healerchoice = 0;
            if(oplist[o1-1]=='M')
            {
                if(playingInt.contains(playernum))
                {
                    int chk3 = 0;
                    while(chk3==0)
                    {
                        try
                        {
                            System.out.print("Choose a Player to Kill: ");
                            mafiachoice = Integer.parseInt(inp.nextLine());
                            if (!playingInt.contains(mafiachoice))
                            {
                                System.out.println("Invalid input");
                            }
                            else if (!optnsformafia.contains(mafiachoice))
                            {
                                System.out.println("You cannot kill a Mafia. ");
                            }
                            else
                            {
                                chk3 = 1;
                            }
                        }
                        catch(InputMismatchException | NumberFormatException e3)
                        {
                            System.out.println("Invalid Input");
                            chk3 = 0;
                        }
                    }
                }
                else
                {
                    mafiachoice = optnsformafia.get(r.nextInt(optnsformafia.size()));
                    System.out.println("Mafia have chosen someone to kill.");
                }

                detectivechoice = optnsfordetective.get(r.nextInt(optnsfordetective.size()));
                System.out.println("Detectives have chosen someone to test.");
                healerchoice = optnsforhealer.get(r.nextInt(optnsforhealer.size()));
                System.out.println("Healers have chosen someone to heal.");
            }
            if(oplist[o1-1]=='D')
            {
                mafiachoice = optnsformafia.get(r.nextInt(optnsformafia.size()));
                System.out.println("Mafia have chosen someone to kill.");

                if(playingInt.contains(playernum))
                {
                    int chk3 = 0;
                    while(chk3==0)
                    {
                        try
                        {
                            System.out.print("Choose a Player to Test: ");
                            detectivechoice = Integer.parseInt(inp.nextLine());
                            if (!playingInt.contains(detectivechoice))
                            {
                                System.out.println("Invalid input");
                            }
                            else if (!optnsfordetective.contains(detectivechoice))
                            {
                                System.out.println("You cannot test a Detective. ");
                            }
                            else
                            {
                                chk3 = 1;
                            }
                        }
                        catch(InputMismatchException | NumberFormatException e3)
                        {
                            System.out.println("Invalid Input");
                            chk3 = 0;
                        }
                    }
                }
                else
                {
                    detectivechoice = optnsfordetective.get(r.nextInt(optnsfordetective.size()));
                    System.out.println("Detectives have chosen someone to test.");
                }

                if(players[detectivechoice-1]=='M')
                {
                    System.out.println("Player" + detectivechoice + " was a Mafia.");
                }
                else
                {
                    System.out.println("Player" + detectivechoice + " was not a Mafia.");
                }
                healerchoice = optnsforhealer.get(r.nextInt(optnsforhealer.size()));
                System.out.println("Healers have chosen someone to heal.");
            }
            if(oplist[o1-1]=='H')
            {
                mafiachoice = optnsformafia.get(r.nextInt(optnsformafia.size()));
                System.out.println("Mafia have chosen someone to kill.");
                detectivechoice = optnsfordetective.get(r.nextInt(optnsfordetective.size()));
                System.out.println("Detectives have chosen someone to test.");

                if(playingInt.contains(playernum))
                {
                    int chk3 = 0;
                    while(chk3==0)
                    {
                        try
                        {
                            System.out.print("Choose a Player to Heal: ");
                            healerchoice = Integer.parseInt(inp.nextLine());
                            if (!playingInt.contains(healerchoice))
                            {
                                System.out.println("Invalid input");
                            }
                            else
                            {
                                chk3 = 1;
                            }
                        }
                        catch(InputMismatchException | NumberFormatException e3)
                        {
                            System.out.println("Invalid Input");
                            chk3 = 0;
                        }
                    }
                }
                else
                {
                    healerchoice = optnsforhealer.get(r.nextInt(optnsforhealer.size()));
                    System.out.println("Healers have chosen someone to heal.");
                }
            }
            if(oplist[o1-1]=='C')
            {
                mafiachoice = optnsformafia.get(r.nextInt(optnsformafia.size()));
                System.out.println("Mafia have chosen someone to kill.");
                detectivechoice = optnsfordetective.get(r.nextInt(optnsfordetective.size()));
                System.out.println("Detectives have chosen someone to test.");
                healerchoice = optnsforhealer.get(r.nextInt(optnsforhealer.size()));
                System.out.println("Healers have chosen someone to heal.");
            }
            System.out.println("--End of Actions--");

// Calculation part

            // For Killing
            for(int k=0;k<playing.size();k++)
            {
                if(playing.get(k) instanceof Detective)
                {
                    Detective d1 = (Detective) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP<=totalmafiahp)
                        {
                            totalmafiahp -= d1.HP;
                            d1.HP = 0;
                        }
                        else
                        {
                            d1.HP -= totalmafiahp;
                            totalmafiahp = 0;

                            for(int z=0;z<playing.size();z++)
                            {
                                if(playing.get(k) instanceof Mafia)
                                {
                                    Mafia d2 = (Mafia) playing.get(k);
                                    d2.HP = 0;
                                }
                            }
                        }
                        break;
                    }
                }
                if(playing.get(k) instanceof Healer)
                {
                    Healer d1 = (Healer) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP<=totalmafiahp)
                        {
                            totalmafiahp -= d1.HP;
                            d1.HP = 0;
                        }
                        else
                        {
                            d1.HP -= totalmafiahp;
                            totalmafiahp = 0;

                            for(int z=0;z<playing.size();z++)
                            {
                                if(playing.get(k) instanceof Mafia)
                                {
                                    Mafia d2 = (Mafia) playing.get(k);
                                    d2.HP = 0;
                                }
                            }
                        }
                        break;
                    }
                }
                if(playing.get(k) instanceof Commoner)
                {
                    Commoner d1 = (Commoner) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP<=totalmafiahp)
                        {
                            totalmafiahp -= d1.HP;
                            d1.HP = 0;
                        }
                        else
                        {
                            d1.HP -= totalmafiahp;
                            totalmafiahp = 0;

                            for(int z=0;z<playing.size();z++)
                            {
                                if(playing.get(k) instanceof Mafia)
                                {
                                    Mafia d2 = (Mafia) playing.get(k);
                                    d2.HP = 0;
                                }
                            }
                        }
                        break;
                    }
                }
            }

            // For Healing
            for(int k=0;k<playing.size();k++)
            {
                if(playing.get(k) instanceof Mafia)
                {
                    Mafia d1 = (Mafia) playing.get(k);
                    if(d1.playernum==healerchoice)
                    {
                        d1.HP += 500;
                        totalmafiahp += 500;
                        break;
                    }
                }
                if(playing.get(k) instanceof Detective)
                {
                    Detective d1 = (Detective) playing.get(k);
                    if(d1.playernum==healerchoice)
                    {
                        d1.HP += 500;
                        break;
                    }
                }
                if(playing.get(k) instanceof Healer)
                {
                    Healer d1 = (Healer) playing.get(k);
                    if(d1.playernum==healerchoice)
                    {
                        d1.HP += 500;
                        break;
                    }
                }
                if(playing.get(k) instanceof Commoner)
                {
                    Commoner d1 = (Commoner) playing.get(k);
                    if(d1.playernum==healerchoice)
                    {
                        d1.HP += 500;
                        break;
                    }
                }
            }

// Anyone Dead + Killed Updation
            for(int k=0;k<playing.size();k++)
            {
                if(playing.get(k) instanceof Detective)
                {
                    Detective d1 = (Detective) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP>0)
                        {
                            System.out.println("No one Died.");
                        }
                        else
                        {
                            System.out.println("Player" + mafiachoice + " has Died.");
                            nonmafiasalive--;
                            playing.remove(k);
                            playingInt.remove(k);
                            for(int y=0;y<optnsfordetective.size();y++)
                            {
                                if(optnsfordetective.get(y)==mafiachoice)
                                {
                                    optnsfordetective.remove(y);
                                }
                            }
                            for(int y=0;y<optnsformafia.size();y++)
                            {
                                if(optnsformafia.get(y)==mafiachoice)
                                {
                                    optnsformafia.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer.size();y++)
                            {
                                if(optnsforhealer.get(y)==mafiachoice)
                                {
                                    optnsforhealer.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer2.size();y++)
                            {
                                if(optnsforhealer2.get(y)==mafiachoice)
                                {
                                    optnsforhealer2.remove(y);
                                }
                            }
                        }
                        break;
                    }
                }
                if(playing.get(k) instanceof Healer)
                {
                    Healer d1 = (Healer) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP>0)
                        {
                            System.out.println("No one Died.");
                        }
                        else
                        {
                            System.out.println("Player" + mafiachoice + " has Died.");
                            nonmafiasalive--;
                            playing.remove(k);
                            playingInt.remove(k);
                            for(int y=0;y<optnsfordetective.size();y++)
                            {
                                if(optnsfordetective.get(y)==mafiachoice)
                                {
                                    optnsfordetective.remove(y);
                                }
                            }
                            for(int y=0;y<optnsformafia.size();y++)
                            {
                                if(optnsformafia.get(y)==mafiachoice)
                                {
                                    optnsformafia.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer.size();y++)
                            {
                                if(optnsforhealer.get(y)==mafiachoice)
                                {
                                    optnsforhealer.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer2.size();y++)
                            {
                                if(optnsforhealer2.get(y)==mafiachoice)
                                {
                                    optnsforhealer2.remove(y);
                                }
                            }
                        }
                        break;
                    }
                }
                if(playing.get(k) instanceof Commoner)
                {
                    Commoner d1 = (Commoner) playing.get(k);
                    if(d1.playernum==mafiachoice)
                    {
                        if(d1.HP>0)
                        {
                            System.out.println("No one Died.");
                        }
                        else
                        {
                            System.out.println("Player" + mafiachoice + " has Died.");
                            nonmafiasalive--;
                            playing.remove(k);
                            playingInt.remove(k);
                            for(int y=0;y<optnsfordetective.size();y++)
                            {
                                if(optnsfordetective.get(y)==mafiachoice)
                                {
                                    optnsfordetective.remove(y);
                                }
                            }
                            for(int y=0;y<optnsformafia.size();y++)
                            {
                                if(optnsformafia.get(y)==mafiachoice)
                                {
                                    optnsformafia.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer.size();y++)
                            {
                                if(optnsforhealer.get(y)==mafiachoice)
                                {
                                    optnsforhealer.remove(y);
                                }
                            }
                            for(int y=0;y<optnsforhealer2.size();y++)
                            {
                                if(optnsforhealer2.get(y)==mafiachoice)
                                {
                                    optnsforhealer2.remove(y);
                                }
                            }
                        }
                        break;
                    }
                }
            }

// Voting
            int votedplayer = 0;
            int uservotedplayer = 0;
            if(players[detectivechoice-1]=='M')
            {
                votedplayer = detectivechoice;
            }
            else
            {
                if(playingInt.contains(playernum))
                {
                    int chk4 = 0;
                    while(chk4==0)
                    {
                        try
                        {
                            System.out.print("Select a Player to Vote out: ");
                            uservotedplayer = Integer.parseInt(inp.nextLine());
                            if (!playingInt.contains(uservotedplayer))
                            {
                                System.out.println("Invalid input");
                            }
                            else
                            {
                                chk4 = 1;
                            }
                        }
                        catch(InputMismatchException | NumberFormatException e4)
                        {
                            System.out.println("Invalid Input");
                            chk4 = 0;
                        }
                    }
                }

                int voting[] = new int[playingInt.size()];
                for(int y=0;y<playingInt.size();y++)
                {
                    if(playernum==playingInt.get(y))
                    {
                        voting[y] = uservotedplayer;
                    }
                    else if(players[playingInt.get(y)-1]=='M')
                    {
                        voting[y] = optnsformafia.get(r.nextInt(optnsformafia.size()));
                    }
                    else if(players[playingInt.get(y)-1]=='D')
                    {
                        voting[y] = optnsfordetective.get(r.nextInt(optnsfordetective.size()));
                    }
                    else if(players[playingInt.get(y)-1]=='H')
                    {
                        voting[y] = optnsforhealer2.get(r.nextInt(optnsforhealer2.size()));
                    }
                    else if(players[playingInt.get(y)-1]=='C')
                    {
                        int ok = -1;
                        do
                        {
                            ok = playingInt.get(r.nextInt(playingInt.size()));

                        }while(ok==playingInt.get(y));
                        voting[y] = ok;
                    }
                }
                Arrays.sort(voting);
                int fin = voting[0];
                int curr = 1;
                int max = 1;
                for(int i=1;i<voting.length;i++)
                {
                    if(voting[i]==voting[i-1])
                    {
                        curr++;
                    }
                    else
                    {
                        if(curr>max)
                        {
                            max = curr;
                            fin = voting[i-1];
                        }
                        curr = 1;
                    }
                }
                if(curr>max)
                {
                    max = curr;
                    fin = voting[voting.length-1];
                }
                votedplayer = fin;
            }
            System.out.println("Player" + votedplayer + " has been Voted out.");
            if(players[votedplayer-1]=='M')
            {
                mafiasalive--;
                totalmafiahp -= ((Mafia)playing.get(playingInt.indexOf(votedplayer))).HP;
            }
            else
            {
                nonmafiasalive--;
            }
            int k = playingInt.indexOf(votedplayer);
            playing.remove(k);
            playingInt.remove(k);
            for(int y=0;y<optnsfordetective.size();y++)
            {
                if(optnsfordetective.get(y)==votedplayer)
                {
                    optnsfordetective.remove(y);
                }
            }
            for(int y=0;y<optnsformafia.size();y++)
            {
                if(optnsformafia.get(y)==votedplayer)
                {
                    optnsformafia.remove(y);
                }
            }
            for(int y=0;y<optnsforhealer.size();y++)
            {
                if(optnsforhealer.get(y)==votedplayer)
                {
                    optnsforhealer.remove(y);
                }
            }
            for(int y=0;y<optnsforhealer2.size();y++)
            {
                if(optnsforhealer2.get(y)==votedplayer)
                {
                    optnsforhealer2.remove(y);
                }
            }

            System.out.println("--End of Round " + roundnum + "--\n");

// Game Decision Part
            if(mafiasalive==0)
            {
                game = 0;
                gamedecision = 0;
            }
            else if(mafiasalive>=nonmafiasalive)
            {
                game = 0;
                gamedecision = 1;
            }
        }

// When Game Over
        System.out.println("Game Over.");
        if(gamedecision==1)
        {
            System.out.println("The Mafias have won.\n");
        }
        if(gamedecision==0)
        {
            System.out.println("The Mafias have lost.\n");
        }

        for(int i=0;i<N;i++)
        {
            if(players[i]=='M')
            {
                System.out.print(" Player" + (i+1) + " ");
                if(playernum==i+1)
                {
                    System.out.print("[User] ");
                }
            }
        }
        if(mafianum==0)
        {
            System.out.print("There were no Mafias.\n");
        }
        else if(mafianum>1)
        {
            System.out.print("were Mafias.\n");
        }
        else
        {
            System.out.print("was Mafia.\n");
        }
        for(int i=0;i<N;i++)
        {
            if(players[i]=='D')
            {
                System.out.print(" Player" + (i+1) + " ");
                if(playernum==i+1)
                {
                    System.out.print("[User] ");
                }
            }
        }
        if(detectnum==0)
        {
            System.out.print("There were no Detectives.\n");
        }
        else if(detectnum>1)
        {
            System.out.print("were Detectives.\n");
        }
        else
        {
            System.out.print("was Detective.\n");
        }
        for(int i=0;i<N;i++)
        {
            if(players[i]=='H')
            {
                System.out.print(" Player" + (i+1) + " ");
                if(playernum==i+1)
                {
                    System.out.print("[User] ");
                }
            }
        }
        if(healernum==0)
        {
            System.out.print("There were no Healers.\n");
        }
        else if(healernum>1)
        {
            System.out.print("were Healers.\n");
        }
        else
        {
            System.out.print("was Healer.\n");
        }
        for(int i=0;i<N;i++)
        {
            if(players[i]=='C')
            {
                System.out.print(" Player" + (i+1) + " ");
                if(playernum==i+1)
                {
                    System.out.print("[User] ");
                }
            }
        }
        if(commonnum==0)
        {
            System.out.print("There were no Commoners.\n");
        }
        else if(commonnum>1)
        {
            System.out.print("were Commoners.\n");
        }
        else
        {
            System.out.print("was Commoner.\n");
        }
    }
}