package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import observer.DeltaUpdater;
import observer.Observer;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Spawnes Enemies and the Player from a Level-File.
 */
public class Spawner implements Observer{
	protected HashMap<BigDecimal,Spawn[]> LevelMap;
	protected double time = 0;
	protected DeltaUpdater deltaUpdater;
	
	/**
	 * Parses the Json-File corresponding to the given levelname, and registers the
	 * object at the deltaUpdater to receive updates.
	 */
	public Spawner(String LevelName, DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
		this.deltaUpdater.register(this);
		Gson g = new Gson();
		try {
			LevelMap = g.fromJson(new FileReader("json/level/"+LevelName+".json")
			, (new TypeToken<HashMap<BigDecimal,Spawn[]>>(){}).getType());
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
	
	/**
	 * Creates the objects specified in the Json-File at the right time.
	 */
	public void update(double delta) {
		time += delta/1000;
		BigDecimal bd = new BigDecimal(time);
		bd = bd.setScale(1, RoundingMode.DOWN);
		if(LevelMap.containsKey(bd)){
			Spawn[] currentSet = LevelMap.get(bd);
			for(Spawn ls : currentSet){
				EnemyFactory.create(ls.Moveable,ls.x,ls.y);
			}
			LevelMap.remove(bd);
		}
	}
}
