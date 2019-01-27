import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.settings.GameSettings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class TowerDefenseApp extends GameApplication {

    private IntegerProperty numEnemies;
    private Point2D enemySpawnPoint = new Point2D(50, 0);

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setTitle("Tower Defense");
        gameSettings.setVersion("0,1");
        gameSettings.setWidth(1600);
        gameSettings.setHeight(900);

        gameSettings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initGame(){

        numEnemies = new SimpleIntegerProperty(10);

        BooleanProperty enemiesLeft = new SimpleBooleanProperty();
        enemiesLeft.bind(numEnemies.greaterThan(0));
        getMasterTimer().runAtIntervalWhile(this::spawnEnemy, Duration.seconds(1), enemiesLeft);
    }

    private void spawnEnemy(){

        EntityFactory.spawnEnemy(enemySpawnPoint.getX(), enemySpawnPoint.getY());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
