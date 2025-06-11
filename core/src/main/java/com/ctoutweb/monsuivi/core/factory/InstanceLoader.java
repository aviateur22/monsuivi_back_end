package com.ctoutweb.monsuivi.core.factory;

import com.ctoutweb.monsuivi.core.mapper.CoreMapper;

public class InstanceLoader {
  private InstanceLoader() {
   throw new IllegalStateException("La classe InstanceLoader ne peut pas etre instanciée");
  }
  private static class CoreFactoryHolder {
    private static final CoreFactory CORE_FACTORY = new CoreFactory();
  }

  private static class MapperHolder<T,U> {
    private static final CoreMapper MAPPER = new CoreMapper();
  }

  public static final CoreMapper getMapper() {
    return MapperHolder.MAPPER;
  }

  public static CoreFactory getCoreFactory() {
    return CoreFactoryHolder.CORE_FACTORY;
  }
}
