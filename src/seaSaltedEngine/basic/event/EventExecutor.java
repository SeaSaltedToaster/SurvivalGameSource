package seaSaltedEngine.basic.event;

import seaSaltedEngine.guis.Listener;

public interface EventExecutor {
    public void execute(Listener listener, Event event);
}