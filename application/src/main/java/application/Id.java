package application;

import java.io.Serializable;

public abstract class Id<T> implements Serializable {

  public abstract T getValue();
}