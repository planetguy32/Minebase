package me.planetguy.minebase;

import java.util.ArrayList;
import java.util.HashMap;

public class MBGameManager {

	private HashMap<String, ArrayList<TileEntityMinebase>> networks=new HashMap<String, ArrayList<TileEntityMinebase>>();

	public void onUpdateNetwork(TileEntityMinebase te, String oldNet, String newNet){
		if(networks.containsKey(oldNet)){
			networks.get(oldNet).remove(te);
			if(networks.get(oldNet).size()==0)//if nothing in the net
				networks.remove(oldNet);      //delete that net
		}
		if(networks.containsKey(newNet)){
			networks.get(newNet).add(te);
		}else{
			ArrayList<TileEntityMinebase> bases=new ArrayList<TileEntityMinebase>();
			bases.add(te);
			networks.put(newNet, bases);
		}
	}

}
