package com.ctoutweb.monsuivi.core.factory;

public class InstanceLoader {
  private InstanceLoader() {
   throw new IllegalStateException("La classe InstanceLoader ne peut pas etre instanciée");
  }
  private static class CoreFactoryHolder {
    private static final CoreFactory CORE_FACTORY = new CoreFactory();
  }

  public static CoreFactory getCoreFactory() {
    return CoreFactoryHolder.CORE_FACTORY;
  }
}
