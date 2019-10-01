import java.util.List;
import java.util.ArrayList;

public class FinishLine {
	
	private List<Horse> horseRanking;

	public FinishLine(){
		horseRanking = new ArrayList<Horse>(100);
	}

	public synchronized void arrive(Horse h) {
		horseRanking.add(h);
		System.out.println(h.getName()+"\t HAS ARRIVED AND SHOUTED WARCRY \t"+h.getWarcry()+"\n");
	}

	public void showRankings(){
		int pos;
		System.out.println("Ranking");
		for(int i=0; i<horseRanking.size(); i++) {
			pos = i + 1;
			System.out.println(pos + ".) " + horseRanking.get(i).getName() + "\tWARCRY: " +horseRanking.get(i).getWarcry());
		}
	}
}
