package managers;

import enums.PlayingStrategyType;
import strategies.PlayStrategy;
import strategies.RandomStrategy;
import strategies.SequentialStrategy;

public class StrategyManager {
    private static StrategyManager instance = null ;
    private SequentialStrategy sequentialStrategy ;
    private RandomStrategy randomStrategy ;
    private StrategyManager() {
        sequentialStrategy = new SequentialStrategy() ;
        randomStrategy = new RandomStrategy() ;
    }

    public static synchronized StrategyManager getInstance() {
        if(instance == null) {
            instance = new StrategyManager() ;
        }
        return instance ;
    }

    public PlayStrategy getStrategy(PlayingStrategyType strategyType) {
        return switch (strategyType) {
            case RANDOM -> randomStrategy;
            default -> sequentialStrategy;
        };
    }
}
