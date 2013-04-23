package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import observer.DeltaUpdater;
import observer.Observer;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Spawner implements Observer{
	public HashMap<BigDecimal,LevelSet[]> LevelMap;
	protected double time = 0;
	protected DeltaUpdater deltaUpdater;
	public Spawner(String LevelName, DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
		this.deltaUpdater.register(this);
		Gson g = new Gson();
//		TypeToken<List<String>> list = new TypeToken<List<String>>() {};
		try {
			LevelMap = g.fromJson(new FileReader("json/level/"+LevelName+".json"), (new TypeToken<HashMap<BigDecimal,LevelSet[]>>(){}).getType());
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void update(double delta) {
		time += delta/1000;
		BigDecimal bd = new BigDecimal(time);
		bd = bd.setScale(1, RoundingMode.DOWN);
		//BigDecimal bd = new BigDecimal(delta/1000 + time);
		//time = ().add(time).setScale(1, RoundingMode.DOWN);
		if(LevelMap.containsKey(bd)){
			LevelSet[] currentSet = LevelMap.get(bd);
			for(LevelSet ls : currentSet){
				EnemyFactory.create(ls.Movable,ls.x,ls.y);
			}
			LevelMap.remove(bd);
		}
		
	}
	
	
	/*
	public LevelSpawner(){
		try {
			Gson g = new Gson();
			TypeToken<List<String>> list = new TypeToken<List<String>>() {};
			Collection<LevelSet> ls = g.fromJson(new FileReader("json/level/level1.json"), (new TypeToken<Collection<LevelSet>>(){}).getType()new TypeToken<LevelSet[]>(){}.getType());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
