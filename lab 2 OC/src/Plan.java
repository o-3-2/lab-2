import java.util.ArrayList;
import java.util.Random;

public class Plan {
    public static final int TIME = 5;
    ArrayList<Process> Processes;
    ArrayList<Stream> Streams;
    private int maxPrioritet = -1;
    private int k;

    public Plan() {
        Processes = new ArrayList<Process>();
        Random length = new Random();

        Streams = new ArrayList<Stream>();
        for (int i = 0; i < length.nextInt(7) + 1; i++) {
            int prioritet = length.nextInt(100) + 1;
            if (prioritet < maxPrioritet || maxPrioritet == -1) {
                maxPrioritet = prioritet;
                k = i;
            }
            Streams.add(new Stream("Ïîòîê " + (i + 1), 8 * i + 1, prioritet));
        }
    }

    public void Start() {
        while (!Streams.isEmpty()) {
            for (int i = 0; i < Streams.size(); i++) {
                int flag = TIME;
                while (Streams.get(i).isTime() && flag > 0) {
                    print(" ïðîöåññ", i);
                    Streams.get(i).timeReduction();
                    flag--;
                    if (flag == 0 && Streams.get(i).getMaxTimeOfStream() != 0) {
                        print(" ïðèîñòîíîâëåí ", i);
                        System.out.println();
                        break;
                    }
                    if (Streams.get(i).getMaxTimeOfStream() == 0) {
                        print(" çàâåðøåí ", i);
                        System.out.println();
                        Streams.remove(i);
                        --i;
                        break;
                    }
                }
            }
        }
    }

    public void print(String str, int i) {
        System.out.println(Streams.get(i).getStreamId() + " ïðèîðèòåò : "
                + Streams.get(i).getPrioritet() + " âðåìÿ: "
                + Streams.get(i).getMaxTimeOfStream()
                + str);
    }
}
