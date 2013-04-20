package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	public HashMap<Integer,LevelSet[]> LevelMap;
	protected double time = 0;
	protected DeltaUpdater deltaUpdater;
	public Spawner(String LevelName, DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
		this.deltaUpdater.register(this);
		Gson g = new Gson();
//		TypeToken<List<String>> list = new TypeToken<List<String>>() {};
		try {
			LevelMap = g.fromJson(new FileReader("json/level/"+LevelName+".json"), (new TypeToken<HashMap<Integer,LevelSet[]>>(){}).getType());
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
		time += delta/1000.0;
		System.out.println(time);
		System.out.println((int)time);
		if(LevelMap.containsKey((int)time)){
			LevelSet[] currentSet = LevelMap.get((int)time);
			for(LevelSet ls : currentSet){
				System.out.println(ls.Movable);
				System.out.println(ls.x);
				System.out.println(ls.y);
			EnemyFactory.create(ls.Movable,ls.x,ls.y);
			}
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
