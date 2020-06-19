package Data;

import Data.EKGObserver;

public interface EKGObservable extends Runnable {
    void register(EKGObserver ekgObserver);
}
