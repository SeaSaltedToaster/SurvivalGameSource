package seaSaltedEngine.basic.event;

public @interface EventHandler {

    boolean ignoreCancelled() default false;
    
}
